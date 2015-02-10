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
<script src="/js/mypage-nav.js"></script>
</head>
<body>
	<div class="search-bar">
		<form id="query-form">
			<input type="hidden" name="pageNow" id="pageNow"/>
		</form>
	</div>
	<table class="table table-bordered">
<%-- 	   <caption>用户列表</caption> --%>
	   <thead>
	      <tr>
	         <th>用户名</th>
	         <th>密码</th>
	         <th>邮箱</th>
	         <th>性别</th>
	         <th>操作</th>
	      </tr>
	   </thead>
	   <tbody id="tbody">
	   </tbody>
	</table>
	
	<div class="pageNav"></div>
</body>
<script type="text/javascript">
	$(function(){
		users.query();
		
	});
	
	var users = {
		query:function(pageNow){
			if(!pageNow) pageNow = 1;
			$("#pageNow").val(pageNow);
			$.ajax({
				type:'post',
				url:'/main/userList.htm',
				data:$("#query-form").serializeArray(),
				dataType:'json',
				success:function(pageObj){
					var htm = "";
					var data = pageObj["list"];
					for(var i=0; i<data.length;i++){
						htm += "<tr><td>"+data[i].userName+"</td><td>"+data[i].password+"</td><td>"+data[i].email+"</td>";
						htm += "<td>"+data[i].sex+"</td><td><a class='btn btn-default' href='#'>编辑</a></td></tr>";
					}
					$("#tbody").html(htm);
					$(".pageNav").pageNav(pageObj,users.query);
				},
				error:function(){
					alert("服务器内部出错");
				}
			});
		}
	}
	
	
</script>


</html>