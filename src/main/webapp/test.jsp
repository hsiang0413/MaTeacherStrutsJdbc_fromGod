<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
</head>
<body>
	<%@ page import="javax.naming.*, javax.sql.*" %>
	<%
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/java");
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM PRODUCT");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			out.println("id"+rs.getString(1)+"<br>");
			out.println("name"+rs.getString(2)+"<br>");
			out.println("price"+rs.getString(3)+"<br>");
			out.println("make"+rs.getString(4)+"<br>");
			out.println("expire"+rs.getString(5)+"<br>");
			out.println("----------------------"+"<br>");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
	%>
	
	<%
		Context ctx2 = new InitialContext();
		DataSource ds2 = (DataSource) ctx2.lookup("java:comp/env/jdbc/java");
		Connection conn2 = ds2.getConnection();
		PreparedStatement pstmt2 = conn2.prepareStatement("SELECT * FROM CUSTOMER");
		ResultSet rs2 = pstmt2.executeQuery();
		while(rs2.next()){
			out.println("custid"+rs2.getString(1)+"<br>");
			out.println("password"+rs2.getString(2)+"<br>");
		}
		
		rs2.close();
		pstmt2.close();
		conn2.close();
	%>
</body>
</html>