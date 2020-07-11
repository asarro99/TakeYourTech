<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*"%>
<%
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/assemblaggio.jsp");	
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
      <link rel="stylesheet" href="./css/assemblaggio.css" />
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
      
	  <!-- ASSEMBLAGGIO -->
	  <div class="site-inner">
	  	<div class="content-sidebar-wrap">
	  		<main class="content">
	  			<article>
	  				<div class="entry-content">
	  					<div id="config-ajax">
	  						<form method="get">
	  							<div class="coloum-wrap">
	  								<div class="coloum">
	  									<label for="cpubase">CPU</label>
	  									<select name="cpubase" id="cpubase" class="configuratore-base-select">
	  										<optgroup label="Intel">
	  										<option value="123" data-img-src="">Intel Core i5 - 73.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 177.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="AMD">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  									</select>
	  								</div>
	  								  <div class="coloum">
	  									<label for="cpubase">GPU</label>
	  									<select name="cpubase" id="gpubase" class="configuratore-base-select">
	  										<optgroup label="GTX">
	  										<option value="123" data-img-src="">Intel Core i5 - 73.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 177.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="RTX">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="AMD">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  									</select>
	  								</div>
	  							</div>
	  							<div class="coloum-wrap">
	  								<div class="coloum">
	  									<label for="cpubase">CPU</label>
	  									<select name="cpubase" id="cpubase" class="configuratore-base-select">
	  										<optgroup label="Intel">
	  										<option value="123" data-img-src="">Intel Core i5 - 73.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 177.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="AMD">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  									</select>
	  								</div>
	  								  <div class="coloum">
	  									<label for="cpubase">GPU</label>
	  									<select name="cpubase" id="gpubase" class="configuratore-base-select">
	  										<optgroup label="GTX">
	  										<option value="123" data-img-src="">Intel Core i5 - 73.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 177.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="RTX">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="AMD">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  									</select>
	  								</div>
	  							</div>
	  							<div class="coloum-wrap">
	  								<div class="coloum">
	  									<label for="cpubase">CPU</label>
	  									<select name="cpubase" id="cpubase" class="configuratore-base-select">
	  										<optgroup label="Intel">
	  										<option value="123" data-img-src="">Intel Core i5 - 73.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 177.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="AMD">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  									</select>
	  								</div>
	  								  <div class="coloum">
	  									<label for="cpubase">GPU</label>
	  									<select name="cpubase" id="gpubase" class="configuratore-base-select">
	  										<optgroup label="GTX">
	  										<option value="123" data-img-src="">Intel Core i5 - 73.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 177.90</option>
	  										<option value="234" data-img-src="">Intel Core i7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="RTX">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  										<optgroup label="AMD">
	  										<option value="123" data-img-src="">Ryzen 3 - 73.90</option>
	  										<option value="234" data-img-src="">Ryzen 5 - 177.90</option>
	  										<option value="234" data-img-src="">Ryzen 7 - 59.90</option>
	  										</optgroup>
	  									</select>
	  								</div>
	  							</div>
	  							<div class="coloum-wrap">
					<div class="alert-hdd-ssd">
                    <div class="message-hdd-ssd"></div>
                </div>
                <div class="alert-cpu-gpu">
                    <div class="message-cpu-gpu"></div>
                </div>
	  							</div>
	  							            <div class="search-button-builds">
                <input class="configbasebutton" type="submit" id="submit" name="submit" value="Crea Configurazione">
            </div>
	  						</form>
	  					</div>
	  				</div>
	  			</article>
	  		</main>
	  	</div>
	  </div>
      
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