<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：文档管理--图片空间--类型管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			<span>
				<label>名称：</label>
				<input	type="text" name="name" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span >
				<label>描述：</label>
				<input type="text" name="remark"  class="span3"/>
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="role:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="imageSpace/type/addPage.do?rel=${param.rel }" target="dialog" width="600" height="350"  rel="${param.rel }_add"  title="添加图片类型">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="imageSpace/type/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="600" height="350"  rel="${param.rel}_update" warn="请先选择一个图片类型" title="修改图片类型">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="role:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="imageSpace/type/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个图片类型">批量删除</a>
				</shiro:hasPermission>
				
				
				
				
				
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
			nowrap:false,
			url : "imageSpace/type/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "name",
					title : "类型名称",
					width : 150,
					align:"center",
					sortable : true

				},
			 	 {
					field : "remark",
					title : "说明",
					width : 200
				},
				{
					field : "createTime",
					title : "创建时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}  
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改图片类型信息','imageSpace/type/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:600,height:350});
			}
		});
		
	});

//-->		
</script>
