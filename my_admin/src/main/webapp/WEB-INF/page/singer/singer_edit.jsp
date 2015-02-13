<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑歌手信息</title>
<%@ include file="/common/header.jsp" %>
</head>
<body>
	<form class="form-horizontal" action="/singer/save.htm" role="form" id="form" method="post" style="border-left:1px solid #ccc;" enctype="multipart/form-data">
	   <div class="form-group">
	      <label for="firstname" class="col-sm-2 control-label">名称</label>
	      <div class="col-sm-4">
	      	 <input type="hidden" name="id" value="${singer.id}"/>
	         <input type="text" class="form-control" name="name" value="${singer.name}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">英文名</label>
	      <div class="col-sm-4">
	         <input type="text" class="form-control" name="englishName" value="${singer.englishName}">
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">所属国家</label>
	      <div class="col-sm-4">
			<select name="countryId" class="form-control">
				<c:forEach items="${countrys}" var="country">
					<option value="${country.id}">${country.name}</option>
				</c:forEach>
			</select>
	      </div>
	   </div>
	   <div class="form-group">
	      <label for="lastname" class="col-sm-2 control-label">所属地区</label>
	      <div class="col-sm-4">
	         <input type="text" class="form-control" name="district" value="${singer.district}">
	      </div>
	   </div>
	   <div class="form-group">
	   	  <label for="lastname" class="col-sm-2 control-label">简介</label>
	      <div class="col-sm-4">
	         <input type="text" name="profile" class="form-control input-lg" value="${singer.profile}">
	      </div>
  	   </div>
	   <div class="form-group">
	   	  <label for="lastname" class="col-sm-2 control-label">头像</label>
	      <div class="col-sm-4">
	      	<c:if test="${empty singer.iconId}">
		         <input type="file" name="file" class="form-control input-lg">
	      	</c:if>
	      	<c:if test="${!empty singer.iconId}">
				<img alt="歌手头像" src="/singer/${singer.iconId}/loadIcon.htm" width="60px" height="50px"/>	      		
	      	</c:if>
	      </div>
  	   </div>
  	     <div class="form-group">
	   	<label for="lastname" class="col-sm-2 control-label">性别</label>
	      <div class="col-sm-4">
	         <div class="radio">
			   <label>
			      <input type="radio" name="sex" id="male" value="1" ${singer.sex == 1 ? 'checked=checked' : ''}>男
			   </label>
			   <label>
			      <input type="radio" name="sex" id="female" value="2" ${singer.sex == 2 ? 'checked=checked': ''}>女
			   </label>
			</div>
	      </div>
	   </div>
	   <div class="form-group">
	   <label for="lastname" class="col-sm-2 control-label">形式</label>
	      <div class="col-sm-4">
	         <div class="radio">
			   <label>
			      <input type="radio" name="stype" value="1" ${singer.stype==1 ? 'checked=checked' : ''}>个人
			   </label>
			   <label>
			      <input type="radio" name="stype" value="2" ${singer.stype==2 ? 'checked=checked' : ''}>组合
			   </label>
			</div>
	      </div>
	   </div>
	   <div class="form-group">
	   	<label for="lastname" class="col-sm-2 control-label">是否置顶</label>
	      <div class="col-sm-4">
	         <div class="radio">
			   <label>
			      <input type="radio" name="isHot"  value="1" ${singer.isHot==1 ? 'checked=checked' : ''}>是
			   </label>
			   <label>
			      <input type="radio" name="isHot" value="0" ${singer.isHot!=1 ? 'checked=checked' : ''}>否
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
		$(".btn-save").bind("click",function(){
			$("#form").submit();
		});
		
		/* $(".btn-save").bind("click",function(){
			
			$.ajax({
				type:'post',
				url:'/singer/save.htm',
				data:$("#form").serializeArray(),
				success:function(data){
					alert(data);
				},
				error:function(){
					alert("保存出错");
				}
			});
			
		}); */
		
	});
	
	
</script>


</html>