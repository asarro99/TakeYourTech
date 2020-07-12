<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<div class="side-menu" id="side-menu">
   <ul>
      <%	
         String menu = (String)request.getParameter("categorie");
         String [] cate = menu.split(" ");
         for(int i=0;i<cate.length;i++)
         {	
         	String c = cate[i].replace("_", " ");
         	out.println("<li><a href=\"Product?page=/articoli.jsp&categoria="+cate[i]+"\">"+c+"<i class=\"fa fa-angle-right\"></i></a>");
         }
         %>
   </ul>
</div>