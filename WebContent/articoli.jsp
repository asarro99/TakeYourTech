<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.model.bean.ProductBean"%>
 <%
 	
 	Collection<?> products = (Collection<?>)request.getAttribute("prodotti");
 
	String sidemenu = (String)request.getAttribute("sidemenu");
	if(sidemenu == null) {
		response.sendRedirect("./Product?page=/articoli.jsp&categoria=Alimentatori");	
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
      <link rel="stylesheet" href="./css/articoli.css" />
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
      
      <!-- ARTICOLI -->
	<section class="new-products">
      <div class="container">
        <div class="title-box">
          <h2 id="cat"></h2>
        </div>
        <div class="row">
        <%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
			
			<div class="col-md-3">
            <div class="product-top">
              <img src="./getPicture?id=<%=bean.getCode() %>"  />
              <div class="overlay-right">
              <a href="Product?page=/product.jsp&codiceprod=<%=bean.getCode()%>">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                </a>
                <a href="./Product?page=/carrello.jsp&codiceprod=<%= bean.getCode()%>&action=addC&quantita=1">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
                </a>
              </div>
            </div>
            <div class="product-bottom text-center">
               <%
                Random rand = new Random();
				int valore = rand.nextInt(4) + 1;
              	for(int i=0; i<valore; i++){
              		out.println("<i class=\"fa fa-star\"></i>");
              	}
              	int valore2 = rand.nextInt(2);
              	for(int j=0; j<valore2; j++){
              		out.println("<i class=\"fa fa-star-half-o\"></i>");
              	}
              %>
              <h3><%=bean.getName() %></h3>
              <h5><%=bean.getPrice() %> Euro</h5>
            </div>
          </div>
			
		<%
				}
			}
		%>
          
        </div>
      </div>
    </section>
      
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
       
     <script>
     let searchParams = new URLSearchParams(window.location.search);
     if(searchParams.has('categoria')){
     	let productParam = searchParams.get('categoria');
     	document.getElementById('cat').innerHTML = productParam;
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