<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cache :: showing <c:out value="${cache}" /></title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Hi. Here, you can administer cache refreshes for <strong>FareGate</strong>.</p>
<c:if test="${not empty successMessages}">
    <div id="successMessages">
    <ul>
        <c:forEach items="${successMessages}" var="entry">
            <li>${entry}</li>
        </c:forEach>
    </ul>
    </div>
</c:if> <%-- TODO messages turn up in URL string after redirect - that's uncool. --%> <%--  Investigate using this: http://developingdeveloper.wordpress.com/2008/02/28/common-reference-data-in-spring-mvc/ --%>
<c:if test="${not empty param.successMessages}">
    <div id="successMessages"><!-- via param -->
    <ul>
        <li>${param.successMessages}</li>
    </ul>
    </div>
</c:if>
<table class="table" id="tableWithForms">
    <tr>
        <th>Cache</th>
        <td><c:out value="${cache}" /></td>
        <td>
            <form method="post" action="refresh">
            <input type="hidden" name="cache" value='<c:out value="${cache}" />' />
            <input type="submit" value="Refresh" /></form>
        </td>
    </tr>
    <tr>
        <th>Current Size</th>
        <td colspan="2"><c:out value="${cacheSize}" /></td>
    </tr>
    <tr>
        <th>Statistics</th>
        <td colspan="2"><c:out value="${statistics}" /></td>
    </tr>
</table>

<jsp:directive.include file="../footer.jspf" /></div>
</body>
</html>