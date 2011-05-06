<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Queues :: Edit Queue Schedule</title>
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
    <h2>Edit Queue Schedule for Queue ${queuesForm.schedule.queue.queueNumber}</h2>
    <div>
    <label for="schedule.startDate">Start Date:</label>
    <form:input path="schedule.startDateString" size="10" />
    </div>
    <div>
    <label for="schedule.updateNotes">Description:</label>
    <form:input path="schedule.updateNotes" size="30" maxlength="150" />
    </div>
    <input type="submit" value="Save" name="save" id="save" title="save" />
    <input type="submit" value="Cancel" name="cancel" id="cancel" title="cancel" onclick="javascript:resetForm()" />
</form:form>

<br/>
<jsp:directive.include file="../footer.jspf" />
</div>
</body>
</html>