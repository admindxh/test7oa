<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--用户管理--查询
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<input id="${param.rel}_deptId" name="deptId" type="hidden" value="0"/>
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
				
				<shiro:hasPermission name="user:add">  
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加用户"   href="user/addPage.do?rel=${param.rel}&deptId={#${param.rel}_deptId}"  target="dialog" width="800" height="400" rel="${param.rel}_add" >添加</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="user:update">  
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改用户信息"	href="user/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" warn="请先选择一个用户" >修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="user:delete">  
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="user/del.do" target="selectedTodo"  title="请谨慎操作！删除用户的同时也会删除跟用户相关的一些数据，如果想保留数据，可以设置禁止用户登录系统代替删除。确定要删除吗?" warn="至少选择一个用户">批量删除</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="user:readRole"> 
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"
					  title="查看角色"	 href="user/updateRolePage.do?id={id}" target="dialog" width="500" height="400" rel="${param.rel}_updaterole" warn="请先选择一个用户">查看角色</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="user:readPower"> 
					<a  class="easyui-linkbutton"  icon="icon-search"	plain="true"	
						title="查看权限"	 href="user/showPowersPage.do?id={id}" target="dialog" width="600" height="400" rel="${param.rel}_updaterole" warn="请先选择一个用户">查看权限</a>
					
				</shiro:hasPermission>
				
				<shiro:hasPermission name="user:resetPassword"> 
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"	
						title="重置密码" href="user/updatePwPage.do?id={id}&rel=${param.rel}" target="dialog"  rel="${param.rel}_updatepw" warn="请先选择一个用户">重置密码</a>
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
			url : "user/queryUsers.do",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "trueName",
					title : "姓名",
					align:"center",
					width : 120,
					formatter: function(value,row,index){
						
						return '<a href="user/show.do?id='+row.id+'" target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_show" title="查看用户详情">'+value+'</a>';
					}
				},
				{
					field : "deptName",
					title : "部门",
					align:"center",
					width : 220
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
					sortable : true,
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
					sortable : true,
					formatter: function(value,row,index){
						
							if("1"==value){
								return "正常";
							}else{
								return "禁用";
							}
					}
				} ,
			 	 {
					field : "registerTime",
					title : "注册时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}    
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改用户信息','user/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
