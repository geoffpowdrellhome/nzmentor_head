<?xml version="1.0" encoding="utf-8"?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
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
		</head>
		<body>
			<div id="main">
				<h1>Currency</h1>
				<ul>
					<li><a href="listCurrencies">Manage Currencies</a><small> (Add / Edit Currencies and Default Currency Markups)</small></li>
					<li><a href="listRates">Manage Currency Rates</a><small> (Add / Edit Currency Conversion Rates, Overrides and Markups)</small></li>
					<li><a href="#">View Currency Default Markup History</a><small> (View Audit Information)</small></li>
					<li><a href="#">View Currency Rate History</a><small> (View Audit Information)</small></li>
					<li><a href="#">View Currency Rate Markup History</a><small> (View Audit Information)</small></li>
					<li><a href="#">View Currency Rate Override History</a><small> (View Audit Information)</small></li>
				</ul>
				<p>&#160;</p>
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>