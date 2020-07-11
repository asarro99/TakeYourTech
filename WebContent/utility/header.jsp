<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<div class="top-nav-bar">
   <div class="search-box">
        <i class="fa fa-bars" id="menu-btn" onclick="openMenu()"></i>
        <i class="fa fa-times" id="close-btn" onclick="closeMenu()"></i>
      <a href="index.jsp">
      <img src="./img/logo.png" class="logo" />
      </a>
      <input id="inputRicerca" type="text" class="form-control" />
      <span id = "inputBottone" class="input-group-text"><i class="fa fa-search"></i></span>
   </div>
   <div class="menu-bar">
      <ul>
        <%
		if(session.getAttribute("roleUtente")==null){
			out.println("<li><a href=" + "assemblaggio.jsp" + ">Assemblaggio</a></li>");
			out.println("<li><a href=" + "carrello.jsp" + ">Carrello</a></li>");
			out.println("<li><a href=" + "login.jsp" + ">Login</a></li>");
		}else if(session.getAttribute("roleUtente")!=null && session.getAttribute("roleUtente").equals("utente")){
			out.println("<li><a href=" + "assemblaggio.jsp" + ">Assemblaggio</a></li>");
			out.println("<li><a href=" + "carrello.jsp" + ">Carrello</a></li>");
			out.println("<li><a href=" + "account.jsp" + ">Account</a></li>");
			out.println("<li><a href=" + "Logout" + ">Logout</a></li>");
		}else if(session.getAttribute("roleUtente")!=null && session.getAttribute("roleUtente").equals("admin")){
			out.println("<li><a href=" + "accountGestione.jsp" + ">Gestione</a></li>");
			out.println("<li><a href=" + "Logout" + ">Logout</a></li>");
		}
		%>
      </ul>
   </div>
</div>