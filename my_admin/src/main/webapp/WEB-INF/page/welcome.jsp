<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">
	.panel-primary{margin-left:20px;width: 260px;}
	#accordion{margin-right:5px; float:left;}
	.tabWrap{position:absolute;left:240px;top:63px;}
	
/* 	.container{margin-left:0px;margin-right: 0px;width: 1800px;} */
	
	.hlb_container{background-color:green;width:1000px;height:100%;}
	
</style>

<script type="text/javascript">
	$(function(){

	});
	
	function addTab(title, url){
		
		
	}
	
</script>

</head>
<body>
	<div class="panel panel-default" >
		<div class="panel-heading" style="height:40px;">
		</div>
	</div>
	
	<div class="row">
	  <div class="col-xs-12 col-sm-6 col-md-2"	>
		  	<div id="accordion" class="panel-group">
			<div class="panel panel-primary">
			   <div class="panel-heading">
			   	<h3 class="panel-title">
			   		<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">用户管理</a>
			   	</h3>
			   	</div>
			   <ul class="list-group panel-collapse collapse in" id="collapseOne">
			      <li class="list-group-item" ><a href="/main/userList.htm" target="rt_page">用户列表</a></li>
			      <li class="list-group-item"><a href="javascript:addTab('控件','http://www.baidu.com')">权限列表</a></li>
			   </ul>
			</div>
			
			<div class="panel panel-primary">
			   <div class="panel-heading">
			   	<h3 class="panel-title">
			   		<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">文件管理</a>
			   	</h3>
			   </div>
			   <ul class="list-group panel-collapse collapse" id="collapseTwo">
			      <li class="list-group-item"><a href="/file/list.htm" target="rt_page">文件列表</a></li>
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
	  </div>
	  <div class="col-xs-6 col-md-10">
	  	<iframe name="rt_page" style="border:none;" width="100%;" height="1000px;"></iframe>
	  </div>
	</div>
	
	
<!-- 	<div class="tabWrap">
		<ul id="myTab" class="nav nav-tabs" style="border-bottom:none;">
			<li class="active">
		      <a href="#home" data-toggle="tab">
		         welcome!
		      </a>
	   		</li>
		</ul>
	</div> -->
</body>
</html>