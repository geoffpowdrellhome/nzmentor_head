<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Queues :: index</title>
<jsp:directive.include file="../header.jspf" />
<script type="text/javascript"><!--

function submitForm(action, type, id) {
    $("#action").val(action);
    $("#type").val(type);
    $("#" + type + "Id").val(id);
    $("#queuesForm").submit();
}

function addItem(type) {
    $("#action").val("add");
    $("#type").val(type);
    $("#queuesForm").submit();
}

function addEntry(scheduleId) {
    $("#action").val("add");
    $("#type").val("entry");
    $("#scheduleId").val(scheduleId);
    $("#queuesForm").submit();
}

function editOverride(id) {
    submitForm("edit", "override", id);
}

function enableOverride(id) {
    submitForm("enable", "override", id);
}

function disableOverride(id) {
    submitForm("disable", "override", id);
}

function editSchedule(id) {
    submitForm("edit", "schedule", id);
}

function enableSchedule(id) {
    submitForm("enable", "schedule", id);
}

function disableSchedule(id) {
    submitForm("disable", "schedule", id);
}

function submitEntry(action, scheduleId, entryId) {
    $("#action").val(action);
    $("#type").val("entry");
    $("#scheduleId").val(scheduleId);
    $("#entryId").val(entryId);
    $("#queuesForm").submit();
}


function editEntry(scheduleId, id) {
    submitEntry("edit", scheduleId, id);
}

function enableEntry(scheduleId, id) {
    submitEntry("enable", scheduleId, id);
}

function disableEntry(scheduleId, id) {
    submitEntry("disable", scheduleId, id);
}

function submitQueue(action) {
    $("#action").val(action);
    $("#type").val("queue");
    $("#queuesForm").submit();
}

//-->
</script>
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
    <form:hidden path="type" />
    <form:hidden path="overrideId" />
    <form:hidden path="scheduleId" />
    <form:hidden path="entryId" />

    <div>
    <label for="queueNumber">Select Queue:</label>
    <form:select path="queueNumber" onchange="javascript:$('#queuesForm').submit()">
        <c:forEach var="queue" items="${queuesForm.queues}" varStatus="status">
            <form:option value="${queue.queueNumber}">${queue.queueNumber} - ${queue.description}</form:option>
        </c:forEach>
    </form:select>
<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
    [<a href='javascript:submitQueue("edit")'>Edit</a>]
</security:authorize>
    </div>

<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
    <div style="width:100%; text-align:right; margin-top:5px; margin-bottom:5px">
    [<a href='javascript:addItem("override")'>Add New Queue Override</a>]&nbsp;
    [<a href='javascript:addItem("schedule")'>Add New Queue Schedule</a>]
    </div>
</security:authorize>

    <table class="table">
        <tr>
            <th>Date</th>
            <th>Day</th>
            <th>Start</th>
            <th>End</th>
            <th>Forwards To</th>
            <th>Active</th>
<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
            <th>&nbsp;</th>
            <th>&nbsp;</th>
</security:authorize>
        </tr>
        
        <%-- TODO display the overrides chronologically with the schedules, not split as they are now --%>
        
        <c:forEach var="override" items="${queuesForm.queueOverrides}" varStatus="overrideStatus">
            <tr <c:if test="${override.active ne 'Y'}">class="inactive"</c:if>>
                <td>${override.dateString}</td>
                <td><b><i>${override.updateNotes}</i></b></td>
                <td>${override.startTimeString}</td>
                <td>${override.endTimeRoundedString}</td>
                <td>${override.alternateQueue.queueNumber}</td>
                <td>${override.activeString}</td>
<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                <td>[<a href='javascript:editOverride(${override.id})'>Edit</a>]</td>
                <td>
                    <c:choose>
                        <c:when test="${override.active ne 'Y'}">
                            [<a href='javascript:enableOverride(${override.id})'>Enable</a>]
                        </c:when>
                        <c:otherwise>
                            [<a href='javascript:disableOverride(${override.id})'>Disable</a>]
                        </c:otherwise>
                    </c:choose>
                </td>
</security:authorize>
            </tr>
        </c:forEach>
        
        <c:forEach var="schedule" items="${queuesForm.queueSchedules}" varStatus="scheduleStatus">
            <tr <c:if test="${schedule.active ne 'Y'}">class="inactive"</c:if>>
                <td colspan="4">
                    <div style="font-weight:bold; padding-left: 5px">${schedule.updateNotes}:</div>
                </td>
                <td>
                    <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                    [<a href='javascript:addEntry(${schedule.id})'>Add New Entry</a>]
                    </security:authorize>
                    <security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
                    &nbsp;
                    </security:authorize>
                </td>
                <td>${schedule.activeString}</td>
<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                <td>[<a href="#" onClick="javascript:editSchedule(${schedule.id})">Edit</a>]</td>
                <td>
                    <c:choose>
                        <c:when test="${schedule.active ne 'Y'}">
                            [<a href="#" onClick="javascript:enableSchedule(${schedule.id})">Enable</a>]
                        </c:when>
                        <c:otherwise>
                            [<a href="#" onClick="javascript:disableSchedule(${schedule.id})">Disable</a>]
                        </c:otherwise>
                    </c:choose>
                </td>
</security:authorize>
            </tr>
            <c:forEach var="entry" items="${schedule.queueScheduleEntries}" varStatus="entryStatus">
                <tr <c:if test="${entry.active ne 'Y' or schedule.active ne 'Y'}">class="inactive"</c:if>>
                    <td>From ${schedule.startDateString}</td>
                    <td>${entry.day.name}</td>
                    <td>${entry.startTimeString}</td>
                    <td>${entry.endTimeRoundedString}</td>
                    <td>${entry.alternateQueue.queueNumber}</td>
                    <td>${entry.activeString}</td>
<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                    <td>[<a href="#" onClick="javascript:editEntry(${schedule.id}, ${entry.id})">Edit</a>]</td>
                    <td>
                        <c:choose>
                            <c:when test="${schedule.active ne 'Y'}">
                                [Enable]
                            </c:when>
                            <c:when test="${entry.active ne 'Y'}">
                                [<a href="#" onClick="javascript:enableEntry(${schedule.id}, ${entry.id})">Enable</a>]
                            </c:when>
                            <c:otherwise>
                                [<a href="#" onClick="javascript:disableEntry(${schedule.id}, ${entry.id})">Disable</a>]
                            </c:otherwise>
                        </c:choose>
                    </td>
</security:authorize>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
    
</form:form>

<p>
<h3>Notes</h3>
<ul>
<li>When providing a new Queue Schedule, add an Entry for each day.</li>
<li>When a public holiday is coming up, add a Queue Override so the PNRs are queued to the correct queue for the public holiday.</li>
</ul>
</p>

<br/>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>