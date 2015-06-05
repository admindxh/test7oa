<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--地区管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
		<div  class="search-content " >
		
			<span>
				<label>地区名称：</label>
				<input type="text" name="disName" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
	
		</div>
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="district:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="district/addPage.do?rel=${param.rel }&id=${param.superId}" target="dialog" width="800" height="400"  rel="${param.rel }_add"  title="添加地区">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="district:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="district/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="800" height="400"  rel="${param.rel}_update" warn="请先选择一个地区" title="修改地区信息">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="district:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="district/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个地区">批量删除</a>
				</shiro:hasPermission>
				
	
			</span>
			
			<span style="float:right">
				
				<c:if test="${!empty requestScope.spId}">
					
					<a class="btn btn-primary btn-small" href="district/load.do?superId=${requestScope.spId }&rel=${param.rel}" target="navTab" rel="${param.rel}" >返回上级</a>
					
				</c:if>
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="<c:out value="${requestScope.spName }" />"></table>



<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "district/query.do",
			queryParams: {
				superId: '<%=request.getParameter("superId")%>'
			},
			mustParamNames:"superId",
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true 
				},
				 {
					field : "disSort",
					title : "顺序号",
					align:"center",
					sortable : true,
					order:"desc",
					width : 40
				} ,  
				{
					field : "disName",
					title : "名称",
					align:"center",
					width : 200,
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="district/load.do?superId='+row.id+'&rel=<%=request.getParameter("rel")%>" target="navTab"  rel="<%=request.getParameter("rel")%>" >'+value+'</a>';
					}
				} , 
				{
					field : "disDesc",
					title : "描述",
					align:"center",
					width : 250
				}
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改地区信息','district/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>