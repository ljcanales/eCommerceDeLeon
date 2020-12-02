
function agregarCarrito(id_producto){  

		var url = "http://localhost:4567/addProducto?id_producto=" + id_producto.toString() + "&cant=1";

/*
		var inputnamec = "cant" + id_producto.toString();

		var cant = $('input[name=inputnamec]').val();
*/		
		alert(url);
		
        $.ajax({
            url: url,
            type: 'GET',
            success: function(ans) {
                $("#micarrito").html(ans);
                console.log("Succes");
            },
            error: function(){
                $("#micarrito").html("<p> Error Ajax addProducto </p>");
                console.log("Fail");
            }
        });
		
}
