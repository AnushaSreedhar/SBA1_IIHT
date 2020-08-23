<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<footer>
	<div align="center">
		<c:if test="${txSucssMsg!=null}">
			<p align="center" style="font: normal; font-size: 20px; color: green;">${txSucssMsg}</p>
		</c:if>
		<c:if test="${txFailMsg!=null}">
			<p align="center" style="font: normal; font-size: 20px; color: red;">${txFailMsg}</p>
		</c:if>
	</div>
</footer>