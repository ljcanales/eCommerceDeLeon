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
    var messaje = "al parecer la BD es mas lenta que la logica del sitio, por lo que el carrito se actualiza antes que la BD";
    messaje+= ", por ese motivo envio una alerta antes de actualizar para darle el tiempo a la BD de realizar los cambios";
    messaje+= ", estando en el carrito, al aparecer la alerta, presionar enter...";
    alert(messaje);
    update_cart();
});

function update_cart(){
    var url = "http://localhost:4567/updateCarrito";
    alert("update");
    $.ajax({
        url: url,
        type: 'GET',
        success: function(ans) {
            $("#micarrito").html(ans);
            console.log("Succes");
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

function increaseItem(id_producto){
    var url = "http://localhost:4567/updateCant?op=increase" + "&id_producto=" + id_producto;
    
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
function decreaseItem(id_producto){
    var url = "http://localhost:4567/updateCant?op=decrease" + "&id_producto=" + id_producto;
    
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
