<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--系统日志--登录日志
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content" >
			<span >
				<label>登录方式：</label>
				<select   name="loginType"  style="width: 80px" submitForm="change">
					<option value="">全部</option>
					<option value="1">网页</option>
					<option value="2">Android客户端</option>
				</select>
			</span>
			<span>
				<label>用户：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
			</span>
			
			<span>
				<label>登录ip：</label>
				<input type="text" name="loginIp"  class="span2"/>
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
				
				<shiro:hasPermission name="log:deleteLogin">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="log/login/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一条日志">批量删除</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="log:exportLogin">
					<a class="easyui-linkbutton"  icon="ui-icon-excel" plain="true"
					   href="log/export.do" target="selectedTodo" targetType="export" >导出</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="log:printLogin">
					<a class="easyui-linkbutton"  icon="icon-print" plain="true"
					   href="log/print.do" target="selectedTodo" targetType="print" >打印</a>
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
			url : "log/login/query.do",
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
					width : 150
				}
			]],
			columns : [ [ 
			             
			 	 {
					field : "loginTime",
					title : "操作时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "loginIp",
					title : "IP",
					align:"center",
					width : 150
				},
			 	 {
					field : "ip",
					title : "IP来源",
					align:"center",
					width : 200,
					formatter: function(value,row,index){
						return row.ipInfoCountry+" "+row.ipInfoRegion+" "+row.ipInfoCity+" "+row.ipInfoIsp;
					}
				},
			 	 {
					field : "loginType",
					title : "登录方式",
					align:"center",
					width : 80,
					formatter: function(value,row,index){
						if(value==1){
							return "网页";
						}else if(value==2){
							return "Android客户端";
						}
					}
					
				},
				{
					field : "loginDesc",
					title : "备注",
					align:"center",
					width : 200
					
				}
				
			
			] ]
		});
		
	});

//-->		
</script>

