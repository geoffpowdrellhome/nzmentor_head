<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Provider Preferences :: Edit</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<c:if test="${not empty statusMessageKey}">
<div id="successMessages"><!-- via param -->
	<ul>
    <li>${statusMessageKey}</li>
	</ul>
	</div>
</c:if>
<c:url var="url" value="/faregate-admin/providerpreferences/form" /> 
<form:form action="${url}" commandName="userProviderSettings">
<form:hidden path="user.id" />
<form:hidden path="key" />
<form:hidden path="provider.name" />
<fieldset> 
<label for="provider.code">Provider</label>
<form:select path="provider.code" items="${providers}" itemValue="code" itemLabel="name"/> <form:errors path="provider.code" cssClass="errors"/>
<%-- TODO use text field instead as diff providers may have diff settings.
which provider the setting is applied to should be determined by which add/edit link the user clicks on previous screen.
<input type="text" disabled="disabled" value="${userProviderSettings.provider.name}" size="32"/> <form:errors path="provider.name" cssClass="errors"/> --%>
<label for="key">Preference Settings</label>
<input type="text" disabled="disabled" value="${userProviderSettings.key}" size="32"/> <form:errors path="key" cssClass="errors"/>
<label for="value">value</label>
<c:choose>
<c:when test="${not empty validValues}">
<form:select path="value" items="${validValues}"/>
</c:when>
<c:otherwise>
<form:input path="value" htmlEscape="true" />
</c:otherwise>
</c:choose>
 <form:errors path="value" cssClass="errors"/>
 <br/>
<input type="submit" name="add" value="Update"/>
<form action="index" class="inline"><input type="submit" name="cancel" value="Cancel"/></form>
</fieldset>
</form:form>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>