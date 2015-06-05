<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理-查找驾驶员         --%>
<div id="${param.rel}_toolbar" class="search-div">
   <form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
    		<!-- 条件查询区 -->
			<div class="search-content">
				<span>
					<label>姓名:</label>
					<input type="text" name="drivingname"  />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				</span>
				<span>
					<label>驾驶证号:</label>
					<input type="text" name="drivingno"  />
				</span>
			</div>
			<div class="search-toolbar" >
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
				
				url : "driver/lookupalldriver.do",
				
				columns :[[{
					field : "drivingname",
					title : "姓名",
					align:"center",
					width : 30,
					
				},
					{
						field : "drivingno",
						title : "驾驶证号",
						align:"center",
						width : 40
					},
					{
						field : "action",
						title : "查找带回",
						align : "center",
						width : 15,
						formatter: function(value,row,index){
							return  "<a class='btnSelect' href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.drivingname+"'})>带回</a>";	
						} 
					}
					
				]]
			
			});
		});
//-->
</script>	

