<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理-查找带回   --%>
<div id="${param.rel}_toolbar" class="search-div">
   <form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" formatFilterData="true"   >
   		<!-- 条件查询区 -->
		<div class="search-content">
			
			<span>
				<label>状态：</label>
				 <select  name="status" submitForm="change">
						<option value="">全部状态</option>
						<option value="0">在库</option>
						<option value="1">使用中</option>
						<option value="2">维修</option>
						<option value="3">报废</option>
				</select>
			</span>	
			<span>
				<label>车辆类型：</label>
				<select name="vg.vehicletypeId" style="width: 110px;">
					<option value="">全部</option>
					<my:outOptions type="10"/>	
				</select>
			</span>	
			<span>
				<label>车牌号:</label>
				<input type="text" name="licenseno"  />
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
				
				url : 'vehiclemanagement/lookUp.do',
				columns :[[{
					field : "licenseno",
					title : "车牌号",
					align:"center",
					width : 120,
					formatter: function(value,row,index){
						
						return '<a href="vehiclemanagement/lookvehiclemanagement.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看详情" >'+value+'</a>';
						
					}
				},
					{
						field : "vehicletypeId",
						title : "类型",
						align:"center",
						width : 100
					},
					{
						field : "seat",
						title : "座位",
						align:"center",
						width : 50,
						
					},
					{
						field : "xm",
						title : "驾驶员",
						align:"center",
						width : 100
					},
					{
						field : "status",
						title : "状态",
						align:"center",
						width : 50,
						formatter: function(value,row,index){
								if("0"==value){
									return "在库";
								}else if("1"==value){
									return "使用中";
								}else if("2"==value){
									return "维修";
								}else if("3"==value){
									return "报废";
								}
						}
						
					},
					{
						field : "action",
						title : "带回",
						align : "center",
						width : 30,
						formatter: function(value,row,index){
							return  "<a class='btnSelect' href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.licenseno+"',drivingno:'"+row.drivingno+"',drivingname:'"+row.xm+"'})>选择</a>";	
						} 
					}
					
				]]
			
			});
		});
//-->
</script>	
