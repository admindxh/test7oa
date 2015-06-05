<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--根据流程版本，查询此版本下全部流程实例
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			<%@ include file="/WEB-INF/jsp/workflow/process/mystart/table_search.jsp" %>
			<span>
				<label>创建人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
			</span>
		</div>
	

		<div class="search-toolbar"  >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="work:deleteHis">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="work/delHis.do" target="selectedTodo"  title="批量删除只能删除已完成的工作，确定要删除吗?" warn="至少勾选一条已完成的工作">批量删除</a>
				</shiro:hasPermission>
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	<shiro:hasPermission name="work:deleteRun">  
		<input type="hidden" id="${param.rel }_delwork" value="1"/>
	</shiro:hasPermission>
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>


<script type="text/javascript" >
<!--	

	$(function() {
		if($('#<%=request.getParameter("rel")%>_delwork').length==1){
			var isDelWork=true;
		}
		
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:false,
			url : "work/monitor/processInstance/query.do",
			queryParams: {
				processDefinitionId: '<%=request.getParameter("processDefinitionId")%>'
			},
			mustParamNames:"processDefinitionId",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
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
						
						return '<a href="work/show.do?processInstanceId='+row.id+'" target="dialog" width="1200" height="450" rel="<%=request.getParameter("rel")%>_workshow" title="工作详情">'+value+'</a>';
					}
				},
				{
					field : "userId",
					title : "创建人",
					align:"center",
					width : 120
					
				},
				{
					field : "startTime",
					title : "创建时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "endTime",
					title : "结束时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						if(value){
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					}
				},
				{
					field : "op",
					title : "操作",
					align:"center",
					width : 50,
					formatter: function(value,row,index){
						if(row.workStatus==0&&isDelWork){
							return '<a href="work/del.do?id='+row.id+'" target="ajaxTodo" datagrid="<%=request.getParameter("rel")%>_datagrid"  titleText="删除只会删除运行中的，不会影响历史记录。请输入删除理由">删除</a>';
						}
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>