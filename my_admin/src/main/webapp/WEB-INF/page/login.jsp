<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/images/cloude.png"/>
<title>音乐管理后台</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<style type="text/css">
	.form-signin{
		background-color:#fff;
		border:1px solid #e5e5e5;
		border-radius:5px;
		box-shadow:0 1px 2px rgba(0, 0, 0, 0.05);
		margin:20px auto;
		max-width:500px;
		padding:19px 29px 29px;
	}
</style>

</head>


<body>
	<div class="center-block" style="width:600px;background-color:#fff;margin-top:100px;padding-left:50px;">
		<form class="form-horizontal form-signin" role="form" action="/main/doLogin.htm">
		   <div class="form-group">
		      <label for="firstname" class="col-sm-2 control-label">用户名</label>
		      <div class="col-sm-6">
		         <input type="text" class="form-control" id="firstname" name="userName"
		            placeholder="userName">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="lastname" class="col-sm-2 control-label">密码</label>
		      <div class="col-sm-6">
		         <input type="password" class="form-control" id="lastname" name="password"
		            placeholder="password">
		      </div>
		   </div>
		   <!-- <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		         <div class="checkbox">
		            <label>
		               <input type="checkbox"> remeber me
		            </label>
		         </div>
		      </div>
		   </div> -->
		   <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		         <button type="submit" class="btn btn-primary">登陆</button>
		      </div>
		   </div>
		</form>
	</div>
</body>

<script type="text/javascript">

	$(function(){
		if('${msg}'){
			alert('${msg}');
		}
	});
	
</script>

</html>