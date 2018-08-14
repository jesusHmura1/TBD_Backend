<template>
<div id="login" class = "panel panel-primary">

    <label>Inicio sesión administrador</label>
    
    <br><hr>
    <div class="panel-body">
        <div>
            <input type="text" name="username" v-model="input.email" placeholder="Correo electrónico" />
        </div>
        <br>
        <div>
        <input type="password" name="password" v-model="input.password" placeholder="Contraseña" />
        </div>
        <br>
        <div>
            <button type="button" class="btn btn-secondary" style="border:1px solid #ababae" v-on:click="login()">
                Ingresar
            </button>
        </div>
    </div>
</div>
</template>

<script>
    export default {
        name: 'Login',
        data() {
            return {
                respuesta: {},
                admin: 'admin',
                pass: 123,
                input: {
                    email: "",
                    password: ""
                }
            }
        },
        methods: {
            login() {
                if(this.input.email != "" && this.input.password != "") {
                    let json = {
                        email: this.input.email,
                        password: this.input.password
                    } 
                    this.$http.post("http://159.65.128.52:8080/TBD-G7/user/auth",json).then(response => {
                        this.respuesta = response.data;
                        console.log(this.respuesta)
                        if(this.respuesta.id != null){
                             this.$router.replace({ path: "/index_admin" });
                        }
                        else{
                            console.log("El user o la clave se encuentran mal ingresadas");
                        }
                    });
                } else {
                    console.log("No se ingreso user o no se ingreso clave");
                }
            }
        }
    }
</script>

<style scoped>
    #login {
        width: 500px;
        border: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        margin: auto;
        margin-top: 70px;
        padding: 20px;
    }
</style>