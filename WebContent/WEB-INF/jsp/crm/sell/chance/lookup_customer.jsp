<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-销售管理-销售机会--查找带回
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
										field : "scName",
										title : "主题",
										width : 80,
										align : "center",
										formatter: function(value,row,index){
											
											return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.scName+"',infoID:'"+row.scInfo+"',info:'"+row.info+"',linkman:'"+row.linkman+"'})>"+value+"</a>";
							
										}


									},
									{
										field : "info",
										title : "客户名称",
										width : 140,
										align : "center"

									},
									{
										field : "linkman",
										title : "联系人",
										width : 100,
										align : "center"

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
										
 
									}

							] ]
						});
						
					});
</script>







