import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function AdminForm() {
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    if (!email || !username || !password) {
      alert('Veuillez remplir tous les champs.');
      return;
    }
  
    try {
      const response = await axios.post('/log', {
        email,
        username,
        password,
      });
  
      if (response.status === 200 && response.data === "BRAVO") {
        navigate('/admin-manager');
      } else {

        alert('Authentification échouée');
      }
    } catch (error) {
      console.error(error);
    }
  };
  

  return (
    <div>
      <h2>Formulaire Admin</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="text"
          placeholder="Nom d'utilisateur"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Mot de passe"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Valider</button>
      </form>
    </div>
  );
}

export default AdminForm;
