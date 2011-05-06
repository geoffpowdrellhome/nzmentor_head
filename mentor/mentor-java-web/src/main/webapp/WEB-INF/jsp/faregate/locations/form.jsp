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
			<title>FareGate - Locations</title>
			<jsp:directive.include file="../header.jspf" />
			<script language="javascript">
				function save()
				{
					var form = document.getElementById('locationsForm');
					/*
					alert("save\nitemType: " + form.itemType.value + "\ncode: " + form.code.value + "\nname: " + form.name.value +
						//"\nparentCode: " + form.parentCode.value +
						"\nsortBy: " + form.sortBy.value + "\nletter: " + form.letter.value);
					*/
					form.submit();
				}
				function cancel()
				{
					var locHref = "list?itemType=${locationsForm.itemType}&amp;sortBy=${locationsForm.sortBy}&amp;letter=${locationsForm.letter}";
					//alert("locHref\n" + locHref);
					location.href = locHref;
				}
			</script>
		</head>
		<body>
			<div id="main">
				<div>
					<h1>${locationsForm.formType} ${locationsForm.itemType}</h1>
	
					<c:set var="parentLabel">
						<c:choose>
                            <c:when test="${locationsForm.itemType eq 'terminal'}">
                                Airport
                            </c:when>
							<c:when test="${locationsForm.itemType eq 'airport'}">
								City
							</c:when>
							<c:when test="${locationsForm.itemType eq 'city'}">
								Country
							</c:when>
						</c:choose>
					</c:set>
	
					<form:form commandName="locationsForm" action="form" method="post">
						<form:hidden path="itemType"/>
						<form:hidden path="sortBy"/>
						<form:hidden path="letter"/>
	
						<div>
							<form:errors path="*" cssClass="errors" />
						</div>
	
						<div>
							<table>
								<c:if test="${locationsForm.itemType eq 'terminal' or locationsForm.itemType eq 'airport' or locationsForm.itemType eq 'city'}">
									<tr>
										<td>
											<label for="parentCode">Parent ${parentLabel} Code</label>
										</td>
										<td>
											<c:choose>
												<c:when test="${locationsForm.formType eq 'Edit'}">
													<!--<form:input path="parentCode" size="30" disabled="true"/>-->
													<!-- if setting to disabled, then need to add hidden inputs to get value to come through.
														cleaner to use readonly. -->
													<form:input path="parentCode" size="30" readonly="true" />
												</c:when>
												<c:otherwise>
													<form:input path="parentCode" size="30" maxlength="3" />
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:if>
								<tr>
									<td>
										<label for="code">Code</label>
									</td>
									<td>
										<c:choose>
											<c:when test="${locationsForm.formType eq 'Edit'}">
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
										<form:input path="name" size="30" maxlength="100" />
									</td>
								</tr>
                                <c:if test="${locationsForm.itemType eq 'terminal'}">
                                    <tr>
                                        <td>
                                            <label for="type">Type</label>
                                        </td>
                                        <td>
                                            <form:select path="type" items="${terminalTypes}" itemValue="type" itemLabel="name" />
                                        </td>
                                    </tr>
                                </c:if>
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