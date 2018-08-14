<template>
  <div class="content">
    <div class="md-layout">
        <md-card>
         <img  class="img" :src="require('@/assets/img/5b4c9b7099327.jpg')">
       </md-card>
    </div>
    <div class="md-layout">
        <md-card>
          <md-card-content>
            <h4 class="title" align="center">Influencia dentro del Gavinete</h4>
            <div align="center">
              <svg width="1000" height="600"></svg>
            </div>          
          </md-card-content>
        </md-card>
    </div>
  </div>

</template>

<script>

export default{
  data:function(){
    return{
      data: []
    }
  },
  mounted:function(){
    this.$http.get('https://api.myjson.com/bins/sd5oc')
      .then(response=>{
        this.data = response.body;
        console.log(this.data);
        this.cargarGrafo(this.data);
      }, response=>{
        console.log("error de conexion");
      });
  },
  methods:{
    cargarGrafo:function(graph){
      var svg = d3.select("svg"),
          width = +svg.attr("width"),
          height = +svg.attr("height");
      
      var color = d3.scaleOrdinal(d3.schemeCategory20c);
      var c = '#FF2900'
      
      var simulation = d3.forceSimulation()
          .force("link", d3.forceLink().id(function(d) { return d.id; }).distance(150).strength(1))
          .force("charge", d3.forceManyBody())
          .force("center", d3.forceCenter(width / 2, height / 2));

        var link = svg.append("g")
            .attr("class", "links")
          .selectAll("line")
          .data(graph.links)
          .enter().append("line")
            .attr("stroke-width", function(d) { return Math.sqrt(d.value); });

        var node = svg.append("g")
            .attr("class", "nodes")
          .selectAll("g")
          .data(graph.nodes)
          .enter().append("g")
          .on("mouseover", mouseover)
          .on("mouseout", mouseout)

        var circles = node.append("circle")
            .attr("r", function(d) { if (d.weight == 10) return 10; else return 6})
            .attr("fill", function(d) { if (d.weight == 10) return c; else return color(d.weight); })
            .call(d3.drag()
                .on("start", dragstarted)
                .on("drag", dragged)
                .on("end", dragended));

        var lables = node.append("text")
            .text(function(d) {
              return d.username;
            })
            .attr('x', 10)
            .attr('y', 3);

        node.append("title")
            .text(function(d) { if (d.weight == 10) 
                                  if(d.username == "Sebastian Piñera") 
                                    return "Presidente";
                                  else if(d.username == "Cecilia Pérez")
                                    return "Ministra Secretaria General de Gobierno";
                                  else if(d.username == "Gloria Hutt Hesse")
                                    return "Ministra de Transportes y Telecomunicaciones";                                 
                                  else if(d.username == "Alejandra Pérez Lecaros")
                                    return "Ministra de la Cultura y Las Artes";
                                  else if(d.username == "Roberto Ampuero")
                                    return "Ministro de Relaciones Exteriores";
                                  else if(d.username == "Alberto Espina")
                                    return "Ministro de Defensa";
                                  else if(d.username == "Felipe Larraín Bascuñán")
                                    return "Ministro de Hacienda";
                                  else if(d.username == "Gonzalo Blumel Mac-Iver")
                                    return "Ministro Secretario General de la Presidencia";
                                  else if(d.username == "José Ramón Valente Vías")
                                    return "Ministro de Economía, Fomento y Turismo";
                                  else if(d.username == "Alfredo Moreno Charme")
                                    return "Ministro de Desarrollo Social";
                                  else if(d.username == "Hernán Larraín Fernández")
                                    return "Ministro de Justicia y Derechos Humanos";
                                  else if(d.username == "Nicolás Monckeberg Díaz")
                                    return "Ministro del Trabajo y Previsión Social";
                                  else if(d.username == "Juan Andrés Fontaine Talavera")
                                    return "Ministro de Obras Públicas";
                                  else if(d.username == "Emilio Santelices Cuevas")
                                    return "Ministro de Salud";
                                  else if(d.username == "Cristian Monckeberg Bruner")
                                    return "Ministro de Vivienda y Urbanismo";
                                  else if(d.username == "Antonio Walker Prieto")
                                    return "Ministro de Agricultura";
                                  else if(d.username == "Baldo Prokurica Prokurica")
                                    return "Ministro de Minería";
                                  else if(d.username == "Felipe Ward Edwards")
                                    return "Ministro de Bienes Nacionales";
                                  else if(d.username == "Susana Jiménez Schuster")
                                    return "Ministra de Energía";
                                  else if(d.username == "Marcela Cubillos Sigall")
                                    return "Ministra de Medio Ambiente";
                                  else if(d.username == "Pauline Kantor Pupkin")
                                    return "Ministra de Medio Ambiente";
                                  else if(d.username == "Isabel Plá Jarufe")
                                    return "Ministra de la Mujer y la Equidad de Género";
                                  else return "Cuenta de Twitter";
                                else return "Cuenta de Twitter";});

        simulation
            .nodes(graph.nodes)
            .on("tick", ticked);

        simulation.force("link")
            .links(graph.links);

        function ticked(){
          link
              .attr("x1", function(d) { return d.source.x; })
              .attr("y1", function(d) { return d.source.y; })
              .attr("x2", function(d) { return d.target.x; })
              .attr("y2", function(d) { return d.target.y; });
      
          node
              .attr("transform", function(d) {
                return "translate(" + d.x + "," + d.y + ")";
              })
          };
        function dragstarted(d) {
          if (!d3.event.active) simulation.alphaTarget(0.3).restart();
          d.fx = d.x;
          d.fy = d.y;
        };
        function dragged(d) {
          d.fx = d3.event.x;
          d.fy = d3.event.y;
        };
        function dragended(d) {
          if (!d3.event.active) simulation.alphaTarget(0);
          d.fx = null;
          d.fy = null;
        };
        function mouseover() {
          d3.select(this).select("circle").transition()
              .duration(750)
              .attr("r", 16);
        };

        function mouseout() {
          d3.select(this).select("circle").transition()
              .duration(750)
              .attr("r", 8);
        };
    }
  }  
}

</script>

<style>
.links line {
  stroke: #999;
  stroke-opacity: 0.6;
}

.nodes circle {
  stroke: #000;
  stroke-width: px;
  stroke-opacity: 1;
}

text {
  font-family: sans-serif;
  font-size: 13px;
}
</style>
