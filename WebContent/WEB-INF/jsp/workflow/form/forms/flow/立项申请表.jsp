<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>立项申请表</caption>
	
	<tr>
		<th style="width: 60px;">项目名称</th>
		<td>
			<input type="text"  taskId="task1376896756310" name="xmmc" required="true" />
		</td>
		<th style="width: 60px;">申请日期</th>
		<td>
			<input type="text" taskId="task1376896756310" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>

	<tr>
		<th>项目概述</th>
		<td colspan="3" >
			<textarea  name="xmgs" taskId="task1376896756310" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>预期效益</th>
		<td colspan="3" >
			<textarea  name="yqxy" taskId="task1376896756310" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>人员需求</th>
		<td colspan="3" >
			<textarea  name="ryxq" taskId="task1376896756310" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>项目进度</th>
		<td colspan="3" >
			<textarea  name="xmjd" taskId="task1376896756310" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>费用预算</th>
		<td colspan="3" >
			<textarea  name="fyys" taskId="task1376896756310" required="true" ></textarea>
		</td>
	</tr>
	
	<tr>
		<th>领导意见</th>
		<td colspan="3">
			<textarea taskId="task1376896804048" name="shyj" required="true"></textarea>
		</td>
	</tr>
	
</table>