<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：行政办公--投票管理页面
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		<!-- 查询条件区域 -->
		<div class="search-content">
			<span>
				<label>标题：</label>
				<input type="text" name="tpTitle"  class="span2" />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<span>
				<label>发布人：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="userid"  toName="user.id"  />
			</span>
			<span>
				<label>状态：</label>
				<select name="stuts" style="width: 110px;" submitForm="change">
					<option value="">==请选择==</option>
						<option value="0">未发布</option>
						<option value="1">生效</option>
						<option value="2">终止</option>
				</select>
			</span>
			<span>
				<label>创建时间：</label>
				<input  type="text"  onFocus="WdatePicker()" readonly="readonly" name="startDate" style="width:100px"   />
					<span class="limit">-</span>
				<input  type="text" readonly="readonly"  onFocus="WdatePicker()" name="endDate" style="width:100px"  />
			</span>
		</div>	
		<!-- 功能区域 -->
		<div class="search-toolbar">
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				   
				 	<shiro:hasPermission name="vote:add">
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
					  title="新增投票"	 href="vote/preaddPage.do?rel=${param.rel }" target="navTab"  rel="${param.rel}_add" >新增投票</a>
				  </shiro:hasPermission>
				 <shiro:hasPermission name="vote:update">
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true"
					  title="修改投票"	 href="vote/updatePage.do?id={id}&rel=${param.rel}" target="navTab"  rel="${param.rel}_update" >修改投票</a>
				</shiro:hasPermission> 
				 <shiro:hasPermission name="vote:delete">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="vote/delete.do" target="selectedTodo"  title="请谨慎操作！确定要删除吗?" warn="至少选择一个投票">批量删除</a>
				</shiro:hasPermission> 
				<shiro:hasPermission name="vote:stop">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="vote/stop.do " target="selectedTodo"  title="确定要终止吗?" warn="至少选择一个投票">立即终止</a>
				</shiro:hasPermission> 
				<shiro:hasPermission name="vote:valid">
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="vote/valid.do" target="selectedTodo"  title="确定要生效吗?" warn="至少选择一个投票">生效</a>
				</shiro:hasPermission> 
			</span>
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
	  </div>
		
	</form>
	<!-- 子投票权限 -->
	<shiro:hasPermission name="vote:read">
				<input type="hidden" name="ztp"  />
  	</shiro:hasPermission>
  	<!-- 发布权限 -->
  	<shiro:hasPermission name="vote:publish">
				<input type="hidden" name="fb"  />
  	</shiro:hasPermission>
  	<!-- 数据清空权限 -->
  	<shiro:hasPermission name="vote:clean">
				<input type="hidden" name="sjqk"  />
  	</shiro:hasPermission>
  	<!--查看投票人权限 -->
  	<shiro:hasPermission name="vote:showman">
				<input type="hidden" name="cktpr"  />
  	</shiro:hasPermission>
  
  	
</div>
<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"   ></table>


<script type="text/javascript" >
		$(function(){
			var _toolbar=$('#<%=request.getParameter("rel")%>_toolbar');
			
			if(_toolbar.find("input[name='ztp']").length>0){
				var rols_ztp=true;
			}
			
			if(_toolbar.find("input[name='fb']").length>0){
				var rols_fb=true;
			}
			
			if(_toolbar.find("input[name='sjqk']").length>0){
				var rols_sjqk=true;
			}
			
			if(_toolbar.find("input[name='cktpr']").length>0){
				var rosl_cktpr=true;
			}
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "vote/loadvote.do",
				frozenColumns:[[
					{
						field:"ck",
						title : "勾选",
						checkbox:true
					}
					
				]],
				columns :[[
				        {
							field : "tpTitle",
							title : "标题",
							align:"center",
							width : 250,
							formatter: function(value,row,index){
								var html='<a href="vote/seeresult.do?id='+row.id+'" target="navTab"  rel="<%=request.getParameter("rel")%>_show"  title="查看投票结果详情" >';
								if(row.tpStick==1){
									html+='<img src="resource/images/arrow_up.gif"  style="margin-top:-3px;"/>';
								}
								return html+value+'</a>';
							}
						},
					{
						field : "userName",
						title : "发布人",
						align:"center",
						width : 100
					},
					{
						field : "tpAnonymous",
						title : "匿名",
						align:"center",
						width : 80,
						formatter: function(value,row,index){
								if("0"==value){
									return "不允许";
								}else{
									return "允许";
								}
						}
					},
					{
						field : "tpValidity",
						title : "开始日期",
						sortable : true,
						align:"center",
						width : 100,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd ");
						}
					},
					{
						field : "tpExpiry",
						title : "终止日期",
						align:"center",
						width : 100,
						sortable : true,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd ");
						}
					},
					{
						field : "tpCreatetime",
						title : "创建日期",
						align:"center",
						width : 150,
						sortable : true,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd HH:mm:ss");
						}
					},
					{
						field : "stuts",
						title : "状态",
						align:"center",
						width : 60,
						formatter: function(value,row,index){
								if("0"==value){
									return "未发布";
								}else if("1"==value){
									return "生效";
								}else if("2"==value){
									return "终止";
								}
						}
					},
					{
						field : "action",
						title : "操作",
						align : "center",
						width : 180,
						formatter: function(value,row,index){
									var s="";
									if("0"==row.stuts){
										
										if(rols_ztp){//有子投票权限
											 s+= '<a href="vote/loadQuestion.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_addztp"  target="navTab" rel="<%=request.getParameter("rel")%>_addztp" title="查看子投票">子投票</a>';
											 s=s+'&nbsp;&nbsp;'; 	
										}
										if(rols_fb){//有发布权限
											 s+= '<a href="vote/publish.do?id='+row.id+'" target="ajaxTodo" datagrid="<%=request.getParameter("rel")%>_datagrid" title="确定要发布吗？">立即发布</a>';
											 s=s+'&nbsp;&nbsp;';
										}
									}else if("2"==row.stuts){
											if(rols_sjqk){//有数据清空的权限
												 s+= '<a href="vote/deldata.do?id='+row.id+'" target="ajaxTodo"  title="确定要清空数据吗？">清空数据</a>';
												 s=s+'&nbsp;&nbsp;';
											}
											if(rols_ztp){
												 s+= '<a href="vote/loadQuestion.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>_addztp" target="navTab" rel="<%=request.getParameter("rel")%>_addztp" title="查看子投票">子投票</a>';
												 s=s+'&nbsp;&nbsp;';
											}
											
									} 
									if("0"==row.tpAnonymous){
										
											if(rosl_cktpr){//查看投票人 的权限
												 s+= '<a href="vote/findman.do?id='+row.id+'" target="navTab" rel="<%=request.getParameter("rel")%>_look" title="查看投票人">查看投票人</a>'; 
											}
											
									} 
								return s;
						} 
					}
					
				]],
				onDblClickRow:function(rowIndex, rowData){
					MUI.openCenterTab('修改投票','vote/updatePage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',false,false);
				}
			});
		});

</script>
