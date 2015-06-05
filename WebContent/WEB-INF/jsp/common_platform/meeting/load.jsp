<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：公共平台--会议管理
--%>

<div id="${param.rel}_toolbar" class="search-div">
	<form onsubmit="return datagridSearch(this,'${param.rel }_datagrid');">
		<div class="search-content ">
			<span> <label style="width: 60px;">
					会议室：
				</label> <select class="combox" name="MRid" sValue="${param.MRid}">
					<option value="">
						全部会议室
					</option>
					<c:forEach var="l" items="${requestScope.list}">
						<option value="${l.id }">
							${l.mrName }
						</option>
					</c:forEach>
				</select> </span>
				
					<span> <label>
					会议类型：
				</label> <select class="combox" name="MType" sValue="${param.MType}">
					<option value="">
							--请选择会议类型--
						</option>
						<my:outOptions type="2" />
				</select> </span>
				
			<span> <label>
					会议主题：
				</label> <input type="text" name="MName"
					class="span2" />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			 </span>
			<span> <label>
					会议日期：
				</label> <input type="text" readonly="readonly" onFocus="WdatePicker()"
					size="13" class="span2" name="MDate" /> <span
				class="limit">-</span> <input type="text" readonly="readonly"
					onFocus="WdatePicker()" size="13" class="span2" name="MDate2"
					/> </span>

		</div>
		<div class="search-toolbar" >
			<span style="float: left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
			 <shiro:hasPermission name="meeting:add">
					<a class="easyui-linkbutton" icon="icon-add" plain="true"
						href="meeting/addPage.do?rel=${param.rel }" target="navTab"
						 title="新增会议" rel="${param.rel}_add" title="添加会议">新增会议</a>
				</shiro:hasPermission>
				 <shiro:hasPermission name="meeting:update">
					<a class="easyui-linkbutton" icon="icon-edit" plain="true"
						href="meeting/updatePage.do?id={id}&rel=${param.rel}"
						target="navTab"  rel="${param.rel}_update"
						warn="请先选择一个会议" title="修改会议">修改会议</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="meeting:delete">
					<a class="easyui-linkbutton" icon="icon-remove" plain="true"
						href="meeting/del.do" target="selectedTodo" rel="ids"
						title="确定要删除吗?" warn="至少选择一条会议">批量删除</a>
				</shiro:hasPermission></span>
			<span style="float: right">
				<button class="btn btn-primary btn-small" type="submit">
					查询
				</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;</span>
		</div>


	</form>


</div>

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar"
	></table>

<script type="text/javascript" >
<!--	
	
	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:false,//true:只在一行显示数据,false:内容多时自动换行，效率低
			
			url : "meeting/query.do",
			columns : [ [ 
			    {
			    	field:"ck",
			    	title : "勾选",
			    	checkbox:true
			    },
				{
					field : "mType",
					title : "会议类型",
					width : 140,
					align:"center"

				},
				{
					field : "mRid",
					title : "会议室",
					width : 150,
					align:"center"

				},
				{
					field : "mName",
					title : "会议主题",
					width : 220,
					align:"center",
					formatter: function(value,row,index){
						
						return '<a  datagrid="${param.rel }_datagrid" href="meeting/find.do?id='+row.id+'" target="navTab"    rel="${param.rel}_find" title="会议详情">'+value+'</a>';
					}

				},
				{
					field : "MDate",
					title : "会议日期",
					width : 90,
					align:"center",
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(row.mDate).format("yyyy-MM-dd");
					}

				},
				{
					field : "MStatime",
					title : "开始时间",
					width : 80,
					align:"center",
					sortable : true,
					formatter : function(value, row, index) {

						return row.mStatime;
					}

				},
				{
					field : "MEndtime",
					title : "结束时间",
					width : 80,
					align:"center",
					sortable : true,
					formatter : function(value, row, index) {

						return row.mEndtime;
					}

				},
				{
					field : "MUid",
					title : "发布人",
					width : 140,
					align:"center",
					sortable : true,
					formatter : function(value, row, index) {

						return row.mUid;
					}

				},
				{
					field : "MPublished",
					title : "发布时间",
					width : 140,
					align:"center",
					sortable : true,
					formatter : function(value, row, index) {

						return row.mPublished;
					}

				}
				
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openCenterTab('修改会议','meeting/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
			}
		});
		
	});

//-->		
</script>
























