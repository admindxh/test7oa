<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>请假申请单</caption>
	
	<tr>
		<th style="width: 60px;">申请人</th>
		<td>
			<input type="text"  taskId="task1375434715943" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th style="width: 60px;">申请日期</th>
		<td>
			<input type="text" taskId="task1375434715943" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>
	<tr>
		<th>部门</th>
		<td>
			<input type="text" taskId="task1375434715943" readonly="readonly" loginUserInfo="deptName" />
		</td>
		<th>假期类型</th>
		<td>
			<select taskId="task1375434715943" name="qjlx" required="true" >
				<option value="">--请选择--</option>
				<option value="1">事假</option>
				<option value="2">病假</option>
				<option value="3">其他</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>请假时间</th>
		<td colspan="3" >
			<input type="text" name="startTime" id="qjsq_start_time" readonly="readonly" taskId="task1375434715943" style="width: 150px;" required="true" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;--&nbsp;<input type="text" name="endTime" readonly="readonly" taskId="task1375434715943" required="true" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'qjsq_start_time\');}'})" style="width: 150px;"/>
		</td>
	</tr>
	<tr>
		<th>请假理由</th>
		<td colspan="3" >
			<textarea  name="qjdesc" taskId="task1375434715943" required="true" ></textarea>
		</td>
	</tr>
	
	<tr>
		<th>审批人</th>
		
		<td >
			<input type="text" taskId="task1375434836822" loginUserInfo="name"   />
		</td>
		
		<th>审批日期</th>
		<td>
			<input type="text" taskId="task1375434836822" nowDate="yyyy-MM-dd" />
		</td>
		
		
	</tr>
	<tr>
		<th>审批结果</th>
		<td colspan="3" >
			<select taskId="task1375434836822" name="spjg" required="true" >
				<option value="" >--请选择--</option>
				<option value="1">批准</option>
				<option value="0">不批准</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>审批意见</th>
		<td colspan="3">
			<textarea taskId="task1375434836822" ></textarea>
		</td>
	</tr>
	
</table>