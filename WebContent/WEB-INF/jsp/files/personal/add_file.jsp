<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：个人文档管理：创建文件
--%>

<div style="width: 500px;margin: 0 auto;" >				
<form method="post" action="personalFiles/addFile.do" enctype="multipart/form-data"   onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">文件名：</th>
				<td>
					<input type="text" name="wdName" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="50" />
					
				</td>
			</tr>
			<tr>
				<th>文件：</th>
				<td>
					<input type="file" fileupload="no" name="file"  class="easyui-validatebox"  required="true"  />
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="wdDesc"  rows="2" maxlength="50" style="width: 250px"></textarea>
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
	
			
		<input type="hidden" name="wdSuperId" value="${param.id }"/>
		
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
		<input type="hidden" name="currentCallback" value="close" />
			
			
</form>
</div>
										
						
					
		

	

