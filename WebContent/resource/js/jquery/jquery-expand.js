
/**
 * 拓展jquery 属性 
 */
(function($){
	//判断浏览器版本
	//解决 jquery 1.9+ 2* 去掉此浏览器类型 版本判断 问题
	if(!$.browser){
		 $.browser={};
		 var userAgent=navigator.userAgent.toLowerCase();
		 var s;
	     (s = userAgent.match(/msie ([\d.]+)/)) ? ($.browser.msie =$.browser.version=s[1]) :
	     (s = userAgent.match(/firefox\/([\d.]+)/)) ? $.browser.mozilla = $.browser.version=s[1] :
	     (s = userAgent.match(/chrome\/([\d.]+)/)) ? $.browser.webkit = $.browser.version=s[1] :
	     (s = userAgent.match(/opera.([\d.]+)/)) ? $.browser.opera = $.browser.version=s[1] :
	     (s = userAgent.match(/version\/([\d.]+).*safari/)) ? $.browser.safari = $.browser.version=s[1] : 0;
	}
	
})(jQuery);

