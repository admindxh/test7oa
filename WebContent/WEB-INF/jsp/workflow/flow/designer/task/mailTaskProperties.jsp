<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--邮件任务--%>

<div class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px;background:#E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true"	iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div  class="easyui-accordion"	fit="true" border="false">
			<div title="邮件任务属性" selected="true" class="properties-menu">
				<table id="general-properties">
					<tr>
						<td align="right">Id:</td>
						<td><span id="eamilId" ></span></td>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td><input type="text" id="eamilTaskName" /></td>
					</tr>
					<tr>
						<td align="right">描述:</td>
						<td><textarea id="documentation"  class="textdesc"	></textarea></td>
					</tr>
					<tr>
						<td align="right">接收者:</td>
						<td><input type="text" id="toEmail" /></td>
					</tr>
					<tr>
						<td align="right">发送者:</td>
						<td><input type="text" id="fromEmail" /></td>
					</tr>
					<tr>
						<td align="right">主题:</td>
						<td><input type="text" id="subjectEmail" /></td>
					</tr>
					<tr>
						<td align="right">抄送:</td>
						<td><input type="text" id="ccEmail" /></td>
					</tr>
					<tr>
						<td align="right">密送:</td>
						<td><input type="text" id="bccEmail" /></td>
					</tr>
					<tr>
						<td align="right">字符集:</td>
						<td><input type="text" id="charsetEmail" />
						</td>
					</tr>
					<tr>
						<td align="right">html:</td>
						<td><textarea id="htmlEmail" class="textdesc"></textarea></td>
					</tr>
					<tr>
						<td align="right">文本:</td>
						<td><textarea id="textEmail" class="textdesc"></textarea></td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.邮件任务，当流程执行到流程任务时，由系统自动发送邮件。</div>
						<div>2.可使用表达式赋值。</div>
						<div>3.html：作为邮件内容的HTML。</div>
						<div>4.文本：邮件的内容，在需要使用原始文字（非富文本）的邮件时使用。 可以与html一起使用，对于不支持富客户端的邮件客户端。 客户端会降级到仅显示文本的方式。</div>
				</fieldset>
				
				
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var task = workflow.getFigure('<%=request.getParameter("nodeId") %>');
	$(function(){
	
		$('#eamilId').text(task.taskId);
		$('#eamilTaskName').val(task.taskName);
		$('#documentation').val(task.documentation);
			
		$('#toEmail').val(task.toEmail);
		$('#fromEmail').val(task.fromEmail);
		$('#subjectEmail').val(task.subjectEmail);
		$('#ccEmail').val(task.ccEmail);
		$('#bccEmail').val(task.bccEmail);
		$('#charsetEmail').val(task.charsetEmail);
		$('#htmlEmail').val(task.htmlEmail);
		$('#textEmail').val(task.textEmail);
	});
	function saveTaskProperties() {
		task.taskName = $('#eamilTaskName').val().trim();
		task.setContent(task.taskName);
		task.documentation = $('#documentation').val().trim();
		
		task.toEmail = $('#toEmail').val().trim();
		task.fromEmail = $('#fromEmail').val().trim();
		task.subjectEmail = $('#subjectEmail').val().trim();
		task.ccEmail = $('#ccEmail').val().trim();
		task.bccEmail = $('#bccEmail').val().trim();
		task.charsetEmail = $('#charsetEmail').val().trim();
		task.htmlEmail = $('#htmlEmail').val().trim();
		task.textEmail = $('#textEmail').val().trim();
	}
//-->
</script>

