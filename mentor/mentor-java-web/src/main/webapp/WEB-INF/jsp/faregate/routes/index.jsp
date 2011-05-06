<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Routes :: index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Here, you can administer routes for the <strong>FareInfo</strong> operation.</p>
<c:if test="${not empty statusMessageKey}">
<div id="successMessages"><!-- via param -->
	<ul>
    <li>${statusMessageKey}</li>
	</ul>
	</div>
</c:if>
<table class="table">
	<tr>
		<th colspan="9">Routes</th>
	</tr>
	<c:if test="${not empty routes}">
		<tr>
		<th width="1%">From</th>
		<th width="1%">To</th>
		<th width="1%">Airline</th>
		<th colspan="3">Description</th>
		</tr>
		<c:forEach var="route" items="${routes}" varStatus="routeStatus">
		<script type="text/javascript">
			function submitForm(formId) {
	 			if(document.forms[formId].onsubmit()) {
	 				document.forms[formId].submit();
 				}
			}

			function areYouSure() {
				return confirm("Really delete this route?");
			}
		</script>
			<tr <c:if test="${routeStatus.index % 2 eq 1}"> class="even"</c:if>>
				<c:set var="routeId" value="routeMarkup${routeStatus.index}"/>
				<c:url var="editUrl" value="/faregate-admin/routes/form">
					<c:param name="id"   value="${route.id}" />
				</c:url>
				<c:url var="deleteUrl" value="/faregate-admin/routes/delete"/>
				<form id="${routeId}" action="${deleteUrl}" method="post" onsubmit="return areYouSure()">
        		<input id="id" name="id" type="hidden" value="${route.id}"/>
    		</form>
    		<td valign="top">
    		<c:forEach items="${route.outboundSegments[0].fromPoints}" var="point">
    			${point.cityCode}
    		</c:forEach>
    		</td>
    		<td valign="top">
    		<c:forEach items="${route.outboundSegments[fn:length(route.outboundSegments)-1].toPoints}" var="point">
    			${point.cityCode}
    		</c:forEach>
    		</td>
    		<td valign="top">
    		<c:forEach items="${route.outboundSegments[0].allowedAirlines}" var="airline">
    			${airline.code}<br/>
    		</c:forEach>
    		</td>
    		<td valign="top">${fn:escapeXml(route.description)}</td>
    		<td valign="top" align="right" width="1%"><a href='<c:out value="${editUrl}"/>'>Edit</a></td>
    		<td valign="top" align="right" width="1%"><a href="javascript:submitForm('${routeId}');">Delete</a></td>
    	</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty routes}">
		<tr>
			<td colspan="6">No routes have been specified.</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="6"><a href='<c:url value="/faregate-admin/routes/form"/>'>Add a route</a></td>
	</tr>
</table>
<p><span class="warningHeading">*</span> FareInfo automatically conducts carrier-neutral searches; routes specified here are in addition to these.</p>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>