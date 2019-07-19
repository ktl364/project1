var map;

$(document).ready(function(){

      $('#sortByPrice').hide();

      myMap();

});

function myMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    zoom: 15
  });
}

function priceClick() {

  $('#sortByPrice').show();
  $('#sortByAlpha').hide();

}

function alphaClick() {

  $('#sortByPrice').hide();
  $('#sortByAlpha').show();

}
