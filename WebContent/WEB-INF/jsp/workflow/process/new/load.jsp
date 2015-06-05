<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--新建工作
--%>

<div class="easyui-layout" fit="true" >
   <!-- 左侧-->
	<div region="west" title="流程分类" split="true" 	style="width:220px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
	
		<div id="${param.rel }_box" title="工作流程" class="easyui-panel" fit="true" href="workflow/newWork/query.do?flowType=">
				
		</div>
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
				var zNodes = new Array();
				zNodes.push({id : 0,name : "全部类型",pId:0,open:true,href:"workflow/newWork/query.do?flowType=&rel=<%= request.getParameter("rel") %>",dwzTarget:"ajax",
					rel:"<%= request.getParameter("rel") %>_box"});
				$.each(json.types, function(i, t){
					zNodes.push({id : t.value,name : t.name,pId:0,href:"workflow/newWork/query.do?flowType="+t.value+"&rel=<%= request.getParameter("rel") %>",dwzTarget:"ajax",
						rel:"<%= request.getParameter("rel") %>_box"});
				});
				
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
			}
		});
	});
//-->
</script>
