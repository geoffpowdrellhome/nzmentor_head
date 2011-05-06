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
				function save()
				{
					var form = document.getElementById('rateForm');
					alert("save" +
							"\nbaseCode: " + form.baseCode.value +
							"\nquotCode: " + form.quotCode.value +
							"\nrate: " + form.rate.value +
							"\nrateOverride: " + form.rateOverride.value +
							"\nrateOverrideActive: " + form.rateOverrideActive.value +
							"\nrateMarkupAmount: " + form.rateMarkupAmount.value +
							"\nrateMarkupType: " + form.rateMarkupType.value +
							"\nrateMarkupActive: " + form.rateMarkupActive.value);
					form.submit();
				}
				function cancel()
				{
					var locHref = "listRates";
					//alert("cancel\nlocHref: " + locHref);
					location.href = locHref;
				}
			</script>
		</head>
		<body>
			<div id="main">
				<h1>${currencyForm.formType} Currency Rate</h1>
				<form:form commandName="rateForm" action="formRate" method="post">
					<div>
						<form:errors path="*" cssClass="errors" />
					</div>
					<div>
						<table>
							<tr>
								<td>
									<label for="baseCode">Base Code</label>
								</td>
								<td>
									<form:input path="baseCode" size="30" readonly="true" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="quotCode">Quote Code</label>
								</td>
								<td>
									<form:input path="quotCode" size="30" readonly="true" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="rate">Rate</label>
								</td>
								<td>
									<form:input path="rate" size="30" readonly="true" />
								</td>
							</tr>
							<tr>
								<th colspan="2">Rate Override<br /><small>(for user: <security:authentication property="principal.username" />)</small></th>
							</tr>
							<tr>
								<td>
									<label for="rateOverride">Override Amount</label>
								</td>
								<td>
									<form:input path="rateOverride" size="30" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="rateOverrideActive">Override Active?</label>
								</td>
								<td>
									<form:select path="rateOverrideActive">
										<form:option value="" label="" />
										<form:options items="${activeOptions}" itemValue="key" itemLabel="value"/>
    								</form:select>
								</td>
							</tr>
							<tr>
								<th colspan="2">Rate Markup<br /><small>(for user: <security:authentication property="principal.username" />)</small></th>
							</tr>
							<tr>
								<td>
									<label for="rateMarkupAmount">Markup Amount</label>
								</td>
								<td>
									<form:input path="rateMarkupAmount" size="30" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="rateMarkupType">Markup Type</label>
								</td>
								<td>
									<form:select path="rateMarkupType">
										<form:option value="" label="" />
										<form:options items="${markupTypes}" itemValue="key" itemLabel="value"/>
									</form:select>
								</td>
							</tr>
							<tr>
								<td>
									<label for="rateMarkupActive">Markup Active?</label>
								</td>
								<td>
									<form:select path="rateMarkupActive">
										<form:option value="" label="" />
										<form:options items="${activeOptions}" itemValue="key" itemLabel="value"/>
    								</form:select>
								</td>
							</tr>
							<tr>
								<td>&#160;</td>
								<td>[<a href="#" onclick="save();">Save</a>] [<a href="#" onclick="cancel();">Cancel</a>]</td>
							</tr>
						</table>
					</div>
				</form:form>
			</div>
		</body>
	</html>
</jsp:root>