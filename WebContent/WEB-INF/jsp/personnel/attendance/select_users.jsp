<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤时间设置--查询未分配用户，考勤时间分配用户
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
				<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					class="add" href="attendance/timeAddUsers.do?attendanceId=${param.attendanceId }&navTabId=${param.rel}" target="selectedTodo"  warn="至少选择一个用户">批量分配</a>
		<shiro:hasPermission name="attendance:timeUserLoad">  
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true" 
					 href="attendance/timeUser/load.do?attendanceId=${param.attendanceId}&rel=${param.rel}" target="navTab"  rel="${param.rel}" title="考勤登记时间设置_分配管理">查看用户</a>
				</shiro:hasPermission>
				
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small" type="reset">重置</button>&nbsp;
			</span>
		
		</div>
		
		<input type="hidden" name="attendanceId" value="${param.attendanceId }"/>
	</form>
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="考勤登记时间设置--分配用户"></table>

<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:true,
			url : "attendance/users/query.do",
			queryParams:{
				attendanceId:'<%=request.getParameter("attendanceId")%>'
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

