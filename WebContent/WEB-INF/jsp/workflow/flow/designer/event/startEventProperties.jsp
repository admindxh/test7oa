<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%--开始节点属性--%>

<div  class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px; background: #E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" 
			iconCls="icon-save" onclick="saveEventProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div class="easyui-accordion" 	fit="true" border="false">
			<div  style="padding: 1px;" title="开始节点属性"	class="properties-menu">
				
				<table  style="margin-top: 10px;">
					<tr>
						<td align="right">id:</td>
						<td>
							<span type="text" id="startId" ></span>
						</td>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td>
							<input type="text" id="startName" />
						</td>
					</tr>
					<tr >
						<td align="right">表单:</td>
						<td>
							<a href="form/lookUpPage.do?type=1"  lookupGroup="flowform" title="表单查询" targetBox="table">
								<input id="formName" toName="flowform.formName" readonly="readonly" />
							</a>
							<input type="hidden" id="formId"  toName="flowform.id" />
							<a  class="clearLookup" style="margin-left: 5px;" href="javascript:;"  clearLookup="flowform" targetBox="table"  title="清空">清空</a>
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
	
		$("#startId").text(eventFigure.eventId);
		$("#startName").val(eventFigure.eventName);
		if(eventFigure.formKey!=null&&eventFigure.formKey!=""){
			
			$.ajax({
				url:"form/getFormNameById.do?id="+eventFigure.formKey,
				type:"get",
				cache:false,
				dataType:"json",
				success:function(data){
					
					if(data.name!=""){
						
						$("#formName").val(data.name);
						$("#formId").val(eventFigure.formKey);
					}else{
						
					}
					
				}
			});
		}
		
	});

	//保存属性
	function saveEventProperties() {
		
		eventFigure.eventName = $("#startName").val().trim();
		eventFigure.formKey = $("#formId").val().trim();
		
	}
//-->
</script>

