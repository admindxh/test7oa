<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品购买-查看物品领用界面--%>
<div id="${param.rel}_toolbar" class="search-div">
   <form   onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
    		<!-- 查询条件区域 -->
			<div class="search-content">
				<span>
					<label>购买物品:</label>
					<input type="text" name="usingnameId" class="span2" />
					&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
				</span>
				<span>
					<label>购买人：</label>
					
					<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
						<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
					</a>
					<input type="hidden"  name="purchaser"  toName="user.id"  />
				
				</span>
				<span>
					<label>购买时间：</label>
					
						<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
						--
						<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
					
				</span>
				
				
			</div>   
			<!-- 功能区域 -->
		<div class="search-toolbar">
			<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				   
				  <shiro:hasPermission name="shop:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="添加物品购买"	 href="purchasegoods/addPage.do?rel=${param.rel }" target="dialog" width="900" height="450"  rel="${param.rel}_add" >添加购买</a>
				 </shiro:hasPermission> 
				<shiro:hasPermission name="shop:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改物品购买记录"	 href="purchasegoods/updatePage.do?id={id}&rel=${param.rel}" target="dialog" width="900" height="450"  rel="${param.rel}_update" >修改购买</a>
				 </shiro:hasPermission>
				<shiro:hasPermission name="shop:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="purchasegoods/deletes.do" target="selectedTodo"  title="请谨慎操作！确定要删除吗?删除购买记录不会修改物品的库存量，如果有变动，请自行修改。" warn="至少选择一个">批量删除</a>
				</shiro:hasPermission>
				
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
			
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "purchasegoods/loadall.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[
					{
						field : "buyName",
						title : "购买人姓名",
						align:"center",
						width : 120,
						
						
					},
				 {
					
					field : "name",
					title : "物品名称",
					align:"center",
					width : 150,
					formatter: function(value,row,index){
						
						return '<a href="goodsmanagerment/lookpage.do?id='+row.usingnameId+'&rel=<%=request.getParameter("rel")%>_shell" target="dialog" width="900" height="450"  rel="<%=request.getParameter("rel")%>_shell"  title="查看物品详情" >'+value+'</a>';
					}
				},
					
					{
						field : "unitprice",
						title : "单价(元)",
						align:"center",
						width : 100,
						
					},
					{
						field : "amount",
						title : "数量",
						align:"center",
						width : 80,
						
					},
					{
						field : "totalmonety",
						title : "总额(元)",
						align:"center",
						width : 120,
						
					},
					{
						field : "buyTime",
						title : "购买时间",
						align:"center",
						width : 120,
						sortable : true,
						formatter: function(value,row,index){
							
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					},
					{
						field : "action",
						title : "操作",
						align : "center",
						width : 60,
						formatter: function(value,row,index){
							
							return '<a href="purchasegoods/lookPage.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_show" target="dialog" width="900" height="450"   rel="<%=request.getParameter("rel")%>_show"  title="查看物品购买详情" >详情</a>';
						}
					}
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openDialog('修改物品购买记录','purchasegoods/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:900,height:450});
				}
			
			});
		});
//-->
</script>	
	 
		

