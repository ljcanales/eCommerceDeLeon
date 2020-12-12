var cantProducts = 0;
$(document).ready(function(){
    $("#nombreProd").prop('disabled', true);
    $("#cant").prop('disabled', true);
    $("#btnAgregar").prop('disabled', true);
    $("#btnGuardar").prop('disabled', true);
    $("#IdProd").prop('disabled', true);

    //CHECK ID PRODUCTO
    $("#IdProd").on("keyup change", function() { //verificar id del input
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
    
    //CHECK CANTIDAD
    $("#cant").on("keyup change", function() { //verificar id del input
        if($("#nombreProd").val().toString() != "" && $(this).val() > 0) {
            $("#btnAgregar").prop('disabled', false);
        }  
        if($(this).val() < 1) {
            $("#btnAgregar").prop('disabled', true);
        } 
    });
    
    //CHECK FORM INPUTS
    $("#nombrePromo").on("keyup change", ctrlPromo);
    $("#descuento").on("keyup change", ctrlPromo);  
    $("#fechaInicio").on("keyup change", ctrlPromo);
    $("#fechaFin").on("keyup change", ctrlPromo);
    
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
        promocionData = {};

        promocionData["nombre"] = $("#nombrePromo").val().toString();
        promocionData["descuento"] = $("#descuento").val().toString();
        promocionData["fechadesde"] = $("#fechaInicio").val().toString();
        promocionData["fechahasta"] = $("#fechaFin").val().toString();

        var productos = [];
        $("#ProductosCargados").find(".form-row").each(function(){
            productos.push({
                "id" : $(this).find(".id").val(),
                "cantidad" : $(this).find(".cantidad").val()
            });
        });

        dataString = "promodata=" + JSON.stringify(promocionData);
        dataString += "&productos=" + JSON.stringify(productos);
        
        $.ajax({
            type: 'GET',
            url: "addPromocion",
            data: dataString,
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
    var number_today = parseInt(new Date().toJSON().substr(0, 10).replaceAll("-",""));
    var number_from = parseInt($("#fechaInicio").val().replaceAll("-",""));
    var number_to = parseInt($("#fechaFin").val().replaceAll("-",""));

    if( $("#nombrePromo").val().toString() != "" && 
        $("#descuento").val() > 0 && 
        number_from <  number_to  && number_from >= number_today){
             $("#IdProd").prop('disabled', false);
             if(cantProducts > 0)
                $("#btnGuardar").prop('disabled', false);
        }  
    else{
        $("#IdProd").prop('disabled', true);
        $("#btnGuardar").prop('disabled', true);
    }
}