<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--流程设计
--%>
<div class="easyui-layout" fit="true" >
   <!-- 左侧-->
	<div region="west" title="流程分类" split="true" 	style="width:200px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		 <%@ include file="/WEB-INF/jsp/workflow/flow/query.jsp" %>		

	</div>
   
</div>

<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"workflow/work/flowType.do",
			cache: false,
			dataType:"json",
			success:function(json){
				var setting = {
						view: {
							showIcon: false
						},
						data: {
							simpleData: {
								enable: true
							}
						},
						callback: {
							onClick: updateWorkFlowType
						}
				};
				var rel='<%= request.getParameter("rel") %>_datagrid';
				var zNodes = new Array();
				zNodes.push({id : 0,name : "全部分类",open:true,datagrid : rel,param : {"flowType":""},updateTitle:"流程查询--全部分类"});
				$.each(json.types, function(i, t){
					zNodes.push({id : t.value,name : t.name,pId:0,
						datagrid : rel,param : {"flowType":t.value},updateTitle:"流程查询--"+t.name});
				});
				
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
			}
		});
	});
	function updateWorkFlowType(event,treeId, treeNodeJSON){
		$('#<%= request.getParameter("rel") %>_flowType').val(treeNodeJSON.id);
		refreshDatagrid(event,treeId, treeNodeJSON);
	}
//-->
</script>
