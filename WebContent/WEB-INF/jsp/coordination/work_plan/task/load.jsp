<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--计划管理
--%>

<table id="${param.rel }_task_datagrid"  title="计划任务" border="true"></table>

<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_task_datagrid').datagrid({
			url : "workPlan/task/query.do?id=<%=(String)request.getAttribute("id")%>",
			columns : [ [ 
			   
			 	 {
					field : "startTime",
					title : "开始时间",
					width : 120,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
				,
			 	 {
					field : "endTime",
					title : "结束时间",
					width : 120,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "userName",
					title : "执行人",
					width : 100,
					align:"center"
				},
			 	 {
					field : "content",
					title : "计划任务",
					width : 200,
					align:"center"
				},
			 	 {
					field : "files",
					title : "附件",
					width : 120,
					align:"center",
					formatter: function(value,row,index){
						if(value!=null){
							var html="";
							var files = jQuery.parseJSON(value); 
							$.each(files,function(i,f){
								html+='<a href="workPlan/download.do?uuid='+f.uuid+'&name='+encodeURIComponent(f.name)+'" target="_blank"  title="点击下载">'+f.name+'</a>&nbsp;&nbsp;'
								
							});
							return html;
						}
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
							
							return '<a href="workPlan/task/updatePage.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_task" target="dialog"  title="修改计划任务" rel="<%=request.getParameter("rel")%>_update">修改</a>&nbsp;|&nbsp;<a  href="workPlan/task/del.do?id='+row.id+'" datagrid="<%=request.getParameter("rel")%>_task_datagrid" target="ajaxTodo"  title="确定要删除吗！" >删除</a>';
						}
						
		
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>
