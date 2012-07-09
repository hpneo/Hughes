<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  Route route = (Route)request.getSession().getAttribute("route");
  Collection<Origin> origins = OriginBean.all();
  Collection<Destination> destinations = DestinationBean.all();

  Iterator<Origin> originIterator = origins.iterator();
  Iterator<Destination> destinationIterator = destinations.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Editar ruta</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Editar ruta</h2>
      <div class="row">
        <form method="post">
          <p>
            <label for="origin">Salida</label>
            <select name="origin" id="origin">
              <%
                while(originIterator.hasNext()) {
                  Origin origin = originIterator.next();
              %>
	              <% if(origin.getId() == route.getOrigin().getId()){ %>
	                <option value="<%= origin.getId() %>" selected="selected"><%= origin.getName() %></option>
	              <% } else { %>
	                <option value="<%= origin.getId() %>"><%= origin.getName() %></option>
	              <% } %>
              <% } %>
            </select>
          </p>
          <p>
            <label for="destination">Destino</label>
            <select name="destination" id="destination">
              <%
                while(destinationIterator.hasNext()) {
                  Destination destination = destinationIterator.next();
              %>
                <% if(destination.getId() == route.getDestination().getId()){ %>
                  <option value="<%= destination.getId() %>" selected="selected"><%= destination.getName() %></option>
                <% } else { %>
                  <option value="<%= destination.getId() %>"><%= destination.getName() %></option>
                <% } %>
              <% } %>
            </select>
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