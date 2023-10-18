package com.example.test33.Repository;
import com.example.test33.Model.Produit;
import com.example.test33.Model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByProduit(Produit produit);
}
