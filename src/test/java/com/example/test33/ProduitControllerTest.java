package com.example.test33;

import com.example.test33.Model.Categorie;
import com.example.test33.Model.Produit;
import com.example.test33.Repository.ProduitRepository;
import com.example.test33.Service.CategorieService;
import com.example.test33.Service.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitService produitService;

    @MockBean
    private ProduitRepository produitRepository;

    @MockBean
    private CategorieService categorieService;

    private List<Produit> mockProduits;
    private Produit mockProduit;

    @BeforeEach
    public void setUp() {
        mockProduits = new ArrayList<>();
        mockProduit = new Produit();
        mockProduit.setId(1L);
        mockProduit.setNom("Mock Produit");
        mockProduits.add(mockProduit);
    }

    @Test
    public void testGetAllProduits() throws Exception {
        when(produitService.getAllProduits()).thenReturn(mockProduits);

        mockMvc.perform(MockMvcRequestBuilders.get("/prod")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(mockProduits.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value(mockProduit.getNom()));

        verify(produitService, times(1)).getAllProduits();
        verifyNoMoreInteractions(produitService);
    }

    @Test
    public void testTrierProduitsParCategorie() throws Exception {
        Categorie mockCategorie = new Categorie();
        mockCategorie.setId(1L);
        mockCategorie.setNom("Mock Categorie");

        when(categorieService.getCategorieById(1L)).thenReturn(mockCategorie);
        when(produitRepository.findByCategorie(mockCategorie)).thenReturn(mockProduits);

        mockMvc.perform(MockMvcRequestBuilders.get("/prod/tri?categorie=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(mockProduits.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value(mockProduit.getNom()));

        verify(categorieService, times(1)).getCategorieById(1L);
        verify(produitRepository, times(1)).findByCategorie(mockCategorie);
        verifyNoMoreInteractions(categorieService, produitRepository);
    }

    @Test
    public void testAddProduct() throws Exception {
        Categorie mockCategorie = new Categorie();
        mockCategorie.setId(1L);

        when(categorieService.getCategorieById(1L)).thenReturn(mockCategorie);
        when(produitService.saveProduit(any(Produit.class))).thenReturn(mockProduit);

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/prod/addProduct")
                        .file("image1", image.getBytes())
                        .param("nom", "Nouveau produit t")
                        .param("description", "Description")
                        .param("prix", "50.0")
                        .param("categorie", "1"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Product added successfully."));

        verify(categorieService, times(1)).getCategorieById(1L);
        verify(produitService, times(1)).saveProduit(any(Produit.class));
        verifyNoMoreInteractions(categorieService, produitService);
    }
}
