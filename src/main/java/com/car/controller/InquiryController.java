package com.car.controller;

import com.car.model.Car;
import com.car.model.Inquiry;
import com.car.repository.CarRepository;
import com.car.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/inquiry")
@Controller
public class InquiryController {
    @Autowired
    InquiryRepository inqRepo;

    @Autowired
    CarRepository carRepo;

    @RequestMapping("/list")
    public String home(Model model, HttpServletRequest req) {
        String utype = req.getSession().getAttribute("usertype").toString();
        String uid = req.getSession().getAttribute("userid").toString();

        List<Inquiry> dataList = new ArrayList<>();
        if (utype.equals("buyer"))
            dataList = inqRepo.findAllByBuyerIdOrderByIdDesc(uid).get();

        else if (utype.equals("seller"))
            dataList = inqRepo.findAllBySellerIdOrderByIdDesc(uid).get();
        else
            dataList = (List<Inquiry>) inqRepo.findAll();


        model.addAttribute("datalist", dataList);
        return "inquiry_list";
    }

    @RequestMapping(value = {"/add/{cid}"})
    public String add(@PathVariable String cid, Model model, HttpServletRequest req) {
        Car car = carRepo.findByCarId(cid).get();
        model.addAttribute("car", car);
        return "inquiry_add";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addPost(Inquiry obj, Model model, HttpServletRequest req) {
        String uid = req.getSession().getAttribute("userid").toString();
        obj.setBuyerId(uid);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMMM-dd hh:mm a");
        obj.setInquiryDate(dtf.format(LocalDateTime.now()));

        inqRepo.save(obj);

        return "redirect:/inquiry/list";
    }

    @RequestMapping(value = {"/respond/{eid}"})
    public String respond(@PathVariable String eid, Model model) {
        model.addAttribute("obj", inqRepo.findById(eid).get());
        return "inquiry_response";
    }

    @RequestMapping(value = {"/respond"}, method = RequestMethod.POST)
    public String respondPost(Inquiry obj, Model model, HttpServletRequest req) {
        Inquiry inq = inqRepo.findById(obj.getId()).get();
        inq.setResponse(obj.getResponse());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMMM-dd hh:mm a");
        obj.setResponseDate(dtf.format(LocalDateTime.now()));
        
        inqRepo.save(inq);

        return "redirect:/inquiry/list";
    }
}
