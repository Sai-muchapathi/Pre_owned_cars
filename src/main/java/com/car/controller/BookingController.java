package com.car.controller;

import com.car.model.Car;
import com.car.model.Payment;
import com.car.repository.CarRepository;
import com.car.repository.PaymentRepository;
import com.car.repository.SettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequestMapping("/booking")
@Controller
public class BookingController {

    @Autowired
    CarRepository carRepo;

    @Autowired
    PaymentRepository payRepo;

    @Autowired
    SettlementRepository setRepo;

    static String ymd_dmy(String date) throws ParseException {
        String arr[] = date.split("-");
        return arr[2] + "-" + arr[1] + "-" + arr[0];
    }

    @RequestMapping("/list")
    public String bookingList(Model model, HttpServletRequest req) {
        String utype = req.getSession().getAttribute("usertype").toString();
        String uid = req.getSession().getAttribute("userid").toString();
        if (utype.equals("buyer"))
            model.addAttribute("datalist", payRepo.findAllByBuyerIdAndStatusEquals(uid, "Booked").get());
        else if (utype.equals("seller"))
            model.addAttribute("datalist", payRepo.findAllBySellerIdAndStatusEquals(uid, "Booked").get());
        else
            model.addAttribute("datalist", payRepo.findAllByStatus("Booked").get());

        return "booking_list";
    }

    @RequestMapping("/create/{id}")
    public String create(@PathVariable String id, Model model, HttpServletRequest request) {
        model.addAttribute("car", carRepo.findById(id).get());
        return "booking_create";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable String id, Model model, HttpServletRequest request) {
        model.addAttribute("car", payRepo.findById(id).get());
        return "booking_update";
    }

    @RequestMapping("/update/pay")
    public String updatePay(Payment obj, Model model, HttpServletRequest request) {
        obj.setPaymentStatus("Paid");
        obj.setStatus("Sold");
        payRepo.save(obj);

        Car car = carRepo.findByCarId(obj.getCarId()).get();
        car.setSold(true);
        carRepo.save(car);

        return "redirect:/booking/list";
    }

    @RequestMapping("/save")
    public String saveBooking(Payment obj, HttpServletRequest req) throws IOException {
        Optional<Payment> idobj = payRepo.findTopByOrderByIdDesc();
        String id = null;
        if (idobj.isPresent()) {
            int idnum = Integer.parseInt(idobj.get().getPaymentId().substring(5));
            idnum++;
            id = "BOOK3" + idnum;
        } else {
            id = "BOOK362353";
        }

        Car car = carRepo.findByCarId(obj.getCarId()).get();

        obj.setPaymentId(id);
        obj.setSellerId(car.getSellerId());
        obj.setBuyerId(req.getSession().getAttribute("userid").toString());


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMMM-dd hh:mm a");
        obj.setBookingDate(dtf.format(LocalDateTime.now()));
        obj.setPaymentStatus("Advance Paid");
        obj.setStatus("Booked");
        payRepo.save(obj);

        car.setBooked(true);
        carRepo.save(car);
        return "redirect:/booking/list";

    }

    @RequestMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable String id, HttpServletRequest req) throws IOException {

        Payment pay = payRepo.findById(id).get();
        pay.setStatus("Cancelled");
        payRepo.save(pay);

        Car car = carRepo.findByCarId(pay.getCarId()).get();
        car.setBooked(false);
        car.setSold(false);
        carRepo.save(car);

        return "redirect:/booking/list";
    }

    @RequestMapping("/sold/{carId}")
    public String empUpdate(@PathVariable String carId) {
        Car obj = carRepo.findByCarId(carId).get();
        obj.setSold(true);
        carRepo.save(obj);

        Payment payment = payRepo.findByCarId(carId).get();
        payment.setStatus("SOLD");
        payRepo.save(payment);
        return "redirect:/car/detail/" + carId;
    }

    @RequestMapping(value = {"/sold", "/purchased"})
    public String soldList(Model model, HttpServletRequest req) {
        String utype = req.getSession().getAttribute("usertype").toString();
        String uid = req.getSession().getAttribute("userid").toString();
        if (utype.equals("buyer"))
            model.addAttribute("datalist", payRepo.findAllByBuyerIdAndStatusEquals(uid, "Sold").get());
        else if (utype.equals("seller"))
            model.addAttribute("datalist", payRepo.findAllBySellerIdAndStatusEquals(uid, "Sold").get());
        else
            model.addAttribute("datalist", payRepo.findAllByStatus("Sold").get());

        return "sold_list";
    }

    @RequestMapping(value = {"/settlements"})
    public String settlements(Model model, HttpServletRequest req) {
        String utype = req.getSession().getAttribute("usertype").toString();
        String uid = req.getSession().getAttribute("userid").toString();

        if (utype.equals("seller"))
            model.addAttribute("datalist", setRepo.findAllBySellerId(uid).get());
        else
            model.addAttribute("datalist", setRepo.findAll());

        return "settlement_list";
    }
}
