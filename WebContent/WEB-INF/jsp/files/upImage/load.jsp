<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：文档管理--上传图片管理--图片管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<div  class="search-content " >
			
			
			<span>
				<label>名称：</label>
				<input	type="text" name="title" class="span2"  />
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
				
				 
				<shiro:hasPermission name="UpImage:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="UpImage/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个图片">批量删除</a>
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
			url : "UpImage/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				 
			 	 {
					field : "title",
					title : "名称",
					width : 200,
					align:"center",
					formatter: function(value,row,index){
						
						return '<a href="UpImage/find.do?id='+row.id+'" target="dialog" width="1000" height="550" rel="<%=request.getParameter("rel")%>_show" title="预览图片">'+value+'</a>';
					}
				},
				{
					field : "address",
					title : "地址",
					align:"center",
					width : 250 
					
				},
				 {
					field : "createtime",
					title : "上传时间",
					align:"center",
					width : 150,
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
