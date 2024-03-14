import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';


class guardarSesionStorage {
    constructor(nombre, dato, padre) {
        try {
            sessionStorage.setItem(nombre, dato);
        } catch (error) {
            new cartelAviso('error al guardar el usuario', padre);

        }
    }
}

class recuperarSesionStorage {
    constructor(nombre, padre) {
        try {
            return sessionStorage.getItem(nombre);
        } catch (error) {
            new cartelAviso('error al recuperar el usuario', padre);

        }
    }
}
// const jwtToken = response.headers.get('Authorization');

export class guardarUsuario {
    constructor(dato, padre) {
        new guardarSesionStorage('usuario', dato, padre);
    }
}

export class reuperarUsuario {
    constructor(padre) {
        new recuperarSesionStorage('usuario', padre);
    }
}