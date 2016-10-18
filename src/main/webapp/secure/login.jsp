<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<s:head theme="xhtml" />
<sx:head />

<link rel="stylesheet" type="text/css" href="" />

<title>Login</title>
</head>
<body>

	<h3>
		Login
		<s:if test="#request.username neq null">
			${requestScope.result}
		</s:if>
	</h3>
	
<%-- 	<s:fielderror></s:fielderror> --%>
	
	<s:form action="loginAction" namespace="/myNamespace">
		<s:textfield name="username" label="ID"></s:textfield>
		<s:password name="password" label="PWD"></s:password>
		<s:submit value="login"></s:submit>
	</s:form>
	
</body>
</html>