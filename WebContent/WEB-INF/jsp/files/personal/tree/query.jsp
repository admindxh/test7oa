<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人文档管理--树状模式查询
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
				
				<shiro:hasPermission name="personalFiles:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="personalFiles/addFilePage.do?rel=${param.rel }&id={#${param.rel }_tree_wdSuperId}" target="dialog" width="650" height="360"  rel="${param.rel }_addfile"  title="创建文件">创建文件</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="personalFiles:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="personalFiles/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="650" height="360"  rel="${param.rel}_update" warn="请先选择一个文档" title="修改文档信息">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="personalFiles:delete">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="personalFiles/delFile.do" target="selectedTodo"  title="提示，只能删除文件，不能删除文件夹。确定要删除吗?" warn="至少勾选一个文件">批量删除文件</a>
				</shiro:hasPermission>
				
	
			</span>
			
			<span style="float:right">
				
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
		<input type="hidden" id="${param.rel }_tree_wdSuperId"  name="wdSuperId"  value="${param.wdSuperId }"/>
		
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar" border="true" title="个人文档"></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "personalFiles/query.do?wdType=1",
			nowrap:false,
			queryParams: {
				wdSuperId: '<%=request.getParameter("wdSuperId")%>'
			},
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
						
						
						return '<a href="personalFiles/show.do?id='+row.id+'" target="dialog"  rel="<%=request.getParameter("rel")%>_show" width="650" height="360" title="文档详情">'
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
						
							return '<a href="personalFiles/download.do?newName='+row.wdNewName+'&name='+encodeURIComponent(row.wdName)+'.'+row.wdExt+'&ext='+row.wdExt+'" target="_blank"  title="点击下载">下载</a>';
						
					}
				} , 
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改文档信息','personalFiles/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:650,height:360});
			}
		});
		
	});
	

//-->		
</script>
