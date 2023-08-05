package com.car.controller;

import com.car.model.Buyer;
import com.car.model.Car;
import com.car.repository.BuyerRepository;
import com.car.repository.CarRepository;
import com.car.repository.CategoryRepository;
import com.car.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/buyer")
@Controller
public class BuyerController {

    @Autowired
    BuyerRepository repo;

    @Autowired
    CarRepository carRepo;

    @Autowired
    CategoryRepository catRepo;

    @Autowired
    PaymentRepository payRepo;

    public static String getUId(HttpServletRequest req) {
        return req.getSession().getAttribute("userid").toString();
    }

    public static String getUType(HttpServletRequest req) {
        return req.getSession().getAttribute("usertype").toString();
    }

    @RequestMapping("index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/home")
    public String home(
            @RequestParam(value = "catId", required = false) String catId,
            @RequestParam(value = "fuelType", required = false) String fuelType,
            @RequestParam(value = "budget", required = false) String budget,
            Model model, HttpServletRequest req) {

        List<Car> dataList = carRepo.findAllByIsApprovedAndIsSold(true, false).get();

        if (catId != null && !catId.isBlank() && fuelType.isBlank() && budget.isBlank()) {
            dataList = carRepo.findAllByCategoryIdAndIsApprovedAndIsSold(catId, true, false).get();
        }
        if (catId != null && catId.isBlank() && !fuelType.isBlank() && budget.isBlank()) {
            dataList = carRepo.findAllByFuelTypeAndIsApprovedAndIsSold(fuelType, true, false).get();
        }
        if (catId != null && catId.isBlank() && fuelType.isBlank() && !budget.isBlank()) {
            BudgetResult result = new BudgetResult(budget);
            if (result.budgetLte == 0) {
                dataList = carRepo.findAllByBasePriceGreaterThanEqualAndIsApprovedAndIsSold(result.budgetGte, true, false).get();
            } else {
                dataList = carRepo.findAllByBasePriceBetweenAndIsApprovedAndIsSold(result.budgetGte, result.budgetLte, true, false).get();
            }
        }
        if (catId != null && !catId.isBlank() && !fuelType.isBlank() && budget.isBlank()) {
            dataList = carRepo.findAllByCategoryIdAndFuelTypeAndIsApprovedAndIsSold(catId, fuelType, true, false).get();
        }
        if (catId != null && !catId.isBlank() && fuelType.isBlank() && !budget.isBlank()) {
            BudgetResult result = new BudgetResult(budget);
            if (result.budgetLte == 0) {
                dataList = carRepo.findAllByCategoryIdAndBasePriceGreaterThanEqualAndIsApprovedAndIsSold(catId, result.budgetGte, true, false).get();
            } else {
                dataList = carRepo.findAllByCategoryIdAndBasePriceBetweenAndIsApprovedAndIsSold(catId, result.budgetGte, result.budgetLte, true, false).get();
            }
        }
        if (catId != null && catId.isBlank() && !fuelType.isBlank() && !budget.isBlank()) {
            BudgetResult result = new BudgetResult(budget);
            if (result.budgetLte == 0) {
                dataList = carRepo.findAllByFuelTypeAndBasePriceGreaterThanEqualAndIsApprovedAndIsSold(fuelType, result.budgetGte, true, false).get();
            } else {
                dataList = carRepo.findAllByFuelTypeAndBasePriceBetweenAndIsApprovedAndIsSold(fuelType, result.budgetGte, result.budgetLte, true, false).get();
            }
        }
        if (catId != null && !catId.isBlank() && !fuelType.isBlank() && !budget.isBlank()) {
            BudgetResult result = new BudgetResult(budget);
            if (result.budgetLte == 0) {
                dataList = carRepo.findAllByCategoryIdAndFuelTypeAndBasePriceGreaterThanEqualAndIsApprovedAndIsSold(catId, fuelType, result.budgetGte, true, false).get();
            } else {
                dataList = carRepo.findAllByCategoryIdAndFuelTypeAndAndBasePriceBetweenAndIsApprovedAndIsSold(catId, fuelType, result.budgetGte, result.budgetLte, true, false).get();
            }
        }
        model.addAttribute("catId", catId);
        model.addAttribute("fuelType", fuelType);
        model.addAttribute("budget", budget);
        model.addAttribute("datalist", dataList);
        model.addAttribute("categories", catRepo.findAll());
        return "buy_home";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("datalist", repo.findAll());
        return "buy";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "buy_create";
    }

    @RequestMapping("/save")
    public String save(Buyer obj, Model model) {
        if (!repo.findByEmailId(obj.getEmailId()).isPresent()) {
            Optional<Buyer> idobj = repo.findTopByOrderByIdDesc();
            String id = null;
            if (idobj.isPresent()) {
                int idnum = Integer.parseInt(idobj.get().getBuyerId().substring(5));
                idnum++;
                id = "BUYER" + idnum;
            } else {
                id = "BUYER64901";
            }

            obj.setBuyerId(id);
            repo.save(obj);
            return "redirect:/account";
        } else {
            model.addAttribute("buyermsg", "This email Id already registered !!!");
            return "login";
        }
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("obj", repo.findById(id).get());
        return "buy_show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<Buyer> obj = repo.findById(id);
        repo.delete(obj.get());

        return "redirect:/buyer/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest req) {
        model.addAttribute("obj", repo.findByBuyerId(req.getSession().getAttribute("userid").toString()).get());
        return "buy_edit";
    }

    @RequestMapping("/edit/{id}")
    public String adminEdit(Model model, @PathVariable String id, HttpServletRequest req) {
        model.addAttribute("obj", repo.findById(id).get());
        return "buy_edit";
    }

    @RequestMapping("/update")
    public String update(Buyer obj, HttpServletRequest req) {
        repo.save(obj);
        if (getUType(req).equals("buyer"))
            return "redirect:/buyer/home/";
        else
            return "redirect:/account/home/";

    }

    @RequestMapping("/booked")
    public String BookedCars(Buyer obj, Model model, HttpServletRequest req) {
        String uid = req.getSession().getAttribute("userid").toString();
        model.addAttribute("datalist", payRepo.findAllByBuyerIdAndStatusEquals(uid, "Booked").get());
        return "buy_booked";
    }

    @RequestMapping("/purchased")
    public String purchasedCars(Buyer obj, HttpServletRequest req) {
        repo.save(obj);
        if (getUType(req).equals("buyer"))
            return "redirect:/buyer/home/";
        else
            return "redirect:/account/home/";

    }

    private static class BudgetResult {
        public String budgetValue;
        public double budgetGte = 0;
        public double budgetLte = 0;

        public BudgetResult(String budgetValue) {
            this.budgetValue = budgetValue;
            this.result();
        }

        public void result() {
            double budgetGte = 0;
            double budgetLte = 0;
            switch (this.budgetValue) {
                case "1":
                    this.budgetGte = 5000;
                    this.budgetLte = 10000;
                    break;
                case "2":
                    this.budgetGte = 10000;
                    this.budgetLte = 40000;
                    break;
                case "3":
                    this.budgetGte = 40000;
                    this.budgetLte = 70000;
                    break;
                case "4":
                    this.budgetGte = 70000;
                    break;
            }
        }
    }
}
