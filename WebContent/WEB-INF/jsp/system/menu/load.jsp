<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--菜单管理
--%>

<div  class="easyui-tabs"  fit="true" border="false" >
	<div  title="菜单管理" 	style="padding:2px; " >
		<div class="easyui-layout" fit="true">
		   <!-- 左侧-->
			<div region="west" title="菜单列表" split="true"	style="width:250px;" >
			
			   	<ul id="menu_gl_tree" class="ztree"></ul>
				
			</div>
		  	
			<div  region="center" border="false">
				<div id="${param.rel }_box" title="菜单管理" class="easyui-panel" fit="true" >
				
					 <img src="resource/images/big/arrow_left_48.png" style="vertical-align: middle;"/>
					 <span style="font-weight: bold;">请先点击左侧选择菜单</span>	
					
				</div>
			</div>
		   
		</div>
	
	
	</div>
	
	
	<my:isDeveloper>
	<div   title="操作管理" 	style="padding:2px; " >
		<div class="easyui-layout" fit="true">
			<!-- 左侧菜单选项 -->
			<div region="west" title="菜单列表" split="true"	style="width:250px;" >
			  	
			  	<ul id="menu_action_tree" class="ztree"></ul>
			    
			</div>
			<!-- 右侧 -->
			<div  region="center" border="false">
					
					<%@ include file="/WEB-INF/jsp/system/menu/action/query.jsp" %>
				
			</div>
		</div>
		
		
	</div>
	</my:isDeveloper>



</div>

<script type="text/javascript">
<!--
	$(function(){
		queryLeftMenus();
	});
	function queryLeftMenus(){
		$.ajax({
			url:"menu/query.do",
			cache: false,
			dataType:"json",
			success:function(json){
				var setting = {
						
						data: {
							simpleData: {
								enable: true
							}
						},
						callback: {
							onClick: zTreeClick
						}
				};
				var rel='<%= request.getParameter("rel") %>';
				
				var zNodes = new Array();
				
				$.each(json, function(i, m) {
					if(m.id!="0"){
						zNodes.push({id : m.id,name : m.menuName,
							pId : m.menuSuperId,icon:m.menuIcon,
							href:"menu/updatePage.do?id="+m.id+"&rel="+rel,dwzTarget:"ajax",
							rel:rel+"_box",open:m.menuOpen});
					}
				});
				
				$.fn.zTree.init($("#menu_gl_tree"), setting, zNodes);
				
				
				var setting_action = {
						view: {
							dblClickExpand: false
							
						},
						data: {
							simpleData: {
								enable: true
							}
						},
						callback: {
							onClick: updateMenusAction 
						}
				};
				var zNodes_action = new Array();
				var did='<%= request.getParameter("rel") %>_datagrid';
				$.each(json, function(i, m) {
					if(m.id!="0"){
						zNodes_action.push({id : m.id,name : m.menuName,
							pId : m.menuSuperId,icon:m.menuIcon,open:m.menuOpen,
							datagrid : did,param : {"menuId":m.id},updateTitle:"操作查询--"+m.menuName
						});
					}
				});
				$.fn.zTree.init($("#menu_action_tree"), setting_action, zNodes_action);
				
				
				
			}
		});
	}
	function updateMenusAction(event,treeId, treeNodeJSON){
		$('#<%= request.getParameter("rel") %>_menuId').val(treeNodeJSON.id);
		refreshDatagrid(event,treeId, treeNodeJSON);
	}
	
//-->
</script>


