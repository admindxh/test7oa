<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：系统管理--数据库管理--上传文件，恢复数据
--%>
<div style="width: 600px;margin: 0 auto;" >						
<form method="post" action="database/uploadFile/restore.do" enctype="multipart/form-data"   onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			
			<tr>
				<th>备份文件：</th>
				<td >
					<input type="file" fileupload="no" name="file" class="easyui-validatebox"  required="true" />
					<span class="text-info">选择需要恢复的备份文件。</span>
				</td>
			</tr>
			<tr>
				<th>提醒：</th>
				<td>恢复数据之前最好先进行一次备份。</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >恢复</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			 </tr>
	</table>
	<input type="hidden" name="currentCallback" value="close" />
	<input type="hidden" name="otherCallback" value="refresh" />
	<input type="hidden" name="otherBoxId" value="${param.rel }" />
</form>
</div>
										
						
					
		

	

