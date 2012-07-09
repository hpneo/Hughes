<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  @SuppressWarnings("unchecked")
  Collection<Route> routes = (Vector<Route>)request.getSession().getAttribute("routes");
  Iterator<Route> iterator = routes.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Rutas</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Rutas</h2>
      <div class="row">
        <a href="<%= application.getContextPath() %>/admin/routes?action=new">Nueva ruta</a>
        <table class="table">
          <thead>
            <tr>
              <th>Salida</th>
              <th>Destino</th>
              <th>Número de frecuencias</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <%
              while(iterator.hasNext()){
                Route route = iterator.next();
            %>
            <tr>
              <td><%= route.getOrigin().getName() %></td>
              <td><%= route.getDestination().getName() %></td>
              <td><%= route.getFrequencies().size() %></td>
              <td>
                <a href="<%= application.getContextPath() %>/admin/routes?id=<%= route.getId() %>&action=edit">Editar</a>
                <a href="<%= application.getContextPath() %>/admin/frequencies?route_id=<%= route.getId() %>">Ver frecuencias</a>
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