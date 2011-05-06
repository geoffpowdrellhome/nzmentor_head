<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>FareGate :: error</title>
<jsp:directive.include file="../header.jspf" />
</head>
<body>
<div id="main">
<p><b>Something unexpected happened. Please try again.</b></p>
<p><b>Error:</b> ${exception}</p>
<c:if test="${exception.cause ne null}">
<p><b>Caused by:</b> ${exception.cause}</p>
<c:if test="${exception.cause.cause ne null}">
<p><b>Caused by:</b> ${exception.cause.cause}</p>
</c:if>
</c:if>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>