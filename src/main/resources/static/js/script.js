
// new GMaps({
//     div: '.popup__map',
//     lat: -12.043333,
//     lng: -77.028333
//   });

function initMap() {
    var townHall = {lat: 50.294042, lng: 18.665822};
    var mapHall = new google.maps.Map(document.getElementById('map--town-hall'), {
      zoom: 15,
      center: townHall
    });
    var marker = new google.maps.Marker({
      position: townHall,
      map: mapHall
    });

    var willaCaro = {lat: 50.295417, lng: 18.667528};
    var mapCaro = new google.maps.Map(document.getElementById('map--willa-caro'), {
      zoom: 15,
      center: willaCaro
    });
    var marker = new google.maps.Marker({
      position: willaCaro,
      map: mapCaro
    });

    var radioStation = {lat: 50.313389, lng: 18.688964};
    var mapRadio = new google.maps.Map(document.getElementById('map--radio-station'), {
      zoom: 15,
      center: radioStation
    });
    var marker = new google.maps.Marker({
      position: radioStation,
      map: mapRadio
    });
  }