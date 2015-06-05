<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--日志管理 --添加
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="journal/add.do"   onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">标题：</th>
				<td >
					<input type="text" name="journalTitle"  class="easyui-validatebox" required="true"  validType="minlength[1]"   maxlength="100"   />
				</td>
			</tr>
			<tr>
				<th>日期：</th>
				<td>
					<input type="text"  name="journalTime"  class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</td>
			</tr>
			<tr>
				<th>日志类型：</th>
				<td>
					<select    name="journalType" class="easyui-validatebox" required="true">
						<option value="">--请选择日志类型--</option>
						<my:outOptions type="5"/>
					</select>
				</td>
			</tr>
			<tr>
				<th>共享用户：</th>
				<td>
					<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<textarea  readonly="readonly" toName="user.trueName"  rows="2" style="width: 400px"/>
					</a>
					<input type="hidden"  name="userIds" toName="user.id" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>

					<label class="checkbox inline" ><input type="checkbox" name="isSendMsg" value="1" />发送提醒</label>
				</td>
			</tr>
			
			<tr>
				<th>内容：</th>
				<td>
					<textarea  name="journalContent"  rows="5" class="editor"  style="width: 98%;"></textarea>
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
						
					
		

	

