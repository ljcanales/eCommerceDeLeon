
$(document).ready(function(){
    $("#nombreProd").prop('disabled', true);
    $("#cant").prop('disabled', true);
    $("#btnAgregar").prop('disabled', true);

    //   CHECK ID PRODUCTO
    $("#IdProd").keyup(function() { //verificar id del input
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

function add(){  
        var id = "value=\"" + $("#IdProd").val() + "\"";

        var nombre = "value=\"" + $("#nombreProd").val() + "\"";
        var cantidad = "value=\"" + $("#cant").val() + "\"";

        var htmlstring=	"<div class=\"form-row align-items-end\">";
        htmlstring+=			"<div class=\"form-group col-md-3\">";
        htmlstring+=				"<input type=\"number\" class=\"form-control id\""+ id +"readonly>";
        htmlstring+=			"</div>";
        
        htmlstring+= 			"<div class=\"form-group col-md-5\">";
        htmlstring+=				"<input type=\"text\" class=\"form-control nombre\""+ nombre +"readonly>";
        htmlstring+=			"</div>";
        
        htmlstring+=			"<div class=\"form-group col-md-2\">";
        htmlstring+=				"<input type=\"number\" class=\"form-control cantidad\""+ cantidad +"readonly>";
        htmlstring+=			"</div>";
        
        htmlstring+=			"<div class=\"form-group col-md-2\">";
        htmlstring+=				"<button type=\"button\" onclick=\"eliminar(this)\" class=\"btn btn-primary\" style=\"width: 100%;\">Eliminar</button>";
        htmlstring+=			"</div>";
        htmlstring+=		"</div>";

        $("#ProductosCargados").append(htmlstring); 

    }
    function save(){
        var todo = "";
        $("#ProductosCargados").find(".form-row").each(function(){
        todo += $(this).find(".id").val() + ",";
        todo += $(this).find(".cantidad").val() + "-";
        });

        var url = "http://localhost:4567/addPromocion?";
        url += "nombre="+$("#nombrePromo").val().toString();
        url += "&descuento="+$("#descuento").val().toString();
        url += "&fechadesde="+$("#fechaInicio").val().toString();
        url += "&fechahasta="+$("#fechaFin").val().toString();
        url += "&productos="+ todo;
        console.log(url);

        $.ajax({
            url: url,
            type: 'GET',
            success: function(ans) {
                var html_string =  "<button name='btnVolver' onClick='window.location.reload();' class='btn btn-success'>Volver</button>";
                console.log("Succes");
                if(ans == "ok"){
                    $("#main-container").html(html_string);
                }
            },
            error: function(){
                console.log("Fail");
            }
        });
}
function eliminar(element) {
    $(element).parent().parent().remove();
}