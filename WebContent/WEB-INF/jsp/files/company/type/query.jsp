<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：文档管理--文档类型--查询
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<input id="${param.rel}_superId" name="superId" type="hidden" value="0"/>
		<div  class="search-content " >
			
			<span>
				<label>名称：</label>
				<input	type="text" name="name"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="companyFilesType:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加文档类型"   href="companyFiles/type/addPage.do?rel=${param.rel}&superId={#${param.rel}_superId}"  target="dialog" width="800" height="400" rel="${param.rel}_add" >添加</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="companyFilesType:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改文档类型信息"	href="companyFiles/type/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" warn="请先选择一个文档类型" >修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="companyFilesType:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="companyFiles/type/del.do?callback_fn=loadLeftWdTypes" target="selectedTodo"  title="确定要删除吗?如果分类下面有子分类，或者有文件则无法删除!" warn="至少勾选一个文档类型">批量删除</a>
				</shiro:hasPermission>
					
			</span>
			
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="用户查询--全部部门"></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			border:true,
			nowrap : false,
			url : "companyFiles/type/query.do",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "name",
					title : "名称",
					align:"center",
					width : 120
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改文档类型信息','companyFiles/type/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
