<template>
    <div class="cuerpo">
    <h1>Usuarios mas influyentes en la liga de futbol chileno</h1>
    <h1>Descripción:</h1>
     <p> "La siguiente tabla nos indicara quienes son los usuarios mas influyentes dentro de la liga chilena"
     </p>
     <br><br>
        <br><br>
         <table class="table">
           <thead class="thead-light">
           <tr>
               <th>Nombre</th>
               <th>Seguidores</th>
               <th>Clubs mencionados</th>
               <th>influencia normalizada </th>
           </tr>
           </thead>
           <tbody>
           <tr v-if="i!==15" :key="i" :value="i" v-for="(usuario,i) in this.eleccion">
               <td> {{ usuario.user }} </td>
               <td> {{ usuario.followers }} </td>
               <td> {{ usuario.clubMentions }} </td>
               <td> {{ (usuario.followers)/1000000 }} </td>
           </tr>
           <!--    <td><span class="label label-success">Active</span> -->
           </tbody>
        </table>

</div>
</template>

<script>
  import VueChart from "vue-chart-js";
     export default {
         name: "Usuarios",
         value:{},

         props:['datos'],
         components: {
             VueChart
         },

         data: () => ({
             showd:true,
             eleccion: "",
             influencia: null,
             chart:null,
             esPositiva: true

         }),

         mounted() {
             this.showd=true;
             this.value=0;
             console.log(this.datos);
             var influencia_normalizada = [];
             influencia_normalizada.push(0.4);
             influencia_normalizada.push(0.7);
             influencia_normalizada.push(1);
             influencia_normalizada.push(0.8);
             influencia_normalizada.push(0.4);
             influencia_normalizada.push(0.7);
             influencia_normalizada.push(0.6);
             influencia_normalizada.push(0.6);
             influencia_normalizada.push(0.4);
             influencia_normalizada.push(0);
             influencia_normalizada.push(0.7);
             influencia_normalizada.push(0.3);
             influencia_normalizada.push(0.3);
             influencia_normalizada.push(0.6);
             influencia_normalizada.push(0);
             this.influencia = influencia_normalizada;
             this.eleccion = this.datos;
         },
         create(){
             var data;
             data = calcularIndice();
             console.log("llega la data:"+ data);
         },
         methods: {
             modGrafico() {
                console.log("los datos son :" + this.datos)
                this.eleccion = this.datos;
             },

             calcularIndice(){
                 var influencia_normalizada = [];
                 this.eleccion.forEach(element => {
                     console.log("el elemento es:"+element)
                     if(element.clubMentions.lenght() == 0){
                         influencia_normalizada.concat("1");
                     }
                     if(element.clubMentions.lenght() > 2 && element.clubMentions.lenght() < 6){
                         influencia_normalizada.concat("3");
                     }
                     else{
                         influencia_normalizada.concat("5");
                     }
                 });
                 return influencia_normalizada;
             }
         }
     }
</script>

 