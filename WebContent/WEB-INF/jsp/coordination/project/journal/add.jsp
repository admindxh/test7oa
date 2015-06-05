<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	项目管理----任务--添加进度日志
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="projectTask/journal/add.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
					
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">名称：</th>
				<td>
					<c:out value="${requestScope.t.name }" />
				</td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td>
					<input type="text"  name="startTime" id="projectJournal_add" class="easyui-validatebox" required="true"  style="width: 140px"  readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td>
					<input type="text"  name="endTime"  class="easyui-validatebox" required="true"   readonly="readonly"  style="width: 140px"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'projectJournal_add\');}'})" />
				</td>
			</tr>
			<tr>
				<th style="width: 80px">任务进度：</th>
				<td>
					<input class="easyui-numberspinner" name="speed" data-options="min:0,max:100,required:true" style="width:80px;" value="${requestScope.t.speed }" />
					<span class="text-info">更新任务进度</span>
				</td>
			</tr>
			<tr>
				<th>进度说明：</th>
				<td>
					<textarea  name="content"  rows="3"  style="width: 98%;" ></textarea>
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
	
	<input type="hidden" name="projectTaskId" value="${param.id }" />
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

