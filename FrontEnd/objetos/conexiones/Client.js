import { Util } from '../Util.js';
import { cartelAviso } from '../cartel_aceptar_cancelar/cartelAviso.js';

export class Client {
    constructor(nameDto, lastnameDto, dniDto, phoneDto, userId, street, number, province, location) {
        this.name = nameDto;
        this.lastname = lastnameDto;
        this.dni = dniDto;
        this.phone = phoneDto;
        this.user = {
            "id": userId
        }
        this.direction = {
            "street": street,
            "number": number,
            "province": province,
            "location": location
        }
    }


    async conexionApi() {

        

        const url = 'http://localhost:8080/api/client';
        const userData = {
            name: this.name,
            lastname: this.lastname,
            dni: this.dni,
            phone: this.phone,
            user: this.user,
            direction: this.direction
        };

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                //'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(userData)
        }).then(response => response.json())
            .then(data => {
                Util.guardarCliente(data);
                Util.cambiarDePagina('sitio_del_cliente.html'); 
            }
            ).catch(err => {
                new cartelAviso('Ups!! algo salio mal, intenta más tarde', 'h2');
            });





        /*.then(response => {
            if (response.ok) return response.json();
            new cartelAviso('Ups!! error de datos', 'h2');
        }).catch(err => {
            //console.error("ERROR: ", err.message);
            new cartelAviso('Ups!! error de conexion', 'h2');
        });*/

    }
}

