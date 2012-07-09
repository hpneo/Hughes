<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  Collection<Frequency> frequencies = FrequencyBean.all();
  Iterator<Frequency> iteratorFrequency = frequencies.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Nuevo vuelo</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
  <link rel="stylesheet" media="screen" type="text/css" href="<%= application.getContextPath() %>/styles/datepicker.css" />
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="<%= application.getContextPath() %>/scripts/datepicker.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
    	$('#outputDate').DatePicker({
        date: new Date(),
        format: "d/m/Y",
        onChange: function(formated, dates){
          $('#outputDate').val(formated);
          $('#outputDate').DatePickerHide();
        }
      });
    });
  </script>
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper padded">
      <h2>Nuevo vuelo</h2>
      <div class="row">
        <form method="post">
          <p>
            <label for="frequency">Frecuencia</label>
            <select name="frequency" id="frequency">
              <%
                while(iteratorFrequency.hasNext()) {
                  Frequency frequency = iteratorFrequency.next();
              %>
                <option value="<%= frequency.getId() %>"><%= FrequencyBean.getIdentifier(frequency) %></option>
              <% } %>
            </select>
          </p>
          <p>
            <label for="code">Código</label>
            <input type="text" name="code" id="code" />
          </p>
          <p>
            <label for="flightClass">Clase</label>
            <input type="text" name="flightClass" id="flightClass" />
          </p>
          <p>
            <label for="outputDate">Fecha de salida</label>
            <input type="text" name="outputDate" id="outputDate" />
          </p>
          <p>
            <label for="price">Precio</label>
            <input type="text" name="price" id="price" />
          </p>
          <p>
            <label for="availableSeats">Asientos disponibles</label>
            <input type="text" name="availableSeats" id="availableSeats" />
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