<template>
        
<div id="register" class = "panel panel-primary">

    <label>Registrar nuevo administrador</label>
    
    <br><hr>
    <div class="panel-body">
        <div>
            <input type="text" name="username" v-model="input.name" placeholder="Nombre de usuario" />
        </div>
        <br>
        <div>
            <input type="password" name="password" v-model="input.password" placeholder="Contraseña" />
        </div>
        <br>
        <div>
            <input type="text" name="email" v-model="input.email" placeholder="Correo electrónico" />
        </div>
        <br>
        <div>
            <button type="button" class="btn btn-secondary" style="border:1px solid #ababae" v-on:click="CrearUser()">
                Registrar
            </button>
        </div>
    </div>
</div>
</template>

<script>
    export default {
        name: "register",
        data(){
            return{
                respuesta: {},
                input: {
                    name: "",
                    password: "",
                    email: ""
                }
            }
        },

        methods: {
            CrearUser(){
                if(this.input.name != "" && this.input.password != "" && this.input.email != "") {
                    //realizar el post a la base de datos para guardar a el usuario
                    let json = {
                        name: this.input.name,
                        password: this.input.password,
                        email: this.input.email
                    }
                    console.log(json)
                    this.$http.post("http://159.65.128.52:8080/TBD-G7/user",json).then(response => {
                        this.respuesta = response.data;
                        this.input.name = ""
                        this.input.password = ""
                        this.input.email = ""
                        console.log("Administrador ingresado con exito!")
                        alert("Persona registrada con exito!")
                    });
                } else {
                    console.log("No se ingreso user/clave o email");
                }
                this.eleccion=1;
            },
            setSelectedItemLiga(){
                this.eleccion=2;
            }

        }
    }
</script>

<style scoped>
    #register {
        width: 500px;
        border: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        margin: auto;
        margin-top: 70px;
        padding: 20px;
    }
</style>