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
				<label>状态：</label>
				<select  name="status" style="width: 110px;"  >
						   <option value="">请选择</option>
			               <option value="1">已发布</option>
			          	   <option value="0">终止</option>
			     </select>
			</span>
			<span>
				<label>创建时间：</label>
				<input type="text"  onFocus="WdatePicker()" readonly="readonly" name="startDate" style="width:100px"  />
					<span class="limit">-</span>
				<input type="text" readonly="readonly"  onFocus="WdatePicker()" name="endDate" style="width:100px"  />
			</span>
			</div>
		<!-- 功能区域 -->
		<div class="search-toolbar">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				
				<shiro:hasPermission name="news:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="新增新闻"	 href="news/addPage.do?rel=${param.rel }" target="navTab"  rel="${param.rel}_add" >新增新闻</a>
				 </shiro:hasPermission> 
				<shiro:hasPermission name="news:update">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true" href="news/updatePage.do?id={id}&rel=${param.rel}" target="navTab" rel="${param.rel}_update" title="修改新闻" ><span>修改新闻</span></a>
				 </shiro:hasPermission> 
				<shiro:hasPermission name="news:delete">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true" href="news/delete.do" target="selectedTodo" rel="ids" title="确定要删除吗?" ><span>批量删除</span></a>
				</shiro:hasPermission> 
				<shiro:hasPermission name="news:stop">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"  href="news/stop.do" target="selectedTodo" rel="ids"  title="确定要终止吗?"><span>终止</span></a>
				</shiro:hasPermission> 
				<shiro:hasPermission name="news:generate">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"  href="news/generate.do" target="selectedTodo" rel="ids"  title="确定要发布吗?"><span>发布</span></a>
				</shiro:hasPermission> 
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		
		</div>
			
	</form>
	<!-- 管理评论权限 -->
	<shiro:hasPermission name="news:management">
				<input type="hidden" name="pl"  />
  	</shiro:hasPermission>
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
				
				url : "news/loadallnews.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[{
					field : "newstitle",
					title : "标题",
					align:"center",
					width : 220,
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
						width : 80
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
						field : "status",
						title : "状态",
						align:"center",
						width : 60,
						formatter: function(value,row,index){
								if("1"==value){
									return "已发布";
								}else if("0"==value) {
									return "终止";
								}
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
						
					},
					{
						field : "action",
						title : "操作",
						align : "center",
						width : 80,
						formatter: function(value,row,index){
									var s="";
									if(rols_pl){
										 s+= '<a href="news/managementcontent.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_managementcontent&page=1&rows=10"  target="navTab" rel="<%=request.getParameter("rel")%>_managementcontent" title="管理评论">管理评论</a>';
									}
								return s;
						} 
					}
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openCenterTab('修改新闻','news/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
				}
			
			});
		});
//-->
</script>	
