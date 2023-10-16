package com.example.test33.Controller;

import com.example.test33.Model.Categorie;
import com.example.test33.Model.Produit;
import com.example.test33.Repository.ProduitRepository;
import com.example.test33.Service.CategorieService;
import com.example.test33.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prod")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @Autowired
    private ProduitRepository produitRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
    @GetMapping("/tri")
    public List<Produit> trierProduitsParCategorie(@RequestParam(name = "categorie") Long Id) {
        Categorie categorie = categorieService.getCategorieById(Id);
        if (categorie == null) {
            return new ArrayList<>();
        }
        return produitRepository.findByCategorie(categorie);
    }




    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam("prix") double prix,
            @RequestParam("image1") MultipartFile image,
            @RequestParam("categorie") long Id) {

        try {
            String originalFilename = image.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFilename;

            String currentWorkingDir = System.getProperty("user.dir");
            String filePath = Paths.get(uploadPath, uniqueFileName).toString();
            File uploadDirectory = new File(filePath).getParentFile();

            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            try (OutputStream os = new FileOutputStream(filePath)) {
                os.write(image.getBytes());
            }

            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setDescription(description);
            produit.setPrix(prix);
            produit.setImage(uniqueFileName);
            Categorie categorie = categorieService.getCategorieById(Id);
            produit.setCategorie(categorie);

            produitService.saveProduit(produit);

            return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to add product. Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}









