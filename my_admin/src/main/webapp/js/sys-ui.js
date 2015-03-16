UI = (function(){
	var rtn = {
		waitWinOpen:function(){
			var h = $(window).height();
			var w = $(window).width();
			var htm = "<div id='sys-waitingWin' style='z-index:10000;background-color:#000000;position:absolute;opacity: 0.5;top:0px;left:0px;width:"+w+"px;height:"+h+"px'></div>";
			var htm2 = "<div id='sys-waitingWin-ico' style='width: 350px;background-color: #ffffff;opacity: 0.7;color: #000000;z-index:10001;width:350px;position:absolute;top:0px;text-align:center;'><img src='/images/waiting.jpg' style='width: 20px;height: 20px;'><span>请稍后...</span></div>";
			$("body").append(htm);
			$("body").append(htm2);
			var ico = $("#sys-waitingWin-ico");
			ico.css("left",w/2 +(-125)+ "px");
			ico.css("top",h/3 + "px");
		},
		waitWinClose:function(){
			$("#sys-waitingWin").remove();
			$("#sys-waitingWin-ico").remove();
		}
	}
	return rtn;
})();