<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--查看流程实例详情
--%>
<div class="easyui-layout" fit="true" border="false">
	<div  region="north" style="width:100%;height: 35px;overflow: hidden;" border="false">
		<div  style="height: 20px;padding: 2px 5px 2px 10px; ">
			<label class="checkbox inline" ><input type="checkbox" value="flow_gzxx" checked="checked" onclick="changeShowInfo(this)" />工作信息</label>
			<label class="checkbox inline" ><input type="checkbox" value="flow_lcxx" checked="checked" onclick="changeShowInfo(this)" />流程信息</label>
			<label class="checkbox inline" ><input type="checkbox" value="flow_gzbd" checked="checked" onclick="changeShowInfo(this)" />工作表单</label>
			<label class="checkbox inline" ><input type="checkbox" value="flow_rwbd" checked="checked" onclick="changeShowInfo(this)" />任务表单</label>
			<label class="checkbox inline" ><input type="checkbox" value="flow_lcjd" checked="checked" onclick="changeShowInfo(this)" />流程进度</label>
			
			<c:choose>
				<%--流程结束时间为空，说明流程还未结束 --%>
				<c:when test="${requestScope.hpi.endTime==null }">
					<%--如果流程没有结束则显示高亮当前任务节点的流程图 --%>
						
						<span style="float: right;color: green;font-weight: bold;margin-right: 5px;">运行中</span>		
					
						<span style="float: right;margin-right: 10px;">
							<a  href="processDefinition/showFlowImgPage.do?processDefinitionId=${requestScope.hpi.processDefinitionId}&processInstanceId=${requestScope.hpi.processInstanceId}"  target="dialog" width="800" height="400" rel="${param.rel }_flowimg" title="${requestScope.work.workTitle }_流程图">
								流程图
							</a>
						</span>
					
				</c:when>
				<c:otherwise>
					<span style="float: right;color: green;font-weight: bold;margin-right: 5px;">已完成</span>		
					
					<span style="float: right;margin-right: 10px;">
						<a  href="processDefinition/showFlowImgPage.do?processDefinitionId=${requestScope.hpi.processDefinitionId}&processInstanceId=${requestScope.hpi.processInstanceId}"  target="dialog" width="800" height="400" rel="${param.rel }_flowimg" title="${requestScope.work.workTitle }_流程图">
							流程图
						</a>
					</span>
				</c:otherwise>
			</c:choose>
			<span style="float: right;margin-right: 10px;">
				<a  href="work/print.do?processInstanceId=${requestScope.hpi.processInstanceId}"  target="_blank" >
					打印
				</a>
			</span>
		</div>
	
		<div class="divider" ></div>
	</div>
	<div  region="center"   border="false" style="padding:2px" >
		<!-- 工作信息 -->
		<div id="flow_gzxx" class="easyui-panel" title="工作信息" collapsible="true" >
			
				<table class="flowtable" style="width: 500px;margin-top: 0px">
					<caption><c:out value="${requestScope.work.workTitle }" /></caption>
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
			
			</div>
			<!-- 流程信息 -->
			<div id="flow_lcxx"  class="easyui-panel" title="流程信息" collapsible="true" style="margin-top: 2px">
			
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
					
			</div>
			
			<!-- 工作表单 -->
			<div id="flow_gzbd"  class="easyui-panel" title="工作表单" collapsible="true" >
				<div flowForm="flow"  formType="show">
					<my:scriptEscape value="${requestScope.work.formHtml }"/>
				</div>
			</div>
			<!-- 工作附件 -->
			<c:if test="${!empty requestScope.workFiles }">
			<div id="flow_gz_files"  class="easyui-panel" title="工作附件" collapsible="true" >
			
				<div style="margin: 5px;">
					<c:forEach var="wf" items="${requestScope.workFiles }" varStatus="status">
						<c:url var="url_download" value="work/download.do" >
							<c:param name="newName" value="${wf.fileNewName }" />
							<c:param name="name" value="${wf.fileName}" />
							<c:param name="ext" value="${wf.fileExt}" />
						</c:url>
						
						<span style="margin-left: 10px;">
							
							<a href="${url_download }" target="_blank">
									<img src="resource/images/icons/${wf.fileExt }.gif" 
									onerror="javascript:this.src='resource/images/icons/page.gif'"  style="vertical-align: middle;" />&nbsp;<c:out value="${wf.fileName }"/></a>
									(<my:fileSizeFormat value="${wf.fileSize }"/>)
			
						</span>
							
							
					
					</c:forEach>
				</div>
			</div>
			</c:if>
			<!-- 任务表单 -->
			
			<div id="flow_rwbd" class="easyui-panel" title="任务表单" collapsible="true" >
				<c:forEach var="m" items="${requestScope.hti }" varStatus="status">
					<c:if test="${m.deleteReason=='completed'}">
						<div style="margin-top:2px;padding-top:10px;padding-left:10px;height:20px;border: solid 1px #CCC ">
							<span style="font-weight: bold;">第${status.count }步【<c:out value="${m.name }" />】<my:outTrueName id="${m.assignee }"/>(<my:outUserDeptName id="${m.assignee }"/>)：</span>
							<div class="div_sendUser" "><fmt:formatDate value="${m.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
						</div>
						<c:if test="${requestScope.taskFormHtml[m.id]!=null }">	
							<div flowForm="task"  formType="show" style="padding-top: 2px;border: solid 1px #CCC;border-top: none;" >
								<my:scriptEscape value="${requestScope.taskFormHtml[m.id] }"/>
							</div>
						</c:if>
						
						
						<c:if test="${!empty requestScope.taskFiles }">
							<fieldset id="flow_rw_files" class="fieldset" style="margin-top: 5px;">
								<legend>附件</legend>
								<div style="margin: 5px;">
									<c:forEach var="tf" items="${requestScope.taskFiles[m.id] }" varStatus="status">
											<c:url var="url_download" value="task/download.do" >
												<c:param name="newName" value="${tf.fileNewName }" />
												<c:param name="name" value="${tf.fileName}" />
												<c:param name="ext" value="${tf.fileExt}" />
											</c:url>
											
											<span style="margin-left: 10px;">
												
												<a href="${url_download }" target="_blank">
														<img src="resource/images/icons/${tf.fileExt }.gif" 
														onerror="javascript:this.src='resource/images/icons/page.gif'"  style="vertical-align: middle;" />&nbsp;<c:out value="${tf.fileName }"/></a>
														(<my:fileSizeFormat value="${tf.fileSize }"/>)
								
											</span>
									</c:forEach>
								</div>
							</fieldset>
						</c:if>
						
					</c:if>
				</c:forEach>
				
			</div>
			
			
			<!-- 流程进度 -->
			<div id="flow_lcjd"  class="easyui-panel" title="工作进度" collapsible="true" style="padding-bottom: 10px">
			
				 	<table  class="table table-bordered center" style="width:99%;margin: 5px auto;">
				 		
						<thead style="font-weight: bold;">
							<tr>
								<th width="270">任务名称</th>
								<th width="270">执行人</th>
								<th width="200">任务分配状态</th>
								<th width="150">结束时间</th>
								<th width="150">用时</th>
								<th width="80">状态</th>
								<th width="80">表单</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 30px;">
								<td colspan="7">
									<span style="font-weight: bold;color: green;">***流程开始***</span>
								</td>
							</tr>
							<c:forEach var="m" items="${requestScope.hti }" varStatus="status">
								<tr style="height: 30px;">
									<td title="<c:out value="${m.name }" />"><c:out value="${m.name }" /></td>
									
										<c:choose>
											<c:when test="${m.assignee!=null }">
												<td title="<my:outUserDeptName id="${m.assignee }"/>：<my:outTrueName id="${m.assignee }"/>">
													<my:outUserDeptName id="${m.assignee }"/>：<my:outTrueName id="${m.assignee }"/>
												</td>
											</c:when>
											<c:otherwise>
												<td>	
													<span style="font-weight: bold;color: red;">等待签收</span>
												</td>
											</c:otherwise>
										</c:choose>	
									
									
										<c:choose>
											<c:when test="${m.claimTime==null&&empty m.owner }">
												<td>分配:<fmt:formatDate value="${m.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											</c:when>
											<c:when test="${!empty m.owner }">
												<td title="<my:outUserDeptName id="${m.owner }"/>">委托人：<my:outTrueName id="${m.owner }"/></td>
											</c:when>
											<c:otherwise>
												<td>签收:<fmt:formatDate value="${m.claimTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											</c:otherwise>
										</c:choose>
									
									
									
									
									<td>
										<fmt:formatDate value="${m.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><my:msFormat milliSecond="${m.durationInMillis }"/></td>
										
									
									<td>
									<c:choose>
											<c:when test="${m.deleteReason==null }">
												
												<span style="font-weight: bold;color: #DEA600;">处理中</span>
							
											</c:when>
											<c:when test="${m.deleteReason=='completed' }">
												<span style="font-weight: bold;color: green;">完成</span>
											</c:when>
											<c:when test="${m.deleteReason=='deleted' }">
												<span style="font-weight: bold;color: red;">删除</span>
											</c:when>
											

									</c:choose>
									</td>
									
									
									
									
									<td>
										<c:if test="${requestScope.taskFormHtml[m.id] !=null }">
											<a href="javascript:;" style="color: blue;" onclick="changeformShowState(this)">显示表单</a>
										</c:if>
									</td>
								</tr>
								<c:if test="${requestScope.taskFormHtml[m.id] !=null }">
									<tr style="display: none;">
										<td colspan="7">
											<div class="divider"></div>
											<div flowForm="task"  formType="show"  >
												<my:scriptEscape value="${requestScope.taskFormHtml[m.id] }"/>
										
											</div>
											<div class="divider"></div>
										</td>
									</tr>
								</c:if>
							</c:forEach>
							<c:if test="${requestScope.hpi.endTime!=null }">
							<tr style="height: 30px;">
								
								
									<c:choose>
											<c:when test="${requestScope.hpi.endActivityId==null }">
												<td >
													<span style="font-weight: bold;color: red;">***流程结束(中途被删除)***</span> 
												</td>
												<td colspan="6">
													<span style="font-weight: bold;">原因:</span>
													<span><c:out value="${requestScope.hpi.deleteReason }" /></span>
												</td>
												
											</c:when>
											<c:otherwise>
												<td colspan="7">
													<span style="font-weight: bold;color: green;">***流程结束***</span>
												</td>
											</c:otherwise>
									</c:choose>
							</tr>
							</c:if>
						</tbody>
					</table>
			</div>		
			
			
		
	</div>

</div>




<script type="text/javascript">
<!--
	//切换内容区域显示
	function changeShowInfo(input){
		var $this=$(input);
		var $div=$this.closest(".easyui-layout");
		if($this.prop("checked")){
		
			$("#"+$this.val(),$div).panel("open");
		}else{
			
			$("#"+$this.val(),$div).panel("close");
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
		
//-->
</script>
