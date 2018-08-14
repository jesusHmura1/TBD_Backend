<template>
<div class="row">
  <br>

  <div class="">
    <label class="">Herramientas para Actualizacion de equipos</label>
      <div style="float:right;">
        <label  for="select" >Seleccione un equipo:</label>
        <div class="styled-select slate">
            <select  v-model="value"  name="" id="select">
                <option v-if="i!==16" :key="i" :value="i" v-for="(equipo,i) in datos">{{equipo.name}}</option>
            </select>
        </div>
      </div>
  </div>
  <br><br>
  <hr>
  <br><br>
<div>
    <label class="">Agregar Equipo</label>
    <input type="text" name="name" v-model="equipoAgregar" placeholder="Nombre Equipo" />
    <button type="button" v-on:click="AgregarEquipo()">Login</button>
</div>
<div>
    <label class="">Eliminar Equipo</label>
    <button type="button" v-on:click="EliminarEquipo()">Login</button>
</div>
<div>
    <label class="">Agregar Keyworks</label>
    <input type="text" name="name" v-model="keyworks" placeholder="Nombre Equipo" />
    <button type="button" v-on:click="AgregarKeywork()">Login</button>
</div>
<div>
    <label class="">Eliminar Keyworks</label>
    <input type="text" name="name" v-model="keyworks" placeholder="Nombre Equipo" />
    <button type="button" v-on:click="EliminarKeywork()">Login</button>
</div>
</div>
</template>


<script>
    export default {
        name: "tools",
        props:['datos'],
        data(){
            return{
                equipoAgregar: "",
                keyworksA: "",
                keyworksE: "",
                clubs:null,
                mapas: null,
                value: {}
            }
        },

    methods: {

        EliminarEquipo(){
            if(this.value == {}){
                alert('No a seleccionado ningun equipo ')
            }
            else{
                this.$http.delete("http://159.65.128.52:8080/TBD-G7/club/" + this.value).then(response => {
                this.clubs = response.data;
                /* console.log("club:" + this.clubs);
                    console.log("data de clubes obtenida con exito!", this.clubs);*/
                    this.eleccion = 1;
                });
                this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
                this.clubs = response.data;
                });
                this.value = 0;
            }
        },

        AgregarEquipo(){
            if(this.value == {}){
                alert('No a seleccionado ningun equipo ')
            }
            else{
                this.$http.delete("http://159.65.128.52:8080/TBD-G7/club/" + this.value).then(response => {
                this.clubs = response.data;
                /* console.log("club:" + this.clubs);
                    console.log("data de clubes obtenida con exito!", this.clubs);*/
                    this.eleccion = 1;
                });
                this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
                this.clubs = response.data;
                });
                this.value = 0;
            }
        },

        EliminarKeywork(){
            if(this.value == {}){
                alert('No a seleccionado ningun equipo ')
            }
            else{
                this.$http.delete("http://159.65.128.52:8080/TBD-G7/club/" + this.value).then(response => {
                this.clubs = response.data;
                /* console.log("club:" + this.clubs);
                    console.log("data de clubes obtenida con exito!", this.clubs);*/
                    this.eleccion = 1;
                });
                this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
                this.clubs = response.data;
                });
                this.value = 0;
            }
        },

        AgregarKeywork(){
            if(this.value == {}){
                alert('No a seleccionado ningun equipo ')
            }
            else{
                this.$http.delete("http://159.65.128.52:8080/TBD-G7/club/" + this.value).then(response => {
                this.clubs = response.data;
                /* console.log("club:" + this.clubs);
                    console.log("data de clubes obtenida con exito!", this.clubs);*/
                    this.eleccion = 1;
                });
                this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
                this.clubs = response.data;
                });
                this.value = 0;
            }
        },

    }
    };
</script>

<style scoped>
  iv { margin: 20px; }

  /* -------------------- Select Box Styles: bavotasan.com Method (with special adaptations by ericrasch.com) */
  /* -------------------- Source: http://bavotasan.com/2011/style-select-box-using-only-css/ */
  .styled-select {
    background: url(http://i62.tinypic.com/15xvbd5.png) no-repeat 96% 0;
    height: 29px;
    overflow: hidden;
    width: 240px;
  }

  .styled-select select {
    background: transparent;
    border: none;
    font-size: 14px;
    height: 29px;
    padding: 5px; /* If you add too much padding here, the options won't show in IE */
    width: 268px;
  }

  .styled-select.slate {
    background: url(http://i62.tinypic.com/2e3ybe1.jpg) no-repeat right center;
    height: 34px;
    width: 240px;
  }

  .styled-select.slate select {
    border: 1px solid #ccc;
    font-size: 16px;
    height: 34px;
    width: 268px;
  }

  /* -------------------- Rounded Corners */


  /* -------------------- Colors: Background */
  .slate   { background-color: #ddd; }

  /* -------------------- Colors: Text */
  .slate select   { color: #000; }

</style>