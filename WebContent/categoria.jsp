<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*"%>
<%
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/categoria.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/categoria.css" />
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
      
     <!---------------------------------CATEGORIA----------------------------------------->
    <section class="categoria">
      <div class="promo">
        <div class="title-box">
          <h2>Promo</h2>
        </div>
        <div class="row">
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <button type="button" class="btn btn-primary">
              Vedi altro
            </button>
          </div>
        </div>
      </div>
      <div class="novita">
        <div class="title-box">
          <h2>Novita</h2>
        </div>
        <div class="row">
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <button type="button" class="btn btn-primary">
              Vedi altro
            </button>
          </div>
        </div>
      </div>
      <div class="componenti">
        <div class="title-box">
          <h2>Tipologia Componenti</h2>
        </div>
        <div class="row">
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <img src="./img/1.jpg" alt="" />
            <p>titolo</p>
          </div>
          <div class="col-md-3">
            <button type="button" class="btn btn-primary">
              Vedi altro
            </button>
          </div>
        </div>
      </div>
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