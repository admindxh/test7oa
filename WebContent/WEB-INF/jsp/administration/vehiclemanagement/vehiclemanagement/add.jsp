<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--添加国辆管理
--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="vehiclemanagement/add.do"  onsubmit="return validateSubmitForm(this)">

  		<table class="table table-bordered ">
  	  	 	<tr>
				<th style="width: 100px">
					车牌号:
				</th>
				<td>
					<input type="text" name="licenseno" class="easyui-validatebox"  required='true' validType="minlength[1]" maxlength="32"  />
				</td>
			</tr>
			 <tr>
				<th>
					驾驶员:
				</th>
				<td>
					<a href="driver/lookupdriver.do"   lookupGroup="accident" title="驾驶员姓名查询" >
	     				<input type="text"  readonly="readonly" toName="accident.name" maxlength="30" class="easyui-validatebox"  required='true' />
	     			</a>
					<input type="hidden"  name="drivingno" readonly="readonly" toName="accident.id"  >
				</td>
			</tr>
			 <tr>
				<th>
					类型:
				</th>
				<td>
		          	<select class="easyui-validatebox"  required='true'   name="vehicletypeId"  >
						<my:outOptions type="10"/>
					</select>
				</td>
			</tr>
			 <tr>
				<th>
					购买日期:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="buydate"  />
				</td>
			</tr>
			 <tr>
				<th>
					价格:
				</th>
				<td>
					<input type="text" name="price" class="easyui-numberbox"  data-options="precision:2"  maxlength="11" size="20" />
				</td>
			</tr>
			 <tr>
				<th>
					发动机号:
				</th>
				<td>
					<input type="text" name="enginenumber"  maxlength="32" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					出产编号:
				</th>
				<td>
					<input type="text" name="pono"  maxlength="30" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					载重:
				</th>
				<td>
					<div class="input-append">
						<input type="text" name="weight" class="easyui-numberbox"  maxlength="4"  size="10" />
						<span class="add-on">吨</span>
					</div>
				</td>
			</tr>
			 <tr>
				<th>
					座位:
				</th>
				<td>
					<div class="input-append">
						<input type="text" name="seat"  class="easyui-numberbox"  maxlength="2" size="10" />
						<span class="add-on">个</span>
					</div>
				</td>
			</tr>
			 <tr>
				<th>
					使用单位:
				</th>
				<td>
					<input type="text" name="usingunit"  maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					强制险时间:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="insurancetime"  />
				</td>
			</tr>
			 <tr>
				<th>
					商业险时间:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="cinsurancetime"  />
				</td>
			</tr>
			 <tr>
				<th>
					状态:
				</th>
				<td>
					<select  name="status">
					<option value="0">在库</option>
					<option value="1">使用中</option>
					<option value="2">维修</option>
					<option value="3">报废</option>
					</select>
				</td>
			</tr>
			 <tr>
				<th>
					 使用者:
				</th>
				<td>
					<input type="text" name="usingpersonal"  maxlength="10" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					备注:
				</th>
				<td>
						<textarea  name="remark"  rows="2"  style="width: 95%"></textarea>
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