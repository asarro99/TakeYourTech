<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%@page import="com.fasterxml.jackson.databind.node.ObjectNode"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.model.bean.ProductBean"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%
   ProductBean prodotto = (ProductBean)request.getAttribute("prodotto");
   if(prodotto==null)
   {
   	response.sendRedirect("./Product?page=/product.jsp&codiceprod=33");	
   	return;
   }
   String sidemenu = (String)request.getAttribute("sidemenu");
   if(sidemenu == null) {
   	response.sendRedirect("./Product?page=/product.jsp&codiceprod=33");	
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
                           <img src="./getPicture?id=<%=prodotto.getCode()%>" class="d-block w-100" />
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-md-7">
                  <h2><%=prodotto.getName() %></h2>
                  <p>Codice Prodotto: <%=prodotto.getCode() %></p>
                  <%
                     Random rand = new Random();
                     int valore = rand.nextInt(4) + 1;
                     for(int i=0; i<valore; i++){
                     out.println("<i class=\"fa fa-star\"></i>");
                     }
                     int valore2 = rand.nextInt(2);
                     for(int j=0; j<valore2; j++){
                     out.println("<i class=\"fa fa-star-half-o\"></i>");
                     }
                     %>
                  <p class="price"><%=prodotto.getPrice()%> Euro</p>
                  <p><b>Disponibilità: </b><%if(prodotto.getQuantity()>50)out.println("<span style=\"color:#1bd615\">In Magazzino</span>"); else if(prodotto.getQuantity()<=50 && prodotto.getQuantity()>0) out.println("<span style=\"color:#f9d342\">In esaurimento</span>"); else  out.println("<span style=\"color:#ff0000\">Non disponibile</span>"); %></p>
                  <p><b>Condizioni: </b>Nuovo</p>
                  <p id="brand"></p>
                  <%
                     if(prodotto.getQuantity()>0)
                     {
                     %>
                  <label>Quantità: </label>
                  <form action="./Product?page=/carrello.jsp&codiceprod=<%= prodotto.getCode()%>&action=addC" method="post">
                     <input type="text" value="1" name="quantita"/>
                     <button type="submit" class="btn btn-primary">
                     Aggiungi al carrello
                     </button>
                  </form>
                  <%
                     }
                     %>
               </div>
            </div>
         </div>
      </section>
      <!---------------------------------DESCRIZIONE----------------------------------------->
      <section class="product-description">
         <div class="container">
            <h6>Descrizione Prodotto</h6>
            <p align="justify" id="descrizione">
            </p>
            <hr />
         </div>
      </section>
      <!---------------------------------FOOTER----------------------------------------->
      <jsp:include page="./utility/footer.jsp"/>
      <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
      <script src="./js/app.js"></script>
      <script src="./js/product.js"></script>
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
      <!---------------------------------ASSISTENZA----------------------------------------->
      <script> 
         window.intergramId = "49403884";
         window.intergramCustomizations = {
            titleClosed: 'Contattaci!',
            titleOpen: 'Supporto TakeYourTech',
            introMessage: 'Ciao! Come possiamo aiutarti?',
            autoResponse: 'Grazie per averci contattato! Un nostro operatore ti rispondera\' a breve.',
            autoNoResponse: 'Ci dispiace per l\'attesa i nostri operatori sono tutti occupati,'+
                            ' tra pochi minuti sarai contattato.',
            mainColor: "#ff5722", // Can be any css supported color 'red', 'rgb(255,87,34)', etc
            alwaysUseFloatingButton: false // Use the mobile floating button also on large screens
         };
      </script>
      <script id="intergram" type="text/javascript" src="https://www.intergram.xyz/js/widget.js"></script>
   </body>
</html>