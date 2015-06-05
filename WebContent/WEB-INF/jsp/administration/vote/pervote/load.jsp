
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--个人投票首页
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
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;
			</span>
		</div>
		
		
	</form>
	
	<!-- 投票权限 -->
	<shiro:hasPermission name="pervote:tp">
				<input type="hidden" name="tp"  />
  	</shiro:hasPermission>
  	<!-- 查看投票结果 -->
  	<shiro:hasPermission name="pervote:show">
				<input type="hidden" name="ckjg"  />
  	</shiro:hasPermission>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"   ></table>
					
<script type="text/javascript" >

		$(function(){
			var _toolbar=$('#<%=request.getParameter("rel")%>_toolbar');
			
			if(_toolbar.find("input[name='tp']").length>0){
				var rols_tp=true;
			}
			
			if(_toolbar.find("input[name='ckjg']").length>0){
				var rols_ckjg=true;
			}
			
			
			
			$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
				
				url : "vote/pervoteload.do",
				
				columns :[[
					{
						field : "tpTitle",
						title : "标题",
						align:"center",
						width :200,
						
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
						width : 50,
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
						align:"center",
						width : 80,
						sortable : true,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd ");
						}
					},
					{
						field : "tpExpiry",
						title : "终止日期",
						align:"center",
						width : 80,
						sortable : true,
						formatter: function(value,row,index){
						
							return new Date(value).format("yyyy-MM-dd ");
						}
					},
					{
						field : "action",
						title : "操作",
						align : "center",
						width : 80,
						formatter: function(value,row,index){
									var s="";
									var nowDate =new Date().format("yyyy-MM-dd 00:00:00");
									
									var ks = new Date(row.tpValidity).format("yyyy-MM-dd HH:ss:mm");
									
									var js = new Date(row.tpExpiry).format("yyyy-MM-dd HH:ss:mm");
									
									
									if(nowDate>=ks&&nowDate<=js){
										if(rols_tp){//有投票权限
											  if(row.count==0){
												  s+= '<a href="vote/tp.do?id='+row.id+'&rel=<%=request.getParameter("rel")%>"  target="navTab" rel="<%=request.getParameter("rel")%>_tp" title="个人投票">投票</a>';
													 s=s+'&nbsp;&nbsp;';  
											  }
										}
									}
									if("2"!=row.tpSeeresult){
											if(rols_ckjg){//查看投票结果的权限
												 s+= '<a href="vote/lookresult.do?id='+row.id+'" target="navTab"  rel="<%=request.getParameter("rel")%>_look" title="查看投票结果">投票结果</a>'; 
											}
									} 
								return s;
						} 
					}
					
				]]
			
			});
		});

	
			


</script>
