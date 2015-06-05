<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理 -- 查看详情
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="项目详情" 	>
			<div style="width: 98%;margin: 0 auto ;" >
					
				<table class="table table-bordered ">
						<tr>
							<th style="width: 80px">项目名称：</th>
							<td>
								<c:out value="${requestScope.p.name }" />
								
							</td>
						</tr>
						<tr>
							<th>项目类型：</th>
							<td>
								<my:outSelectValue id="${requestScope.p.type }"/>
							</td>
						</tr>
						<tr>
							<th>重要程度：</th>
							<td>
								<my:outSelectValue id="${requestScope.p.importance }"/>
							</td>	
						</tr>
						<tr>
							<th>计划时间：</th>
							<td>
								<fmt:formatDate value="${requestScope.p.startTime }" pattern="yyyy-MM-dd HH:mm" />
								--
								<fmt:formatDate value="${requestScope.p.endTime }" pattern="yyyy-MM-dd HH:mm" />
							</td>
						</tr>
						<tr>
							<th>负责人：</th>
							<td>
								<my:outTrueName id="${requestScope.fzIds }"/>
							</td>
						</tr>
						
						<tr>
							<th>授权部门：</th>
							<td>
								
								<my:outDeptName id="${requestScope.deptIds }"/>
								
							</td>
						</tr>
						
						<tr>
							<th>授权人员：</th>
							<td>
								
								<my:outTrueName id="${requestScope.userIds }"/>
						
							</td>
						</tr>
						
						<tr>
							<th>创建人：</th>
							<td>
								
								<my:outTrueName id="${requestScope.p.createUserId }"/>
						
							</td>
						</tr>
						<tr>
							<th>创建时间：</th>
							<td>
								<fmt:formatDate value="${requestScope.p.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>项目内容：</th>
							<td>
								<my:scriptEscape value="${requestScope.p.content}"/>
							</td>
						</tr>
						
				</table>
				
			</div>	
			
			
			
	</div>
	<div   title="任务进度" 	href="projectTask/load.do?projectId=${requestScope.p.id }&rel=${param.rel }_task">
			
	</div>
	
	<div title="项目文档" href="project/file/load.do?projectId=${requestScope.p.id }&rel=${param.rel }_files">
	
	</div>
</div>




										
						
					
		

	

