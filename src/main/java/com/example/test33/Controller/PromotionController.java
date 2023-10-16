package com.example.test33.Controller;

import com.example.test33.Model.Produit;
import com.example.test33.Model.Promotion;
import com.example.test33.Request.PromotionRequest;
import com.example.test33.Service.ProduitService;
import com.example.test33.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




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
                promotionService.deletePromotion(existingPromotion.getId());
            }
            double prixOriginal = produit.getPrix();

            Promotion newPromotion = promotionService.createPromotion(produitId, debutPromo, finPromo, pourcentage);
            return new ResponseEntity<>(newPromotion, HttpStatus.CREATED);
        }


        @GetMapping("/getPrice/{produitId}")
        public ResponseEntity<Double> getPriceWithPromotion(@PathVariable Long produitId) {
            Produit produit = produitService.getProduitById(produitId)
                    .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));

            Promotion promotion = promotionService.getPromotionByProduit(produit);

            if (promotion != null) {
                double prixOriginal = produit.getPrix();
                double pourcentage = promotion.getPourcentage().doubleValue();
                double nouveauPrix = prixOriginal * (1 - pourcentage / 100.0);
                return new ResponseEntity<>(nouveauPrix, HttpStatus.OK);
            }
            return new ResponseEntity<>(produit.getPrix(), HttpStatus.OK);
        }

        @DeleteMapping("delete/{id}")
        public ResponseEntity<?> deletePromotion(@PathVariable Long id) {
            Promotion promotion = promotionService.getPromotionById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Promotion non trouvée"));

            Produit produit = promotion.getProduit();
            promotionService.deletePromotion(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }









