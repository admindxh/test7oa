<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公共平台--公告管理--部门公告
--%>


<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  formatFilterData="true"  >
		<div  class="search-content " >
			<span >
				<label>类型：</label>
				<select   name="afficheType"  style="width: 100px;" submitForm="change"   >
					<option value="">全部类型</option>
					<my:outOptions type="7"/>
				</select>
			</span>
			<span>
				<label>标题：</label>
				<input	type="text" name="afficheTitle" class="span2"  op="like" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			
			<span>
				<label>发布人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userId"  toName="user.id"  />
				
			</span>
			<span>
				<label>部门：</label>
				<a href="dept/lookUpPage.do?type=1" lookupGroup="dept" title="部门查询">
					<input  type="text"  readonly="readonly"  toName="dept.deptName"  class="span2"/>
				</a>
				<input type="hidden"  name="deptId" toName="dept.id"/>
			</span>
			<span>
				<label>发布时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="createTime" class="span2" fieldType="date" op="greaterorequal"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="createTime" class="span2" fieldType="date"  op="lessorequal"/>
				
			</span>
		
		</div>
	
		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="affiche:addDept">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						href="affiche/dept/addPage.do?rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="添加系统公告">添加</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="affiche:updateDept">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="affiche/dept/updatePage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一个公告" title="修改系统公告">修改</a>
					
				</shiro:hasPermission>
				<shiro:hasPermission name="affiche:deleteDept">
					<a  class="easyui-linkbutton"  icon="icon-remove"	plain="true"
						href="affiche/dept/del.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一个公告">批量删除</a>
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
			url : "affiche/dept/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "afficheType",
					title : "公告类型",
					width : 120,
					align:"center"

				},
				{
					field : "afficheTitle",
					title : "标题",
					width : 250,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						var html='<a href="affiche/dept/show.do?id='+row.id+'" target="dialog" width="1000" height="450" title="部门公告详情" rel="<%=request.getParameter("rel")%>_show">';
						if(row.isTop==1){
							html+='<img src="resource/images/arrow_up.gif"  style="margin-top:-3px;"/>  ';
						}
						return html+value+'</a>'; 
					}

				},
				{
					field : "userId",
					title : "发布人",
					width : 180,
					align:"center"
				},
				{
					field : "deptId",
					title : "所属部门",
					width : 180,
					align:"center"
				},
			 	 {
					field : "createTime",
					title : "发送时间",
					width : 150,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				} 
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改部门公告','affiche/dept/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>