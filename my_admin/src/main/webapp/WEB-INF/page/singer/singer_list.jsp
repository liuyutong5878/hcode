<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Singer list</title>
<%@ include file="/common/header.jsp" %>
</head>
<body>
	<div class="search-bar">
		<form id="query-form">
			<input type="hidden" id="pageNow" name="pageNow"/>
			<input type="text" name="singerName" placeHolder="请输入歌手名"/>
			<a class="btn btn-primary btn-query">查询</a>
		</form>
	</div>
	<table class="table table-bordered">
	   <thead>
	      <tr>
	         <th>名字</th>
	         <th>英文名</th>
	         <th>性别</th>
	         <th>类型</th>
	         <th>所属国家</th>
	         <th>所属地区</th>
	         <th>简介</th>
	         <th>是否置顶</th>
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
		singers.query();
		$(".btn-query").bind("click",function(){
			singers.query();
		});
		
		$("#query-form").live("keypress",function(e){
			if(e.keyCode == 13) e.preventDefault();
			$(".btn-query").click();
		});
		
	});
	
	var singers = {
		query:function(pageNow){
			if(!pageNow) pageNow = 1;
			$("#pageNow").val(pageNow);
			$.ajax({
				cache:false,
				type:'post',
				url:'/singer/list.htm',
				data:$("#query-form").serializeArray(),
				dataType:'json',
				success:function(pageObj){
					var htm = "";
					var data = pageObj["list"];
					for(var i=0; i<data.length;i++){
						htm += "<tr><td>"+data[i].name+"</td><td>"+data[i].englishName+"</td><td>"+data[i].sex+"</td>";
						htm += "<td>"+data[i].stype+"</td><td>"+data[i].country+"</td><td>"+data[i].district+"</td><td>"+data[i].profile+"</td>";
						htm += "<td>"+data[i].isHot+"</td><td><a class='btn btn-default' href='/singer/"+data[i].id+"/edit.htm'>编辑</a>";
						htm += "<a class='btn btn-default' onclick='singers.setHot("+data[i].id+")'>置顶</a></td></tr>";
					}
					$("#tbody").html(htm);
					$(".pageNav").pageNav(pageObj,singers.query);
				},
				error:function(){
					alert("服务器内部出错");
				}
			});
		},
		setHot:function(ids){
			if(!confirm("确定要设为置顶吗？")) return;
			$.ajax({
				type:'post',
				url:'/singer/'+ids+'/setHot.htm',
				dataType:'json',
				success:function(data){
					alert(data);
					singers.query();
				},
				error:function(){
					alert("服务器内部出错");
				}
			});
		}
	}
	
	
</script>


</html>