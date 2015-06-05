<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--导出打印自定义管理 --添加
--%>

<div style="width: 600px;margin: 0 auto;" >					
<form method="post" action="tableCustom/dev/add.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">顺序号：</th>
				<td>
					<input type="text" name="fieldSort"  class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3"/>
					<br/><span class="text-info">用于打印，导出的排序，值越小越靠前(数值范围：1-999 整数)</span>
				</td>
			</tr>
			<tr>
				<th>属性名称：</th>
				<td>
					<input type="text" name="fieldName"  class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="30" />
					<span class="text-info">表字段对应的javaBean属性名称</span>
				</td>
			</tr>
			<tr>
				<th>属性类型：</th>
				<td>
					<select  name="fieldType" >
							<option value="1">文本</option>
							<option value="2">数字</option>
							<option value="3">日期(YYYY-MM-DD)</option>
							<option value="4">日期时间(YYYY-MM-DD HH:MM:SS)</option>
							<option value="5">用户id(转换用户姓名)</option>
							<option value="6">部门id(转换部门名称)</option>
							<option value="7">用户id(转换用户登录名)</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>显示名：</th>
				<td>
					<input type="text" name="fieldTitle" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="20"/>
					<span class="text-info">显示的默认名称</span>
				</td>
			</tr>
			<tr>
				<th>别名：</th>
				<td>
					<input type="text" name="fieldAnotherTitle"  maxlength="20" />
					<br/><span class="text-info">用户可以自定义的别名，当别名为空时使用默认名称</span>
				</td>
			</tr>
			<tr>
				<th>是否导出：</th>
				<td>
					<select   name="isExport" >
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>是否打印：</th>
				<td>
					<select   name="isPrint" >
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>是否显示：</th>
				<td>
					<select  name="isShow" >
							<option value="1">是</option>
							<option value="0">否</option>
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
			
	<input type="hidden" name="tbType" value="${param.tbType }"/>	
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
	<input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

