<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--日程安排--修改日程
--%>

<div style="width: 98%;margin: 0 auto ;" >				
	
	<form method="post" action="schedule/update.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">主题：</th>
				<td>
					<input type="text" name="schTitle"   maxlength="50" class="easyui-validatebox" required="true" validType="minlength[1]"  value="<c:out value="${requestScope.s.schTitle }"/>"/>
				</td>
			</tr>
			<tr>
				<th>日程类型：</th>
				<td>
					<select  name="schType"  sValue="${requestScope.s.schType}" >
						<my:outOptions type="6"/>
					</select>
				</td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td>
					<input type="text" class="easyui-validatebox" required="true" readonly="readonly" id="schedule_updatetime_start" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s'})" name="startTime" value="<fmt:formatDate value="${requestScope.s.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/>
				</td>
			</tr>
			
			<tr>
				<th>结束时间：</th>
				<td>
					<input type="text" class="easyui-validatebox" required="true" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'schedule_updatetime_start\');}'})" name="endTime"  value="<fmt:formatDate value="${requestScope.s.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/>
				</td>
			</tr>
			<tr>
				<th>提醒时间：</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'schedule_updatetime_start\');}'})" name="warnTime"  value="<fmt:formatDate value="${requestScope.s.warnTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/>
				</td>
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					<textarea  name="schContent" rows="5" class="editor"  style="width: 98%;" >${requestScope.s.schContent }</textarea>
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
	<input type="hidden" name="id" value="${requestScope.s.id }"/>
	
    <input type="hidden" name="currentCallback" value="close" />
     <%--重新初始化日程提醒js--%>
    <input type="hidden" name="callback_fn" value="initScheduleWarn" />
    
    <c:choose>
    	<c:when test="${empty param.view }">
    		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
    	</c:when>
    	<c:otherwise>
    		<input type="hidden" name="otherCallback" value="refresh" />
    		<input type="hidden" name="otherBoxId" value="${param.rel }" />
    	</c:otherwise>
    </c:choose>
    
</form>
</div>		
					