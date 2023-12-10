package com.example.test33;

import com.example.test33.Model.Produit;
import com.example.test33.Repository.ProduitRepository;
import com.example.test33.Service.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitService produitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduit() {
        Produit mockProduit = new Produit();
        mockProduit.setId(1L);
        mockProduit.setNom("Mock Produit");

        when(produitRepository.save(any(Produit.class))).thenReturn(mockProduit);

        Produit savedProduit = produitService.saveProduit(new Produit());

        assertEquals(mockProduit.getId(), savedProduit.getId());
        assertEquals(mockProduit.getNom(), savedProduit.getNom());

        verify(produitRepository, times(1)).save(any(Produit.class));
        verifyNoMoreInteractions(produitRepository);
    }

    @Test
    void testGetAllProduits() {
        List<Produit> mockProduits = new ArrayList<>();
        mockProduits.add(new Produit());
        mockProduits.add(new Produit());

        when(produitRepository.findAll()).thenReturn(mockProduits);

        List<Produit> produits = produitService.getAllProduits();

        assertEquals(mockProduits.size(), produits.size());

        verify(produitRepository, times(1)).findAll();
        verifyNoMoreInteractions(produitRepository);
    }

    @Test
    void testGetProduitById() {
        Long productId = 1L;
        Produit mockProduit = new Produit();
        mockProduit.setId(productId);
        mockProduit.setNom("Mock Produit");

        when(produitRepository.findById(productId)).thenReturn(Optional.of(mockProduit));

        Optional<Produit> produit = produitService.getProduitById(productId);

        assertEquals(mockProduit.getId(), produit.get().getId());
        assertEquals(mockProduit.getNom(), produit.get().getNom());

        verify(produitRepository, times(1)).findById(productId);
        verifyNoMoreInteractions(produitRepository);
    }
}
