<?xml version="1.0" encoding="utf-8"?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	version="2.1">

	<jsp:directive.page
		language="java"
		contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"/>

	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="utf-8" ?> ]]>
	</jsp:text>

	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>

	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>FareGate - Airlines</title>
			<jsp:directive.include file="../header.jspf" />
		</head>
		<body>
			<div id="main">

				<h1>Airline - by ${sortBy} - ${letter} - </h1>

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
				<c:if test="${not empty confirmDeleteCode}">
					<div id="successMessages">
						Are you sure you want to delete Airline with code ${confirmDeleteCode}?&#160;
						<a href="delete?code=${confirmDeleteCode}&amp;sortBy=${sortBy}&amp;letter=${letter}">Yes</a>&#160;
						<a href="list?sortBy=${sortBy}&amp;letter=${letter}">No</a>
					</div>
				</c:if>

				<!--<c:if test="${not empty items}">-->
					<div>
						<table class="table">
							<thead>
								<tr>
									<th>Code</th>
									<th>Name</th>
									<th>&#160;</th>
									<th>&#160;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${items}">
									<tr>
										<td>${item.code}</td>
										<td>${item.name}</td>
										<td>[<a href="form?code=${item.code}&amp;sortBy=${sortBy}&amp;letter=${letter}">Edit</a>]</td>
										<td>[<a href="delete?code=${item.code}&amp;sortBy=${sortBy}&amp;letter=${letter}&amp;confirmDelete=true">Delete</a>]</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="4">[<a href="form?sortBy=${sortBy}&amp;letter=${letter}">Add new ${itemType}</a>]</td>
								</tr>
							</tbody>
						</table>
					</div>
				<!--</c:if>-->
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>