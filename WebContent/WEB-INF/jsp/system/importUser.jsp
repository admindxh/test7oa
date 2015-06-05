<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--用户导入
--%>
<div style="width: 98%;margin: 0 auto;" >
	<form  action="user/importUser.do"   method="post"   enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
		   
		 <table class="table table-bordered ">	
		    
		    <tr>
				 <th>导入数据：</th>
				 <td>
				 <input type="file" fileupload="no"  name="file"  size="25" />&nbsp;&nbsp;&nbsp;&nbsp; 
				 	 <span class="text-info"><a rel="点击下载用户导入模板" target="_blank" href="./templet/impUsers.xls">模板下载</a></span> 
 				 </td>
		   </tr>
		     
			 
		  
		  <tr>
			 <th></th>
			 <td>
				<div  style="margin-top: 10px;margin-bottom: 10px;">
					  <button type="submit" class="btn btn-primary" >导入</button>&nbsp;&nbsp;&nbsp;&nbsp;
					  <button type="button" class="btn clear" >清空</button>
				</div>
			 </td>
		  </tr>
	 </table>  
		  
	 <input type="hidden" name="currentCallback" value="refresh" /> 
		  
 </form>
</div>	
		

	

