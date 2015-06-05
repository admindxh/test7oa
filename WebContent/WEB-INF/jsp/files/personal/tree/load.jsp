<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人文档管理--树状模式
--%>

<div class="easyui-layout" fit="true" >
   <!-- 左侧-->
	<div region="west" title="目录列表" split="true" 	style="width:250px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		
		 <%@ include file="/WEB-INF/jsp/files/personal/tree/query.jsp" %>
		
	</div>
   
</div>

<script type="text/javascript">
<!--
	$(function(){

		$.ajax({
			url:"personalFiles/tree/allNode.do",
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
							onClick: updatePersonalFilesWdSuperId
						}
				};
				var rel='<%= request.getParameter("rel") %>_datagrid';
				var zNodes = new Array();
				zNodes.push({id : 0,name : "个人文档",open:true,datagrid : rel,param : {"wdSuperId":"0"},updateTitle:"个人文档",iconSkin:"folder"});
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.name,pId : d.pId,datagrid : rel,param : {"wdSuperId":d.id},updateTitle:d.name,iconSkin:"folder"});
					
				});
				
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
				
				
			}
		});
	});
	function updatePersonalFilesWdSuperId(event,treeId, treeNodeJSON){
		$('#<%= request.getParameter("rel") %>_tree_wdSuperId').val(treeNodeJSON.id);
		refreshDatagrid(event,treeId, treeNodeJSON);
	}
	
//-->
</script>

