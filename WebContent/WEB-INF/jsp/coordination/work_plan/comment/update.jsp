<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--计划任务--修改
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="workPlan/comment/update.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			
			<tr>
				<th style="width: 80px">计划任务：</th>
				<td>
					<textarea  name="content"  rows="3"  style="width: 98%;" class="easyui-validatebox" required="true" validType="minlength[1]" maxlength="255"><c:out value="${requestScope.t.content}" /></textarea>
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
	
	<input type="hidden" name="id" value="${requestScope.t.id }" />
	
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

