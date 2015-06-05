<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--待办任务，办理任务
--%>

<div style="width: 98%;margin: 0 auto;" >
				
	<form method="post" action="${requestScope.completeUrl }"  beforeCallback="checkBackActivityId"  enctype="multipart/form-data"  onsubmit="return validateSubmitForm(this)">
							
		<div class="workflowform" >
			
			<c:if test="${!empty requestScope.flowFormHtml }">
				<fieldset class="fieldset">
					<legend>工作表单</legend>
					<div   flowForm="flow"  formType="editor"  taskId="${requestScope.task.taskDefinitionKey }" ><my:scriptEscape value="${requestScope.flowFormHtml }"/></div>
				 </fieldset>
			 </c:if> 
			 
			 <c:if test="${!empty requestScope.taskFormHtml }">
				<fieldset class="fieldset">
					<legend>任务表单</legend>
					<div  flowForm="task"  formType="editor">${requestScope.taskFormHtml }</div>
				</fieldset>
			</c:if>	
			
			<c:if test="${requestScope.backActivityId!=null }">
			<fieldset id="task_com_fieldset" class="fieldset">
				<legend>移交下一步</legend>	
						
					<div style="margin-left: 60px;margin-bottom: 10px">
						<span class="text-info">如果需要驳回，请选择需要驳回到的任务名称。</span><br/><br/>
						<label class="radio inline" ><input type="radio" name="nextType" value="1" checked="checked"  />正常流转(按流程设计)</label>
									
						<label class="radio inline" ><input type="radio" id="radioBackAct" name="nextType" value="2"  />驳回至</label>
						<select  name="activityId" >
							<option value="">--请选择任务名称--</option>
							<c:forEach var="m" items="${requestScope.backActivityId }">
								
								<option value="${m.id }">${m.name }</option>
								
							</c:forEach>
						</select>
					</div>
						
			</fieldset>
			</c:if>
			<fieldset class="fieldset">
				<legend>任务附件</legend>
				<table class="table table-bordered itemDetail" addButton="添加任务附件"  style="width:99%;margin-top: 0;"
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
				
			</fieldset>
		
				
				
				
		</div>
		<c:if test="${!empty param.rel }">
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
		</c:if>
   		<input type="hidden" name="currentCallback" value="close" />
		
		<input type="hidden" name="taskId" value="${param.taskId}"/>
		<input type="hidden" name="nextType" value="1"/>
		<input type="hidden" name="flowFormHtml" />
		<input type="hidden" name="taskFormHtml" />
		<input type="hidden" name="processVariablesKeys">
		
		
		
		
		<div  style="width: 100px;margin: 10px auto;" >
			  <button type="submit" class="btn btn-primary" onclick="$.onFlowFormSubmit(this)">提交</button>
		</div>
			
		 
		
	</form>
	
</div>
<script type="text/javascript">
	//提交前进行判断 是否驳回
	function checkBackActivityId($form){
		
		if($("#radioBackAct",$form).prop("checked")){
			
			if($("select[name='activityId']",$form).val()==""){
				Msg.topCenter({
					title:"提示",
					msg:"如果进行驳回操作，请选择驳回到的任务名称！"
				});
				return false;
			}
			
		}
		return true;
	}

</script>




									