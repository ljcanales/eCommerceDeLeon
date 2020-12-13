function agregarCarrito(id_producto,element){  

    var cant = $(element).parent().parent().find("input").val();
    var url = "http://localhost:4567/addProducto?id_producto=" + id_producto.toString() + "&cant=" + cant;

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
    update_cart();
});

function update_cart(){
    var url = "http://localhost:4567/updateCarrito";

    $.ajax({
        url: url,
        type: 'GET',
        success: function(ans) {
            $("#micarrito").html(ans);
            console.log("Cart Update Succes");
        },
        error: function(){
            $("#micarrito").html("<p> Error Ajax updateCarrito </p>");
            console.log("Fail");
        }
    });
}

function deleteProd(id_carrito, id_producto){  

    var url = "http://localhost:4567/delProducto?id_carrito=" + id_carrito + "&id_producto=" + id_producto;
    
    $.ajax({
        url: url,
        type: 'GET',
        success: function(ans) {
            console.log("Succes");
        },
        error: function(){
            console.log("Fail");
        }
    });   
    update_cart();
}

function updateCant(id_producto, op){
    var option = op? "increase" : "decrease";

    var url = "http://localhost:4567/updateCant?op=" + option + "&id_producto=" + id_producto;
    
    $.ajax({
        url: url,
        type: 'GET',
        async : false,
        success: function(ans) {
            console.log("increaseItem(): Succes");
        },
        error: function(){
            console.log("increaseItem(): Fail");
        }
    });
    update_cart();
}
