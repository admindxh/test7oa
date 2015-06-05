<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--调用活动 外部子流程--%>

<div class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px;background:#E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true"	iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div  class="easyui-accordion"	fit="true" border="false">
			<div  title="活动属性" selected="true" class="properties-menu">
				<table >
					<tr>
						<td align="right">id:</td>
						<td><span id="callActivityId" ></span>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td><input type="text" id="callActivityName" /></td>
					</tr>
					<tr>
						<td align="right">描述:</td>
						<td><textarea id="documentation" name="documentation" class="textdesc"	></textarea></td>
					</tr>
					
					<tr>
						<td align="right">子流程:</td>
						<td>
							<select id="callSubProcess" >
								<option value="">--选择子流程--</option>
								
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">传出变量</td>
					</tr>
					<tr>
						<td align="right">来源变量:</td>
						<td><input type="text" id="insource" /></td>
					</tr>
					<tr>
						<td align="right">目标变量:</td>
						<td><input type="text" id="intarget" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">传入变量</td>
					</tr>
					<tr>
						<td align="right">来源变量:</td>
						<td><input type="text" id="outsource" /></td>
					</tr>
					<tr>
						<td align="right">目标变量:</td>
						<td><input type="text" id="outtarget" /></td>
					</tr>
					
					
					
					
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.调用活动：执行外部一个流程，本流程会等待子流程完成，然后才会继续向下执行。</div>
						<div>2.传出变量：来源变量是本流程的一个流程变量名称，子流程使用目标变量名称可以直接获取到值。</div>	
						<div>3.传入变量：来源变量是子流程的一个流程变量名称，主流程使用目标变量名称可以直接获取到值。</div>		
				</fieldset>
			</div>
			
			
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var callActivity = workflow.getFigure('<%=request.getParameter("nodeId") %>');
	$(function(){
		
		$('#callActivityId').text(callActivity.subProcessId);
		$('#callActivityName').val(callActivity.name);
		$('#documentation').val(callActivity.documentation);
		
		
		$('#insource').val(callActivity.insource);
		$('#intarget').val(callActivity.intarget);
		$('#outsource').val(callActivity.outsource);
		$('#outtarget').val(callActivity.outtarget);
		$.ajax({
			url:"workflow/work/flowTree.do",
			dataType:"json",
			success:function(json){
				var html="";
				$.each(json.flows,function(i,f){
					html+='<option value="'+f.key+'">'+f.name+'</option>';
				});
				$("#callSubProcess").append(html).val(callActivity.callSubProcess)
				
			}
		});
	});
	
	function saveTaskProperties() {
		
		callActivity.name = $('#callActivityName').val().trim();
		callActivity.documentation = $('#documentation').val().trim();
		
		callActivity.callSubProcess = $('#callSubProcess').val().trim();
		callActivity.insource = $('#insource').val().trim();
		callActivity.intarget = $('#intarget').val().trim();
		callActivity.outsource = $('#outsource').val().trim();
		callActivity.outtarget = $('#outtarget').val().trim();
		
	}
//-->
</script>
