<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤统计
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content" >
			
			<span>
				<label>考勤用户：</label>
				
				<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userIds"  toName="user.id"  />
			</span> 
			 
			<span>
				<label>部门：</label>
				<a href="dept/lookUpPage.do?type=1" lookupGroup="dept" title="部门查询">
					<input  type="text"  readonly="readonly"  toName="dept.deptName"  class="span2"/>
				</a>
				<input type="hidden"  name="deptId" toName="dept.id"/>
			</span>
			<span>
				<label>登记时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
 			  
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
				
				
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
			url : "attendance/signStatisticsQuery.do",
			frozenColumns:[[
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "signuser",
					title : "姓名",
					align:"center",
					width : 150
				}
			]],
			columns : [ [ 
			             
			 	 
			 	 {
					field : "signCount",
					title : "登记总数量",
					align:"center",
					width : 50
				},
				 {
					field : "normalCount",
					title : "正常登记次数",
					align:"center",
					width : 50
					
				},{
					field : "lateCount",
					title : "迟到次数",
					align:"center",
					width : 50
					
				},{
					field : "earlyCount",
					title : "早退次数",
					align:"center",
					width : 50
					
				},
				 {
					field : "leaveCount",
					title : "请假次数",
					align:"center",
					width : 50
					
				},
				{
					field : "absenteeismCount",
					title : "旷工次数",
					align:"center",
					width : 50
					
				},
				 {
					field : "outCount",
					title : "外出次数",
					align:"center",
					width : 50
					
				},
				{
					field : "otherCount",
					title : "其他次数",
					align:"center",
					width : 50
					
				},
				 {
					field : "times",
					title : "登记时间段",
					align:"center",
					width : 100
					
				}
			] ]
		});
		
	});

//-->		
</script>

