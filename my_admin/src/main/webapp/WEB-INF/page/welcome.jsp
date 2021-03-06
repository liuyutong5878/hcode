<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>音乐管理后台</title>
<%@ include file="/common/header.jsp" %>
<style type="text/css">
	.panel-primary{margin-left:20px;width: 260px;}
	#accordion{margin-right:5px; float:left;}
	.tabWrap{position:absolute;left:240px;top:63px;}
	.hlb_container{background-color:green;width:1000px;height:100%;}
	.active{color:red;}
</style>

<script type="text/javascript">
	$(function(){
		$(".list-group-item a").bind("click",function(){
			$(this).addClass("active");
			var _this = $(this);
			$(".list-group-item a").each(function(){
				if(_this.html() != $(this).html()){
					$(this).removeClass("active");
				}
			});
			
		});
		
	});
	
	function addTab(title, url){
		
	}
	
</script>

</head>
<body>
	<div class="panel panel-default" >
		<div class="panel-heading" style="height:40px;">
			你好！${sessionScope.user.userName}欢迎登陆 || 
			<a href="/main/logout.htm">退出</a>
			<embed style="width:80px;height:30px;" class="pull-right" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_wh.swf"/>
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
			      <li class="list-group-item" ><a href="/main/showUserList.htm" target="rt_page">用户列表</a></li>
			      <li class="list-group-item" ><a href="/singer/showList.htm" target="rt_page">歌手列表</a></li>
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
			      <li class="list-group-item"><a href="/music/showList.htm" target="rt_page">曲库管理</a></li>
			   </ul>
			</div>
			
			<div class="panel panel-primary">
			   <div class="panel-heading">
			   	<h3 class="panel-title">
			   		<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">第 3 部分</a>
			   	</h3>
			   </div>
			   <ul class="list-group panel-collapse collapse" id="collapse3">
			      <li class="list-group-item">图像的数量</li>
			      <li class="list-group-item">每年更新成本</li>
			   </ul>
			</div>
		</div>
	  </div>
	  <div class="col-xs-6 col-md-10">
	  	<iframe name="rt_page" id="pageFrame" style="border:none;" width="100%;" height="1000px;"></iframe>
	  </div>
	</div>
</body>
</html>