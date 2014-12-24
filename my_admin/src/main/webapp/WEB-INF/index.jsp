<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<style type="text/css">
	.panel-primary{margin-left:20px;width: 200px;}
	#accordion{margin-right:5px;}
	.tabWrap{position:absolute;left:240px;top:63px;}
</style>
</head>
<body>
	<div class="panel panel-default" >
		<div class="panel-heading" style="height:40px;">
		</div>
	</div>
	
	<div id="accordion" class="panel-group">
		<div class="panel panel-primary">
		   <div class="panel-heading">
		   	<h3 class="panel-title">
		   		<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">第 1 部分</a>
		   	</h3>
		   	</div>
		   <ul class="list-group panel-collapse collapse in" id="collapseOne">
		      <li class="list-group-item">免费域名注册</li>
		      <li class="list-group-item">免费 Window 空间托管</li>
		      <li class="list-group-item">图像的数量</li>
		      <li class="list-group-item">24*7 支持</li>
		      <li class="list-group-item">每年更新成本</li>
		   </ul>
		</div>
		
		<div class="panel panel-primary">
		   <div class="panel-heading">
		   	<h3 class="panel-title">
		   		<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">第 2 部分</a>
		   	</h3>
		   </div>
		   <ul class="list-group panel-collapse collapse" id="collapseTwo">
		      <li class="list-group-item">免费域名注册</li>
		      <li class="list-group-item">免费 Window 空间托管空间托管空间托管</li>
		      <li class="list-group-item">图像的数量</li>
		      <li class="list-group-item">24*7 支持</li>
		      <li class="list-group-item">每年更新成本</li>
		   </ul>
		</div>
		
		<div class="panel panel-primary">
		   <div class="panel-heading">
		   	<h3 class="panel-title">
		   		<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">第 3 部分</a>
		   	</h3>
		   </div>
		   <ul class="list-group panel-collapse collapse" id="collapse3">
		      <li class="list-group-item">免费域名注册</li>
		      <li class="list-group-item">免费 Window 空间托管空间托管空间托管</li>
		      <li class="list-group-item">图像的数量</li>
		      <li class="list-group-item">24*7 支持</li>
		      <li class="list-group-item">每年更新成本</li>
		   </ul>
		</div>
	</div>
	
	<iframe style="border:solid 1px #ccc;display:none;" id="home">
		
	</iframe>
	
	<div class="tabWrap">
		<ul id="myTab" class="nav nav-tabs" style="border-bottom:none;">
			<li class="active">
		      <a href="#home" data-toggle="tab">
		         W3Cschool Home
		      </a>
	   		</li>
	   		<li >
		      <a href="#home2" data-toggle="tab">
		         hjsns
		      </a>
	   		</li>
		</ul>
	</div>
</body>
</html>