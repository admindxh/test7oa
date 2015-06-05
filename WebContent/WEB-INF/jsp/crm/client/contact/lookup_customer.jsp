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
			
			<span >
				<label>手机：</label>
				<input type="text" name="l.liMobilePhone" style="width: 90px" />
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
							url : "linkman/lookUp.do?info=<%=request.getParameter("id") %>",
							columns : [ [
											{
												field : "ck",
												title : "勾选",
												checkbox : true
											},
											{
												field : "customerName",
												title : "客户名称",
												width : 150,
												align : "center"
												

											},
											{
												field : "name",
												title : "姓名",
												width : 80,
												align : "center",
												formatter: function(value,row,index){
													
													return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.name+"',phone:'"+row.phone+"'})>"+value+"</a>";
									
												}

											},
											{
												field : "phone",
												title : "手机",
												width : 60,
												align : "center"
												
		 
											},
											{
												field : "post",
												title : "职务",
												width : 80,
												align : "center"
												
												
		 
											}
									] ]
						});
						
					});
//-->
</script














