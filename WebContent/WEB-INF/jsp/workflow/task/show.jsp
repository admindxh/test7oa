<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--任务详情
--%>

<div style="width: 98%;margin: 0 auto;">
	
		<fieldset class="fieldset">
			<legend>基本信息</legend>	
			<table class="table table-bordered "  style="margin-top: 10px">
				<tr>
					<th style="width: 80px">任务名称：</th>
					<td>${requestScope.task.name }</td>
				</tr>
				
				<tr>
					<th>任务描述：</th>
					<td>
						<c:out value="${requestScope.task.description  }"/>
					</td>
				</tr>
				
				<%--<tr>
					<th>优先级：</th>
					<td>
						<c:out value="${requestScope.task.priority  }"/>
					</td>
				</tr>
				--%>
				<c:if test="${requestScope.task.formKey!=null }">
					<tr>
						<th>表单预览：</th>
						<td>
							<a class="btn btn-primary" style="margin-left: 10px;"  href="form/show.do?id=${requestScope.task.formKey}"  target="dialog" width="800" height="400" rel="${param.rel }_taskform" title="${requestScope.task.name }__任务表单">任务表单模板</a>
						</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<fieldset class="fieldset">
			<legend>执行信息</legend>		
			<table class="table table-bordered "  style="margin-top: 10px">	
				<tr>
					<th style="width: 80px">开始时间：</th>
					<td>
						<fmt:formatDate value="${requestScope.task.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<c:if test="${requestScope.task.dueDate !=null }">
				<tr>
					<th>任务期限：</th>
					<td>
						<fmt:formatDate value="${requestScope.task.dueDate }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				</c:if>
				<c:if test="${requestScope.task.claimTime !=null }">
					<tr>
						<th>领取时间：</th>
						<td>
							<fmt:formatDate value="${requestScope.task.claimTime }" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.task.owner !=null }">
					<tr>
						<th>委托人：</th>
						<td>
							<my:outTrueName id="${requestScope.task.owner }"/>
							
						</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.task.assignee !=null }">
					<tr>
						<th>执行人：</th>
						<td>
							<my:outTrueName id="${requestScope.task.assignee }"/>
							
						</td>
					</tr>
				</c:if>
				<c:if test="${requestScope.task.endTime !=null }">
				<tr>
					<th>完成时间：</th>
					<td>
						<fmt:formatDate value="${requestScope.task.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
						
					</td>
				</tr>
				
				
				<tr>
					<th>用时：</th>
					<td>
						<my:msFormat milliSecond="${requestScope.task.durationInMillis  }"/>
					</td>
				</tr>
				</c:if>
			 </table>
		</fieldset>
		
		

	
</div>
	
		
	
