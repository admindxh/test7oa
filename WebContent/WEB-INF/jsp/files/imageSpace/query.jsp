<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：文档管理--图片空间--图片管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			
			<span>
				<label>类型：</label>
				<select name="type"   ajaxUrl="imageSpace/type/list.do" submitForm="change">
    				<option value="" >全部类型</option>
    			</select>
			</span>
			<span>
				<label>名称：</label>
				<input	type="text" name="name" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>上传时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="role:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="imageSpace/addPage.do?rel=${param.rel }" target="dialog" width="700" height="380"  rel="${param.rel }_add"  title="添加图片">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="imageSpace/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="600" height="350"  rel="${param.rel}_update" warn="请先选择一个图片" title="修改图片">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="role:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="imageSpace/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个图片">批量删除</a>
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
			url : "imageSpace/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "tp",
					title : "图片",
					width : 220,
					align:"center",
					formatter: function(value,row,index){
						return '<img src='+row.savePath+' style="max-width:200px ;max-height: 150px" />';
					}

				},
			 	 {
					field : "name",
					title : "名称",
					width : 200,
					align:"center",
					formatter: function(value,row,index){
						
						return '<a href="imageSpace/show.do?id='+row.id+'" target="dialog" width="900" height="450" rel="<%=request.getParameter("rel")%>_show" title="预览图片">'+value+'</a>';
					}
				},
				{
					field : "savePath",
					title : "图片地址",
					width : 250,
					formatter: function(value,row,index){
						return appWebRoot+row.savePath;
					}
					
				},
				 {
					field : "createTime",
					title : "上传时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改图片信息','imageSpace/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:600,height:350});
			}
		});
		
	});

//-->		
</script>
