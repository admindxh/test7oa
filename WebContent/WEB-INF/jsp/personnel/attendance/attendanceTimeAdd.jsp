<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-考勤管理-考勤时间设置
--%>

<div style="width: 98%;margin: 0 auto;" >						
<form method="post" action="attendance/updateAttendanceTime.do" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
	
			<tr>
				<th style="width: 120px">名称：</th>
				<td>
					 <input type="text" name="name" class="easyui-validatebox" data-options="required:true"  />
				</td>
			</tr>
			<tr>
				<th>第一次上班时间：</th>
				<td>
					<input type="text" name="firstup" class="easyui-validatebox" required="true" 
					 id="firstup_startTime"  value="8:00:00" onFocus="WdatePicker({dateFmt:'HH:mm:ss'})" readonly="readonly"/>
				</td> 
				<th>第一次下班时间：</th>
				<td>					
					<input type="text" name="firstdown" id="firstdown_startTime" class="easyui-validatebox" required="true" 
					 value="12:00:00" onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'firstup_startTime\');}'})" readonly="readonly"/>
				</td> 
			</tr>
			<tr>
				<th>第二次上班时间：</th>
				<td>
					<input type="text" name="secondup" class="easyui-validatebox" required="true" 
					id="secondup_startTime"  value="13:30:00" onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'firstdown_startTime\');}'})" readonly="readonly"/>
				</td> 
				<th>第二次下班时间：</th>
				<td>					
					<input type="text" name="seconddown" id="seconddown_startTime" class="easyui-validatebox" required="true" 
					  value="17:30:00" onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'secondup_startTime\');}'})"  readonly="readonly"/>
				</td> 
			</tr>	
			<tr>
				<th>第三次上班时间：</th>
				<td>
					<input type="text" name="thirdup"  class="easyui-validatebox"  id="thirdup_startTime"  
					onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'seconddown_startTime\');}'})" readonly="readonly"/>
				</td> 
				<th>第三次下班时间：</th>
				<td>					
					<input type="text" name="thirddown"  class="easyui-validatebox"  
					 onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'thirdup_startTime\');}'})" readonly="readonly"/>
				</td> 
			</tr>			
			 
			<tr>
				<th>上班打卡开始时间：</th>
				<td>
				    <input  type="text" name="upstart"    class="easyui-numberspinner " required="required" style="width: 100px" data-options="min:1,max:100" />&nbsp;分钟
				</td>
				<th rowspan="4" >工作日设置：</th>
				<td rowspan="4" >
				 <table> 
					 <tr>
					  <th>类型</th><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th>周六</th><th>周七</th>
					 </tr>
					 <tr><td>第一次上班</td>
					 <td><input type="checkbox" name="work1" value="1"  ></td>
					 <td><input type="checkbox" name="work2" value="1"   ></td> 
					 <td><input type="checkbox" name="work3" value="1" ></td>
					 <td><input type="checkbox" name="work4" value="1"  ></td>
					 <td><input type="checkbox" name="work5" value="1"  ></td>
					 <td><input type="checkbox" name="work6" value="1" ></td>
					 <td><input type="checkbox" name="work7" value="1" ></td>
					  </tr>
					  <tr><td>第二次上班</td>
					  <td><input type="checkbox" name="work1" value="2" ></td>
					  <td><input type="checkbox" name="work2" value="2"  ></td> 
					  <td><input type="checkbox" name="work3" value="2" ></td>
					  <td><input type="checkbox" name="work4" value="2" ></td>
					  <td><input type="checkbox" name="work5" value="2" ></td>
					  <td><input type="checkbox" name="work6" value="2"  ></td>
					  <td><input type="checkbox" name="work7" value="2" ></td>
					  </tr>
					  <tr><td>第三次上班</td>
					  <td><input type="checkbox" name="work1" value="3" ></td>
					  <td><input type="checkbox" name="work2" value="3"  ></td> 
					  <td><input type="checkbox" name="work3" value="3"  ></td>
					  <td><input type="checkbox" name="work4" value="3" ></td>
					  <td><input type="checkbox" name="work5" value="3"  ></td> 
					  <td><input type="checkbox" name="work6" value="3" ></td>
					  <td><input type="checkbox" name="work7" value="3"  ></td>
					  </tr>
					  <tr><td colspan="8">
					  <span class="label label-warning">说明：</span>
					   <span class="alert" >勾中着为工作时间,工作时间需要登记打卡。</span>
					  </td></tr>
					</table>
				
				
				</td>
			</tr>
			<tr> 
				<th>上班打卡结束时间：</th>
				<td>
				    <input  type="text" name="upend"  class="easyui-numberspinner " required="required" style="width: 100px" data-options="min:1,max:100" />&nbsp;分钟
				</td>
			</tr>
			<tr>
				<th>下班打卡开始时间：</th>
				<td>
				    <input  type="text" name="downstart"   class="easyui-numberspinner " required="required" style="width: 100px" data-options="min:1,max:100" />&nbsp;分钟
				</td>
			</tr>
			<tr>
				<th>下班打卡结束时间：</th>
				<td>
				    <input  type="text" name="downend"   class="easyui-numberspinner " required="required" style="width: 100px" data-options="min:1,max:100" />&nbsp;分钟
				</td>
			</tr> 
			<tr>
				<th></th>
				<td colspan="3">
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			 </tr>
			
			
	</table> 
	<input type="hidden" name="currentCallback" value="close" />
</form>
</div>									
						
					
		

	

