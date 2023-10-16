import React, { useState } from 'react';
import CreateProduct from './CreateProduct'; 
import AddPromo from './addPromo';

function AdminManager() {
  const [isCreateProductFormVisible, setCreateProductFormVisible] = useState(false);
  const [isAddPromoFormVisible, setAddPromoFormVisible] = useState(false)

  const handleCreateProductClick = () => {
    setCreateProductFormVisible(true);
  };

  const handleAddPromoClick = () => {
    setAddPromoFormVisible(true);
  };

  return (
    <div>
      <button onClick={handleCreateProductClick}>Créer Produit</button>
      {isCreateProductFormVisible && <CreateProduct />}
      <button onClick={handleAddPromoClick}>Ajouter Promotion</button>
      {isAddPromoFormVisible && <AddPromo />}
    </div>
  );
}

export default AdminManager;


