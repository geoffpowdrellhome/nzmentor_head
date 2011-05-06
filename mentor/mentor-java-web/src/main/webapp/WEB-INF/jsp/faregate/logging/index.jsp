<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Logging :: index</title>
<jsp:directive.include file="../header.jspf" />
</head>

<body>
<div id="main">
<p>Hi. Here, you can administer logging levels for <strong>FareGate</strong>.</p>
<hr/>
Levels: ${levels}
<hr/>
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
<script type="text/javascript">
  var allSelects;
  dojo.addOnLoad(function(){
    // cache this query on page load since otherwise it's hella slow.  
    allSelects = dojo.query('#tableWithForms select'); 
  });
  function submitAndDisable(id) {
    dojo.byId(id).submit();
    allSelects.forEach(
      function(el){ el.disabled = 'disabled'; }
    )
  }
</script>
<table class="table" id="tableWithForms">
  <tr>
    <th>Logger</th>
    <th colspan="2">Level</th>
  </tr>
  <c:forEach var="logger" items="${loggers}" varStatus="status">
    <tr id="logger.${logger.key}" <c:if test="${status.index % 2 eq 1}"> class="even"</c:if>>
      <td><c:out value="${logger.key}" /></td>
      <td>
      <form method="post" action="update" id="update${status.index}"><input type="hidden" name="name"
        value='<c:out value="${logger.key}" />' /> <select name="level"
        onchange="submitAndDisable('update${status.index}');">
        <!-- onchange="document.getElementById('update${status.index}').submit()" -->
        <c:forEach var="level" items="${levels}">
          <option value='<c:out value="${level}" />' <c:if test="${logger.value eq level}"> selected="selected"</c:if>><c:out
            value="${level}" /></option>
        </c:forEach>
      </select></form>
      </td>
      <td class="level${logger.value}">&nbsp;</td>
    </tr>
    <c:if test="${logger.key eq param.updatedLogger}">
      <script type="text/javascript">
      var highlight = dojo.animateProperty(
          {
            node: "logger.${logger.key}", duration: 1000,
            properties: { backgroundColor: { start: "white", end: "#FFCC66" } }
          });
      var fade = dojo.animateProperty(
            {
              node: "logger.${logger.key}", duration: 500,
              properties: { backgroundColor: { start: "#FFCC66", end: "#FFDD66" } }
            });
      var currentAnimation = dojo.fx.chain([highlight, fade]);
      dojo.addOnLoad(function(){  currentAnimation.play(); });
    </script>
    </c:if>
  </c:forEach>
</table>

<jsp:directive.include file="../footer.jspf" /></div>
</body>
</html>