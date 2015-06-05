<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤设置---查找带回(多选)
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content " >
			
			<span>
				<label>名称：</label>
				<input	type="text" name="name" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			 
			
			
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<a class="easyui-linkbutton"  icon="icon-add"	plain="true" warn="请先选择登记时间设置" 
				 href="javascript:;"  onclick="$.multLookup(this,true,'${param.rel }_datagrid')" >带回并覆盖</a>
				 
				 <a class="easyui-linkbutton"  icon="icon-add"	plain="true" warn="请先选择登记时间设置" 
				 href="javascript:;"  onclick="$.multLookup(this,false,'${param.rel }_datagrid')" >带回并追加</a>
				
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

			url : "attendance/lookUp.do",
			queryParams: {
				type: '<%=request.getParameter("type")%>'
				
			},
			mustParamNames:"id,type",
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
						width : 180,
						formatter: function(value,row,index){
							
							return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.name+"'"+"})>"+value+"</a>";
							
						}
					}
			]],
			columns : [ [ 
			    
				{
					field : "createuser",
					title : "创建人",
					align:"center",
					width : 250
				} 
			 	  
			
			] ]
		});
		
	});

//-->		
</script>
	

