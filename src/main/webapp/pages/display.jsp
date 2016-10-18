<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>Display</title>
</head>
<body>
	<h3>Select Product Table Result : <s:property value="#request.result.size()" /> row(s) selected</h3>
	<s:if test="#request.result neq null">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
					<th>Make</th>
					<th>Expire</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.result}">
					<tr>
						<td>${bean.id}</td>
						<td>${bean.name}</td>
						<td>${bean.price}</td>
						<td>${bean.make}</td>
						<td>${bean.expire}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</s:if>
	<h3>
		<a href="<%=request.getContextPath()%>/pages/product.jsp">Product Table</a>
	</h3>
</body>
</html>