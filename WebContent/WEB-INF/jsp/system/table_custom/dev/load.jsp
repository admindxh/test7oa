<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--导出打印自定义管理
--%>

<div class="easyui-layout" fit="true" >
   <!-- 左侧-->
	<div region="west" title="功能模块" split="true" 	style="width:180px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<%@ include file="/WEB-INF/jsp/system/table_custom/dev/query.jsp"%>
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
					onClick: updatetbType
				}
		};
		var rel='<%= request.getParameter("rel") %>_datagrid';
		var zNodes =[
				  {id:1,name:"登陆日志",updateTitle:"登陆日志",param : {"tbType":1},datagrid:rel}
				  
				  ];
		
		
		
		$.fn.zTree.init($("#<%= request.getParameter("rel") %>_leftTree"), setting, zNodes);
	});
	function updatetbType(event,treeId, treeNodeJSON){
		$('#<%= request.getParameter("rel") %>_tbType').val(treeNodeJSON.param.tbType);
		refreshDatagrid(event,treeId, treeNodeJSON);
	}
//-->
</script>

