var ipAddress = "192.168.1.41";
var appServer = "http://" + ipAddress + ":4567/app/";
var server = "http://" + ipAddress + ":4567/";

document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    // Now safe to use device APIs

  
}

$(document).ready(function () {
  
  
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
      $("#main-panel").html(response);
      closeNav();
    }
  });
  
}
