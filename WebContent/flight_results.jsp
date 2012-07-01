<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  @SuppressWarnings("unchecked")
  Collection<Flight> flights = (Vector<Flight>)request.getSession().getAttribute("flight_results");
  Iterator<Flight> iterator = flights.iterator();

  Origin origin = (Origin)request.getSession().getAttribute("search_origin");
  Destination destination = (Destination)request.getSession().getAttribute("search_destination");

  Integer adult_passages_count = Integer.parseInt(request.getSession().getAttribute("adult_passages_count").toString());
  Integer child_passages_count = Integer.parseInt(request.getSession().getAttribute("child_passages_count").toString());
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Hughes - Resultados de búsqueda</title>
  <link rel="stylesheet" type="text/css" href="styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="styles/flight_results.css" />
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <h2>Resultados de búsqueda</h2>
      <div class="row">
        <dl>
          <dt>Salida:</dt>
          <dd><%= origin.getName() %></dd>
          <dt>Destino:</dt>
          <dd><%= destination.getName() %></dd>
          <dt>Número de pasajes:</dt>
          <dd>
            <%= adult_passages_count %> adultos y <%= child_passages_count %> niños
          </dd>
        </dl>
        <form action="bookings" method="post">
	        <table class="table">
	          <thead>
	            <tr>
	              <th>Código</th>
	              <th>Salida</th>
	              <th>Destino</th>
	              <th>Duración</th>
	              <th>Tarifa</th>
	              <th></th>
	            </tr>
	          </thead>
	          <tbody>
	            <%
	              while(iterator.hasNext()){
	                Flight flight = (Flight)iterator.next();
	            %>
	            <tr>
	              <td>
	                <%= flight.getCode() %>
	                <br />
	                <small><%= flight.getFrequency().getAircraft().getModel() %></small>
	              </td>
	              <td><%= flight.getFrequency().getOutput().toString() %></td>
	              <td><%= flight.getFrequency().getArrival().toString() %></td>
	              <td><%= flight.getFrequency().getDuration() %> minutos</td>
	              <td>S/. <%= flight.getPrice() %></td>
	              <td>
	                <input type="checkbox" name="flight_ids[]" value="<%= flight.getId() %>" />
	              </td>
	            </tr>
	            <% } %>
	          </tbody>
	        </table>
          <input type="submit" value="Reservar" />
        </form>
      </div>
    </div>
  </div>
</body>
</html>