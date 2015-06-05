<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理--文档管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true">
		<div  class="search-content " >
			<span>
				<label>标题：</label>
				<input	type="text" name="title" class="span2"  op="like"/>
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>创建人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="createUserId"  toName="user.id"  />
				
			</span>
			<span>
				<label>创建时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="createTime" class="span2" fieldType="date" op="greaterorequal"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="createTime" class="span2" fieldType="date"  op="lessorequal"/>
				
			</span>
			
		</div>
	
		<div class="search-toolbar"  >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<c:if test="${!empty requestScope.fzr }">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="project/file/addPage.do?pid=${param.projectId }&rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="添加项目文档">添加</a>
			
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="project/file/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一个项目文档" title="修改项目文档">修改</a>
					
			
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="project/file/del.do?projectId=${param.projectId }" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个项目文档">批量删除</a>
			</c:if>
				
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
			url : 'project/file/query.do?id=<%=request.getParameter("projectId")%>',
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "title",
					title : "标题",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="project/file/show.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="1000" height="450"  title="项目文档详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}

				},
			 	 {
					field : "createUserId",
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
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改项目文档','project/file/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>
