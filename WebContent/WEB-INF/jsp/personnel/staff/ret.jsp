<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理--人员管理--退休
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');">
		<input id="${param.rel}_id" name="puGrouping" type="hidden" value="0" />
		<div class="search-content ">
			<span> <label>
									姓名：</label><input type="text" name="psName" style="width: 90px" value="<c:out value="${param.psName }" />" />
								&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
								</span>
								
								<span> <label>
									性别：</label>
									<select name="psSex" class="combox" sValue="${param.psSex } ">
											<option value="">--请选择--</option>
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
								</span>
								
								<span> <label>
									籍贯：</label><input type="text" name="psHomeTown" style="width: 90px" value="<c:out value="${param.psHomeTown }" />" />
								</span>
								
								<span> <label>离职时间：</label>
										<input type="text"  readonly="readonly" style="width: 90px" onFocus="WdatePicker()"  name="staDate" value="${param.staDate }" />
										<span class="limit">-</span>
										<input type="text"  readonly="readonly" style="width: 90px" onFocus="WdatePicker()" name="endDate" value="${param.endDate }" />
								</span>
						</div>
						<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

<%--					<my:validationAuthority url="staff/staffAdd.do">--%>
<%--						<a class="easyui-linkbutton" icon="icon-add" plain="true"--%>
<%--							href="staff/staffAdd.do?rel=${param.rel }" target="navTab" width="1000" height="500"--%>
<%--							rel="${param.rel}_add" ><span>添加员工</span>--%>
<%--						</a>--%>
<%--					</my:validationAuthority>--%>
					<shiro:hasPermission name="staff:update">
						<a class="easyui-linkbutton" icon="icon-edit" plain="true" href="staff/updatePage.do?id={id}&rel=${param.rel}"  target="navTab"
							rel="${param.rel}_update" warn="请选择一条信息"  title="修改员工"><span>修改员工</span>
						</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="staff:delete">
						<a class="easyui-linkbutton" icon="icon-remove" plain="true" href="staff/del.do?rel=${param.rel}" target="selectedTodo"
							rel="ids" title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span>
						</a>
					</shiro:hasPermission>
<%--					<my:validationAuthority url="staff/updateStatusPage.do">--%>
<%--						<a  class="easyui-linkbutton" icon="icon-edit" plain="true" href="staff/updateStatusPage.do?id={id}&status=在职&rel=${param.rel}"  width="800" height="450"  target="dialog"--%>
<%--							rel="${param.rel}" warn="请选择一条信息"><span>更新状态</span>--%>
<%--						</a>--%>
<%--					</my:validationAuthority>		--%>
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true" 
					title="高级搜索" href="staff/searchTag.do?search=work&rel=${param.rel}" target="dialog"  width="650" height="350"  rel="${param.rel}_queryfilter"  >高级查询</a>
						</span><span style="float: right">
					<button class="btn btn-primary btn-small" type="submit">
						查询
					</button>&nbsp;
					<button  class="btn btn-primary btn-small"  target="dialog"  width="850" height="620"  rel="${param.rel }_select" title="查询框" href="staff/searchTag.do?search=ret&rel=${param.rel}" >
						高级检索</button>
					&nbsp;
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
							url : "staff/queryRet.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "psName",
										title : "姓名",
										width : 140,
										align : "center",
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="staff/find.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="人员详情">'
													+ value + '</a>';
										}

									}, {
										field : "psSex",
										title : "性别",
										width : 70,
										align : "center",
										sortable : true

									}, {
										field : "psAge",
										title : "年龄",
										width : 70,
										sortable : true,
										align : "center",
										sortable : true

									}, {
										field : "psEntryDate",
										title : "入职时间",
										width : 70,
										align : "center",
										sortable : true,
										formatter: function(value,row,index){
											if(row.psEntryDate){
												return new Date(row.psEntryDate).format("yyyy-MM-dd");
											}
										}

									}, {
										field : "psHomeTown",
										title : "籍贯",
										width : 342,
										align : "center",
										sortable : true

									}

							] ],
							onDblClickRow:function(rowIndex, rowData){
								MUI.openCenterTab('修改员工','staff/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
							}
						});

	});
//-->
</script>
								
								
								
								
						






