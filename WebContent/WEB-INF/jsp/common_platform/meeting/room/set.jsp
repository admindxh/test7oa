<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公共平台--会议设置
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form 	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div class="search-content ">
					<span> <label style="width: 60px;"></label>
							会议室：<input type="text" name="mrName" value="<c:out value="${param.mrName }" />"/>
							&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
					</span>
		</div>
		
	<div class="search-toolbar" >
		<span style="float: left;">
			<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
			
			<shiro:hasPermission name="room:add">
				<a class="easyui-linkbutton" icon="icon-add" plain="true" href="room/addPage.do?rel=${param.rel }" target="dialog" width="800" height="400" title="新增会议室" rel="${param.rel}_add"><span>新增会议室</span></a>
			</shiro:hasPermission> 
			<shiro:hasPermission name="room:update">
				<a class="easyui-linkbutton" icon="icon-add" plain="true" href="room/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" title="修改会议室" height="400" rel="${param.rel}_update" warn="请先选择一个会议室"><span>修改</span></a>
			</shiro:hasPermission>
			<shiro:hasPermission name="room:delete">
				<a  class="easyui-linkbutton" icon="icon-add" plain="true" href="room/del.do" target="selectedTodo" rel="ids" title="确定要删除吗?" warn="至少选择一条会议室"><span>批量删除</span></a>
			</shiro:hasPermission>
		</span>
		<span style="float: right">
				<button class="btn btn-primary btn-small" type="submit">
					查询
				</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;</span>
	</div>
	
	
		
	</form>
</div>

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar"
	></table>



<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:false,//true:只在一行显示数据,false:内容多时自动换行，效率低
			
			url : "room/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "mrNo",
					title : "编号",
					width : 140,
					align:"center",
					sortable : true

				},
				{
					field : "mrName",
					title : "名称",
					width : 150,
					align:"center",
					sortable : true

				},
				{
					field : "mrNum",
					title : "容纳人数",
					width : 50,
					align:"center",
					sortable : true

				},
				{
					field : "mrRemark",
					title : "备注",
					width : 250,
					align:"center",
					sortable : true

				}
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改会议室','room/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>


	

