<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.model.Cart,com.Bean.ProductBean"%>
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
      <link rel="stylesheet" href="./css/ordine.css" />
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
      <!---------------------------------ORDINE----------------------------------------->
      <div align="center">
         <table class="tabella">
            <thead>
               <tr>
                  <th>
                     Descrizione
                  </th>
                  <th>
                     Quantit� 
                  </th>
                  <th>
                     IVA
                  </th>
                  <th>
                     Prezzo
                  </th>
               </tr>
            </thead>
            <% List<ProductBean> prodcart = cart.getProducts(); 	
               for(ProductBean beancart: prodcart) {
               %>
            <tbody>
               <tr>
                  <td><%=beancart.getDescription()%></td>
                  <td><%=beancart.getQuantity()%></td>
                  <td>22%</td>
                  <td><%=beancart.getPrice()%> Euro</td>
               </tr>
            </tbody>
            <%} %>
         </table>
         <h2 style="color: #009879">Prezzo totale</h2>
         <a style="color: #009879; font-weight: bold; font-size: 1.1em;"><%if(cart != null) out.println(cart.getTotal());%> Euro</a>
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