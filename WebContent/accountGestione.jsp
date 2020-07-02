<%

	if(session.getAttribute("roleUtente")!=null)
	{
		String role = (String)session.getAttribute("roleUtente");
        if(role.equals("utente")){
			response.sendRedirect("./login.jsp");
			return;
        }
	}
	else
	{
		response.sendRedirect("./login.jsp");
		return;
	}

	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/accountGestione.jsp");	
		return;
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/accountGestione.css" />
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
	<div class= "form-container">
		<div class= "form-wrapper">
			<form action="Product?action=insert" enctype="multipart/form-data" method="post">
				<h2>Inserimento</h2>
				<input name="nome" type="text" placeholder="Inserisci nome prodotto">
				<input name="categoria" type="text" placeholder="Inserisci categoria prodotto">
				<input name="iva" type="text" placeholder="Inserisci IVA prodotto">
				<input name="descrizione" type="text" placeholder="Inserisci descrizione prodotto">
				<input name="prezzo" type="text" placeholder="Inserisci prezzo prodotto">
				<input name="quantita" type="text" placeholder="Inserisci quantita prodotto">
				<input class="file" type="file" name="talkPhoto" value="" maxlength="255">	
				<input type="submit">
			</form>
		</div>
		<div class= "form-wrapper">
			<form action="Product?action=modifica" method="post">
				<h2>Modifica</h2>
				<input name="codice" type="text" placeholder="Inserisci codice prodotto">
				<input name="categoria" type="text" placeholder="Inserisci categoria prodotto">
				<input name="nome" type="text" placeholder="Inserisci nome prodotto">
				<input name="descrizione" type="text" placeholder="Inserisci descrizione prodotto">
				<input name="prezzo" type="text" placeholder="Inserisci prezzo prodotto">
				<input name="quantita" type="text" placeholder="Inserisci quantita prodotto">
				<input type="submit">
			</form>
		</div>
		<div class= "form-wrapper">
			<form action="Product?action=rimozione" method="post">
				<h2>Rimozione</h2>
				<input name="codice" type="text" placeholder="Inserisci codice prodotto">
				<input type="submit">
			</form>
		</div>
		
	</div>
	
	<section>
	<button type="button" class="btn btn-primary">
              Ordini
            </button>
	</section>
      
      <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
   </body>
</html>