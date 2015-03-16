(function($){
	$.fn.pageNav = function(pageObj,callback){
		var pageNo = pageObj['pageNow'];
		var pageCount = pageObj['pageCount'];
		var pageSize = pageObj['pageSize'];
		var totalCount = pageObj['totalCount'];
		$(this).empty();
		$(this).append("每页"+pageSize+"条/共"+totalCount+"条&nbsp;&nbsp;");
		$(this).append(pageNo+"/"+pageCount+"&nbsp;&nbsp;<a href='javascript:jumpPage("+(parseInt(pageNo)-1)+","+pageCount+")'>上一页</a>");
		$(this).append("&nbsp;&nbsp;<a href='javascript:jumpPage("+(parseInt(pageNo)+1)+","+pageCount+")'>下一页</a>");
		var pageSlk = "&nbsp;&nbsp;跳转到第<select onchange='javascript:jumpPage(this.value)'>";
		for(var j=0; j<pageCount; j++){
			if(pageNo == (j+1)){
				pageSlk += "<option value='"+(j+1)+"' selected='selected'>"+(j+1)+"</option>";
			}else{
				pageSlk += "<option value='"+(j+1)+"'>"+(j+1)+"</option>";
			}
		}
		$(this).append(pageSlk+"</select>页");
		
		jumpPage =  function(pageNow,pageCount){
			if(pageNow <= 0 || pageNow > pageCount) return;
			callback(pageNow);
		};
		
		$(this).css({"text-align":"center",background:"#ccc",height:"20px"});
		$(this).addClass("pull-right");
	};
})(jQuery);