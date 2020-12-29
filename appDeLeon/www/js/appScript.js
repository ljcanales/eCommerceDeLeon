var ipAddress = "192.168.1.41";
var appServer = "http://" + ipAddress + ":4567/app/";
var server = "http://" + ipAddress + ":4567/";

document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    // Now safe to use device APIs

  
}

$(document).ready(function () {
  // VERIFICA SI ESTA INICIADA LA SESION
  if(window.localStorage.getItem("username") == null){
    btnNoLogueado();
    window.localStorage.clear();
  } else {
    btnLogueado();
  }
  
});

function toggleNav() {
  if ( $("#mySidenav").css("width") == "0px") {
    $("#mySidenav").css("width", "250px");
  } else {
    $("#mySidenav").css("width", "0px");
  }
  
}

/* Set the width of the side navigation to 0 */
function closeNav() {
  $("#mySidenav").css("width", "0px");
}

//MOSTRAR CATALOGO
function mostrarCatalogo() {
  $.ajax({
    type: "get",
    url: appServer + "getProductos",
    success: function (response) {
      $("#main-panel").html(response);
      //cambio src de imagen segun ip server
      $("#main-panel").find("img").each(function () { 
        var newSrc = server + "img/" + $(this).attr("src");
        $(this).attr("src", newSrc);
      });
      closeNav();
    },
    error: function () {
      console.log("fail");
    }
  });
}

// agregar a carrito
function agregarCarrito(id_producto, element){
  if(window.localStorage.getItem("username") == null){ // CONTROL DE LOGUEO
    inicioSesion();
    closeNav();
    return;
  }

  var cant = $(element).parent().parent().find("input").val();
  
  $.ajax({
    type: 'post',
    url: appServer + "addProducto",
    data: "cart_id=" + window.localStorage.getItem("cart_id") + "&id_producto=" + id_producto + "&cant=" + cant,
    success: function(ans) {
        
    },
    error: function(){
        console.log("fail");
    }
  });
}

//INICIO SESION
function inicioSesion() {
  $.ajax({
    type: "get",
    url: appServer + "login",
    success: function (response) {
      // FORMULARIO DE INICIO SESION
      $("#main-panel").html(response);

      // CAPTURA DE SUBMIT FORMULARIO
      $("#formulario").bind("submit", function() {
        // INICIO
        $.ajax({
          type: "get",
          url: appServer + "login?" + $(this).serialize(),
          success: function (response) {
            // USUARIO LOGUEADO
            var jsonAnswer = JSON.parse(response);
      
            // GUARDAR EN LOCALSTORE
            window.localStorage.setItem("user_id", jsonAnswer["user_id"]);
            window.localStorage.setItem("user_type", jsonAnswer["user_type"]);
            window.localStorage.setItem("username", jsonAnswer["username"]);
            window.localStorage.setItem("cart_id", jsonAnswer["cart_id"]);
            
            $("#main-panel").html("<div class='alert alert-warning mx-1 my-3' role='alert'> Bienvenido  <b>"+window.localStorage.getItem("username")+"</b></div>");
            btnLogueado();
            setTimeout(mostrarCatalogo, 2000);
          },
          error: function () {
            // USUARIO NO EXISTE
            $("#main-panel").html("el usuario no existe");
          }
        });
        return false;
      });
      closeNav();
    },
  });
  
}
function cerrarSesion() {
  btnNoLogueado();
  window.localStorage.clear();
  closeNav();
}

// MIS PEDIDOS
function mostrarPedidos() {
  $.ajax({
    type: "get",
    url: appServer + "getPedidos?user_id=" + window.localStorage.getItem("user_id"),
    success: function (response) {
      $("#main-panel").html(response);
      // EVENTO PARA MOSTRAR DETALLES DE PEDIDO
      $("#main-panel").find("tbody tr").on("click", function verDetallePedido() {
        $.ajax({
          type: "get",
          url: appServer + "getDetallesPedidos",
          data: "id_pedido=" + $(this).find(".id_pedido").text(),
          success: function (response) {
            $("#main-panel").html(response);
          }
        });
      });
      closeNav();
    }
  });
}

// VER CARRITO
function verCarrito() {
  if(window.localStorage.getItem("cart_id") == null)
    return;
  
  $.ajax({
    type: "get",
    url: appServer + "verCarrito",
    data: "cart_id=" + window.localStorage.getItem("cart_id"),
    success: function (response) {
      $("#main-panel").html(response);
      $("#main-panel").find("img").each(function () { 
        var newSrc = server + "img/" + $(this).attr("src");
        $(this).attr("src", newSrc);
      });
      closeNav();
    }
  });
}

// CONTROL botones LOGUEADO
function btnLogueado() {
  $("#btniniciar").hide();
  $("#btncerrar").show();
  $("#btnpedidos").show();
}

function btnNoLogueado() {
  $("#btniniciar").show();
  $("#btncerrar").hide();
  $("#btnpedidos").hide();
}