<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：公司文档类型管理
--%>

<div class="easyui-layout" fit="true" >
   <!-- 左侧-->
	<div region="west" title="公司文档分类列表" split="true" 	style="width:250px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<%@ include file="/WEB-INF/jsp/files/company/type/query.jsp"%>
	</div>
   
</div>

<script type="text/javascript">
<!--
	$(function(){
		loadLeftWdTypes();
	});
	function loadLeftWdTypes(){
		$.ajax({
			url:"companyFiles/type/allTypes.do",
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
							onClick: updateWdTypesuperId
						}
				};
				var rel='<%= request.getParameter("rel") %>_datagrid';
				var zNodes = new Array();
				//zNodes.push({id : 0,name : "文档分类",open:true,datagrid : rel,param : {"superId":"0"},updateTitle:"文档分类"});
				$.each(json, function(i, m) {
					
					zNodes.push({id : m.id,name : m.name,pId : m.superId,open:false,
					datagrid : rel,param : {"superId":m.id},updateTitle:m.name,iconSkin:"folder"});
					
				});
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
				
			}
		});
	}
	function updateWdTypesuperId(event,treeId, treeNodeJSON){
		$('#<%= request.getParameter("rel") %>_superId').val(treeNodeJSON.id);
		refreshDatagrid(event,treeId, treeNodeJSON);
	}
	
//-->
</script>

