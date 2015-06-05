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
							url : "info/lookUp.do",
							columns : [ [
									
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
										formatter: function(value,row,index){
											
											return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.clName+"'})>"+value+"</a>";
							
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
										
 
									}
<%--									,{--%>
<%--										field : "action",--%>
<%--										title : "操作",--%>
<%--										width : 80,--%>
<%--										align : "center",--%>
<%--										formatter: function(value,row,index){--%>
<%--							--%>
<%--											return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.clName+"'})>带回</a>";--%>
<%--							--%>
<%--										}--%>
<%-- 									}--%>

							] ]
						});
						
					});
//-->
</script>







