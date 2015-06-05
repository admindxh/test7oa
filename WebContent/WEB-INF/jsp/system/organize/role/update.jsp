<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构--角色管理--更新角色
--%>
<div style="width: 500px;margin: 0 auto;" >
	<form  action="role/update.do"   method="post"   onsubmit="return validateSubmitForm(this)">
		
		 <table class="table table-nobordered " style="margin-top: 25px;">
			  <tr>
			    	<th style="width: 80px">角色名称：</th>
			    	<td>
			     		<input type="text" name="roleName" class="easyui-validatebox" data-options="required:true"  value="<c:out value="${requestScope.r.roleName }" />"/>
			    	</td>
			  </tr>
			  <tr>
			   		<th>说明：</th>
			    	<td>
			    		 <textarea  name="roleDesc"  rows="3" maxlength="255" ><c:out value="${requestScope.r.roleDesc }" /></textarea>
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
		  
		  <input type="hidden" name="id" value="${requestScope.r.id }"/>
		  <input type="hidden" name="currentCallback" value="close" />
		  <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		   
	</form>
</div>
					
