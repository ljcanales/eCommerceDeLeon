$(document).ready(function(){
    $("#IdProd").change(function(){ //verificar id del input
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