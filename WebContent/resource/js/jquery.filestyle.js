/*
 * 
 * 上传按钮样式美化
 * 为上传输入框添加  fileupload='no'
 */
(function($) {
    $.fn.filestyle = function(options) {
        var settings = {
        	image: "resource/images/add.gif",//默认按钮图片地址, resourceVersion:资源目录
            imageheight : 20,//默认按钮图片高度
	        imagewidth : 20,//默认按钮图片 宽度
	        width : 250//默认输入框宽度
        };        
        if(options) {
            $.extend(settings, options);
        };                
        return this.each(function(){
            var $this = $(this);
            //判断当前上传输入框是否已被美化
            if($this.attr("fileupload")=="yes"){
            	return;
            }
            if($this.attr("image"))settings.image=$this.attr("image");
            if($this.attr("imageheight"))settings.imageheight=$this.attr("imageheight");
            if($this.attr("imagewidth"))settings.imagewidth=$this.attr("imagewidth");
            if($this.attr("width"))settings.width=$this.attr("width");
            
            var wrapper = $("<div title='\u9009\u62E9\u6587\u4EF6'></div>")
                            .css({
                                "width": settings.imagewidth + "px",
                                "height": settings.imageheight + "px",
                                "background": "url(" + settings.image + ") 0 0 no-repeat",
                                "background-position": "left",
                                "display": "inline",
                                "overflow": "hidden"
                            });
                            
            var filename = $('<input class="file" readonly="readonly" />')
                             .addClass($(self).attr("class"))
                             .css({
                                 "display": "inline",
                                 "width": settings.width + "px"
                             });
            filename.removeClass("easyui-validatebox validatebox-text validatebox-invalid");//移除多余生成的easyui校验
            $this.before(filename);
            $this.wrap(wrapper);
            $this.css({
                        "height": settings.imageheight + "px",
                        "width":  settings.imagewidth+"px",
                        "display": "inline",
                        "cursor": "pointer",
                        "opacity": "0.0"
                    })
             //美化之后添加标记，防止重复美化
            $this.attr("fileupload","yes");
            //获取fileValue值 为输入框赋初始值
            filename.val($this.attr("fileValue"));
            $this.on("change", function() {
                filename.val($this.val());
            });
        });
        
    };
    
})(jQuery);
