<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--字典管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<input id="${param.rel}_listType" name="listType" type="hidden" />
		<div  class="search-content " >
			
			<span>
				<label>字典值：</label>
				<input	type="text" name="listValue"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			
		</div>
	

		<div class="search-toolbar">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="list:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加字典值"	 href="list/addPage.do?rel=${param.rel}&listType={#${param.rel}_listType}" warn="请先点击左侧选择类型" target="dialog" width="800" height="450" rel="${param.rel}_add" >添加</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="list:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改字典值"	href="list/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" warn="请先选择一个字典值" >修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="list:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="list/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少选择一个字典值">批量删除</a>
				</shiro:hasPermission>
				
					
			</span>
			
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="字典值查询"></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			border:true,
			nowrap:false,
			url : "list/query.do",
			queryParams: {
				listType: 0
			},
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				{
					field : "listValue",
					title : "字典值",
					align:"center",
					sortable : true,
					width : 250
				},
			 	 
			 	 {
					field : "listDesc",
					title : "备注",
					align:"center",
					width : 250
					
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改字典值','list/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
