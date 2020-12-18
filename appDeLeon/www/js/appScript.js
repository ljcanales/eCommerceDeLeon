document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    // Now safe to use device APIs

    /* Set the width of the side navigation to 250px */
  
}

function openNav() {
  //document.getElementById("mySidenav").style.width = "250px";
  $("#mySidenav").css("width", "250px");
  console.log("ola");
}

/* Set the width of the side navigation to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
} 