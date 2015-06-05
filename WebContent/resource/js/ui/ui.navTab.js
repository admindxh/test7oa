

$.fn.extend({
	/**
	 * 初始化右侧工作区
	 * @returns
	 */
	initMainTab:function(){
		return this.each(function(){
			var $this = $(this);
			//将此工作区设为当前默认工作过区 
			navTab.mainTab=$this;
			$this.attr("id","_tb_center_"+$(".maintabs").length);//为tab指定id
			var $menu=$("#maintabs_menu");
			
			$this.tabs({onContextMenu:function(e, title,index){
				e.preventDefault();
				$menu.menu('show', {
					left : e.pageX,
					top : e.pageY
				});
				navTab._rightClickedIndex=index;
				var c=$this.tabs('tabs').length;
				if((index+1)==c){
					$menu.menu('disableItem',$("#maintabs-closeright"));
				}else{
					$menu.menu('enableItem',$("#maintabs-closeright"));
				}
				if(index>0){
					$menu.menu('enableItem',$("#maintabs-closeleft"));
				}else{
					$menu.menu('disableItem',$("#maintabs-closeleft"));
				}
				if(c>1){
					$menu.menu('enableItem',$("#maintabs-closeother"));
				}else{
					$menu.menu('disableItem',$("#maintabs-closeother"));
				}
				if(index==0){
					if(c==1){
						$menu.menu('disableItem',$("#maintabs-closeall"));
					}
				}else{
					$menu.menu('enableItem',$("#maintabs-close"));
					$menu.menu('enableItem',$("#maintabs-closeall"));
				}	
			},tools:[{
				iconCls:'icon-reload',
				handler:function(){
					
					if(!maintabstoolsNum){
						//刷新当前
						var tb=$this.tabs("getSelected");
						if(tb){
							tb.panel('refresh');
							maintabstoolsNum=1;
						}
						
					}
					setTimeout(function(){
						maintabstoolsNum=null;
					},3000);//当点击刷新之后禁用按钮时间
				}
			}]
			,
			isWork:true //标记为工作区
		  });
		
			
		});
	}
});



var navTab={
	mainWorkTab:null,//中间主工作区
	mainTab:null,//当前打开的工作区间右侧的tab
	//maintabsMenu:null,//工作区菜单对象
	//_currentOpenIndex: 0,//当前打开的标签
	_rightClickedIndex:0,//右击的标签
	/**
	 * 初始化中间主工作区
	 */
	init:function(){
		
		this.mainWorkTab=$("#mainWorkTab");//中间工作区 tab
		
		this.mainWorkTab.tabs({
			
			onSelect:function(title,index){
				//navTab._currentOpenIndex=index;
				
				navTab.mainTab=navTab.mainWorkTab.tabs("getTab",index).find(".maintabs");
				
			}
		});
		this.mainTab=$("div > .maintabs",navTab.mainWorkTab).first();//默认工作区
		
		maintabstoolsNum=null;//一个标识 用于防止一直点右侧 刷新tab 按钮 
		maintabsMenuEvent();//菜单绑定右键事件
		
	},
	/**
	 * 在系统主工作区添加一个标签
	 * @param {} title	  标签名称，应避免重复名称
	 * @param {} url
	 * @param {} id	  id，唯一性，如果id已存在，则在已打开的标签显示
	 * @param {} external 是否使用iframe
	 * @param {} fresh	  如果此id已存在(标签已有)，是否刷新重新获取数据，
	 * 					  如果url和已打开的url不一样则强制刷新。 
	 */
	addTab:function(title,url,id,external,fresh){
		
		if(this.mainTab.length!=1){
			Msg.alert('错误','找不到可用的右侧工作区!','error');
			return false;
		}
		
		$.messager.progress({
			text : '页面加载中...',
			interval : 200
		});
		var tab=$("#"+id);
		if(tab.length==0){
			this._add(title,url,id,external);
		}else{
			
			//已存在此id
			var tab_index=this.mainTab.tabs('getTabIndex', tab);
			this.mainTab.tabs('select',tab_index );//切换到此标签页
			var newTitle=tab.panel('options').title;
			if(title&&title.length>0){
				newTitle=title;
			}
			this._reload(tab,newTitle,url,external,fresh);
		
		}
		
	},
	/**
	 * 新增标签
	 * @param {} id	
	 * @param {} title
	 * @param {} url	
	 * @param {} external 是否使用iframe
	 */
	_add:function(title,url,id,external){
		if(external){
			this.mainTab.tabs('add', {
				id:id,
				title : title,
				content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				closable : true
			});	
		}else{
			this.mainTab.tabs('add', {
				id:id,
				title : title,
				href : url,
				closable : true
				
			});		
		}
	},
	/**
	 * tab已存在，刷新tab
	 * @param tab
	 * @param title
	 * @param url
	 * @param external
	 * @param fresh
	 */
	_reload:function(tab,title,url,external,fresh){
		
		if(tab.panel('options').href==url){
			if(fresh){
				//刷新数据
				this.mainTab.tabs('update', {
					tab: tab,
					options: {
						title:title
					}
				});
			}else{
				$.messager.progress('close');
			}
		}else{
			//两次url不一样，需要刷新url
			this._refresh(tab,title,url,external);
		}
		
	},
	/**
	 * 在已有的tab刷新其他url
	 * @param tab
	 * @param title
	 * @param url
	 * @param external
	 */
	_refresh:function(tab,title,url,external){
		
		if(external){
			this.mainTab.tabs('update', {
				tab: tab,
				options: {
					title:title,
					content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>'
					
				}
			});
		}else{
			this.mainTab.tabs('update', {
				tab: tab,
				options: {
					title:title,
					href : url
				}
			});
		}
	},	
	
	/**
	 * 拓展方法 根据id获取标签
	 * @param {} id
	 * @return {}
	 */
	_getTabById:function (id){
		var tabs=this.mainTab.tabs("tabs");
		for(var i=0;i<tabs.length;i++){
			var tab=tabs[i];
			if(tab.panel('options').id==id){
				return tab;
			}
		}
	},
	/**
	 * 根据id，获取标签title
	 * @param {} id
	 * @return {}
	 */
	_getTabTitleById:function (id){
		var tabs=this.mainTab.tabs("tabs");
		for(var i=0;i<tabs.length;i++){
			var o=tabs[i].panel('options');
			if(o.id==id){
				return o.title;
			}
		}
	},
	
	/**
	 * 拓展方法，根据id获取标签index
	 * @param {} id
	 * @return {}
	 */
	_getTabIndexById:function (id){
		
		return this.mainTab.tabs("getTabIndex",this._getTabById(id));
			
	}
	
	
	
	
	
	
	
	
	
	
};


// 绑定右键菜单事件
function maintabsMenuEvent() {
	// 刷新
	$('#maintabs-update').click(function() {
		//if($(this).hasClass("menu-item-disabled"))return false;//禁用时取消执行事件
		navTab.mainTab.tabs("getTab",navTab._rightClickedIndex).panel('refresh');
		
	})
	// 关闭当前
	$('#maintabs-close').click(function() {
		if($(this).hasClass("menu-item-disabled"))return false;//禁用时取消执行事件
		
		var currentOpenIndex=navTab.mainTab.tabs('getTabIndex',navTab.mainTab.tabs("getSelected"));
	
		var rightClickedIndex=navTab._rightClickedIndex;
		navTab.mainTab.tabs('close', rightClickedIndex);
		if(currentOpenIndex==rightClickedIndex){
			navTab.mainTab.tabs('select', currentOpenIndex);
		}
	})
	// 全部关闭
	$('#maintabs-closeall').click(function() {
		if($(this).hasClass("menu-item-disabled"))return false;
		var c=navTab.mainTab.tabs('tabs').length;
		for(var i=0;i<c;i++){
			navTab.mainTab.tabs('close', 0);
		}
	});
	// 关闭除当前之外的TAB
	$('#maintabs-closeother').click(function() {
		if($(this).hasClass("menu-item-disabled"))return false;
		var ts=navTab.mainTab.tabs('tabs');
		var c=ts.length;
		var titles=new Array();
		for(var i=0;i<c;i++){
			var t=ts[i];
			if(i!=navTab._rightClickedIndex){
				titles.push(t.panel('options').title);
			}
		}
		for(var i=0;i<c;i++){
			navTab.mainTab.tabs('close', titles[i]);
		}
		
		
	});
	// 关闭当前右侧的TAB
	$('#maintabs-closeright').click(function() {
		if($(this).hasClass("menu-item-disabled"))return false;
		var currentOpenIndex=navTab.mainTab.tabs('getTabIndex',navTab.mainTab.tabs("getSelected"));
		var rightClickedIndex=navTab._rightClickedIndex;
		//获取现在已开打的标签的title
		var currentOpenTitle=navTab.mainTab.tabs('getTab',currentOpenIndex).panel('options').title;
		var ts=navTab.mainTab.tabs('tabs');
		var c=ts.length;
		var titles=new Array();
		for(var i=(rightClickedIndex+1);i<c;i++){
			var t=ts[i];
			titles.push(t.panel('options').title);
		}
		for(var i=0,k=titles.length;i<k;i++){
			navTab.mainTab.tabs('close', titles[i]);
		}
		//如果右键选中的标签和当前已打开的标签不是同一个，则在关闭完成之后恢复到原来已打开的标签
		if(currentOpenIndex!=rightClickedIndex){
			navTab.mainTab.tabs('select', currentOpenTitle);
		}
		
	});
	// 关闭当前左侧的TAB
	$('#maintabs-closeleft').click(function() {
		if($(this).hasClass("menu-item-disabled"))return false;
		var currentOpenIndex=navTab.mainTab.tabs('getTabIndex',navTab.mainTab.tabs("getSelected"));
		var rightClickedIndex=navTab._rightClickedIndex;
		//获取现在已开打的标签的title
		var currentOpenTitle=navTab.mainTab.tabs('getTab',currentOpenIndex).panel('options').title;
		var ts=navTab.mainTab.tabs('tabs');
		var c=ts.length;
		var titles=new Array();
		for(var i=0;i<rightClickedIndex;i++){
			var t=ts[i];
			titles.push(t.panel('options').title);
		}
		for(var i=0,k=titles.length;i<k;i++){
			navTab.mainTab.tabs('close', titles[i]);
		}
		//如果右键选中的标签和当前已打开的标签不是同一个，则在关闭完成之后恢复到原来已打开的标签
		if(currentOpenIndex!=rightClickedIndex){
			navTab.mainTab.tabs('select', currentOpenTitle);
		}
	});

}







