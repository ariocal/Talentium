import { User } from './conecciones/user.js';
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import * as util from './Util.js';


const registrarse = document.querySelector('.login_button');

registrarse.addEventListener('click', async () => {

  const inputs = document.querySelectorAll('input');



  if (inputs[0].value != '' && inputs[1].value != '') {

    const userCreado = new User(inputs[0].value, inputs[1].value, inputs[2].value);

   // const respuesta = null;

    const respuesta = await userCreado.conexionApi().then(user => {
      console.log('user: '+ user)
  
    }).catch(error => {
      //console.error('Error:', error);
      new cartelAviso('Lo siento, algo salio mal. vuelve mas tarde a intentarlo');

    });

    await console.log('respuesta:', respuesta);
    if ( await respuesta != null) {
      await  util.guardarUsuario(respuesta);
      await  console.log(util.reuperarUsuario());
      registrarse.href = "invitaAregistrar.html";
    }

  } else {
    new cartelAviso('los campos usuario y contraseña no deben estar vacios')

  }

});







