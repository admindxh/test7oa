<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	工作流程--流程管理----监听器管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
			<span >
				<label>类型：</label>
				<select   name="type"   submitForm="change">
					 <option value="">全部类型</option>
				     <option value="1">执行监听器</option>
				     <option value="2">用户任务监听器</option>
				</select>
			</span>
			<span>
				<label>名称：</label>
				<input	type="text" name="name"   />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			
		</div>
	

		<div class="search-toolbar"  >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="flowListener:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加监听器"	 href="flowListener/addPage.do?rel=${param.rel }"  target="dialog" width="800" height="400" rel="${param.rel}_add" >添加</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="flowListener:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改监听器"	href="flowListener/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" warn="请先选择一个监听器" >修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="flowListener:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="flowListener/del.do" target="selectedTodo"  title="确定要删除吗?删除之后，如果有流程使用了此监听器将出现执行错误！！" warn="至少勾选一个监听器">批量删除</a>
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
			url : "flowListener/query.do",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				{
					field : "type",
					title : "类型",
					align:"center",
					width : 80,
					formatter: function(value,row,index){
						if(1==value){
							return "执行监听器";
						}else{
							return "用户任务监听器";
						}
					}
				},
				{
					field : "name",
					title : "名称",
					align:"center",
					width : 150
					
				},
				{
					field : "event",
					title : "事件",
					align:"center",
					width : 80
					
					
				},
				{
					field : "valueType",
					title : "执行类型",
					align:"center",
					width : 60,
					formatter: function(value,row,index){
						if(1==value){
							return "Java Class";
						}else{
							return "expression";
						}
					}
				},
				{
					field : "value",
					title : "执行内容",
					align:"center",
					width : 200
				},
				{
					field : "desc",
					title : "备注",
					align:"center",
					width : 150
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改监听器','flowListener/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
