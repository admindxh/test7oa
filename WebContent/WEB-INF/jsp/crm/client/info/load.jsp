<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户管理-客户信息
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true" >
		<div class="search-content ">
			<span> <label>
					客户编号：
				</label> <input type="text" name="clNo" style="width: 90px" op="like"
					 />&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i> </span>
			
			<span> <label>
					客户名称：
				</label> <input type="text" name="clName" style="width: 90px" op="like"
					 />&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i> </span>
			
			<span >
				<label>客户类别：</label>
				<select   name="clCategory"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<my:outOptions type="408"/>
				</select>
			</span>
			
			<span >
				<label>客户等级：</label>
				<select   name="clGrade"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<my:outOptions type="403"/>
				</select>
			</span>
			<span >
				<label>所属行业：</label>
				<select   name="clIndustry"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<my:outOptions type="407"/>
				</select>
			</span>	
			
			
			

		</div>


		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

				 <shiro:hasPermission name="info:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true"
						href="info/addPage.do?rel=${param.rel }" title="添加客户" target="navTab"
						width="1000" height="500" rel="${param.rel}_add"><span>新增客户</span>
					</a>

				</shiro:hasPermission>
				<shiro:hasPermission name="info:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="修改客户"
						href="info/updatePage.do?id={id}&rel=${param.rel}"
						target="navTab" width="1000" height="500"
						rel="${param.rel}_update" warn="请先选择一 条信息"><span>修改客户</span>
					</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="info:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true"
						href="info/del.do?rel=${param.rel }" target="selectedTodo"
						rel="ids" title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span>
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:share">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="客户共享"
						href="info/sharePage.do?id={id}&rel=${param.rel }" target="navTab" width="1000" height="500"
						rel="" warn="请先选择一 条信息"><span>客户共享</span>
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:shareUpdate">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="修改客户共享"
						href="info/shareUpdatePage.do?id={id}&rel=${param.rel }" target="navTab" width="1000" height="500"
						rel="" warn="请先选择一 条信息"><span>修改客户共享</span>
					</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:shift">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true" title="客户转移"
						href="info/shiftPage.do?id={id}&rel=${param.rel }" target="navTab" width="1000" height="500"
						rel="" warn="请先选择一 条信息"><span>客户转移</span>
					</a>
					</shiro:hasPermission>
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true" 
					title="高级搜索" href="info/searchTag.do?rel=${param.rel}" target="dialog"  width="650" height="350"  rel="${param.rel}_queryfilter"  >高级查询</a>
						
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

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar" border="true"
	></table>

<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "info/query.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "clNo",
										title : "客户编号",
										width : 80,
										align : "center"

									},
									{
										field : "clName",
										title : "客户名称",
										width : 140,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="info/find.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="客户详情">'
													+ value + '</a>';
										}

									},
									{
										field : "clGrade",
										title : "客户等级",
										width : 100,
										align : "center",
										sortable : true
										

									},
									{
										field : "clRelation",
										title : "客户关系",
										width : 100,
										align : "center"
										

									},
									{
										field : "clIndustry",
										title : "所属行业",
										width : 100,
										align : "center"
										

									},
									
									{
										field : "clStaff",
										title : "负责人",
										width : 80,
										align : "center",
										sortable : true
										
 
									},
									
									{
										field : "clRemark",
										title : "备注",
										width : 180,
										align : "center"
										
 
									},
									{
										field : "操作",
										title : "操作",
										width : 140,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="linkman/load.do?info='
													+ row.id
													+ '&rel=crm_lxrgl" target="navTab"    rel="crm_lxrgl" title="查看联系人">查看联系人</a>';
										}

									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openCenterTab('修改客户','info/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
							}
						});
						
					});
//-->
</script>







