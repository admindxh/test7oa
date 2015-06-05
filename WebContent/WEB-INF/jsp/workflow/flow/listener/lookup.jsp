<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--流程监听器--查找带回  用于在线流程设计
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel}_datagrid');"  >
		
		<div  class="search-content " >
			<span>
				<label>名称：</label>
				<input	type="text" name="name" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				
			</span>
			
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<a class="easyui-linkbutton"  icon="icon-add"	plain="true" 
				 href="javascript:;"  onclick="checkListener()" >批量选择</a>
				 
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>	 
 
<script type="text/javascript">
<!--
	$(function() {
		var lsType='<%=(String)request.getParameter("type") %>';
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : 'flowListener/lookUp.do?ids='+getListenerIdsByType(lsType)+'&type='+lsType,
			pagination:false,
			nowrap : false,
			columns : [ [ 
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
	             {
	 				field : 'name',
	 				title : '名称',
	 				width : 150
	 			},
			  {
				field : 'type',
				title : '类型',
				width : 80,
				formatter : function(value, rec, index) {
					if(rec.type==1){
						return "执行监听器";
					}else if(rec.type==2){
						return "用户任务监听器";	
					}
				}
				
				
			},
			{
				field : 'event',
				title : '事件',
				width : 80,
				align:'center'
			},
			{
				field : 'valueType',
				title : '执行类型',
				align:'center',
				width : 80,
				formatter : function(value, rec, index) {
					
					if(rec.valueType==1){
						return "Java class";
					}else if(rec.valueType==2){
						return "expression";	
					}
					
				}
			},
			{
				field : 'value',
				title : '执行内容',
				align:'center',
				width : 150
			},
			{
				field : 'desc',
				title : '备注',
				width : 100
				
			}
			] ]
			
		});
	
	});
	//选择
	function checkListener() {
		
		var rows = $('#<%=request.getParameter("rel")%>_datagrid').datagrid('getChecked');
		if (rows.length > 0) {
			for(var i=0;i<rows.length;i++){
				var row=rows[i];
				var lsType='<%=(String)request.getParameter("type") %>';
				if(lsType=="1"){
					//执行监听器
					addListener(row);
					$('#oldListenerDatagrid').datagrid('appendRow',{
						id:row.id,
						name:row.name,
						event:row.event,
						valueType:row.valueType,
						value:row.value
					});
				}else{
					//任务监听器
					addTaskListener(row);
					$('#oldTaskListenerDatagrid').datagrid('appendRow',{
						id:row.id,
						name:row.name,
						event:row.event,
						valueType:row.valueType,
						value:row.value
					});
				}
				
				
			}
			//关闭弹出框
			$lookupWindow.window('close');
			
			
		} else {
			Msg.alert('提示', '至少选择一个监听器！', 'error');
		}
	}
	//根据类型判断是任务监听还是执行监听
	function getListenerIdsByType(type){
		if(type==1){
			return getOldListenerIds();
		}else{
			return getOldTaskListenerIds();
		}
	}
//-->
</script>
