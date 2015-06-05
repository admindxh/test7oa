<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理 -- 任务详情
--%>
<div style="width: 98%;margin: 0 auto ;" >
		
	<table class="table table-bordered ">
	
			<tr>
				<th style="width: 80px">开始时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.j.startTime }" pattern="yyyy-MM-dd HH:mm" />
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.j.endTime }" pattern="yyyy-MM-dd HH:mm" />
				</td>
			</tr>
			<tr>
				<th>进度说明：</th>
				<td>
					<my:scriptEscape value="${requestScope.j.content}"/>
				</td>
			</tr>
			<tr>
				<th>进度日志：</th>
				<td>
					<c:out value="${requestScope.j.journal }" />
					
				</td>
			</tr>
			<tr>
				<th>操作人：</th>
				<td>
					
					<my:outTrueName id="${requestScope.j.createUserId }"/>
			
				</td>
			</tr>
			<tr>
				<th>操作时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.j.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			
			
	</table>
	
</div>	






										
						
					
		

	

