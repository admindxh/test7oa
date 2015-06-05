<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-内部邮件-收件箱
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');">
		<div class="search-content ">

			<span> <label>
					标题：
				</label> <input type="text"  name="name" style="width: 150PX"
					 />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
					 </span>
			<span> <label>
					邮件状态：
				</label>
					<select name="status" class="combox" sValue="${param.status}"  submitForm="change" style="width: 100PX" >
						<option value="">
							--请选择--
						</option>
						<option value="1">
							已读
						</option>
						<option value="0">
							未读
						</option>
					</select>
					</span>
				 <span> <label>
						发送日期：
					</label> <input type="text" readonly="readonly" onFocus="WdatePicker()" style="width: 100PX"
						name="date1" /> <span class="limit">-</span>
					<input type="text" readonly="readonly" onFocus="WdatePicker()" style="width: 100PX"
						name="date2" /> </span>
		</div>
		<div class="search-toolbar">
			<span style="float: left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

						<a class="easyui-linkbutton" icon="icon-remove" plain="true"
							href="email/delIn.do" target="selectedTodo" rel="ids"
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
							url : "email/queryIn.do",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "ioStatus",
										title : "状态",
										width : 30,
										align : "center",
										sortable : true,
										formatter: function(value,row,index){
											if(value=='未读'){
												return '<img src="resource/images/email_close.gif" alt="未读"  style="margin-top: 3px;"/>';
											}else{
												return '<img src="resource/images/email_open.gif" alt="已读" style="margin-top: 3px;"/>';
											}
										}
										
									},
									{
										field : "eiName",
										title : "标题",
										width : 240,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {

											return '<a  datagrid="${param.rel }_datagrid" href="email/findIn.do?id='
													+ row.id
													+ '&rel=<%=request.getParameter("rel")%>_find" target="navTab"    rel="${param.rel}_find" title="邮件详情">'
													+ row.eiName + '</a>';
										}


									},
									{
										field : "eiUid",
										title : "发件人",
										width : 170,
										align : "center"
										
									},
									{
										field : "eiDate",
										title : "发送时间",
										width : 100,
										align : "center",
										sortable : true,
										formatter : function(value, row, index) {

											return new Date(row.eiDate)
													.format("yyyy-MM-dd  HH:mm:ss");
										}

									}

							] ]
						});

	});
//-->
</script>






