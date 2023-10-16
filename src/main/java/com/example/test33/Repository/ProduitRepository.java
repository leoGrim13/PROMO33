package com.example.test33.Repository;


import com.example.test33.Model.Categorie;
import com.example.test33.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorie(Categorie categorie);
    Optional<Produit> findById(Long id);

}

