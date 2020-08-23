<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<header>
	<h1 align="center">Product Management</h1>
	<hr />
	<nav>
		<div>
			<a href="index.jsp">Home</a> <span> | </span>
			<a href="newuser.jsp">Create New Kit</a> <span> | </span>
			<c:if test="${isAdminVerified!=null || isAdminVerified==true}">
				<a href="admin?action=list">View Products</a>
				<span> | </span>
				<a href="admin?action=newproduct">Add New Product</a>
				<div align="right">
					<a href="admin?action=logout">Log Out</a>
				</div>
			</c:if>
		</div>
	</nav>
	<hr />
</header>