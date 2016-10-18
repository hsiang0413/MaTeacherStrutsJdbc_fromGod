<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<h3>
		Welcome,
		<s:if test="#session.username neq null">
			${sessionScope.username}
		</s:if>
		<s:else>
			Guest
		</s:else>
	</h3>

	<h3>
		<a href="<%=request.getContextPath()%>/secure/login.jsp">Login</a>
	</h3>
	<h3>
		<a href="<%=request.getContextPath()%>/pages/product.jsp">Product</a>
	</h3>

</body>
</html>