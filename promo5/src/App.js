import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import AdminForm from './AdminForm';
import Catalogue from './Catalogue';  
import AdminManager from './AdminManager';

function App() {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('/categorie')
      .then((response) => {
        const toutesLesCategories = [
          { id: '', nom: 'Tous les produits' },  // Utilisez une chaîne vide pour tous les produits
          ...response.data
        ];
        setCategories(toutesLesCategories);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  useEffect(() => {
    if (selectedCategory) {
      axios.get('/categorie?categorieId=' + selectedCategory)

        .then((response) => {
          setProducts(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }, [selectedCategory]);

  return (
    <Router>
      <div>
        <h1>Promo Mercadona</h1>
        <div>
          <Link to="/catalogue">Catalogue</Link>
          <Link to="/admin">Admin</Link>
        </div>
        
       <Routes>
          <Route
            path="/catalogue"
            element={<Catalogue products={products} categories={categories} selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory} />}
          />
          <Route path="/admin" element={<AdminForm />} />
          <Route path="/admin-manager" element={<AdminManager />} />

      </Routes>
      </div>
    </Router>
  );
}

export default App;
