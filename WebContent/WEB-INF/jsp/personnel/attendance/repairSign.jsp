<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-考勤管理-考勤补录
--%>

<div style="width: 98%;margin: 0 auto;" >						
<form method="post" action="attendance/repairSign.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
	
			<tr>
				<th style="width: 120px">补录人员：</th>
				<td>
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName"  class="easyui-validatebox" required="true" />
				</a>
				<input type="hidden"  name="signuser"  toName="user.id"  />
 				</td>
			</tr>
			<tr>
				<th>补录时间：</th>
				<td>
					<input type="text" name="signtime" class="easyui-validatebox" required="true" 
					  value="<c:out value="${requestScope.att.firstup }"/>" onFocus="WdatePicker({dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" readonly="readonly"/>
				</td> 
				 
			</tr>
			<tr>
				<th>补录日期：</th>
				<td>
					<input type="text" readonly="readonly"  class="easyui-validatebox" required="true" onFocus="WdatePicker()"
						name="signdate" />
				</td>  
			</tr>	
			<tr>
				<th>考勤类型：</th>
				<td> 
					<select name="attType"  class="easyui-validatebox" required="true" >
					<option value="1">第一次上班 </option>
					<option value="2">第一次下班 </option>
					<option value="3">第二次上班</option>
					<option value="4">第二次下班 </option>
					<option value="5">第三次上班</option>
					<option value="6">第三次下班</option>
					</select> 
				</td>
			</tr>
			<tr>
				<th>登记状态：</th>
				<td>  
					<select name="islate"  class="easyui-validatebox" required="true" >
					<option value="0">正常</option>
					<option value="1">迟到</option>
					<option value="2">早退</option>
					<option value="3">请假</option>
					<option value="4">旷工</option>
					<option value="5">外出</option>
					<option value="6">其他</option>
					</select> 
				</td>
			</tr>
			<tr> 
				<th>补录说明：</th>
				<td>
					<textarea  name="repairexplain" rows="2" maxlength="50"></textarea>
				</td>
			 
			</tr>
			<tr>
				<th></th>
				<td colspan="3">
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
						
					
		

	

