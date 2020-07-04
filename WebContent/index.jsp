<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*"%>
     <%@ page import="java.util.*,com.Bean.ProductBean"%>
<%
	Collection<?> products = (Collection<?>)request.getAttribute("prodotti");
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/index.jsp&action=indexProd");	
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
           <%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				int cont=0;
				while (it.hasNext() && cont<8) {
					cont++;
					ProductBean bean = (ProductBean) it.next();
		%>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./getPicture?id=<%=bean.getCode() %>" />
              <div class="overlay-right">
                <a href="Product?page=/product.jsp&codiceprod=<%=bean.getCode()%>">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                </a>
                <a href="./Product?page=/carrello.jsp&codiceprod=<%= bean.getCode()%>&action=addC&quantita=1">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
                </a>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3><%=bean.getName() %></h3>
              <h5><%=bean.getPrice() %> Euro</h5>
            </div>
          </div>
       	<%
				}
			}
		%>
        </div>
      </div>
    </section>
    <!---------------------------------NUOVI PRODOTTI----------------------------------------->
    <section class="new-products">
    <div class="title-box">
          <h2>Nuovi Prodotti</h2>
        </div>
      <div class="container">
        <div class="row">
           <%
			if (products != null && products.size() != 0) {
				
				Iterator<?> it = products.iterator();
				int cont=0;
				while (it.hasNext()) {
					cont++;
					if(cont >8)
					{
					ProductBean bean = (ProductBean) it.next();
		%>
          <div class="col-md-3">
            <div class="product-top">
              <img src="./getPicture?id=<%=bean.getCode() %>" />
              <div class="overlay-right">
                <a href="Product?page=/product.jsp&codiceprod=<%=bean.getCode()%>">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                </a>
                <a href="./Product?page=/carrello.jsp&codiceprod=<%= bean.getCode()%>&action=addC&quantita=1">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
                </a>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
              <h3><%=bean.getName() %></h3>
              <h5><%=bean.getPrice() %> Euro</h5>
            </div>
          </div>
       	<%
					}
					else
					{
						it.next();
					}
				}
			}
		%>
          
        </div>
      </div>
    </section>
    <!---------------------------------CARATTERISTICHE----------------------------------------->
    <section class="website-features">
      <div class="container">
        <div class="row">
          <div class="col-md-3 feature-box">
            <img src="./img/svg/originale.svg" />
              <p><b>Prodotti 100% originali </b>presso la nostra compagnia!</p>
          </div>
          <div class="col-md-3 feature-box">
            <img src="./img/svg/reso.svg" />
              <p><b>Reso entro 30 giorni </b>dal ricevimento del prodotto!</p>
          </div>
          <div class="col-md-3 feature-box">
            <img src="./img/svg/spedizione.svg" />
              <p><b>Spedizione gratuita </b>per ordini superiori a 500 Euro!</p>
          </div>
          <div class="col-md-3 feature-box">
            <img src="./img/svg/pagamento.svg" />
              <p>
                <b>Paga online </b>con varie soluzioni offerte dal nostro sito!
              </p>
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