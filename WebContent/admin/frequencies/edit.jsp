<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  Frequency frequency = (Frequency)request.getSession().getAttribute("frequency");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Editar frecuencia</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Editar frecuencia</h2>
      <div class="row">
        <form method="post">
          <p>
            <label for="duration">Duración</label>
            <input type="text" name="duration" id="duration" value="<%= frequency.getDuration() %>" />
          </p>
          <p>
            <label for="output">Horario de salida</label>
            <input type="text" name="output" id="output" value="<%= DateBean.formatTime(frequency.getOutput()) %>" />
          </p>
          <p>
            <label for="arrival">Horario de llegada</label>
            <input type="text" name="arrival" id="arrival" value="<%= DateBean.formatTime(frequency.getArrival()) %>" />
          </p>
          <p>
            <input type="submit" value="Editar" />
          </p>
        </form>
      </div>
    </div>
  </div>
</body>
</html>