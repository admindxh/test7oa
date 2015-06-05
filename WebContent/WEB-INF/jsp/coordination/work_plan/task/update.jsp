<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作计划--计划任务--修改
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="workPlan/task/update.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">计划时间：</th>
				<td>
					<input type="text"  name="startTime" id="workplan_add" class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${requestScope.t.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/>
					--
					<input type="text"  name="endTime"  class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'workplan_add\');}'})"  value="<fmt:formatDate value="${requestScope.t.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/>
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
						<tbody>
							<c:forEach  var="m"  items="${requestScope.files }" varStatus="status">
								<tr >	
										<td style="text-align: center;"><div class="itemDetail-rownumber">${status.count }</div></td>
										<td><c:out value="${m.name }"/></td>
										<td>
											<a href="javascript:;" class="btnDel"  title="删除"></a>
										</td>
										<input type="hidden" name="uuids" value="${m.uuid }" />
								</tr>
							</c:forEach>
						</tbody>
					</table>
						
				</td>
				
			</tr>
			<tr>
				<th>计划任务：</th>
				<td>
					<textarea  name="content"  rows="3"  style="width: 98%;" class="easyui-validatebox" required="true" validType="minlength[1]" maxlength="255"><c:out value="${requestScope.t.content}" /></textarea>
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
	
	<input type="hidden" name="id" value="${requestScope.t.id }" />
	
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

