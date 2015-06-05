<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：文档管理--图片空间--图片管理--修改
--%>
<div style="width: 500px;margin: 0 auto;" >
	<form  action="imageSpace/update.do"   method="post"   onsubmit="return validateSubmitForm(this)">
		
		 <table class="table table-bordered " style="margin-top: 25px;">
			  <tr>
			    	<th style="width: 80px">图片名称：</th>
			    	<td>
			     		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width: 98%" value="<c:out value="${requestScope.m.name }" />"/>
			    	</td>
			  </tr>
			  <tr>
				<th>图片类型：</th>
				<td>
					<select name="type"  ajaxUrl="imageSpace/type/list.do" sValue="${requestScope.m.type }">
    					
    				</select>
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
		  
		  <input type="hidden" name="id" value="${requestScope.m.id }"/>
		  <input type="hidden" name="currentCallback" value="close" />
		  <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		   
	</form>
</div>
					
