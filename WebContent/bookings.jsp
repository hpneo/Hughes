<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  User current_user = (User)request.getSession().getAttribute("current_user");
  Collection<Booking> bookings = (Vector<Booking>)current_user.getBookings();
  Iterator<Booking> iterator = bookings.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Hughes - Mis reservas</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/bookings.css" />
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <h2>Mis reservas</h2>
      <div class="row">
        <table class="table">
          <thead>
            <tr>
              <th>Vuelo</th>
              <th>Pasajero</th>
              <th>Fecha de salida</th>
              <th>Fecha de llegada</th>
              <th>Duración</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <%
              while(iterator.hasNext()){
                Booking booking = iterator.next();
            %>
            <tr>
              <td><%= booking.getFlight().getCode() %></td>
              <td><%= booking.getPassengerName() %></td>
              <td><%= FlightBean.getOuputDate(booking.getFlight()) %></td>
              <td><%= FlightBean.getArrivalDate(booking.getFlight()) %></td>
              <td><%= booking.getFlight().getFrequency().getDuration() %> minutos</td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>
</html>