/**
 * 消息提醒工具
 * 调用此类进行消息提醒，方便换为其他提醒插件
 * @type 
 */
var Msg={
	/**
	 * 消息提醒 
	 * 参数:
	 * showType: 如何显示消息, null,slide(滑动),fade(淡入淡出),show(显示),默认为滑动
	 * showSpeed: 定义的时间，以毫秒为单位的消息窗口完成表演。默认为600。
	 * width: 定义消息窗口的宽度。默认为250。
	 * height: 定义消息窗口的高度。默认为100。
	 * title: 标题文字头面板上显示。
	 * msg: 消息文本显示。
	 * style: 定义消息窗口的的自定义dpecification风格。默认在右下角显示
	 * timeout:如果定义为0，消息窗口将不会关闭，除非用户关闭它。定义unzero，消息窗口会自动关闭时超时。默认为4秒
	 * 
	 * msgType: 拓展属性：根据类型，在前面添加图片， success warning
	 */
	show:function(options){
		if(options.msgType){
			if(options.msgType=="warning"){
				options.msg='<div style="font-weight: bold;"><img src="resource/js/ui/images/msg/messager_warning.gif"/>&nbsp;&nbsp;'+options.msg+"</div>";
			}else if(options.msgType=="success"){
				options.msg='<div><img src="resource/js/ui/images/msg/Check_32x32.png"/>&nbsp;&nbsp;'+options.msg+"</div>";
				
			}
		}
		if(!options.showSpeed)options.showSpeed=300;//修改默认窗口表演时间，加快播放速度
		$.messager.show(options);
	},
	/**
	 * 在左上角显示消息提醒
	 * @param {} options
	 */
	topLeft:function (options){
		options["style"]={
					right:'',
					left:0,
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				};
		Msg.show(options);
	},
	/**
	 * 在顶部中间显示消息提醒
	 * @param {} options
	 */
	topCenter:function(options){
		options["style"]={
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				};
		Msg.show(options);
	},
	/**
	 * 在右上角显示消息提醒
	 * @param {} options
	 */
	topRight:function(options){
		options["style"]={
					left:'',
					right:0,
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				};
		Msg.show(options);
	},
	/**
	 * 在中间左边显示消息提醒
	 * @param {} options
	 */
	centerLeft:function(options){
		options["style"]={
					left:0,
					right:'',
					bottom:''
				};
		Msg.show(options);
	},
	/**
	 * 在屏幕中间显示消息提醒
	 * @param {} options
	 */
	center:function(options){
		options["style"]={
					right:'',
					bottom:''
				};
		Msg.show(options);
	},
	/**
	 * 在中间右边显示消息提醒
	 * @param {} options
	 */
	centerRight:function(options){
		options["style"]={
					left:'',
					right:0,
					bottom:''
				};
		Msg.show(options);
	},
	/**
	 * 在左下角显示消息提醒
	 * @param {} options
	 */
	bottomLeft:function(options){
		options["style"]={
					left:0,
					right:'',
					top:'',
					bottom:-document.body.scrollTop-document.documentElement.scrollTop
				};
		Msg.show(options);
	},
	/**
	 * 在底部中间显示消息提醒
	 * @param {} options
	 */
	bottomCenter:function(options){
		options["style"]={
					right:'',
					top:'',
					bottom:-document.body.scrollTop-document.documentElement.scrollTop
				};
		Msg.show(options);
	},
	
	/**
	 * 显示一个警告窗口。遮蔽屏幕
	 * 参数：
 	 * @param {String} title：标题文字头面板上显示。
    * @param {String} msg：消息文本显示。
  	 * @param {String} icon：显示的图标图像。可用值：error(错误)，question(问题)，info(信息)，warning(警告)。
  	 * @param {Function} fn：回调函数,当窗口关闭时触发
	 */
	alert:function(title,msg,icon,fn){
		$.messager.alert(title,msg,icon,fn);

	},
	/** 
	 * 显示一个确认消息窗口的“确定”和“取消”按钮。遮蔽屏幕
	 * 参数：
    * @param {String} title 标题文字头面板上显示。
	 * @param {String} msg   消息文本显示。
	 * @param {Function} fn    回调函数，当用户点击OK按钮，传入true,false
	 */
	confirm:function(title,msg,fn){
		$.messager.confirm(title,msg,fn);
	},
	/**
	 * 显示一个确认消息窗口的“确定”和“取消”按钮,用户输入一些文字 遮蔽屏幕
	 *
	 * @param {String} title 标题文字头面板上显示。
	 * @param {String} msg   消息文本显示。
	 * @param {Function} fn    回调函数，当用户点击OK按钮，传入用户输入的信息
	 */
	prompt:function(title,msg,fn){
		$.messager.prompt(title,msg,fn);
	}
	
	
	
	
	
}