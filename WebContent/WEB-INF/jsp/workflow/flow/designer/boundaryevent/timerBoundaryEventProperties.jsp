<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--定时边界事件--%>

<div  class="easyui-layout" fit="true">
	<div region="north" border="false" style="height:30px;background:#E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveBoundaryEventProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div  class="easyui-accordion" fit="true" border="false">
			<div  title="定时边界事件属性" selected="true" class="properties-menu">
				<table >
					<tr>
						<td align="right">Id:</td>
						<td><span id="boundaryEventId" ></span></td>
					</tr>
					<tr>
						<td align="right">事件名称:</td>
						<td>
							<input type="text" id="boundaryEventName" />
					   <td>
					</tr>
					<tr>
						<td align="right">中断活动:</td>
						<td>
							<select id="cancelActivity" name="cancelActivity">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
					   <td>
					</tr>
					<tr>
						<td align="right">事件类型:</td>
						<td>
							<select id="timeType" name="timeType">
								<option value="timeDate">timeDate</option>
								<option value="timeDuration">timeDuration</option>
								<option value="timeCycle">timeCycle</option>
							</select>
					   <td>
					</tr>
					<tr>
						<td align="right">expression:</td>
						<td>
							<input type="text" id="expression" name="expression" />
					   <td>
					</tr>
					<tr>
						<td align="right">关联活动Id:</td>
						<td><input type="text" id="attached" name="attached"/></td>
					</tr>
					
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.边界定时事件吸附在所关联的活动下面，当此活动开执行时，启动此定时器，到达指定时间时执行定时器。</div>
						<div>2.timeDate：精确的执行时间，ISO 8601格式。例：2013-08-08T12:12:12。</div>
						<div>3.timeDuration:指定在多长时间之后触发，ISO 8601格式。例：P10D,10天之后触发。格式参考：P1Y3M5DT6H7M30S(一年三个月五天六小时七分三十秒)</div>
				 		<div>4.timeCycle:指定循环的时间间隔。ISO 8601格式。例:R3/PT10H,每隔10小时执行一次，共循环3次。也可以使用cron表达式指定循环次数，例：0 0/5 * * * ?,每5分钟循环一次。</div>
						<div>三种事件类型都可以使用表达式从流程变量获取，但格式必须符合上述要求。</div>
				 </fieldset>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var timerEvent = workflow.getFigure('<%=(String)request.getParameter("nodeId") %>');
	
	$(function(){
		
		$('#boundaryEventId').text(timerEvent.boundaryId);
		$('#boundaryEventName').val(timerEvent.name);
		$('#cancelActivity').val(timerEvent.cancelActivity);
		$('#attached').val(timerEvent.attached);
		$('#expression').val(timerEvent.expression);
		$('#timeType').val(timerEvent.timeType);
	});

	//保存
	function saveBoundaryEventProperties(){
		
		timerEvent.name=$('#boundaryEventName').val();
		timerEvent.cancelActivity=$('#cancelActivity').val();
		timerEvent.attached=$('#attached').val().trim();
		timerEvent.expression=$('#expression').val().trim();
		timerEvent.timeType=$('#timeType').val();
		
	}
//-->
</script>
