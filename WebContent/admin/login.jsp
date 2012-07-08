<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Entrar</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/sign_in.css" />
</head>
<body>
  <div id="body">
    <div class="wrapper">
      <form method="POST" action="j_security_check">
        <p>
          <label for="username">Username:</label>
          <input type="text" name="j_username" id="username" />
        </p>
        <p>
          <label for="password">Contraseña:</label>
          <input type="password" name="j_password" id="password" />
        </p>
        <p>
          <input type="submit" value="Entrar" />
        </p>
		  </form>
    </div>
  </div>
</body>
</html>