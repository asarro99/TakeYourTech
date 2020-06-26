<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.Bean.ProductBean"%>
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
          <h2>Articoli</h2>
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
              <a href="Product?page=/product.jsp&codiceprod=<%=bean.getCode() %>">
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Dettagli"
                >
                  <i class="fa fa-eye"></i>
                </button>
                </a>
                <button
                  type="button"
                  class="btn btn-secondary"
                  title="Aggiungi al carrello"
                >
                  <i class="fa fa-shopping-basket"></i>
                </button>
              </div>
            </div>
            <div class="product-bottom text-center">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star-half-o"></i>
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
       
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
   </body>
</html>