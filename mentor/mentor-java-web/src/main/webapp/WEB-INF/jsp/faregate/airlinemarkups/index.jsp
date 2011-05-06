<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Airline Markups :: index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Hi. Here, you can administer mark-ups for the <strong>FareInfo</strong> operation.</p>
<c:if test="${not empty statusMessageKey}">
<div id="successMessages"><!-- via param -->
	<ul>
    <li>${statusMessageKey}</li>
	</ul>
	</div>
</c:if>
<table class="table">
	<tr>
		<th colspan="5">Airline Mark-ups</th>
	</tr>
	<c:if test="${not empty airlineMarkups}">
		<tr>
			<th colspan="2">Airline</th>
			<th>Mark-up</th>
			<th>Type</th>
			<th></th>
		</tr>
		<c:forEach items="${airlineMarkups}" var="entry" varStatus="entryStatus">
			
			
			<tr <c:if test="${entryStatus.index % 2 eq 1}"> class="even"</c:if>>
				<c:set var="airlineMarkupFormId" value="airlineMarkup${entryStatus.index}"/>
				<c:url var="deleteUrl" value="/faregate-admin/airlinemarkups/delete"/>
				<form id="${airlineMarkupFormId}" action="${deleteUrl}" method="post">
            		<input id="id" name="id" type="hidden" value="${entry.id}"/>
        		</form>
				<td>${entry.airlineDTO.code}</td>
				<td>${entry.airlineDTO.name}</td>
				<td>${entry.markupDTO.amount}</td>
				<td>${fn:toLowerCase(entry.markupDTO.type)}</td>
				<td align="right"><a href="javascript:document.forms['${airlineMarkupFormId}'].submit();">Delete</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty airlineMarkups}">
		<tr>
			<td colspan="5">No mark-ups in this category.</td>
		</tr>
	</c:if>
	<tr>
		<c:url var="editUrl" value="/faregate-admin/airlinemarkups/form"/>
		<td colspan="5"><a href='<c:out value="${editUrl}"/>'>Add</a></td>
	</tr>
</table>
<p><span class="warningHeading">*</span> All airlines not listed have default mark down of 5%.</p>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>