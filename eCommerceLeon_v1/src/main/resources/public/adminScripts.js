function getFormPromo() {
    var url = "http://localhost:4567/getForm?name=" + "addPromocion";

    $.ajax({
        url: url,
        type: 'GET',
        success: function(ans) {
            $("#form-container").html(ans); //verificar id del contenedor
            
            console.log("Succes");
        },
        error: function() {
            $("#form-container").html("<p>--- Error Ajax select!! ---</p>");
            console.log("Fail");
        }
    });
}

$(document).ready(function(){

    //   CHECK ID PRODUCTO
    $("#form-container").on("keyup", "#IdProd", function() { //verificar id del input
        if($(this).val().toString() == "") {
            $("#btnAgregar").prop('disabled', true);
            $("#cant").prop('disabled', true);
            $("#nombreProd").val("");
            return;
        }
        
        var url = "http://localhost:4567/checkId?id=" + $(this).val().toString();

        $.ajax({
            url: url,
            type: 'GET',
            success: function(ans) {
                $("#nombreProd").val(ans.toString()); //verificar id del input
                if(ans != "") {
                    $("#btnAgregar").prop('disabled', false);
                    $("#cant").prop('disabled', false);
                } else {
                    $("#btnAgregar").prop('disabled', true);
                    $("#cant").prop('disabled', true);
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