<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑歌曲信息</title>
<%@ include file="/common/header.jsp" %>
</head>
<body>
	<form class="form-horizontal" action="/music/save.htm" role="form" id="form" method="post" style="border-left:1px solid #ccc;">
	   <div class="form-group">
	      <label for="firstname" class="col-sm-2 control-label">歌曲名</label>
	      <div class="col-sm-4">
	      	 <input type="hidden" name="id" value="${music.id}"/>
	         <input type="text" class="form-control" name="name" value="${music.name}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="firstname" class="col-sm-2 control-label">歌手</label>
	      <div class="col-sm-4">
	         <input name="singerId" class="form-control" id="singerId" value="${music.singerId}">
		         	
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">格式</label>
	      <div class="col-sm-4">
	         <input type="text" class="form-control" name="extension" value="${music.extension}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">时长</label>
	      <div class="col-sm-4">
	         <input type="text" class="form-control" name="time" value="${music.time}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">相对路径</label>
	      <div class="col-sm-4">
	         <input type="text" class="form-control" name="uri" value="${music.uri}">
	      </div>
	   </div>
	   <div class="form-group">
	   	  <label for="lastname" class="col-sm-2 control-label">下载地址</label>
	      <div class="col-sm-4">
	         <input type="text" name="downloadUrl" class="form-control input-lg" value="${music.downloadUrl}">
	      </div>
  	   </div>
	   <div class="form-group">
	   	  <label for="lastname" class="col-sm-2 control-label">添加时间</label>
	   	  <div class="col-sm-4">
	         <input type="text" name="addTime" class="form-control input-lg" value="${music.addTime}">
	      </div>
  	   </div>
  	     <div class="form-group">
	   	<label for="lastname" class="col-sm-2 control-label">是否在首页</label>
	      <div class="col-sm-4">
	         <div class="radio">
			   <label>
			      <input type="radio" name="isIndex" id="yes" value="1" ${music.isIndex == 1 ? 'checked=checked' : ''}>是
			   </label>
			   <label>
			      <input type="radio" name="isIndex" id="no" value="2" ${music.isIndex != 1 ? 'checked=checked': ''}>否
			   </label>
			</div>
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label" style="padding-top:0px;">
	      	<a class="btn btn-default" onclick="javascript:history.go(-1)">返回</a>
	      </label>
	      <div class="col-sm-4">
	      	<a class="btn btn-primary btn-save">保存</a>
	      </div>
	   </div>
	</form>
</body>
<script type="text/javascript">
	$(function(){
		$('#singerId').select2({
            placeholder: "请输入歌手名",
            minimumInputLength: 2,
            allowClear: true,
            ajax: {
            	type:"POST",
                url: "/singer/search.htm",
                cache: false,
                dataType: 'jsonp',
                data: function (term) {
                    return { keyword: term  };
                },
                results: function (data) {
                	 return {results: data};
                }
            },
            id: function (item) {
                return item.id;
            },
            formatResult: function (item) {
                return item.name;
            },
            formatSelection: function (item) {
                return item.name;
            },
            initSelection: function (element, callback) {
            	var id = $(element).val();
                if (id != null && id != 'null' && id != "") {
                    $.ajax("/singer/search.htm?singerId=" + id, {
                        data: {
                        },
                        dataType: "jsonp"
                    }).done(function (data) {
                        callback(data);
                    });
                }
            }
        }).on('change', function () {
        	var val = $(this).val();
            $('#singerId').val(val);
        });
		
		$(".btn-save").bind("click",function(){
			$("#form").submit();
		});
	});
	
</script>


</html>