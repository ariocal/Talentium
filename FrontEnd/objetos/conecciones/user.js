export class User {
    constructor(emai, passwor, avata) {
        this.email = emai ?? NaN;
        this.password = passwor ?? NaN;
        this.avatar = avata ?? '';

    }
    async conexionApi() {


        const url = 'http://localhost:8080/usuarios';
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
        });

        if (response.ok) {
            return await response.json();
            //console.log(user);
           // return user;
        } else {
            return await null
        }
    }

}

