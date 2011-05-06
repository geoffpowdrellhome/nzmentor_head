<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ACCC Data Management :: index</title>
<link rel="stylesheet" href="../../css/default.css" />
</head>

<body>
<div id="main">
<h1>ACCC Data Management</h1>
<br/>

<c:choose>
	<c:when test="${not empty statusMessageKey}">
		<div id="successMessages">
			${statusMessageKey}
	
			<ul>
				<li>Configured servers:</li>
				<ul>
					<c:forEach var="serverURL" items="${serverURLs}">
						<li>${serverURL}</li>
					</c:forEach>
				</ul>
				<li>Successfully refreshed:</li>
				<ul>
					<c:forEach var="serverSuccess" items="${serversSuccess}">
						<li>${serverSuccess}</li>
					</c:forEach>
				</ul>

				<c:if test="${not empty serversFailure}">
					<li>Failed to refresh:</li>
					<ul>
						<c:forEach var="serverFailure" items="${serversFailure}">
							<li>${serverFailure}</li>
						</c:forEach>
					</ul>
				</c:if>

			</ul>
		</div>
	</c:when>
	<c:otherwise />
</c:choose>
<br/><br/>
<a href="loadXMLFileForm-init"> Load XML Data </a>
<br/><br/>
<a href="refreshCache-init"> Refresh Cache </a>
<br/><br/>
<jsp:directive.include file="../footer.jspf" />

</div>

</body>
</html>