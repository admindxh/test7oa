<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<div style="width: 98%;margin: 0 auto;">
     <form method="post" action="rules/add.do"   onsubmit="return validateSubmitForm(this)">
    	<table class="table table-bordered ">
	           <tr>
			         <th style="width: 50px">标题:</th>
			         <td><input type="text" name="rulestitle" class="easyui-validatebox"  required='true' validType="minlength[1]"  maxlength="50"></td>
		       </tr>
		       <tr>
			       <th>类型:</th>
			       <td>
				       <select class="easyui-validatebox"  required='true' name="typeid"  >
				        	<option value="">--请选择规章类型--</option>
					        <my:outOptions type="8"/>
				       </select>
			       </td>
		       </tr>
		       <tr>
			       <th>内容:</th>
			       <td>
			       		<textarea  name="contents" cols="100" rows="15" class="editor" style="width: 98%;"></textarea>
			       </td>
		       </tr>
		       <tr>
					<th></th>
					<td >
						<div  style="margin-top: 10px;margin-bottom: 10px;">
							  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
							  <button type="button" class="btn clear" >清空</button>
						</div>
					</td>
				</tr>
		</table>       
		       
		     <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	 <input type="hidden" name="currentCallback" value="close" />
    
		
	</form>
</div>
