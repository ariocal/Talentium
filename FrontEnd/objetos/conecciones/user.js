import { Util } from '../Util.js';

export class User {
    constructor(emai, passwor, avata) {
        this.email = emai ?? NaN;
        this.password = passwor ?? NaN;
        this.avatar = avata ?? '';

    }
    async conexionApi(urlvs = 'http://localhost:8080/usuarios') {


        const url = urlvs;
        const userData = {
            email: this.email,
            password: this.password,
            avatar: this.avatar
        };

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
      
    }

    async login(){
        this.conexionApi("http://localhost:8080/usuarios/login");
    }
}

