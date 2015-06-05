<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--网关节点属性  inclusiveGateway  包容--%>

<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 
			iconCls="icon-save" onclick="saveGatewayProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="包容网关属性"	class="properties-menu">
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
					<tr>
						<td align="right">默认流线:</td>
						<td>
							<input type="text" id="defaultFlow" />
						</td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.包容网关，有拆分，合并功能。</div>
						<div>2.包容网关是并行，分支的结合体，有条件判断，但是不会只执行一条，只要条件符合都会执行。</div>
						<div>3.如果都不符合条件会报异常，默认流程指定一个默认流向路线的id，当没有符合条件的会走默认流线。</div>
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
		$("#defaultFlow").val(gatewayFigure.defaultFlow);
	});
	//保存属性
	function saveGatewayProperties() {
		gatewayFigure.gatewayName = $("#gatewayName").val().trim();
		gatewayFigure.defaultFlow = $("#defaultFlow").val().trim();
	}
//-->
</script>