package com.example.test33.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produit produit;

    @Temporal(TemporalType.DATE)
    private Date debutpromo;

    @Temporal(TemporalType.DATE)
    private Date finpromo;
    @Column(name = "pourcentage")
    private BigDecimal pourcentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Date getDebutpromo() {
        return debutpromo;
    }

    public void setDebutpromo(Date debutpromo) {
        this.debutpromo = debutpromo;
    }

    public Date getFinpromo() {
        return finpromo;
    }

    public void setFinpromo(Date finpromo) {
        this.finpromo = finpromo;
    }

    public BigDecimal getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(BigDecimal pourcentage) {
        this.pourcentage = pourcentage;
    }

}
