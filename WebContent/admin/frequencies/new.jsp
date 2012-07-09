<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Collection<Route> routes = RouteBean.all();
	Iterator<Route> routeIterator = routes.iterator();
	
	Collection<Aircraft> aircrafts = AircraftBean.all();
	Iterator<Aircraft> aircraftIterator = aircrafts.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Nueva frecuencia</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Nueva frecuencia</h2>
      <div class="row">
        <form method="post">
          <p>
            <label for="route">Ruta</label>
            <select name="route" id="route">
              <%
                while(routeIterator.hasNext()) {
                  Route route = routeIterator.next();
              %>
                <option value="<%= route.getId() %>"><%= route.getOrigin().getName() %> - <%= route.getDestination().getName() %></option>
              <% } %>
            </select>
          </p>
          <p>
            <label for="aircraft">Avión</label>
            <select name="aircraft" id="aircraft">
              <%
                while(aircraftIterator.hasNext()) {
                  Aircraft aircraft = aircraftIterator.next();
              %>
                <option value="<%= aircraft.getId() %>"><%= aircraft.getModel() %> (<%= aircraft.getCapacity() %>)</option>
              <% } %>
            </select>
          </p>
          <p>
            <label for="duration">Duración</label>
            <input type="text" name="duration" id="duration" />
          </p>
          <p>
            <label for="output">Horario de salida</label>
            <input type="text" name="output" id="output" />
          </p>
          <p>
            <label for="arrival">Horario de llegada</label>
            <input type="text" name="arrival" id="arrival" />
          </p>
          <p>
            <input type="submit" value="Crear" />
          </p>
        </form>
      </div>
    </div>
  </div>
</body>
</html>