<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--数据库备份
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
			<span class="alert alert-info">
				
				系统自动备份数据库，文件保存在服务器，为防止服务器意外数据丢失，建议定期导出备份的数据。
			</span>
			
			
			
		</div>
	

		<div class="search-toolbar"  >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="database:backup">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="手动备份数据库" href="database/backupPage.do?rel=${param.rel}"  target="dialog" width="700" height="350" rel="${param.rel}_bf" >手动备份</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="database:uploadRestore">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="手动恢复数据库"	href="database/uploadFilePage.do?rel=${param.rel}" target="dialog" width="700" height="350" rel="${param.rel}_hf" >手动恢复</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="database:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="database/del.do" target="selectedTodo" field="name" title="确定要删除吗?" warn="至少选择一个备份文件">批量删除</a>
				</shiro:hasPermission>
				
				
				
			</span>
			
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "database/query.do",
			nowrap:false,
			showFooter:true,
			pagination:false,
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
			    
				{
					field : "name",
					title : "文件名称",
					align:"center",
					width : 250
				},
			 	 {
					field : "size",
					title : "文件大小",
					align:"center",
					width : 150
				},
				{
					field : "time",
					title : "创建时间",
					align:"center",
					width : 150,
					formatter: function(value,row,index){
						if(value){
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					}
				},
			 	 {
					field : "op",
					title : "操作",
					align:"center",
					width : 100,
					formatter: function(value,row,index){
						if(!value){
							return '<a  href="database/export.do?name='+row.name+'" target="_blank" >导出备份</a> | <a  href="database/restore.do?name='+row.name+'"  target="ajaxTodo" title="确定恢复此版本数据吗?" >恢复数据</a>';
						}
					
					}
				} 
			 	 
			
			] ]
		});
		
	});

//-->		
</script>
