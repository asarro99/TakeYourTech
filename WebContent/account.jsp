<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
   if(session.getAttribute("roleUtente") == null)
   {
   	response.sendRedirect("login.jsp");	
   	return;
   }
   String sidemenu = (String)request.getAttribute("sidemenu");
   if(sidemenu == null) {
   	response.sendRedirect("./Product?page=/account.jsp");	
   	return;
   }
   %>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/account.css" />
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
      <!-- ACCOUNT -->
      <section class="body_account">
         <div class="boxProfile">
            <div class="propic">
               <img src="./img/svg/utente.svg" alt="" />
            </div>
            <div class="bottoni">
               <div class="indirizzo">
                  <a href="indirizzi.jsp">
                  <button type="button" class="btn btn-primary">
                  Indirizzi
                  </button>
                  </a>
               </div>
               <div class="pagamento">
                  <a href="metodiDiPagamento.jsp">
                  <button type="button" class="btn btn-primary">
                  Metodi di Pagamento
                  </button>
                  </a>
               </div>
               <div class="ordini">
                  <a href="mieiOrdini.jsp">
                  <button type="button" class="btn btn-primary">
                  Ordini
                  </button>
                  </a>
               </div>
            </div>
         </div>
         <div id="profilo" class="profilo">
         </div>
      </section>
      <jsp:include page="./utility/footer.jsp"/>
      <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
      <script src="./js/app.js"></script>
      <script src="./js/account.js"></script>
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