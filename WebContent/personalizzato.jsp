<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/personalizzato.css" />
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
	
	    <!---------------------------------SCELTA COMPONENTI----------------------------------------->
    <section class="selection">
      <div class="selection_copy">
        <h4>Scelta progressiva</h4>
      </div>
      <div class="componenti">
        <div class="componente" id="mboard"></div>
        <div class="componente"></div>
        <div class="componente"></div>
        <div class="componente"></div>
        <div class="componente"></div>
        <div class="componente"></div>
      </div>
      <div class="componente_corrente">
        <p>Scelta componente corrente</p>
      </div>
    </section>

    <!---------------------------------LISTA COMPONENTI----------------------------------------->
    <section class="list">
      <div class="list_copy">
        <h4>Risultato Assemblaggio</h4>
      </div>
      <div class="list_img">
        <img src="./img/propic1.jpg" alt="" />
      </div>
      <div class="list_elements">
        <ul>
          <li>Scheda Madre</li>
          <li>CPU</li>
          <li>GPU</li>
          <li>RAM</li>
          <li>SSD</li>
          <li>HDD</li>
        </ul>
      </div>
      <button type="button" class="btn btn-primary">
        Aggiungi al carrello
      </button>
    </section>
	
    
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