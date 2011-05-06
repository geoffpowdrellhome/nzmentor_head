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
			<title>FareGate - Airlines</title>
			<jsp:directive.include file="../header.jspf" />
			<script language="javascript">
				function save()
				{
					var form = document.getElementById('airlinesForm');
					/*
					alert("save\ncode: " + form.code.value + "\nname: " + form.name.value +
						"\nsortBy: " + form.sortBy.value + "\nletter: " + form.letter.value);
					//*/
					form.submit();
				}
				function cancel()
				{
					var locHref = "list?sortBy=${airlinesForm.sortBy}&amp;letter=${airlinesForm.letter}";
					//alert("locHref\n" + locHref);
					location.href = locHref;
				}
			</script>
		</head>
		<body>
			<div id="main">
				<div>
					<h1>${airlinesForm.formType} Airline</h1>

					<form:form commandName="airlinesForm" action="form" method="post">
						<form:hidden path="sortBy"/>
						<form:hidden path="letter"/>
	
						<div>
							<form:errors path="*" cssClass="errors" />
						</div>
	
						<div>
							<table>
								<tr>
									<td>
										<label for="code">Code</label>
									</td>
									<td>
										<c:choose>
											<c:when test="${airlinesForm.formType eq 'Edit'}">
												<form:input path="code" size="30" readonly="true" />
											</c:when>
											<c:otherwise>
												<form:input path="code" size="30" maxlength="3" />
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td>
										<label for="name">Name</label>
									</td>
									<td>
										<form:input path="name" size="30" maxlength="64" />
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