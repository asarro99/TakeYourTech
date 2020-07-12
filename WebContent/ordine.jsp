<%@ page import="java.util.*,com.model.bean.*,com.model.*"%>
<%
   if(session.getAttribute("roleUtente") == null)
   {
   	response.sendRedirect("login.jsp");	
   	return;
   }
   Cart cart = (Cart) request.getAttribute("ordine");
   String sidemenu = (String)request.getAttribute("sidemenu");
   if(sidemenu == null) {
   	if(request.getParameter("ordineID")!=null)
   	{
   		response.sendRedirect("./Ordine?page=/ordine.jsp&action=show&orderID="+(String)request.getParameter("ordineID"));	
   		return;
   	}
   	else
   	{
   		response.sendRedirect("account.jsp");
   		return;
   	}
   }
   
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
      <div class="qwerty" align="center">
         <table class="tabella">
            <thead>
               <tr>
                  <th>
                     Nome
                  </th>
                  <th>
                     Quantità
                  </th>
                  <th>
                     IVA
                  </th>
                  <th>
                     Prezzo
                  </th>
               </tr>
            </thead>
            <tbody>
               <% List<ProductBean> prodcart = cart.getProducts(); 
                  for(ProductBean beancart: prodcart) {
                  %>
               <tr>
                  <td><%=beancart.getName() %></td>
                  <td><%=beancart.getQuantity() %></td>
                  <td><%=beancart.getIva()%></td>
                  <td><%=beancart.getPrice() %></td>
               </tr>
               <%
                  }
                         %>
            </tbody>
         </table>
         <h2>Prezzo totale</h2>
         <a><%=cart.getTotal() %> Euro</a>
      </div>
      <!---------------------------------FOOTER----------------------------------------->
      <jsp:include page="./utility/footer.jsp"/>
      <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
      <script src="./js/app.js"></script>
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