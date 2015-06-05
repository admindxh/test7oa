<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：工作流程--流程设计--权限变更
--%>

<div style="width: 98%;margin: 0 auto ;" >
				
	<form method="post" action="workflow/set/update.do" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-bordered ">
			   	
			   	<tr>
					<th style="width: 80px">使用状态：</th>
					<td>
						<select  name="isEnable" sValue="${requestScope.wf.isEnable }">
							<option value="1">可用</option>
							<option value="0">禁用</option>
						</select>
					</td>
				</tr>
			   	
			   	<tr>
					<th>授权部门：</th>
					<td>
						<div class="alert alert-info">如果所有用户都可以使用此流程，则不需要分配授权部门和授权用户！</div>
						<a href="dept/lookUpPage.do" lookupGroup="dept"  title="部门查询">
							<textarea  readonly="readonly"  toName="dept.deptName" rows="2" style="width: 400px" ><my:outDeptName id="${requestScope.deptIds }" /></textarea>
						</a>
						<input type="hidden"  name="deptIds" toName="dept.id" value="<c:out value="${requestScope.deptIds }" />"/>
						<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
					</td>
				</tr>
				<tr>
					<th>授权用户：</th>
					<td>
						<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
							<textarea  readonly="readonly" toName="user.trueName"  rows="2" style="width: 400px"><my:outTrueName id="${requestScope.userIds }" /></textarea>
						</a>
						<input type="hidden"  name="userIds" toName="user.id" value="<c:out value="${requestScope.userIds }" />"/>
						<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					</td>
				</tr>
				
				<tr>
					<th></th>
					<td>
						<div  style="margin-top: 10px;margin-bottom: 10px;">
							  <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
							  <button type="button" class="btn clear" >清空</button>
						</div>
					</td>
			   </tr>
			
				
		</table>
		<input type="hidden" name="id" value="${param.id}"/>
		
		
	</form>

</div>
