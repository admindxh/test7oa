<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理--任务管理
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
				
				<c:if test="${!empty requestScope.fzr }">
					
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="projectTask/addPage.do?pid=${param.projectId }&rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="添加任务">添加</a>
					
					
					
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="projectTask/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一个任务" title="修改任务">修改</a>
					
				
					
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="projectTask/del.do?projectId=${param.projectId }" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个任务">批量删除</a>
					
					
				</c:if>
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
			url : 'projectTask/query.do?projectId=<%=request.getParameter("projectId")%>',
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "name",
					title : "任务名称",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="projectTask/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="800" height="400"  title="任务详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
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
				} 
				,
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
					field : "speed",
					title : "进度",
					width : 80,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return value+"%";
					}
				},
				{
					field : "createUserName",
					title : "创建人",
					width : 120,
					align:"center"
					
				},
			 	 {
					field : "createTime",
					title : "创建时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改任务','projectTask/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>
