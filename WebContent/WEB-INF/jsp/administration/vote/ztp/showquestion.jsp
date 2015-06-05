<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：行政办公--子投票管理页面
--%>
<div style="width: 98%;margin: 10px auto;">
		
			<shiro:hasPermission name="vote:add">
			 	<a class="btn btn-primary btn-lg "   title="新建子投票"	 href="vote/preaddquestion.do?id=${voteid }&rel=${param.rel}" target="dialog" width="900" height="450" rel="${param.rel}_add" >新建子投票</a>	
			</shiro:hasPermission>
						
			<table   title="子投票" class="table table-bordered table-striped center">
					<thead>
						<tr>
							<th width="30" >序号</th>
							<th width="250" >标题</th>
							<th width="80"  >类型</th>
							<th width="50" >操作</th>
						</tr>
					</thead>
					<tbody >
					
							<c:forEach  var="v" items="${questions}">
								<tr>
									<td style="text-align: center;"><c:out value="${v.seqnum}"/></td>
									<td title="${v.title}"><c:out value="${v.title}"></c:out></td>
									<td style="text-align: center;">
										<c:if test="${v.type==0}"><c:out value="单选"></c:out></c:if>
										<c:if test="${v.type==1}"><c:out value="多选"></c:out></c:if>
										<c:if test="${v.type==2}"><c:out value="文本输入"></c:out></c:if>
									</td>
									<td style="text-align: center;">
										
											<a href="vote/preudpateztp.do?id=${v.id}&rel=${param.rel}" rel="${param.rel}_updateztp" target="dialog" width="900" height="450" title="修改子投票" >修改</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="vote/delztp.do?id=${v.id}&voteid=${voteid}&otherBoxId=${param.rel}&otherCallback=refresh"     target="ajaxTodo"   title="确定要删除吗？" >删除</a>
										
									</td>
								</tr>
							</c:forEach>	
					
						
					</tbody>
			</table>

</div>
