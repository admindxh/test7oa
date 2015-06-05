<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-通讯录-个人通讯录
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form 	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');">
		<input id="${param.rel}_id" name="peGrouping" type="hidden" value="0" />
		<div class="search-content ">
			<span> <label>
					姓名：
				</label> <input type="text" name="pjName" style="width: 90px"
					 />&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i> </span>
			<span> <label>
					学历：
				</label>
				<input type="text" name="pjEducation" style="width: 90px"
					 /> </span>
			<span> <label>
					岗位：
				</label>
				<input type="text" name="pjPost" style="width: 90px"
					 /> </span>
			<span> <label>
					求职状态：
				</label>
				<input type="text" name="pjJobStatus" style="width: 90px"
					 /> </span>

		</div>


		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

				<shiro:hasPermission name="job:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true" title="录入简历"
						href="job/addPage.do?rel=${param.rel }" target="navTab"
						width="1000" height="500" rel="${param.rel}_add"><span>录入简历</span>
					</a>

				</shiro:hasPermission>
				<shiro:hasPermission name="job:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="修改简历"
						href="job/updatePage.do?id={id}&rel=${param.rel}"
						target="navTab" width="1000" height="500"
						rel="${param.rel}_update" warn="请先选择一 条信息"><span>修改简历</span>
					</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="job:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true"
						href="job/del.do?rel=${param.rel }" target="selectedTodo"
						rel="ids" title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span>
					</a>
			
				</shiro:hasPermission></span>
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

<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "job/query.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "pjName",
										title : "姓名",
										width : 80,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="job/find.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="简历详情">'
													+ value + '</a>';
										}

									},
									{
										field : "pjSex",
										title : "性别",
										width : 46,
										align : "center",
										sortable : true

									},
									{
										field : "pjBirthdate",
										title : "出生日期",
										width : 120,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjPhone",
										title : "联系方式",
										width : 120,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjQq",
										title : "QQ",
										width : 100,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjEducation",
										title : "学历",
										width : 120,
										align : "center",
										sortable : true

									},
									{
										field : "pjExperience",
										title : "经验",
										width : 160,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjPost",
										title : "应聘岗位",
										width : 150,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjSalary",
										title : "期望薪资",
										width : 100,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjJobStatus",
										title : "求职状态",
										width : 100,
										align : "center",
										sortable : true
										

									},
									{
										field : "pjDate",
										title : "填表日期",
										width : 150,
										align : "center",
										sortable : true,
										formatter: function(value,row,index){
											if(value){
												return new Date(value).format("yyyy-MM-dd");
											}
										}
										

									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openCenterTab('修改简历','job/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
							}
						});

	});
</script>







