import React from 'react';

const Panel = () => {
  const usuario = JSON.parse(localStorage.getItem('usuario'));

  if (!usuario) {
    return <div>No autorizado</div>;
  }

  return (
    <div>
      <h1>Bienvenido, {usuario.nombre}!</h1>
      <p>CI: {usuario.ci}</p>
      <p>Email: {usuario.email}</p>
      {/* Lo que quieras mostrar */}
    </div>
  );
};

export default Panel;
