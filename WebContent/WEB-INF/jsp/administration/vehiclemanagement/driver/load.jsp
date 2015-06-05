<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--驾驶员管理--查看驾驶员页面
--%>
<div id="${param.rel}_toolbar" class="search-div">
   	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
    		<!-- 查询条件区域 -->
			<div class="search-content">
				<span>
					<label>姓名:</label>
					<input type="text" name="drivingname"  class="span2" />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				</span>
				<span>
					<label>驾驶证号:</label>
					<input type="text" name="drivingno"  class="span2" />
				</span>
				<span>
					<label>性别:</label>
					<select name="gender">
					    <option value="">全部</option>
					    <option value="0">男</option>
					    <option value="1">女</option>
				    </select>
				</span>
			</div>
			<!-- 功能区域 -->
		<div class="search-toolbar" >
			<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				   
				 <shiro:hasPermission name="driver:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加驾驶员"	 href="driver/addPage.do?rel=${param.rel }" target="dialog" width="900" height="450"  rel="${param.rel}_add" >添加驾驶员</a>
				 </shiro:hasPermission> 
				 <shiro:hasPermission name="driver:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改驾驶员"	 href="driver/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="900" height="450"  rel="${param.rel}_update" >修改驾驶员</a>
				 </shiro:hasPermission> 
				<shiro:hasPermission name="driver:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="driver/delete.do" target="selectedTodo"  title="请谨慎操作！确定要删除吗?" warn="至少选择一个投票">批量删除</a>
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
				
				url : "driver/loadall.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[{
					field : "drivingname",
					title : "姓名",
					align:"center",
					width : 60,
					formatter: function(value,row,index){
						
						return '<a href="driver/lookdriver.do.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_show"  title="查看驾驶员详情" >'+value+'</a>';
						
					}
				},
					{
						field : "gender",
						title : "性别",
						align:"center",
						width : 20,
						formatter: function(value,row,index){
							if("0"==value){
								return "男";
							}else if("1"==value){
								return "女";	
							}
							
						}
						
					},
					{
						field : "drivingno",
						title : "驾驶证号",
						align:"center",
						width : 80,
						
					},
					{
						field : "papervaliddate",
						title : "证件到期时间",
						align:"center",
						width : 55,
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
						field : "drivingage",
						title : "驾龄(年)",
						align:"center",
						width : 30,
						
					},
					{
						field : "allowdriving",
						title : "准驾车型",
						align:"center",
						width : 70,
						
					},
					{
						field : "address",
						title : "单位或地址",
						align:"center",
						width : 120,
						
						
					},
					{
						field : "phone",
						title : "联系电话",
						align:"center",
						width : 60,
						
					},
					{
						field : "userName",
						title : "创建人",
						align:"center",
						width : 50,
						
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
					MUI.openDialog('修改驾驶员信息','driver/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:900,height:450});
				}
			
			});
		});
//-->
</script>	
