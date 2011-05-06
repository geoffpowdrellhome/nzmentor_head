<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<html>
<head>
<title>FareGate :: Administration</title>
<jsp:directive.include file="WEB-INF/jsp/header.jspf" />
</head>
<body>
<h1>Welcome to FareGate</h1>
<p>Version: @@version@@</p>
<p>Release: @@release@@</p>
<p><a href="services/FareGate" title="endpoint">Web Service</a></p>
<p><a href="services/FareGate?wsdl" title="endpoint">WSDL</a></p>
<h4>Administration <sup><span class="warningHeading">*</span></sup></h4>
<ul>
  <li><a href="faregate-admin/airlines/index">Airlines</a></li>
  <li><a href="faregate-admin/airline_fare_details/index">Fare Details</a></li>
  <li><a href="faregate-admin/preferences/index">Airline Sort Preferences</a></li>
  <li><a href="faregate-admin/airlinemarkups/index">Airline Mark-up Preferences</a></li>
  <li><a href="faregate-admin/routemarkups/index">Route Mark-up Preferences</a></li>
  <li><a href="faregate-admin/routes/index">Routes Administration</a></li>
  <li><a href="faregate-admin/providerpreferences/index">Provider Preferences</a></li>
  <li><a href="faregate-admin/acccdatamanagement/home">ACCC Data Management</a></li>
  <li><a href="faregate-admin/logging/index">Logging</a></li>
  <li><a href="faregate-admin/cache/index">Caches</a></li>
  <li><a href="faregate-admin/locations/index">Locations</a></li>
  <li><a href="faregate-admin/queues/index">Queues</a></li>
  <li><a href="faregate-admin/messages/index">Messages</a></li>
  <li><a href="axis2-web">Axis2 Settings</a></li>
  <li><!-- <a href="faregate-admin/currency/index"> -->Currency<!-- </a> --></li>
  <security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
    <li><a href="spring_security_login">Login</a></li>
  </security:authorize>
  <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
    <li><a href="j_spring_security_logout">Logout</a></li>
  </security:authorize>
</ul>

<p class="smallText"><span class="warningHeading">*</span> <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
  Logged in as: <strong><security:authentication property="principal.username" /></strong>
  <security:authorize ifAnyGranted="ROLE_ADMIN">(admin)</security:authorize></p>
<br />
</security:authorize>
<security:authorize ifNotGranted="ROLE_ADMIN">
Requires admin login<br />
</security:authorize>
</p>
</body>
</html>