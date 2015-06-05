<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-考勤管理-登记记录详情
--%>

<div style="width: 98%;margin: 0 auto;" >						
<form method="post" action="attendance/updateSign.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
	
			<tr>
				<th style="width: 120px">登记人员：</th>
				<td>&nbsp;<my:outTrueName id='${requestScope.sign.signuser}'/></td>
			</tr>
			<tr>
				<th>登记时间：</th>
				<td>
				<c:out value="${requestScope.sign.signtime}" />
				</td> 
				 
			</tr>
			<tr>
				<th>登记日期：</th>
				<td>
					<c:out value="${requestScope.sign.signdate}" />
				</td>  
			</tr>	
			<tr>
				<th>考勤类型：</th>
				<td>
					<select name="attType" sValue="${requestScope.sign.attType}"  class="easyui-validatebox" required="true" >
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
					<select name="islate" sValue="${requestScope.sign.islate}" class="easyui-validatebox" required="true" >
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
				<th>补录时间：</th>
				<td>
					&nbsp;<fmt:formatDate value="${requestScope.sign.repairtime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			 
			</tr>
			<tr> 
				<th>补录人：</th>
				<td>
					&nbsp;<my:outTrueName id='${requestScope.sign.repairuser}'/>
				</td>
			 
			</tr>
			<tr> 
				<th>补录说明：</th>
				<td>
					<c:out value="${requestScope.sign.repairexplain}" />
				</td>
			 
			</tr>
			
			
	</table>
	
	<input type="hidden" name="currentCallback" value="close" />
</form>
</div>									
						
					
		

	

