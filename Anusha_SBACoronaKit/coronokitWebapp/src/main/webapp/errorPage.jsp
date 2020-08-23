<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Error</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div>
		<h3>Something went wrong! We regret the inconvenience!</h3>
		<p style="font: normal; font-size: 20px; color: red;">
			Error Message :
			<c:choose>
				<c:when test="${exception!=null }">
					${exception.getMessage() }
				</c:when>
				<c:when test="${exMsg!=null }">
					${exMsg }
				</c:when>
				<c:otherwise>
					Unknown Error !!
				</c:otherwise>
			</c:choose>
		</p>
		<p>Please Contact Administrator</p>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>