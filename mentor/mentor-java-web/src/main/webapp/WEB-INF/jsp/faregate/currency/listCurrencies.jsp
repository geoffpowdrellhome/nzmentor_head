<?xml version="1.0" encoding="utf-8"?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:security="http://www.springframework.org/security/tags"
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
		</head>
		<body>
			<div id="main">
				<h1>Manage Currencies</h1>
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
								<th>Code</th>
								<th>Name</th>
								<th colspan="2">Default Markup<br /><small>(for user: <security:authentication property="principal.username" />)</small></th>
								<th>&#160;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${items}">
								<tr>
									<td>${item.code}</td>
									<td>${item.name}</td>
									<td>${item.defaultMarkupAmountBD} ${item.defaultMarkupType}</td>
									<td>${item.defaultMarkupActiveBoolean}</td>
									<td>[<a href="formCurrency?code=${item.code}">Edit</a>]</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5">[<a href="formCurrency">Add new Currency</a>]</td>
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