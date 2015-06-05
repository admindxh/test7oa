<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--菜单管理 -- 添加菜单
--%>

<div style="width: 98%;margin: 0 auto;" >						
<form method="post" action="systemSet/update.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
	
			<tr>
				<th style="width: 120px">系统开关：</th>
				<td>
					<select class="span2"  name="appStart" sValue="${requestScope.c.appStart }">
							<option value="1" >开启</option>
							<option value="0">关闭</option>
					</select>
					<br/><span class="text-info">关闭系统之后除系统 超级管理员 以外，任何人无法登录系统！</span>
				</td>
			</tr>
			<tr>
				<th>时间限制：</th>
				<td>
					
					<input type="text" name="loginStartTime" class="span2" id="systemset_startTime"  value="<c:out value="${requestScope.c.loginStartTime }"/>" onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"  readonly="readonly"/>
					
					--
					
					<input type="text" name="loginEndTime" class="span2"  value="<c:out value="${requestScope.c.loginEndTime }"/>" onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'systemset_startTime\');}'})"  readonly="readonly"/>
					
					<br/><span class="text-info">只有在此时间范围内才可以使用系统</span>
				</td>
				
			</tr>
			<tr>
				<th>IP限制：</th>
				<td>
					<textarea  rows="2" name="limitIps" style="width: 400px"><c:out value="${requestScope.c.limitIps }"/></textarea>
					<br/><span class="text-info">设置限制登录的ip地址多个ip以,隔开。可以用*做匹配，例：192.168.1.*</span>
				</td>
			</tr>
			<tr>
				<th>IP允许：</th>
				<td>
					<textarea  rows="2" name="allowIps" style="width: 400px"><c:out value="${requestScope.c.allowIps }"/></textarea>
					<br/><span class="text-info">设置允许登录的ip地址多个ip以,隔开,注：如果设置了IP允许，则IP限制失效。可以用*做匹配，例：192.168.1.*</span>
				</td>
			</tr>
			<tr>
				<th>消息提醒刷新时间：</th>
				<td>
					
					<input  type="text" name="msgwarnTime"  value="<c:out value="${requestScope.c.msgwarnTime }"/>"  class="easyui-numberspinner " required="required" style="width: 60px" data-options="min:1,max:100" />&nbsp;分钟
					
				</td>
			</tr>
			<tr>
				<th>允许密码错误次数：</th>
				<td>
					<input type="text" name="pwdErrorNum" value="<c:out value="${requestScope.c.pwdErrorNum }"/>" class="easyui-numberspinner "  style="width: 60px" data-options="min:1,max:100" />&nbsp;次
					<br/><span class="text-info">允许用户登录时输入的错误次数，不输入则不限制密码错误次数。当达到最大输入错误次数时会限制用户登录，过了限制登录冷却时间之后才能再次登录</span>
				</td>
			</tr>
			
			<tr>
				<th>限制登录冷却时间：</th>
				<td>
					<input type="text" name="pwdErrorTime" value="<c:out value="${requestScope.c.pwdErrorTime }"/>" class="easyui-numberspinner "  style="width: 60px" data-options="min:1,max:1000" />&nbsp;分钟
					<br/><span class="text-info">此参数和允许密码错误次数搭配使用</span>
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
	
	<input type="hidden" name="currentCallback" value="close" />
</form>
</div>									
						
					
		

	

