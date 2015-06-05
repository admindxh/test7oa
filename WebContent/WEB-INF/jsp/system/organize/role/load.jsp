<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--组织机构--角色管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			<span>
				<label>名称：</label>
				<input	type="text" name="roleName" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span >
				<label>描述：</label>
				<input type="text" name="roleDesc"  class="span3"/>
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="role:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="role/addPage.do?rel=${param.rel }" target="dialog" width="600" height="350"  rel="${param.rel }_add"  title="添加角色">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="role/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="600" height="350"  rel="${param.rel}_update" warn="请先选择一个角色" title="修改角色">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="role:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="role/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个角色">批量删除</a>
				</shiro:hasPermission>
				
				
				<shiro:hasPermission name="role:readPower">
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"	
						href="role/power/updatePage.do?id={id}&rel=${param.rel}_roleaction" target="dialog"  rel="${param.rel}_roleaction" title="角色管理_查看权限">查看权限</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="role:hasUser">
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"	 
						href="role/roleUsers/load.do?roleId={id}&rel=${param.rel}_fpgl" target="navTab"  rel="${param.rel}_fpgl" title="角色管理_分配管理" warn="请先选择一个角色">查看用户</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:readUser">
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"	 
						href="role/users/load.do?roleId={id}&rel=${param.rel}_fpjs" target="navTab"  rel="${param.rel}_fpjs" title="角色管理_分配管理" warn="请先选择一个角色">分配用户</a>
					
				</shiro:hasPermission>
				
				
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
			url : "role/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "roleName",
					title : "角色名称",
					width : 150,
					align:"center",
					sortable : true

				},
			 	 {
					field : "roleDesc",
					title : "说明",
					width : 500
				} 
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改角色信息','role/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:600,height:350});
			}
		});
		
	});

//-->		
</script>
