<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<c:choose>
		<c:when test="${coronaKit!=null }">
			<p>Name: ${coronaKit.getPersonName() }</p>
			<p>Email: ${coronaKit.getEmail() }</p>
			<p>Mobile: ${coronaKit.getContactNumber() }</p>
			<p>Total Amount: ${coronaKit.getTotalAmount() }</p>
		</c:when>
	</c:choose>

	<p></p>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>