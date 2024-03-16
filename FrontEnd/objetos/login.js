import { User } from './conecciones/user.js';
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Util } from './Util.js';


const registrarse = document.querySelector('.login_button');

registrarse.addEventListener('click', async () => {
  
  const inputs = document.querySelectorAll('input');
  const a = document.querySelector('#login');


  if (inputs[0].value != '' && inputs[1].value != '') {

    const userCreado = new User(inputs[0].value, inputs[1].value);




    let respuesta = await userCreado.login();
    
    if(await respuesta == null) new cartelAviso('Lo siento, algo salió mal, vuelve más tarde a intentarlo');


    if (await respuesta != null) Util.guardarUsuario(respuesta);
    
    if (await respuesta != null) a.href = "invitaAregistrar.html";
    
    //alert(Util.reuperarUsuario());

  } else {
    new cartelAviso('Los campos usuario y contraseña no deben estar vacíos')

  }

});







