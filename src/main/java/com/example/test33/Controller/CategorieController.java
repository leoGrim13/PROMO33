package com.example.test33.Controller;


import com.example.test33.Model.Categorie;
import com.example.test33.Service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

    @RestController
    @RequestMapping("/categorie")
    public class CategorieController {
        private final CategorieService categorieService;

        @Autowired
        public CategorieController(CategorieService categorieService) {
            this.categorieService = categorieService;
        }

        @GetMapping
        public List<Categorie> getAllCategories() {
            return categorieService.getAllCategories();
        }

    }


