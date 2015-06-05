<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--已发工作--运行中
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
				<label>创建时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
			
		</div>
	

		<div class="search-toolbar" >
			
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
			url : "work/mystart/run/query.do",
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
					field : "currentActivityName",
					title : "当前步骤",
					align:"center",
					width : 220
					
				},
				{
					field : "startTime",
					title : "创建时间",
					align:"center",
					width : 150,
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
						if(isDelWork){
							return '<a href="work/del.do?id='+row.processInstanceId+'" target="ajaxTodo" datagrid="<%=request.getParameter("rel")%>_datagrid"  titleText="删除只会删除运行中的，不会影响历史记录。请输入删除理由">删除</a>';
						}
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>