<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--结束节点属性--%>

<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 
			iconCls="icon-save" onclick="saveEventProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="结束节点属性"	class="properties-menu">
				<table  style="margin-top: 10px;">
					<tr>
						<td align="right">id:</td>
						<td>
							<span type="text" id="endId" ></span>
						</td>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td>
							<input type="text" id="endName" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var eventFigure = workflow.getFigure(nodeid);
	$(function(){
	
		$("#endId").text(eventFigure.eventId);
		$("#endName").val(eventFigure.eventName);
		
	});
	//保存属性
	function saveEventProperties() {
		eventFigure.eventName = $("#endName").val().trim();
		
	}
//-->
</script>
