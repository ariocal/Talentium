import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Client } from './conexiones/Client.js';
import {Util} from './Util.js'

try {
    Util.existLogin();
    
    const inputs = document.querySelectorAll('input');
    const crearPerfil = document.querySelector('#crearPerfil');

    crearPerfil.addEventListener('click',  () => {
      

        let user = 1;
        let cliente = new Client(inputs[1].value, inputs[2].value, inputs[3].value,
            inputs[4].value, user,
            inputs[5].value, inputs[6].value, inputs[7].value, inputs[8].value);
        cliente.conexionApi();

    });


} catch (error) {
    new cartelAviso('Ups!! algo salio mal', 'h2');
}
