<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3 align="center" style="font: normal; font-size: 20px; color: blue;">Step-1 : Enter Customer Details</h3>
	<div align="center">
		<div align="left" style="display: inline-block; border: thin solid black; padding: 10px;">
			<form action=user?action=insertuser method="post">
				<label style="display: table-cell;">Name:</label>
				<input type="text" maxlength="50" required name="usrName" type="text" />					
				<br> <br> 
				<label style="display: table-cell;">Email:</label>
				<input type="email" name="usrEmail" required/><br> <br> 
				<label style="display: table-cell;">Mobile:</label> 
				<input type="text" name="usrMobile"  required /><br> <br> 
				<button>Next>> Add Products</button>
				<br>
			</form>
		</div>
	</div>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>