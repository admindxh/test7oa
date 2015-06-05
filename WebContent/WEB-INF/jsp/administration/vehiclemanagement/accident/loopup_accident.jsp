<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理-查找车牌号   --%>
<div id="${param.rel}_toolbar" class="search-div">
   <form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" formatFilterData="true"   >
    		<!-- 条件查询区 -->
			<div class="search-content">
				<span>
					<label>车牌号:</label>
					<input type="text" name="licenseno"  />
					
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
				
				url : "vehiclemanagement/lookAllUpage.do",
				
				columns :[[{
					field : "licenseno",
					title : "车牌号",
					align:"center",
					width : 70,
					
				},
					{
						field : "vehicletypeId",
						title : "类型",
						align:"center",
						width : 40
					},
					{
						field : "xm",
						title : "驾驶员",
						align:"center",
						width : 60
					},
					{
						field : "action",
						title : "查找带回",
						align : "center",
						width : 30,
						formatter: function(value,row,index){
							return  "<a class='btnSelect' href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.licenseno+"',drivingno:'"+row.drivingno+"',drivingname:'"+row.xm+"'})>带回</a>";	
						} 
					}
					
				]]
			
			});
		});
//-->
</script>	
