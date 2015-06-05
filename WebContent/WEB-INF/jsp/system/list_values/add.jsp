<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：系统管理--字典管理 -- 新增
--%>

<div style="width: 600px;margin: 0 auto;" >
				
<form method="post" action="list/add.do"  onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">类型：</th>
				<td>
					<select  name="listType"  sValue="${param.listType}">
						 
						  <%@ include file="/WEB-INF/jsp/system/list_values/list.jsp" %>
					</select>
				</td>
			</tr>
			<tr>
				<th>字典值：</th>
				<td>
					<input type="text" name="listValue" class="easyui-validatebox"  required="true"   data-options="validType:['length[1,50]']" maxlength="50" />
					
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="listDesc"  rows="2" maxlength="50" style="width: 95%"></textarea>
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
	
</form>
</div>
										
						
					
		

	

