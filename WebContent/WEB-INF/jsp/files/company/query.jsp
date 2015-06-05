<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公司文档管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content " >
		
			<span>
				<label>文件名称：</label>
				<input type="text" name="wdName" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>创建时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
	
		</div>
		

		<div class="search-toolbar">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="companyFiles:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="companyFiles/addPage.do?rel=${param.rel }&wdSuperId={#${param.rel }_wdSuperId}" target="dialog" width="650" height="360"  rel="${param.rel }_addfile" warn="请先选择文档分类" title="创建文件">创建文件</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="companyFiles:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="companyFiles/updatePage.do?id={id}&rel=${param.rel}&wdSuperId={#${param.rel }_wdSuperId}" target="dialog"  width="650" height="360"  rel="${param.rel}_update" warn="请先选择一个文档" title="修改文档信息">修改</a>
					
				</shiro:hasPermission>
				
			</span>
			
			<span style="float:right">
				
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
		
		<input type="hidden" id="${param.rel }_wdSuperId" name="wdSuperId" />
		
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>



<script type="text/javascript" >
<!--	
	$(function() {
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "companyFiles/query.do",
			border:true,
			nowrap:false,
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "wdName",
					title : "文件名",
					sortable : true,
					width : 300,
					formatter: function(value,row,index){
						
						return '<a href="companyFiles/show.do?id='+row.id+'" target="dialog"  rel="<%=request.getParameter("rel")%>_show" width="650" height="360" title="文档详情">'
									+'<img src="resource/images/icons/'+row.wdExt+'.gif" onerror="javascript:this.src=\'resource/images/icons/page.gif\'" align="top" />&nbsp;'+value+'</a>';
					}
				} ,
				{
					field : "wdDesc",
					title : "描述",
					align:"center",
					width : 250
				}, 
				{
					field : "wdUserId",
					title : "创建人",
					align:"center",
					width : 150
				}, 
				 {
					field : "wdCreateTime",
					title : "创建时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				},
				{
					field : "op",
					title : "操作",
					align:"center",
					width : 100,
					formatter: function(value,row,index){
						
						return '<a href="companyFiles/download.do?newName='+row.wdNewName+'&name='+encodeURIComponent(row.wdName)+'.'+row.wdExt+'&ext='+row.wdExt+'" target="_blank"  title="点击下载">下载</a>&nbsp;|&nbsp;<a  href="companyFiles/del.do?id='+row.id+'" datagrid="<%=request.getParameter("rel")%>_datagrid" target="ajaxTodo"  title="确定要删除文件吗！" >删除</a>';
						
						
					}
				} , 
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				var wdSuperId=$('#<%=request.getParameter("rel")%>_wdSuperId').val();
				MUI.openDialog('修改文档信息','companyFiles/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>&wdSuperId='+wdSuperId,'<%=request.getParameter("rel")%>_update',{width:650,height:360});
			}
		});
		
	});
	
	

//-->		
</script>