<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--待办任务
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
			
			<span>
				<label>流程名称：</label>
				<input	type="text" name="flowName"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>工作标题：</label>
				<input	type="text" name="workTitle"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label style="width: 120px">任务创建时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="createdAfter" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="createdBefore" class="span2"/>
				
			</span>
			
		</div>
	

		<div class="search-toolbar">
			<span style="float:left;">
				<shiro:hasPermission name="task:addAssignee">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="委托任务"	href="task/todo/doing/entrustPage.do?taskId={id}&rel=${param.rel }" target="dialog" width="600" height="300" rel="${param.rel}_addwtrw" warn="请先选择一个任务" >委托任务</a>
				</shiro:hasPermission>
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>


<script type="text/javascript" >
<!--	

	$(function() {
	
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:false,
			url : "task/todo/doing/query.do",
			columns : [ [ 
				
				{
					field : "flowName",
					title : "流程名称",
					align:"center",
					width : 250,
					formatter: function(value,row,index){
						
						return '<a href="processDefinition/show.do?processDefinitionId='+row.processDefinitionId+'" target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_flowshow" title="流程详情">'+value+'</a>';
					}
				},
				{
					field : "workTitle",
					title : "工作标题",
					align:"center",
					width : 250,
					formatter: function(value,row,index){
						
						return '<a href="work/show.do?processInstanceId='+row.processInstanceId+'" target="dialog" width="1200" height="450" rel="<%=request.getParameter("rel")%>_workshow" title="工作详情">'+value+'</a>';
					}
				},
				{
					field : "applyUserId",
					title : "发起人",
					align:"center",
					width : 120
					
				},
			 	 {
					field : "name",
					title : "当前步骤",
					align:"center",
					width : 220,
					formatter: function(value,row,index){
						
						return '<a href="task/show.do?taskId='+row.id+'" target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_taskshow" title="工作流程--任务详情">'+value+'</a>';
					}
					
				},
				{
					field : "createTime",
					title : "任务创建时间",
					align:"center",
					width : 150,
					sortable:true,
					order:"desc",
					formatter: function(value,row,index){
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "op",
					title : "操作",
					align:"center",
					width : 50,
					formatter: function(value,row,index){
						
						return '<a href="'+row.completeUrl+'?taskId='+row.id+'&rel=<%=request.getParameter("rel")%>" target="dialog" width="1000" height="450"  title="任务办理">办理</a>';
						
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>