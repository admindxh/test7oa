<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--菜单管理--action管理 -- 添加action
--%>

<div style="width: 600px;margin: 0 auto;" >				
<form method="post" action="menu/action/add.do"  onsubmit="return validateSubmitForm(this)">
						
	 <table class="table table-bordered ">
			<tr>
				<th style="width: 80px">操作序号：</th>
				<td>
					<input type="text" name="actionSort" class="easyui-numberbox" required="true"  style="width: 60px"  min="1" max="999"  maxlength="3" />
					<br/><span class="text-info">用一菜单下排序展示，值越小越靠前(数值范围：1-999 整数)</span>
				</td>
			</tr>	
			<tr>
				<th>操作名称：</th>
				<td>
					<input type="text" name="actionName" class="easyui-validatebox"  style="width: 400px" required="true"   data-options="validType:['length[1,50]']" maxlength="50" />
				</td>
			</tr>
			<tr>
				<th>权限标识：</th>
				<td>
					<textarea  name="actionUrl" rows="3" maxlength="500" style="width: 400px"></textarea>
					<br/><span class="text-info">操作权限标识，多个以,隔开</span>
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
	<input type="hidden" name="menuId" value="${param.id}"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
	<input type="hidden" name="currentCallback" value="close" />	
	
</form>
</div>
										
						
					
		

	

