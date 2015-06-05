<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--服务任务 --%>

<div class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px;background:#E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true"	iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div  class="easyui-accordion"	fit="true" border="false">
			<div  title="服务任务属性" selected="true" class="properties-menu">
				<table >
					<tr>
						<td align="right">id:</td>
						<td><span id="serviceTaskId" ></span>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td><input type="text" id="serviceTaskName" /></td>
					</tr>
					<tr>
						<td align="right">描述:</td>
						<td><textarea id="documentation" name="documentation" class="textdesc"	></textarea></td>
					</tr>
					<tr>
						<td align="right">类型:</td>
						<td>
							<select id="serviceType" >
								<option value="javaClass">javaClass</option>
								<option value="expression" >expression</option>
								<option value="delegateExpression" >delegateExpression</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">执行内容:</td>
						<td><textarea id="expression" name="expression" class="textdesc" ></textarea></td>
					</tr>
					<tr>
						<td align="right">返回变量:</td>
						<td><input type="text" id="resultVariableName"	/></td>
					</tr>
					
					
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.服务任务，当流程执行到服务任务时，执行相应的服务内容。</div>
						<div>2.类型:<br/>&nbsp;&nbsp;&nbsp;&nbsp;javaClass,执行java类;<br/>&nbsp;&nbsp;&nbsp;&nbsp;expression：表达式，<br/>&nbsp;&nbsp;&nbsp;&nbsp;delegateExpression：Spring容器中bean。</div>
						<div>3.执行内容：具体的类全名，或表达式。</div>
						<div>4.返回变量：任务返回处理结果保存到流程变量中的变量名称。</div>
				</fieldset>
			</div>
			
			
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var task = workflow.getFigure('<%=request.getParameter("nodeId") %>');
	$(function(){
		
		$('#serviceTaskId').text(task.taskId);
		$('#serviceTaskName').val(task.taskName);
		$('#expression').val(task.expression);
		$('#documentation').val(task.documentation);
		$('#serviceType').val(task.serviceType);
		$('#resultVariableName').val(task.resultVariableName);
	});
	function saveTaskProperties() {
		
		task.taskName = $('#serviceTaskName').val().trim();
		task.setContent(task.taskName);
		task.expression = $('#expression').val().trim();
		task.documentation = $('#documentation').val().trim();
		task.serviceType = $('#serviceType').val();
		task.resultVariableName = $('#resultVariableName').val().trim();
		
	}
//-->
</script>

