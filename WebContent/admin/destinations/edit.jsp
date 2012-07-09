<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  Destination destination = (Destination)request.getSession().getAttribute("destination");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador - Editar destino</title>
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/styles/admin.css" />
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=es"></script>
  <script type="text/javascript" src="<%= application.getContextPath() %>/scripts/gmaps.js"></script>
  <script type="text/javascript">
    var map;
    
    $(document).ready(function(){
    	map = new GMaps({
        div: '#map_canvas',
        lat: <%= destination.getLat() %>,
        lng: <%= destination.getLng() %>,
        zoom: 14
      });
    	
    	map.addMarker({
	      lat: <%= destination.getLat() %>,
	      lng: <%= destination.getLng() %>,
	      draggable: true,
	      dragend: function(e){
	        $('#lat').val(e.position.lat());
	        $('#lng').val(e.position.lng());
	      }
    	});
    });
  </script>
</head>
<body>
  <jsp:include page="/admin/partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <h2>Editar vuelo <%= destination.getName() %></h2>
      <div id="map_canvas"></div>
      <div class="row">
        <form method="post">
          <p>
            <label for="name">Nombre</label>
            <input type="text" name="name" id="name" value="<%= destination.getName() %>" />
          </p>
          <p>
            <input type="hidden" name="lat" id="lat" value="<%= destination.getLat() %>" />
            <input type="hidden" name="lng" id="lng" value="<%= destination.getLng() %>" />
            <input type="submit" value="Editar" />
          </p>
        </form>
      </div>
    </div>
  </div>
</body>
</html>