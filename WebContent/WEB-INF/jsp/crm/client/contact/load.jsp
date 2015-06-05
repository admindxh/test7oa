<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户管理--联系人管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true" >
		<div class="search-content ">
			<span> <label>
					姓名：
				</label> <input type="text" name="l.liName" style="width: 90px" op="like"
					 />&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i> 
			</span>
			
			<span> <label>性别:</label> 
					<select name="l.liSex" style="width: 100px;" submitForm="change">
						<option value="">全部</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
			</span>
			<span >
				<label>类型：</label>
				<select   name="l.liType"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<my:outOptions type="502"/>
				</select>
			</span>
			
			<span >
				<label>种类：</label>
				<select   name="l.liSpecies"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<my:outOptions type="501"/>
				</select>
			</span>
			<span >
				<label>客户：</label>
				<a href="info/lookUpPage.do" lookupGroup="customer" title="客户查询">
				    <input type="text" readonly="readonly" toName="customer.name"  /> </a>
					<input type="hidden"  name="l.customerId" toName="customer.id">
			</span>
				
		</div>
		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

				<shiro:hasPermission name="linkman:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true"
						href="linkman/addPage.do?rel=${param.rel }" title="添加客户联系人" target="navTab"
						width="1000" height="500" rel="${param.rel}_add"><span>新增客户联系人</span>
					</a>

				</shiro:hasPermission> 
				<shiro:hasPermission name="linkman:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="修改客户联系人"
						href="linkman/updatePage.do?id={id}&rel=${param.rel}"
						target="navTab" width="1000" height="500"
						rel="${param.rel}_update" warn="请先选择一条信息"><span>修改客户联系人</span>
					</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="linkman:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true"
						href="linkman/del.do?rel=${param.rel }" target="selectedTodo"
						rel="ids" title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span>
					</a>
					</shiro:hasPermission>
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true" 
					title="高级搜索" href="linkman/searchTag.do?rel=${param.rel}" target="dialog"  width="650" height="350"  rel="${param.rel}_queryfilter"  >高级查询</a>
						
				 </span>

			<span style="float: right">
				<button class="btn btn-primary btn-small" type="submit">
					查询
				</button>&nbsp;
				<button class="btn btn-small clear" type="button">
					清空
				</button>&nbsp; 
			</span>

		</div>
	</form>

</div>
<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar" border="true"
	></table>

<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "linkman/query.do?info=<%=request.getParameter("info")%>",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "name",
										title : "姓名",
										width : 80,
										align : "center",
										formatter: function(value,row,index){
						
											return '<a href="linkman/find.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="550"  rel="<%=request.getParameter("rel")%>_show"  title="联系人详情" >'+value+'</a>';
						
										}

									},
									{
										field : "sex",
										title : "性别",
										width : 40,
										align : "center",
										

									},
									{
										field : "manType",
										title : "类型",
										width :60,
										align : "center",
										
										

									},
									{
										field : "manSpecies",
										title : "种类",
										width : 60,
										align : "center"
										

									},
									{
										field : "customerName",
										title : "客户名称",
										width : 150,
										align : "center"
										

									},
									
									{
										field : "post",
										title : "职务",
										width : 80,
										align : "center",
										
										
 
									},
									
									{
										field : "phone",
										title : "电话",
										width : 60,
										align : "center"
										
 
									},
									{
										field : "userName",
										title : "创建人",
										width : 80,
										align : "center",
										
 
									},
									{
										field : "createtime",
										title : "创建时间",
										width : 90,
										align : "center",
										sortable : true,
										formatter: function(value,row,index){
						
											return new Date(value).format("yyyy-MM-dd HH:mm:ss");
										}
 
									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openCenterTab('修改客户','linkman/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
							}
						});
						
					});
//-->
</script














