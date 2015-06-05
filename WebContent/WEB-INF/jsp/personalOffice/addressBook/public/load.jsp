
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-通讯录-公共通讯录
--%>

<div class="easyui-layout" fit="true">
   <!-- 左侧-->
	<div region="west" title="公共通讯录" split="true"	style="width:180px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<%@ include file="/WEB-INF/jsp/personalOffice/addressBook/public/query.jsp" %>
	</div>
   
</div>

<script type="text/javascript">
<!--
	$(function(){

		$.ajax({
			url:"public/loadGroup.do",
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
							onClick: updateRightUserPenel
						}
				};
				var rel='<%= request.getParameter("rel") %>_datagrid';
				var zNodes = new Array();
				zNodes.push({id : 0,name : "全部分组",open:true,datagrid : rel,param : {"id":""},updateTitle:"分组查询--全部分组"});
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.gaName,open:true,
					datagrid : rel,param : {"id":d.id},updateTitle:"分组查询--"+d.gaName});
					
				});
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
				
			}
		});
		
	});
	function updateRightUserPenel(event,treeId, treeNodeJSON){
		$('#<%= request.getParameter("rel") %>_id').val(treeNodeJSON.id);
		refreshDatagrid(event,treeId, treeNodeJSON);
	}
//-->
</script>

