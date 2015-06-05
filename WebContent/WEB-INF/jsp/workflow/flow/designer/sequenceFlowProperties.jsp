<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--顺序流属性--%>
<script type="text/javascript">
<!--
	var sequenceLine = workflow.getLine(nodeid);
	//获取监听器id
	function getOldListenerIds(){
		var listeners=sequenceLine.listeners;
		  var listenersIds=new Array();
		  for(var i=0;i<listeners.getSize();i++){
			var listener = listeners.get(i);
			listenersIds.push(listener.getId());
		  }
		return listenersIds.join(",");
	}
	//添加监听器
	function addListener(row){
		
		var ls=sequenceLine.getListener(row.id);
		if(!ls){
			var listener = new draw2d.Process.Listener();
			listener.id=row.id;
			listener.event = row.event;
			if(row.valueType==1){
				listener.serviceType="javaClass";
				listener.serviceClass = row.value;
			}else{
				listener.serviceType="expression";
				listener.serviceExpression = row.value;
			}
			
			sequenceLine.listeners.add(listener);
		}
	}
	//删除监听器
	function removeListener(id){
		sequenceLine.deleteListener(id);
	}
	
//-->
</script>
<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 
			iconCls="icon-save" onclick="saveLineProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="顺序流属性"	class="properties-menu">
				<table  style="margin-top: 10px;">
					<tr>
						<td align="right">id:</td>
						<td>
							<span type="text" id="lineId" ></span>
						</td>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td>
							<input type="text" id="lineName" />
						</td>
					</tr>
					<tr>
						<td align="right">条件:</td>
						<td>
							<textarea  id="lineCondition" class="textdesc" ></textarea>
						</td>
					</tr>
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.流程的流向路线，连接节点。</div>
						<div>2.流程的默认行为是并行行为，会选择符合条件的流线，多个流线符合条件将并行执行。(注：分支网关会根据分支类型特点执行。)</div>
						<div>3.跳转条件为表达式，例:\${day==1 },表达式返回true或false，若不填默认为true。</div>
				</fieldset>
			</div>
			<%--执行监听器 --%>
			<%@ include file="/WEB-INF/jsp/workflow/flow/designer/include/executionListener.jsp" %>
			
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	$(function(){
		$("#lineId").text(sequenceLine.lineId);
		$("#lineName").val(sequenceLine.lineName);
		$("#lineCondition").val(sequenceLine.condition);
	});
	//保存属性
	function saveLineProperties() {
		
		sequenceLine.lineName = $("#lineName").val().trim();
		sequenceLine.condition = $("#lineCondition").val().trim();
		sequenceLine.setLabel(sequenceLine.lineName);
	}
//-->
</script>
