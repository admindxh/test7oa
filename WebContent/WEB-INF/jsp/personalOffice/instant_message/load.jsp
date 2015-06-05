<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息
--%>

<div  class="easyui-tabs"  fit="true" border="false" >
	<div  title="用户列表" 	style="padding:2px; " >
		

		<div class="easyui-layout" fit="true">
		   <!-- 左侧-->
			<div region="west" title="部门列表" split="true"	style="width:250px;" >
			
			   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
				
			</div>
		  	
			<div  region="center" border="false">
				<%@ include file="/WEB-INF/jsp/personalOffice/instant_message/query_user.jsp"%>
			</div>
		   
		</div>
		
		
	</div>

	<div  title="已收消息"  href="instantMessage/selectReceiveMessage.do?rel=${param.rel }_receiveMessage" rel="${param.rel }_receiveMessage"	>
	
	</div>
	<div  title="已发消息"  href="instantMessage/selectSendMessage.do?rel=${param.rel }_sendMessage" rel="${param.rel }_sendMessage" 	>
	
	</div>

</div>

<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"dept/load/all.do",
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
							onClick: refreshDatagrid
						}
				};
				var rel='<%= request.getParameter("rel") %>_datagrid';
				var zNodes = new Array();
				zNodes.push({id : 0,name : "全部部门",open:true,datagrid : rel,param : {"deptId":""},updateTitle:"用户查询--全部部门"});
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.deptName,pId : d.superId,open:true,
					datagrid : rel,param : {"deptId":d.id},updateTitle:"用户查询--"+d.deptName});
					
				});
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
				
			}
		});
		
		
	});
	
//-->
</script>