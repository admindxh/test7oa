<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--字典值管理
--%>
<div class="easyui-layout" fit="true" >
   <!-- 左侧-->
	<div region="west" title="字典类型" split="true" 	style="width:220px;" >
	
	   	<ul id="${param.rel }_leftTree" class="ztree"></ul>
		
	</div>
  	
	<div  region="center" border="false">
		<%@ include file="/WEB-INF/jsp/system/list_values/query.jsp"%>
	</div>
   
</div>

<script type="text/javascript">
<!--
	$(function(){
		
		var setting = {
				view: {
					showIcon: false
				},
				callback: {
					onClick: updateListType
				}
		};
		var rel='<%= request.getParameter("rel") %>_datagrid';
	
		var zNodes =[
		     {name:"个人办公",open:true,
		    		children:[
							 {name:"通讯录分组",param : {"listType":4},datagrid:rel}
							,{name:"日程类型",param : {"listType":6},datagrid:rel}
		    		
				    ]
		    	 
		     },
		 	{name:"协同办公",open:true,
		 		children:[
		 				 {name:"通知类型",param : {"listType":1},datagrid:rel}	
		 				,{name:"日志类型",param : {"listType":5},datagrid:rel}	
		 				,{name:"工作计划类型",param : {"listType":60},datagrid:rel}
		 		]
		 				
		 	},
		 	{name:"公共平台",open:true,
		 		children:[
						{name:"会议类型",param : {"listType":2},datagrid:rel}
					   ,{name:"新闻类型",param : {"listType":3},datagrid:rel}
					   ,{name:"公告类型",param : {"listType":7},datagrid:rel}
					   ,{name:"规章类型",param : {"listType":8},datagrid:rel}
					   ,{name:"法规类型",param : {"listType":9},datagrid:rel}	
		 		]
		 		
		 	},
		 	{name:"行政办公",open:true,
		 		children:[
						 {name:"车辆类型",param : {"listType":10},datagrid:rel}
						,{name:"办公用品类型",param : {"listType":16},datagrid:rel}
		 		]
		 		
		 	},
		 	{name:"工作流程",open:true,
		 		children:[
						 {name:"工作流程类型",param : {"listType":17},datagrid:rel}
						
		 		]
		 		
		 	},
		 	{name:"人事管理",open:true,
		 		children:[
						 {name:"员工类型",param : {"listType":11},datagrid:rel}
						,{name:"职位",param : {"listType":12},datagrid:rel}
						,{name:"学历",param : {"listType":13},datagrid:rel}
						,{name:"离职原因",param : {"listType":14},datagrid:rel}
						,{name:"退休原因",param : {"listType":15},datagrid:rel}
						,{name:"值班类型",param : {"listType":21},datagrid:rel}
		 		]
		 		
		 	},
		 	{name:"CRM",open:true,
		 		children:[
						 {name:"合同类型",param : {"listType":701},datagrid:rel}
						,{name:"合同状态",param : {"listType":702},datagrid:rel}
						,{name:"客户类型",param : {"listType":401},datagrid:rel}
						,{name:"客户来源",param : {"listType":402},datagrid:rel}
						,{name:"客户等级",param : {"listType":403},datagrid:rel}
						,{name:"客户状态",param : {"listType":404},datagrid:rel}
						,{name:"客户阶段",param : {"listType":405},datagrid:rel}
						,{name:"客户关系",param : {"listType":406},datagrid:rel}
						,{name:"所属行业",param : {"listType":407},datagrid:rel}
						,{name:"客户类别",param : {"listType":408},datagrid:rel}
						,{name:"联系人种类",param : {"listType":501},datagrid:rel}
						,{name:"联系人类型",param : {"listType":502},datagrid:rel}
						,{name:"机会来源",param : {"listType":601},datagrid:rel}
						,{name:"机会状态",param : {"listType":602},datagrid:rel}
						,{name:"活动方式",param : {"listType":603},datagrid:rel}
		 		]
		 		
		 	},
		 	{name:"项目管理",open:true,
		 		children:[
						 {name:"项目类型",param : {"listType":331},datagrid:rel}
						,{name:"重要程度",param : {"listType":332},datagrid:rel}
						
		 		]
		 		
		 	}
		 		      
		 		      
		 ];
		
		$.fn.zTree.init($("#<%= request.getParameter("rel") %>_leftTree"), setting, zNodes);
				
		
	});
	function updateListType(event,treeId, treeNodeJSON){
		if(treeNodeJSON.param){
			$('#<%= request.getParameter("rel") %>_listType').val(treeNodeJSON.param.listType);
			treeNodeJSON.updateTitle=treeNodeJSON.name;
			refreshDatagrid(event,treeId, treeNodeJSON);
		}
	}
//-->
</script>


	

