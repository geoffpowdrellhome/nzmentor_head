<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Provider Preferences :: index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>This page allows administration of preferences for each provider.</p>
<c:if test="${not empty statusMessageKey}">
	<div id="successMessages">
	<ul>
		<c:forEach items="${statusMessageKey}" var="entry">
			<li>${entry}</li>
		</c:forEach>
	</ul>
	</div>
</c:if>
<c:if test="${not empty param.statusMessageKey}">

	<div id="successMessages"><!-- via param -->
	<ul>
		<li>${param.statusMessageKey}</li>
	</ul>
	</div>
</c:if>
<table class="table">
<c:forEach var="entry" items="${providerPreferences}">
		<tr>
			<th>Provider</th>
			<th>Preference Name</th>
			<th>Preference Value</th>
			<th>&nbsp;</th>
		</tr>
	<c:forEach var="preference" items='${entry.value}' varStatus="preferenceStatus">
		<c:set var="formId" value="formId${preferenceStatus.index}"/>
		<c:url var="deleteUrl" value="/faregate-admin/providerpreferences/delete"/>
		<form id="${formId}" action="${deleteUrl}" method="post">
			<input id="providerCode" name="providerCode" type="hidden" value="${entry.key.code}"/>
        	<input id="preferenceCode" name="code" type="hidden" value="${preference.value}"/>
        </form>
	<tr <c:if test="${preferenceStatus.index % 2 eq 1}"> class="even"</c:if>>
	<td>${entry.key.name}</td>
	<td>${preference.key}</td>

	<c:choose>
	<c:when test="${not empty preference.value}">
        <td>
            <c:choose>
                <c:when test="${fn:length(preference.value) > 20}">
		            ${fn:substring(fn:escapeXml(preference.value), 0, 20)}...
                </c:when>
                <c:otherwise>
                    ${fn:escapeXml(preference.value)}
                </c:otherwise>
            </c:choose>
        </td>
		<c:url var="editUrl" value="/faregate-admin/providerpreferences/form">
			<c:param name="providerCode" value="${entry.key.code}"/>
			<c:param name="preferenceCode" value="${preference.key}"/>
		</c:url>
		<td align="right"><a href='<c:out value="${editUrl}"/>'>Edit</a></td>
		<%--<td align="right"><a href="javascript:document.forms['${formId}'].submit();">Delete</a></td> --%>
	</c:when>
	<c:otherwise>
		<td><em>not yet set for this provider</em></td>
		<c:url var="addUrl" value="/faregate-admin/providerpreferences/form">
			<c:param name="providerCode" value="${entry.key.code}"/>
			<c:param name="preferenceCode" value="${preference.key}"/>
		</c:url>
		<td align="right"><a href='<c:out value="${addUrl}"/>'>Add</a></td>
	</c:otherwise>
	</c:choose>
	</tr>
	</c:forEach>
</c:forEach>
</table>

<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>