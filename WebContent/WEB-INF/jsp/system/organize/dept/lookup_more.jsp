<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构 -- 部门管理--查找带回(多选)
--%>
<div class="easyui-tabs" fit="true" border="false" >
	<div   title="树状结构" 	style="padding:2px; ">
		<div class="easyui-layout" fit="true" >
			<div  region="center"  >
				 <ul id="dept_lookup_tree" class="ztree"></ul>
			</div>
			<div  region="south"  style="height: 50px;padding: 5px;margin-top: 1px;">

				<button type="button" class="btn"  onclick="zTreeExpandAllNodes('dept_lookup_tree',true)">展开</button>
				<button type="button" class="btn" onclick="zTreeExpandAllNodes('dept_lookup_tree',false)">合并</button>

				<button type="button" class="btn" onclick="zTreeCheckAllNodes('dept_lookup_tree',true)">全选</button>
			
				<button type="button" class="btn" onclick="zTreeCheckAllNodes('dept_lookup_tree',false)">全部取消 </button>
			
				<button type="button" class="btn btn-primary" onclick="deptlookupCheck(1)">覆盖选择</button>							
				
				<button type="button" class="btn btn-primary" onclick="deptlookupCheck(2)">追加选择</button>
								
		
			</div>
		</div>
	</div>
	<div   title="列表查询" 	style="padding:2px; ">
			<div id="${param.rel}_toolbar" class="search-div">
				<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
					<div  class="search-content " >
						<span>
							<label>名称：</label>
							<input	type="text" name="deptName"  />
							&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
						</span>
					
					
					</div>
				
					<div class="search-toolbar" >
						<span style="float:left;">
							<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
							
							<a class="easyui-linkbutton"  icon="icon-add"	plain="true" warn="请先选择用户" 
							 href="javascript:;"  onclick="$.multLookup(this,true,'${param.rel }_datagrid')" >带回并覆盖</a>
							 
							 <a class="easyui-linkbutton"  icon="icon-add"	plain="true" warn="请先选择用户" 
							 href="javascript:;"  onclick="$.multLookup(this,false,'${param.rel }_datagrid')" >带回并追加</a>
							
						</span>
						<span style="float:right">
							<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
							<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
						</span>
					
					</div>
				</form>
				
			</div>
			
			<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar" ></table>
			
	
	</div>
	
</div>

<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"dept/load/all.do",
			cache: false,
			dataType:"json",
			success:function(json){
				var setting = {
						check: {
							enable: true,
							chkStyle: "checkbox",
							chkboxType: { "Y": "", "N": "" }
						},
						view: {
							dblClickExpand: false,
							showIcon: false
						},
						data: {
							simpleData: {
								enable: true
							}
						}
				};
				var zNodes = new Array();
				$.each(json, function(i, d) {
					zNodes.push({id : d.id,name : d.deptName,
						pId : d.superId,open:true});
				});
				$.fn.zTree.init($("#dept_lookup_tree"), setting, zNodes);
		
			}
		});
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "dept/lookUp.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "deptName",
					title : "部门名称",
					width : 200,
					align:"center",
					sortable : true

				},
			 	 {
					field : "deptPhone",
					title : "电话",
					align:"center",
					width : 150
				} ,
			 	 {
					field : "deptDesc",
					title : "备注",
					width : 250
				} 
			
			] ]
		});
	});
	
	function deptlookupCheck(type){
		var treeObj = $.fn.zTree.getZTreeObj("dept_lookup_tree");
		
		//获取所有改变的节点
		var nodes = treeObj.getCheckedNodes(true);
		
		if(nodes.length==0){
			Msg.topCenter({
				title:"提示",
				msg:"请先勾选部门!"
			});
			return false;
		}
		var args={};
		for ( var i = 0,k=nodes.length; i <k ; i++) {
			var node=nodes[i];
			var id = args["id"] ? args["id"]+"," : "";
			args["id"] =id+node.id;
			var name = args["deptName"] ? args["deptName"]+"," : "";
			args["deptName"] =name+node.name;
		}
		if(type==1){
			$.bringBack(args);
		}else{
			$.appendBringBack(args);
		}
		return true;
		
	};

//-->
</script>
