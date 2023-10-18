package com.example.test33.Request;

public class PromotionRequest {
    private Long produitId;
    private String debutPromo;
    private String finPromo;
    private double pourcentage;

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getDebutPromo() {
        return debutPromo;
    }

    public void setDebutPromo(String debutPromo) {
        this.debutPromo = debutPromo;
    }

    public String getFinPromo() {
        return finPromo;
    }

    public void setFinPromo(String finPromo) {
        this.finPromo = finPromo;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

}