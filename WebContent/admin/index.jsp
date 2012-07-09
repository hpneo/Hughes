<%@page import="java.util.*"%>
<%@page import="com.hughes.models.*"%>
<%@page import="com.hughes.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Collection<Origin> origins = OriginBean.all();
	Collection<Destination> destinations = DestinationBean.all();

  Iterator<Origin> originIterator = origins.iterator();
  Iterator<Destination> destinationIterator = destinations.iterator();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Administrador</title>
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
        lat: 14.859850400601037,
        lng: -24.43359375,
        zoom: 2
      });
      <%
      while(originIterator.hasNext()){
        Origin origin = originIterator.next();
      %>
		    map.addMarker({
		      lat: <%= origin.getLat() %>,
		      lng: <%= origin.getLng() %>,
		      title: '<%= origin.getName() %>',
		      icon: '<%= application.getContextPath() %>/images/origin_marker.png'
		    });
      <% } %>
	    <%
	    while(destinationIterator.hasNext()){
	      Destination destination = destinationIterator.next();
	    %>
			  map.addMarker({
			    lat: <%= destination.getLat() %>,
			    lng: <%= destination.getLng() %>,
			    title: '<%= destination.getName() %>',
			    icon: '<%= application.getContextPath() %>/images/destination_marker.png'
			  });
      <% } %>
      map.fitZoom();
    });
  </script>
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <div id="map_canvas"></div>
      <div class="row"></div>
    </div>
  </div>
</body>
</html>