import { User } from './conexiones/user.js';
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Util } from './Util.js';


const registrarse = document.querySelector('.login_button');

registrarse.addEventListener('click',  () => {

  const inputs = document.querySelectorAll('input');



  if (inputs[0].value != '' && inputs[1].value != '') {

    const userCreado = new User(inputs[0].value, inputs[1].value, inputs[2].value);

    userCreado.conexionApi();
  
  } else {
    new cartelAviso('Los campos usuario y contraseña no deben estar vacíos')

  }

});







