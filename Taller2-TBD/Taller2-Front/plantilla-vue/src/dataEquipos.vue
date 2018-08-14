<template>
<div class="row">
    
    <br>
    <div class="">
    <label class="">Gráfico del club {{datos[this.value].name}}</label>
        <div style="float:right;">
        <label for="select" >Seleccione un equipo:</label>
        <div class="styled-select slate">
        <select @change="modificarGrafico" v-model="value"  name="" id="select">
            <option v-if="i!==16" :key="i" :value="i" v-for="(equipo,i) in datos">{{equipo.name}}</option>
        </select>
        </div>
        </div>
    </div>

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
  
    <div style="width:100%; float: left;"  >
        <h1>Descripción:</h1>
        <p>"Este gráfico muestra la cantidad de comentarios positivos como negativos de un equipo en especifico en un determinado
        tiempo.
        Dichos comentarios están epresentados en un gráfico de barras y un gráfico de torta. Los colores que toman cada tipo
        de comentario son verde y rojo respectivamente. "
        </p>
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
        this.value=0;
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

        timeConverter(timestamp){
            var ent = parseInt(timestamp);
            var a = new Date(ent);
            var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
            var year = a.getFullYear();
            var month = months[a.getMonth()];
            var date = a.getDate();
            var hour = a.getHours();
            var min = a.getMinutes();
            var sec = a.getSeconds();
            var time = date + ' ' + month + ' ' + year  ;
            return (time);
        },

        crearGrafico()
        {
            let chartData = {
                labels: [],
                datasets: [
                    {
                        label: "Comentarios positivos",
                        borderColor: "#56FB4C",
                        backgroundColor: "#56FB4C",
                        data: [],
                        fill: false
                    },

                    {
                        label: "Comentarios negativos",
                        borderColor: "#FB5C57",
                        backgroundColor: "#FB5C57",
                        data: [],
                        fill: false
                    },
                ]
            };
            /* Largo  */
            console.log("aaaa"+this.datos[this.value])
            var tam = this.datos[this.value].statistics.length
            console.log("el timestamp que quiero cambiar es: ", this.datos[this.value].statistics[tam - 1].lastUpdate)

            console.log("el timestamp cambiado es: ", this.timeConverter(this.datos[this.value].statistics[tam - 1].lastUpdate))
            /*Primer elemento de fecha*/
            chartData.labels.push(this.timeConverter(this.datos[this.value].statistics[0].lastUpdate))
            /*Segundo elemento intermedio de fecha*/
            chartData.labels.push(this.timeConverter(this.datos[this.value].statistics[Math.trunc(tam / 2)].lastUpdate))
            /*Ultima fecha*/
            chartData.labels.push(this.timeConverter(this.datos[this.value].statistics[tam - 1].lastUpdate))

            /**Comentarios positivos/
             /*Primer elemento de fecha*/
            chartData.datasets[0].data.push(this.datos[this.value].statistics[0].positive_value)
            /*Segundo elemento intermedio de fecha*/
            chartData.datasets[0].data.push(this.datos[this.value].statistics[Math.trunc(tam / 2)].positive_value)
            /*Ultima fecha*/
            chartData.datasets[0].data.push(this.datos[this.value].statistics[tam - 1].positive_value)

            /**Comentarios negativos/
             /*Primer elemento de fecha*/
            chartData.datasets[1].data.push(this.datos[this.value].statistics[0].negative_value)
            /*Segundo elemento intermedio de fecha*/
            chartData.datasets[1].data.push(this.datos[this.value].statistics[Math.trunc(tam / 2)].negative_value)
            /*Ultima fecha*/
            chartData.datasets[1].data.push(this.datos[this.value].statistics[tam - 1].negative_value)
            console.log("modificado"+chartData)
            return chartData;
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