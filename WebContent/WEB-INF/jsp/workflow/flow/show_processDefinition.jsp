<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程定义查看详情
--%>
<div style="width: 98%;margin: 0 auto;">
			
		<fieldset class="fieldset">
			<legend>基本信息</legend>
			<table class="table table-bordered "  style="margin-top: 10px">	
				<tr>
					<th style="width: 80px">流程名称：</th>
					<td><c:out value="${requestScope.pd.name }"/></td>
				</tr>
				<tr>
					<th>流程类型：</th>
					<td><my:outSelectValue id="${requestScope.pd.category }"/></td>
				</tr>
				
				<tr>
					<th>描述：</th>
					<td>
						<c:out value="${requestScope.pd.description  }"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="fieldset">
			<legend>预览</legend>
			<div style="width: 200px;margin: 10px auto;">
		   		
				<a class="btn btn-primary"  href="processDefinition/showFlowImgPage.do?processDefinitionId=${requestScope.pd.id}"  target="dialog" width="800" height="400" rel="${param.rel }_flowimg" title="${requestScope.pd.name }__流程图_第${requestScope.pd.version }版"><span>流程图</span></a>
				
				<c:if test="${requestScope.pd.hasStartFormKey }">
					<a class="btn btn-primary" style="margin-left: 10px"  href="processDefinition/startForm/show.do?processDefinitionId=${requestScope.pd.id}"  target="dialog" width="800" height="400" rel="${param.rel }_flowform" title="${requestScope.pd.name }__流程表单"><span>表单模板</span></a>
				</c:if>
				
			</div>	
		</fieldset>	


</div>