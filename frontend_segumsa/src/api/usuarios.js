import axios from 'axios'

const API_URL = 'http://localhost:8081/api/usuarios'

export const obtenerUsuarios = () =>{
    return axios.get(API_URL);
}

export const crearUsuario = (usuarionuevo) =>{
    return axios.post(API_URL,usuarionuevo);
}

export const obtenerUsuarioCi = (ci) => 
    axios.get(`${API_URL}/${ci}`);

export const actualizarUsuario = (user,usuariodatos) => 
    axios.put(`${API_URL}/${user}`,usuariodatos);

export const eliminarUsuario = (user)=> 
    axios.delete(`${API_URL}/${user}`);