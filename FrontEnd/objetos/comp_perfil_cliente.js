import { User } from './conecciones/user.js';
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Util } from './Util.js';
import { Client } from './conecciones/Client.js';

try {
    const inputs = document.querySelectorAll('input');
    const crearPerfil = document.querySelector('#crearPerfil');

    crearPerfil.addEventListener('click', async (event) => {
        event.preventDefault();
    
      let  user = 1;
        let cliente =  new Client(inputs[1].value, inputs[2].value, inputs[3].value,user,
             inputs[5].value, inputs[4].value, inputs[6].value, inputs[7].value);
             cliente = await cliente.conexionApi() ?? NaN;
             new cartelAviso('cliente: '+ cliente, 'h2');
             if(cliente != NaN) Util.guardarCliente(cliente, 'h2');
    });

    
} catch (error) {
    new cartelAviso('Ups!! algo salio mal', 'h2');
}
