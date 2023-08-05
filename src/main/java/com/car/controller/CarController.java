package com.car.controller;

import com.car.model.Car;
import com.car.model.Payment;
import com.car.model.Settlement;
import com.car.repository.CarRepository;
import com.car.repository.CategoryRepository;
import com.car.repository.PaymentRepository;
import com.car.repository.SettlementRepository;
import com.car.service.FileUploadUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequestMapping("/car")
@Controller
public class CarController {

    @Autowired
    CarRepository repo;

    @Autowired
    SettlementRepository setRepo;

    @Autowired
    PaymentRepository payRepo;

    @Autowired
    CategoryRepository catRepo;

    @RequestMapping("/list")
    public String home(Model model, HttpServletRequest req) {
        if (req.getSession().getAttribute("usertype").equals("admin"))
            model.addAttribute("datalist", repo.findAll());
        else
            model.addAttribute("datalist", repo.findBySellerId(req.getSession().getAttribute("userid").toString()).get());

        return "car";
    }

    @RequestMapping(value = {"/create", "/edit/{id}"})
    public String create(@PathVariable(required = false) String id, Model model) {
        model.addAttribute("categories", catRepo.findAll());
        Car obj = new Car();
        if (id != null) {
            obj = repo.findById(id).get();
        }

        model.addAttribute("obj", obj);
        return "car_create";
    }

    @RequestMapping("/save")
    public String save(Car obj, @RequestParam("image") MultipartFile multipartFile, HttpServletRequest req) throws IOException {

        if (obj.getId().isBlank()) {
            Optional<Car> idobj = repo.findTopByOrderByIdDesc();
            String carId = null;
            if (idobj.isPresent()) {
                int idnum = Integer.parseInt(idobj.get().getCarId().substring(5));
                idnum++;
                carId = "CAR03" + idnum;
            } else {
                carId = "CAR0362353";
            }

            String id = new ObjectId().toString();
            obj.setId(id);
            obj.setCarId(carId);
            obj.setSellerId(req.getSession().getAttribute("userid").toString());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMMM-dd hh:mm a");
            obj.setPostedDate(dtf.format(LocalDateTime.now()));

            String fileName = multipartFile.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String imgUrl = id + "." + extension;
            obj.setImgUrl(imgUrl);

            repo.save(obj);

            String uploadDir = "uploads";
            FileUploadUtil.saveFile(uploadDir, imgUrl, multipartFile);
        } else {
            // Update Car
            String imgUrl = obj.getImgUrl();
            if (!multipartFile.isEmpty()) {
                String originalFilename = multipartFile.getOriginalFilename();
                String extn = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                imgUrl = obj.getId() + "." + extn;

                String uploadDir = "uploads";
                FileUploadUtil.saveFile(uploadDir, imgUrl, multipartFile);
            }

            Car car = repo.findById(obj.getId()).get();
            car.setCategoryId(obj.getCategoryId());
            car.setBrand(obj.getBrand());
            car.setModel(obj.getModel());
            car.setRegYear(obj.getRegYear());
            car.setRegNumber(obj.getRegNumber());
            car.setEngineCapacity(obj.getEngineCapacity());
            car.setTransmission(obj.getTransmission());
            car.setKmDriven(obj.getKmDriven());
            car.setFuelType(obj.getFuelType());
            car.setNoOfOwner(obj.getNoOfOwner());
            car.setBasePrice(obj.getBasePrice());
            car.setDescription(obj.getDescription());
            car.setImgUrl(imgUrl);

            repo.save(car);
        }

        return "redirect:/car/list";
    }

    @RequestMapping("/details/{id}")
    public String Details(@PathVariable String id, Model model, HttpServletRequest request) {
        Car car = repo.findByCarId(id).get();
        car.setCategory(catRepo.findById(car.getCategoryId()).get());
        model.addAttribute("obj", car);
        return "car_details";
    }


    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model, HttpServletRequest request) {
        Car car = repo.findById(id).get();
        car.setCategory(catRepo.findById(car.getCategoryId()).get());
        model.addAttribute("obj", car);
        return "car_show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<Car> obj = repo.findById(id);
        repo.delete(obj.get());

        return "redirect:/car/list";
    }


    @RequestMapping("/admin/update")
    public String carApprove(@RequestParam String id, @RequestParam double commission) {
        Car car = repo.findById(id).get();
        car.setCommissionPercentage(commission);
        car.setApproved(true);
        repo.save(car);
        return "redirect:/car/show/" + id;
    }

    @RequestMapping("/admin/reject")
    public String carReject(@RequestParam String id) {
        Car car = repo.findById(id).get();
        repo.delete(car);
        return "redirect:/car/list";
    }


    @RequestMapping("/settle/{id}")
    public String Settle(@PathVariable String id, Model model, HttpServletRequest request) {

        Car car = repo.findByCarId(id).get();
        Payment pmt = payRepo.findByCarId(id).get();

        Settlement obj = new Settlement();
        obj.setSellerId(car.getSellerId());
        obj.setPaymentId(pmt.getPaymentId());

        double saleAmount = pmt.getAmount();
        double commissionPercentage = car.getCommissionPercentage();
        double commissionAmount = saleAmount * (commissionPercentage / 100);

        obj.setSaleAmount(saleAmount);
        obj.setCommissionPercentage(commissionPercentage);
        obj.setCommissionAmount(commissionAmount);
        obj.setSettlementStatus("Settled");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMMM-dd hh:mm a");
        obj.setPayDate(dtf.format(LocalDateTime.now()));
        setRepo.save(obj);

        pmt.setSettlementStatus("Settled");
        payRepo.save(pmt);

        return "redirect:/booking/sold";
    }

}
