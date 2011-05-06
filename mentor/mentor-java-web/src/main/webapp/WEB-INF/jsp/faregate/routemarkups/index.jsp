<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Route Markups :: index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Hi. Here, you can administer route-specific mark-up overrides for the <strong>FareInfo</strong> operation.</p>
<c:if test="${not empty statusMessageKey}">
<div id="successMessages"><!-- via param -->
	<ul>
    <li>${statusMessageKey}</li>
	</ul>
	</div>
</c:if>
<table class="table">
	<tr>
		<th colspan="9">Route Mark-up Overrides</th>
	</tr>
	<c:if test="${not empty routeMarkups}">
		<tr>
			<th colspan="2">Airline</th>
			<th colspan="2">From</th>
			<th colspan="2">To</th>
			<th>Mark-up</th>
			<th>Type</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${routeMarkups}" var="entry" varStatus="entryStatus">
			
			
			<tr <c:if test="${entryStatus.index % 2 eq 1}"> class="even"</c:if>>
				<c:set var="routeMarkupFormId" value="routeMarkup${entryStatus.index}"/>
				<c:url var="deleteUrl" value="/faregate-admin/routemarkups/delete"/>
				<form id="${routeMarkupFormId}" action="${deleteUrl}" method="POST">
            		<input id="id" name="id" type="hidden" value="${entry.id}"/>
        		</form>
				<td>${entry.airlineMarkupDTO.airlineDTO.code}</td>
				<td>${entry.airlineMarkupDTO.airlineDTO.name}</td>
				<td>${entry.departureAirport.code}</td>
				<td>${entry.departureAirport.name}</td>
				<td>${entry.arrivalAirport.code}</td>
				<td>${entry.arrivalAirport.name}</td>
				<td>${entry.markupDTO.amount}</td>
				<td>${fn:toLowerCase(entry.markupDTO.type)}</td>
				<td align="right"><a href="javascript:document.forms['${routeMarkupFormId}'].submit();">Delete</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty routeMarkups}">
		<tr>
			<td colspan="9">No mark-ups in this category.</td>
		</tr>
	</c:if>
	<tr>
		<c:url var="editUrl" value="/faregate-admin/routemarkups/form"/>
		<td colspan="9"><a href='<c:out value="${editUrl}"/>'>Add</a></td>
	</tr>
</table>
<p><span class="warningHeading">*</span> All routes not listed will use the default mark-up for their respective airline.</p>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>