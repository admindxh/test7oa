<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--菜单管理--action管理	查询
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<input id="${param.rel}_menuId" name="menuId" type="hidden" />
		<div  class="search-content " >
			<span>
				<label>名称：</label>
				<input	type="text" name="actionName" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>url：</label>
				<input	type="text" name="actionUrl" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
		</div>
	

		<div class="search-toolbar"  >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加系统菜单操作"	 href="menu/action/addPage.do?id={#${param.rel}_menuId}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_add" warn="请先点击左侧菜单,选择操作所属菜单!">添加</a>
					
				
				
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改系统菜单操作"	href="menu/action/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" warn="请先选择一个操作" >修改</a>
					
				
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="menu/action/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少选择一个操作">批量删除</a>
				
				
	
			</span>
			
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="操作查询"></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			border:true,
			nowrap:false,
			url : "menu/action/query.do",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				{
					field : "actionSort",
					title : "操作序号",
					align:"center",
					width : 50
				},
				 {
					field : "actionName",
					title : "名称",
					align:"center",
					width : 180,
					formatter: function(value,row,index){
						
						return '<a href="menu/action/updatePage.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>" target="dialog" width="700" height="420" rel="<%=request.getParameter("rel")%>_update" title="修改系统菜单操作">'+value+'</a>';
					}
				},
			    
				{
					field : "actionUrl",
					title : "操作权限标识",
					align:"center",
					width : 250
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改系统菜单操作','menu/action/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
