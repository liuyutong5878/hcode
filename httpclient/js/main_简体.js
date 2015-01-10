//(function (self) {

var pf = geturlpara("pf");

if (pf == "kugou") {
	document.domain = "6ght.com";
}

var source = 2;
var versionOK = false;
var showLoadPercend;
var logoFinished = false;

function $(id) {
	return document.getElementById(id);
}

function GOON() {
	// if(chIsLogin()) {
	chIsLogin();
	checkVersion();
	startLogo();

	// startSwf();
	// startShowLoadPercend();
	// setTimeout("preloaderIsOk(true);", 2500);

	// 3s后loading消失
	// setInterval("startFlashMonitor()",10000);
	// }else {
	// $("swf_div").style.display='none';
	// gotoLoginPage();
	// }
}

function startShowLoadPercend() {
	showLoadPercend = setInterval("doShowLoadPercend()", 100);
}

function doShowLoadPercend() {
	$("loadingImg").innerHTML = "加载中，请稍后... " + $("game").PercentLoaded() + "%";
}

function stopShowLoadPercend() {
	// alert("stopShowLoadPercend")
	clearInterval(showLoadPercend);
}

function geturlpara(tparaname) // 获取地址栏的参数
{
	// alert(tparaname)
	var pos, str, paraname, paravar, parastr, tempstr1;
	var tempstr = "";
	str = window.location.href;
	pos = str.indexOf("?")
	parastr = str.substring(pos + 1);
	if (pos > 0) {
		// 开始分析串
		if (str.indexOf("&") > 0) {
			para = parastr.split("&");
			for (i = 0; i < para.length; i++) {
				tempstr1 = para[i];
				pos = tempstr1.indexOf("=");
				paraname = tempstr1.substring(0, pos);
				paravar = tempstr1.substring(pos + 1);
				if (paraname == tparaname) {
					tempstr = paravar;
					return tempstr;
				} else {
					tempstr = "";
				}
			}
		} else {
			tempstr1 = parastr;
			pos = tempstr1.indexOf("=");
			paraname = tempstr1.substring(0, pos);
			paravar = tempstr1.substring(pos + 1);
			if (paraname == tparaname) {
				tempstr = paravar;
				return tempstr;
			} else {
				tempstr = "";
			}
		}
	} else {
		return tempstr;
	}
}

function preloaderIsOk(timeAuto) {
	if (timeAuto) {
		// 到点自动清除，检查是否版本不够
		if (versionOK == false) {
			return;
		}
	}

	stopShowLoadPercend();
	removeLoadingDiv();
}

function removeLoadingDiv() {
	var loadingDiv = document.getElementById("loadingDiv");
	if (loadingDiv) {
		loadingDiv.parentNode.removeChild(loadingDiv);
		// if(game) {
		// game.height = container.height;
		// }
	}
}

function resetDg() {
	var np = getarg("nopub");
	dg = np;
}

function getarg(name) {
	var url = unescape(window.location.href);
	var allargs = url.split("?")[1];
	if (allargs == null) {
		return null;
	}
	var args = allargs.split("&");
	for (var i = 0; i < args.length; i++) {
		var arg = args[i].split("=");
		if (arg[0] == name) {
			return arg[1];
		}
	}
	return null;
}

function getSource() {
	var sfrom = "";
	var srefer = document.referrer;
	// addByMsun 如果refer为空默认为从收藏夹进入(官网)[注意：有可能先进入ptloing]
	if (srefer == null || srefer == "" || srefer.indexOf("ptlogin") >= 0) {
		sfrom = "website";
	} else {
		sfrom = chGetCookie('sFrom');
	}

	switch (sfrom) {
	case 'qzone':
		source = 1;
		break;
	case 'qun':
		source = 3;
		break;
	case '3366':
		source = 6;
		break;
	case 'xiaoyou':
		source = 14;
		break;
	case 'igame':
		source = 15;
		break;
	case 'webqq':
		source = 16;
		break;
	case 'qplus':
		source = 17;
		break;
	case 'qqgame':
		source = 4;
		break;
	case 'weibo':
		source = 20;
		break;
	case 'qqgame_invite':// QQ游戏好友邀请
		source = 27;
		break;
	case 'website':
	default:
		source = 2;
		break;
	}
}

function checkVersion() {
	var ver_major = 10;
	var ver_minor = 3;
	var ver_rev = 183;
	// check flashVersion
	var version = deconcept.SWFObjectUtil.getPlayerVersion();
	var msg = '';
	var major = version["major"];
	var minor = version["minor"];
	var rev = version["rev"];

	versionOK = (major > ver_major)
			|| (major == ver_major && minor > ver_minor)
			|| (major == ver_major && minor == ver_minor && rev >= ver_rev);
	if (!versionOK) {
		// not install or need upgrade
		msg = '<div style="color:#FFFF00;margin-top:50px;">您的FLASH版本过低，点击下面按钮下载最新的FLASH插件</div><br/><br/><a href="http://get.adobe.com/flashplayer/" target="_blank"><img width="158" height="39" alt="下载最新的FLASH" src="http://www.adobe.com/images/shared/download_buttons/get_adobe_flash_player.png"></a>';
	}
	$('flashVersion').innerHTML = msg;
}

function startLogo() {
	var logoWidth = 800;
	var logoHeight = 600;
	var so = new SWFObject(logoVersion, "gameLogo", logoWidth, logoHeight,
			"10.0.0", "#000000");
	so.addParam("base", window.YOLK7_CLIENT_ENV.resHost);
	so.addParam("quality", "high");
	so.addParam("allowScriptAccess", "always");
	so.addParam("menu", "false");
	so.addParam("wmode", "direct");
	so.addParam("pluginspage", "http://www.adobe.com/go/getflashplayer");
	so.useExpressInstall('expressinstall.swf');
	so.setAttribute('xiRedirectUrl', '');
	so.write("loadingImg");

	var winSize = getWinSize();
	$("loadingImg").style.top = ((winSize.height - logoHeight) / 2) + "px";

	setTimeout("logoFinishHandler();", 3000);
}

function logoFinishHandler() {
	if (!logoFinished) {
		logoFinished = true;

		startSwf();

		if (!versionOK) {
			var loadingImg = $("loadingImg");
			if (loadingImg) {
				loadingImg.parentNode.removeChild(loadingImg);
			}
		} else {
			$("loadingImg").style.top = "45%";
			startShowLoadPercend();
			setTimeout("preloaderIsOk(true);", 2500);
			doShowLoadPercend();
		}
	}
}

function gameReadyHandler(state) {
	preloaderIsOk(true);
}
function startSwf(autoFight) {
	urlinfo = window.location.href; // 获取当前页面的url
	len = urlinfo.length; // 获取url的长度
	offset = urlinfo.indexOf("?"); // 设置参数字符串开始的位置
	if (offset != -1) {
		newsidinfo = urlinfo.substr(offset, len); // 取出参数字符串
													// 这里会获得类似“id=1”这样的字符串
	} else {
		// 默认后缀
		// 测试服后缀
		// newsidinfo="?ip=183.60.138.93&port=80&sport=443";
		// 体验服后缀
		// newsidinfo="?ip=172.25.34.153&port=8680&sport=8688";
		// 封测服后缀
		newsidinfo = "?ip=122.225.208.13&port=80&sport=443";
	}
	getSource();
	resetDg();
	var facebook_uid = getarg("facebook_uid");
	// var uin = chGetUin();
	// var skey = chGetSkey();
	var ua = window.navigator.userAgent;
	var svrUrl = getLogicUrl(window.location.search);

	// added: jeff
	var filesXML2 = window.YOLK7_CLIENT_ENV.resHost + filesXML;
	//
	try {
		svrUrl += "&publish=" + publish + "&filesXML=" + filesXML2 + "&source="
				+ source + "&build_time=" + build_time + "&build=" + build
				+ "&dg=" + dg + "&ua=" + ua + "&login=" + login
				+ "&registSceneSwf=" + registSceneSwf
				+ "&registCreatJobSceneSwf=" + registCreatJobSceneSwf;
	} catch (e) {
		svrUrl += "&publish=" + publish + "&filesXML=" + filesXML2 + "&source="
				+ source + "&build_time=" + build_time + "&build=" + build
				+ "&dg=" + dg + "&ua=" + ua + "&login=" + login
				+ "&registSceneSwf=" + registSceneSwf
	}

	if (autoFight) {
		svrUrl += "&autoFight=1";
	}

	// added: jeff
	var swfurl1 = window.YOLK7_CLIENT_ENV.resHost + swfurl;
	// alert(swfurl)
	var so = new SWFObject(swfurl1, "game", "100%", "100%", "10.3.183",
			"#000000");//
	so.addParam("FlashVars", svrUrl);
	so.addParam("base", window.YOLK7_CLIENT_ENV.resHost);
	so.addParam("quality", "high");
	so.addParam("id", "game");
	so.addParam("align", "center");
	so.addParam("allowScriptAccess", "always");
	so.addParam("menu", "false");
	if (facebook_uid == null) {
		so.addParam("wmode", "direct");
	} else {
		so.addParam("wmode", "opaque");
	}
	so.addParam("pluginspage", "http://www.adobe.com/go/getflashplayer");
	so.useExpressInstall('expressinstall.swf');
	so.setAttribute('xiRedirectUrl', '');
	so.write("swf_div");

	// MouseWheel.init("game");
	// $("container").style.display="";
}

function chGetUin() {
	var aRet = chGetCookie('uin').match(/\d+/);
	return aRet;
}

function chGetSkey() {
	return chGetCookie('skey');
}

function chGetCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null) {
		return unescape(arr[2]);
	}
	return "";
}

function chIsLogin() {
	var openid = geturlpara("openid");
	var openkey = geturlpara("openkey");

	var qid = geturlpara("qid");
	var sign = geturlpara("sign");

	// alert(openid + "--" + openkey)
	if ((openid == null || openid.length != 32 || openkey == null || openkey.length != 32)
			&& (qid == null || sign == null)) {
		// alert("XX")
		return false;
	}

	var pf = geturlpara("pf");
	// alert(pf)
	// 写到html
	/*
	 * if (pf == "qzone" || pf == "pengyou" || pf == "tapp" || pf == "3366" ||
	 * pf == "qqgame") { $("bottomTxt").innerHTML = "openid:" + openid + "\t\t" +
	 * "\t\t《DTSZJ》"; } else if (pf == "1360" || pf == "360") {
	 * $("bottomTxt").innerHTML = "" + "\t\t《DTSZJ》"; }
	 */

	return true;
}

function chDeleteCookie(name) {
	chSetCookie(name, "");
}

function chSetCookie(name, value) {
	document.cookie = name + "=" + value + "; path=/";
}

function chLogout(jumpurl) {
	chDeleteCookie("uin");
	chDeleteCookie("skey");
	chDeleteCookie("zzpaneluin");
	chDeleteCookie("zzpanelkey");
	window.location = jumpurl;
}

function gotoLoginPage() {
	var baseURL = window.location.href.split('?')[0];
	baseURL = baseURL.replace("http://", "");
	if (window.location.search.substr(0, 1) == '?') {
		window.location.href = 'login.html' + window.location.search + '&ul='
				+ baseURL;
	} else {
		window.location.href = 'login.html?ul=' + baseURL;
	}
}

function openWindow(url, target) {
	if (!target) {
		target = "_blank";
	}
	if (target == "_self" || target == "self") {
		window.location.href = url;
	} else {
		window.open(url);
		window.target = target;
	}
}

function reload() {
	// window.location.reload(); //cause 304 not brower Cache
	window.location = window.location;
}

function addFavorite(sURL, sTitle) {
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, "");
		} catch (e) {
			alert("加入收藏失败，请使用Ctrl+D进行添加");
		}
	}
}

function createDesktop() {
	setTimeout(
			function() {
				if (window.ActiveXObject) {

				} else {
					try {
						window.external.addFavorite(
								"http://rc.qzone.qq.com/100670976?via=DLJ",
								"vgame");
					} catch (e) {
						try {
							window.external.addToFavoritesBar(
									"http://rc.qzone.qq.com/100670976?via=DLJ",
									"vgame", "slice");
						} catch (e) {
							try {
								window.sidebar
										.addPanel(
												"vgame",
												"http://rc.qzone.qq.com/100670976?via=DLJ",
												"");
							} catch (e) {
								alert(
										"非常抱歉，你的浏览器目前还不支持此功能！请在浏览器的收藏夹或者书签菜单里进行手动添加。",
										1, 1000)
							}
						}
					}
				}
			}, 100);
}

// 日志-------------------------------------------------------
var err_scroll = true;

function errStopScroll() {
	if (err_scroll) {
		err_scroll = false;
		$("stop_err_scroll_btn").value = "自动滚动";
	} else {
		err_scroll = true;
		$("err_txt").scrollTop = $("err_txt").scrollHeight;
		$("stop_err_scroll_btn").value = "停止滚动";
	}
}

function clearErr() {
	$("err_txt").innerHTML = "";
}

function addLog(str) {
	/*
	 * $("err_txt").innerHTML = $("err_txt").innerHTML+"\n"+str;
	 * 
	 * if(err_scroll){ $("err_txt").scrollTop = $("err_txt").scrollHeight; }
	 */
}

function showHideLog() {
	var logDiv = $("err_panel");
	if (logDiv) {
		if (logDiv.style.display == 'block') {
			logDiv.style.display = 'none';
		} else {
			logDiv.style.display = 'block';
		}
	}

	if (err_scroll) {
		$("err_txt").scrollTop = $("err_txt").scrollHeight;
	}
}

var imgLoad = function(url, callback) {
	var img = new Image();
	img.src = url;
	if (img.complete) {
		callback(img.width, img.height);
	} else {
		img.onload = function() {
			callback(img.width, img.height);
			img.onload = null;
		};
	}
	;
};

function getThisMovie(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		// alert(window[movieName]);
		return window[movieName]
	} else {
		// alert(window[movieName]);
		return document[movieName]
	}
}

// flash崩溃检查
var flashRuning = true;
var forceF5 = false;
function startFlashMonitor() {
	if (flashRuning) {
		flashRuning = false;

	} else {
		forceF5 = true;
		// window.location.reload();
		// var svrUrl = getLogicUrl(window.location.search);
		startSwf(true);

	}
}

function heartBeatHandler() {
	flashRuning = true;
}

function init() {
	// 开始Flash检测
	setInterval("startFlashMonitor()", 60000);
}

// ----------------------------酷狗------------------------------------------
// 是否是酷狗盒子登录
function kugouCheckIsInTheBox() {
	try {
		var isInTheBox = window.external.GetVersion();
		return isInTheBox;
	} catch (e) {
		return 0;
	}
}
// 离开帮会
function kugouLeaveFactionNotify(params) {
	// var params = '{"loginID":111,"factionID":"daonao-1-1","userID":""}';
	return window.external.LeaveFactionNotify(params);
}
// 帮会公告信息改变
function kugouOnFactionInfoChange(params) {

	// var params =
	// '{"loginID":111,"factionID":"daonao-1-1","userID":"","notice":"公告改变了77"}';
	return window.external.OnFactionInfoChange(params)
}
// 帮派成员列表
function kugouFactionMemberListNotify(params) {
	// var params =
	// '{"loginID":123456,"userID":"11","factionID":"daonao-1-1","factionName":"大闹帮一","notice":"这是公告","memberList":[{"loginID":123456,"userID":"11","userName":"我是帮派成员","pos":0,"sex":0},{"loginID":132,"userID":"22","userName":"成员2","pos":0,"sex":0}]}';
	// alert("js调用到这里了");
	return window.external.FactionMemberListNotify(params)
}
// 删除帮会
function kugouDeleteFactionNotify(params) {
	// var params = '{"loginID":111,"factionID":"daonao-1-1","userID":""}';
	return window.external.DeleteFactionNotify(params)
}
// ----------------------------酷狗------------------------------------------

// ----------------------是否是360大厅登录-------------------
function checkIsIn360Box() {
	if (wan360.Is360Game()) {
		// alert('当前浏览器是游戏大厅，可以领取特权。');
	} else {
		// alert('当前浏览器不是游戏大厅，不可以领取特权。');
	}
}
// ----------------------是否是360大厅登录-------------------

// 打印日志
/*
 * function printMsg(str) { $("err_txt").innerHTML += str + "\n"; } function
 * closeMsg() { $("err_txt").innerHTML = ""; }
 */

// self.preloaderIsOk = preloaderIsOk;
// self.GOON = GOON;
// self.openWindow = openWindow;
// self.checkVersion = checkVersion;
// self.imgLoad = imgLoad;
// self.createDesktop = createDesktop;
// self.addFavorite = addFavorite;
// self.chGetUin = chGetUin;
// self.chGetSkey = chGetSkey;
// self.gotoLoginPage = gotoLoginPage;
// self.chLogout = chLogout;
// self.reload = reload;
// self.errStopScroll = errStopScroll;
// self.clearErr = clearErr;
// self.addLog = addLog;
// self.showHideLog = showHideLog;
// })(window);
