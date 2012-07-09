<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  @SuppressWarnings("unchecked")
  Collection<Frequency> frequencies = (Vector<Frequency>)request.getSession().getAttribute("frequencies");
  Iterator<Frequency> iterator = frequencies.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Frecuencias</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Frecuencias</h2>
      <div class="row">
        <a href="<%= application.getContextPath() %>/admin/frequencies?action=new">Nueva frecuencia</a>
        <table class="table">
          <thead>
            <tr>
              <th>Avión</th>
              <th>Duración</th>
              <th>Hora de salida</th>
              <th>Hora de llegada</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <%
              while(iterator.hasNext()){
                Frequency frequency = iterator.next();
            %>
            <tr>
              <td><%= frequency.getAircraft().getModel() %></td>
              <td><%= frequency.getDuration() %></td>
              <td><%= DateBean.formatTime(frequency.getOutput()) %></td>
              <td><%= DateBean.formatTime(frequency.getArrival()) %></td>
              <td>
                <a href="<%= application.getContextPath() %>/admin/frequencies?id=<%= frequency.getId() %>&action=edit">Editar</a>
                <a href="<%= application.getContextPath() %>/admin/flights?frequency_id=<%= frequency.getId() %>">Ver vuelos</a>
              </td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>
</html>