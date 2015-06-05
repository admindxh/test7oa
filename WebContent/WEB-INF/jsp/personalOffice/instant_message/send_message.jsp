<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息---已发消息
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
			<span >
				<label>状态：</label>
				<select name="readState" style="width: 100px;" submitForm="change">
					<option value="">全部</option>
					<option value="0">未读</option>
					<option value="1">已读</option>
				</select>
			</span>
			<span>
				<label>接收人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" />
				</a>
				<input type="hidden"  name="receiveUid"  toName="user.id"  />
				
			</span>
			
			<span>
				<label>发送时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="instantMessage/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一条信息">批量删除</a>
				
				
			</span>
			
			
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
			
			url : "instantMessage/selectSendMessage/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "readState",
					title : "状态",
					width : 30,
					align:"center",
					formatter: function(value,row,index){
						if(value==0){
							return '<img src="resource/images/email_close.gif" alt="未读"  style="margin-top: 3px;"/>';
						}else{
							return '<img src="resource/images/email_open.gif" alt="已读" style="margin-top: 3px;"/>';
						}
					}

				},
				{
					field : "receiveName",
					title : "接收人",
					width : 150,
					align:"center",
					formatter: function(value,row,index){
						
						return ' <a href="instantMessage/addPage.do?userId='+row.receiveUid+'&rel=<%=request.getParameter("rel")%>" target="dialog" width="1000" height="450" rel="<%=request.getParameter("rel")%>_add" title="发送即时消息">'+value+'</a>';
					}
					
				},
				{
					field : "messageContent",
					title : "内容",
					width : 350,
					formatter: function(value,row,index){
						return '<a href="instantMessage/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show&type=2" target="dialog" width="800" height="400" title="即时沟通详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}
					
				},
			 	 {
					field : "createTime",
					title : "发送时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ]
		});
		
	});

//-->		
</script>
