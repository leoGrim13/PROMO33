package com.example.test33;

import com.example.test33.Model.Categorie;
import com.example.test33.Model.Produit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProduitTest {

    @Test
    void testGettersAndSetters() {

        Produit produit = new Produit();

        Long id = 1L;
        String image = "image.jpg";
        String nom = "Produit Test";
        double prix = 29.99;
        String description = "Ceci est un produit de test";
        Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setNom("TestCategory");

        produit.setId(id);
        produit.setImage(image);
        produit.setNom(nom);
        produit.setPrix(prix);
        produit.setDescription(description);
        produit.setCategorie(categorie);

        assertEquals(id, produit.getId());
        assertEquals(image, produit.getImage());
        assertEquals(nom, produit.getNom());
        assertEquals(prix, produit.getPrix());
        assertEquals(description, produit.getDescription());
        assertEquals(categorie, produit.getCategorie());


        Long newId = 2L;
        String newImage = "new_image.jpg";
        String newNom = "New Product";
        double newPrix = 39.99;
        String newDescription = "This is a new product";
        Categorie newCategorie = new Categorie();
        newCategorie.setId(2L);
        newCategorie.setNom("NewTestCategory");

        produit.setId(newId);
        produit.setImage(newImage);
        produit.setNom(newNom);
        produit.setPrix(newPrix);
        produit.setDescription(newDescription);
        produit.setCategorie(newCategorie);

        assertEquals(newId, produit.getId());
        assertEquals(newImage, produit.getImage());
        assertEquals(newNom, produit.getNom());
        assertEquals(newPrix, produit.getPrix());
        assertEquals(newDescription, produit.getDescription());
        assertEquals(newCategorie, produit.getCategorie());
    }
}

