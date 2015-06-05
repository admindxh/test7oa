<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构 -- 用户管理--查找带回
--%>

<div class="easyui-layout"  fit="true">
   <!-- 左侧-->
	<div region="west" title="部门列表" split="true"	style="width:250px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<div id="${param.rel }_box" class="easyui-panel" fit="true"  title="用户查询">
			
			<c:choose>
				<c:when test="${param.type==1 }">
					<%--单选--%>
					 <%@ include file="/WEB-INF/jsp/system/organize/user/lookup_query.jsp"%>
				</c:when>
				<c:when test="${param.type==2 }">
					<%--多选--%>
					 <%@ include file="/WEB-INF/jsp/system/organize/user/lookup_more_query.jsp"%>
				</c:when>
				<c:when test="${param.type==3 }">
					<%--手机号--%>
					
					  <%@ include file="/WEB-INF/jsp/system/organize/user/lookup_more_query_num.jsp"%>
				</c:when>
				
			</c:choose>
		
		
		</div>
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
							dblClickExpand: false,
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
				zNodes.push({id : 0,name : "全部部门",open:true,datagrid : rel,param : {"deptId":""}});
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.deptName,pId : d.superId,open:true,
					datagrid : rel,param : {"deptId":d.id}});
					
				});
				$.fn.zTree.init($('#<%= request.getParameter("rel") %>_leftTree'), setting, zNodes);
				
			}
		});
		
	});
//-->
</script>