import React, { useState, useEffect } from 'react';

function AddPromo() {
  // État local pour les valeurs du formulaire
  const [produitId, setProduitId] = useState(''); // État pour l'ID du produit sélectionné
  const [produits, setProduits] = useState([]); // État pour la liste des produits
  const [pourcentage, setPourcentage] = useState(0); // État pour le pourcentage de réduction
  const [debutPromo, setDebutPromo] = useState(''); // État pour la date de début de promotion
  const [finPromo, setFinPromo] = useState(''); // État pour la date de fin de promotion

  useEffect(() => {
    // Effet secondaire pour récupérer la liste des produits depuis votre API
    fetch('/prod')
      .then((response) => response.json())
      .then((data) => {
        // Mettre à jour l'état avec la liste des produits
        setProduits(data);
      })
      .catch((error) => {
        console.error('Erreur lors de la récupération des produits : ' + error.message);
      });
  }, []);

  const handleSubmit = () => {
    // Validation des données du formulaire
    if (!produitId) {
      alert('Veuillez sélectionner un produit.');
      return;
    }

    if (pourcentage < 0 || pourcentage > 100) {
      alert('Le pourcentage de réduction doit être compris entre 0 et 100.');
      return;
    }

    const dateDebutObj = new Date(debutPromo);
    const dateFinObj = new Date(finPromo);

    if (dateDebutObj >= dateFinObj) {
      alert('La date de début doit être antérieure à la date de fin de la promotion.');
      return;
    }

    // Préparation des données pour la requête POST
    const Id = produitId; // Utilisez la variable correcte ici (était déjà correct)
    const pourcentageReduction = pourcentage;
    const dateDebut = debutPromo;
    const dateFin = finPromo;

    // Affichage des données pour débogage
    console.log('Données de la promotion :', {
      produitId: Id,
      debutPromo: dateDebut,
      finPromo: dateFin,
      pourcentage: pourcentageReduction,
    });

    // Envoi de la requête POST pour créer la promotion
    fetch('/promotion/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        produitId :Id,
        debutPromo: dateDebut,
        finPromo: dateFin,
        pourcentage: pourcentageReduction,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        // Gérer la réponse de création de promotion
        alert('Promotion créée avec succès');
        setProduitId(''); // Réinitialisation des champs du formulaire
        setPourcentage(0);
        setDebutPromo('');
        setFinPromo('');
      })
      .catch((error) => {
        // Gérer les erreurs de la requête
        alert('Erreur lors de la création de la promotion : ' + error.message);
      });
  };

  return (
    <div>
      <h2>Créer une promotion</h2>
      <label>Sélectionnez un produit :</label>
      <select value={produitId} onChange={(e) => setProduitId(e.target.value)}>
        <option value="">Sélectionnez un produit</option>
        {produits.map((produit) => (
          <option key={produit.id} value={produit.id}>
            {produit.nom}
          </option>
        ))}
      </select>
      <br />
      <label>Pourcentage de réduction :</label>
      <input type="number" value={pourcentage} onChange={(e) => setPourcentage(e.target.value)} />
      <br />
      <label>Date de début de promotion :</label>
      <input type="date" value={debutPromo} onChange={(e) => setDebutPromo(e.target.value)} />
      <br />
      <label>Date de fin de promotion :</label>
      <input type="date" value={finPromo} onChange={(e) => setFinPromo(e.target.value)} />
      <br />
      <button onClick={handleSubmit}>Créer la promotion</button>
    </div>
  );
}

export default AddPromo;
