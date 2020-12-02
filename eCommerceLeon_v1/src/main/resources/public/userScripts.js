
function agregarCarrito(id_producto){  

		var url = "http://localhost:4567/addProducto?id_producto=" + id_producto.toString() + "&cant=1";

		alert(url);
		
        $.ajax({
            url: consulta,
            type: 'GET',
            success: function(ans) {
                $("#contenedor").html(ans);
                console.log("Succes");
            },
            error: function(){
                $("#contenedor").html("<p> Error Ajax addProducto </p>");
                console.log("Fail");
            }
        });
		
}
