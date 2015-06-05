<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：工作流程--流程设置--用户任务人员配置
--%>
<div style="width: 98%;margin: 0 auto;">
	<table class="table table-bordered center"  >
			<thead>
				<tr>
					<th width="26px" >序号</th>
					<th width="200px">任务名称</th>
					<th width="250px">任务描述</th>
					<th width="110px" >任务表单</th>
					<th>人员分配</th>
				</tr>
			</thead>	
			<c:forEach var="m" items="${requestScope.ut }" varStatus="status">
				<tr>
					<td style="text-align: center;">${status.count }</td>
					<td>${m.name }</td>
					<td title="${m.documentation }"><c:out value="${m.documentation }"></c:out></td>
					<td style="text-align: center;">
						<c:if test="${m.formKey!=null }">
							<a  class="btn btn-primary" href="form/show.do?id=${m.formKey }"  target="dialog" width="800" height="400" rel="${param.rel }_flowform" title="${m.name }--任务表单"><span>表单预览</span></a>
						</c:if>
					</td>
					
					<td>
						<c:choose>
							<c:when test="${m.assignee!=null }">
								执行人：${m.assignee }
							</c:when>
							<c:when test="${m.no!=null }">
								无人员分配
							</c:when>
							<c:otherwise>
								<c:if test="${m.userNames!=null }">
									<div >
										待选人员：${m.userNames}
									</div>
								</c:if>
								<c:if test="${m.roleNames!=null }">
									<div style="margin-top: 10px;">
										待选角色：${m.roleNames}
									</div>
								</c:if>
										
								<c:if test="${m.userIds!=null }">
									<div >待选人员：<my:outTrueName id="${m.userIds }"/></div>
								</c:if>	
								<c:if test="${m.roleIdsLst!=null }">			
									<div style="margin-top: 10px;">待选角色(点击角色配置人员)：
								
										<c:forEach var="id" items="${m.roleIdsLst }" varStatus="status">
									
											<a href="role/roleUsers/load.do?roleId=${id}&rel=${param.rel}_role_user" target="navTab"  rel="${param.rel}_role_user" title="角色管理--分配管理"><my:outRoleName id="${id }"/></a> |
										
										</c:forEach>
											
										
									</div>
								</c:if>
								
							</c:otherwise>
							
							
						</c:choose>
							
						
						
					</td>
				</tr>
			</c:forEach>
		</table>
	
</div>