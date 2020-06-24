<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/contatti.css" />
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
	
	<!---------------------------------CONTATTI----------------------------------------->
    <section class="contatti">
      <div class="title-box">
        <h2>Contatti</h2>
      </div>
      <div class="contacts_box">
        <div class="social">
          <i class="fa fa-facebook" id="_facebook" aria-hidden="true"></i>
          <i class="fa fa-twitter" id="_twitter" aria-hidden="true"></i>
          <i class="fa fa-instagram" id="_instagram" aria-hidden="true"></i>
        </div>
        <div class="phone">
          <h4>Telefono:</h4>
          <p>Numero telefono</p>
        </div>
        <div class="email">
          <h4>Email:</h4>
          <a
            href="mailto:indirizzo1@email.com,indirizzo3@email.com,indirizzo3@email.com"
            >Scrivi una mail</a
          >
        </div>
      </div>
    </section>
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
   </body>
</html>