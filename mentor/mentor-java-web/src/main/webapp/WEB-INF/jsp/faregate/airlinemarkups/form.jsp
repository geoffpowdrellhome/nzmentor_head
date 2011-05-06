<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Airline Markups :: Edit</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Here, you can administer airline markups for the <strong>FareInfo</strong> operation.</p>
<c:if test="${not empty statusMessageKey}">
<div id="successMessages"><!-- via param -->
	<ul>
    <li>${statusMessageKey}</li>
	</ul>
	</div>
</c:if>
<c:url var="url" value="/faregate-admin/airlinemarkups/form" /> 
<form:form action="${url}" commandName="airlineMarkup">
<form:hidden path="id" />
<fieldset> 
<label for="airline">Airline Code</label>
<form:select path="airlineDTO.code" items="${airlines}" itemValue="code" itemLabel="name"/> <form:errors path="airlineDTO.code" cssClass="errors"/>
<label for="markupDTO.amount">mark up amount</label>
<form:input path="markupDTO.amount" /> <form:errors path="markupDTO.amount" cssClass="errors"/>
<label for="markupDTO.type">mark up type</label>
<form:select path="markupDTO.type" items="${markupTypes}"/> <form:errors path="markupDTO.type" cssClass="errors"/>
<input type="submit" name="add" value="Add"/>
<form action="index" class="inline"><input type="submit" name="cancel" value="Cancel"/></form>
</fieldset>
</form:form>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>