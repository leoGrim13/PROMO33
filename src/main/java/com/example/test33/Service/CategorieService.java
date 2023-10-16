package com.example.test33.Service;


import com.example.test33.Model.Categorie;
import com.example.test33.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CategorieService {
        private final CategorieRepository categorieRepository;

        @Autowired
        public CategorieService(CategorieRepository categorieRepository) {
            this.categorieRepository = categorieRepository;
        }

        public List<Categorie> getAllCategories() {
            return categorieRepository.findAll();
        }

        public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
        }

}


