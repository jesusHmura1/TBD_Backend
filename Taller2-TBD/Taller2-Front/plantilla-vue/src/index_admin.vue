<template>
    <div class="panel panel-primary">
        <body style="background-color:#e2dfe7;">
        <div class="panel-body">
            <header>
                <a><img src="https://image.ibb.co/cD2UOJ/logo.png" alt="logo" width="160" height="150"></a>
                <label class="center">OBSERVATORIO LIGA NACIONAL ADMIN</label>
            </header>
            <hr>
            <menu-component></menu-component>
            <hr>
            <footer></footer>
        </div>

        </body>

    </div>

</template>

<script>
import menuComponent_admin from './menuComponent_admin.vue';
export default{

    name: '',
    components: {
        'menu-component': menuComponent_admin
    },
    data(){
        return{

        }
    }
}
</script>

<style scoped>
    @import url('//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css');
    @import url('https://fonts.googleapis.com/css?family=Bowlby+One+SC');

    header {

        padding: 10px 20px;
        background-image: url("https://baexrentals.com/wp-content/uploads/2014/01/fondo-banners2.png");
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    footer {
        position: absolute;
        right: 0;
        bottom: 0;
        left: 0;
        padding: 1rem;
        background-color: #ffaa2a;
        text-align: center;
    }

    label {
        text-align: right;
        font-family: 'Bowlby One SC', cursive;
        font-weight: bold;
        font-size: 36px;
        color: #fff;
        text-shadow: 0 1px 0 #ddd, 0 2px 0 #ccc, 0 3px 0 #bbb, 0 4px 0 #aaa, 0 5px 0 #acacac, 0 6px 1px rgba(0,0,0,0.1), 0 0 5px rgba(0,0,0,0.1), 0 1px 3px rgba(0,0,0,0.3), 0 3px 5px rgba(0,0,0,0.2), 0 5px 10px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.2), 0 20px 20px rgba(0,0,0,0.15);
    }

</style>
