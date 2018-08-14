<template>
    <div>
        <br>

        <label>Tabla de Influencia de Usuarios referenciando a un equipo </label>
         <b-container class="bootstrapVue-example-row" style="margin-left: 3em;">
    <b-row>

        <b-col>
          <label for="equipos" class="form-control-label"></label>
          <select v-model="value" @change="modificarGrafico" id="Top" class="form-control" required>
          <option disabled :value="null">Seleccione un equipo</option>
                <option  value="Universidad Católica">Universidad Católica</option>
                 <option  value="Colo Colo">Colo Colo</option>
                 <option  value="Universidad de Chile">Universidad de Chile</option>
                 <option  value="Antofagasta">Antofagasta</option>
                 <option  value="Audax Italiano">Audax Italiano</option>
                 <option  value="Curico Unido">Curico Unido</option>
                 <option  value="Deportes Iquique">Deportes Iquique</option>
                 <option  value="Deportes Temuco">Deportes Temuco</option>
                 <option  value="Everton CD">Everton CD</option>
                 <option  value="Huachipato">Huachipato</option>
                 <option  value="O'Higgins">O'Higgins</option>
                 <option  value="Palestino">Palestino</option>
                 <option  value="San Luis">San Luis</option>
                 <option  value="Universidad de Concepción">Universidad de Concepción</option>
                 <option  value="Unión Española">Unión Española</option>
                 <option  value="Unión la Calera">Unión la Calera</option>
          </select>
        </b-col>
        
    </b-row>
    </b-container>
    <br><br>
    <hr>
    <div style="width:100%; float: left;"  >
        <h1>Descripción:</h1>
        <p>"Este gráfico nos indica los 3 usuarios mas relevantes para cada uno de los equipos de la liga
            Chilena de fitbol"
        </p>
    </div>
    <br><br>
    <br><br>
    <hr>
    <br><br>
    <div v-if="this.showd">
        <div style="width:50%; float: left;">
        <vue-chart v-if="this.chartData !== null " type="horizontalBar" v-bind:data="this.chartData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div><div></div>
                    </div>
                </div>
            </div>
        </div>

        <div style="width:50%; float: right;"  >
            <vue-chart v-if="this.chartData !== null" type="line" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        
    </div>
</template>


<script>
    import VueChart from "vue-chart-js";
    export default {
        name: "App",
        value:{},
       
        props:['datos'],
        components: {
            VueChart
        },

        data: () => ({
        chartData: null,
        showd:null,
        barra: true,
        torta: false,
    }),

    created() {
        this.showd=true;
        console.log("estoy creando");
        this.value=null;
        this.chartData=this.crearGrafico();
        console.log("grafico creado", this.chartData);
    },


    computed:{
        
    },
    methods: {
        modificarGrafico(){
            console.log("*********"+this.datos);
            this.showd=false;
            this.chartData= this.crearGrafico();
            this.showd=false;
            this.$nextTick(() => {
                    this.showd = true
                    console.log('re-render start')
                    this.$nextTick(() => {
                    console.log('re-render end')
                })
            })
        },

        mostrarBarra()
        {
            this.barra = true;
            this.torta = false;

        },

        mostrarTorta()
        {
            this.barra = false;
            this.torta = true;

        },

        crearGrafico()
        {
            let chartData = {
                labels: [],
                datasets: [
                    {
                        label: "Influencia del usuario en un equipo",
                        borderColor: "#00FF00",
                        backgroundColor: "#00FF00",
                        data: [],
                        fill: false
                    }
                ]
            };
            /* Largo  */
            console.log(this.datos)
            console.log(this.value)
            let grupo = [];
            if(this.value != null){
                for (let index = 0; index < this.datos.length; index++) {
                    const element = this.datos[index].clubMentions;
                    for (let contador = 0; contador < element.length; contador++) {
                        const data = element[contador];
                        if(this.value === data){
                            grupo.push(this.datos[index])
                        } 
                    }
                }
            }
            console.log(grupo)
            if(grupo.length != 0){
                for (let contador = 0; contador < grupo.length; contador++) {
                    if(contador < 3){
                        const element = grupo[contador];
                        console.log("aqui esta la data del grupo");
                        console.log(element);
                        chartData.labels.push(element.user);
                        chartData.datasets[0].data.push(element.followers.toString());
                    }
                }
            }
            else{
                chartData.labels.push("juaquin46");
                chartData.datasets[0].data.push("34567");

                chartData.labels.push("RodrigoHFanatico");
                chartData.datasets[0].data.push("324567");

                chartData.labels.push("CDF");
                chartData.datasets[0].data.push("4434567");
            }
            return chartData;
        } 
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