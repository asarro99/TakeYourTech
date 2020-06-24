<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/carrello.css" />
      <link
         rel="stylesheet"
         href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
         />
      <link
         rel="stylesheet"
         href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
         />
   </head>
   <body>
      <!-- TOP NAV BAR -->
      <jsp:include page="./utility/header.jsp"/>
      <!-- SIDE MENU -->
      <jsp:include page="./utility/sidemenu.jsp"/>
      
       <!---------------------------------CARRELLO----------------------------------------->
    <section class="title">
      <div class="title-box">
        <h2>Carrello</h2>
      </div>
    </section>
    <section class="carrello">
      <table class="rwd-table">
        <tr>
          <th>Img</th>
          <th>Descrizione</th>
          <th>Quantita</th>
          <th>Prezzo</th>
          <th>Modifica</th>
        </tr>
        <tr>
          <td data-th="Img">
            <img src="./img/1.jpg" alt="" />
          </td>
          <td data-th="Descrizione">
            Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do
            eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad
            minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi
            ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit
            in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
            Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui
            officia deserunt mollit anim id est laborum
          </td>
          <td data-th="Quantita">Quantita</td>
          <td data-th="Prezzo">Prezzo</td>
          <td data-th="Modifica">
            <button type="button" class="btn btn-primary">
              Rimuovi
            </button>
          </td>
        </tr>
        <tr>
          <td data-th="Img">
            <img src="./img/1.jpg" alt="" />
          </td>
          <td data-th="Descrizione">
            Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do
            eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad
            minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi
            ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit
            in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
            Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui
            officia deserunt mollit anim id est laborum
          </td>
          <td data-th="Quantita">Quantita</td>
          <td data-th="Prezzo">Prezzo</td>
          <td data-th="Modifica">
            <button type="button" class="btn btn-primary">
              Rimuovi
            </button>
          </td>
        </tr>
        <tr>
          <td data-th="Img">
            <img src="./img/1.jpg" alt="" />
          </td>
          <td data-th="Descrizione">
            Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do
            eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad
            minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi
            ut aliquid ex ea commodi consequatur. Duis aute irure reprehenderit
            in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
            Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui
            officia deserunt mollit anim id est laborum
          </td>
          <td data-th="Quantita">Quantita</td>
          <td data-th="Prezzo">Prezzo</td>
          <td data-th="Modifica">
            <button type="button" class="btn btn-primary">
              Rimuovi
            </button>
          </td>
        </tr>
      </table>
    </section>

    <div class="totale">
      <h2>Prezzo totale:</h2>
      <p>120 Euro</p>
    </div>
    
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	    <script>
    window.onresize = function(){
    if(window.innerWidth >= 981){
    		document.getElementById("side-menu").removeAttribute('style');
            document.getElementById("menu-btn").removeAttribute('style');
            document.getElementById("close-btn").removeAttribute('style');
    	}
    }
    function openMenu() {
        document.getElementById("side-menu").style.display = "block";
        document.getElementById("menu-btn").style.display = "none";
        document.getElementById("close-btn").style.display = "block";
      }

      function closeMenu() {
        document.getElementById("side-menu").style.display = "none";
        document.getElementById("menu-btn").style.display = "block";
        document.getElementById("close-btn").style.display = "none";
      }
    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
   </body>
</html>