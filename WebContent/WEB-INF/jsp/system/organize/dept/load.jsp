<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--部门管理
--%>
<div class="easyui-layout" fit="true">
   <!-- 左侧-->
	<div region="west" title="部门列表" split="true"	style="width:250px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<div id="${param.rel }_box" title="部门操作" class="easyui-panel" fit="true" >
			<shiro:hasPermission name="dept:add">
				<%@ include file="/WEB-INF/jsp/system/organize/dept/add.jsp" %>
			</shiro:hasPermission>
			<shiro:lacksPermission name="dept:add">
				 <img src="resource/images/big/arrow_left_48.png" style="vertical-align: middle;"/>
				 <span style="font-weight: bold;">请先点击左侧选择部门</span>	
			</shiro:lacksPermission>
		</div>
	</div>
   
</div>
<script type="text/javascript">
<!--
	$(function(){
		queryLeftDepts();
	});
	function queryLeftDepts(){
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
							onClick: zTreeClick
						}
				};
				var rel='<%= request.getParameter("rel") %>';
				var zNodes = new Array();
				$.each(json, function(i, d){
					zNodes.push({id : d.id,name : d.deptName,
						pId : d.superId,href:"dept/updatePage.do?id="+d.id+"&rel="+rel,dwzTarget:"ajax",
						rel:rel+"_box",open:true});
				});
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
			}
		});
	}
	
//-->
</script>

	

	

