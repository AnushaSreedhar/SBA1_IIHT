<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<h1 align="center" style="font: normal; font-size: 20px; color: blue;">Step-2 : Select Products</h1>
	<c:choose>
		<c:when test="${products==null || products.isEmpty() }">
			<p style="font: normal; font-size: 30px; color: red;">No products found. Add some products!!</p>
		</c:when>
		<c:otherwise>
			<div align="center">
				<div style="display: inline-block; border: thin solid black; padding: 10px;">
						<table border="1" cellspacing="5px" cellpadding="5px">
							<thead>
								<tr style="color: brown; font-weight: bold">
									<td>Product Id</td>
									<td>Name</td>
									<td>Description</td>
									<td>Price</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${products }" var="product">
									<tr>
										<td>${product.id }</td>
										<td>${product.productName }</td>
										<td>${product.productDescription }</td>
										<td>${product.cost }</td>
										<td><c:choose>
												<c:when test="${addedProducts!=null }">
													<c:choose>
														<c:when test="${!addedProducts.contains(product.id)}">
															<a href="user?action=addnewitem&productid=${product.id }"><button>Add</button></a>
															<a href="user?action=deleteitem&productid=${product.id }"><button disabled>Delete</button></a>
														</c:when>
														<c:otherwise>
															<a href="user?action=addnewitem&productid=${product.id }"><button disabled>Add</button></a>
															<a href="user?action=deleteitem&productid=${product.id }"><button>Delete</button></a>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<a href="user?action=addnewitem&productid=${product.id }"><button>Add</button></a>
													<a href="user?action=deleteitem&productid=${product.id }"><button disabled>Delete</button></a>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
					<a href="user?action=showkit"><button>Next>> Add Quantities</button></a></td>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${txErrMsg!=null}">
		<p style="font: normal; font-size: 20px; color: red;">${txErrMsg }</p>
	</c:if>
	<c:if test="${txSucssMsg!=null}">
		<p align="center" style="font: normal; font-size: 20px; color: green;">${txSucssMsg }</p>
	</c:if>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>