<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--计划任务--添加
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="workPlan/task/add.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">计划时间：</th>
				<td>
					<input type="text"  name="startTime" id="workplan_add" class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					--
					<input type="text"  name="endTime"  class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'workplan_add\');}'})" />
				</td>
			</tr>

			<tr>
				<th>附件：</th>
				<td>
					
					<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
						template='
							<tr>
					 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
								<td ><input type="file" fileupload="no" class="easyui-validatebox"  required="true" name="file[#index#]"  /></td>
								<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
							</tr>
						'>
						<thead>
							<tr>
								 <th width="35" style="text-align: center;">序号</th>
								 <th style="text-align: center;">附件</th>
							 	 <th width="35" style="text-align: center;">删除</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
						
				</td>
				
			</tr>
			<tr>
				<th>计划任务：</th>
				<td>
					<textarea  name="content"  rows="3"  style="width: 98%;" class="easyui-validatebox" required="true" validType="minlength[1]" maxlength="255"></textarea>
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
	
	
	<input type="hidden" name="workPlanId" value="${param.id }" />
	<input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

