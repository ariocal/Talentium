import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';

export class Util{

    static esPromesa(variable){
        return Promise.resolve(variable) instanceof Promise;
      }

      static guardarSesionStorage(nombre, dato, padre) {
        try {
            const datoParce =JSON.stringify(dato);
           // console.log('datoParce: '+ datoParce);
            sessionStorage.setItem(nombre,datoParce );
        } catch (error) {
            new cartelAviso('error al guardar el usuario', padre);

        }
    }


    static recuperarSesionStorage(nombre, padre) {
        try {
            const recuperarUsuario = sessionStorage.getItem(nombre);
            //console.log('recuperarUsuario: '+ recuperarUsuario);
            return recuperarUsuario;
        } catch (error) {
            new cartelAviso('error al recuperar el usuario', padre);

        }
    }

    static  guardarUsuario(dato, padre) {
         Util.guardarSesionStorage('usuario', dato, padre);
    }


    static reuperarUsuario (padre) {
        return   Util.recuperarSesionStorage('usuario', padre);
       }


}

  









