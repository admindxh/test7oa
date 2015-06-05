<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：个人文档管理：创建目录
--%>

<div style="width: 500px;margin: 0 auto;" >
		
	<form method="post" action="personalFiles/addNode.do" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-bordered ">
				<tr>
					<th style="width: 80px">文件夹名：</th>
					<td>
						<input type="text" name="wdName" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="50" />
						<br/><span class="text-info">同一目录下文件夹名称不可重复</span>
					</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td>
						<textarea  name="wdDesc" rows="2" maxlength="50" style="width: 250px"></textarea>
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
										
						
					
		

	

