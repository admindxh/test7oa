<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--
	模块：工作流程--查看流程实例详情
--%>
<html>
	<head>
		<base href="<%=basePath%>"/>
		<link href="favicon.ico" rel="SHORTCUT ICON" />
		<link href="resource/css/font-size-14/font-size.css" rel="stylesheet" type="text/css" media="screen"/>
		<style type="text/css">
			body{
				background-color: white;
			}
			.pageContent{
				width: 1000px;
				margin: 10px auto;
			}
			.div_sendUser{
				float:right;
				margin-right: 20px;
				color: #666666
			}
			/* 流程表单样式   */
		table.flowtable{
			width: 500px;
			border-collapse:collapse;
			margin: 10px auto 10px;
			border: 1px solid black;
			height: auto;
		}
		table.flowtable caption{
			font-size: 20px;
			font-weight: bold;
			padding: 10px;
		}
		table.flowtable th{
			border: 1px solid black;
			padding: 10px;
			font-weight: bold;
			text-align:center;
		}
		table.flowtable td{
			border: 1px solid black;
			padding: 10px;
		}
		
		table.flowtable textarea{
			width: 90%;
			height: 30px;
		}
		table.flowtable input{
			width: 90%;
		}

			
		</style>
	
		<script src="resource/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
		<!-- 流程表单插件 -->
		<script src="resource/js/flowForm_plugin.js" type="text/javascript"></script>
		
	</head>

<body>
<div class="pageContent" style="padding:1px" >
		<div style="height: 20px;padding: 5px 5px 2px 10px;">
			<%--<label >工作信息<input type="checkbox" value="flow_gzxx" checked="checked" onclick="changeShowInfo(this)" style="vertical-align: middle;"/></label>
			<label >流程信息<input type="checkbox" value="flow_lcxx" checked="checked" onclick="changeShowInfo(this)" style="vertical-align: middle;"/></label>
			<label >工作表单<input type="checkbox" value="flow_gzbd" checked="checked" onclick="changeShowInfo(this)" style="vertical-align: middle;"/></label>
			<label >任务表单<input type="checkbox" value="flow_rwbd" checked="checked" onclick="changeShowInfo(this)" style="vertical-align: middle;"/></label>
			<label >流程进度<input type="checkbox" value="flow_lcjd" checked="checked" onclick="changeShowInfo(this)" style="vertical-align: middle;"/></label>
			
			--%><%--<c:choose>
				流程结束时间为空，说明流程还未结束 
				<c:when test="${requestScope.hpi.endTime==null }">
					如果流程没有结束则显示高亮当前任务节点的流程图 
						
						<span style="float: right;color: green;font-weight: bold;margin-right: 5px;">运行中</span>		
					
						
				</c:when>
				<c:otherwise>
					<span style="float: right;color: green;font-weight: bold;margin-right: 5px;">已完成</span>		
					
					
				</c:otherwise>
			</c:choose>
			
		--%></div>
		<div class="divider" ></div>
		<div layoutH="50">
			<!-- 工作信息 -->
			<%--<fieldset id="flow_gzxx">
				<legend>工作信息</legend>
				
				<table class="flowtable" style="width: 500px;margin-top: 0px">
					<caption><c:out value="${requestScope.hpi.processVariables.workTitle }" /></caption>
					<tr>
						<th style="width: 60px;">创建人</th>
						<td><my:outTrueName id="${requestScope.hpi.startUserId }"/></td>
					</tr>
					<tr>
						<th>部门</th>
						<td><my:outUserDeptName id="${requestScope.hpi.startUserId }"/></td>
					</tr>
					<tr>
						<th>创建时间</th>
						<td><fmt:formatDate value="${requestScope.hpi.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					
					<c:choose>
						<c:when test="${requestScope.hpi.endTime==null }">
							<tr>
								<th>状态</th>
								<td>运行中</td>
							</tr>
						</c:when>
						
						<c:otherwise>
							<tr>
								<th>状态</th>
								<td>已结束</td>
							</tr>
							<tr>
								<th>结束时间</th>
								<td><fmt:formatDate value="${requestScope.hpi.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<tr>
								<th>用时</th>
								<td><my:msFormat milliSecond="${requestScope.hpi.durationInMillis }"/></td>
							</tr>
							<c:if test="${requestScope.hpi.endActivityId==null }">
								<tr>
									<th>备注</th>
									<td>未完成，被删除，删除原因：<c:out value="${requestScope.hpi.deleteReason }" /></td>
								</tr>
							</c:if>
						</c:otherwise>
					
					</c:choose>
						
					
				
				</table>
			</fieldset>
			<!-- 流程信息 -->
			<fieldset id="flow_lcxx">
				<legend>流程信息</legend>
				<table class="flowtable" style="width: 500px;margin-top: 0px">
					<tr>
						<th style="width: 60px;">名称</th>
						<td><c:out value="${requestScope.pd.name }" /></td>
					</tr>
					<tr>
						<th>类型</th>
						<td><my:outSelectValue id="${requestScope.pd.category }"/></td>
					</tr>
					<tr>
						<th>版本</th>
						<td><c:out value="${requestScope.pd.version }" /></td>
					</tr>
					<tr>
						<th>描述</th>
						<td><c:out value="${requestScope.pd.description }" /></td>
					</tr>
				</table>
					
			</fieldset>
			
			--%><!-- 工作表单 -->
			<%--<fieldset id="flow_gzbd" >
				<legend>工作表单</legend>
				<div flowForm="flow"  formType="show">
					<my:scriptEscape value="${requestScope.flowFormHtml }"/>
				</div>
			</fieldset>
			
		
			--%>
			<div flowForm="flow"  formType="print">
					<my:scriptEscape value="${requestScope.flowFormHtml }"/>
			</div>
			
			
			<!-- 任务表单 -->
			
			<div id="flow_rwbd"  style="border: solid 1px #CCC;padding: 2px;margin-top: 10px;">
				<c:forEach var="m" items="${requestScope.hti }" varStatus="status">
					<c:if test="${m.endTime!=null}">
						<div style="margin-top:2px;padding-top:10px;padding-left:10px;height:20px;border: solid 1px #CCC ">
							<span style="font-weight: bold;">第${status.count }步【<c:out value="${m.name }" />】<my:outTrueName id="${m.assignee }"/>(<my:outUserDeptName id="${m.assignee }"/>)：</span>
							<div class="div_sendUser" "><fmt:formatDate value="${m.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
						</div>
						<c:if test="${requestScope.taskFormHtml[m.id]!=null }">	
							<div flowForm="task"  formType="show" style="padding-top: 2px;border: solid 1px #CCC;border-top: none;" >
								<my:scriptEscape value="${requestScope.taskFormHtml[m.id] }"/>
							</div>
						</c:if>
					</c:if>
				</c:forEach>
				
			</div>
			
			
			<!-- 流程进度 -->
			<%--<fieldset id="flow_lcjd" style="margin-top: 10px;">
				<legend>工作进度</legend>
				 
				 	<table  class="list" width="100%">
				 		
						<thead style="font-weight: bold;">
							<tr>
								<th >任务名称</th>
								<th width="170">执行人</th>
								<th width="200">任务分配状态</th>
								<th width="150">完成时间</th>
								<th width="150">用时</th>
								<th width="150">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 30px;">
								<td colspan="6">
									<span style="font-weight: bold;color: green;">***流程开始***</span>
								</td>
							</tr>
							<c:forEach var="m" items="${requestScope.hti }" varStatus="status">
								<tr style="height: 30px;">
									<td><c:out value="${m.name }" /></td>
									
										<c:choose>
											<c:when test="${m.assignee!=null }">
												<td title="<my:outUserDeptName id="${m.assignee }"/>">
													<my:outTrueName id="${m.assignee }"/>
												</td>
											</c:when>
											<c:otherwise>
												<td>	
													<span style="font-weight: bold;color: red;">等待签收中</span>
												</td>
											</c:otherwise>
										</c:choose>	
									
									
										<c:choose>
											<c:when test="${m.claimTime==null&&m.owner==null }">
												<td>系统分配</td>
											</c:when>
											<c:when test="${m.owner!=null }">
												<td title="<my:outUserDeptName id="${m.owner }"/>">委托人：<my:outTrueName id="${m.owner }"/></td>
											</c:when>
											<c:otherwise>
												<td>自己签收:<fmt:formatDate value="${m.claimTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											</c:otherwise>
										</c:choose>
									
									
									
									<c:choose>
										<c:when test="${m.endTime==null }">
											<td>
												<span style="font-weight: bold;color: green;">正在处理中</span>
											</td>
											<td></td>
										</c:when>
										<c:otherwise>
											<td>
												<fmt:formatDate value="${m.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td><my:msFormat milliSecond="${m.durationInMillis }"/></td>
										</c:otherwise>
									</c:choose>
										
									
									
									
									<td>
										<c:if test="${requestScope.taskFormHtml[m.id] !=null }">
											<a href="javascript:;" style="color: blue;" onclick="changeformShowState(this)">显示表单</a>
										</c:if>
									</td>
								</tr>
								<c:if test="${requestScope.taskFormHtml[m.id] !=null }">
									<tr style="display: none;">
										<td colspan="6">
											<div class="divider"></div>
											<div style="width: auto;height: auto;margin: 5px;">
												<my:scriptEscape value="${requestScope.taskFormHtml[m.id] }"/>
										
											</div>
											<div class="divider"></div>
										</td>
									</tr>
								</c:if>
							</c:forEach>
							
							<tr style="height: 30px;">
								
								<c:if test="${requestScope.hpi.endTime!=null }">
									<c:choose>
											<c:when test="${requestScope.hpi.endActivityId==null }">
												<td >
													<span style="font-weight: bold;color: red;">***流程结束(中途被删除)***</span> 
												</td>
												<td colspan="5">
													<span style="font-weight: bold;">原因:</span>
													<span><c:out value="${requestScope.hpi.deleteReason }" /></span>
												</td>
												
											</c:when>
											<c:otherwise>
												<td colspan="6">
													<span style="font-weight: bold;color: green;">***流程结束***</span>
												</td>
											</c:otherwise>
									</c:choose>
								</c:if>
								
							</tr>
						</tbody>
					</table>
			</fieldset>		
			
							
	--%></div>
	
</div>
</body>


<script type="text/javascript">
<!--
	
	//流程表单插件
	if ($.fn.initFlowForm) $("div[flowForm]").initFlowForm();
	
	//切换内容区域显示
	function changeShowInfo(input){
		var $this=$(input);
		var $div=$this.closest(".pageContent");
		if($this.attr("checked")){
			$("#"+$this.val()).show();
		}else{
			$("#"+$this.val()).hide();
		}
	}

	//切换显示隐藏表单
	function changeformShowState(a){
		var $tr=$(a).closest("tr").next();
		if($tr.css("display")=="none"){
			$tr.show();
			$(a).text("隐藏表单");
		}else{
			$tr.hide();
			$(a).text("显示表单");
		}
	
	}
	//调用打印
	window.print();
	
//-->
</script>


</html>

