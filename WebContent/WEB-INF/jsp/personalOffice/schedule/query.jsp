<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--日程安排
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<div  class="search-content " >
		
			<span >
				<label>类型：</label>
				<select class="span2"   name="schType"  submitForm="change">
					<option value="">全部类型</option>
					<my:outOptions type="6"/>
				</select>
			</span>
			<span>
				 <label>主题：</label>
				 <input type="text" name="schTitle" />
				 &nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
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
				
				
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="schedule/addPage.do?rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="添加日程安排">添加</a>
				
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="schedule/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一个日程安排" title="修改日程安排">修改</a>	
				
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="schedule/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个日程安排">批量删除</a>
				
				
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
			url : "schedule/manage/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "schType",
					title : "日程类型",
					width : 150,
					align:"center"

				},
				{
					field : "schTitle",
					title : "主题",
					width : 350,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						return '<a href="schedule/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="1000" height="450" title="日程安排详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
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
				},
			 	 {
					field : "endTime",
					title : "结束时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				} ,
			 	 {
					field : "warnTime",
					title : "提醒时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}  
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改日程安排','schedule/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>