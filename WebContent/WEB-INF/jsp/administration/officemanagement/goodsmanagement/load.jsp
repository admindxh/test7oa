<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
行政办公--办公用品管理--物品管理--查看物品管理页面--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<!-- 查询条件区域 -->
		<div class="search-content">
				<span>
					<label>物品名称:</label>
					<input type="text" name="usingname"  />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				</span>
				<span>
					<label>类型:</label>
					<select name="typeId">
					    <option value="">全部</option>
					    <my:outOptions type="16" />
				    </select>
				</span>
		</div>
			<!-- 功能区域 -->
		<div class="search-toolbar" >
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				   
				  <shiro:hasPermission name="goods:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加物品"	 href="goodsmanagerment/addPage.do?rel=${param.rel }" target="dialog" width="900" height="450"  rel="${param.rel}_add" >添加物品</a>
				 </shiro:hasPermission> 
				  <shiro:hasPermission name="goods:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改物品"	 href="goodsmanagerment/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="900" height="450"  rel="${param.rel}_update" >修改物品</a>
				</shiro:hasPermission> 
				 <shiro:hasPermission name="goods:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="goodsmanagerment/delete.do" target="selectedTodo"  title="请谨慎操作！确定要删除吗?" warn="至少选择一个">批量删除</a>
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
			
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "goodsmanagerment/loadall.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[{
						field : "usingname",
						title : "物品名称",
						align:"center",
						width : 100,
						formatter: function(value,row,index){
							
							return '<a href="goodsmanagerment/lookpage.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看物品详情" >'+value+'</a>';
							
						}
						
					},
					{
						field : "typeId",
						title : "办公类型",
						align:"center",
						width : 50
						
						
					},
					{
						field : "specificationstype",
						title : "型号",
						align:"center",
						width : 80
						
					},
					{
						field : "danwei",
						title : "单位",
						align:"center",
						width : 60
						
					},
					{
						field : "currentinventory",
						title : "总数",
						align:"center",
						width : 50
						
					},
					{
						field : "userName",
						title : "创建人",
						align:"center",
						width : 50
						
					},
					{
						field : "createdate",
						title : "创建时间",
						align:"center",
						width : 70,
						sortable : true,
						formatter: function(value,row,index){
							
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					}
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openDialog('修改物品','goodsmanagerment/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:900,height:450});
				}
			
			});
		});
//-->
</script>	
	