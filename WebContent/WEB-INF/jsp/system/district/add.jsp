<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：系统管理--地区管理 -- 新增
--%>

<div style="width: 600px;margin: 0 auto;" >
				
<form method="post" action="district/add.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">序号：</th>
				<td>
					<input type="text" name="disSort"  class="easyui-numberbox" required="true"  min="1" max="9999" maxlength="4"/>
					<br/><span class="text-info">用于同级下排序，值越小越靠前(数值范围：1-9999 整数)</span>
				</td>
			</tr>
			
			<tr>
				<th>名称：</th>
				<td>
					<input type="text" name="disName" class="easyui-validatebox"  required="true"   validType="minlength[1]" maxlength="50" />
					
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="disDesc" rows="2" maxlength="50" style="width: 300px"></textarea>
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
			
			
	<input type="hidden" name="superId" value="${param.id}"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
	<input type="hidden" name="currentCallback" value="close" />
			
			
</form>
</div>
										
						
					
		

	

