<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--新建工作，查询流程
--%>

<div style="width: 98%;margin: 0 auto;">
	
	<table class="table table-bordered table-striped center"  >
		<thead>
			<tr>
				<th width="28" align="center">行号</th>
				<th width="350" align="center">流程名称</th>
				<th align="center">流程描述</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${requestScope.flows }" varStatus="status">
				
				<tr>
		
					<td style="text-align: center;">${status.count }</td>
					<td style="text-align: center;">
						<a  href="work/addPage.do?id=${m.id }&key=${m.flowId}"  target="dialog"  width="1000"  height="450" rel="${param.rel }_newwork" title="新建工作_<c:out value="${m.flowName }" />">
							<c:out value="${m.flowName }" />
						</a>
					</td>
					<td style="text-align: center;">
						<c:out value="${m.flowDesc }"></c:out>
					</td>
					
					
				</tr>
				
			</c:forEach>
			
		</tbody>
	</table>
	
</div>
