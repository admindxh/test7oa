<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--事故管理--增加事故管理页面
--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="accident/add.do"  onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered ">
  	  	 <tr>
				<th style="width: 110px">车牌号:</th>
				<td>
					<a href="vehiclemanagement/lookUpPage.do"  lookupGroup="vehiclemanagement" title="车牌号查询" >
	     				<input type="text"  class="easyui-validatebox"  required='true'    readonly="readonly" toName="vehiclemanagement.name"   />
					 </a>
			   		<input type="hidden"   name="licenseno"  toName="vehiclemanagement.id"  >
				</td>
			</tr>
			 <tr>
				<th> 驾驶员:</th>
				<td>
					<input type="text"  class="easyui-validatebox"      readonly="readonly" toName="vehiclemanagement.drivingname"   />
					<input type="hidden"   name="drivingno"  toName="vehiclemanagement.drivingno"  >
				</td>
				<!--  <td>
					<a href="driver/lookupdriver.do"   lookupGroup="accident" title="驾驶员姓名查询" >
	     				<input type="text"  class="easyui-validatebox"  required='true'  readonly="readonly" toName="accident.name"   />
	     			</a>
					<input type="hidden"  name="drivingno" readonly="readonly" toName="accident.id"  >
				</td>-->
			</tr>
			 <tr>
				<th>发生时间:</th>
				<td>
			    <input type="text"  readonly="readonly" onFocus="WdatePicker()" name="happendate"  />
				</td>
			</tr>
			 <tr>
				<th>发生地点:</th>
				<td>
				<input type="text" name="happenplace"  maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>事故确认者:</th>
				<td>
					<input type="text" name="accidentconfirm"  maxlength="10" size="50" />
				</td>
			</tr>
			 <tr>
				<th>保险理赔金额:</th>
				<td>
					<input type="text" name="insurancecompensation"  class="easyui-numberbox" data-options="precision:2" maxlength="9" size="10" />
				</td>
			</tr>
			 <tr>
				<th> 对方姓名:</th>
				<td>
					<input type="text" name="byname"  maxlength="10" size="50" />
				</td>
			</tr>
			 <tr>
				<th>对方住址:</th>
				<td>
					<input type="text" name="byaddress"  maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>对方电话:</th>
				<td>
				     <input type="text" name="byphone"   maxlength="15" size="50" />
				</td>
			</tr>
			 <tr>
				<th>公司暂负金额:</th>
				<td>
					<input type="text" name="cgmoney"  class="easyui-numberbox" data-options="precision:2" maxlength="9" size="50" />
				</td>
			</tr>
			<tr>
				<th>本人负担金额:</th>
				<td>
					<input type="text" name="selfgmomey"  class="easyui-numberbox" data-options="precision:2"  maxlength="9" size="50" />
					<span class="info"></span>
				</td>
			</tr>
			<tr>
				<th>对方车牌号码:</th>
				<td>
				<input type="text" name="bylicensenumberId"  maxlength="32" size="50" />
				</td>
			</tr>
			<tr>
				<th>事故概要:</th>
				<td>
					<textarea  name="summary" rows="2"  style="width: 95%"  ></textarea>
				</td>
			</tr>
			 <tr>
				<th>和解内容:</th>
				<td>
					<textarea  name="contentreconciliation" rows="2"  style="width: 95%"  ></textarea>
				</td>
			</tr> 	
			 <tr>
				<th>备注:</th>
				<td>
						<textarea  name="remark" rows="2"  style="width: 95%"  ></textarea>
				</td>
			</tr>
			<tr>
					<th></th>
					<td>
						<div  style="margin-top: 10px;margin-bottom: 10px;">
							  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
							  <button type="button" class="btn clear" >清空</button>
						</div>
					</td>
			</tr>
		</table>		
  			 <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	 <input type="hidden" name="currentCallback" value="close" />
	</form>
</div>