<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Airline Preferences :: index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Hi. Here, you can administer airline preferences for the <strong>Faregate</strong> operation.</p>
<c:if test="${not empty successMessages}">
	<div id="successMessages">
	<ul>
		<c:forEach items="${successMessages}" var="entry">
			<li>${entry}</li>
		</c:forEach>
	</ul>
	</div>
</c:if>

<%-- TODO messages turn up in URL string after redirect - that's uncool. --%>
<%--  Investigate using this: http://developingdeveloper.wordpress.com/2008/02/28/common-reference-data-in-spring-mvc/ --%>
<c:if test="${not empty param.successMessages}">

	<div id="successMessages"><!-- via param -->
	<ul>
		<li>${param.successMessages}</li>
	</ul>
	</div>
</c:if>
<table class="table">
	<tr>
		<th colspan="2">Preferred airline codes</td>
	</tr>
	<c:if test="${not empty preferredAirlineCodes}">
		<c:forEach items="${preferredAirlineCodes}" var="entry" varStatus="entryStatus">
			<tr<c:if test="${entryStatus.index % 2 eq 1}"> class="even"</c:if>>
				<td>${entry.code} : ${entry.name}</td>
				<td align="right"><a href="deletePreferred?airlineCode=${entry.code}">Delete</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty preferredAirlineCodes}">
		<tr>
			<td colspan="2">No airlines in this category.</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="2"><a href="add?type=Preferred">Add</a></td>
	</tr>
</table>
<table class="table">
	<tr>
		<th colspan="2">Unpopular airline codes</td>
	</tr>
	<c:if test="${not empty unpopularAirlineCodes}">
		<c:forEach items="${unpopularAirlineCodes}" var="entry" varStatus="entryStatus">
			<tr<c:if test="${entryStatus.index % 2 eq 1}"> class="even"</c:if>>
				<td>${entry.code} : ${entry.name}</td>
				<td align="right"><a href="deleteUnpopular?airlineCode=${entry.code}">Delete</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty unpopularAirlineCodes}">
		<tr>
			<td colspan="2">No airlines in this category.</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="2"><a href="add?type=Unpopular">Add</a></td>
	</tr>
</table>
<table class="table">
	<tr>
		<th colspan="2">Blacklisted airline codes</td>
	</tr>
	<c:if test="${not empty blacklistedAirlineCodes}">
		<c:forEach items="${blacklistedAirlineCodes}" var="entry" varStatus="entryStatus">
			<tr<c:if test="${entryStatus.index % 2 eq 1}"> class="even"</c:if>>
				<td>${entry.code} : ${entry.name}</td>
				<td align="right"><a href="deleteBlacklisted?airlineCode=${entry.code}">Delete</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty blacklistedAirlineCodes}">
		<tr>
			<td colspan="2">No airlines in this category.</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="2"><a href="add?type=Blacklisted">Add</a></td>
	</tr>
</table>
<p><span class="warningHeading">*</span> All airlines not listed have normal preference status.</p>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>