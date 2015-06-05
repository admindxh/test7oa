<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤管理查询页面
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
		 
			<span>
				 <label>名称：</label>
				 <input type="text" name="name" />
			 
			</span>
			
			<span>
				<label>开始时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				<shiro:hasPermission name="attendance:addcloseTime"> 
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="attendance/closeTimeSetPage.do?rel=${param.rel }" target="dialog" width="800" height="450"  rel="${param.rel }_add"  title="添加休息日">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="attendance:updateCloseTime"> 
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="attendance/updateCloseTimePage.do?id={id}&rel=${param.rel}" target="dialog"  width="800" height="450"  rel="${param.rel}_update" warn="请先选择一个休息日" title="修改休息日">修改</a>	
				</shiro:hasPermission>  
				<shiro:hasPermission name="attendance:closeTimedel">  
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="attendance/closeTimedel.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个休息日">批量删除</a>
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
			url : "attendance/manageCloseTime/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
			
				{
					field : "name",
					title : "名称",
					width : 200,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						return '<a href="attendance/closeTimeshow.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="1000" height="450" title="休息日详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}

				},
			 	 {
					field : "startdate",
					title : "开始时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
			 	 {
					field : "enddate",
					title : "结束时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
			 	 {
					field : "createuser",
					title : "操作人",
					width : 150,
					align:"center",
					sortable : true 
				},
			 	 {
					field : "createtime",
					title : "操作时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改休息日','attendance/updateCloseTimePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>