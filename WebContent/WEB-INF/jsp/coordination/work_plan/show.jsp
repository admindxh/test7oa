<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--计划管理 -- 查看详情
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="工作计划详情" 	>
			<div style="width: 98%;margin: 0 auto ;" >
				<div  style="margin-top: 5px">
					<shiro:hasPermission name="workPlan:update">
					<c:if test="${!empty requestScope.fz }">
						<a class="btn btn-primary" href="workPlan/updatePage.do?id=${requestScope.wp.id }"  rel="${param.rel }_addTask" target="dialog" title="添加计划任务"><i class="boot_icon-plus-sign boot_icon-white"></i> 修改计划</a>
					</c:if>	
					</shiro:hasPermission>
					<c:if test="${!empty requestScope.rw }">
						<a class="btn btn-primary" href="workPlan/task/addPage.do?id=${requestScope.wp.id }"  rel="${param.rel }_addTask" target="dialog" title="添加计划任务"><i class="boot_icon-plus-sign boot_icon-white"></i> 计划任务</a>
					</c:if>	
			
					<c:if test="${!empty requestScope.pz }">
						&nbsp;&nbsp;<a class="btn btn-primary" href="workPlan/comment/addPage.do?id=${requestScope.wp.id }"  rel="${param.rel }_addComment" target="dialog" title="添加领导批注"><i class="boot_icon-plus-sign boot_icon-white"></i> 领导批注</a>
					</c:if>	
				</div>			
				<table class="table table-bordered ">
						<tr>
							<th style="width: 80px">计划名称：</th>
							<td>
								<c:out value="${requestScope.wp.name }" />
								
							</td>
						</tr>
						<tr>
							<th>计划类型：</th>
							<td>
								<my:outSelectValue id="${requestScope.wp.type }"/>
							</td>
						</tr>
						<tr>
							<th>计划时间：</th>
							<td>
								<fmt:formatDate value="${requestScope.wp.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								--
								<fmt:formatDate value="${requestScope.wp.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>负责人：</th>
							<td>
								<my:outTrueName id="${requestScope.fzIds }"/>
							</td>
						</tr>
						<tr>
							<th>参与人：</th>
							<td>
								
								<my:outTrueName id="${requestScope.cyIds }"/>
			
							</td>
						</tr>
						<tr>
							<th>批注领导：</th>
							<td>
								<my:outTrueName id="${requestScope.headIds }"/>
							</td>
						</tr>
						<tr>
							<th>授权部门：</th>
							<td>
								
								<my:outDeptName id="${requestScope.deptIds }"/>
								
							</td>
						</tr>
						
						<tr>
							<th>授权人员：</th>
							<td>
								
								<my:outTrueName id="${requestScope.userIds }"/>
						
							</td>
						</tr>
						
						<tr>
							<th>附件：</th>
							<td>
								<c:forEach  var="f"  items="${requestScope.files }" >
						
									<c:url var="url_download" value="workPlan/download.do" >
										<c:param name="name" value="${f.name }" />
										<c:param name="uuid" value="${f.uuid}" />
										<c:param name="ext" value="${f.ext}" />
									</c:url>
									<a href="${url_download }" target="_blank">
											<img src="resource/images/icons/${f.ext }.gif" 
											onerror="javascript:this.src='resource/images/icons/page.gif'" align="top" />&nbsp;<c:out value="${f.name }"/></a>
											 (<my:fileSizeFormat value="${f.size }"/>)&nbsp;&nbsp;&nbsp;&nbsp;
											
									
									
								</c:forEach>
								
									
							</td>
							
						</tr>
						<tr>
							<th>备注：</th>
							<td>
								<c:out value="${requestScope.wp.remark }" />
							</td>
						</tr>
						<tr>
							<th>创建人：</th>
							<td>
								
								<my:outTrueName id="${requestScope.wp.createUserId }"/>
						
							</td>
						</tr>
						<tr>
							<th>创建时间：</th>
							<td>
								<fmt:formatDate value="${requestScope.wp.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>计划内容：</th>
							<td>
								<my:scriptEscape value="${requestScope.wp.content}"/>
							</td>
						</tr>
						
				</table>
				
			</div>	
			
			
			
	</div>
	<div   title="计划任务" 	>
			<div class="easyui-layout" fit="true" >
			   <!-- 左侧-->
				<div region="west" title="人员列表" split="true" 	style="width:150px;" >
				
					<ul class="nav nav-list">
					  	<li class="nav-header">负责人</li>
					  	<c:forEach  var="u"  items="${requestScope.fzLst }" >
					  		 <li><a href="javascript:;" onclick="updateWorkPlanData('${param.rel }_task_datagrid','${u }')" ><my:outTrueName id="${u }"/></a></li>
					  	
					  	</c:forEach>
						<li class="nav-header">参与人</li>
						<c:forEach  var="u"  items="${requestScope.cyLst }" >
					  		 <li><a href="javascript:;" onclick="updateWorkPlanData('${param.rel }_task_datagrid','${u }')" ><my:outTrueName id="${u }"/></a></li>
					  	
					  	</c:forEach>
							
					
					</ul>



					
				</div>
			  	
				<div  region="center" border="false">
					<%@ include file="/WEB-INF/jsp/coordination/work_plan/task/load.jsp"%>
				</div>
			   
			</div>
	</div>
	
	<div   title="领导批注" 	href="workPlan/comment/load.do?id=${requestScope.wp.id }&rel=${param.rel }_comment">
	
	</div>
	
</div>


<script type="text/javascript">
<!--
	//刷新数据表格
	function updateWorkPlanData(dataId,userId){
		
		$("#"+dataId).datagrid('load', {"userId":userId});
	}
//-->
</script>



										
						
					
		

	

