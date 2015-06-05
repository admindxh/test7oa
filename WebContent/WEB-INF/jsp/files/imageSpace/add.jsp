<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：文档管理--图片空间--上传图片
--%>

<div style="width: 500px;margin: 0 auto;" >				
<form method="post" action="imageSpace/add.do" enctype="multipart/form-data"   onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">名称：</th>
				<td>
					<input type="text" name="name"    maxlength="255" />
					
				</td>
			</tr>
			<tr>
				<th>类型：</th>
				<td>
					<select name="type"  class="easyui-validatebox"  required="true" ajaxUrl="imageSpace/type/list.do" >
    					<option value="" >--请先选择类型--</option>
    				</select>
				</td>
			</tr>
			<tr>
				<th>文件：</th>
				<td>
					<input type="file" fileupload="no" name="file"  class="easyui-validatebox"  required="true"  />
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
