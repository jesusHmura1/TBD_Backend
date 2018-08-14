import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import data from './dataEquipos.vue'
import estadisticas from './EstadisticasEquipos.vue'
import trofeos from './trofeos.vue'
import ligaChilena from './ligaChilena.vue'
import Index from './Index.vue';
import Index_admin from './index_admin.vue';
import grafo from './grafos.vue';
import VueResource from 'vue-resource';
 

// Vue.component(ChoroplethMap);

require("./style.scss");

Vue.use(VueRouter);
Vue.use(VueResource);

const routes = [
    { path: '/index', alias: '/', component: Index},
    { path: '/dataEquipo/', component: data },
    { path: '/estadisticas', component: estadisticas},
    { path: '/trofeos', component: trofeos},
    { path: '/ligaChilena', component: ligaChilena},
    { path: '/grafo', component: grafo}
];

// Create the router instance and pass the `routes` option
const router = new VueRouter({
    routes
});

new Vue({
    el: '#app',
    router,
    render: h => h(App)
});


