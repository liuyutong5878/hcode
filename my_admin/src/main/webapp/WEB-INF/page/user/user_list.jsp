<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>

	<table class="table table-bordered">
	   <caption>用户列表</caption>
	   <thead>
	      <tr>
	         <th>用户名</th>
	         <th>密码</th>
	         <th>邮箱</th>
	         <th>性别</th>
	      </tr>
	   </thead>
	   <tbody>
	      <c:forEach items="${ulist}" var="user">
	      	<tr>
	      		<td>${user.userName}</td>
	      		<td>${user.password}</td>
	      		<td>${user.email}</td>
	      		<td>${user.sex}</td>
	      	</tr>
	      </c:forEach>
	   </tbody>
	</table>

</body>
</html>