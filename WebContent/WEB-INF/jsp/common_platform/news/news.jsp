<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公各平台--新闻管理首页面
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<!-- 查询条件区域 -->
		<div class="search-content">
			<span>
				<label>标题：</label>
				<input type="text" name="newstitle"  class="span2" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>类型：</label>
				<select  name="typeid" style="width: 110px;" >
			                <option value="">请选择</option>
			                <my:outOptions type="3"/>
			    </select>
			</span>
			<span>
				<label>创建时间：</label>
				<input type="text"  onFocus="WdatePicker()" readonly="readonly" name="startDate" style="width:100px"  />
					<span class="limit">-</span>
				<input type="text" readonly="readonly"  onFocus="WdatePicker()" name="endDate" style="width:100px"  />
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
			</div>
		
	</form>
	
</div>
	
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"   ></table>	

<script type="text/javascript">
<!--
	$(function(){
			var _toolbar=$('#<%=request.getParameter("rel")%>_toolbar');
			if(_toolbar.find("input[name='pl']").length>0){
				var rols_pl=true;
			}
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "news/loadpernews.do",
				
				columns :[[{
					field : "newstitle",
					title : "标题",
					align:"center",
					width : 200,
					formatter: function(value,row,index){
						var html='<a href="news/look.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show&page=1&rows=10" target="navTab"  rel="<%=request.getParameter("rel")%>_show"  title="查看详情" >';
						if(row.stick==1){
							html+='<img src="resource/images/arrow_up.gif"  style="margin-top:-3px;"/>';
							
						}
						
						return html+value+'</a>';   
						
					}
				},
					{
						field : "promulgator",
						title : "发布人",
						align:"center",
						width : 100
					},
					{
						field : "typeid",
						title : "类型",
						align:"center",
						width : 70,
						
					},
					{
						field : "createdtime",
						title : "发布时间",
						align:"center",
						width : 100,
						sortable : true,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					},
					{
						field : "hits",
						title : "点击数",
						align:"center",
						width : 50,
						
					},
					{
						field : "contents",
						title : "评论(条)",
						align:"center",
						width : 50,
						
					}
				]]
			
			});
		});

//-->
</script>	
	
	
	