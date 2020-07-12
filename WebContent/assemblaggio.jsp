<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.bean.*"%>
<%
   Collection<?> products = (Collection<?>) request.getSession().getAttribute("configuratore");
   String sidemenu = (String)request.getAttribute("sidemenu");
   if(sidemenu == null) {
   	response.sendRedirect("./Product?page=/assemblaggio.jsp");	
   	return;
   }
   
   if(products == null){
    response.sendRedirect("Assemblaggio");	
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
<form method="post" action="Assemblaggio">
<div class="coloum-wrap">
<div class="coloum">
<label for="cpubase">CPU</label>
<select name="cpubase" id="cpubase" class="configuratore-base-select">
<optgroup label="CPU">
<%
   if (products != null && products.size() != 0) {
   	Iterator<?> it = products.iterator();
      while (it.hasNext()) {
      	ProductBean bean = (ProductBean) it.next();
      	if(bean.getCategoria().equals("Processori")){
      		out.println("<option value=\"" + bean.getCode() + "\"" + ">" + bean.getName() + "</option>");
      	}
      }
      }
      %>
</optgroup>
</select>
</div>
<div class="coloum">
<label for="gpubase">GPU</label>
<select name="gpubase" id="gpubase" class="configuratore-base-select">
<optgroup label="GPU">
<%
   if (products != null && products.size() != 0) {
   	Iterator<?> it = products.iterator();
      while (it.hasNext()) {
      	ProductBean bean = (ProductBean) it.next();
      	if(bean.getCategoria().equals("Schede_Video")){
      		out.println("<option value=\"" + bean.getCode() + "\"" + ">" + bean.getName() + "</option>");
      	}
      }
      }
      %>
</optgroup>
</select>
</div>
</div>
<div class="coloum-wrap">
<div class="coloum">
<label for="hddbase">STORAGE</label>
<select name="hddbase" id="hddbase" class="configuratore-base-select">
<optgroup label="Storage">
<%
   if (products != null && products.size() != 0) {
   	Iterator<?> it = products.iterator();
      while (it.hasNext()) {
      	ProductBean bean = (ProductBean) it.next();
      	if(bean.getCategoria().equals("Hard_Disk")){
      		out.println("<option value=\"" + bean.getCode() + "\"" + ">" + bean.getName() + "</option>");
      	}
      }
      }
      %>
</optgroup>
</select>
</div>
<div class="coloum">
<label for="alibase">ALIMENTATORE</label>
<select name="alibase" id="alibase" class="configuratore-base-select">
<optgroup label="Alimentatore">
<%
   if (products != null && products.size() != 0) {
   	Iterator<?> it = products.iterator();
      while (it.hasNext()) {
      	ProductBean bean = (ProductBean) it.next();
      	if(bean.getCategoria().equals("Alimentatori")){
      		out.println("<option value=\"" + bean.getCode() + "\"" + ">" + bean.getName() + "</option>");
      	}
      }
      }
      %>
</optgroup>
</select>
</div>
</div>
<div class="coloum-wrap">
<div class="coloum">
<label for="rambase">RAM</label>
<select name="rambase" id="rambase" class="configuratore-base-select">
<optgroup label="RAM">
<%
   if (products != null && products.size() != 0) {
   	Iterator<?> it = products.iterator();
      while (it.hasNext()) {
      	ProductBean bean = (ProductBean) it.next();
      	if(bean.getCategoria().equals("RAM")){
      		out.println("<option value=\"" + bean.getCode() + "\"" + ">" + bean.getName() + "</option>");
      	}
      }
      }
      %>
</optgroup>
</select>
</div>
<div class="coloum">
<label for="casebase">CASE</label>
<select name="casebase" id="casebase" class="configuratore-base-select">
<optgroup label="CASE">
<%
   if (products != null && products.size() != 0) {
   	Iterator<?> it = products.iterator();
   	while (it.hasNext()) {
   		ProductBean bean = (ProductBean) it.next();
   		if(bean.getCategoria().equals("Case")){
   			out.println("<option value=\"" + bean.getCode() + "\"" + ">" + bean.getName() + "</option>");
   		}
   	}
   }
   %>
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
   <input class="configbasebutton" type="submit" id="submit" name="submit" value="Assemblaggio">
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="./js/app.js"></script>
<script>
   $('#cpubase2').change(()=>{
   	console.log($('#cpubase2 option:selected').val());
   })
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