<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：查询我的已办任务
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<input id="${param.rel}_listType" type="hidden" />
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
				<label>任务名称：</label>
				<input	type="text" name="taskName"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label >完成时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
			<span>
				<label>发起人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
			</span>
			<span>
				<label>委托人：</label>
				
				<a href="user/lookUpPage.do?type=1" lookupGroup="owner" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="owner.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="taskOwner"  toName="owner.id"  />
				
			</span>
				
		</div>
	

		<div class="search-toolbar" >
			
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
			url : "task/havedo/query.do",
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
						
						return '<a href="work/show.do?processInstanceId='+row.workId+'" target="dialog" width="1200" height="450" rel="<%=request.getParameter("rel")%>_workshow" title="工作详情">'+value+'</a>';
					}
				},
				{
					field : "userName",
					title : "发起人",
					align:"center",
					width : 120
					
				},
			 	 {
					field : "taskName",
					title : "任务名称",
					align:"center",
					width : 220,
					formatter: function(value,row,index){
	
						return '<a href="task/show.do?taskId='+row.id+'" target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_taskshow" title="工作流程--任务详情">'+value+'</a>';
					}
					
				},
				{
					field : "ownerName",
					title : "委托人",
					align:"center",
					width : 120
					
				},
				{
					field : "completeTime",
					title : "完成时间",
					align:"center",
					width : 150,
					sortable:true,
					order:"desc",
					formatter: function(value,row,index){
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>
