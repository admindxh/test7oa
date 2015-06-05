<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-考勤管理-休息日详情
--%>

<div style="width: 98%;margin: 0 auto ;" >			
	
	<form method="post" action="attendance/addCloseTimeSet.do"  onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">名称：</th>
				<td><c:out value="${requestScope.c.name}" /> 
				</td>
			</tr>
			<tr>
				<th>考勤登记时间设置：</th>
				<td><c:out value="${requestScope.tnames}" />
				</td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td>
				<fmt:formatDate value="${requestScope.c.startdate }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			
			<tr>
				<th>结束时间：</th>
				<td>
				<fmt:formatDate value="${requestScope.c.enddate }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			 <tr>
				<th>操作人：</th>
				<td>
				<my:outTrueName id='${requestScope.c.createuser}'/>
				</td>
			</tr>
			<tr>
				<th>操作时间：</th>
				<td>
				<fmt:formatDate value="${requestScope.c.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			   
	</table>
	
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
    <%--重新初始化日程提醒js--%>
    <input type="hidden" name="callback_fn" value="initScheduleWarn" />
</form>
</div>
										
						
					
		

	

