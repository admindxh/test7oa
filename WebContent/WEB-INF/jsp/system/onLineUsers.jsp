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
		<span class="alert">实时监控在线人员状态，登陆地方，登陆方式。</span>&nbsp;&nbsp;
		<span class="label label-important">
			注意：
		</span>
		<span class="alert alert-error">如果发现有异常登陆可以进行下线操作。</span>
	</div>										
	<table class="table table-bordered center" >
		
		<thead>
			<tr>
				<th width="10" >序号</th>
				<th width="140" >部门</th>
				<th width="140"  >姓名</th>
				<th width="140" >账号</th>
				<th width="50" >性别</th>
				<th width="100" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="u" items="${requestScope.users }"  varStatus="status">
			<tr>
				<td style="text-align: center;">${status.count }</td>
				<td style="text-align: center;">
					<my:outDeptName id="${u.deptId }"/>
				</td>
			
				<td style="text-align: center;">
					<my:outTrueName id="${u.id }"/>
				</td>
			
				<td style="text-align: center;">
					<my:outUserName id="${u.id }"/>
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${u.sex==1 }">
							男
						</c:when>
						<c:otherwise>女</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;">
					<shiro:hasPermission name="user:logoutUser">
						<a class="btn btn-danger btn-small" target="ajaxTodo"  href="user/allUsers/logout.do?id=${u.id }&otherBoxId=${param.rel}&otherCallback=refresh"  title="确定要注销此用户登陆吗?此用户的所有登陆都会被强制退出。"><i class="boot_icon-off boot_icon-white"></i> 下线</a>
					</shiro:hasPermission>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="5">
					
						<c:forEach var="m" items="${u.loginInfos }">
							<div style="margin-bottom: 5px" class="alert alert-success">
									<fmt:formatDate value="${m.value.loginTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							
									登陆系统,&nbsp;&nbsp;
									
									地点:
									
									${m.value.ipInfo.country }&nbsp;
									${m.value.ipInfo.region }&nbsp;
									${m.value.ipInfo.city }&nbsp;
									${m.value.ipInfo.isp }
							
									( ${m.value.ipInfo.ip } )。
									
									<span class="badge badge-info">
										<c:choose>
											<c:when test="${m.value.loginType==1 }">
												web (网页)
											</c:when>
											<c:otherwise>Android (手机客户端)</c:otherwise>
										</c:choose>
									
									</span>
									<shiro:hasPermission name="user:logoutOuther">
									<a class="btn btn-danger btn-mini" target="ajaxTodo"  href="user/my/loginInfo/logout.do?id=${m.value.id }&otherBoxId=${param.rel}&otherCallback=refresh"  title="确定要注销用户此处登陆吗?"><i class="boot_icon-off boot_icon-white"></i> 下线</a>
									</shiro:hasPermission>
							</div>
						</c:forEach>
					
				</td>
			</tr>
			
		</c:forEach>
		</tbody>	
	</table>
			
</div>
								