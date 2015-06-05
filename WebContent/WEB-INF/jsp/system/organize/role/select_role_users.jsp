<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构--角色管理--查询已分配用户
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form   onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			<span>
				<label>账号：</label>
				<input	type="text" name="userName" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>姓名：</label>
				<input	type="text" name="trueName" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>性别：</label>
				<select  name="userSex" style="width: 70px;" >
					<option value="">全部</option>
					<option value="1">男</option>
					<option value="0">女</option>
				</select>
				
			</span>
		
		</div>
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="role:deleteUser">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true" 
						href="role/delRoleUsers.do" target="selectedTodo"   warn="至少选择一个用户">批量删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:readUser">
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"
					 href="role/users/load.do?roleId=${param.roleId}&rel=${param.rel}" target="navTab"  rel="${param.rel}" title="角色管理_分配管理">分配用户管理</a>	
				</shiro:hasPermission>
				
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small" type="reset">重置</button>&nbsp;
			</span>
		
		</div>
		
		<input type="hidden" name="roleId" value="${param.roleId }"/>
	</form>
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="操作角色：<c:out value="${requestScope.name }" />  "></table>

<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:true,
			url : "role/roleUsers/query.do",
			queryParams:{
				roleId:'<%=request.getParameter("roleId")%>'
			},
			frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					},
					 {
						field : "trueName",
						title : "姓名",
						align:"center",
						width : 220
					}
			]],
			columns : [ [ 
			    
				{
					field : "deptName",
					title : "部门",
					align:"center",
					width : 250
				},
			 	 {
					field : "userName",
					title : "账号",
					align:"center",
					width : 200
				},
			 	 {
					field : "userSex",
					title : "性别",
					align:"center",
					width : 40,
					formatter: function(value,row,index){
							if("1"==value){
								return "男";
							}else{
								return "女";
							}
							
					}
				},
			 	 {
					field : "userStatus",
					title : "状态",
					align:"center",
					width : 40,
					
					formatter: function(value,row,index){
						
							if("1"==value){
								return "正常";
							}else{
								return "禁用";
							}
					}
				}  
				
			
			] ]
		});
		
	});

//-->		
</script>

