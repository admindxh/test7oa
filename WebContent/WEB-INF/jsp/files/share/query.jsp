<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人文档管理
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  >
		
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
		

		<div class="search-toolbar" style="border-bottom:1px solid #ccc">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="shareFiles:addNode">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="shareFiles/addNodePage.do?rel=${param.rel }&id={#${param.rel }_wdSuperId}" target="dialog" width="650" height="360"  rel="${param.rel }_addnode"  title="创建文件夹">创建文件夹</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="shareFiles:addFile">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="shareFiles/addFilePage.do?rel=${param.rel }&id={#${param.rel }_wdSuperId}" target="dialog" width="650" height="360"  rel="${param.rel }_addfile"  title="创建文件">创建文件</a>
				</shiro:hasPermission>
				
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="shareFiles/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="650" height="360"  rel="${param.rel}_update" warn="请先选择一个文档" title="修改文档信息">修改</a>
					
				<shiro:hasPermission name="shareFiles:deleteFile">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="shareFiles/delFile.do" target="selectedTodo"  title="提示，只能删除文件，不能删除文件夹。确定要删除吗?" warn="至少勾选一个文件">批量删除文件</a>
				</shiro:hasPermission>
				
	
			</span>
			
			<span style="float:right">
				
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
		<div>
			  <%--注意，ul元素必须放在此隐藏域后面--%>
				<input type="hidden" id="${param.rel }_wdSuperId" name="wdSuperId" value="${param.wdSuperId }"/>
			    <ul class="breadcrumb" style="margin-bottom: 0px">
				    <li><a  href="javascript:;" onclick="updateShareFilesDatagrid('0','首页',this)">首页</a></li>
				</ul>
		</div>
		
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>



<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "shareFiles/query.do",
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
						if(row.wdType==0){
							//目录
							return '<a href="javascript:; " onclick="updateShareFilesDatagrid(\''+row.id+'\',\''+row.wdName+'\')"><img src="resource/images/icons/folder_Close.gif" align="top"/>&nbsp;'+value+'</a>';
						}else{
						
							return '<a href="shareFiles/show.do?id='+row.id+'" target="dialog"  rel="<%=request.getParameter("rel")%>_show" width="650" height="360" title="文档详情">'
									+'<img src="resource/images/icons/'+row.wdExt+'.gif" onerror="javascript:this.src=\'resource/images/icons/page.gif\'" align="top" />&nbsp;'+value+'</a>';
						}
						
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
						if(row.wdType==0){
							return '<a  href="shareFiles/delNode.do?id='+row.id+'" datagrid="<%=request.getParameter("rel")%>_datagrid" target="ajaxTodo"  title="确定要删除文件夹吗?<br/>注：，如果文件夹内有文件，请先清空文件夹内文件！" >删除</a>';
						}else{
							
							return '<a href="shareFiles/download.do?newName='+row.wdNewName+'&name='+encodeURIComponent(row.wdName)+'.'+row.wdExt+'&ext='+row.wdExt+'" target="_blank"  title="点击下载">下载</a>&nbsp;|&nbsp;<a  href="shareFiles/delFile.do?ids='+row.id+'" datagrid="<%=request.getParameter("rel")%>_datagrid" target="ajaxTodo"  title="确定要删除文件吗！" >删除</a>';
						}
						
					}
				} , 
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改文档信息','shareFiles/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:650,height:360});
			}
		});
		
	});
	
	//点击目录时刷新表格
	function updateShareFilesDatagrid(id,name,bt){
		var $dg=$('#<%=request.getParameter("rel")%>_datagrid');
		$dg.datagrid('load', {wdSuperId:id});
		var $wdSup=$('#<%=request.getParameter("rel")%>_wdSuperId').val(id);
		if(bt){
			$(bt).closest("li").nextAll().remove();
		}else{
			$wdSup.next().append(' <li><span class="divider">>></span><a href="javascript:;" onclick="updateShareFilesDatagrid(\''+id+'\',\''+name+'\',this)">'+name+'</a></li>');
		}
	}

//-->		
</script>