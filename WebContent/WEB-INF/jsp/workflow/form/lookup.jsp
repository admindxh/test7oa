<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--表单设计--查找带回
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'flow_form_toolbar_datagrid');" >
		
		<div  class="search-content " >
			<span>
				<label>表单名：</label>
				<input	type="text" name="formName" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				
			</span>
			
		</div>
	
		<div class="search-toolbar" >
			
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
		var lookupType='<%=(String)request.getParameter("type")%>';
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap : false,
			url : 'form/query.do',
			queryParams: {
				type:lookupType
			},
			columns : [ [
					{
						field : 'formName',
						title : '表单名称',
						width : 150,
						align:'center',
						formatter : function(value, row, index) {
							
							return '<a href="form/show.do?id='+row.id+'" target="dialog" width="1000" height="450" rel="<%=request.getParameter("rel")%>_show" title="预览表单设计">'+value+'</a>';
							
						}
					},
		             {
		 				field : 'formPath',
		 				title : 'url',
		 				align:'center',
		 				width : 100
		 				
		 			},
		              {
					field : 'formDesc',
					title : '说明',
					align:'center',
					width : 100
					
					
					
				},
				{
					field : 'opt',
					title : '操作',
					width : 50,
					align:'center',
					formatter : function(value, row, index){
						
						return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',formName:'"+row.formName+"'})>选中</a>";
							
					}
				}
			] ]
		});
		
	});

//-->		
</script>