<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/product.jsp");	
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
      <link rel="stylesheet" href="./css/product.css" />
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
      <jsp:include page="./utility/sidemenu.jsp">
        	<jsp:param value="<%=sidemenu%>" name="categorie"/>
        </jsp:include>
	
<!---------------------------------SINGOLO----------------------------------------->
    <section class="single-product">
      <div class="container">
        <div class="row">
          <div class="col-md-5">
            <div
              id="product-slider"
              class="carousel slide carousel-fade"
              data-ride="carousel"
            >
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img src="./img/1.jpg" class="d-block w-100" />
                </div>
                <div class="carousel-item">
                  <img src="./img/2.jpg" class="d-block w-100" />
                </div>
                <div class="carousel-item">
                  <img src="./img/3.jpg" class="d-block w-100" />
                </div>
                <a
                  class="carousel-control-prev"
                  href="#product-slider"
                  role="button"
                  data-slide="prev"
                >
                  <span
                    class="carousel-control-prev-icon"
                    aria-hidden="true"
                  ></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a
                  class="carousel-control-next"
                  href="#product-slider"
                  role="button"
                  data-slide="next"
                >
                  <span
                    class="carousel-control-next-icon"
                    aria-hidden="true"
                  ></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>
            </div>
          </div>

          <div class="col-md-7">
            <p class="new-arrival text-center">NEW</p>
            <h2>MacBook Pro</h2>
            <p>Codice Prodotto: 123</p>

            <i class="fa fa-star"></i>
            <i class="fa fa-star"></i>
            <i class="fa fa-star"></i>
            <i class="fa fa-star"></i>
            <i class="fa fa-star-half-o"></i>

            <p class="price">1000 Euro</p>
            <p><b>Disponibilità: </b>In magaziono</p>
            <p><b>Condizioni: </b>Nuovo</p>
            <p><b>Brand: </b>Apple Inc.</p>
            <label>Quantità: </label>
            <input type="text" value="1" />
            <button type="button" class="btn btn-primary">
              Aggiungi al carrello
            </button>
          </div>
        </div>
      </div>
    </section>

    <!---------------------------------DESCRIZIONE----------------------------------------->
    <section class="product-description">
      <div class="container">
        <h6>Descrizione Prodotto</h6>
        <p align="justify">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam molestias
          quaerat eligendi numquam aspernatur, obcaecati consequuntur quos
          assumenda recusandae doloremque libero aut iusto facilis, officia
          dolor debitis quae doloribus labore. Lorem ipsum dolor sit amet
          consectetur adipisicing elit. Nam molestias quaerat eligendi numquam
          aspernatur, obcaecati consequuntur quos assumenda recusandae
          doloremque libero aut iusto facilis, officia dolor debitis quae
          doloribus labore. Lorem ipsum dolor sit amet consectetur adipisicing
          elit. Nam molestias quaerat eligendi numquam aspernatur, obcaecati
          consequuntur quos assumenda recusandae doloremque libero aut iusto
          facilis, officia dolor debitis quae doloribus labore.
        </p>
        <hr />
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