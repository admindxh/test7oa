<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--事故管理--查看事故管理页面--%>
<div id="${param.rel}_toolbar"  class="search-div">
   <form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
    		<!-- 查询条件区域 -->
			<div class="search-content">
				<span>
					<label>车辆：</label>
					<a href="vehiclemanagement/lookUpPage.do"  lookupGroup="vehiclemanagement" title="车牌号查询" >
	     				<input type="text" name="licenseno"    readonly="readonly" toName="vehiclemanagement.name"   />
					 </a>
				</span>
				
				<span>
					<label>驾驶员：</label>
					<a href="driver/lookupdriver.do?rel=${param.rel}_jsy"  rel="${param.rel}_jsy"  lookupGroup="accident" title="驾驶员姓名查询" >
	     				<input type="text" name="drivingno"  readonly="readonly" toName="accident.name"   />
	     			</a>
				</span>
				<span>
					<label>发生时间：</label>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="startDate"  class="span2" />至
                    <input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2" />
				</span>
			</div>
			<!-- 功能区域 -->
		<div class="search-toolbar" >
			<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				   
				  <shiro:hasPermission name="accident:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加事故"	 href="accident/addPage.do?rel=${param.rel }" target="dialog" width="900" height="450"   rel="${param.rel}_add" >添加事故</a>
				 </shiro:hasPermission> 
				  <shiro:hasPermission name="accident:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改事故"	 href="accident/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="900" height="450"   rel="${param.rel}_update" >修改事故</a>
				  </shiro:hasPermission>  
				<shiro:hasPermission name="accident:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="accident/delete.do" target="selectedTodo"  title="请谨慎操作！确定要删除吗?" warn="至少选择一个投票">批量删除</a>
				 </shiro:hasPermission>
				
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
			
		
		</div>	
	</form>
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"   ></table>
<script type="text/javascript">
<!--
	$(function(){
			
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "accident/loadallaccident.do",
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
						
						return '<a href="vehiclemanagement/lookvehiclemanagement.do?id='+row.licensenoId+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看车辆详情" >'+value+'</a>';
						
					}
				},
					{
						field : "drivingname",
						title : "驾驶员姓名",
						align:"center",
						width : 50,
						formatter: function(value,row,index){
							
							return '<a href="driver/lookdriver.do?id='+row.drivingno+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看驾驶员详情" >'+value+'</a>';
							
						}
					},
					{
						field : "happenplace",
						title : "发生地点",
						align:"center",
						width : 80,
						
					},
					{
						field : "accidentconfirm",
						title : "事故确认者",
						align:"center",
						width : 60,
						
					},
					{
						field : "summary",
						title : "事故概要",
						align:"center",
						width : 150,
						
					}
					,
					{
						field : "createdate",
						title : "创建时间",
						align:"center",
						width : 80,
						sortable : true,
						formatter: function(value,row,index){
							
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					},
					{
						field : "action",
						title : "操作",
						align:"center",
						width : 60,
						formatter: function(value,row,index){
							
							return '<a  href="accident/lookaccident.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_update" target="dialog" width="900" height="450" rel="${param.rel}_update" title="事故详情" >查看详情</a>';
						}
					}
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openDialog('修改事故信息','accident/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:900,height:450});
				}
			
			});
		});
//-->
</script>	
