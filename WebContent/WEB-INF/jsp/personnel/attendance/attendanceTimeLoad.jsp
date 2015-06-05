<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤登记时间设置页面
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content" >
			
			<span>
				<label>名称：</label>
				
				  <input type="text" name="name"  />
			</span> 
			
			
			
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
			<shiro:hasPermission name="attendance:addAttendanceTime"> 
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="attendance/addAttendanceTimePage.do?rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="添加登记时间设置">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="attendance:delAttendanceTime">  
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="attendance/delAttendanceTime.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一条登记时间设置">批量删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="attendance:updateAttendanceTime"> 
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="attendance/updateTimePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一条登记时间设置记录" title="修改登记时间设置">修改</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="attendance:timeUserLoad">  
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"	 
						href="attendance/timeUser/load.do.do?attendanceId={id}&rel=${param.rel}_fpgl" target="navTab"  rel="${param.rel}_fpgl" title="登记时间设置_查看分配用户">查看用户</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="attendance:timeAddUsers">  
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"	 
						href="attendance/users/load.do?attendanceId={id}&rel=${param.rel}_fpjs" target="navTab"  rel="${param.rel}_fpjs" title="登记时间设置_分配管理">分配用户</a>
					
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
			url : "attendance/attendanceTimeQuery.do",
			frozenColumns:[[
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "name",
					title : "名称",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="attendance/attendancetimeShowPage.do?id='+row.id+'" target="dialog" width="1000" height="450" title="登记详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}
				}
			]],
			columns : [ [ 
			             
			 	 
			 	 
				{
					field : "createtime",
					title : "创建时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},{
					field : "createuser",
					title : "创建人",
					align:"center",
					width : 200
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改登记时间设置','attendance/updateTimePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>

