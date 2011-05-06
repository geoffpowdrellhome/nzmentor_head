<?xml version="1.0" encoding="utf-8"?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	version="2.1">

	<jsp:directive.page
		language="java"
		contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"/>

	<jsp:directive.page
		import="net.flitech.fareinfo.web.AirlineFareDetailsController"/>

	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="utf-8" ?> ]]>
	</jsp:text>

	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>

	<c:set var="SORT_BY_AIRLINE">
		<jsp:expression>AirlineFareDetailsController.SORT_BY_AIRLINE</jsp:expression>
	</c:set>
	<c:set var="SORT_BY_FARE_BASIS">
		<jsp:expression>AirlineFareDetailsController.SORT_BY_FARE_BASIS</jsp:expression>
	</c:set>
	<c:set var="SORT_BY_FARE_CLASS">
		<jsp:expression>AirlineFareDetailsController.SORT_BY_FARE_CLASS</jsp:expression>
	</c:set>

	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>FareGate - Airline Fare Details</title>
			<jsp:directive.include file="../header.jspf" />
		</head>
		<body>
			<div id="main">
				<h1>Airline Fare Details</h1>
				<p>
					<ul>
						<li><b>By Airline</b><br/>
							<c:forEach var="item" items="${aToZList}">
								<a href="list?sortBy=${SORT_BY_AIRLINE}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
							<br/>
							<c:forEach var="item" items="${zeroToNineList}">
								<a href="list?sortBy=${SORT_BY_AIRLINE}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
						</li>
						<li><b>By Fare Basis</b><br/>
							<c:forEach var="item" items="${aToZList}">
								<a href="list?sortBy=${SORT_BY_FARE_BASIS}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
							<br/>
							<c:forEach var="item" items="${zeroToNineList}">
								<a href="list?sortBy=${SORT_BY_FARE_BASIS}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
						</li>
						<li><b>By Fare Class</b><br/>
							<c:forEach var="item" items="${aToZList}">
								<a href="list?sortBy=${SORT_BY_FARE_CLASS}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
							<br/>
							<c:forEach var="item" items="${zeroToNineList}">
								<a href="list?sortBy=${SORT_BY_FARE_CLASS}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
						</li>
					</ul>
				</p>
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>