<%@page import="java.util.Iterator"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
  Origin[] origins = OriginBean.all().toArray(new Origin[0]);
  Destination[] destinations = DestinationBean.all().toArray(new Destination[0]);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Flights</title>
  <link rel="stylesheet" type="text/css" href="styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="styles/index.css" />
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=es"></script>
  <script type="text/javascript" src="scripts/gmaps.js"></script>
  <script type="text/javascript">
    var map;
    var route;
    $(document).ready(function(){
    	map = new GMaps({
    		div: '#map_container',
    		lat: 14.859850400601037,
        lng: -24.43359375,
        zoom: 2
    	});
      map.addMarkers([{
        title: 'Origen',
        lat: -12.043333,
        lng: -77.028333,
        icon: 'images/dot.png'
      },{
        title: 'Destino',
        lat: -12.043333,
        lng: -77.028333,
        icon: 'images/dot-blue.png'
      }]);
      
      route = new google.maps.Polyline({
    	  geodesic: true,
    	  clickable: false,
    	  map: null,
    	  strokeColor: '#6699cc',
    	  strokeOpacity: 0.8,
    	  strokeWeight: 4
      });
    });
    $(document).on('change', '#flight_origin', function(e){
    	if($(this).val() != ''){
        var selected_option = $(this).find('option:selected');
        var lat = parseFloat(selected_option.data('lat'));
        var lng = parseFloat(selected_option.data('lng'));
        
        map.markers[0].setPosition(new google.maps.LatLng(lat, lng));
        map.fitZoom();
        route.setMap(map.map);
        route.setPath([map.markers[0].getPosition(), map.markers[1].getPosition()]);
    	}
    });
    $(document).on('change', '#flight_destination', function(e){
        if($(this).val() != ''){
        	var selected_option = $(this).find('option:selected');
        	var lat = parseFloat(selected_option.data('lat'));
          var lng = parseFloat(selected_option.data('lng'));
          
          map.markers[1].setPosition(new google.maps.LatLng(lat, lng));
          map.fitZoom();
          route.setMap(map.map);
          route.setPath([map.markers[0].getPosition(), map.markers[1].getPosition()]);
        }
    });
  </script>
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <div id="map_container"></div>
      <div class="row">
        <div class="column _70">
          <form action="flights" method="post">
            <p>
              <label for="flight_type_bi"><input type="radio" name="type" id="flight_type_bi" value="bi" checked="checked" /> Ida y vuelta</label>
              <label for="flight_type_one"><input type="radio" name="type" value="one" id="flight_type_one" /> Solo ida</label>
            </p>
            <p>
              <label for="flight_output_date">Fecha de ida:</label>
              <input type="datetime" name="output_date" id="flight_output_date" />
              <label for="flight_arrival_date">Fecha de vuelta:</label>
              <input type="datetime" name="arrival_date" id="flight_arrival_date" />
            </p>
            <p>
              <label for="flight_origin">Ciudad de origen:</label>
              <select name="origin" id="flight_origin">
                <option></option>
                <% for(int i=0;i<origins.length;i++) { %>
                  <option data-lat="<%= origins[i].getLat() %>" data-lng="<%= origins[i].getLng() %>" value="<%= origins[i].getId() %>"><%= origins[i].getName() %></option>
                 <% } %>
              </select>
              <label for="flight_destination">Ciudad de destino:</label>
              <select name="destination" id="flight_destination">
                <option></option>
                <% for(int i=0;i<destinations.length;i++) { %>
                  <option data-lat="<%= destinations[i].getLat() %>" data-lng="<%= destinations[i].getLng() %>" value="<%= destinations[i].getId() %>"><%= destinations[i].getName() %></option>
                <% } %>
              </select>
            </p>
            <p>
              <label for="flight_flight_class">Clase:</label>
              <select name="flight_class" id="flight_flight_class">
                <option value="economico">clase económica</option>
                <option value="empresarial">clase empresarial</option>
                <option value="vip">clase vip</option>
              </select>
            </p>
            <p>
              <label for="flight_adult_passages_count">Número de pasajes:</label>
              <select name="adult_passages_count" id="flight_adult_passages_count">
                <option value="1">1 adulto</option>
                <option value="2">2 adultos</option>
                <option value="3">3 adultos</option>
                <option value="4">4 adultos</option>
                <option value="5">5 adultos</option>
              </select>
              <label for="flight_child_passages_count">Número de pasajes:</label>
              <select name="child_passages_count" id="flight_child_passages_count">
                <option value="1">1 niño</option>
                <option value="2">2 niños</option>
                <option value="3">3 niños</option>
                <option value="4">4 niños</option>
                <option value="5">5 niños</option>
              </select>
            </p>
            <p>
             <input type="submit" value="Buscar vuelos" />
            </p>
          </form>
        </div>
        <div class="column _30" id="user_links">
          <ul class="unstyled">
            <li>
              <a href="#">Mis reservas anteriores</a>
            </li>
            <li>
              <a href="#">Actualizar datos personales</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</body>
</html>