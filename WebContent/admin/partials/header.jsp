<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
  <div class="wrapper">
    <h1>
      <a href="/Flights/admin">Administrador</a>
    </h1>
    <ul>
      <li><a href="<%= application.getContextPath() %>/admin/routes">Rutas</a></li>
      <li><a href="<%= application.getContextPath() %>/admin/frequencies">Frecuencias</a></li>
      <li><a href="<%= application.getContextPath() %>/admin/flights">Vuelos</a></li>
      <li><a href="<%= application.getContextPath() %>/admin/origins">Salidas</a></li>
      <li><a href="<%= application.getContextPath() %>/admin/destinations">Destinos</a></li>
    </ul>
  </div>
</div>