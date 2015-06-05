<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--网关节点属性  Exclusive Gateway 判断 分支--%>

<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 
			iconCls="icon-save" onclick="saveGatewayProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="分支网关属性"	class="properties-menu">
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
						<div>1.此网关相当于判断，会判断符合条件的一个流向(流向路线的条件返回true,如果流向路线没有定义条件，默认为true)</div>
						<div>2.如果多个符合，只会选择第一个定义的流程。</div>
						<div>3.如果都不符合条件会报异常，默认流程指定一个默认流向路线的id，当没有符合条件的会走默认流线。</div>
				</fieldset>
				
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var exclusiveGatewayFigure = workflow.getFigure(nodeid);
	$(function(){
		
		
		$("#gatewayId").text(exclusiveGatewayFigure.gatewayId);
		$("#gatewayName").val(exclusiveGatewayFigure.gatewayName);
		$("#defaultFlow").val(exclusiveGatewayFigure.defaultFlow);
		
	});
	//保存属性
	function saveGatewayProperties() {
		exclusiveGatewayFigure.gatewayName = $("#gatewayName").val().trim();
		exclusiveGatewayFigure.defaultFlow = $("#defaultFlow").val().trim();
		
	}
//-->
</script>