<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<h1 align="center" style="font: normal; font-size: 20px; color: blue;">Step-3 : Select Quantity</h1>
	<c:choose>
		<c:when test="${products==null || products.isEmpty() }">
			<p style="font: normal; font-size: 30px; color: red;">No products found. Add some products!!</p>
		</c:when>
		<c:otherwise>
			<div align="center">
				<div style="display: inline-block; border: thin solid black; padding: 10px;">
					<form action="user?action=placeorder" method="post">
						<table border="1" cellspacing="5px" cellpadding="5px">
							<thead>
								<tr style="color: brown; font-weight: bold">
									<td>Product Id</td>
									<td>Name</td>
									<td>Description</td>
									<td>Cost</td>
									<td>Quantity</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${products }" var="product">
									<tr>
										<td>${product.id }</td>
										<td>${product.productName }</td>
										<td>${product.productDescription }</td>
										<td>${product.cost }</td>
										<td><input type="text" maxlength="3" size="3" name="pid${product.id }"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<button>Next>> Add Address</button>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>