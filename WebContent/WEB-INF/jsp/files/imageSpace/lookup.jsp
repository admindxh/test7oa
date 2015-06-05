<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：文档管理--图片空间--图片查找带回
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
		
		
		</div>
	
		<div class="search-toolbar" >
			
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
					field : "createTime",
					title : "上传时间",
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
					width : 50,
					align:"center",
					formatter : function(value, row, index) {
						
						return  "<a href='javascript:;' onclick=$.bringBack({id:'"+row.id+"',name:'"+row.name+"',savePath:'"+row.savePath+"'})>选择</a>";
					}

				}
				
			
			] ],
			onDblClickRow:function(rowIndex, row){
				$.bringBack({id:row.id,name:row.name,savePath:row.savePath});
			}
		});
		
	});

//-->		
</script>
