<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  @SuppressWarnings("unchecked")
  Collection<Booking> bookings = (Vector<Booking>)request.getSession().getAttribute("bookings");
  Iterator<Booking> iterator = bookings.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Reservas</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Reservas</h2>
      <div class="row">
        <table class="table">
          <thead>
            <tr>
              <th>Pasajero</th>
              <th>Usuario</th>
              <th>Vuelo</th>
              <th>Fecha</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <%
              while(iterator.hasNext()){
                Booking booking = iterator.next();
            %>
            <tr>
              <td><%= booking.getPassengerName() %></td>
              <td><%= booking.getUser().getLastName() + ", " + booking.getUser().getFirstName() %></td>
              <td><%= FrequencyBean.getIdentifier(booking.getFlight().getFrequency()) %></td>
              <td><%= DateBean.formatDate(booking.getFlight().getOutputDate()) %></td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>
</html>