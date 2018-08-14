<template>
  <div id="app">
      <!-- Color de fondo general   -->
      <body background="http://www.hdfondos.eu/preview/get_photo/59048/1920/1200">
      <div class="container">
          <router-view> </router-view>
      </div>

      </body>
  </div>
</template>

<script>
    /*               npm install
                     npm i bootstrap-vue
                     npm i vue-chartjs
                     npm install d3
                     npm install d3plus@next          */


    export default {
        name: "app",
        data(){
            return{

            }
        }
    }
</script>

<style>
#app {

}
</style>
