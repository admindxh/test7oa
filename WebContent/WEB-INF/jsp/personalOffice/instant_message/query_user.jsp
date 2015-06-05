<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息--全部用户--查询用户
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			
			<span>
				<label>姓名：</label>
				<input	type="text" name="trueName" />
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
	

		<div class="search-toolbar">
			
			
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
			url : "instantMessage/queryUsers.do",
			mustParamNames:"deptId",
			columns : [ [ 
				
				 {
					field : "trueName",
					title : "姓名",
					width : 350,
					sortable : true,
					formatter: function(value,row,index){
						var html='';
						if(row.userSex==1){
							html+='<img src="resource/images/user/U01.png" alt="男" style="vertical-align:middle;"/>';
						}else{
							html+='<img src="resource/images/user/U11.png" alt="女" style="vertical-align:middle;"/>';
						}
						return html+=' <a href="instantMessage/addPage.do?userId='+row.id+'&rel=<%=request.getParameter("rel")%>" target="dialog" width="1000" height="450" rel="<%=request.getParameter("rel")%>_add" title="发送即时消息">'+value+'</a>';
					}
				},
			 	 {
					field : "lastLoginTime",
					title : "最后登陆时间",
					align:"center",
					width : 180,
					sortable : true,
					formatter: function(value,row,index){
						if(value){
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					}
				}    
				
			
			] ]
		});
		
	});

//-->		
</script>
