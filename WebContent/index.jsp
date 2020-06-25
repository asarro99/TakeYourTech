<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*"%>
<%
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/index.jsp");	
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
      <section class="header">
         <!-- SIDE MENU -->
        <jsp:include page="./utility/sidemenu.jsp">
        	<jsp:param value="<%=sidemenu%>" name="categorie"/>
        </jsp:include>
         <!-- SLIDER -->
         <div class="slider">
            <div
               id="slider"
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
                  <div class="carousel-item">
                     <img src="./img/2.jpg" class="d-block w-100" />
                  </div>
               </div>
               <ol class="carousel-indicators">
                  <li data-target="#slider" data-slide-to="0" class="active"></li>
                  <li data-target="#slider" data-slide-to="1"></li>
                  <li data-target="#slider" data-slide-to="2"></li>
                  <li data-target="#slider" data-slide-to="3"></li>
               </ol>
            </div>
         </div>
      </section>
      <!---------------------------------IN PRIMO PIANO----------------------------------------->
      <section class="featured-categories">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <img src="./img/1.jpg" />
          </div>
          <div class="col-md-4">
            <img src="./img/2.jpg" />
          </div>
          <div class="col-md-4">
            <img src="./img/3.jpg" />
          </div>
        </div>
      </div>
    </section>
     <!---------------------------------IN VENDITA----------------------------------------->
     <section class="on-sale">
      <div class="container">
        <div class="title-box">
          <h2>In vendita</h2>
        </div>
        <div class="row">
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/1.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Msi Portatile</h3>
              <h5>800 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/3.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Iphone Se</h3>
              <h5>400 Euro</h5>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!---------------------------------NUOVI PRODOTTI----------------------------------------->
    <section class="new-products">
      <div class="container">
        <div class="title-box">
          <h2>Nuovi Prodotti</h2>
        </div>
        <div class="row">
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/1.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Msi Portatile</h3>
              <h5>800 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/3.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Iphone Se</h3>
              <h5>400 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./img/2.jpg" />
              <div class="overlay-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3>Mackbook Pro</h3>
              <h5>3000 Euro</h5>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!---------------------------------CARATTERISTICHE----------------------------------------->
    <section class="website-features">
      <div class="container">
        <div class="row">
          <div class="col-md-3 feature-box">
            <img src="./img/3.jpg" />
            <div class="feature-text">
              <p><b>Prodotti 100% originali </b>presso la nostra compagnia!</p>
            </div>
          </div>
          <div class="col-md-3 feature-box">
            <img src="./img/3.jpg" />
            <div class="feature-text">
              <p><b>Reso entro 30 giorni </b>dal ricevimento del prodotto!</p>
            </div>
          </div>
          <div class="col-md-3 feature-box">
            <img src="./img/3.jpg" />
            <div class="feature-text">
              <p><b>Spedizione gratuita </b>per ordini superiori a 500 Euro!</p>
            </div>
          </div>
          <div class="col-md-3 feature-box">
            <img src="./img/3.jpg" />
            <div class="feature-text">
              <p>
                <b>Paga online </b>con varie soluzioni offerte dal nostro sito!
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	<script src="./js/app.js"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
   </body>
</html>