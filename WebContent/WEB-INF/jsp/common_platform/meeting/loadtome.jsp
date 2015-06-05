<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：公共平台--会议管理--个人会议
--%>

<div id="${param.rel}_toolbar" class="search-div" >
	<form 	onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
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
					会议主题：
				</label> <input type="text" name="MName"
					value="<c:out value="${param.MName }" />" class="span2" /> </span>
			<span> <label>
					会议日期：
				</label> <input type="text" readonly="readonly" onFocus="WdatePicker()"
					size="13" class="span2" name="MDate" value="${param.MDate }" /> <span
				class="limit">-</span> <input type="text" readonly="readonly"
					onFocus="WdatePicker()" size="13" class="span2" name="MDate2"
					value="${param.MDate2 }" /> </span>

		</div>
		<div class="search-toolbar">
			<span style="float: right">
				<button class="btn btn-primary btn-small" type="submit">
					查询
				</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;</span>
		</div>


	</form>


</div>

<table id="${param.rel }_datagrid" toolbar="#${param.rel}_toolbar"></table>

<script type="text/javascript" >
<!--	
	
	$(function() {
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			nowrap:false,//true:只在一行显示数据,false:内容多时自动换行，效率低
			url : "meeting/queryTome.do",
			columns : [ [ 
			    
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
					field : "mDate",
					title : "会议日期",
					width : 90,
					align:"center",
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd");
					}

				},
				{
					field : "mStatime",
					title : "开始时间",
					width : 80,
					align:"center"

				},
				{
					field : "mEndtime",
					title : "结束时间",
					width : 80,
					align:"center"

				},
				{
					field : "mUid",
					title : "发布人",
					width : 140,
					align:"center"

				},
				{
					field : "mPublished",
					title : "发布时间",
					width : 140,
					align:"center"

				}
				
			
			] ]
		});
		
	});

//-->		
</script>
























