<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--脚本任务 --%>

<div class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px;background:#E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true"	iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div  class="easyui-accordion"	fit="true" border="false">
			<div  title="脚本任务属性" selected="true" class="properties-menu">
				<table >
					<tr>
						<td align="right">id:</td>
						<td><span id="scriptTaskId" ></span>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td><input type="text" id="scriptTaskName" /></td>
					</tr>
					<tr>
						<td align="right">描述:</td>
						<td><textarea id="documentation" name="documentation" class="textdesc"	></textarea></td>
					</tr>
					<tr>
						<td align="right">格式:</td>
						<td>
							<select id="scriptFormat" >
								<option value="javascript">javascript</option>
								<option value="groovy" >groovy</option>
							</select>
						</td>
					</tr>
					<%--<tr>
						<td align="right">返回变量:</td>
						<td><input type="text" id="resultVariable"	name="resultVariable" /></td>
					</tr>
					--%>
					<tr>
						<td align="right">脚本:</td>
						<td><textarea id="expression" name="expression" class="textdesc" ></textarea></td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.脚本任务，当流程执行到脚本任务时，执行相应的脚本。</div>
						<div>2.默认脚本变量不自动保存到流程变量，需在脚本中调用:execution.setVariable("myVar",val)保存到流程变量。</div>
						<div>3.脚本内容需要和脚本格式对应。</div>
						
				</fieldset>
			</div>
			
			
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var task = workflow.getFigure('<%=request.getParameter("nodeId") %>');
	$(function(){
		
		$('#scriptTaskId').text(task.taskId);
		$('#scriptTaskName').val(task.taskName);
		$('#expression').val(task.expression);
		$('#documentation').val(task.documentation);
		$('#scriptFormat').val(task.scriptFormat);
		//$('#resultVariable').val(task.resultVariable);
	});
	function saveTaskProperties() {
		
		task.taskName = $('#scriptTaskName').val().trim();
		task.setContent(task.taskName);
		task.expression = $('#expression').val().trim();
		task.documentation = $('#documentation').val().trim();
		task.scriptFormat = $('#scriptFormat').val();
		//task.resultVariable = $('#resultVariable').val();
		
	}
//-->
</script>

