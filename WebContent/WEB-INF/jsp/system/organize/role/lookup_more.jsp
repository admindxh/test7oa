<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构 -- 角色管理--查找带回(多选)
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			
			<span>
				<label>角色名称：</label>
				<input	type="text" name="roleName"   />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<a class="easyui-linkbutton"  icon="icon-add"	plain="true" warn="请先选择用户" 
				 href="javascript:;"  onclick="$.multLookup(this,true,'${param.rel }_datagrid')" >带回并覆盖</a>
				 
				 <a class="easyui-linkbutton"  icon="icon-add"	plain="true" warn="请先选择用户" 
				 href="javascript:;"  onclick="$.multLookup(this,false,'${param.rel }_datagrid')" >带回并追加</a>
				
			</span>
			<span style="float:right">
				
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small" type="reset">重置</button>&nbsp;
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>

<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap : false,
			url : 'role/query.do',
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
					sortable : true,
					formatter : function(value, row, index) {
						
						return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',roleName:'"+value+"'})>"+value+"</a>";
					}

				},
			 	 {
					field : "roleDesc",
					title : "说明",
					width : 500
				} 
			
			] ]
		});
		
	});

//-->		
</script>
