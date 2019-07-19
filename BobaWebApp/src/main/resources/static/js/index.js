$(document).ready(function(){


      myMap();

});

var map;
function myMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    zoom: 15
  });
}
