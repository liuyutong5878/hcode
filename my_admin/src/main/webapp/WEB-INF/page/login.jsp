<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>



<body>
	<div class="center-block" style="width:600px;background-color:#fff;margin-top:100px;padding-left:50px;">
		<form class="form-horizontal" role="form" action="/main/doLogin.htm">
		   <div class="form-group">
		      <label for="firstname" class="col-sm-2 control-label">UserName</label>
		      <div class="col-sm-6">
		         <input type="text" class="form-control" id="firstname" name="userName"
		            placeholder="userName">
		      </div>
		   </div>
		   <div class="form-group">
		      <label for="lastname" class="col-sm-2 control-label">Password</label>
		      <div class="col-sm-6">
		         <input type="text" class="form-control" id="lastname" name="password"
		            placeholder="password">
		      </div>
		   </div>
		   <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		         <div class="checkbox">
		            <label>
		               <input type="checkbox"> remeber me
		            </label>
		         </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		         <button type="submit" class="btn btn-default">Login</button>
		      </div>
		   </div>
		</form>
	</div>
</body>
</html>