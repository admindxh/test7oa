<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：工作流程--待办任务--添加委托人
--%>

<div style="width: 400px;margin: 0 auto;" >
				
	<form method="post" action="task/todo/doing/entrust.do" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-nobordered " style="margin-top: 30px;">
			   	<tr>
					<th style="width: 80px;">委托人：</th>
					<td>
						<a  href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询">
							<input type="text" readonly="readonly"  toName="user.trueName" />
						</a>
						<input type="hidden"  name="userId" toName="user.id" />
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
		  		
		 <input type="hidden" name="taskId" value="${param.taskId}"/>
		 <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 <input type="hidden" name="currentCallback" value="close" />				
		
		
	</form>

</div>