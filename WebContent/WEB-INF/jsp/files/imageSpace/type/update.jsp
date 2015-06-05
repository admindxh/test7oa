<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：文档管理--图片空间--类型管理--修改
--%>
<div style="width: 500px;margin: 0 auto;" >
	<form  action="imageSpace/type/update.do"   method="post"   onsubmit="return validateSubmitForm(this)">
		
		 <table class="table table-nobordered " style="margin-top: 25px;">
			  <tr>
			    	<th style="width: 80px">类型名称：</th>
			    	<td>
			     		<input type="text" name="name" class="easyui-validatebox" required="true"  data-options="validType:['length[1,50]']"  maxlength="50"  value="<c:out value="${requestScope.m.name }" />"/>
			    	</td>
			  </tr>
			  <tr>
			   		<th>说明：</th>
			    	<td>
			    		 <textarea  name="remark"  rows="3" maxlength="255" ><c:out value="${requestScope.m.remark }" /></textarea>
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
					
