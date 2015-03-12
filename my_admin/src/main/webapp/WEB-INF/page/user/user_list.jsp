<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/header.jsp" %>
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
						htm += "<td>"+data[i].sex+"</td><td><a class='btn btn-default' href='/main/"+data[i].id+"/userEdit.htm'>编辑</a>";
						htm += "<a class='btn btn-danger' href='javascript:users.del("+data[i].id+");'>删除</a></td></tr>";
					}
					$("#tbody").html(htm);
					$(".pageNav").pageNav(pageObj,users.query);
				},
				error:function(){
					alert("服务器内部出错");
				}
			});
		},
		del:function(userId){
			if(confirm("确认删除该用户吗?")){
				window.location.href="/main/"+userId+"/userDel.htm";
			}
		}
	}
	
	
</script>


</html>