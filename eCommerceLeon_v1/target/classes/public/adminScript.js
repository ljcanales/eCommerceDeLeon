$(document).ready(function(){

    function select(getMethodName) {
        var url = "http://localhost:4567/adminMethod?id=" + getMethodName.toString();

        $.ajax({
            url: url,
            type: 'GET',
            success: function(ans) {
                $("#contenedor").html(ans); //verificar id del contenedor
                
                console.log("Succes");
            },
            error: function() {
                $("#contenedor").html("<p>--- Error Ajax select!! ---</p>");
                console.log("Fail");
            }
        });
    }


    //   CHECK ID PRODUCTO
    $("#IdProd").change(function() { //verificar id del input
        var url = "http://localhost:4567/checkId?id=" + $(this).val().toString();

        $.ajax({
            url: url,
            type: 'GET',
            success: function(ans) {
                $("#nombreProd").text(ans); //verificar id del input
                if(ans != "") {
                    $("#btnAgregar").prop('disabled', false);
                } else {
                    $("#btnAgregar").prop('disabled', true);
                }
                
                console.log("Succes");
            },
            error: function(){
                $("#nombreProd").text("--- Error Ajax checkId!! ---");
                console.log("Fail");
            }
        });
    });
});