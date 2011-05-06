<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ACCC Download :: Index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<h1> Download ACCC XML feeds</h1>
<br/>

<c:choose>
	<c:when test="${'successful' eq statusMessageKey}">
		<!-- Why == doesn't work here??? -->
		<div id="successMessages">
		<ul>
			<li>You have successfully download the XML feeds to the
			database. Fare cached will be reloaded within the 12 hours.</li>
		</ul>
		</div>
	</c:when>
	<c:when test="${not empty statusMessageKey}">
		<div id="errorMessages">
		<ul>
			<li>${statusMessageKey}</li>
		</ul>
		</div>
	</c:when>
	<c:otherwise />
</c:choose>

<form action="/faregate/faregate-admin/datadownloading/load"
	method="POST">	Enter file you want to download: <input type="text" name="fileName" value="" /> 
	<br />
	<br />
	
	Select the brand/site to apply for:
	<c:forEach items="${siteList}" var="site" varStatus="status">
		${site.siteCode}<input type="radio" name="brandName" value="${site.siteCode}"/>
	</c:forEach>
	<br/>
	<br/>

<input type="submit" value="Download File" /></form>

<%
    // we might implement the refresh cache at a later stage
%> <!-- 
	<c:if test="${statusMessageKey == 'successful'}">
		You can now refresh the flight data cache.
		<form action="refreshData" ><input type="submit" value="Refresh Data"/></form>
	</c:if>

--> <jsp:directive.include file="../footer.jspf" /></div>

</body>
</html>