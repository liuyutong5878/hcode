<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户信息</title>
<%@ include file="/common/header.jsp" %>
</head>
<body>
	<form class="form-horizontal" action="/main/userSave.htm" role="form" id="form" method="post" style="border-left:1px solid #ccc;">
	   <div class="form-group">
	      <label for="firstname" class="col-sm-2 control-label">用户名</label>
	      <div class="col-sm-4">
	      	 <input type="hidden" name="id" value="${user.id}"/>
	         <input type="text" class="form-control" name="userName" value="${user.userName}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">密码</label>
	      <div class="col-sm-4">
	         <input type="text" class="form-control" name="password" value="${user.password}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">邮箱</label>
	      <div class="col-sm-4">
			<input type="text" class="form-control" name="email" value="${user.email}">
	      </div>
	   </div>
  	     <div class="form-group">
	   	<label for="lastname" class="col-sm-2 control-label">性别</label>
	      <div class="col-sm-4">
	         <div class="radio">
			   <label>
			      <input type="radio" name="sex" id="male" value="1" ${user.sex == 1 ? 'checked=checked' : ''}>男
			   </label>
			   <label>
			      <input type="radio" name="sex" id="female" value="2" ${user.sex == 2 ? 'checked=checked': ''}>女
			   </label>
			</div>
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label" style="padding-top:0px;">
	      	<a class="btn btn-default" onclick="javascript:history.go(-1)">返回</a>
	      </label>
	      <div class="col-sm-4">
	      	<a class="btn btn-primary btn-save">保存</a>
	      </div>
	   </div>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$(".btn-save").bind("click",function(){
			$("#form").submit();
		});
	});
	
	
</script>


</html>