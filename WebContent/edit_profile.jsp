<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  User current_user = (User)request.getSession().getAttribute("current_user");
  Object user_profile_message = request.getSession().getAttribute("user_profile_message");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Hughes - Editar perfil</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/bookings.css" />
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <h2>Editar mi perfil</h2>
      <div class="row">
        <% if(user_profile_message != null){ %>
          <p><%= user_profile_message.toString() %></p>
        <% } %>
        <form action="<%= application.getContextPath() %>/panel/edit" method="post">
          <p>
            <label for="first_name">Nombres:</label>
            <input type="text" name="first_name" id="first_name" value="<%= current_user.getFirstName() %>" />
          </p>
          <p>
            <label for="last_name">Apellidos:</label>
            <input type="text" name="last_name" id="last_name" value="<%= current_user.getLastName() %>" />
          </p>
          <p>
            <label for="dni">DNI:</label>
            <input type="text" name="dni" id="dni" value="<%= current_user.getDni() %>" />
          </p>
          <p>
            <input type="submit" value="Editar perfil" />
          </p>
        </form>
      </div>
    </div>
  </div>
</body>
</html>