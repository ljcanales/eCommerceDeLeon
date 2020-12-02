function agregarCarrito(id_producto){  

		var url = "http://localhost:4567/addProducto?id_producto=" + id_producto.toString() + "&cant=1";

		//alert(url);
		//console.log("cantidad"+$(element).parent().find("input").val());
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

$(document).ready(function(){
	var url = "http://localhost:4567/updateCarrito";

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
});