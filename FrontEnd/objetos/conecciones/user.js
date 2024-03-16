import { cartelAviso } from '../cartel_aceptar_cancelar/cartelAviso.js';


export class User {
    constructor(emai, passwor, avata) {
        this.email = emai ?? NaN;
        this.password = passwor ?? NaN;
        this.avatar = avata ?? '';

    }
    async conexionApi() {
       

        const url =  'http://localhost:8080/usuarios';
        const userData = {
            email: this.email,
            password: this.password,
            avatar: this.avatar
        };
try {
    

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
        
        
        
        

        if (response.ok) {

            const user = await response.json();
     
       // console.log('Authorization: '+ response.headers.get('Authorization'));
            return user;
        } else {
            return  null;
        }

    } catch (error) {
    
    }
      
    }

    async login(){
       
        const url =  'http://localhost:8080/usuarios/login';
        const userData = {
            email: this.email,
            password: this.password
        };

        try {
            
    
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })

        if (response.ok) {

            const user = await response.json();
     /* buscando el jwt en el header
            for ([key, value] of response.headers.entries()) {
                console.log("Clave: ", key, "valor: ", value);
              }*/
           
       // console.log('Authorization: '+ response.headers.get('Authorization'));
            return user;
        } else {
            return  null;
        }

    } catch (error) {
      //  new cartelAviso('Lo siento, algo salio mal. vuelve mas tarde a intentarlo');

    }
}
}
