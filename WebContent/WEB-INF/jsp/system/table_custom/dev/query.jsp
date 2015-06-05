<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--导出打印自定义管理--查询
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		<input id="${param.rel}_tbType" name="tbType" type="hidden" />
		<div  class="search-content " >
			
			<span>
				<label>属性名：</label>
				<input	type="text" name="fieldName" class="span2"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>显示名：</label>
				<input	type="text" name="fieldTitle"  class="span2" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>别名：</label>
				<input	type="text" name="fieldAnotherTitle"  class="span2" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加字段"	 href="tableCustom/dev/addPage.do?rel=${param.rel}&tbType={#${param.rel}_tbType}" warn="请先点击左侧选择模块" target="dialog" width="800" height="400" rel="${param.rel}_add" >添加</a>
					
				
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改字段信息"	href="tableCustom/dev/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" warn="请先选择一个字段" >修改</a>
					
				
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="tableCustom/dev/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少选择一个字段">批量删除</a>
				
				
					
			</span>
			
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  title="模块自定义管理"></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			border:true,
			nowrap:false,
			url : "tableCustom/dev/query.do",
			queryParams: {
				tbType: 0
			},
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				{
					field : "fieldSort",
					title : "序号",
					align:"center",
					sortable : true,
					order:"desc",
					width : 40
				},
			 	 {
					field : "fieldName",
					title : "属性名",
					align:"center",
					width : 170
					
				},
				{
					field : "fieldTitle",
					title : "显示名",
					align:"center",
					width : 150
					
				},
				{
					field : "fieldAnotherTitle",
					title : "别名",
					align:"center",
					width : 150
					
				},
				{
					field : "fieldType",
					title : "类型",
					align:"center",
					width : 120,
					formatter: function(value,row,index){
						
						if(value==1){
							return "文本";
						}else if(value==2){
							return "数字";
						}else if(value==3){
							return "日期";
						}else if(value==4){
							return "日期+时间";
						}else if(value==5){
							return "用户id(转换用户姓名)";
						}else if(value==6){
							return "部门id(转换部门名称)";
						}else if(value==7){
							return "用户id(转换用户登录名)";
						}else{
							return "";
						}
					}
					
				},
				{
					field : "isExport",
					title : "导出",
					align:"center",
					width : 30,
					formatter: function(value,row,index){
						if(value==1){
							return '<img src="resource/images/small/Check_16x16.png" alt="是" title="是"/>';
						}else{
							return '<img src="resource/images/small/Delete_16x16.png" alt="否" title="否"/>';
						}
					}
					
				},
				{
					field : "isPrint",
					title : "打印",
					align:"center",
					width : 30,
					formatter: function(value,row,index){
						if(value==1){
							return '<img src="resource/images/small/Check_16x16.png" alt="是" title="是"/>';
						}else{
							return '<img src="resource/images/small/Delete_16x16.png" alt="否" title="否"/>';
						}
					}
					
				},
				{
					field : "isShow",
					title : "显示",
					align:"center",
					width : 30,
					formatter: function(value,row,index){
						if(value==1){
							return '<img src="resource/images/small/Check_16x16.png" alt="是" title="是"/>';
						}else{
							return '<img src="resource/images/small/Delete_16x16.png" alt="否" title="否"/>';
						}
					}
					
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改字段信息','tableCustom/dev/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:400});
			}
		});
		
	});

//-->		
</script>
