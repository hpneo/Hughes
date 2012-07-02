<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@page import="com.hughes.servlets.*"%>
<%
  User current_user = null;
  if(request.getCookies() != null){
	  String current_user_id = ServletUtilities.getCookieValue(request.getCookies(), "current_user");
	  if(current_user_id != null){
	    current_user = UserBean.get(Integer.parseInt(current_user_id));
	  }
  }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
  <div class="wrapper">
	  <h1>
	    <a href="/Flights">Hughes</a>
	  </h1>
	  <% if(current_user != null) { %>
	  <ul>
      <li><a href="<%= application.getContextPath() %>/panel/bookings">Mis reservas</a></li>
      <li><a href="<%= application.getContextPath() %>/panel/edit">Actualizar datos personales</a></li>
	  </ul>
	  <% } %>
  </div>
</div>