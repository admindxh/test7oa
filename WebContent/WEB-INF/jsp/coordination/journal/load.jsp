
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--日志管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			<span >
				<label>类型：</label>
				<select   name="journalType"  style="width: 100px;" submitForm="change">
					<option value="">全部类型</option>
					<my:outOptions type="5"/>
				</select>
			</span>
			<span>
				<label>标题：</label>
				<input	type="text" name="journalTitle"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>日期：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
		
		</div>
	
		<div class="search-toolbar">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="journal:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="journal/addPage.do?rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="写日志">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="journal:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="journal/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一个日志" title="修改日志">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="journal:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="journal/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个日志">批量删除</a>
				</shiro:hasPermission>
				
					<a class="easyui-linkbutton"  icon="icon-search"	plain="true"
					  title="共享日志"	 href="journal/share/load.do?rel=${param.rel }_share" target="navTab"  rel="${param.rel}_share" >共享日志</a>
					
				
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
			url : "journal/my/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "journalType",
					title : "日志类型",
					width : 150,
					align:"center"

				},
				{
					field : "journalTitle",
					title : "标题",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="journal/show.do?id='+row.id+'" target="dialog" width="1000" height="450" title="日志详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}

				},
			 	 {
					field : "journalTime",
					title : "日期",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改日志','journal/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>

