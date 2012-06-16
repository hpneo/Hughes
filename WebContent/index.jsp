<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Flights</title>
  <link rel="stylesheet" type="text/css" href="styles/layout.css" />
  <link rel="stylesheet" type="text/css" href="styles/index.css" />
</head>
<body>
  <jsp:include page="partials/header.jsp"></jsp:include>
  <div id="body">
    <div class="wrapper">
      <div id="map"></div>
      <div class="row">
        <div class="column _70">
          <form action="" method="post">
            <p>
              <label for="vuelo_tipo_ida_vuelta"><input type="radio" name="vuelo_tipo" id="vuelo_tipo_ida_vuelta" checked="checked" /> Ida y vuelta</label>
              <label for="vuelo_tipo_ida"><input type="radio" name="vuelo_tipo" id="vuelo_tipo_ida" /> Solo ida</label>
            </p>
            <p>
					    <label for="vuelo_fecha_ida">Fecha de ida:</label>
					    <input type="datetime" name="vuelo[fecha_ida]" id="vuelo_fecha_ida" />
					    <label for="vuelo_fecha_vuelta">Fecha de vuelta:</label>
					    <input type="datetime" name="vuelo[fecha_vuelta]" id="vuelo_fecha_vuelta" />
					  </p>
            <p>
              <label for="vuelo_origen">Ciudad de origen:</label>
              <label for="vuelo_origen">Ciudad de destino:</label>
            </p>
            <p>
					    <label for="vuelo_clase">Clase:</label>
					    <select name="vuelo[clase]" id="vuelo_clase">
					      <option value="economico">clase económica</option>
					      <option value="empresarial">clase empresarial</option>
					      <option value="vip">clase vip</option>
					    </select>
            </p>
            <p>
					    <label for="vuelo_numero_pasajes_adultos">Número de pasajes:</label>
					    <select name="vuelo[numero_pasajes_adultos]" id="vuelo_numero_pasajes_adultos">
					      <option value="1">1 adulto</option>
					      <option value="2">2 adultos</option>
					      <option value="3">3 adultos</option>
					      <option value="4">4 adultos</option>
					      <option value="5">5 adultos</option>
					    </select>
					    <label for="vuelo_numero_pasajes_niños">Número de pasajes:</label>
					    <select name="vuelo[numero_pasajes_niños]" id="vuelo_numero_pasajes_niños">
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