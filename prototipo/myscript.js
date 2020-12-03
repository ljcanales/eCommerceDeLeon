$(document).ready(function(){
    $("#estilo1").click(function(){
        //hacer  consulta Ajax
        var consulta = "http://localhost:4567/getNoticias1";
        $("#consulta").html("la consulta es:  "+consulta);
        /*
        $.get(consulta, function(answer){
            //insertar codigo obtenido
            console.log("consulta recibida");
            $("#contenedorimagen").html(answer);
        });
        */
        
        $.ajax({
            url: consulta,
            type: 'GET',
            success: function(ans) {
                $("#contenedor").html(ans);
                console.log("Succes");
            },
            error: function(){
                $("#contenedor").html("<p> NO ANDUVO WACHO </p>");
                console.log("Fail");
            }
        });
    });
});

$(document).ready(function(){
    $("#estilo2").click(function(){
        //hacer  consulta Ajax
        var consulta = "http://localhost:4567/getNoticias2";
        $("#consulta").html("la consulta es:  "+consulta);
        /*
        $.get(consulta, function(answer){
            //insertar codigo obtenido
            console.log("consulta recibida");
            $("#contenedorimagen").html(answer);
        });
        */
        
        $.ajax({
            url: consulta,
            type: 'GET',
            success: function(ans) {
                $("#contenedor").html(ans);
                console.log("Succes");
            },
            error: function(){
                $("#contenedor").html("<p> NO ANDUVO WACHO </p>");
                console.log("Fail");
            }
        });
    });
});
/* $(document).ready(function(){
    $('.sidenav').sidenav();
  }); */
  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, options);
  });
/* $(document).ready(function () {

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    }); */

});