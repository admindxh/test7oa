<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-考勤记录
--%>
<div id="${param.rel}_toolbar" class="search-div">
	<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');" >
		
		<div  class="search-content" >
			
			<span>
				<label>考勤用户：</label>
				
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName" class="span2"/>
				</a>
				<input type="hidden"  name="signuser"  toName="user.id"  />
				
			</span> 
			<span>
				<label>登记时间：</label>
				
					<input type="text"  readonly="readonly" onFocus="WdatePicker()"  name="startDate" class="span2"/>
					--
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="endDate" class="span2"/>
				
			</span>
 			<span >
				<label>登记状态：</label>
				<select name="islate"  class="easyui-validatebox"  style="width: 80px" submitForm="change" >
					<option value="">全部</option>
					<option value="0">正常</option>
					<option value="1">迟到</option>
					<option value="2">早退</option>
					<option value="3">请假</option>
					<option value="4">旷工</option>
					<option value="5">外出</option>
					<option value="6">其他</option>
				</select>
			</span>
			<span >
				<label>考勤类型：</label>
				<select name="attType"  class="easyui-validatebox"  style="width: 110px" submitForm="change">
					<option value="">全部</option>
					<option value="1">第一次上班 </option>
					<option value="2">第一次下班 </option>
					<option value="3">第二次上班</option>
					<option value="4">第二次下班 </option>
					<option value="5">第三次上班</option>
					<option value="6">第三次下班</option>
					</select> 
			</span>
			
		</div>
	

		<div class="search-toolbar" >
			<span style="float:left;">
				<a class="easyui-linkbutton clearDgChecked"  icon="icon-redo"	plain="true"  title="清空所有勾选项" >清空勾选</a>
				<shiro:hasPermission name="attendance:delSign"> 
					<a class="easyui-linkbutton"  icon="icon-remove"	plain="true"
					   	 href="attendance/delSign.do" target="selectedTodo"  title="确定要删除吗?" warn="至少勾选一条考勤记录">批量删除</a>
				 
				</shiro:hasPermission>
				<shiro:hasPermission name="attendance:updateSign">  
					<a class="easyui-linkbutton"  icon="icon-edit"	plain="true" 
						 href="attendance/updateSignPage.do?id={id}&rel=${param.rel}" target="dialog"  width="1000" height="450"  rel="${param.rel}_update" warn="请先选择一条考勤记录" title="修改考勤记录">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="attendance:repairSign"> 
				 
					<a class="easyui-linkbutton"  icon="icon-add"	plain="true"
						 href="attendance/repairSignUpdatePage.do?rel=${param.rel }" target="dialog" width="1000" height="450"  rel="${param.rel }_add"  title="考勤补录">考勤补录</a>
				</shiro:hasPermission>
				
				
			</span>
			
			<span style="float:right">
				<button class="btn btn-primary btn-small" type="submit">查询</button>&nbsp;
				<button class="btn btn-small clear" type="button" >清空</button>&nbsp;	
			</span>
		
		</div>
	</form>
	
</div>

<table id="${param.rel }_datagrid"   toolbar="#${param.rel}_toolbar"  ></table>


<script type="text/javascript" >
<!--	

	$(function() {
		
		$('#<%=request.getParameter("rel")%>_datagrid').datagrid({
			url : "attendance/attendanceQuery.do",
			frozenColumns:[[
				{
					field:"ck",
					title : "勾选",
					checkbox:true
				},
				 {
					field : "signuser",
					title : "姓名",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return '<a href="attendance/showSign.do?id='+row.id+'" target="dialog" width="1000" height="450" title="登记详情" rel="<%=request.getParameter("rel")%>_show">'+value+'</a>';
					}
				}
			]],
			columns : [ [ 
			             
			 	 
			 	 {
					field : "signdate",
					title : "登记时间",
					align:"center",
					width : 200,
					formatter: function(value,row,index){
						return row.signdate+" "+row.signtime;
					}
				},
			 	 {
					field : "islate",
					title : "登记状态",
					align:"center",
					width : 80,
					formatter: function(value,row,index){
						if(value==0){
							return "正常";
							}else if(value==1){
							return "迟到";
						}else if(value==2){
							return "早退";
						}else if(value==3){
							return "请假";
						}else if(value==4){
							return "旷工";
						}else if(value==5){
							return "外出";
						}else if(value==6){
							return "其他";
						}
					}
					
				},
			 	 {
					field : "attType",
					title : "考勤类型",
					align:"center",
					width : 80,
					formatter: function(value,row,index){
						if(value==1){
							return "第一次上班";
							}else if(value==2){
							return "第一次下班";
						}else if(value==3){
							return "第二次上班";
						}else if(value==4){
							return "第二次下班";
						}else if(value==5){
							return "第三次上班";
						}else if(value==6){
							return "第三次下班";
						}
						
						
						
					}
					
				} ,
				{
					field : "creattime",
					title : "操作时间",
					align:"center",
					width : 150,
					sortable : true,
					formatter: function(value,row,index){
						
						return new Date(value).format("yyyy-MM-dd HH:mm:ss");
					}
				}
			
			] ],
			onDblClickRow:function(rowIndex, rowData){
				MUI.openDialog('修改登记记录','attendance/updateSignPage.do?id='+rowData.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
			}
		});
		
	});

//-->		
</script>

