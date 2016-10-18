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

<title>Product</title>
<script type="text/javascript">
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
	}
</script>
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

	<h3>Product Table</h3>
	
	<s:fielderror></s:fielderror>
	
	<s:form action="prodAction" namespace="/myNamespace">
		<table>
			<tr>
				<td colspan="2"><s:textfield name="bean.id" label="ID" value="%{#parameters['bean.id']}" /></td>
			</tr>
			<tr>
				<td colspan="2"><s:textfield name="bean.name" label="Name" value="%{#parameters['bean.name']}" /></td>
			</tr>
			<tr>
				<td colspan="2"><s:textfield name="bean.price" label="Price" value="%{#parameters['bean.price']}" /></td>
			</tr>
			<tr>
				<td colspan="2"><s:textfield name="bean.make" label="Make" value="%{#parameters['bean.make']}" /></td>
			</tr>
			<tr>
				<td colspan="2"><s:textfield name="bean.expire" label="Expire" value="%{#parameters['bean.expire']}" /></td>
			</tr>
			<tr>
				<td colspan="2">
					
					<!-- ProductAction使用 (分為4個method) -->
					<s:submit value="Insert" name="production" method="prodInsert" theme="simple" />
					<s:submit value="Update" name="production" method="prodUpdate" theme="simple" />
					<s:submit value="Delete" name="production" method="prodDelete" theme="simple" />
					<s:submit value="Select" name="production" method="prodSelect" theme="simple" />
					<input type="button" value="Clear" onclick="clearForm()">
					
					<!-- ProductAction2使用 (只有execute method) -->
					<!--
					<s:submit value="Insert" name="production" theme="simple" />
					<s:submit value="Update" name="production" theme="simple" />
					<s:submit value="Delete" name="production" theme="simple" />
					<s:submit value="Select" name="production" theme="simple" />
					<input type="button" value="Clear" onclick="clearForm()">
					 -->
					 
				</td>
			</tr>
		</table>
	</s:form>
	
	<hr/>
	
	<s:if test="#request.result neq null">	
		${requestScope.result}
	</s:if>
</body>
</html>