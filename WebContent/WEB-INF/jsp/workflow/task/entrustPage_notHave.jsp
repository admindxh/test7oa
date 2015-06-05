<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：工作流程--未领任务--添加候选人
--%>

<div style="width: 500px;margin: 0 auto;" >
				
	<form method="post" action="task/todo/notHave/entrust.do" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-nobordered " style="margin-top: 30px;">
			   	<tr>
					<th style="width: 80px;">候选人：</th>
					<td>
						<a  href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
							<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 300px" class="easyui-validatebox" required="true"  validType="minlength[1]"></textarea>
						</a>
						<input type="hidden"  name="userIds" toName="user.id" />
					
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
		  
		  <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		  <input type="hidden" name="currentCallback" value="close" />		
		 <input type="hidden" name="taskId" value="${param.taskId}"/>
				
	</form>

</div>