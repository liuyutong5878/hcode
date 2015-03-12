<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>榜单</title>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<link rel="stylesheet" type="text/css" href="../plugin/css/style.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/demo.css">
<link href="http://m.kugou.com/v2/static/css/style.css?20130320" rel="stylesheet" type="text/css"/>

<style>
	#pageone{
		background:url("/images/bg.jpg") repeat scroll 0 0 #444;
		background-size :100% 100%;
	}
	.m-row{height:30px;width:100%;margin:5px 0 5px 0;line-height:30px;border-bottom:#5F5757 1px solid;font-size:12px;font-family:KaiTi;}
	.mycontent{color:#fff;}
/* 	.go-back{color:#fff;position:absolute;left:0px;font-size:40px;line-height:20px;cursor:pointer;} */
</style>
</head>
<body>
<div data-role="page" id="pageone">
	  <div data-role="header">
	    <h1><span class="go-back" style="color:#fff;position:absolute;left:0px;font-size:40px;line-height:20px;cursor:pointer;">&lt</span>welcome home</h1>
	  </div>
	  <div class="mycontent" data-role="content">
		<h2 style="text-align:center;font-family:Microsoft YaHei;">${type.name}</h2>
		
		<c:forEach items="${musics}" var="music">
			<div class="m-row">${music.singer}-${music.name}<span style="float:right;"><img style="position:relative;top:7px;margin-right:10px;" src="/images/play.png" width="20px" height="20px"/><a href="${music.downloadUrl}" target="_self">下载</a></span></div>
		</c:forEach>
	  </div>
	</div> 
</body>
<script>
	
	$(function(){
		$(".go-back").bind("click",function(){
			history.go("-1");
		});
	});
	
	
</script>
</html>