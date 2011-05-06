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
			<title>FareGate - Airline Fare Details</title>
			<jsp:directive.include file="../header.jspf" />
			<script language="javascript">
				function save()
				{
					var form = document.getElementById('airlineFareDetailsForm');
					form.submit();
				}
				function cancel()
				{
					var locHref = "list?sortBy=${airlineFareDetailsForm.sortBy}&amp;letter=${airlineFareDetailsForm.letter}";
					location.href = locHref;
				}
			</script>
		</head>
		<body>
			<div id="main">
				<div>
					<h1>${airlineFareDetailsForm.formType} Airline Fare Details</h1>

					<form:form commandName="airlineFareDetailsForm" action="form" method="post">
						<form:hidden path="sortBy"/>
						<form:hidden path="letter"/>
						<form:hidden path="id"/>
	
						<div>
							<form:errors path="*" cssClass="errors" />
						</div>
	
						<div>
							<table>
								<tr>
									<td>
										<label for="airlineCode">Airline Code</label>
									</td>
									<td>
										<form:select path="airlineCode" items="${airlines}" itemValue="code" itemLabel="code"/> <form:errors path="airlineCode" cssClass="errors"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="fareBasis">Fare Basis</label>
									</td>
									<td>
										<form:input path="fareBasis" size="30"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="fareFamily">Fare Family</label>
									</td>
									<td>
										<form:input path="fareFamily" size="30"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="fareClass">Fare Class</label>
									</td>
									<td>
										<form:input path="fareClass" size="30"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="cabinClass">Cabin Class</label>
									</td>
									<td>
										<form:select path="cabinClass" items="${cabinClasses}" itemValue="classCode" itemLabel="className"/> <form:errors path="cabinClass" cssClass="errors"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="childSpecific">Child Specific</label>
									</td>
									<td>
										<form:select path="childSpecific">
        									<form:option value="Y">true</form:option>
		        							<form:option value="N">false</form:option>
    									</form:select>
									</td>
								</tr>								
								<tr>
									<td>
										<label for="advanceDays">Advance Days</label>
									</td>
									<td>
										<form:input path="advanceDays" size="30"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="active">Active</label>
									</td>
									<td>
										<form:select path="active">
        									<form:option value="Y">true</form:option>
		        							<form:option value="N">false</form:option>
    									</form:select>
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