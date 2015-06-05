<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公--个人设置 -- 登录信息
--%>

<div style="width: 98%;margin: 0 auto;" >	
	<div style="margin-top: 10px">
		<span class="label label-warning">
			说明：
		</span>
		<span class="alert">您的账号正在以下设备登陆，可远程进行管理。</span>&nbsp;&nbsp;
		<span class="label label-important">
			注意：
		</span>
		<span class="alert alert-error">如果不是您本人登陆，请及时修改密码。</span>
	</div>										
	<table class="table table-bordered center" >
		
		<thead>
			<tr>
				<th width="10" >序号</th>
				<th width="120"  >登录时间</th>
				<th width="140"  >登录ip</th>
				<th width="200" >登录地点</th>
				<th width="80"  >登录方式</th>
				<th width="100" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="m" items="${requestScope.loginInfos }"  varStatus="status">
			<tr>
				<td style="text-align: center;">${status.count }</td>
				<td style="text-align: center;"><fmt:formatDate value="${m.loginTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				
				<td style="text-align: center;">
					${m.ipInfo.ip }
				</td>
			
				<td style="text-align: center;">
					${m.ipInfo.country }&nbsp;
					${m.ipInfo.region }&nbsp;
					${m.ipInfo.city }&nbsp;
					${m.ipInfo.isp }
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${m.loginType==1 }">
							web (网页)
						</c:when>
						<c:otherwise>Android (手机客户端)</c:otherwise>
					</c:choose>
				</td>
			
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${requestScope.nowId==m.id }">
							<span class="alert alert-success">当前登陆</span>
						</c:when>
						<c:otherwise>
							<shiro:hasPermission name="user:logoutOuther">
							
						
								<a class="btn btn-danger btn-small" target="ajaxTodo"  href="user/my/loginInfo/logout.do?id=${m.id }&otherBoxId=${param.rel}&otherCallback=refresh"  title="确定要注销此处登录吗?"><i class="boot_icon-off boot_icon-white"></i> 下线</a>
						
							</shiro:hasPermission>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>	
	</table>
			
</div>
										
				