<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">
	.row{background-color:#666; margin:5px 0px 0px 0px; height:30px;}
	#footer{width:100%;height:200px;background-color:#ccc;}
</style>
</head>
<body>

	<div class="navbar-wrapper">
      <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Project name</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li><a href="/index.jsp">首页</a></li>
                <li class="active"><a href="javascript:list();">发现音乐</a></li>
                <li><a href="#about">原创地带</a></li>
                <li><a href="#about">交流园区</a></li>
                <li><a href="#contact">分享</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">更多<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="#">注册</a></li>
                    <li><a href="#">登录</a></li>
                    <li><a href="#">建议</a></li>
                    <li><a href="#">关于我们</a></li>
                    <!-- <li class="divider"></li>
                    <li class="dropdown-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li> -->
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>

      </div>
    </div>

	<div id="my_content" class="container">
		<div class="row">
			<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
<!-- 				<audio src="/sound/song.mp3" controls="controls"></audio> -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
<!-- 				<audio src="/sound/song.mp3" controls="controls"></audio> -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
<!-- 				<audio src="/sound/song.mp3" controls="controls"></audio> -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
<!-- 				<audio src="/sound/song.mp3" controls="controls"></audio> -->
			</div>
		</div>
	</div>
	
	<div id="footer">
		
	</div>
</body>
</html>