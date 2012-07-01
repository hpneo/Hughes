<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Hughes - Iniciar sesión</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/sign_in.css" />
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <% if(request.getSession().getAttribute("sign_in_error") != null) { %>
      <p class="error">
        <%= request.getSession().getAttribute("sign_in_error").toString() %>
      </p>
      <% } %>
      <form action="<%= application.getContextPath() %>/users/sign_in" method="post">
        <p>
          <label name="email">Correo electrónico:</label>
          <input type="email" name="email" id="email" />
        </p>
        <p>
          <label name="password">Contraseña:</label>
          <input type="password" name="password" id="password" />
        </p>
        <p>
          <input type="submit" value="Iniciar sesión" />
        </p>
      </form>
    </div>
  </div>
</body>
</html>