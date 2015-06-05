<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--驾驶员--添加驾驶员页面
--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="driver/add.do"   onsubmit="return validateSubmitForm(this)">

  		<table class="table table-bordered ">
  	   		<tr>
				<th style="width: 110px">
					姓名:
				</th>
				<td>
					<input type="text" name="drivingname"  class="easyui-validatebox"  required='true' validType="minlength[1]"  maxlength="20"  />
				</td>
			</tr>
			 <tr>
				<th>
                      性别:
				</th>
				<td>
					<select name="gender" >
					<option value="0">男</option>
					<option value="1">女</option>
					</select>
				</td>
			</tr>
			 <tr>
				<th>
					驾驶证号:
				</th>
				<td>
			 <input type="text" name="drivingno"   minlength="0" maxlength="30" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					出生日期:
				</th>
				<td>
				<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="birthday" />
				</td>
			</tr>
			 <tr>
				<th>
					领证日期:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="registerdate"  />
				</td>
			</tr>
			 <tr>
				<th>
					证件到期时间:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="papervaliddate"  />
				</td>
			</tr>
			 <tr>
				<th>
					驾龄:
				</th>
				<td>
					<div class="input-append">
						<input type="text"  minlength="0" maxlength="3" name="drivingage" class="easyui-numberbox" size="10"  />
						<span class="add-on">年</span>
					</div>
				</td>
			</tr>
			 <tr>
				<th>
					联系电话:
				</th>
				<td>
					<input type="text" name="phone"   maxlength="14"  />
				</td>
			</tr>
			 <tr>
				<th>
				   准驾车型:
				</th>
				<td>
					<input type="text" name="allowdriving"   minlength="0" maxlength="20" size="50"/>
				</td>
			</tr>
			 <tr>
				<th>
					单位或地址:
				</th>
				<td>
					<input type="text" name="address"   minlength="0" maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					年检记录:
				</th>
				<td>
					<textarea  name="checkrecord" rows="2"  style="width: 95%"  ></textarea>
				</td>
			</tr>
			 <tr>
				<th>
					备注:
				</th>
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
  	