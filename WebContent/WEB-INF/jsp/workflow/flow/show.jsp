<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--流程设计--流程详情
--%>
<div style="width: 98%;margin: 0 auto;">
	
		<fieldset class="fieldset">
				<legend>基本信息</legend>
				<table class="table table-bordered "  style="margin-top: 10px">
					<tr>
						<th style="width: 80px">流程类型：</th>
						<td><my:outSelectValue id="${requestScope.wf.flowType }"/></td>
					</tr>
					<tr>
						<th>流程名称：</th>
						<td><c:out value="${requestScope.wf.flowName }"/></td>
					</tr>
					<tr>
						<th>流程描述：</th>
						<td>
							<c:out value="${requestScope.wf.flowDesc }"/>
						</td>
					</tr>
					<tr>
						<th>创建人：</th>
						<td>
							<my:outTrueName id="${requestScope.wf.createUserId }"/>
						</td>
					</tr>
					<tr>
						<th>创建时间：</th>
						<td>
							<fmt:formatDate value="${requestScope.wf.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
					<tr>
						<th>发布状态：</th>
						<td>
							<c:choose>
							<c:when test="${requestScope.wf.releaseState==0 }">
								未发布
							</c:when>
							<c:when test="${requestScope.wf.releaseState==1 }">
								已发布
							</c:when>
							<c:when test="${requestScope.wf.releaseState==2 }">
								有更新
							</c:when>
							
						</c:choose>
						</td>
					</tr>
					<tr>
						<th>版本数量：</th>
						<td>
							<c:out value="${requestScope.wf.versionNum }"/>
						</td>
					</tr>
					<tr>
						<th>使用状态：</th>
						<td>
							<c:choose>
								<c:when test="${requestScope.wf.isEnable==1 }">
									可用
								</c:when>
								<c:otherwise>
									禁用
								</c:otherwise>
							
						</c:choose>
						</td>
					</tr>
				</table>	
		</fieldset>
		<fieldset class="fieldset">
				<legend>最后修改信息</legend>
				<table class="table table-bordered "  style="margin-top: 10px">
					<tr>
						<th style="width: 80px">修改人：</th>
						<td>
							<my:outTrueName id="${requestScope.wf.updateUserId }"/>
						</td>
					</tr>
					<tr>
						<th>修改时间：</th>
						<td>
							<fmt:formatDate value="${requestScope.wf.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
					<tr>
						<th>修改说明：</th>
						<td>
							<c:out value="${requestScope.wf.updateDesc }"/>
						</td>
					</tr>
				</table>
				
		</fieldset>
		

</div>