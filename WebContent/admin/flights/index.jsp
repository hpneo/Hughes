<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  @SuppressWarnings("unchecked")
  Collection<Flight> flights = (Vector<Flight>)request.getSession().getAttribute("flights");
  Iterator<Flight> iterator = flights.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Vuelos</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Vuelos</h2>
      <div class="row">
        <a href="<%= application.getContextPath() %>/admin/flights?action=new">Nuevo vuelo</a>
        <table class="table">
          <thead>
            <tr>
              <th>Código</th>
              <th>Frecuencia</th>
              <th>Clase</th>
              <th>Asientos disponibles</th>
              <th>Fecha de salida</th>
              <th>Precio</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <%
              while(iterator.hasNext()){
                Flight flight = iterator.next();
            %>
            <tr>
              <td><%= flight.getCode() %></td>
              <td><%= FrequencyBean.getIdentifier(flight.getFrequency()) %></td>
              <td><%= flight.getFlightClass() %></td>
              <td><%= flight.getAvailableSeats() %></td>
              <td><%= DateBean.formatDate(flight.getOutputDate()) %></td>
              <td>S/. <%= flight.getPrice() %></td>
              <td>
                <a href="<%= application.getContextPath() %>/admin/flights?id=<%= flight.getId() %>&action=edit">Editar</a>
                <a href="<%= application.getContextPath() %>/admin/bookings?flight_id=<%= flight.getId() %>">Ver reservas</a>
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