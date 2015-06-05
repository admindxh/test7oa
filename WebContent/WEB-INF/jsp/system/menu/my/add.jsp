<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：快捷菜单管理--添加快捷菜单
--%>

<div style="width: 500px;margin: 0 auto;" >
	<form  action="menu/my/add.do"  method="post"   onsubmit="return validateSubmitForm(this)">
		 
		  <table class="table table-nobordered " style="margin-top: 25px;">
		 	  <tr>
					<th style="width: 80px;">菜单序号：</th>
					<td>
						<input type="text" name="sort" class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3" />
						<br/><span class="text-info">用于菜单排序，值越小越靠前(数值范围：1-999 整数)</span>
					</td>
			  </tr>
			  <tr>
			    	<th >菜单名称：</th>
			    	<td>
			     		<a href="menu/lookUpPage.do?type=2" lookupGroup="menu" title="菜单查询" width="400">
						<input  type="text" readonly="readonly" class="easyui-validatebox"  required="true" toName="menu.name" />
					</a>
					
						<input type="hidden" name="menuId"  toName="menu.id"  value="${param.superId }" />
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
		  
		  <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		  <input type="hidden" name="currentCallback" value="close" />
		
	</form>
	
	
</div>
