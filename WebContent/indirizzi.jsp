<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.model.bean.*"%>
<%
   if(session.getAttribute("roleUtente") == null)
   {
   	response.sendRedirect("login.jsp");	
   	return;
   }
   Collection<?> indirizzi = (Collection<?>) request.getAttribute("indirizzi");
   String sidemenu = (String)request.getAttribute("sidemenu");
   if(sidemenu == null) {
   	response.sendRedirect("./Product?page=/indirizzi.jsp&action=indirizzi");	
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
<link rel="stylesheet" href="./css/indirizzi.css" />
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
<!---------------------------------Indirizzi----------------------------------------->
<section class="title">
<div class="title-box">
<h2>Indirizzi</h2>
</div>
</section>
<section class="indirizzi">
<table class="rwd-table">
<tr>
<th>ID</th>
<th>Indirizzo</th>
<th>Città</th>
<th>CAP</th>
<th>Rimuovi</th>
</tr>
<%
   if (indirizzi != null && indirizzi.size() != 0) {
   	Iterator<?> it = indirizzi.iterator();
   	while (it.hasNext()) {
   		IndirizziBean bean = (IndirizziBean) it.next();
   %>
<tr>
   <td data-th="ID"><%=bean.getCode() %></td>
   <td data-th="Indirizzo">
      <%=bean.getVia()%>
   </td>
   <td data-th="Città"><%=bean.getCitta()%></td>
   <td data-th="CAP"><%=bean.getCodicePostale() %></td>
   <td data-th="Rimuovi">
      <a href="./Product?page=/indirizzi.jsp&action=indirizzi&type=rimuovi&idind=<%=bean.getCode()%>">
      <button type="button" class="btn btn-primary">
      Rimuovi
      </button>
      </a>
   </td>
</tr>
<%
   }
   } else {
   %>
<tr>
   <td colspan="6">Nessun indirizzo disponibile</td>
</tr>
<%
   }
   %>
</table>
</section>
<div class= "form-container">
   <div class= "form-wrapper">
      <form id="form-inserimento" action="./Product?page=/indirizzi.jsp&action=indirizzi&type=ins" enctype="multipart/form-data" method="post">
         <h2>Inserimento</h2>
         <div class="form-controller">
            <input id="via-inserimento" name="via" type="text" placeholder="Inserisci la via">
            <div class="div-non-visualize">
               <small></small>
            </div>
         </div>
         <div class="form-controller">
            <input id="citta-inserimento" name="citta" type="text" placeholder="Inserisci la citta">
            <div class="div-non-visualize">
               <small></small>
            </div>
         </div>
         <div class="form-controller">
            <input id="cap-inserimento" name="CAP" type="text" placeholder="Inserisci CAP">
            <div class="div-non-visualize">
               <small></small>
            </div>
         </div>
         <input type="submit" value="Aggiungi indirizzo">
      </form>
   </div>
</div>
<!---------------------------------FOOTER----------------------------------------->
<jsp:include page="./utility/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="./js/app.js"></script>
<script src="./js/indirizzi.js"></script>
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