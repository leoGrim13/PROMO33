package com.example.test33.Service;

import com.example.test33.Model.Produit;
import com.example.test33.Model.Promotion;
import com.example.test33.Repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final ProduitService produitService;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, ProduitService produitService) {
        this.promotionRepository = promotionRepository;
        this.produitService = produitService;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    public Promotion createPromotion(Long produitId, String debutPromo, String finPromo, double pourcentage) {
        Optional<Produit> produitOptional = produitService.getProduitById(produitId);

        if (produitOptional.isEmpty()) {
            throw new IllegalArgumentException("Produit non trouvé");
        }

        Produit produit = produitOptional.get();

        Promotion promotion = new Promotion();
        promotion.setProduit(produit);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date debutPromoDate = sdf.parse(debutPromo);
            Date finPromoDate = sdf.parse(finPromo);
            promotion.setDebutpromo(debutPromoDate);
            promotion.setFinpromo(finPromoDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Format de date invalide");
        }

        promotion.setPourcentage(BigDecimal.valueOf(pourcentage));

        promotionRepository.save(promotion);

        return promotion;
    }

    public void deletePromotion(Long promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new IllegalArgumentException("Promotion non trouvée"));

        Produit produit = promotion.getProduit();


        promotionRepository.delete(promotion);
    }

    public Promotion getPromotionByProduit(Produit produit) {
        return promotionRepository.findByProduit(produit);
    }
}




