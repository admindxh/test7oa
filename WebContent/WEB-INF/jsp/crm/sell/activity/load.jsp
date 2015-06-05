<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-商务活动管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true" >
		<div class="search-content ">
			<span> <label>
					标题：
				</label> <input type="text" name="acTitle" style="width: 90px" op="like"
					 />&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i> 
			</span>
			
			<span >
				<label>活动方式：</label>
				<select   name="acType"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<my:outOptions type="603"/>
				</select>
			</span>
			
			<span >
				<label>重要：</label>
				<select   name="acImportance"  style="width: 100px;" submitForm="change"   >
					<option value="">全部</option>
					<option value="0">一般</option>
					<option value="1">重要</option>
					<option value="2">非常重要</option>
				</select>
			</span>
			<span> <label>负责人：</label>
			 <input type="text" name="acOurStaff" toName="user.trueName" style="width: 120px" op="like"/> 
			 <a href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询"><img src="resource/images/menu/24507_s.gif"  style="margin-top:-3px;"/></a>
			</span>
		</div>
		<div class="search-toolbar">
			<span style="float: left;"> 
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>

					<shiro:hasPermission name="activity:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true" title="添加客户活动"
						href="activity/addPage.do?id=${param.chanceid}&rel=${param.rel }"  target="navTab"
						width="1000" height="500" rel="${param.rel}_add"><span>新增客户活动</span>
					</a>
					</shiro:hasPermission>
				 
				
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
		<input type="hidden" name="chanceid" value="${param.chanceid }"/>
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
							url : "activity/query.do?chanceid=<%=request.getParameter("chanceid")%>",
							columns : [ [
									{
										field : "ck",
										title : "勾选",
										checkbox : true
									},
									{
										field : "acTitle",
										title : "活动主题",
										width : 80,
										align : "center",
										formatter: function(value,row,index){
						
											return '<a href="activity/find.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="550"  rel="<%=request.getParameter("rel")%>_show"  title="活动详情" >'+value+'</a>';
						
										}

									},
									{
										field : "acType",
										title : "方式",
										width : 40,
										align : "center",
										sortable : true,

									},
									{
										field : "acTime",
										title : "活动时间",
										width :60,
										align : "center",
										sortable : true,
										formatter: function(value,row,index){
						
											return new Date(value).format("yyyy-MM-dd HH:mm:ss");
										}
									},
									{
										field : "acDanwei",
										title : "单位",
										width : 100,
										align : "center"
									},
									{
										field : "acContacts",
										title : "联系人",
										width : 50,
										align : "center"
									},
									
									{
										field : "acPlace",
										title : "活动地点",
										width : 80,
										align : "center",
									},
									
									{
										field : "acOurStaff",
										title : "本次活动负责人",
										width : 60,
										align : "center"
																		}

							] ]
						});
						
					});
//-->
</script














