<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>用车申请单</caption>
	
	<tr>
		<th style="width: 60px;">申请人</th>
		<td>
			<input type="text"  taskId="task1375089602932" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th style="width: 60px;">申请日期</th>
		<td>
			<input type="text" taskId="task1375089602932" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>
	<tr>
		<th>部门</th>
		<td>
			<input type="text" taskId="task1375089602932" readonly="readonly" loginUserInfo="deptName" />
		</td>
		<th>申请车辆</th>
		<td>
    		<input type="text"  class="easyui-validatebox"  required="true"    readonly="readonly" toName="char.name"   
    		   href="vehiclemanagement/lookUpPage.do"  lookupGroup="char" lookupTitle="车辆查询" targetBox="td" lookupShow="char"/>
			
	   		<input type="hidden"   name="licenseno"  toName="char.id"  processVariable="true">
		</td>
	</tr>
	<tr>
		<th>用车时间</th>
		<td colspan="3" >
			<input type="text" taskId="task1375089602932" id="ycsq_start_time" name="startTime" readonly="readonly" style="width: 150px;"  required="true" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;--&nbsp;<input type="text" taskId="task1375089602932" name="endTime" readonly="readonly"  required="true" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'ycsq_start_time\');}'})" style="width: 150px;"/>
		</td>
	</tr>
	<tr>
		<th>目的地</th>
		<td colspan="3" >
			<textarea  name="sqmdd" taskId="task1375089602932" class="easyui-validatebox" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>申请事由</th>
		<td colspan="3" >
			<textarea  name="sqsy" taskId="task1375089602932" class="easyui-validatebox" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>审批人</th>
		
		<td>
			<input type="text" taskId="task1375089877173" loginUserInfo="name"   />
		</td>
		
		<th>审批日期</th>
		<td>
			<input type="text" taskId="task1375089877173" nowDate="yyyy-MM-dd" />
		</td>
	
	</tr>
	<tr>
		<th>审批结果</th>
		<td colspan="3" >
			<select taskId="task1375089877173" name="isAgree" class="easyui-validatebox" required="true" processVariable="true">
				<option value="" >--请选择--</option>
				<option value="1">批准</option>
				<option value="0">不批准</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>审批意见</th>
		<td colspan="3">
			<textarea taskId="task1375089877173" ></textarea>
		</td>
	</tr>
	<tr>
		<th>起始数</th>
		<td>
			<input type="text"  taskId="task1375090006403" name="startGls" class="easyui-validatebox" required="true" style="width: 90px;"/>公里
		</td>
		<th>终止数</th>
		<td>
			<input type="text"  taskId="task1375090060489" name="endGls" class="easyui-validatebox" required="true" style="width: 90px;"/>公里
		</td>
	</tr>
	
	<tr>
		<th>归还时间</th>
		<td>
			<input type="text"  taskId="task1375090060489" name="ghtime" class="easyui-validatebox" required="true" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		</td>
		<th>行驶里程</th>
		<td>
			<input type="text"  taskId="task1375090060489" name="xslc" class="easyui-validatebox" required="true" style="width: 90px;"/>公里
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3">
			<textarea taskId="task1375090060489" ></textarea>
		</td>
	</tr>
</table>