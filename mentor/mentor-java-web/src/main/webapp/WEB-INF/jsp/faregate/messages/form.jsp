<?xml version="1.0" encoding="utf-8"?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:form="http://www.springframework.org/tags/form"
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
			<script language="javascript">
				function save()
				{
					var form = document.getElementById('messagesForm');
					form.submit();
				}
				function cancel()
				{
					location.href = "index";
				}
			</script>
		</head>
		<body>
			<div id="main">
				<div>
					<h1>${airlinesForm.formType} Message</h1>

					<form:form commandName="messagesForm" action="form" method="post">
                    
						<c:if test="${messagesForm.formType eq 'Edit'}">
							<form:hidden path="message.id" />
						</c:if>
                        
						<div>
							<form:errors path="*" cssClass="errors" />
						</div>
	
						<div>
							<table>
								<tr>
									<td>
										<label for="message.provider">Provider</label>
									</td>
									<td>
                                        <form:select path="message.provider">
                                            <form:options items="${providerList}" itemValue="code" itemLabel="fullName" />
                                        </form:select>
									</td>
								</tr>
								<tr>
									<td>
										<label for="message.regex">Regular Expression</label>
									</td>
									<td>
										<form:input path="message.regex" size="100" />
									</td>
								</tr>
                                <tr>
                                    <td>
                                        <label for="message.translation">Translation</label>
                                    </td>
                                    <td>
                                        <form:input path="message.translation" size="100" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="message.messageType">Message Type</label>
                                    </td>
                                    <td>
                                        <form:select path="message.messageType">
                                            <form:options items="${messageTypes}" itemValue="type" itemLabel="description" />
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="message.errorCode">Error Code</label>
                                    </td>
                                    <td>
                                        <form:select path="message.errorCode">
                                            <form:options items="${errorCodes}" itemValue="code" itemLabel="fullMessage" />
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="message.active">Active</label>
                                    </td>
                                    <td>
                                        <form:checkbox path="message.active" />
                                    </td>
                                </tr>
							</table>
						</div>
						<input type="button" value="Save" onclick="save();" />
						<input type="button" value="Cancel" onclick="cancel();" />
					</form:form>
					<br/>
				</div>
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>