<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%@ taglib uri="/WEB-INF/tag/myTag.tld" prefix="my" %>
<%--
	模块：行政办公--投票管理  查看投票人
--%>
<div style="width: 900px;margin: 0 auto;">
	<table class="table table-bordered  center "   >
		<thead>
			<th width="23" align="center">行号</th>
			<th width="40" align="center">部门名称</th>
			<th width="40" align="center">投票人</th>
			<th width="40" align="center">投票时间</th>
		</thead>
		<tbody>
			<c:forEach var="m" items="${list}" varStatus="status">
				<tr>
					<td align="center">${status.count }</td>
					<td align="center"><my:outDeptName id="${m.deptname}"/></td>
					<td align="center"><my:outTrueName id="${pageScope.m.username}"/></td>
					<td align="center"><fmt:formatDate value="${m.d}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>


</div>