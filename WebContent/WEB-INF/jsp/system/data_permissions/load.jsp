<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--数据权限管理
--%>
<div class="easyui-layout" fit="true">
   <!-- 左侧-->
	<div region="west" title="功能模块" split="true"	style="width:250px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<div id="${param.rel }_box" title="数据权限" class="easyui-panel" fit="true" >
			  <img src="resource/images/big/arrow_left_48.png" style="vertical-align: middle;"/>
			  <span style="font-weight: bold;">请先点击左侧选择模块</span>	
		</div>
	</div>
   
</div>
<script type="text/javascript">
<!--
	$(function(){
		var setting = {
				view: {
					dblClickExpand: false,
					showIcon: false
				},
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
		var zNodes =[
		    {id :1,name : "部门公告查询",href:"dataPer/updatePage.do?type=10001&name="+encodeURIComponent("部门公告查询")+"&rel="+rel,dwzTarget:"ajax",rel:rel+"_box"}
		   
		 ];
	
		$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
	});
	
	
//-->
</script>