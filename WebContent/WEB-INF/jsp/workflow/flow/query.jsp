<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--流程设计
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<input id="${param.rel}_flowType" type="hidden" value="0"/>
		<div  class="search-content " >
			<span>
				<label>名称：</label>
				<input	type="text" name="flowName" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label >启用状态：</label>
				<select name="isEnable" style="width: 80px">
					  <option value="">全部</option>
					  <option value="1">可用</option>
					  <option value="0">禁用</option>
			   </select>	
			</span>
			
			<span>
				<label >发布状态：</label>
				<select name="releaseState" style="width: 80px">
					  <option value="">全部</option>
					  <option value="0">未发布</option>
					  <option value="1">已发布</option>
					   <option value="2">有更新</option>
			   </select>	
				
			</span>

		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="workflow:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加流程设计"	 href="workflow/addPage.do?rel=${param.rel}&flowType={#${param.rel}_flowType}"  target="dialog" width="800" height="400" rel="${param.rel}_add" >添加</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="workflow:add">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="复制流程设计"	href="workflow/copyPage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_copy" warn="请先选择一个流程设计" >复制</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="workflow:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="workflow/del.do" target="selectedTodo"  title="确定要删除吗?会同时删除所有版本流程和历史流程记录！！" warn="至少勾选一个流程设计">批量删除</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="flowListener:read">
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"
					  title="流程监听器管理"	 href="flowListener/load.do?rel=${param.rel }_listener" target="navTab"  rel="${param.rel}_listener" >监听器管理</a>
					
				</shiro:hasPermission>
				
			</span>
			
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="流程查询--全部分类"></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			border:true,
			nowrap : false,
			url : "workflow/query.do",
			mustParamNames:"flowType",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "flowName",
					title : "名称",
					align:"center",
					width : 200,
					formatter: function(value,row,index){
						
						return '<a href="workflow/flow/show.do?id='+row.id+'" target="dialog" width="800" height="450" rel="<%=request.getParameter("rel")%>_show" title="查看用户详情">'+value+'</a>';
					}
				},
				{
					field : "isEnable",
					title : "启用状态",
					align:"center",
					width : 50,
					formatter: function(value,row,index){
						if(1==value){
							return '<img src="resource/images/small/Check_16x16.png" alt="可用" title="可用"  style="margin-top: 2px;"/>';
						}else{
							return '<img src="resource/images/small/Delete_16x16.png" alt="禁用" title="禁用" style="margin-top: 2px;"/>';
						}
					}
				},
			 	 {
					field : "releaseState",
					title : "发布状态",
					align:"center",
					width : 40,
					formatter: function(value,row,index){
						if(1==value){
							return "已发布";
						}else if(2==value){
							return "有更新";
						}else {
							return "未发布";
						}
					}
				},
			 	 {
					field : "versionNum",
					title : "版本数量",
					align:"center",
					width : 50
				},
			 	 {
					field : "op",
					title : "操作",
					align:"center",
					width : 100,
					formatter: function(value,row,index){
						var html='';
						html+='<a  href="workflow/flow/setPage.do?id='+row.id+'" target="navTab"   rel="<%=request.getParameter("rel")%>_set" title="流程设计设置">设置</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="workflow/designer/load.do?id='+row.id+'"  target="_blank"  >设计</a>';
						if(1!=row.releaseState){
							html+='&nbsp;&nbsp;|&nbsp;&nbsp;<a href="workflow/flow/deploy.do?id='+row.id+'"  target="ajaxTodo" datagrid="<%=request.getParameter("rel")%>_datagrid" title="确定发布流程？发布之后，新工作会按照最新的流程设计执行，正在运行中的工作还是按照原先的版本执行！">发布</a>';
						}
						if(row.versionNum>0){
							html+='&nbsp;&nbsp;|&nbsp;&nbsp;<a href="processDefinition/queryAllVersion.do?processDefinitionKey='+row.flowId+'&rel=<%=request.getParameter("rel")%>_bbjk" target="navTab" rel="<%=request.getParameter("rel")%>_bbjk" title="流程设计版本监控">版本监控</a>';
						}
						return html;
					}
				}    
				
			
			] ]
		});
		
	});

//-->		
</script>
