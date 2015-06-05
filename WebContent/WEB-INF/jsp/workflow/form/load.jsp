<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--表单设计
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			<span >
				<label>类型：</label>
				<select   name="type"  style="width: 100px;" submitForm="change">
					<option value="">全部类型</option>
					<option value="1">工作表单</option>
					<option value="2">任务表单</option>
				</select>
			</span>
	
			<span>
				<label>名称：</label>
				<input	type="text" name="formName" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
		
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="form:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加表单设计"	 href="form/addPage.do?rel=${param.rel }"  target="dialog" width="1000" height="450" rel="${param.rel}_add" >添加</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="form:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改表单设计"	href="form/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="1000" height="450" rel="${param.rel}_update" warn="请先选择一个表单" >修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="form:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="form/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个表单">批量删除</a>
				</shiro:hasPermission>
				
				<a  class="easyui-linkbutton"  icon="icon-search"	plain="true"	
					title="表单测试"	 href="form/testPage.do?rel=${param.rel }" target="dialog" width="1000" height="450" rel="${param.rel}_test" >测试</a>
					
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
			nowrap:false,
			url : "form/query.do",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				{
					field : "type",
					title : "类型",
					align:"center",
					width : 80,
					formatter: function(value,row,index){
						if(1==value){
							return "工作表单";
						}else{
							return "任务表单";
						}
					}
				},
				 {
					field : "formName",
					title : "名称",
					align:"center",
					width : 200,
					formatter: function(value,row,index){
						
						return '<a href="form/show.do?id='+row.id+'" target="dialog" width="800" height="400" rel="<%=request.getParameter("rel")%>_show" title="预览表单设计">'+value+'</a>';
					}
				},
				{
					field : "formDesc",
					title : "备注",
					align:"center",
					width : 250
				},
			 	 {
					field : "updateUserName",
					title : "更新人",
					align:"center",
					width : 120
				},
			 	 {
					field : "updateTime",
					title : "更新时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}    
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改表单设计','form/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>