package com.example.test33.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorie")
    public class Categorie {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nom;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }
    }
