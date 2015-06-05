<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-通讯录-个人通讯录-分组设置
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form 	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');">
		<input id="${param.rel}_deptId" type="hidden" value="0" />
		<div class="search-content ">
			<span><label>
							名称：
							</label>
							<input type="text" name="grName" />
						</span>
						
					
			</div>
			<div class="search-toolbar">
			<span style="float: left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
			
			 <shiro:hasPermission name="group:add">
				<a class="easyui-linkbutton" icon="icon-add" plain="true" href="group/addPage.do?rel=${param.rel }" target="dialog" width="800" height="400" rel="${param.rel}_add" title="新增分组"><span>新增分组</span></a>
			</shiro:hasPermission>
			<shiro:hasPermission name="group:update">
				<a class="easyui-linkbutton" icon="icon-edit" plain="true"   href="group/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" title="修改分组" warn="请先选择一个分组"><span>修改分组</span></a>
			</shiro:hasPermission>
			<shiro:hasPermission name="group:delete">
				<a class="easyui-linkbutton" icon="icon-remove" plain="true" href="group/del.do" target="selectedTodo" rel="ids" title="确定要删除吗?" warn="至少选择一条分组"><span>批量删除</span></a>
			</shiro:hasPermission>
			</span>
			<span style="float: right">
				<button class="btn btn-primary btn-small" type="submit">
					查询
				</button>&nbsp;
				<button class="btn btn-small clear" type="button">
					清空
				</button>&nbsp; </span>
		</div>
	</form>
</div>


<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar"
	></table>



<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--			multiSort:true,//是否进行多字段排序--%>
			url : "group/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "grName",
					title : "名称",
					width : 350,
					align:"center",
					sortable : true

				},
			
				{
					field : "grRemark",
					title : "备注",
					width : 80,
					align:"center",
					sortable : true

				}
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改分组','group/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
	

