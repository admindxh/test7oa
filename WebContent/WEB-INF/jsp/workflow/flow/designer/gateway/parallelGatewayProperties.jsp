<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--网关节点属性  parallelGateway  并行--%>

<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 
			iconCls="icon-save" onclick="saveGatewayProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="并行网关属性"	class="properties-menu">
				<table  style="margin-top: 10px;">
					<tr>
						<td align="right">id:</td>
						<td>
							<span type="text" id="gatewayId" ></span>
						</td>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td>
							<input type="text" id="gatewayName" />
						</td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.并行节点，有拆分，合并功能。</div>
						<div>2.拆分节点会同时执行任务，合并节点会等到拆分的这些任务都执行完成才会继续往下执行。</div>
						<div>3.并行之后的流向条件会被忽略。</div>
				</fieldset>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var gatewayFigure = workflow.getFigure(nodeid);
	$(function(){
	
		$("#gatewayId").text(gatewayFigure.gatewayId);
		$("#gatewayName").val(gatewayFigure.gatewayName);
	});
	//保存属性
	function saveGatewayProperties() {
		gatewayFigure.gatewayName = $("#gatewayName").val().trim();
		
	}
//-->
</script>