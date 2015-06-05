<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--用户管理  用户更新
--%>
					
<div style="width: 600px;margin: 0 auto;" >	
				
<form method="post" action="user/update.do"  onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered">				
	
			<tr>
				<th style="width: 80px">账号：</th>
				<td>
					<input type="text" name="userName" value="<c:out value="${requestScope.u.userName }" />"  class="easyui-validatebox" required="true" data-options="validType:['minlength[1]','alphanumeric']"    maxlength="15" />
					<br/><span class="text-info">登录系统的账号,只能由数字、字母或者下划线组成，长度在3-15之间。</span>
				</td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td>
					<input type="text" name="trueName"  value="<c:out value="${requestScope.u.trueName }" />"  class="easyui-validatebox" required="true"  validType="minlength[1]"  maxlength="10" />
					<br/><span class="text-info">用户真实姓名，长度在2-10之间。</span>
				</td>
			</tr>
			<tr>
				<th>性别：</th>
				<td>
					<select  name="userSex" sValue="${requestScope.u.userSex }">
							<option value="1">男</option>
							<option value="0">女</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>手机号：</th>
				<td>
					<input type="text" name="mobilePhoneNumber"   value="${requestScope.u.mobilePhoneNumber }"  class="easyui-validatebox"  data-options="validType:['mPhone']"   maxlength="11" />
				</td>
			</tr>
			<tr>
				<th>部门：</th>
				<td>
					<a  href="dept/lookUpPage.do?type=1"  lookupGroup="dept" title="部门查询">
					<input type="text" readonly="readonly"  toName="dept.deptName"   value="<my:outDeptName id="${requestScope.u.deptId }"/>"  class="easyui-validatebox" required="true"/>
					</a>
					<input type="hidden" name="deptId" toName="dept.id" value="${requestScope.u.deptId }"/>
				</td>
			</tr>
			
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="userDesc"  rows="2" maxlength="50"><c:out value="${requestScope.u.userDesc }" /></textarea>
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<select  name="userStatus" sValue="${requestScope.u.userStatus }">
							<option value="1">允许登陆系统</option>
							<option value="0">禁止登陆系统</option>
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
			
		<input type="hidden"  name="id" value="${requestScope.u.id }"/>
		
	    <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
	    <input type="hidden" name="currentCallback" value="close" />
		
		  
	
</form>
</div>	
					
		

	

