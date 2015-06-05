<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构 -- 部门管理--查找带回(单选)
--%>

<div class="easyui-tabs" fit="true" border="false" >
	<div title="树状结构" 	style="padding:2px; ">
		 <ul id="dept_lookup_tree" class="ztree"></ul>
	
	</div>
	<div   title="列表查询" 	style="padding:2px; ">
			<div id="${param.rel}_toolbar" class="search-div">
				<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
					<div  class="search-content " >
						<span>
							<label>名称：</label>
							<input	type="text" name="deptName" />
							&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
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
				var type='<%= request.getParameter("type") %>';
				
				var zNodes = new Array();
				if(type==3){
					zNodes.push({id : 0,name : "根部门",open:true,click:"$.bringBack({id:'0',deptName:'无上级'})"});
				}
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.deptName,
						pId : d.superId,open:true,click:"$.bringBack({id:'"+d.id+"',deptName:'"+d.deptName+"'})"});
					
				});
				
				$.fn.zTree.init($("#dept_lookup_tree"), setting, zNodes);
		
			}
		});
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "dept/lookUp.do",
			columns : [ [ 
	
				{
					field : "deptName",
					title : "部门名称",
					width : 200,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',deptName:'"+row.deptName+"'})>"+value+"</a>";
						
					}

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
//-->
</script>
