<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Airline Preferences :: Edit</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>${param.type} airlines may be added here.</p>
<form action="saveAdd">
<fieldset><label for="airlineCode">Airline Code</label> <select name="airlineCode">
	<c:forEach items="${airlines}" var="airline">
		<option value="${airline.code}">${airline.code} : ${airline.name}</option>
	</c:forEach>
</select>
<input type="hidden" name="type" value="${param.type}"/>
<input type="submit" value="Add"/>
</form><form action="index" class="inline"><input type="submit" value="Cancel"/></form>
</fieldset>
</form>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>