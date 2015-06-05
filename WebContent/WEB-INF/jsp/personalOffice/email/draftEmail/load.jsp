<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-内部邮件-草稿箱  email/loadDraft.do   
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div class="search-content ">

			<span> <label>
					标题：
				</label> <input type="text" name="name" style="width: 150PX"
					 /> 
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
					</span>

			<span> <label>
					发送日期：
				</label> <input type="text" readonly="readonly" onFocus="WdatePicker()" style="width: 100PX"
					name="date1"  /> <span class="limit">-</span>
				<input type="text" readonly="readonly" onFocus="WdatePicker()" style="width: 100PX"
					name="date2" /> </span> 
		</div>

		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

						<a class="easyui-linkbutton" icon="icon-remove" plain="true"
							href="email/deldraft.do" target="selectedTodo" rel="ids"
							title="确定要删除吗?" warn="至少选择一条信息"><span>批量删除</span>
						</a>
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

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar"
	></table>


<script type="text/javascript">	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid')
				.datagrid(
						{
							nowrap : false,//true:只在一行显示数据,false:内容多时自动换行，效率低
<%--							multiSort : true,//是否进行多字段排序--%>
							url : "email/queryDraft.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "edName",
										title : "标题",
										width : 240,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="email/edit.do?id='
													+ row.id
													+ '" target="navTab"    rel="${param.rel}_find" title="邮件详情">'
													+ value + '</a>';
										}

									},
									{
										field : "edInUid",
										title : "收件人",
										width : 170,
										align : "center"

									},
									{
										field : "edDate",
										title : "保存时间",
										width : 100,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {

											return new Date(value)
													.format("yyyy-MM-dd  HH:mm:ss");
										}

									}

							] ]
						});

	});
//-->
</script>





