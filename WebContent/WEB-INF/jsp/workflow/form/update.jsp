<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--表单设计--修改
--%>

<div style="width: 98%;margin: 0 auto ;" >
				
<form method="post" action="form/update.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">名称：</th>
				<td>
					<input type="text" name="formName" class="easyui-validatebox" required="true" validType="minlength[1]" maxlength="50"  value="<c:out value="${requestScope.f.formName }"/>"/>
					
				</td>
			</tr>
			<tr>
				<th>类型：</th>
				<td>
					<select  name="type" sValue="${requestScope.f.type  }">
						<option value="1">工作表单</option>
						<option value="2">任务表单</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>处理url：</th>
				<td>
					<input type="text" name="completePageUrl"  class="easyui-validatebox" required="true" validType="minlength[1]"  maxlength="255"   style="width: 98%" value="<c:out value="${requestScope.f.completePageUrl }"/>"/>
					<br/><span class="text-info">获取任务处理页面的url。工作流程默认:task/completePage.do </span>
				</td>
			</tr>
			<tr>
				<th>提交url：</th>
				<td>
					<input type="text" name="completeUrl"    maxlength="255"    style="width: 98%" value="<c:out value="${requestScope.f.completeUrl }"/>"/>
					<br/><span class="text-info">提交处理任务url。工作流程默认:</span>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					
					<textarea  name="formDesc" style="width: 98%;height: 30px;"  maxlength="50"><c:out value="${requestScope.f.formDesc }"/></textarea>
				</td>
			</tr>
			<tr>
				<th>html：</th>
				<td>
					<textarea  name="formHtml" style="width: 98%;height: 100px;"  ><c:out value="${requestScope.f.formHtml }"/></textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			 </tr>
		
	</table>		
		
		
		<input type="hidden" name="id" value="${requestScope.f.id}"/>
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
   	    <input type="hidden" name="currentCallback" value="close" />
</form>

</div>
										
						
					
		

	

