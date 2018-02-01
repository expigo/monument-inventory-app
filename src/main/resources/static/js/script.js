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

  $(document).ready(function () {

        // datatable
        $('.datatable__table').DataTable({
            "language": {
                "search": "Szukaj:"
              },
            responsive: {
                breakpoints: [
                    { name: 'large', width: Infinity },
                    { name: 'normal',  width: 1200 },
                    { name: 'medium',  width: 900 },
                    { name: 'small',   width: 600 }
                ]
            }
        });

        /*Scroll on buttons*/
      $('.navigation__link--2').click(function () {
          $('html, body').animate({
              scrollTop: /*dist from top*/ $('.section-about').offset().top
          }, 1000);
          $('.navigation__checkbox').prop('checked', false); // Uncheck checkbox
      })
      $('.navigation__link--3').click(function () {
        $('.navigation__checkbox').prop('checked', false); // Uncheck checkbox
          
              $('html, body').animate({
                  scrollTop: /*dist from top*/ $('.section-cards').offset().top
              }, 1000);
      })

      $('.navigation__link--4').click(function () {
        $('.navigation__checkbox').prop('checked', false); // Uncheck checkbox
          
              $('html, body').animate({
                  scrollTop: /*dist from top*/ $('.section-search').offset().top
              }, 1000);
      })

      $('.navigation__link--5').click(function () {
          
              $('html, body').animate({
                  scrollTop: /*dist from top*/ $('.section-about').offset().top
              }, 1000);
      })
  
      /*smooth scrolling*/
          // Select all links with hashes
      $('a[href*="#"]')
          // Remove links that don't actually link to anything
          .not('[href="#"]').not('[href="#0"]').not('[href*="#popup"]').click(function (event) {
              // On-page links
              if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
                  // Figure out element to scroll to
                  var target = $(this.hash);
                  target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                  // Does a scroll target exist?
                  if (target.length) {
                      // Only prevent default if animation is actually gonna happen
                      event.preventDefault();
                      $('html, body').animate({
                          scrollTop: target.offset().top
                      }, 1000, function () {
                          // Callback after animation
                          // Must change focus!
                          var $target = $(target);
                          $target.focus();
                          if ($target.is(":focus")) { // Checking if the target was focused
                              return false;
                          }
                          else {
                              $target.attr('tabindex', '-1'); // Adding tabindex for elements not focusable
                              $target.focus(); // Set focus again
                          };
                      });
                  }
              }
          });
      // end



  });

//   $(document).ready(function() {
//     $('.datatable__table').DataTable();
// } );