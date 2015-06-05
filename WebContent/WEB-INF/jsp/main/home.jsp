<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
	首页 拖拽 模块效果 
--%>

<div id="main_portal" >
	<div></div>
	<div></div>
</div>
	
<div style="display: none"> 
	<%@ include file="/WEB-INF/jsp/main/home_content.jsp"%>
</div>
<script type="text/javascript">
<!--
//重置顺序,并刷新首页
function resetMainPortalState(){
	 Msg.confirm('提示',"确定重置首页内容显示顺序吗?", function(r){
         if (r){
        	 $.cookie('portal-state',null);
        	 $("#tab-mainPage").panel('refresh');
         }
	 });
}

var mainPortal;
$(function() {
	mainPanels =new Array();
	if($("#p1").length==1){
		mainPanels.push({
			id : 'p1',
			title : '系统公告',
			height : 220,
			collapsible : true,
			tools:[{
			       iconCls:'icon-search',
			       handler:function(){
			         MUI.openCenterTab('系统公告','affiche/load/my.do?rel=gtpt_xtgg','gtpt_xtgg',false,true)
			       }
			    }
			   ]
		});
	};
	if($("#p2").length==1){
		mainPanels.push({
			id : 'p2',
			title : '部门公告',
			height : 220,
			collapsible : true,
			tools:[{
			       iconCls:'icon-search',
			       handler:function(){
			         MUI.openCenterTab('部门公告','affiche/dept/load/my.do?rel=gtpt_bmgg','gtpt_bmgg',false,true)
			       }
			    }
			  ]
		});
	};
	
	if($("#p3").length==1){
		mainPanels.push({
			id : 'p3',
			title : '我的会议',
			height : 220,
			collapsible : true,
			tools:[{
			       iconCls:'icon-search',
			       handler:function(){
			         MUI.openCenterTab('我的会议','meeting/loadtome.do?rel=ggpt_hygl','ggpt_hygl',false,true)
			       }
			    }
			  ]
		});
	};
	if($("#p4").length==1){
		mainPanels.push({
			id : 'p4',
			title : '新闻',
			height : 220,
			collapsible : true,
			tools:[{
			       iconCls:'icon-search',
			       handler:function(){
			         MUI.openCenterTab('新闻','news/load_news.do?rel=grbg_xw','grbg_xw',false,true)
			       }
			    }
			  ]
		});
	};
	
	
	mainPortal = $('#main_portal').portal({
		border : false,
		fit : true,
		onStateChange : function() {
			$.cookie('portal-state', getPortalState(),{expires:7});
		}
	});
	var state = $.cookie('portal-state');
	if (!state) {
		state = 'p1,p3,:p2,p4';/*冒号代表列，逗号代表行*/
	}
	addPortalPanels(state);
	mainPortal.portal('resize');
	
});

function getPanelOptions(id) {
	for ( var i = 0; i < mainPanels.length; i++) {
		if (mainPanels[i].id == id) {
			return mainPanels[i];
		}
	}
	return undefined;
}
function getPortalState() {
	var aa = [];
	for ( var columnIndex = 0; columnIndex < 2; columnIndex++) {
		var cc = [];
		var panels = mainPortal.portal('getPanels', columnIndex);
		for ( var i = 0; i < panels.length; i++) {
			cc.push(panels[i].attr('id'));
		}
		aa.push(cc.join(','));
	}
	return aa.join(':');
}
function addPortalPanels(portalState) {

	var columns = portalState.split(':');
	for ( var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
		var cc = columns[columnIndex].split(',');
		for ( var j = 0; j < cc.length; j++) {
			var options = getPanelOptions(cc[j]);
			if (options) {
				var p=$("#"+options.id);
				if(p.length>0){
					p.panel(options);
					mainPortal.portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
	}
}
//-->
</script>

