var cantProducts = 0;
$(document).ready(function(){
    $("#nombreProd").prop('disabled', true);
    $("#cant").prop('disabled', true);
    $("#btnAgregar").prop('disabled', true);
    $("#btnGuardar").prop('disabled', true);

    //CHECK ID PRODUCTO AL INGRESAR POR TECLADO
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
                    //$("#btnAgregar").prop('disabled', false);
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
    
    //CHECK ID PRODUCTO AL INGRESAR CON BOTONES
    $("#IdProd").change(function() { //verificar id del input
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
                    //$("#btnAgregar").prop('disabled', false);
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
    
    //CHECK CANTIDAD AL INGRESAR POR TECLADO
    $("#cant").keyup(function() { //verificar id del input
        if($("#nombreProd").val().toString() != "" && $(this).val() > 0) {
            $("#btnAgregar").prop('disabled', false);
        }  
        if($(this).val() < 1) {
            $("#btnAgregar").prop('disabled', true);
        } 
    });
    
    //CHECK CANTIDAD AL INGRESAR CON BOTONES
    $("#cant").change(function() { //verificar id del input
        if($("#nombreProd").val().toString() != "" && $(this).val() > 0) {
            $("#btnAgregar").prop('disabled', false);
        } 
        if($(this).val() < 1) {
            $("#btnAgregar").prop('disabled', true);
        } 
    });
    
    //CHECK FORM INPUTS
    /*$("#nombrePromo").change(ctrlPromo());
    $("#nombrePromo").keyup(ctrlPromo());    
    $("#descuento").change(ctrlPromo());
    $("#descuento").keyup(ctrlPromo());    
    $("#fechaInicio").change(ctrlPromo());
    $("#fechaInicio").keyup(ctrlPromo());
    $("#fechaFin").change(ctrlPromo());
    $("#fechaFin").keyup(ctrlPromo());
    */
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
        
        //CONTROLES DE CAMPOS
        $("#btnGuardar").prop('disabled', false);
        $("#IdProd").val("");
        $("#cant").val("");
        $("#nombreProd").val("");
        $("#cant").prop('disabled', true);
        
        //CANTIDAD DE PRODUCTOS PARA CONTROL DEL BOTON GUARDAR
        cantProducts += 1;
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
    cantProducts -= 1;
    
    if(cantProducts < 1)
        $("#btnGuardar").prop('disabled', true);
}

function ctrlPromo() {
    var today = new Date();
    if( $("#nombrePromo").val().toString() != "" && 
        $("#descuento").val() > 0 && 
        $("#fechaInicio").val() <  $("#fechaFin").val() &&
        $("#fechaInicio").val() >= today){
            $("#btnGuardar").prop('disabled', true);
            $("#IdProd").prop('disabled', true);
        }     
        alert("hola");
}