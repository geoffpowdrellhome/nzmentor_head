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

	<jsp:directive.page
		import="net.flitech.fareinfo.web.CurrencyController" />

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
					var form = document.getElementById('currencyForm');
					alert("save" +
							"\ncode: " + form.code.value +
							"\nname: " + form.name.value +
							"\namount: " + form.defaultMarkupAmount.value +
							"\ntype: " + form.defaultMarkupType.value +
							"\nactive: " + form.defaultMarkupActive.value);
					form.submit();
				}
				function cancel()
				{
					var locHref = "listCurrencies";
					//alert("cancel\nlocHref: " + locHref);
					location.href = locHref;
				}
			</script>
		</head>
		<body>
			<div id="main">
				<h1>${currencyForm.formType} Currency</h1>
				<form:form commandName="currencyForm" action="formCurrency" method="post">
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
										<c:when test="${currencyForm.formType eq 'Edit'}">
											<!--<form:input path="code" size="30" disabled="true"/>-->
											<!-- if setting to disabled, then need to add hidden inputs to get value to come through.
												cleaner to use readonly. -->
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
							<tr>
								<th colspan="2">Default Markup<br /><small>(for user: <security:authentication property="principal.username" />)</small></th>
							</tr>
							<tr>
								<td>
									<label for="defaultMarkupAmount">Amount</label>
								</td>
								<td>
									<form:input path="defaultMarkupAmount" size="30" maxlength="20" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="defaultMarkupType">Markup Type</label>
								</td>
								<td>
									<form:select path="defaultMarkupType">
										<form:option value="" label="" />
										<form:options items="${markupTypes}" itemValue="key" itemLabel="value"/>
									</form:select>
									<!--
									<form:select path="childSpecific">
        								<form:option value="Y">true</form:option>
		        						<form:option value="N">false</form:option>
    								</form:select>
									-->
								</td>
							</tr>
							<tr>
								<td>
									<label for="defaultMarkupActive">Markup Active?</label>
								</td>
								<td>
									<!--
									<form:select path="defaultMarkupActive" items="${}" />
									-->
									<form:select path="defaultMarkupActive">
										<!--
        								<form:option value="true">Yes</form:option>
		        						<form:option value="false">No</form:option>
										-->
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