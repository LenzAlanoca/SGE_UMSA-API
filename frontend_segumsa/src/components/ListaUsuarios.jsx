import React, {useEffect,useState} from 'react';
import { obtenerUsuarios } from '../api/usuarios';
const ListaUsuarios = () =>{
    const [usuarios, setUsuarios] = useState([]);

    useEffect(()=>{
        obtenerUsuarios()
        .then((response) =>{
            console.log('Respuesta de la API:', response.data);
            setUsuarios(response.data);
        })
        .catch(error => {
            console.error('Error al obtener usuarios: ',error)
        });
    },[]);

    return(
        <div>
            <h1>Lista de Usuarios</h1>
            <ul>
                {usuarios.length > 0 ? (
                    usuarios.map((usuario) => (
                        <li key={usuario.ci}>{usuario.nombre}</li> // Asegúrate que estos campos existen
                    ))
                    ) : (
                    <li>No hay usuarios disponibles</li>
                    )}
            </ul>
        </div>
    );
};

export default ListaUsuarios;
