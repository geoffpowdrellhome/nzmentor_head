<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Queues :: Add Queue Schedule Entry</title>
<jsp:directive.include file="../header.jspf" />
<script language="javascript"><!--
$(document).ready(function() {
    $("#action").val("save");
});
function resetForm() {
    document.getElementById("queuesForm").reset();
}
//--></script>
</head>
<body>
<div id="main">
<p>Here, you can administer queues for <strong>FareGate</strong> operation.</p>
<c:if test="${not empty statusMessageKey}">
<div id="successMessages"><!-- via param -->
    <ul>
    <li>${statusMessageKey}</li>
    </ul>
    </div>
</c:if>

<form:form action="form" commandName="queuesForm" method="post">
    <form:hidden path="action" />
    <h2>
        Add Queue Schedule Entry for
        Queue ${queuesForm.entry.queueSchedule.queue.queueNumber}
        starting on ${queuesForm.entry.queueSchedule.startDateString}
    </h2>
    <div>
    <label for="entry.day">Day:</label>
    <form:select path="day">
        <form:option value="MON">Monday</form:option>
        <form:option value="TUE">Tuesday</form:option>
        <form:option value="WED">Wednesday</form:option>
        <form:option value="THU">Thursday</form:option>
        <form:option value="FRI">Friday</form:option>
        <form:option value="SAT">Saturday</form:option>
        <form:option value="SUN">Sunday</form:option>
    </form:select>
    </div>
    <div>
    <label for="entry.startTimeString">Start Time:</label>
    <form:select path="entry.startTimeString">
        <%@ include file="startTimes.jspf" %>
    </form:select>
    <small>(TimeZone: ${queuesForm.entry.timezone})</small>
    </div>
    <div>
    <label for="entry.endTimeLongString">End Time:</label>
    <form:select path="entry.endTimeLongString">
        <%@ include file="endTimes.jspf" %>
    </form:select>
    <small>(TimeZone: ${queuesForm.entry.timezone})</small>
    </div>
    <div>
    <label for="alternateQueueNumber">Forwards to Queue:</label>
    <form:select path="alternateQueueNumber">
        <c:forEach var="queue" items="${queuesForm.queues}" varStatus="status">
            <form:option value="${queue.queueNumber}" >${queue.queueNumber} - ${queue.description}</form:option>
        </c:forEach>
    </form:select>
    </div>
    <input type="submit" value="Save" name="save" id="save" title="save" />
    <input type="submit" value="Cancel" name="cancel" id="cancel" title="cancel" onclick="javascript:resetForm()" />
</form:form>

<br/>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>