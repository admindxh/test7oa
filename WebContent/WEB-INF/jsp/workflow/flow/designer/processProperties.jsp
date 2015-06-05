<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tag/myTag.tld" prefix="my" %>


<%--流程属性页面 --%>
<script type="text/javascript">
<!--
	//流程对象
	var process = workflow.process;
	
	//获取监听器id
	function getOldListenerIds(){
		var listeners=process.listeners;
		  var listenersIds=new Array();
		  for(var i=0;i<listeners.getSize();i++){
			var listener = listeners.get(i);
			listenersIds.push(listener.getId());
		  }
		return listenersIds.join(",");
	}
	//添加监听器
	function addListener(row){
		
		var ls=process.getListener(row.id);
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
			
			process.addListener(listener);
		}
	}
	//删除监听器
	function removeListener(id){
		process.deleteListener(id);
	}

//-->
</script>
<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 	iconCls="icon-save" onclick="saveProcessProperties()">保存</a>
	</div>
	
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="流程基本属性"	class="properties-menu">
				
					<table>
							<tr>
								<td align="right">id(key):</td>
								<td>
									<span  id="flowId" ></span>
								</td>
							</tr>
							<tr>
								<td align="right">流程名称:</td>
								<td>
									<input type="text" id="flowName" />
								</td>
							</tr>
							<tr>
								<td align="right">流程类型:</td>
								<td>
									<select id="flowType"  disabled="disabled">
										<my:outOptions type="17"/>
									</select>
								</td>
							</tr>
							
							<tr>
								<td align="right">描述:</td>
								<td>
									<textarea id="flowDesc" class="textdesc"></textarea>
								</td>
							</tr>
							
							<tr  id="tr_updateDesc">
								<td align="right">修改说明:</td>
								<td>
									<textarea id="flowUpdateDesc" cols="16" rows="2"></textarea>
								</td>
							</tr>
							
					</table>
					<fieldset style="line-height: 21px;">
							<legend>说明</legend>
							<div>流程类型在保存之后不可进行修改</div>
							<div>必须添加流程结束提醒监听器。</div>
					
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
		$("#flowId").text(process.id);
		
		$("#flowType").val(process.category);
		
		$("#flowName").val(process.name);
		
		$("#flowDesc").val(process.documentation);
	});
	
	
	//保存属性
	function saveProcessProperties() {
		
		process.name = $("#flowName").val().trim();
		process.documentation = $("#flowDesc").val().trim();
		flowUpdateDesc=$("#flowUpdateDesc").val().trim();
	}

		 
	  

	
	//-->
</script>


