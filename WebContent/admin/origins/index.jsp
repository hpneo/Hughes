<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  @SuppressWarnings("unchecked")
  Collection<Origin> origins = (Vector<Origin>)request.getSession().getAttribute("origins");
  Iterator<Origin> iterator = origins.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Salidas</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Salidas</h2>
      <div class="row">
        <table class="table">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Coordenadas</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <%
              while(iterator.hasNext()){
                Origin origin = iterator.next();
            %>
            <tr>
              <td><%= origin.getName() %></td>
              <td><a href="http://maps.google.com?q=<%= origin.getLat() %>,<%= origin.getLng() %>">Ver en mapa</a></td>
              <td>
                <a href="<%= application.getContextPath() %>/admin/origins?id=<%= origin.getId() %>&action=edit">Editar</a>
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