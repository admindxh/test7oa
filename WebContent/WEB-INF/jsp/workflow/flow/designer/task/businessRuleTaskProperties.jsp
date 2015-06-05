<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--业务规则--%>

<script type="text/javascript">
	var tid = 'task1373947091391';
	var task = workflow.getFigure(tid);
	populateTaskProperites();
	function saveTaskProperties() {
		task.taskId = $('#id').val();
		task.rulesInput = $('#rulesInput').val();
		task.rulesOutputs = $('#rulesOutputs').val();
		task.isclude = $('#isclude').combobox('getValue');
		task.rules = $('#rules').val();
		tip("保存成功!");
	}
	function populateTaskProperites() {
		$('#id').val(task.taskId);
		$('#rulesInput').val(task.rulesInput);
		$('#rulesOutputs').val(task.rulesOutputs);
		$('#isclude').combobox('setValue', task.isclude);
		$('#rules').val(task.rules);
	}
</script>
<div id="task-properties-layout" class="easyui-layout" fit="true">
	<div id="task-properties-toolbar-panel" region="north" border="false"
		style="height:30px;background:#E1F0F2;">
		<a href="##" id="sb2" class="easyui-linkbutton" plain="true"
			iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div id="task-properties-panel" region="center" border="true">
		<div id="task-properties-accordion" class="easyui-accordion"
			fit="true" border="false">
			<div id="general" title="主属性" selected="true" class="properties-menu">
				<table id="general-properties">
					<tr>
						<td align="right">Id:</td>
						<td><input type="text" id="id" name="id" value="" />
						</td>
					</tr>
					<tr>
						<td align="right">输入变量:</td>
						<td><input type="text" id="rulesInput" name="rulesInput"
							value="" />
						</td>
					</tr>
					<tr>
						<td align="right">输出变量:</td>
						<td><input type="text" id="rulesOutputs" name="rulesOutputs"
							value="" />
						</td>
					</tr>
					<tr>
						<td align="right">选择规则:</td>
						<td><select id="isclude" name="isclude">
								<option value="">全部规则</option>
								<option value="include">包含规则</option>
								<option value="exclude">排除规则</option>
						</select></td>
					</tr>
					<tr>
						<td align="right">规则:</td>
						<td><input type="text" id="rules" name="rules"
							readonly="true" value="" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>