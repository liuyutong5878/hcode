<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
	<button class="btn btn-primary upd-lib">更新类库</button>
	<form id="query-form">
		<input type="hidden" name="pageNow" id="pageNow" value="1"/>
	</form>
  	<div class="toolBar">
  		<label>
  			<input type="checkbox" id="checkAll"/>
  			全选
  		</label>
  	<a href="javascript:mpg.batchAddToType();">添加到类别</a>
  	<a href="javascript:mpg.batchDel()">删除</a>
  	</div>
	<table class="table table-bordered table-hover">
<%-- 	   <caption>曲库</caption> --%>
	   <thead>
	      <tr>
	      	 <th>选择</th>
	         <th>歌曲名</th>
	         <th>格式</th>
	         <th>时长</th>
	         <th>歌手</th>
	         <th>相对路径</th>
	         <th>下载地址</th>
	         <th>添加时间</th>
	         <th>操作</th>
	      </tr>
	   </thead>
	   <tbody id="tbody">
	   </tbody>
	</table>
	<div class="pageNav pull-right" style="background-color:#ccc;width:360px;height:20px;text-align:center;">
	</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              	添加到类库
            </h4>
         </div>
         <form id="typeForm">
	         <div class="modal-body">
	         	<select name="typeId" id="m-type" style="width:200px;height:30px;">
	         		
	         	</select>
	         </div>
	         <input type="hidden" name="musicId" id="musicId"/>
         </form>
         <div class="modal-footer">
            <button type="button" class="btn btn-default btn-close" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" onclick="javascript:mpg.submitType()">提交更改</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->

</body>
<script type="text/javascript">
	
	$(function(){
		$(".upd-lib").bind("click",function(){
			$.ajax({
				type:'post',
				url:'/file/updateMusicLib.htm',
				dataType:'json',
				success:function(data){
					alert(data);
					mpg.query();
				},
				error:function(err){
					alert("服务器内部错误");
				}
			});
		});
		
		$(".addToType").bind("click",function(){
			$("#musicId").val($(this).attr("rowId"));
			$("#myModalLabel").html("添加【" + $(this).attr("musicName") + "】到类库");
		});
		mpg.initSelect();
		mpg.query();
		
		$("#checkAll").bind("click",function(){
			if($(this).prop("checked")){ //全选
				$(".rowId").prop("checked",true);
			}else{	//取消全选
				$(".rowId").prop("checked",false);
			}
		});
		
	})
	
	var mpg = {
		initSelect:function(){
			$.ajax({
				type:'post',
				url:'/music/listTypes.htm',
				dataType:'json',
				success:function(data){
					$("#m-type").empty();
					var htm = "";
					for(var i=0; i<data.length; i++){
						htm += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
					$("#m-type").append(htm);
				},
				error:function(err){
					alert("初始化类型出错！");
				}
			});
		},
		submitType:function(){
			$.ajax({
				type:'post',
				url:'/music/addToType.htm',
				dataType:'json',
				data:$("#typeForm").serializeArray(),
				success:function(data){
					alert(data);
					$(".btn-close").click();
				},
				error:function(){
					alert("操作失败");
				}
			});
		},
		query:function(){
			$.ajax({
				type:'post',
				url:'/music/list.htm',
				data:$("#query-form").serializeArray(),
				dataType:'json',
				success:function(pageObj){
					$("#tbody").empty();
					var htm = "";
					var data = pageObj['list'];
					var pageCount = pageObj['pageCount'];
					var pageNo = pageObj['pageNow'];
					for(var i=0; i<data.length;i++){
						htm += "<tr><td><input type='checkbox' class='rowId' value='"+data[i].id+"'></td>";
						htm += "<td>"+data[i].name+"</td><td>"+data[i].extension+"</td><td>"+data[i].time+"</td><td>"+data[i].singer+"</td>";
						htm += "<td>"+data[i].uri+"</td><td>"+data[i].downloadUrl+"</td><td>"+data[i].addTime+"</td>";
						htm += "<td><a class='btn btn-default addToType' data-toggle='modal' data-target='#myModal' musicName='"+data[i].name+"' rowId='"+data[i].id+"'>添加到类别</a>";
						htm += "<a class='btn btn-default'>编辑</a><a href='javascript:mpg.del("+data[i].id+")' class='btn btn-default'>删除</a></td></tr>";
					}
					$("#tbody").append(htm);
					$(".pageNav").empty();
					$(".pageNav").append("共查询到" + pageObj['totalCount'] + "条记录&nbsp;&nbsp;");
					$(".pageNav").append(pageNo+"/"+pageCount+"&nbsp;&nbsp;<a href='javascript:mpg.jumpPage("+(parseInt(pageNo)-1)+","+pageCount+")'>上一页</a>");
					$(".pageNav").append("&nbsp;&nbsp;<a href='javascript:mpg.jumpPage("+(parseInt(pageNo)+1)+","+pageCount+")'>下一页</a>");
					var pageSlk = "&nbsp;&nbsp;跳转到第<select onchange='javascript:mpg.jumpPage(this.value)'>";
					for(var j=0; j<pageCount; j++){
						if($("#pageNow").val() == (j+1)){
							pageSlk += "<option value='"+(j+1)+"' selected='selected'>"+(j+1)+"</option>";
						}else{
							pageSlk += "<option value='"+(j+1)+"'>"+(j+1)+"</option>";
						}
					}
					$(".pageNav").append(pageSlk+"</select>页");
				}
			});
		},
		jumpPage:function(pageNow,pageCount){
			if(pageNow <= 0 || pageNow > pageCount) return;
			$("#pageNow").val(pageNow);
			mpg.query();
		},
		validRowIds:function(){
			var rowIds = [];
			$(".rowId").each(function(){
				if($(this).prop("checked")){
					rowIds.push($(this).val());
				}
			});
			if(!rowIds || rowIds.length <= 0){
				alert("请至少选择一行操作");
				return false;
			}
			return rowIds;
		},
		batchDel:function(){
			var rowIds = mpg.validRowIds();
			if(rowIds) mpg.del(rowIds);
		},
		del:function(id){
			if(!confirm("确定删除吗?")) return;
			$.ajax({
				type:'post',
				url:'/music/'+id+'/del.htm',
				dataType:'json',
				success:function(data){
					alert(data);
					mpg.query();
				},
				error:function(){
					alert("操作失败");
				}
			});
		},
		batchAddToType:function(){
			var rowIds = mpg.validRowIds();
			if(rowIds){
				$("#musicId").val(rowIds);
				$("#myModal").modal();
			}
		}
	}
	
		
</script>

</html>