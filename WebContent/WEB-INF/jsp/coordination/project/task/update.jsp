<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理 -- 修改
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="projectTask/update.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">任务名称：</th>
				<td>
					<input type="text" name="name" class="easyui-validatebox" required="true" validType="minlength[1]"  maxlength="100"   value="<c:out value="${requestScope.t.name }" />"/>
					
				</td>
			</tr>
			<tr>
				<th>任务时间：</th>
				<td>
					<input type="text"  name="startTime" id="projectTask_update" class="easyui-validatebox" style="width: 140px"  required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${requestScope.t.startTime }" pattern="yyyy-MM-dd HH:mm" />"/>
					--
					<input type="text"  name="endTime"  class="easyui-validatebox" required="true"  style="width: 140px"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'projectTask_update\');}'})"  value="<fmt:formatDate value="${requestScope.t.endTime }" pattern="yyyy-MM-dd HH:mm" />"/>
				</td>
			</tr>
			<tr>
				<th>执行人：</th>
				<td>
					<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 400px"><my:outTrueName id="${requestScope.userIds }"/></textarea>
					</a>
					<input type="hidden"  name="userIds" toName="user.id" value="${requestScope.userIds }"/>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					<br/><span class="text-info">只有查询功能。</span>

				</td>
			</tr>
			<tr>
				<th>提醒：</th>
				<td>
					<label class="checkbox inline" ><input type="checkbox" name="isSendMsg" value="1" />系统提醒</label>
				</td>
			</tr>
	
			<tr>
				<th>内容：</th>
				<td>
					<textarea  name="content"  rows="3"  style="width: 98%;" ><c:out value="${requestScope.t.content}" /></textarea>
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

	<input type="hidden" name="id" value="<c:out value="${requestScope.t.id}" />"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

