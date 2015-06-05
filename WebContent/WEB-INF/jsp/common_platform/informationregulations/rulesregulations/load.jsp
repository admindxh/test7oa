<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公各平台--规章制度管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<!-- 查询条件区域 -->
		
		<div class="search-content">
			<span>
				<label>标题：</label>
				<input type="text" name="rulestitle"  class="span2" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>类型：</label>
				<select name="typeid" style="width: 110px;">
					<option value="">==请选择==</option>
					<my:outOptions type="8"/>	
				</select>
			</span>
		</div>
		<!-- 功能区域 -->
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
			
				<shiro:hasPermission name="rules:add">
				<a  class="easyui-linkbutton"  icon="icon-add"	plain="true" title="添加规章" href="rules/addPage.do?rel=${param.rel }" target="navTab" rel="${param.rel}_add"><span>添加规章 </span></a>
				
			</shiro:hasPermission>
			<shiro:hasPermission name="rules:update">
				<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" title="修改规章" href="rules/updatePage.do?id={id}&rel=${param.rel}" target="navTab" rel="${param.rel}_update" ><span>修改规章</span></a>
				
			</shiro:hasPermission>
			<shiro:hasPermission name="rules:delete">
				<a class="easyui-linkbutton"  icon="icon-remove"	plain="true" href="rules/delete.do" target="selectedTodo" rel="ids" title="确定要删除吗?" ><span>批量删除</span></a>
			</shiro:hasPermission>
			
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		</div>
			</form>
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"   ></table>	

<script type="text/javascript">
<!--
	$(function(){
			var _toolbar=$('#<%=request.getParameter("rel")%>_toolbar');
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "rules/loadallrules.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[
					{
						field : "rulestitle",
						title : "标题",
						align:"center",
						width : 100,
						formatter: function(value,row,index){
							
							return '<a href="rules/lookrules.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="navTab"  rel="<%=request.getParameter("rel")%>_show"  title="查看详情" >'+value+'</a>';
							
						}
					},
					{
						field : "typeid",
						title : "规章类型",
						align:"center",
						width : 50
					},
					{
						field : "promulgator",
						title : "发布人",
						align:"center",
						width : 60,
						
					},
					{
						field : "createdate",
						title : "发布时间",
						align:"center",
						width : 50,
						sortable : true,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					}
					
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openCenterTab('修改规章','rules/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
				}
			
			});
		});
//-->
</script>	
	