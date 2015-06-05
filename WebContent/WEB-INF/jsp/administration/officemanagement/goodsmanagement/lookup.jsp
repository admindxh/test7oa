<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品管理--查看物品管理页面--%>
<div id="${param.rel}_toolbar" class="search-div">
   <form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
    		
			<div class="search-content">
				<span>
					<label>类别:</label>
					<select name="typeId" submitForm="change">
					    <option value="">全部类型</option>
					    <my:outOptions type="16"/>
					</select>
					
				</span>
				<span>
					<label>物品名称:</label>
					<input type="text" name="usingname" />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				</span>
				<span style="float:right">
						<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
						<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
				</span>
			</div>   
    </form>
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar" ></table>
<script type="text/javascript">
<!--
	$(function(){
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "goodsmanagerment/lookUp.do",
				
				columns :[[{
						field : "usingname",
						title : "物品名称",
						align:"center",
						width : 180,
						
					},
					{
						field : "typeId",
						title : "办公类型",
						align:"center",
						width : 80
					},
					{
						field : "danwei",
						title : "单位",
						align:"center",
						width : 50
					},
					{
						field : "currentinventory",
						title : "库存量",
						align:"center",
						width : 100
					},
					{
						field : "action",
						title : "查找带回",
						align : "center",
						width : 50,
						formatter: function(value,row,index){
							return  "<a class='btnSelect' href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',usingname:'"+row.usingname+"',danwei:'"+row.danwei+"'})>选择</a>";	
						} 
					}
					
				]]
			
			});
		});
//-->
</script>	
