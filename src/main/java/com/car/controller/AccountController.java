package com.car.controller;

import com.car.model.Buyer;
import com.car.model.Login;
import com.car.model.Seller;
import com.car.repository.BuyerRepository;
import com.car.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired
    BuyerRepository buyerRepo;

    @Autowired
    SellerRepository sellerRepo;

    @RequestMapping()
    public String login(Model model) {
        return "temp_dup";
    }

    @RequestMapping("/login/buyerLogin")
    public String createBuyer(Model model) {
        return "buyerLogin";
    }

    @RequestMapping("/login/sellerLogin")
    public String createSeller(Model model) {
        return "sellerLogin";
    }

    @RequestMapping("/login/adminLogin")
    public String createAdmin(Model model) {
        return "adminLogin";
    }

    @PostMapping("/login/buyer")
    public String buyerLogin(Login login, Model model, HttpServletRequest request) {
        Optional<Buyer> obj = buyerRepo.findByEmailIdAndPassword(login.getEmail(), login.getPassword());
        if (obj.isPresent()) {
            model.addAttribute("name", obj.get().getName());
            request.getSession().setAttribute("id", obj.get().getId());
            request.getSession().setAttribute("userid", obj.get().getBuyerId());
            request.getSession().setAttribute("usertype", "buyer");
            request.getSession().setAttribute("name", obj.get().getName());
            return "redirect:/buyer/home";
        } else {
            model.addAttribute("buyermsg", "Invalid Login Credentials");
            return "buyerLogin";
        }
    }

    @PostMapping("/login/seller")
    public String sellerLogin(Login login, Model model, HttpServletRequest request) {
        Optional<Seller> obj = sellerRepo.findByEmailIdAndPassword(login.getEmail(), login.getPassword());
        if (obj.isPresent()) {
            model.addAttribute("name", obj.get().getName());
            request.getSession().setAttribute("id", obj.get().getId());
            request.getSession().setAttribute("userid", obj.get().getSellerId());
            request.getSession().setAttribute("usertype", "seller");
            request.getSession().setAttribute("name", obj.get().getName());
            return "redirect:/seller/home";
        } else {
            model.addAttribute("sellermsg", "Invalid Login Credentials");
            return "sellerLogin";
        }
    }


    @PostMapping("/login/admin")
    public String adminLogin(Login login, Model model, HttpServletRequest request) {
        if (login.getEmail().equals("admin") && login.getPassword().equals("admin")) {
            model.addAttribute("name", "Admin");
            request.getSession().setAttribute("id", "Admin");
            request.getSession().setAttribute("userid", "Admin");
            request.getSession().setAttribute("usertype", "admin");
            request.getSession().setAttribute("name", "Admin");
            return "redirect:/account/home";
        } else {
            model.addAttribute("adminmsg", "Invalid Login Credentials");
            return "temp_dup";
        }
    }


    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest req) {
        return "admin_home";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        return "logout";
    }


}
