package com.example.test33.Controller;

import com.example.test33.Model.Produit;
import com.example.test33.Model.Promotion;
import com.example.test33.Request.PromotionRequest;
import com.example.test33.Service.ProduitService;
import com.example.test33.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



    @RestController
    @RequestMapping("/promotion")
    public class PromotionController {
        private final PromotionService promotionService;
        private final ProduitService produitService;

        @Autowired
        public PromotionController(PromotionService promotionService, ProduitService produitService) {
            this.promotionService = promotionService;
            this.produitService = produitService;
        }

        @PostMapping("/create")
        public ResponseEntity<Promotion> createPromotion(@RequestBody PromotionRequest promotionRequest) {
            Long produitId = promotionRequest.getProduitId();
            String debutPromo = promotionRequest.getDebutPromo();
            String finPromo = promotionRequest.getFinPromo();
            double pourcentage = promotionRequest.getPourcentage();

            Produit produit = produitService.getProduitById(produitId)
                    .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));

            Promotion existingPromotion = promotionService.getPromotionByProduit(produit);

            if (existingPromotion != null) {
                // Supprimez la promotion existante
                promotionService.deletePromotion(existingPromotion.getId());
            }

            // Récupérez le prix actuel du produit
            double prixOriginal = produit.getPrix();

            // Ajoutez la nouvelle promotion
            Promotion newPromotion = promotionService.createPromotion(produitId, debutPromo, finPromo, pourcentage);

            return new ResponseEntity<>(newPromotion, HttpStatus.CREATED);
        }


        @GetMapping("/getPrice/{produitId}")
        public ResponseEntity<Double> getPriceWithPromotion(@PathVariable Long produitId) {
            Produit produit = produitService.getProduitById(produitId)
                    .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));

            Promotion promotion = promotionService.getPromotionByProduit(produit);

            if (promotion != null) {
                // Calculer le prix avec réduction
                double prixOriginal = produit.getPrix();
                double pourcentage = promotion.getPourcentage().doubleValue();
                double nouveauPrix = prixOriginal * (1 - pourcentage / 100.0);
                return new ResponseEntity<>(nouveauPrix, HttpStatus.OK);
            }

            // Pas de promotion en cours, renvoyer le prix d'origine
            return new ResponseEntity<>(produit.getPrix(), HttpStatus.OK);
        }

        @DeleteMapping("delete/{id}")
        public ResponseEntity<?> deletePromotion(@PathVariable Long id) {
            Promotion promotion = promotionService.getPromotionById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Promotion non trouvée"));

            Produit produit = promotion.getProduit();

            // Supprimez la promotion de la base de données
            promotionService.deletePromotion(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }









