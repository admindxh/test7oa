<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--根据key查询所有版本流程定义
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content " style="padding-left: 20px">
			<span class="alert alert-info">新创建工作会自动按照最新版本(版本号最大的)执行。</span>
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
				<shiro:hasPermission name="processDefinition:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="processDefinition/del.do" field="deploymentId"  target="selectedTodo"  title="批量删除只能删除已完成的工作，确定要删除吗?" warn="至少勾选一条已完成的工作">批量删除</a>
				</shiro:hasPermission>
			</span>
			
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:true,
			url : "processDefinition/queryAllVersion/query.do",
			queryParams: {
				processDefinitionKey: '<%=request.getParameter("processDefinitionKey")%>'
			},
			mustParamNames:"processDefinitionKey",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				{
					field : "name",
					title : "流程名称",
					align:"center",
					width : 250,
					formatter: function(value,row,index){
						
						return '<a href="processDefinition/show.do?processDefinitionId='+row.id+'" target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_flowshow" title="流程详情">'+value+'</a>';
					}
				},
				{
					field : "version",
					title : "版本号",
					align:"center",
					width : 80
				},
				{
					field : "op",
					title : "操作",
					align:"center",
					width : 150,
					formatter: function(value,row,index){
						
						return '<a title="人员分配查看" href="processDefinition/userTask/assigneePage.do?processDefinitionId='+row.id+'&rel=<%=request.getParameter("rel")%>_rysz" target="navTab" rel="<%=request.getParameter("rel")%>_rysz">人员分配查看</a>'
						+' &nbsp;| &nbsp;<a   href="processDefinition/startForm/show.do?processDefinitionId='+row.id+'"  target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_flowform" title="'+row.name+'__流程表单">表单预览</a>'
						+' &nbsp;| &nbsp;<a  href="processDefinition/showFlowImgPage.do?processDefinitionId='+row.id+'"  target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_showflowimg" title="'+row.name +'__流程图_第'+row.version+'版">流程图</a>'
						+' &nbsp;| &nbsp;<a  href="work/monitor/processInstance/version.do?processDefinitionId='+row.id+'&rel=<%=request.getParameter("rel")%>_proin"  target="navTab"  rel="<%=request.getParameter("rel")%>_proin" title="'+row.name +'__第'+row.version +'版流程实例">工作记录</a>';
						
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>

