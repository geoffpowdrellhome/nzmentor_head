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
		import="net.flitech.fareinfo.web.LocationsController"/>

	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="utf-8" ?> ]]>
	</jsp:text>

	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>

	<c:set var="ITEM_TYPE_COUNTRY">
		<jsp:expression>LocationsController.ITEM_TYPE_COUNTRY</jsp:expression>
	</c:set>
	<c:set var="ITEM_TYPE_CITY">
		<jsp:expression>LocationsController.ITEM_TYPE_CITY</jsp:expression>
	</c:set>
	<c:set var="ITEM_TYPE_AIRPORT">
		<jsp:expression>LocationsController.ITEM_TYPE_AIRPORT</jsp:expression>
	</c:set>
    <c:set var="ITEM_TYPE_TERMINAL">
        <jsp:expression>LocationsController.ITEM_TYPE_TERMINAL</jsp:expression>
    </c:set>
	<c:set var="SORT_BY_CODE">
		<jsp:expression>LocationsController.SORT_BY_CODE</jsp:expression>
	</c:set>
	<c:set var="SORT_BY_NAME">
		<jsp:expression>LocationsController.SORT_BY_NAME</jsp:expression>
	</c:set>

	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>FareGate - Locations</title>
			<jsp:directive.include file="../header.jspf" />
		</head>
		<body>
			<div id="main">
				<h1>Locations</h1>
                <p>
                    <ul>
                        <li><b>Countries by Code</b><br/>
                            <c:forEach var="item" items="${aToZList}">
                                <a href="list?itemType=${ITEM_TYPE_COUNTRY}&amp;sortBy=${SORT_BY_CODE}&amp;letter=${item}">${item}</a>&#160;
                            </c:forEach>
                        </li>
                        <li><b>Countries by Name</b><br/>
                            <c:forEach var="item" items="${aToZList}">
                                <a href="list?itemType=${ITEM_TYPE_COUNTRY}&amp;sortBy=${SORT_BY_NAME}&amp;letter=${item}">${item}</a>&#160;
                            </c:forEach>
                        </li>
                    </ul>
                </p>
                <p>
                    <ul>
                        <li><b>Cities by Code</b><br/>
                            <c:forEach var="item" items="${aToZList}">
                                <a href="list?itemType=${ITEM_TYPE_CITY}&amp;sortBy=${SORT_BY_CODE}&amp;letter=${item}">${item}</a>&#160;
                            </c:forEach>
                        </li>
                        <li><b>Cities by Name</b><br/>
                            <c:forEach var="item" items="${aToZList}">
                                <a href="list?itemType=${ITEM_TYPE_CITY}&amp;sortBy=${SORT_BY_NAME}&amp;letter=${item}">${item}</a>&#160;
                            </c:forEach>
                        </li>
                    </ul>
                </p>
                <p>
                    <ul>
                        <li><b>Airports by Code</b><br/>
                            <c:forEach var="item" items="${aToZList}">
                                <a href="list?itemType=${ITEM_TYPE_AIRPORT}&amp;sortBy=${SORT_BY_CODE}&amp;letter=${item}">${item}</a>&#160;
                            </c:forEach>
                        </li>
                        <li><b>Airports by Name</b><br/>
                            <c:forEach var="item" items="${aToZList}">
                                <a href="list?itemType=${ITEM_TYPE_AIRPORT}&amp;sortBy=${SORT_BY_NAME}&amp;letter=${item}">${item}</a>&#160;
                            </c:forEach>
                        </li>
                    </ul>
                </p>
				<p>
					<ul>
						<li><b>Terminals by Airport Code</b><br/>
							<c:forEach var="item" items="${aToZList}">
								<a href="list?itemType=${ITEM_TYPE_TERMINAL}&amp;sortBy=${SORT_BY_CODE}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
						</li>
						<li><b>Terminals by Airport Name</b><br/>
							<c:forEach var="item" items="${aToZList}">
								<a href="list?itemType=${ITEM_TYPE_TERMINAL}&amp;sortBy=${SORT_BY_NAME}&amp;letter=${item}">${item}</a>&#160;
							</c:forEach>
						</li>
					</ul>
				</p>
				<jsp:directive.include file="../footer.jspf" />
			</div>
		</body>
	</html>
</jsp:root>