import {User} from './conecciones/user.js';


const userCreado = new User('prueba101@hotmail.com', '123');

const respuesta =  userCreado.conexionApi().then(user => {
    console.log('Usuario creado:', user);
  })
  .catch(error => {
    console.error('Error:', error);
  });


