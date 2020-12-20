var ipAddress = "192.168.1.41";
var appServer = "http://" + ipAddress + ":4567/app/";
var server = "http://" + ipAddress + ":4567/";

document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    // Now safe to use device APIs

  
}

$(document).ready(function () {
  if(window.localStorage.getItem("username") == null){
    $("#btniniciar").show();
    $("#btncerrar").hide();
    window.localStorage.clear();
  } else {
    $("#btniniciar").hide();
    $("#btncerrar").show();
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
        closeNav();
      });
    },
    error: function () {
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
            
            $("#main-panel").html("<p class='display-3 my-3'> Has iniciado sesion como <b>"+window.localStorage.getItem("username")+"</b></p>");
            $("#btniniciar").hide();
            $("#btncerrar").show();
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
  $("#btniniciar").show();
  $("#btncerrar").hide();
  window.localStorage.clear();
  closeNav();
}
