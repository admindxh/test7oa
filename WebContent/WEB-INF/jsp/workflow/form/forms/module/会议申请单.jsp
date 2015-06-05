<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>会议申请单</caption>
	
	<tr>
		<th style="width: 60px;">申请人</th>
		<td>
			<input type="text"  taskId="task1387522779050" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th style="width: 60px;">申请日期</th>
		<td>
			<input type="text" taskId="task1387522779050" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>
	<tr>
		<th>部门</th>
		<td>
			<input type="text" taskId="task1387522779050" readonly="readonly" loginUserInfo="deptName" />
		</td>
		<th>会议类型</th>
		<td>
    		<select name="mType" selectOptions="listValue" processVariable="true"  optionsType="2" taskId="task1387522779050" class="easyui-validatebox" required="true">
    			<option value="" >--请选择--</option>
    		</select>
		</td>
	</tr>
	<tr>
		<th>标题</th>
		<td colspan="3" >
			<input type="text"  name="mName" taskId="task1387522779050" processVariable="true"  class="easyui-validatebox"	required="true"  data-options="validType:['length[1,50]']" maxlength="50" />
		</td>
	</tr>
	<tr>
		<th>主持人</th>
		<td colspan="3" >
			<input type="text"  name="mHost" processVariable="true" class="easyui-validatebox" required="true" maxlength="50" taskId="task1387522779050"  />
		</td>
	</tr>
	<tr>
		<th>会议日期</th>
		<td>
			<input type="text" name="mDate" processVariable="true" taskId="task1387522779050" readonly="readonly"  class="easyui-validatebox" required="true" onFocus="WdatePicker()" />
		</td>
		<th>会议室</th>
		<td>
    		<select name="mRid" processVariable="true" class="easyui-validatebox" required="true"  ajaxUrl="room/getSelectOptions.do" taskId="task1387522779050">
    			<option value="" >--请选择--</option>
    		</select>
		</td>
	</tr>
	<tr>
		<th>会议时间</th>
		<td colspan="3" >
			
			<input type="text" name="mStartTime" processVariable="true" readonly="readonly"  class="easyui-validatebox" required="true" id="hysq_statime"
			onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"  style="width: 150px;"  	name="MStatime" taskId="task1387522779050"/>
			&nbsp;--&nbsp;			
			<input type="text" name="mEndTime" processVariable="true" readonly="readonly"  class="easyui-validatebox" required="true"
			onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'hysq_statime\');}'})" 	name="MEndtime"  style="width: 150px;" taskId="task1387522779050"/>
		
		</td>
	</tr>
	<tr>
		<th>参与部门</th>
		<td colspan="3" >

			<textarea  readonly="readonly"  toName="dept.deptName" rows="2"  style="width: 90%" taskId="task1387522779050"
				href="dept/lookUpPage.do?type=2" lookupGroup="dept" lookupTitle="部门查询" targetBox="td"></textarea>

			<input type="hidden"   name="deptIds"  toName="dept.id" processVariable="true">
			<a class="btnDelete clearLookup" 
				 href="javascript:;" clearLookup="dept" title="清空" targetBox="td"></a>
		
		</td>
	</tr>
	<tr>
		<th>参与人</th>
		<td colspan="3" >
			<textarea  readonly="readonly" toName="user.trueName" rows="2"  style="width: 90%" taskId="task1387522779050"
				href="user/lookUpPage.do?type=2" lookupGroup="user" lookupTitle="用户查询" targetBox="td"></textarea>

			<input type="hidden"  name="userIds"  toName="user.id" processVariable="true">
			<a class="btnDelete clearLookup" 
				href="javascript:;" clearLookup="user" title="清空" targetBox="td"></a>
		</td>
	</tr>
	
	<tr>
		<th>简介</th>
		<td colspan="3" >
			<textarea  name="mSummary" taskId="task1387522779050" processVariable="true"></textarea>
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3" >
			<textarea  name="mRemark" taskId="task1387522779050"  processVariable="true"></textarea>
		</td>
	</tr>
	<tr>
		<th>审批人</th>
		
		<td>
			<input type="text" taskId="task1387522924340" loginUserInfo="name"   readonly="readonly"/>
		</td>
		
		<th>审批日期</th>
		<td>
			<input type="text" taskId="task1387522924340" nowDate="yyyy-MM-dd" readonly="readonly"/>
		</td>
	
	</tr>
	<tr>
		<th>审批结果</th>
		<td colspan="3" >
			<select taskId="task1387522924340" name="isAgree" class="easyui-validatebox" required="true" processVariable="true">
				<option value="" >--请选择--</option>
				<option value="1">批准</option>
				<option value="0">不批准</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>审批意见</th>
		<td colspan="3">
			<textarea taskId="task1387522924340" ></textarea>
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3">
			<textarea taskId="task1387522924340" ></textarea>
		</td>
	</tr>
</table>