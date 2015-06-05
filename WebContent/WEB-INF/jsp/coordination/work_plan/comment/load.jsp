<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--计划管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_task_datagrid');"  formatFilterData="true">
		<div  class="search-content " >
			
			<span>
				<label>批注领导：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" />
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
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

<table id="${param.rel }_task_datagrid"  toolbar="#${param.rel}_toolbar"></table>

<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_task_datagrid').datagrid({
			url : "workPlan/comment/query.do?id=<%=(String)request.getParameter("id")%>",
			columns : [ [ 
				{
					field : "userName",
					title : "批注领导",
					width : 100,
					align:"center"
				},
			 	 {
					field : "content",
					title : "内容",
					width : 300,
					align:"center"
				},
			 	 {
					field : "createTime",
					title : "批注时间",
					width : 120,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
				,
			 	 {
					field : "userId",
					title : "操作",
					width : 70,
					align:"center",
					formatter: function(value,row,index){
						if(loginUserId==value){
							
							return '<a href="workPlan/comment/updatePage.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_task" target="dialog"  title="修改计划任务" rel="<%=request.getParameter("rel")%>_update">修改</a>&nbsp;|&nbsp;<a  href="workPlan/comment/del.do?id='+row.id+'" datagrid="<%=request.getParameter("rel")%>_task_datagrid" target="ajaxTodo"  title="确定要删除吗！" >删除</a>';
						}
						
		
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>
