<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程监控
--%>
<div class="easyui-layout" fit="true" >
    <!-- 左侧-->
	<div region="west" title="流程列表" split="true" 	style="width:220px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<%@ include file="/WEB-INF/jsp/workflow/process/monitor/processInstance_key.jsp"%>
	</div>
   
</div>

<script type="text/javascript">
<!--
	$(function(){

		$.ajax({
			url:"workflow/work/flowTree.do",
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
							onClick: refreshDatagrid
						}
				};
				var rel='<%= request.getParameter("rel") %>_datagrid';
				var zNodes = new Array();
				zNodes.push({id : 0,name : "全部流程",datagrid : rel,param : {"workflowId":""},updateTitle:"全部流程",open:true,icon:"resource/images/menu/zzjg.png"});
				//流程类型
				$.each(json.types, function(i, t){
					zNodes.push({id : t.value,pId:0,name : t.name,open:true});
				});
				$.each(json.flows, function(i, f) {
					
					zNodes.push({id : f.id,name : f.name,pId : f.type,icon:"resource/images/menu/zzjg.png",
					datagrid : rel,param : {"workflowId":f.id},updateTitle:""+f.name});
					
				});
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
				
			}
		});
		
	});
	
	
//-->
</script>

