<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true">
		<div  class="search-content " >
		
			<span>
				<label>进度说明：</label>
				<input	type="text" name="content"  op="like"/>
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>操作时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="createTime" class="span2" fieldType="date" op="greaterorequal"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="createTime" class="span2" fieldType="date"  op="lessorequal"/>
				
			</span>
			
		</div>
	
		<div class="search-toolbar"  >
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar" title="任务日志所属任务名称： <c:out value="${requestScope.name }" />"></table>


<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : 'projectTask/journal/query.do?taskId=<%=request.getParameter("taskId")%>',
			columns : [ [ 
			    
				{
					field : "content",
					title : "进度说明",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="projectTask/journal/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="800" height="400"  title="任务进度日志详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}

				},
			 	 {
					field : "startTime",
					title : "开始时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm");
					}
				},
			 	 {
					field : "endTime",
					title : "结束时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm");
					}
				},
				{
					field : "journal",
					title : "进度日志",
					width : 150,
					align:"center"
					
				},
			 	 {
					field : "createUserName",
					title : "操作人",
					width : 120,
					align:"center"
					
				},
			 	 {
					field : "createTime",
					title : "操作时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>
