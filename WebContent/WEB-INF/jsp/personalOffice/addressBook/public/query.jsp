<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-通讯录-公共通讯录
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<input id="${param.rel}_id" name="puGrouping" type="hidden" value="0" />
		<div class="search-content ">
			<span> <label>
					姓名：
				</label>
				<input type="text" name="puName" style="width: 90px"
					value="<c:out value="${param.puName }" />" />&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i> </span>
			<span> <label>
					单位名称：
				</label>
				<input type="text" name="puCompany" style="width: 90px"
					value="<c:out value="${param.puCompany }" />" /> </span>
			<span> <label>
					移动电话：
				</label>
				<input type="text" name="puPhone" style="width: 90px"
					value="<c:out value="${param.puPhone }" />" /> </span>
			<span> <label>
					办公电话：
				</label>
				<input type="text" name="puOfficPhone" style="width: 90px"
					value="<c:out value="${param.puOfficPhone }" />" /> </span>
		</div>


		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>


				 <shiro:hasPermission name="public:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true" href="public/addPage.do?rel=${param.rel }"
						target="dialog" width="1000" height="500" rel="${param.rel}_add" title="添加人员" ><span>新增人员</span>
					</a>
				</shiro:hasPermission>
				  <shiro:hasPermission name="public:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="修改人员"
						href="public/updatePage.do?id={id}&rel=${param.rel}"
						target="dialog" width="1000" height="500"
						rel="${param.rel}_update" warn="请先选择一条信息"><span>修改人员</span>
					</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="public:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true" href="public/del.do?rel=${param.rel }"
						target="selectedTodo" rel="ids" title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span>
					</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="publicGroup:read">
					<a  class="easyui-linkbutton" icon="icon-edit" plain="true" href="publicGroup/load.do?rel=${param.rel }_group"
						target="navTab" rel="${param.rel }_group" title="公共通讯录分组维护" ><span>公共通讯录分组维护</span>
					</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="public:excel">
					<a  class="easyui-linkbutton" icon="icon-add" plain="true" href="public/excelPage.do?rel=${param.rel }"
						target="dialog" width="500" height="300" rel="${param.rel}_imp" title="Excel导入" ><span>Excel导入</span>
					</a>
				</shiro:hasPermission> 
				</span><span style="float: right">
					<button class="btn btn-primary btn-small" type="submit">
						查询
					</button>&nbsp;
					<button class="btn btn-small clear" type="button">
						清空
					</button>&nbsp; </span>
		</div>
	</form>

</div>

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar" border="true"
	title="公共通讯录"></table>

<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "public/queryGrp.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "puName",
										title : "姓名",
										width : 140,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="public/find.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="通讯录详情">'
													+ value + '</a>';
										}

									}, {
										field : "puSex",
										title : "性别",
										width : 46,
										align : "center",
										sortable : true

									}, {
										field : "puGrouping",
										title : "所属分组",
										width : 100,
										align : "center"

									}, {
										field : "puCompany",
										title : "单位名称",
										align : "center",
										sortable : true

									}, {
										field : "puPost",
										title : "职位",
										width : 80,
										align : "center",
										sortable : true

									}, {
										field : "puPhone",
										title : "移动电话",
										width : 120,
										align : "center",
										sortable : true

									}, {
										field : "puOfficPhone",
										title : "办公电话",
										width : 120,
										align : "center",
										sortable : true

									}, {
										field : "puEmail",
										title : "电子邮件",
										width : 150,
										align : "center",
										sortable : true

									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openDialog('修改人员','public/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:500});
							}
						});
						
					});
//-->
</script>





