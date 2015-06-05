<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--系统日志--重要操作日志
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			<span>
				<label>用户：</label>
				
				<a href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询" >
					<input type="text" name="userName"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
			</span>
			
			<span>
				<label>登录ip：</label>
				<input type="text" name="loginIp" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>登录时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>

		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="log:deleteAction">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="log/action/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少选择一条日志">批量删除</a>
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
			url : "log/action/query.do",
			frozenColumns:[[
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "userId",
					title : "姓名",
					align:"center",
					width : 180
				}
			]],
			columns : [ [ 
			             
			 	 {
					field : "actionTime",
					title : "操作时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "actionIp",
					title : "IP",
					align:"center",
					width : 150
				},
			 	 {
					field : "actionIpInfo",
					title : "IP来源",
					align:"center",
					width : 200
				},
			 	 {
					field : "actionContent",
					title : "操作内容",
					align:"center",
					width : 180
					
				},
			 	 {
					field : "actionDesc",
					title : "备注",
					align:"center",
					width : 200
					
				}
				
			
			] ]
		});
		
	});

//-->		
</script>
