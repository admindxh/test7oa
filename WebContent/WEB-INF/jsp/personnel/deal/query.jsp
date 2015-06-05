<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-人员合同
--%>

<div id="${param.rel}_toolbar"  class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');">
		<div class="search-content ">

			<span> <label>
					合同名称：
				</label>
				<input type="text" name="pdName" style="width: 90px" /> &nbsp;<i
				class="boot_icon-question-sign" selectLike="tooltip"></i> </span>
			<span> <label>
					员工姓名：
				</label> <a href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询"> <input
						readonly="readonly" type="text" toName="user.trueName"
						style="width: 90px" /> </a> <input type="hidden" name="pdUid"
					toName="user.id" /> </span>

			<span> <label>
					合同类型：
				</label> <select class="combox" name="pdType" style="width: 90px">
					<option value="">
						-请选择-
					</option>
					<my:outOptions type="701"/>
				</select> </span>
			<span> <label>
					合同状态:
				</label> <select class="combox" name="pdStatus" style="width: 90px">
					<option value="">
						-请选择-
					</option>
					<my:outOptions type="702"/>
				</select> </span>

			<span> <label>
					合同范围：
				</label> <input type="text" readonly="readonly" onFocus="WdatePicker()"
					style="width: 90PX" name="pdStart" /> <span class="limit">-</span>
				<input type="text" readonly="readonly" onFocus="WdatePicker()"
					style="width: 90PX" name="pdEnd" />
					 </span>

		</div>

		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				 <shiro:hasPermission name="deal:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true"
						title="添加合同" href="deal/addPage.do?rel=${param.rel }"
						target="navTab" width="1000" height="500" rel="${param.rel}_add"><span>添加合同</span>
					</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="deal:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true"
						title="修改合同" href="deal/updatePage.do?id={id}&rel=${param.rel}"
						target="navTab" width="1000" height="500"
						rel="${param.rel}_update" warn="请先选择一 条信息"><span>修改合同</span> </a>
				</shiro:hasPermission>
				<shiro:hasPermission name="deal:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true"
						href="deal/del.do" target="selectedTodo" rel="ids" title="确定要删除吗?"
						warn="至少选择一条信息"><span>批量删除</span> </a>
				</shiro:hasPermission>
				</span>
			<span style="float: right">
				<button class="btn btn-primary btn-small" type="submit">
					查询
				</button>&nbsp;
				<button class="btn btn-small clear" type="button">
					清空
				</button>&nbsp;</span>
		</div>


	</form>


</div>

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar"></table>


<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "deal/query.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "pdName",
										title : "合同名称",
										width : 240,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="deal/find.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="合同详情">'
													+ value + '</a>';
										}

									},
									{
										field : "pdUid",
										title : "员工姓名",
										width : 170,
										align : "center"

									},
									{
										field : "pdType",
										title : "合同类型",
										width : 150,
										align : "center",
										sortable : true

									},
									{
										field : "pdStatus",
										title : "合同状态",
										width : 150,
										align : "center",
										sortable : true
									},
									{
										field : "pdSignDate",
										title : "签订日期",
										width : 150,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {
											return new Date(value)
													.format("yyyy-MM-dd");
										}
									},
									{
										field : "pdStart",
										title : "合同生效日期",
										width : 150,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {
											return new Date(value)
													.format("yyyy-MM-dd");
										}
									},
									{
										field : "pdEnd",
										title : "合同结束日期",
										width : 150,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {
											return new Date(value)
													.format("yyyy-MM-dd");
										}
									},
									{
										field : "pdStaffId",
										title : "创建人",
										width : 150,
										align : "center",
										sortable : true
									},
									{
										field : "pdDate",
										title : "创建日期",
										width : 150,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {
											return new Date(value)
													.format("yyyy-MM-dd");
										}
									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openCenterTab('修改合同','deal/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
							}
						});

	});
//-->
</script>





