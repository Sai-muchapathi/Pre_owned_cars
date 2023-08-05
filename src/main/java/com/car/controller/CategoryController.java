package com.car.controller;

import com.car.model.Car;
import com.car.model.Category;
import com.car.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/category")
@Controller
public class CategoryController {

    @Autowired
    CategoryRepository catRepo;

    @RequestMapping("/list")
    public String listCategory(@RequestParam(value = "id", required = false) String id, Model model) {
        model.addAttribute("datalist", catRepo.findAll());

        Category obj = new Category();
        if (id != null) {
            obj = catRepo.findById(id).get();
        }

        model.addAttribute("obj", obj);
        return "category_list";
    }

    @RequestMapping("/save")
    public String create(Category obj, RedirectAttributes reAttr) {
        String msg = "";

        if (obj.getId().isBlank()) {
            Optional<Category> idobj = catRepo.findTopByOrderByIdDesc();
            String id = null;
            if (idobj.isPresent()) {
                int idnum = Integer.parseInt(idobj.get().getCatId().substring(5));
                idnum++;
                id = "CAT23" + idnum;
            } else {
                id = "CAT2370001";
            }

            obj.setId(null);
            obj.setCatId(id);
            catRepo.save(obj);
            msg = "Category Created Successfully";
        } else {
            //Update Category
            catRepo.save(obj);
            msg = "Category Updated Successfully";
        }
        System.out.println(msg);
        reAttr.addFlashAttribute("msg", msg);
        return "redirect:/category/list";
    }
}
