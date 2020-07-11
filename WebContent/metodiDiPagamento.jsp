<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.*,com.Bean.*"%>
<%
	if(session.getAttribute("roleUtente") == null)
	{
		response.sendRedirect("login.jsp");	
		return;
	}
	Collection<?> metodPagamento = (Collection<?>) request.getAttribute("metPagamento");
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/metodiDiPagamento.jsp&action=metPagamento");	
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
      <link rel="stylesheet" href="./css/metodiDiPagamento.css" />
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
        <h2>Pagamento</h2>
      </div>
    </section>

    <section class="indirizzi">
      <table class="rwd-table">
        <tr>
          <th>ID</th>
          <th>Codice carta</th>
          <th>Intestatario</th>
          <th>Tipologia</th>
          <th>Data di scadenza</th>
          <th>Rimuovi</th>
        </tr>
        <%
			if (metodPagamento != null && metodPagamento.size() != 0) {
				Iterator<?> it = metodPagamento.iterator();
				while (it.hasNext()) {
					metPagaBean bean = (metPagaBean) it.next();
		%>
        <tr>
          <td data-th="ID"><%=bean.getCode() %></td>
          <td data-th="Codice carta">
            <%=bean.getCodiceCarta()%>
          </td>
          <td data-th="Intestatario"><%=bean.getIntestatario()%></td>
          <td data-th="Tipologia"><%=bean.getTipologia() %></td>
          <td data-th="Data di scadenza">
            <%=bean.getDataDiScadenza() %>
          </td>
          <td data-th="Dettagli">
          <a href="./Product?page=/metodiDiPagamento.jsp&action=metPagamento&type=rimuovi&idind=<%=bean.getCode()%>">
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
			<form id="form-inserimento" action="./Product?page=/metodiDiPagamento.jsp&action=metPagamento&type=ins" enctype="multipart/form-data" method="post">
				<h2>Inserimento</h2>
				<div class="form-controller">
				<input id="codice-inserimento" name="codiceCarta" type="text" placeholder="Inserisci codice carta">
												 <div class="div-non-visualize">
	          <small></small>
	          </div>
	          </div>
				<div class="form-controller">
				<input id="intestatario-inserimento" name="intestatario" type="text" placeholder="Inserisci intestatario">
												 <div class="div-non-visualize">
	          <small></small>
	          </div>
	          </div>
				<div class="form-controller">
				<input id="tipologia-inserimento" name="tipologia" type="text" placeholder="Inserisci tipologia">
												 <div class="div-non-visualize">
	          <small></small>
	          </div>
	          </div>
				<div class="form-controller">
				<input id="scadenza-inserimento" name="scadenza" type="text" placeholder="Inserisci scadenza">
												 <div class="div-non-visualize">
	          <small></small>
	          </div>
	          </div>
				
				<input type="submit" value="Aggiungi pagamento">
			</form>
		</div>
	</div>
    
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    
    <script src="./js/pagamenti.js"></script>
    
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