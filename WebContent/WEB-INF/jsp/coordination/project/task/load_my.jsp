<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理--任务预览
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true">
		<div  class="search-content " >
		
			<span>
				<label>任务名称：</label>
				<input	type="text" name="name"  op="like"/>
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>创建时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="createTime" class="span2" fieldType="date" op="greaterorequal"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="createTime" class="span2" fieldType="date"  op="lessorequal"/>
				
			</span>
			
		</div>
	
		<div class="search-toolbar"  >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"	 
						href="projectTask/journal/load.do?taskId={id}&rel=${param.rel}_seerz" target="navTab"  rel="${param.rel}_fpgl" warn="请选选择一个任务" title="任务日志">查看任务日志</a>
				
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar" ></table>


<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : 'projectTask/my/query.do',
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
			    {
					field : "projectName",
					title : "项目名称",
					width : 200,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="project/show.do?id='+row.projectId+'&rel=<%=request.getParameter("rel")%>_xmshow" target="navTab"  title="项目详情" rel="<%=request.getParameter("rel")%>_xmshow">'+value+'</a>';
					}

				},
				{
					field : "name",
					title : "任务名称",
					width : 200,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="projectTask/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="800" height="400"  title="任务详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}

				},
			 	 {
					field : "startTime",
					title : "任务开始时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm");
					}
				} 
				,
			 	 {
					field : "endTime",
					title : "任务结束时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm");
					}
				},
			 	 {
					field : "speed",
					title : "任务进度",
					width : 80,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return value+"%";
					}
				},
				{
					field : "createUserName",
					title : "任务创建人",
					width : 120,
					align:"center"
					
				},
			 	 {
					field : "createTime",
					title : "任务创建时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "op",
					title : "操作",
					width : 80,
					align:"center",
					formatter: function(value,row,index){
						return '<a href="projectTask/journal/addPage.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>" target="dialog" width="1000" height="440"  title="添加任务日志" rel="<%=request.getParameter("rel")%>_addrz">添加日志</a>';
					}

				}
			
			] ]
		});
		
	});

//-->		
</script>
