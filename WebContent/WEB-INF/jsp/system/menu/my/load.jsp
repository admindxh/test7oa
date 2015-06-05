<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：快捷菜单 管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			<span>
				<label>菜单名称：</label>
				<input	type="text" name="menuName"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="menu/my/addPage.do?rel=${param.rel }" target="dialog" width="600" height="350"  rel="${param.rel }_add"  title="添加快捷菜单">添加</a>
				
			
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="menu/my/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="600" height="350"  rel="${param.rel}_update" warn="请先选择一个菜单" title="修改快捷菜单">修改</a>
					
				
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
			nowrap:false,
			url : "menu/my/query.do",
			pagination:false,
			columns : [ [ 
				{
					field : "sort",
					title : "排序号",
					width : 50,
					align:"center",
					sortable : true
				
				},
				{
					field : "name",
					title : "菜单名称",
					width : 200,
					align:"center",
					sortable : true

				},
			 	 {
					field : "op",
					title : "操作",
					width : 50,
					align:"center",
					formatter: function(value,row,index){
						
						return '<a  href="menu/my/del.do?id='+row.id+'" datagrid="<%=request.getParameter("rel")%>_datagrid" target="ajaxTodo"  title="确定要删除快捷菜单吗?" >删除</a>';
					}
				} 
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改快捷菜单','menu/my/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:600,height:350});
			}
		});
		
	});

//-->		
</script>
