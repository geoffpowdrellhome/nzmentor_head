<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Routes :: <c:out value="${routesForm.formType}" /></title>
<script type="text/javascript" src='<c:url value="/resources/spring/Spring.js" />'> </script>
<jsp:directive.include file="../header.jspf" />
<script language="javascript"><!--
// TODO move to routes.js
function addSegment(segmentType) {
   var routesForm = document.getElementById('routesForm');
   routesForm.action.value = 'addSegment';
   routesForm.segmentType.value = segmentType;
   submit(routesForm);
}

function addPoint(segmentType, segmentIndex, pointType) {
   var routesForm = document.getElementById('routesForm');
	 routesForm.action.value = 'addPoint';
	 routesForm.segmentType.value = segmentType;
	 routesForm.segmentIndex.value = segmentIndex;
	 routesForm.pointType.value = pointType;
   submit(routesForm);
}

function addAirline(segmentType, segmentIndex) {
	 var routesForm = document.getElementById('routesForm');
   routesForm.action.value = 'addAirline';
   routesForm.segmentType.value = segmentType;
   routesForm.segmentIndex.value = segmentIndex;
   submit(routesForm);
}

function save() {
	 var routesForm = document.getElementById('routesForm');
	 routesForm.action.value = 'save';
   submit(routesForm);
}

function submit(routesForm) {
	routesForm.submit();
}
--></script>
</head>
<body>
<div id="main">
<p>Here, you can <b><c:out value="${routesForm.formType}" /></b> a route for the <strong>FareInfo</strong> operation.</p>
<c:if test="${not empty statusMessageKey}">
    <div id="successMessages"><!-- via param -->
    <ul>
        <li>${statusMessageKey}</li>
    </ul>
    </div>
</c:if>
<form id="cancelForm" name="cancelForm" action="index" method="get"></form>
<form:form action="${url}" commandName="routesForm" method="post">
    <%-- hidden fields for adding new fields --%>
    <form:hidden path="action" />
    <form:hidden path="segmentType" />
    <form:hidden path="segmentIndex" />
    <form:hidden path="pointType" />
    <form:errors path="*" cssClass="errors" />
    <div style="clear: both" class="padded-bottom">
        <label for="description">Description</label>
        <form:textarea path="route.description" htmlEscape="true" cols="80" rows="1" onkeydown="limitText(this,150)" onkeyup="limitText(this,150)" />
    </div>
    <div id="left-column">
    <table>
        <tr>
            <th colspan="5">Outbound</th>
            <th>
              <input type="button" value="Add" onclick="addSegment('outbound')" />
            </th>
        </tr>
        <tr>
            <th>Order</th>
            <th>From Points</th>
            <th>Via Points</th>
            <th>To Points</th>
            <th>Allowed Airlines</th>
            <th>Days Offset</th>
        </tr>
        <c:forEach var="segment" items="${routesForm.route.outboundSegments}" varStatus="s">
            <tr>
                <td valign="top">
                    <form:input path="route.outboundSegments[${s.index}].segmentOrder" size="2" maxlength="2" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.fromPoints}" varStatus="p">
                        <form:input path="route.outboundSegments[${s.index}].fromPoints[${p.index}].cityCode" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addPoint('outbound','${s.index}','from')" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.viaPoints}" varStatus="p">
                        <form:input path="route.outboundSegments[${s.index}].viaPoints[${p.index}].cityCode" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addPoint('outbound','${s.index}','via')" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.toPoints}" varStatus="p">
                        <form:input path="route.outboundSegments[${s.index}].toPoints[${p.index}].cityCode" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addPoint('outbound','${s.index}','to')" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.allowedAirlines}" varStatus="p">
                        <form:input path="route.outboundSegments[${s.index}].allowedAirlines[${p.index}].code" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addAirline('outbound','${s.index}')" />
                </td>
                <td valign="top">
                    <form:input path="route.outboundSegments[${s.index}].daysOffset" size="4" maxlength="4" />
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <div id="right-column">
    <table>
        <tr>
            <th colspan="5">Inbound</th>
            <th>
              <input type="button" value="Add" onclick="addSegment('inbound')" />
            </th>
        </tr>
        <tr>
            <th>Order</th>
            <th>From Points</th>
            <th>Via Points</th>
            <th>To Points</th>
            <th>Allowed Airlines</th>
            <th>Days Offset</th>
        </tr>
        <c:forEach var="segment" items="${routesForm.route.inboundSegments}" varStatus="s">
            <tr>
                <td valign="top">
                    <form:input path="route.inboundSegments[${s.index}].segmentOrder" size="2" maxlength="2" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.fromPoints}" varStatus="p">
                        <form:input path="route.inboundSegments[${s.index}].fromPoints[${p.index}].cityCode" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addPoint('inbound','${s.index}','from')" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.viaPoints}" varStatus="p">
                        <form:input path="route.inboundSegments[${s.index}].viaPoints[${p.index}].cityCode" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addPoint('inbound','${s.index}','via')" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.toPoints}" varStatus="p">
                        <form:input path="route.inboundSegments[${s.index}].toPoints[${p.index}].cityCode" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addPoint('inbound','${s.index}','to')" />
                </td>
                <td valign="top">
                    <c:forEach var="point" items="${segment.allowedAirlines}" varStatus="p">
                        <form:input path="route.inboundSegments[${s.index}].allowedAirlines[${p.index}].code" size="3" maxlength="3" />
                        <br />
                    </c:forEach>
                    <input type="button" value="Add" onclick="addAirline('inbound','${s.index}')" />
                </td>
                <td valign="top">
                    <form:input path="route.inboundSegments[${s.index}].daysOffset" size="4" maxlength="4" />
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <div style="clear: both" class="centred padded-bottom">
        <input type="button" value="Save" onclick="save()" />
        <input type="button" value="Cancel" onclick="document.getElementById('cancelForm').submit()" />
    </div>
</form:form>
<p><span class="warningHeading">*</span> To delete a city code or airline, please delete the text and save. The city or airline will be removed.</p>
<p><span class="warningHeading">*</span> To delete a segment, please delete the From, Via, To and Airline text for the segment and save. The segment will be removed.</p>
<p><span class="warningHeading">*</span> To re-order the segments, do not re-use existing order numbers. For example, if segments are numbered <i>0, 1, 2</i>, please re-order the segments with <i>3, 4, 5</i>.</p>
<jsp:directive.include file="../footer.jspf" /></div>
</body>
</html>