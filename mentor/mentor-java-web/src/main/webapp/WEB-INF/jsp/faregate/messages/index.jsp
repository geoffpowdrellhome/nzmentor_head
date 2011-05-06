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
			<title>FareGate - Messages</title>
			<jsp:directive.include file="../header.jspf" />
		</head>
		<body>
			<div id="main">

				<h1>Messages</h1>

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
						Are you sure you want to delete Message with regular expression ${messagesForm.message.regex}?&#160;
						<a href="delete?id=${messagesForm.message.id}">Yes</a>&#160;
						<a href="index">No</a>
					</div>
				</c:if>

					<div>
						<table class="table">
							<thead>
								<tr>
									<th>Provider</th>
									<th>Regular Expression</th>
                                    <th>Translation</th>
                                    <th>Message Type</th>
                                    <th>Error Code</th>
                                    <th>Active</th>
									<th>&#160;</th>
									<th>&#160;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${items}">
									<tr>
										<td>${item.provider.name} (${item.provider.code})</td>
										<td>${item.regex}</td>
                                        <td>${item.translation}</td>
                                        <td>${item.messageType.description}</td>
                                        <td>${item.errorCode.fullMessage}</td>
                                        <td>${item.activeString}</td>
										<td>[<a href="form?id=${item.id}">Edit</a>]</td>
										<td>[<a href="delete?id=${item.id}&amp;confirmDelete=true">Delete</a>]</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="4">[<a href="form">Add new Message</a>]</td>
								</tr>
							</tbody>
						</table>
					</div>
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>