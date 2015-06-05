<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--字典值管理 --字典值更新
--%>

<div style="width: 600px;margin: 0 auto;" >				
<form method="post" action="list/update.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">类型：</th>
				<td>
					<select  name="listType" sValue="${requestScope.lv.listType }">
							 <%@ include file="/WEB-INF/jsp/system/list_values/list.jsp" %>
					</select>
				</td>
			</tr>
			<tr>
				<th>字典值：</th>
				<td>
					<input type="text" name="listValue" class="easyui-validatebox"  required="true"   data-options="validType:['length[1,50]']" maxlength="50"  value="<c:out value="${requestScope.lv.listValue }" />"/>
					
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="listDesc"  rows="2" maxlength="50" style="width: 95%"><c:out value="${requestScope.lv.listDesc }" /></textarea>
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
	<input type="hidden" name="id" value="${requestScope.lv.id }"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
	<input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>	
					
		

	

