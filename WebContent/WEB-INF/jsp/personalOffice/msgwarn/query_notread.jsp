<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--消息提醒-未读消息查询
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
		
			<span >
				<label>提醒类型：</label>
				
				<select  name="msgType"  class="span2" submitForm="change">
						<option value="">全部提醒</option>
						<%@ include file="/WEB-INF/jsp/personalOffice/msgwarn/type.jsp" %>
						
				</select>
				
			</span>
			
			<span>
				<label>提醒时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="msgwarn/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个提醒">批量删除</a>
				
				
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
			url : "msgwarn/query.do?readState=0",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "msgType",
					title : "标题",
					width : 350,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						var html='<a href="msgwarn/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" title="消息提醒详情"  width="1000" height="450" rel="<%=request.getParameter("rel")%>_show">';
					
						if(row.msgType==1){
							html+='您有新通知，请及时查看！';
						}else if(row.msgType==2){
							html+='您有新会议，请及时查看！';
						}else if(row.msgType==3){
							html+='有新发布的新闻，请及时查看！';
						}
						else if(row.msgType==4){
							html+='您有新共享日志，请及时查看！';
						}else if(row.msgType==5){
							html+='您有新的投票，请及时查看！';
						}
						else if(row.msgType==60){
							html+='您有新的工作流程消息，请及时查看！';
						}else if(row.msgType==20){
							html+='您有新的工作计划，请及时查看！';
						}else if(row.msgType==31){
							html+='您有新的项目，请及时查看！';
						}else if(row.msgType==32){
							html+='您有新的项目任务，请及时查看！';
						}
						else{
							html+='无此类型消息：'+row.msgType;
						}
						
						return html+'</a>';
					}

				},
			 	 {
					field : "msgTime",
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