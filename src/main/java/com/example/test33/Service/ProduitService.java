package com.example.test33.Service;


import com.example.test33.Model.Categorie;
import com.example.test33.Model.Produit;
import com.example.test33.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private ProduitRepository produitRepository;

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }


}













