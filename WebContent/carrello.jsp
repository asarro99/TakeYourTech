<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*,com.model.Cart,com.Bean.ProductBean"%>
 <%@page import="com.fasterxml.jackson.databind.node.ObjectNode"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.fasterxml.jackson.databind.JsonNode"%>
<%

	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/carrello.jsp");	
		return;
	}
	
	Cart cart = (Cart) request.getSession().getAttribute("cart");
%>
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
      <jsp:include page="./utility/sidemenu.jsp">
        	<jsp:param value="<%=sidemenu%>" name="categorie"/>
        </jsp:include>
      
       <!---------------------------------CARRELLO----------------------------------------->
    <section class="title">
      <div class="title-box">
        <h2>Carrello</h2>
      </div>
    </section>
    <section class="carrello">
      <table class="rwd-table">
        <tr>
          <th></th>
          <th>Descrizione</th>
          <th>Quantita</th>
          <th>Prezzo</th>
          <th>Modifica</th>
        </tr>
         <%
      		if(cart!=null)
      		{
      			ObjectMapper objectMapper = new ObjectMapper();
      			List<ProductBean> prodcart = cart.getProducts(); 	
 		   		for(ProductBean beancart: prodcart) {
 		   		JsonNode rootNode = objectMapper.readValue(beancart.getDescription(), JsonNode.class);
      	%>
        <tr>
          <td data-th="">
            <img src="./getPicture?id=<%=beancart.getCode() %>" alt="" />
          </td>
          <td data-th="Descrizione">
            <%=rootNode.get("descrizione").asText() %>
          </td>
          <td data-th="Quantita"><input class="inputQuantita" id="modQuantita<%=beancart.getCode() %>" type="text" value="<%=beancart.getQuantity() %>"></input></td>
          <td id= "prezzoProdotto<%=beancart.getCode() %>" data-th="Prezzo"><%=beancart.getPrice()*beancart.getQuantity()%> Euro</td>
          <td data-th="Modifica">
          	<a href="Product?page=/carrello.jsp&action=removeC&id=<%=beancart.getCode()%>">
          	  <button type="button" class="btn btn-primary">
              Rimuovi
            </button>
          	</a>
          </td>
        </tr>
        <%
 		   		}
      		}
        %>
      </table>
    </section>

    <div class="totale">
      <h2>Prezzo totale:</h2>
      <p id="prezzoTotale"><%=cart.getTotal() %></p>
      <div>
      <a href="Product?page=/carrello.jsp&action=checkout">
          	  <button type="button" class="btn btn-primary">
              Checkout
            </button>
      </a>
      </div>
    </div>
    
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	<script src="./js/carrello.js"></script>
	
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