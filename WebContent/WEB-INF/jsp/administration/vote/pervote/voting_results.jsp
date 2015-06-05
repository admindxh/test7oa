<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--投票统计结果
--%>
<div style="width: 850px;margin: 0 auto;">
	<div style="text-align: center; ">
		<h3>${vo.tpTitle}</h3>
	</div>
	
	<c:forEach var="m" items="${requestScope.bl }" varStatus="status">
		
			<table class="table ">	
				<tbody>
					<tr>
						<td colspan="4"><h4>${status.count }、<c:out value="${m.question.title }"/></h4></td>
					</tr>
					<c:if test="${m.question.type!=2 }">
					<c:forEach var="t" items="${m.list }" varStatus="status1">
						<tr>
							<td style="width: 10px"></td>
							<td style="width: 300px">${status1.count }、<c:out value="${t.detail.optiondesc }"/></td>
							<td>
								<div class="progress progress-danger" style="margin-bottom: 0;width: 98%">
								 	<div class="bar" style="width: ${t.percentage}%"></div>
								</div>
									
							</td>
							<td style="width: 120px">${t.tote}票 (${t.percentage}%)</td>
							
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${m.question.type==2 }">
						<tr>
							<td style="width: 10px"></td>
							<td colspan="3">
								<ol >
									<c:forEach var="wb" items="${m.str }" >
										<li>
											<div class="well well-small">
												${wb }
											</div>
										</li>
									</c:forEach>
								
								</ol>
							</td>
						</tr>
					</c:if>
				</tbody>
			
			</table>
		
	
	</c:forEach>
	
	
</div>