<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--个人设置 -- 修改密码
--%>

<div style="width: 500px;margin: 0 auto;" >	
					
	<form method="post" action="user/updateMyPw.do"  beforeCallback="encryptePassword"   onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-nobordered" style="margin-top: 30px;">
				
				<tr>
					<th>旧密码：</th>
					<td>
						<input  type="password"  encryptTo="oldPassword"  class="easyui-validatebox" required="true" data-options="validType:['length[6,20]']" maxlength="20" />
						<span class="text-info">输入旧密码，长度在6-20之间。</span>
					</td>
				</tr>
				<tr>
					<th>新密码：</th>
					<td>
						<input id="w_update_pwd" type="password" encryptTo="userPassword"   class="easyui-validatebox" required="true" data-options="validType:['length[6,20]']"/>
						<span class="text-info">输入新密码，长度在6-20之间。</span>
					</td>
				</tr>
				<tr>
					<th>重复密码：</th>
					<td>
						<input type="password"   encryptTo="pwd"  class="easyui-validatebox" required="true" data-options="validType:['length[6,20]','equals[\'#w_update_pwd\']']"/>
						<span class="text-info">重新输入一次新密码。</span>
					</td>
				</tr>
				<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			 </tr>
				
		</table>
		<input type="hidden" name="currentCallback" value="close" />
				
		<input type="hidden" id="modulus" value="${requestScope.modulus }"/>
		<input type="hidden" id="exponent" value="${requestScope.exponent }"/>
		
	</form>
</div>
										
						