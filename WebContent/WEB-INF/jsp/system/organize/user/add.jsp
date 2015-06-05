<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--用户管理 -- 新增用户
--%>

<div style="width: 600px;margin: 0 auto;" >

	<form  action="user/add.do" beforeCallback="encryptePassword"	 onsubmit="return validateSubmitForm(this)" method="post">
		  <table class="table table-bordered ">
			   <tr>
					<th style="width: 70px">账号：</th>
					<td>
						<input type="text" name="userName"  class="easyui-validatebox" required="true" data-options="validType:['minlength[3]','alphanumeric']"   maxlength="15"  />
						<br/><span class="text-info">登录系统的账号,只能由数字、字母或者下划线组成，长度在3-15之间。</span>
					</td>
				</tr>
				<tr>
					<th>密码：</th>
					<td>
						<input type="password" class="easyui-validatebox" required="true" validType="minlength[6]" encryptTo="userPassword"   maxlength="20" value="123456"/>
						<br/><span class="text-info">系统登录密码，默认为123456。长度在6-20之间。</span>
					</td>
				</tr>
				<tr>
					<th>姓名：</th>
					<td>
						<input type="text" name="trueName" class="easyui-validatebox" required="true" validType="minlength[2]"  maxlength="15" />
						<br/><span class="text-info">用户真实姓名，长度在2-15之间。</span>
					</td>
				</tr>
				<tr>
					<th>性别：</th>
					<td>
						<select  name="userSex" >
								<option value="1">男</option>
								<option value="0">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>手机号：</th>
					<td>
						<input type="text" name="mobilePhoneNumber"  class="easyui-validatebox"  data-options="validType:['mPhone']"   maxlength="11" />
					</td>
				</tr>
				<tr>
					<th>部门：</th>
					<td>
						<a href="dept/lookUpPage.do?type=1"  lookupGroup="dept" title="部门查询">
							<input type="text" readonly="readonly"  toName="dept.deptName"  class="easyui-validatebox" required="true" value='<my:outDeptName id="${param.deptId }"/>'/>
						</a>
						<input type="hidden" name="deptId"  toName="dept.id"  value="${param.deptId }"/>
					</td>
				</tr>
				
				<tr>
					<th>备注：</th>
					<td>
						<textarea  name="userDesc" rows="2" maxlength="50"></textarea>
					</td>
				</tr>
				<tr>
					<th>状态：</th>
					<td>
						<select  name="userStatus" >
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
		  
		  
		  <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		  <input type="hidden" name="currentCallback" value="close" />
		  <input type="hidden" id="modulus" value="${requestScope.modulus }"/>
		  <input type="hidden" id="exponent" value="${requestScope.exponent }"/>
		 
	</form>
	
	
</div>

