/**
 * @author Roger Wu
 * @version 1.0
 */
(function($){

	$.fn.extend({
		jTask:function(options){
			return this.each(function(){
				var $task = $(this);
				var id = $task.attr("did");
				$task.click(function(e){
					var box=$("#"+id,".window");
					if(!$task.hasClass("selected")){
						box.dialog("open");
						$.taskBar.switchTask(id);
					}else{
						if(box.parent(".window").css("display")=="none"){
							box.dialog("open");
						}else{
							box.dialog("minimize");
						}
					}
					$.taskBar.scrollCurrent($(this));
					return false;
				});
				$("div.close", $task).click(function(e){
					$.taskBar.closeDialog(id);
					return false;
				}).hoverClass("closeHover");
				
				$task.hoverClass("hover");
			});
		}
	});
	$.taskBar = {
		_taskBar:null,
		_taskBox:null,
		_prevBut:null,
		_nextBut:null,
		_rightClickedId:"",//右击的弹出窗id
		_op:{id:"taskbar", taskBox:"div.taskbarContent",prevBut:".taskbarLeft",prevDis:"taskbarLeftDisabled", nextBut:".taskbarRight",nextDis:"taskbarRightDisabled", selected:"selected",boxMargin:"taskbarMargin"},
		init:function(options) {
			var $this = this;
			$.extend(this._op, options);
			this._taskBar = $("#" + this._op.id);
			this._taskBox = this._taskBar.find(this._op.taskBox);
			this._taskList = this._taskBox.find(">ul");
			this._prevBut = this._taskBar.find(this._op.prevBut);
			this._nextBut = this._taskBar.find(this._op.nextBut);
			this._prevBut.click(function(e){$this.scrollLeft()});
			this._nextBut.click(function(e){$this.scrollRight()});

			//this._contextmenu(this._taskBox); // taskBar右键菜单
			taskDialogMenuEvent();
			//固定在底部，并在浏览器大小改变时一直保持在底部
			this._taskBar.css({top: $(window).height() - 30, width:$(window).width()});
			$(window).resize(function(){
			 	$.taskBar._taskBar.css({top: $(window).height() - 30, width:$(window).width()});
			});
			
		},
		_contextmenu:function(obj) {
			$(obj).on('contextmenu',function(e){
               e.preventDefault();
                $('#taskbar_menu').menu('show', {
                   left: e.pageX,
                   top: e.pageY
               });
               $.taskBar._rightClickedId=$(this).attr("did");
           });
			 
	
		},
		/*_contextmenu:function(obj) {
			$(obj).contextMenu('dialogCM', {
				bindings:{
					closeCurrent:function(t,m){
						var obj = t.isTag("li")?t:$.taskBar._getCurrent();
						$("div.close", obj).trigger("click");
					},
					closeOther:function(t,m){
						var selector = t.isTag("li")?("#" +t.attr("id")):".selected";
						var tasks = $.taskBar._taskList.find(">li:not(:"+selector+")");
						tasks.each(function(i){
							$("div.close",tasks[i]).trigger("click");
						});
					},
					closeAll:function(t,m){
						var tasks = $.taskBar._getTasks();
						tasks.each(function(i){
							$("div.close",tasks[i]).trigger("click");
						});
					}
				},
				ctrSub:function(t,m){
					var mCur = m.find("[rel='closeCurrent']");
					var mOther = m.find("[rel='closeOther']");
					if(!$.taskBar._getCurrent()[0]) {
						mCur.addClass("disabled");
						mOther.addClass("disabled");
					} else {
						if($.taskBar._getTasks().size() == 1)
							mOther.addClass("disabled");
					}
				}
			});
		},*/
		_scrollCurrent:function(){
			var iW = this._tasksW(this._getTasks());
			if (iW > this._getTaskBarW()) {
				var $this = this;
				var lTask = $(">li:last-child", this._taskList);
				if(lTask.length>0){//
					var left = this._getTaskBarW() - lTask.position().left - lTask.outerWidth(true);
					this._taskList.animate({
						left: left + 'px'
					}, 200, function(){
						$this._ctrlScrollBut();
					});
				}
			} else {
				this._ctrlScrollBut();
			}
		},
		_getTaskBarW:function(){
			return this._taskBox.width()- (this._prevBut.is(":hidden")?this._prevBut.width()+2:0) - (this._nextBut.is(":hidden")?this._nextBut.width()+2:0);
		},
		_scrollTask:function(task){
			var $this = this;
			if(task.position().left + this._getLeft()+task.outerWidth() > this._getBarWidth()) {
				var left = this._getTaskBarW()- task.position().left  - task.outerWidth(true) - 2;
				this._taskList.animate({left: left + 'px'}, 200, function(){
					$this._ctrlScrollBut();
				});
			} else if(task.position().left + this._getLeft() < 0) {
				var left = this._getLeft()-(task.position().left + this._getLeft());
				this._taskList.animate({left: left + 'px'}, 200, function(){
					$this._ctrlScrollBut();
				});
			}
		},
		/**
		 * 控制左右移动按钮何时显示与隐藏
		 */
		_ctrlScrollBut:function(){
			var iW = this._tasksW(this._getTasks());
			if (this._getTaskBarW() > iW) {
				this._taskBox.removeClass(this._op.boxMargin);
				this._nextBut.hide();
				this._prevBut.hide();
				if(this._getTasks().eq(0)[0])this._scrollTask(this._getTasks().eq(0));
			} else {
				this._taskBox.addClass(this._op.boxMargin);
				this._nextBut.show().removeClass(this._op.nextDis);
				this._prevBut.show().removeClass(this._op.prevDis);
				if (this._getLeft() >= 0){
					this._prevBut.addClass(this._op.prevDis);
				}
				if (this._getLeft() <= this._getTaskBarW() - iW) {
					this._nextBut.addClass(this._op.nextDis);
				} 
			}
		},
		_getLeft: function(){
			return this._taskList.position().left;
		},
		/**
		 * 取得第一个完全显示在taskbar上的任务
		 */
		_visibleStart: function(){
			var iLeft = this._getLeft();
			var jTasks = this._getTasks();
			for (var i=0; i<jTasks.size(); i++){
				if (jTasks.eq(i).position().left + jTasks.eq(i).outerWidth(true) + iLeft >= 0) return jTasks.eq(i);
			}
			return jTasks.eq(0);
		},
		/**
		 * 取得最后一个完全显示在taskbar上的任务
		 */
		_visibleEnd: function(){
			var iLeft = this._getLeft();
			var jTasks = this._getTasks();
			for (var i=0; i<jTasks.size(); i++){
				if (jTasks.eq(i).position().left + jTasks.eq(i).outerWidth(true) + iLeft > this._getBarWidth()) return jTasks.eq(i);
			}
			return jTasks.eq(jTasks.size()-1);
		},
		/**
		 * 取得所有的任务
		 */
		_getTasks:function(){
			return this._taskList.find(">li");
		},
		/**
		 * 计算所传入的所有任务的宽度和
		 * @param {Object} jTasks
		 */
		_tasksW:function(jTasks){
			var iW = 0;
			jTasks.each(function(){
				iW += $(this).outerWidth(true);
			});
			return iW;
		},
		_getBarWidth: function() {
			return this._taskBar.innerWidth(true);
		},
		/**
		 * 在任务栏上新加一个任务
		 * @param {Object} id
		 * @param {Object} title
		 */
		addDialog: function(id, title){
			this.show();
			var task = $("li[did='"+id+"']",this._taskList);
			if (!task[0]) {
				var taskFrag = '<li did="#taskid#"><div class="taskbutton"><span>#title#</span></div><div class="close">Close</div></li>';
				this._taskList.append(taskFrag.replace("#taskid#", id).replace("#title#", title));
				task = $("li[did='"+id+"']",this._taskList);
				task.jTask();
			} else {
				$(">div>span", task).text(title);
			}
			this._contextmenu(task);
			this.switchTask(id);
			this._scrollTask(task);
		},
		/**
		 * 关闭一个任务 同时关闭弹出窗口
		 * @param {Object} id
		 */
		closeDialog: function(obj){
			var task = null;
			var box =null;
			if(typeof obj == 'string'){
				task=$("li[did='"+obj+"']", this._taskList);
				box=$("#"+obj);
			}else{
				task=obj;
				box=$("#"+$(obj).attr("did"));
			}
			task.remove();
			if(box.find(".dialog-content").length>0){
				box.dialog('close');
			}else{
				box.window('close');
			}
			if(this._getTasks().size() == 0){
				this.hide();
			}			
			this._scrollCurrent();
		},
		/**
		 * 关闭一个任务
		 * @param {Object} id
		 */
		closeTask: function(id){
			var task = $("li[did='"+id+"']", this._taskList);
			task.remove();
			if(this._getTasks().size() == 0){
				this.hide();
			}			
			this._scrollCurrent();
		},
		/**
		 * 
		 * @param {Object} id or dialog
		 */
		restoreDialog:function(obj){
			var dialog = (typeof obj == 'string')?$("body").data(obj):obj;
			var id = (typeof obj == 'string')?obj:dialog.data("id");
			var task = $.taskBar.getTask(id);
			$(".resizable").css({top: $(window).height()-60,left:$(task).position().left,height:$(task).outerHeight(),width:$(task).outerWidth()
			}).show().animate({top:$(dialog).css("top"),left: $(dialog).css("left"),width:$(dialog).css("width"),height:$(dialog).css("height")},250,function(){
				$(this).hide();
				$(dialog).show();
				$.pdialog.attachShadow(dialog);
			});
			$.taskBar.switchTask(id);
		},
		/**
		 * 把任务变成不是当前的
		 * @param {Object} id
		 */
		inactive:function(id){
			$("li[did='"+id+"']", this._taskList).removeClass("selected");
		},
		/**
		 * 向左移一个任务
		 */
		scrollLeft: function(){
			var task = this._visibleStart();
			this._scrollTask(task);
		},
		/**
		 * 向右移一个任务
		 */
		scrollRight: function(){
			var task = this._visibleEnd();
			this._scrollTask(task);
		},
		/**
		 * 移出当前点击的任务
		 * @param {Object} task
		 */
		scrollCurrent:function(task){
			this._scrollTask(task);
		},
		/**
		 * 切换任务
		 * @param {Object} id
		 */
		switchTask:function(id) {
			this._getCurrent().removeClass("selected");
			this.getTask(id).addClass("selected");
		},
		_getCurrent:function() {
			return this._taskList.find(">.selected");
		},
		getTask:function(id) {
			return $("li[did='"+id+"']", this._taskList);
		},
		/**
		 * 显示任务栏
		 */
		show:function(){
			if (this._taskBar.is(":hidden")) {
				this._taskBar.css("top", $(window).height() - 34 + this._taskBar.outerHeight()).show();
				this._taskBar.animate({
					top: $(window).height() - this._taskBar.outerHeight()
				}, 500);
			}
		},
		/**
		 * 隐藏任务栏
		 */
		hide:function(){
			this._taskBar.animate({
				top: $(window).height() - 29 + this._taskBar.outerHeight(true)
			}, 500,function(){
				$.taskBar._taskBar.hide();
			});
		}
	}
})(jQuery);

// 绑定右键菜单事件 底部任务 弹出框 
function taskDialogMenuEvent() {
	// 刷新
	$('#taskbar_menu-update').click(function() {
		
		var box=$("#"+$.taskBar._rightClickedId);
		if(box.find(".dialog-content").length>0){
			box.dialog('refresh');
		}else{
			box.window('refresh');
		}
	})
	// 关闭当前
	$('#taskbar_menu-close').click(function() {
		
		$.taskBar.closeDialog($.taskBar._rightClickedId);
	})
	// 全部关闭
	$('#taskbar_menu-closeall').click(function() {
		$.taskBar._getTasks().each(function(){
			$.taskBar.closeDialog(this);
		});
		
	});
	// 关闭除当前之外的TAB
	$('#taskbar_menu-closeother').click(function() {
		/*$.taskBar._getTasks().each(function(){
			if($(this).attr("did")!=$.taskBar._rightClickedId){
				$.taskBar.closeDialog(this);
			}
		});*/
		//关闭后面的$("li[did='"+id+"']",this._taskList)
		$("li[did='"+$.taskBar._rightClickedId+"']",$.taskBar._taskList).nextAll().each(function(){
			$.taskBar.closeDialog(this);
		});
		//关闭前面的
		$("li[did='"+$.taskBar._rightClickedId+"']",$.taskBar._taskList).prevAll().each(function(){
			$.taskBar.closeDialog(this);
		});
	});
	// 关闭当前右侧的TAB
	$('#taskbar_menu-closeright').click(function() {
		
		$("li[did='"+$.taskBar._rightClickedId+"']",$.taskBar._taskList).nextAll().each(function(){
			
			$.taskBar.closeDialog(this);
			
		});
		
	});
	// 关闭当前左侧的TAB
	$('#taskbar_menu-closeleft').click(function() {
		$("li[did='"+$.taskBar._rightClickedId+"']",$.taskBar._taskList).prevAll().each(function(){
			
			$.taskBar.closeDialog(this);
			
		});
	});

}
