<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<c:choose>
		<c:when test="${isAdminVerified==null || isAdminVerified==false}">
			<div align="center">
				<div>
					<br> <br> <br>
					<p style="font: normal; font-size: 30px; color: red;">
						You are not authorized to view this Page. Try <a href="index.jsp">Login</a> first.
					</p>
				</div>
			</div>

		</c:when>
		<c:otherwise>
			<h1 align="center" style="font: normal; font-size: 30px; color: blue;">Add Product</h1>
			<div align="center">
				<div align="left" style="display: inline-block; border: thin solid black; padding: 10px;">
					<form action="admin?action=insertproduct" method="post">
						<label style="display: table-cell;">Product Id:</label>
						<c:choose>
							<c:when test="${productId!=null }">
								<input type="text" maxlength="10" name="productId" type="number" readonly value="${productId }" />
							</c:when>
							<c:otherwise>
								<input type="text" maxlength="10"name="productId" type="number"/>
							</c:otherwise>
						</c:choose>
						<br> <br> <label style="display: table-cell;">Name:</label>
						<input type="text" name="productName" required maxlength="50" pattern="^[a-zA-Z]+[a-zA-Z ]*" 
								oninvalid="setCustomValidity('Please enter on alphabets only. ')" /><br> <br> 
						<label style="display: table-cell;">Cost:</label> 
						<input type="number" step="0.01" min="0.01" name="productPrice" required /><br> <br> 
						<label style="display: table-cell;">Description:</label>
						<input type="text" name="productDescription" maxlength="100" /><br> <br>
						<button>Add</button>
						<br>
					</form>

				</div>
				<c:if test="${addProductErrMsg!=null }">
					<p style="font: normal; font-size: 20px; color: red;">${addProductErrMsg }</p>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>