<template>
    <div>
        <br>

        <label>Gráfico de trofeos por cada equipo</label>
        <br><br>
        <hr>
        <br><br>
        <div style="width:100%; float: left;"  >

            <vue-chart  v-if="this.chartData !== null" type="horizontalBar" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>

        </div>

        <div style="width:50%; float:right;">
            <vue-chart v-if="this.chartData !== null" type="doughnut" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class="lds-css  ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>
        </div>


        <div>
            <h1>Descripción</h1>

            <p> "En estos gráficos se puede observar la cantidad de trofeos que han ganado cada equipo de la primera
                división de la liga chilena."
            </p>
        </div>

    </div>
</template>


<script>
    import VueChart from "vue-chart-js";
    export default {
        name: "trofeos",
        props:['datos'],
        components: {
            VueChart
        },

        data: () => ({
            chartData: null,
            barra:true,
            torta:false,
            colores: []
        }),

        created() {
            this.crearGrafico();
            console.log("grafico creado", this.chartData);
        },

        methods: {
            mostrarBarra(){
                this.barra=true;
                this.torta=false;

            },
            mostrarTorta(){
                this.barra=false;
                this.torta=true;
            },

            /*dejar esta funcion en el for de crear grafico*/
            colorRandom(){
                var color = "#"+((1<<24)*Math.random()|0).toString(16)
                this.colores.push(color)
                console.log("color", color, "agregado")
                console.log("el arreglo de colores es:", this.colores)

            },

            crearGrafico() {
                this.chartData = {
                    labels: [],
                    datasets: [
                        {
                            backgroundColor: this.colores,
                            data: [],
                            label: ["Cantidad de trofeos"]
                        },
                    ]
                };

                for (let i = 0; i <= this._props.datos.length-1; i++) {
                    this.colorRandom();
                    this.chartData.labels = this.chartData.labels.concat([
                        this._props.datos[i].name
                    ]);
                    this.chartData.datasets[0].data.push( this._props.datos[i].trophies)
                }
            }
        }
    };
</script>
