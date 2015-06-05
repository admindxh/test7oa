<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--执行监听器 --%>


<div  style="padding: 1px;" title="执行监听器"	class="properties-menu">
						
		<table id="oldListenerDatagrid" ></table>
				
</div>
<script type="text/javascript">
<!--	
	$(function(){
		//根据监听器id，查询数据库监听器信息
		$('#oldListenerDatagrid').datagrid({
				url : 'flowListener/getByIds.do',
				queryParams: {
					ids: getOldListenerIds()
				},
				toolbar : 
					[{
			    		text:'添加',
			               iconCls:'icon-add',
			               handler:function(){
			            	
			            	   $lookupWindow.dialog({  
									title:"监听器查询",
									width:850,  
								    height:450,  
								    modal:true,
								    minimizable:false
								 
								}).dialog("refresh", "flowListener/lookUpPage.do?type=1&rel=lookup_flowListener");  
			            	  
			            	  
			              }
			            },'-',{
			                text:'删除',
			               iconCls:'icon-remove',
			              handler:function(){
			            	  
			            	  var rows=$('#oldListenerDatagrid').datagrid('getSelections');
			            	  for(var i=0;i<rows.length;i++){
			            		  var row=rows[i];
			            		  var index=$('#oldListenerDatagrid').datagrid('getRowIndex',row);
				            	  $('#oldListenerDatagrid').datagrid('deleteRow',index);
				            	  removeListener(row.id);
			            	  }
			            	  
			            	  
			              }
			            }],
				title : '添加，删除操作会自动保存',
				fitColumns : false,
				nowrap : true,
				border : false,
				rownumbers:false,
				pagination:false,
				frozenColumns : [ [ {
					field : 'name',
					title : '名称',
					width:100,
					formatter : function(value, rec, index) {
						return "<span title='"+rec.desc+"'>"+rec.name+"</span>";
					}
					
				} ] ],
				columns : [ [ 
				{
					field : 'event',
					title : '事件',
					width:80,
					align:'center'
				},
				{
					field : 'valueType',
					title : '执行类型',
					width:80,
					align:'center',
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
					width:'auto'
					
				}
			] ]
			 
		});

	});
//-->
</script>