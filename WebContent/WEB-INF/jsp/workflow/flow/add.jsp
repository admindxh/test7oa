<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程管理--添加
--%>

<div style="width: 600px;margin: 0 auto;" >
				
<form method="post" action="workflow/add.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered " >
			
			<tr>
				<th style="width: 80px">流程名称：</th>
				<td>
					<input  type="text" name="flowName"  class="easyui-validatebox" required="true" validType="minlength[1]" maxlength="255" />
				</td>
			</tr>
			<tr>
				<th>流程类型：</th>
				<td>
					<select class="easyui-validatebox" required="true"   name="flowType" sValue="${param.flowType}">
						<option value="">--请选择--</option>
						<my:outOptions type="17"/>
					</select>
					<br/><span class="text-info">流程类型不可修改。</span>
				</td>
			</tr>
			<dl >
				<th>key：</th>
				<td>
					<input type="text" name="flowId"  class="easyui-validatebox" required="true" data-options="validType:['minlength[1]','alphanumeric']"  maxlength="255" />
					<br/><span class="text-info">流程识别标识，不可重复,不可修改。格式：字符，数字，下划线</span>
				</td>
			</tr>
			<dl >
				<th>授权部门：</th>
				<td>
					<div class="alert alert-info">如果所有用户都可以使用此流程，则不需要分配授权部门和授权用户！</div>	
					<a href="dept/lookUpPage.do?type=2"  lookupGroup="dept" title="部门查询">
						<textarea  readonly="readonly"  toName="dept.deptName" rows="2" style="width: 400px"></textarea>
					</a>
					<input type="hidden"  name="deptIds" toName="dept.id" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
				</td>
			</tr>
			<dl >
				<th>授权用户：</th>
				<td>
					<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<textarea  readonly="readonly"  toName="user.trueName" rows="2" style="width: 400px"></textarea>
					</a>
					<input type="hidden"  name="userIds"  toName="user.id" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>使用状态：</th>
				<td>
					<select  name="isEnable">
						<option value="1">可用</option>
						<option value="0">禁用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>描述：</th>
				<td>
					<textarea  name="flowDesc" rows="2" maxlength="255" style="width: 98%"></textarea>
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
										
						
					
		

	

