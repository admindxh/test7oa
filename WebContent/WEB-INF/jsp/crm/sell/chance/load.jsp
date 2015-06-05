<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-销售管理-销售机会
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"
		formatFilterData="true">
		<div class="search-content ">
			<span> <label>
					主题：
				</label> <input type="text" name="scName" style="width: 90px" op="like" />&nbsp;<i
				class="boot_icon-question-sign" selectLike="tooltip"></i> </span>

			<span> <label>
					客户名称：
				</label> 
				<a href="info/lookUpPage.do" lookupGroup="customer" title="客户查询">
					<input type="text" toName="customer.name"  style="width: 90PX"    /> </a>
					<input type="hidden" id="Info" name="scInfo" toName="customer.id"  >
					
				
				</span>

			<span> <label>
					联系人：
				</label> 
				<a href="linkman/lookUpPage.do" lookupGroup="linkman" title="联系人查询" warn="请先点击选择客户!">
						<input type="text" toName="linkman.name" style="width: 90PX"   /> </a>
					<input type="hidden"  name="scLinkman" toName="linkman.id">
			</span>

			<span> <label>
					客户来源：
				</label> <select name="scSource" style="width: 100px;" submitForm="change">
					<option value="">
						全部
					</option>
					<my:outOptions type="601" />
				</select> </span>
			<span> <label>
					客户状态：
				</label> <select name="scStatus" style="width: 100px;" submitForm="change">
					<option value="">
						全部
					</option>
					<my:outOptions type="602" />
				</select> </span>




		</div>


		<div class="search-toolbar">
			<span style="float: left;"> <a
				class="easyui-linkbutton clearDgChecked" icon="icon-redo"
				plain="true" title="清空所有勾选项">清空勾选</a> <shiro:hasPermission
					name="chance:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true"
						href="chance/addPage.do?rel=${param.rel }" title="添加销售机会"
						target="navTab" width="1000" height="500" rel="${param.rel}_add"><span>新增销售机会</span>
					</a>

				</shiro:hasPermission> <shiro:hasPermission name="chance:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true"
						title="修改销售机会" href="chance/updatePage.do?id={id}&rel=${param.rel}"
						target="navTab" width="1000" height="500"
						rel="${param.rel}_update" warn="请先选择一 条信息"><span>修改销售机会</span> </a>
				</shiro:hasPermission> <shiro:hasPermission name="chance:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true"
						href="chance/del.do?rel=${param.rel }" target="selectedTodo"
						rel="ids" title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span> </a>
			
				</shiro:hasPermission> <shiro:hasPermission name="chance:allocation">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true"
						title="销售机会分配" href="chance/allocationPage.do?id={id}&rel=${param.rel }"
						target="navTab" width="1000" height="500" rel="" warn="请先选择一 条信息"><span>销售机会分配</span>
					</a>
				</shiro:hasPermission> 
				<shiro:hasPermission name="activity:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true"
						href="activity/addPage.do?id={id}&rel=xsgl_khhd" title="添加客户活动" target="navTab"
						width="1000" height="500" rel="xsgl_khhd_add"><span>添加客户活动</span>
					</a>
					</shiro:hasPermission>
				
<%--				<a class="easyui-linkbutton" icon="icon-search" plain="true"--%>
<%--				title="高级搜索" href="chance/searchTag.do?rel=${param.rel}"--%>
<%--				target="dialog" width="650" height="350"--%>
<%--				rel="${param.rel}_queryfilter">高级查询</a> --%>
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
	border="true"></table>

<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "chance/query.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "scName",
										title : "主题",
										width : 80,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="chance/find.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="机会详情">'
													+ value + '</a>';
										}


									},
									{
										field : "info",
										title : "客户名称",
										width : 140,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="info/find.do?id='
													+row.scInfo
													+ '" target="navTab"    rel="${param.rel}_find" title="客户详情">'
													+ value + '</a>';
										}

									},
									{
										field : "linkman",
										title : "联系人",
										width : 100,
										align : "center",
										formatter: function(value,row,index){
											return '<a  datagrid="${param.rel }_datagrid" href="linkman/find.do?id='
											+row.scLinkman
											+ '&rel=<%=request.getParameter("rel")%>_show" target="navTab"    rel="${param.rel}_find" title="联系人详情">'
											+ value + '</a>';
						
										}

									},
									{
										field : "phone",
										title : "联系人电话",
										width : 100,
										align : "center"
										

									},
									{
										field : "scSource",
										title : "客户来源",
										width : 100,
										align : "center"
										

									},
									
									{
										field : "scStatus",
										title : "客户状态",
										width : 80,
										align : "center",
										sortable : true
										
 
									},
									
									{
										field : "scPicture",
										title : "描述",
										width : 180,
										align : "center"
										
 
									},
									{
										field : "操作",
										title : "操作",
										width : 100,
										align : "center",
										formatter: function(value,row,index){
											return '<a  datagrid="${param.rel }_datagrid" href="activity/load.do?chanceid='
											+row.id
											+ '&rel=xsgl_khhd" target="navTab"    rel="xsgl_khhd" title="商务活动">商务活动</a>';
						
										}

									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openCenterTab('修改机会','chance/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
							}
						});
						
					});
</script>







