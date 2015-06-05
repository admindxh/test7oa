<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公--个人设置 -- 个人信息
--%>

<div style="width: 800px;margin: 0 auto;" >	
										
	<table class="table table-bordered" >
			
			<tr>
				<th style="width: 120px">账号：</th>
				<td>
					${requestScope.user.userName }
				</td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td>
					${requestScope.user.trueName }
				</td>
			</tr>
			<tr>
				<th>性别：</th>
				<td>
					<c:choose>
						<c:when test="${requestScope.user.userSex==1 }">男</c:when>
						<c:otherwise>女</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>部门：</th>
				<td>
					<my:outDeptName id="${requestScope.user.deptId }"/>
				</td>
			</tr>
			<tr>
				<th>角色：</th>
				<td>
					<my:outRoleName id="${requestScope.roleIds }"/>
				</td>
			</tr>
			
			
	</table>
	<table class="table table-bordered" >
			
			<tr>
				<th style="width: 120px">最后登陆时间：</th>
				<td>
					<fmt:formatDate value="${requestScope.user.lastLoginTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<th>最后登陆Ip：</th>
				<td>
					${requestScope.user.lastLoginIp }
				</td>
			</tr>
			<tr>
				<th>最后登陆地点：</th>
				<td>
					${requestScope.ipInfo.country }&nbsp;
					${requestScope.ipInfo.region }&nbsp;
					${requestScope.ipInfo.city }&nbsp;
					${requestScope.ipInfo.isp }
				</td>
			</tr>
			
	</table>
			
</div>
										
				