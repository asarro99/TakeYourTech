<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.*,com.Bean.*"%>
<%
	if(session.getAttribute("roleUtente") == null)
	{
		response.sendRedirect("login.jsp");	
		return;
	}
	Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/OrdiniAdmin.jsp&action=ordiniAdmin");	
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
      <link rel="stylesheet" href="./css/mieiOrdini.css" />
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
	
	    <!---------------------------------MIEI ORDINI----------------------------------------->
    <section class="title">
      <div class="title-box">
        <h2>Ordini</h2>
      </div>
    </section>
    <section class="ordini">
	    <div class="ricerca">
	    	<h1>Ricerca</h1>
		<form action="./Product?page=/OrdiniAdmin.jsp&action=ordiniAdmin" method="post">
			<h3>Dalla data: </h3> <input type="date" name="data1" value="2017-06-01">
			<h3>alla data: </h3> <input type="date" name="data2" value="2017-06-01">
			<h3>Id utente</h3> 
			 <input type="text" name="idUtenteRic">
			<br>
			<div class="bottone">
			<input type="submit" value="Ricerca" class="btn btn-primary">
			</div>
		</form>
		</div>
		
		
		<div class="boxtabella">
			<table class="rwd-table">
	               <tr>
	               	  <th>Codice Utente</th>
		              <th>Codice Ordine</th>
			          <th>Data</th>
			          <th>Prezzo</th>
			      	  <th>Fattura</th>
			          <th>Dettagli</th>
	               </tr>
	                <%
					if (ordini != null && ordini.size() != 0) {
						Iterator<?> it = ordini.iterator();
						while (it.hasNext()) {
							OrdiniBean bean = (OrdiniBean) it.next();
				%>
		        <tr>
		          <td data-th="Codice">
		            <%=bean.getIdUtente()%>
		          </td>
		          <td data-th="Codice">
		            <%=bean.getCode() %>
		          </td>
		          <td data-th="Data">
		            <%=bean.getData() %>
		          </td>
		          <td data-th="Prezzo"><%=bean.getTotale() %></td>
		          <td data-th="Fattura">
		          <a href="Fattura?ordineID=<%= bean.getCode()%>">
		          	 <button type="button" class="btn btn-primary">
		              Fattura
		            </button>
		          </a>
		          </td>
		          <td data-th="Dettagli">
		          <a href="./ordine.jsp?ordineID=<%= bean.getCode()%>">
		          	 <button type="button" class="btn btn-primary">
		              Dettagli
		            </button>
		          </a>
		          </td>
		        </tr>
		        <%
						}
					} else {
				%>
				<tr>
					<td colspan="6">Nessun ordine disponibile</td>
				</tr>
				<%
					}
				%>
	         </table>
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