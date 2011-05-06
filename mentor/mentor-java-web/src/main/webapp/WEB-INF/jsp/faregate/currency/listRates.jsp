<?xml version="1.0" encoding="utf-8"?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:security="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	version="2.1">

	<jsp:directive.page
		language="java"
		contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" />

	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="utf-8" ?> ]]>
	</jsp:text>

	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>FareGate - Currency</title>
			<jsp:directive.include file="../header.jspf" />
			<script language="javascript">
				function add() {
					//alert("add()");
					var baseCode = document.getElementById('baseCode').value;
					var quotCode = document.getElementById('quotCode').value;
					var locHrefStr = "formRate?baseCode="+baseCode+"&amp;quotCode="+quotCode+"&amp;formType=Add";
					//alert("going to: " + locHrefStr);
					document.location.href = locHrefStr;
				}
			</script>
		</head>
		<body>
			<div id="main">
				<h1>Manage Currency Rates</h1>
				<p><a href="index">&lt;&lt; Back to Currency Index Page</a></p>
				<c:if test="${not empty statusMessageKey}">
					<div id="successMessages">
					<ul>
						<c:forEach items="${statusMessageKey}" var="entry">
							<li>${entry}</li>
						</c:forEach>
					</ul>
					</div>
				</c:if>
				<c:if test="${not empty param.statusMessageKey}">
					<div id="successMessages">
					<ul>
						<li>${param.statusMessageKey}</li>
					</ul>
					</div>
				</c:if>
				<div>
					<table class="table">
						<thead>
							<tr>
								<th>Base</th>
								<th>Quote</th>
								<th colspan="2">Rate</th>
								<th colspan="2">Rate Override<br /><small>(for user: <security:authentication property="principal.username" />)</small></th>
								<th colspan="2">Rate Markup<br /><small>(for user: <security:authentication property="principal.username" />)</small></th>
								<th>&#160;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${items}">
								<tr>
									<td>${item.baseCurrCode}</td>
									<td>${item.quotCurrCode}</td>
									<td>${item.rateBD}</td>
									<td>[Refresh]</td>
									<td>${item.rateOverride}</td>
									<td>${item.rateOverrideActive}</td>
									<td>${item.rateMarkupAmount} ${item.rateMarkupType}</td>
									<td>${item.rateMarkupActive}</td>
									<td>[<a href="formRate?baseCode=${item.baseCurrCode}&amp;quotCode=${item.quotCurrCode}&amp;formType=Edit">Edit</a>]</td>
								</tr>
							</c:forEach>
							<tr>
								<td>
									<!--
									<form:form commandName="rateForm" action="formRate" method="get">
										<form:select path="baseCode" items="${ATTR_CURR_CODES}" />
										<form:select path="quotCode" items="${ATTR_CURR_CODES}" />
									-->
									<select id="baseCode" name="baseCode">
										<option value=""></option>
										<c:forEach var="currCode" items="${currCodes}">
											<option value="${currCode}">${currCode}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select id="quotCode" name="quotCode">
										<option value=""></option>
										<c:forEach var="currCode" items="${currCodes}">
											<option value="${currCode}">${currCode}</option>
										</c:forEach>
									</select>
								</td>
								<td colspan="6">
										[<a href="#" onclick="add();">Add</a>]
										<!--
										[<a href="formRate?formType=Add">Add</a>]
										<form:hidden path="formType" />
									</form:form>
										-->
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<p><a href="index">&lt;&lt; Back to Currency Index Page</a></p>
				<p>&#160;</p>
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>