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
<script type="text/javascript">
	$(function(){
		$("#sb-btn").click(function(){
			$("#sb-form").submit();
		});
		
		$(".glyphicon-open").click(function(){
			$("#inputfile").click();
			$("#name").val($("#inputfile").val());
		});
		
	})
</script>
</head>
<body>

	<span class="glyphicon glyphicon-plus" data-toggle="modal" data-target="#myModal"></span>
	<table class="table table-bordered sa">
	   <caption>文件列表</caption>
	   <thead>
	      <tr>
	         <th>文件名</th>
	         <th>文件大小</th>
	         <th>相对路径</th>
	         <th>下载路径</th>
	         <th>是否可下载</th>
	         <th>上传时间</th>
	      </tr>
	   </thead>
	   <tbody>
	      <c:forEach items="${attachments}" var="file">
	      	<tr>
	      		<td>${file.fileName}</td>
	      		<td>${file.fileSize}</td>
	      		<td>${file.uri}</td>
	      		<td>${file.downloadUrl}</td>
	      		<td>${file.downloadAble}</td>
	      		<td>${file.addTime}</td>
	      	</tr>
	      </c:forEach>
	   </tbody>
	</table>
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               		添加文件
            </h4>
         </div>
         <div class="modal-body">
			<form role="form" id="sb-form" action="/file/upload.htm" method="post" enctype="multipart/form-data">
			   <div class="form-group">
			      <label for="inputfile">文件输入</label>
			      <input type="file" id="inputfile" name="file">
			      <input type="text" class="form-control" id="name" name="name">
<!-- 			      <button class="glyphicon glyphicon-open"></button> -->
			   </div>
			</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" id="sb-btn" class="btn btn-primary">
             	  提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
	</div>
</div>

</body>
</html>