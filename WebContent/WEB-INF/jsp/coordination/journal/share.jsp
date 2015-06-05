
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--日志管理--共享日志查询
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
			<span >
				<label>类型：</label>
				<select   name="journalType"  style="width: 100px;" submitForm="change">
					<option value="">全部类型</option>
					<my:outOptions type="5"/>
				</select>
			</span>
			<span>
				<label>作者：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
			</span>
			<span>
				<label>标题：</label>
				<input	type="text" name="journalTitle" class="span2" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>日期：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
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


<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "journal/share/query.do",
			columns : [ [ 
			    
				{
					field : "userId",
					title : "作者",
					width : 150,
					align:"center"

				},
				{
					field : "journalType",
					title : "日志类型",
					width : 100,
					align:"center"

				},
				{
					field : "j.journalTitle",
					title : "标题",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="journal/show.do?id='+row.id+'" target="dialog" width="1000" height="450" title="日志详情" rel="<%=request.getParameter("rel")%>_show">'+row.journalTitle+'</a>';
					}

				},
			 	 {
					field : "j.journalTime",
					title : "日期",
					width : 100,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(row.journalTime).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
			
			] ]
		});
		
	});

//-->		
</script>

