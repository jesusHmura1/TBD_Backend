<template>
<div v-if="this.clubs !== null">
    <div class="container">
        <ul class="nav nav-pills">

            <li role="presentation" class="dropdown"  v-on:click="setSelectedItemHome">
                <a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a>
            </li>

            <li role="presentation" class="dropdown"  v-on:click="setSelectedItemLiga">
                <a href="#"><span></span>Estadísticas liga chilena</a>
            </li>

            <li role="presentation" class="dropdown" v-on:click="setSelectedItemUserL">
                <a href="#"><span></span>Influencia de usuarios en la liga chilena</a>
            </li>

            <li role="presentation" class="dropdown" v-on:click="setSelectedItemUserE">
                <a href="#"><span></span>Influencia de usuarios en los equipos</a>
            </li>

            <li role="presentation" class="dropdown" v-on:click="setSelectedItemG">
                <router-link to="">Graficos por club</router-link>
            </li>

            <li role="presentation" class="dropdown" v-on:click="setSelectedItemE">
                <router-link to="">Análisis por club</router-link>
            </li>

            <li role="presentation" class="dropdown" v-on:click="setSelectedItemTrofeos">
                <a href="#">Trofeos</a>
            </li>

        </ul>
    </div>
    <div>
        <div v-if="this.eleccion == 1">
            <home-component></home-component>
        </div>

        <div v-if="this.eleccion == 2">
            <Liga-component v-bind:datos="clubs"></Liga-component>
        </div>

        <div v-if="this.eleccion == 3">
            <Userl-component v-bind:datos="users"></Userl-component>
        </div>

        <div v-if="this.eleccion == 4">
            <Usere-component v-bind:datos="users"></Usere-component>
        </div>

        <div v-if="this.eleccion == 5">
            <EquipoDatacomponente v-bind:datos="clubs"></EquipoDatacomponente>
        </div>

        <div v-if="this.eleccion == 6">
            <Equipoestadisticascomponente v-bind:datos="clubs"></Equipoestadisticascomponente>
        </div>

        <div v-if="this.eleccion == 7">
            <Trofeos-component v-bind:datos="clubs"></Trofeos-component>
        </div>

        <div v-if="this.eleccion == 8">
            <grafo-component></grafo-component>
        </div>
    </div>
</div>
<div v-else style="margin: 0 auto;">
    <div style="margin: 0 auto;">
        <div class="lds-css ng-scope" style="margin: 0 auto;">
            <div style="width:100%;height:100%;margin:0 auto;" class="lds-facebook">
                <div></div><div></div><div></div></div></div>
            </div>
    </div>
</template>

<script>
    import home from './home.vue';
    import dataEquipo from './dataEquipos.vue';
    import estadisticasEquipo from './EstadisticasEquipos.vue';
    import ligachilena from './ligaChilena.vue';
    import trofeos from './trofeos.vue';
    import grafo from './grafos.vue';
    import user_liga from './seguidores_liga.vue';
    import user_equipo from './seguidores_equipo.vue';

    export default {
        components: {
            'home-component': home,
            'Liga-component': ligachilena,
            'Trofeos-component': trofeos,
            'EquipoDatacomponente': dataEquipo,
            'Equipoestadisticascomponente': estadisticasEquipo,
            'grafo-component': grafo,
            'Userl-component': user_liga,
            'Usere-component': user_equipo
        },
        name: "menuComponent.vue",
        data(){
            return{
                eleccion: 0,
                clubs: null,
                users:null,
                seguidores: null
            }
        },

    created() {
        this.$http.get("http://localhost:8081/club").then(response => {
            console.log(response.body)
            this.clubs = response.body;
            console.log("club:" + this.clubs);
            console.log("data de clubes obtenida con exito!", this.clubs);
            this.eleccion = 1;
        });

        this.$http.get("http://localhost:8080/user/topUser?cantidad=15").then(response => {
            console.log(response)
            this.users = response.body;
        console.log("users:" + this.users);
        console.log("data de users obtenida con exito!", this.users);
        });
    },
    
    methods: {
            setSelectedItemHome(){
                this.eleccion=1;
            },
            setSelectedItemLiga(){
                this.eleccion=2;
            },
            setSelectedItemUserL(){
                this.eleccion=3;
            },
            setSelectedItemUserE(){
                this.eleccion=4;
            },
            setSelectedItemG(){
               this.eleccion=5;
            },
            setSelectedItemE(){
               this.eleccion=6;
            },
            setSelectedItemTrofeos(){
               this.eleccion=7;
            },
            setSelectedGrafo(){
                this.eleccion=8;
            }

        },
        ready: function() {
                $('.dropdown-submenu a.test').on("click", function(e){
                $(this).next('ul').toggle();
                e.stopPropagation();
                e.preventDefault();
            });    
        }
    }
</script>

<style scoped>

</style>