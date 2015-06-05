<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--用户管理 --单个用户详情
--%>
				
<div style="width: 600px;margin: 0 auto;" >				
		
		<table class="table table-bordered" >			
	
			<tr>
				<th style="width: 120px">账号：</th>
				<td><c:out value="${requestScope.u.userName }"/></td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td><c:out value="${requestScope.u.trueName }"/></td>
			</tr>
			<tr>
				<th>性别：</th>
				<td>
					<c:choose><c:when test="${requestScope.u.userSex==1}">男</c:when><c:otherwise>女</c:otherwise></c:choose>
				</td>
			</tr>
			<tr>
				<th>手机号：</th>
				<td>
					${requestScope.u.mobilePhoneNumber }
				</td>
			</tr>
			<tr>
				<th>部门：</th>
				<td>
					<my:outDeptName id="${requestScope.u.deptId }"/>
				</td>
			</tr>
			
			<tr>
				<th>备注：</th>
				<td>
					<c:out value="${requestScope.u.userDesc }"/>
				</td>
			</tr>
			<tr>
				<th>允许登录系统：</th>
				<td>
					<c:choose><c:when test="${requestScope.u.userStatus==1}">正常</c:when><c:otherwise>禁用</c:otherwise></c:choose>
				</td>
			</tr>
		
		
		
		</table>
	
	

</div>	
					
		

	

