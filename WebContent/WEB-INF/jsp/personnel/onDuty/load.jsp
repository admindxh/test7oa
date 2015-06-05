<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-值班管理-值班记录
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content" >
			
			<span>
				<label>值班人：</label> 
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userid"  toName="user.id"  /> 
			</span> 
			<span>
				<label>标题：</label>
				<input	type="text" name="title" class="span2"  /> 
			</span>
			
			<span>
				<label>值班开始：</label> 
					<input type="text" name="startDate" class="easyui-validatebox" required="true" 
					 id="startDate"   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/>
				 -			
					<input type="text" name="endDate" id="endDate" class="easyui-validatebox" required="true" 
					 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\');}'})" readonly="readonly"/>
			</span>
 			 <span >
				<label>值班类型：</label>
				<select   name="type"  style="width: 100px;" submitForm="change">
					<option value="">全部类型</option>
					<my:outOptions type="21"/>
				</select>
			</span>
			 
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				<shiro:hasPermission name="onDuty:add">    
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="onDuty/addOnDutyPage.do?rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="添加值班记录">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="onDuty:update">    
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="onDuty/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一条值班记录" title="修改值班记录">修改</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="onDuty:delOnDuty">   
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="onDuty/delOnDuty.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一条值班记录">批量删除</a>
				 
				</shiro:hasPermission>
			</span>
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "onDuty/selectOnDuty.do",
			frozenColumns:[[
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "title",
					title : "标题",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="onDuty/showOnDuty.do?id='+row.id+'" target="dialog" width="1000" height="450" title="值班详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}
				}
			]],
			columns : [ [ 
			             
					{
						field : "userid",
						title : "值班人",
						width : 180,
						align:"center"
					},{
						field : "type",
						title : "值班类型",
						width : 150,
						align:"center"
					
					},
					
			 	 {
					field : "starttime",
					title : "值班开始时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},{
					field : "endtime",
					title : "值班结束时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
				, 
				{
					field : "createuser",
					title : "记录人",
					width : 180,
					align:"center"
				},
				{
					field : "createtime",
					title : "记录时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改值班记录','onDuty/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>

