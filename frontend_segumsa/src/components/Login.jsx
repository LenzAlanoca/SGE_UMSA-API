import React, { useState } from 'react';
import { obtenerUsuarioCi } from '../api/usuarios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [ci, setCi] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await obtenerUsuarioCi(ci);
      if (response.data) {
        console.log('Usuario encontrado:', response.data);
        // Redirigir a "panel" y pasarle el usuario, por ejemplo usando localStorage:
        localStorage.setItem('usuario', JSON.stringify(response.data));
        navigate('/panel'); // Ruta al panel
      } else {
        setError('Usuario no encontrado');
      }
    } catch (err) {
      console.error('Error en login:', err);
      setError('Usuario no encontrado');
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <label>CI:</label>
        <input
          type="text"
          value={ci}
          onChange={(e) => setCi(e.target.value)}
        />
        <button type="submit">Entrar</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
};

export default Login;
