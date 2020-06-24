<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<div class="top-nav-bar">
   <div class="search-box">
        <i class="fa fa-bars" id="menu-btn" onclick="openMenu()"></i>
        <i class="fa fa-times" id="close-btn" onclick="closeMenu()"></i>
      <img src="./img/logo.png" class="logo" />
      <input type="text" class="form-control" />
      <span class="input-group-text"><i class="fa fa-search"></i></span>
   </div>
   <div class="menu-bar">
      <ul>
         <li><a href="#">Assemblaggio</a></li>
         <li>
            <a href="#">Carrello</a>
         </li>
         <li><a href="#">Account</a></li>
      </ul>
   </div>
</div>