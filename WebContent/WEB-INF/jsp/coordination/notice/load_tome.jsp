<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--通知管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
			<span >
				<label>类型：</label>
				<select   name="noticeType"  style="width: 100px;" submitForm="change">
					<option value="">全部类型</option>
					<my:outOptions type="1"/>
				</select>
			</span>
			<span>
				<label>标题：</label>
				<input	type="text" name="noticeTitle" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>发送人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="noticeSendId"  toName="user.id"  />
				
			</span>
			<span>
				<label>发送时间：</label>
				
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
			url : "notice/loadtome/query.do",
			columns : [ [ 
           		  {
					field : "readTime",
					title : "状态",
					width : 30,
					align:"center",
					formatter: function(value,row,index){
						if(value==null){
							return '<img src="resource/images/email_close.gif" alt="未读"  style="margin-top: 3px;"/>';
						}else{
							return '<img src="resource/images/email_open.gif" alt="已读" style="margin-top: 3px;"/>';
						}
					}

				},
				{
					field : "noticeType",
					title : "通知类型",
					width : 150,
					align:"center"

				},
				{
					field : "n.noticeTitle",
					title : "标题",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="notice/show.do?id='+row.id+'" target="dialog" width="1000" height="450" title="通知详情" rel="<%=request.getParameter("rel")%>_show">'+row.noticeTitle+'</a>';
					}

				},
				{
					field : "noticeSendId",
					title : "发送人",
					width : 180,
					align:"center"

				},
			 	 {
					field : "n.noticeCreateTime",
					title : "发送时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(row.noticeCreateTime).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
			
			] ]
		});
		
	});

//-->		
</script>

