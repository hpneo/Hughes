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
  <title>Hughes - Compra de pasajes</title>
  <link rel="stylesheet" type="text/css" href="styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="styles/bookings.css" />
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <h2>Compra de pasajes</h2>
      <form action="bookings" method="post">
        <%
          while(iterator.hasNext()){
            Flight flight = (Flight)iterator.next();
        %>
        <div class="row bordered">
          <h3>Vuelo <%= flight.getCode() %></h3>
          <h4>Origen: <%= flight.getFrequency().getRoute().getOrigin().getName() %> - <%= FlightBean.getOuputDate(flight) %></h4>
          <h4>Destino: <%= flight.getFrequency().getRoute().getDestination().getName() %> - <%= FlightBean.getArrivalDate(flight) %></h4>
          <p>
            <label for="flight_passenger_name_<%= flight.getId() %>">Pasajero:</label>
            <input type="text" name="flight_passenger_names[]" id="flight_passenger_name_<%= flight.getId() %>" size="50" />
            <input type="hidden" name="flight_ids[]" value="<%= flight.getId() %>" />
          </p>
        </div>
        <% } %>
        <p>
          <input type="submit" value="Comprar pasajes" />
        </p>
      </form>
    </div>
  </div>
</body>
</html>