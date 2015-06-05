<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--个人计划查询
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true">
		<div  class="search-content " >
			<span >
				<label>计划类型：</label>
				<select   name="w.type"  style="width: 100px;" submitForm="change">
					<option value="">全部类型</option>
					<my:outOptions type="60"/>
				</select>
			</span>
			<span >
				<label>状态：</label>
				<select   name="w.status"  style="width: 100px;" submitForm="change" fieldType="short">
					<option value="">全部类型</option>
					<option value="0">未开始</option>
					<option value="1">进行中</option>
					<option value="2">暂停</option>
					<option value="3">结束</option>
				</select>
			</span>
			<span>
				<label>计划名称：</label>
				<input	type="text" name="w.name"   op="like"/>
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>创建人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="w.createUserId"  toName="user.id"  />
				
			</span>
			<span>
				<label>创建时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="w.createTime" class="span2" fieldType="date" op="greaterorequal"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="w.createTime" class="span2" fieldType="date"  op="lessorequal"/>
				
			</span>
			
		</div>
	
		<div class="search-toolbar"  >
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar" ></table>


<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "workPlan/my/query.do",
			columns : [ [ 
		
				{
					field : "type",
					title : "计划类型",
					width : 100,
					align:"center"

				},
				{
					field : "name",
					title : "计划名称",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="workPlan/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="navTab"  title="工作计划详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}

				},
			 	 {
					field : "startTime",
					title : "开始时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
				,
			 	 {
					field : "endTime",
					title : "结束时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "createUser",
					title : "创建人",
					width : 150,
					align:"center"
				},
			 	 {
					field : "createTime",
					title : "创建时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
			 	 {
					field : "status",
					title : "状态",
					width : 70,
					align:"center",
					formatter: function(value,row,index){
						if(0==value){
							return '<span class="label">未开始</span>';
						}else if(1==value){
							return '<span class="label label-success">运行中</span>';
						}else if(2==value){
							return '<span class="label label-warning">暂停</span>';
						}else if(3==value){
							return '<span class="label label-important">已结束</span>';
						}
						return "";
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>
