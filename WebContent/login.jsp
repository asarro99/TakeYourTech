<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
   <%
   boolean login = false;
   if(request.getSession().getAttribute("erroreLogin") != null){
	    login = (boolean) request.getSession().getAttribute("erroreLogin");
   }
	
%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>TakeYourTech</title>
      <link rel="stylesheet" href="./css/style.css" />
      <link rel="stylesheet" href="./css/login.css" />
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
     <!--------------------------------LOGIN----------------------------------------->
     <div class="login-reg-panel">
      <div class="login-info-box">
        <h2>Hai già un account</h2>
        <label id="label-register" for="log-reg-show">Login</label>
        <input
          type="radio"
          name="active-log-panel"
          id="log-reg-show"
          checked="checked"
        />
      </div>

      <div class="register-info-box">
        <h2>Non hai un account?</h2>
        <label id="label-login" for="log-login-show">Registrati</label>
        <input type="radio" name="active-log-panel" id="log-login-show" />
      </div>
		
      <div class="white-panel">
        <div class="login-show">
          <h2>LOGIN</h2>
          <form id="log-form" method="post" action="Login">
          	  <div class="form-controller">
	          <input id="log-email" type="text" placeholder="Email" name = "email"/>
	          <div class="div-non-visualize">
	          <small></small>
	          </div>
	          </div>
	          <div class="form-controller">
	          <input id="log-password" type="password" placeholder="Password" name="password"/>
	          <div class="div-non-visualize">
	           <small></small>
	          </div>
	          </div>
	          <input type="submit" value="Login" id="loginBtn"/>
          </form>
          <% if(login)out.print("<p>Credenziali errate!</p>"); %>
        </div>
        <div class="register-show">
          <h2>REGISTRATI</h2>
          <form id="reg-form" method="post" action="SignIn">
          	  <div class="form-controller">
          	  	  <input id="reg-email" type="text" placeholder="Email" name = "email" />
          	  	  <div class="div-non-visualize">
          	  	  <small></small>
          	  	  </div>
          	  </div>
          	  <div class="form-controller">
	          	  <input id="reg-pass" type="password" placeholder="Password" name = "password"/>
	          	  <div class="div-non-visualize">
          	  	  <small></small>
          	  	  </div>
	          </div>
	          <div class="form-controller">
	          <input id="reg-pass2" type="password" placeholder="Conferma Password"/>
	          <div class="div-non-visualize">
          	  	  <small></small>
          	  	  </div>
	          </div>
	          <div class="form-controller">
	          <input id="reg-nome" type="text" placeholder="Nome" name = "nome"/>
	          <div class="div-non-visualize">
          	  	  <small></small>
          	  	  </div>
	          </div>
	          <div class="form-controller">
	          <input id="reg-cognome" type="text" placeholder="Cognome" name = "cognome"/>
	          <div class="div-non-visualize">
          	  	  <small></small>
          	  	  </div>
	          </div>
	          <input type="submit" value="Registrati" />
          </form>
        </div>
      </div>
    </div>
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="./js/login.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    
    <script src="./js/app.js"></script>
    <script>
    $(document).ready(function () {
        $(".login-info-box").fadeOut();
        $(".login-show").addClass("show-log-panel");
      });

      $('.login-reg-panel input[type="radio"]').on("change", function () {
        if ($("#log-login-show").is(":checked")) {
          $(".register-info-box").fadeOut();
          $(".login-info-box").fadeIn();

          $(".white-panel").addClass("right-log");
          $(".register-show").addClass("show-log-panel");
          $(".login-show").removeClass("show-log-panel");
        } else if ($("#log-reg-show").is(":checked")) {
          $(".register-info-box").fadeIn();
          $(".login-info-box").fadeOut();

          $(".white-panel").removeClass("right-log");

          $(".login-show").addClass("show-log-panel");
          $(".register-show").removeClass("show-log-panel");
        }
      });
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