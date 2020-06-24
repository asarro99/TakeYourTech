<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
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
          <input type="text" placeholder="Email" />
          <input type="password" placeholder="Password" />
          <input type="button" value="Login" />
        </div>
        <div class="register-show">
          <h2>REGISTRATI</h2>
          <input type="text" placeholder="Email" required />
          <input type="password" placeholder="Password" required />
          <input type="password" placeholder="Conferma Password" required />
          <input type="text" placeholder="Nome" />
          <input type="text" placeholder="Cognome" />
          <input type="button" value="Registrati" />
        </div>
      </div>
    </div>
    <!---------------------------------FOOTER----------------------------------------->
    <jsp:include page="./utility/footer.jsp"/>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
   </body>
</html>