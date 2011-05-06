<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ACCC Data Management :: Load XML file</title>
<link rel="stylesheet" href="../../css/default.css" />
</head>

<body>
<div id="main">
<h1> Load ACCC XML file</h1>
<br/>

<c:choose>
	<c:when test="${not empty statusMessageKey}">
		<div id="errorMessages">
		<ul>
			<li>${statusMessageKey}</li>
		</ul>
		</div>
	</c:when>
	<c:otherwise />
</c:choose>

<form action="loadfile"	method="POST">	
	Enter file location: <input type="text" name="fileName" value="" /> 
	<br />
	<br />
	
	Select the brand/site to apply for:
	<c:forEach items="${siteList}" var="site" varStatus="status">
		${site.siteCode}<input type="radio" name="site" value="${site.siteCode}"/>
	</c:forEach>
	<br/>
	<br/>
	<input type="submit" value="Download File" />
</form>

<jsp:directive.include file="../footer.jspf" /></div>

</body>
</html>