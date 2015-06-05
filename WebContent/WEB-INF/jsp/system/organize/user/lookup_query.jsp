<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构 -- 用户管理--查找带回(单选)
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			
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
			url : "user/lookUp.do",
			queryParams: {
				type: '<%=request.getParameter("type")%>'
				
			},
			mustParamNames:"deptId,type",
			frozenColumns:[[
					
					 {
						field : "trueName",
						title : "姓名",
						align:"center",
						width : 180,
						formatter: function(value,row,index){
							
							return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',userName:'"+row.userName+"',trueName:'"+row.trueName+"'})>"+value+"</a>";
							
						}
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
				}
			 	  
			
			] ]
		});
		
	});

//-->		
</script>