function agregarCarrito(id_producto,element){  

    var cant = $(element).parent().parent().find("input").val();
    var url = "http://localhost:4567/addProducto?id_producto=" + id_producto.toString() + "&cant=" + cant;

    $.ajax({
        url: url,
        type: 'GET',
        success: function(ans) {
            $("#micarrito").html(ans);
            console.log("agregarCarrito(): Succes");
        },
        error: function(){
            $("#micarrito").html("<p> Error Ajax addProducto </p>");
            console.log("agregarCarrito(): Fail");
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
            console.log("update_cart(): Succes");
        },
        error: function(){
            $("#micarrito").html("<p> Error Ajax updateCarrito </p>");
            console.log("update_cart(): Fail");
        }
    });
}

function deleteProd(id_producto){  

    var url = "http://localhost:4567/delProducto?id_producto=" + id_producto;
    
    $.ajax({
        url: url,
        type: 'GET',
        async : false,
        success: function(ans) {
            console.log("deleteProd(): Succes");
        },
        error: function(){
            console.log("deleteProd(): Fail");
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

function comprar(){

    var url = "http://localhost:4567/comprarCarrito";
    
    $.ajax({
        url: url,
        type: 'GET',
        async : false,
        success: function(ans) {
            console.log("comprar(): Succes");
        },
        error: function(){
            console.log("comprar(): Fail");
        }
    });
    update_cart();
}

//function filtrarPor(filtro){ 
//    var url = "http://localhost:4567/getPedidos?filtro=" + filtro;
//    window.location.href = url;
//    alert(url);
//    window.location.replace(url);
//}