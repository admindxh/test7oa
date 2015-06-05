<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理-车辆管理--查看车辆管理页面--%>
<div id="${param.rel}_toolbar" class="search-div">
  	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" formatFilterData="true"  >
    		<!-- 查询条件区域 -->
		<div class="search-content">
			<span>
				<label>车牌号：</label>
				<input type="text" name="licenseno"  class="span2" />
			</span>
			<span>
				<label>车辆类型：</label>
				<select name="vg.vehicletypeId" style="width: 110px;">
					<option value="">全部</option>
					<my:outOptions type="10"/>	
				</select>
			</span>		
			<span>
				<label>驾驶员：</label>
				<a href="driver/lookupdriver.do"   lookupGroup="accident" title="驾驶员姓名查询" >
	     		<input type="text" name="vt.drivingname"  readonly="readonly" toName="accident.name" maxlength="30"  />
	     		</a>
			</span>	
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
		</div>
		<!-- 功能区域 -->
		<div class="search-toolbar">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				   
				 <shiro:hasPermission name="vehicle:add"> 
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加车辆"	 href="vehiclemanagement/addPage.do?rel=${param.rel }"  target="dialog" width="900" height="450"  rel="${param.rel}_add" >添加</a>
				 </shiro:hasPermission>
				 <shiro:hasPermission name="vehicle:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改车辆"	 href="vehiclemanagement/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="900" height="450"  rel="${param.rel}_update" >修改</a>
				  </shiro:hasPermission>
				<shiro:hasPermission name="vehicle:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="vehiclemanagement/delete.do" target="selectedTodo"  title="请谨慎操作！确定要删除吗?" warn="至少选择一个投票">批量删除</a>
				</shiro:hasPermission>
				<a class="easyui-linkbutton"  icon="icon-search"	plain="true" 
					title="车辆管理高级搜索" href="vehiclemanagement/searchPage.do?rel=${param.rel}" target="dialog"  width="650" height="350"  rel="${param.rel}_queryfilter"  >高级查询</a>
			</span>
			<span style="float:right">
				
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		
		</div>
		
			</form>
    
    
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>
<script type="text/javascript">
<!--
	$(function(){
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "vehiclemanagement/laodall.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[{
					field : "licenseno",
					title : "车牌号",
					align:"center",
					width : 80,
					formatter: function(value,row,index){
						
						return '<a href="vehiclemanagement/lookvehiclemanagement.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看车辆详情" >'+value+'</a>';
						
					}
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
						width : 80,
						formatter: function(value,row,index){
						
							return '<a href="driver/lookdriver.do?id='+row.drivingno+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看驾驶员详情" >'+value+'</a>';
						}
					},
					{
						field : "buydate",
						title : "购买日期",
						align:"center",
						width : 60,
						sortable : true,
						formatter: function(value,row,index){
							if(""==value||null==value){
								return "";
							}else{
								return new Date(value).format("yyyy-MM-dd ");	
							}
							
						}
					},
					{
						field : "weight",
						title : "载重(吨)",
						align:"center",
						width : 40,
						
					},
					{
						field : "seat",
						title : "座位",
						align:"center",
						width : 40,
						
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
						field : "usingunit",
						title : "使用单位",
						align:"center",
						width : 120,
						
					},
					{
						field : "userName",
						title : "创建人",
						align:"center",
						width : 60,
						
					},
					{
						field : "createdate",
						title : "创建时间",
						align:"center",
						width : 80,
						sortable : true,
						formatter: function(value,row,index){
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					}
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openDialog('修改车辆信息','vehiclemanagement/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:900,height:450});

				}
			
			});
		});
//-->
</script>	
		