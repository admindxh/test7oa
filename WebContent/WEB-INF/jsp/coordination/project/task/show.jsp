<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理 -- 任务详情
--%>
<div style="width: 98%;margin: 0 auto ;" >
		
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">任务名称：</th>
				<td>
					<c:out value="${requestScope.t.name }" />
					
				</td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.t.startTime }" pattern="yyyy-MM-dd HH:mm" />
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.t.endTime }" pattern="yyyy-MM-dd HH:mm" />
				</td>
			</tr>
			<tr>
				<th>进度：</th>
				<td>
					${requestScope.t.speed }%
				</td>
			</tr>
			
			<tr>
				<th>执行人：</th>
				<td>
					<my:outTrueName id="${requestScope.userIds }"/>
				</td>
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					<my:scriptEscape value="${requestScope.t.content}"/>
				</td>
			</tr>
			<tr>
				<th>创建人：</th>
				<td>
					
					<my:outTrueName id="${requestScope.t.createUserId }"/>
			
				</td>
			</tr>
			<tr>
				<th>创建时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.t.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			
			
	</table>
	
</div>	






										
						
					
		

	

