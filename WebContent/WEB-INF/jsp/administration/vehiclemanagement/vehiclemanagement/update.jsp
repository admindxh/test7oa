<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--修改国辆管理
--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="vehiclemanagement/update.do"   onsubmit="return validateSubmitForm(this)">

  	<table class="table table-bordered">
  		<c:forEach var="v" items="${vm}" >
  	   		<tr>
				<th  style="width: 100px">
					车牌号:
				</th>
				<td>
					<input type="text" readonly="readonly" name="licenseno" class="easyui-validatebox"  required='true' validType="minlength[1]" value="${v.vm.licenseno}" maxlength="50"  />
				</td>
			</tr>
			<tr>
				<th>
					驾驶员:
				</th>
				<td>
			  	<input type="text" class="notice_dept" readonly="readonly" toName="accident.name" value="${v.xm }" maxlength="50" size="50" />
				<input type="hidden"  name="drivingno" readonly="readonly" toName="accident.id" value="${v.vm.drivingno }" value="驾驶员">
				</td>
			</tr>
			 <tr>
				<th>
					类型:
				</th>
				<td>
					<select  name="vehicletypeId" sValue="${v.vm.vehicletypeId}" >
						<my:outOptions type="10"/>
					</select>
				</td>
			</tr>
			 <tr>
				<th>
					购买日期:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="buydate" value="<fmt:formatDate  value="${v.vm.buydate}" pattern="yyyy-MM-dd" />" />
				</td>
			</tr>
			 <tr>
				<th>
					价格:
				</th>
				<td>
					<input type="text" name="price" class="easyui-numberbox"  data-options="precision:2" value="${v.vm.price}"  maxlength="11"  />
				</td>
			</tr>
			 <tr>
				<th>
					发动机号:
				</th>
				<td>
					<input type="text" name="enginenumber" value="${v.vm.enginenumber}"  maxlength="32" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					出产编号:
				</th>
				<td>
					<input type="text" name="pono" value="${v.vm.pono }"  maxlength="30" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					载重:
				</th>
				<td>
					<div class="input-append">
						<input type="text" name="weight" value="${v.vm.weight }" class="easyui-numberbox"   maxlength="4" size="10" />
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
						<input type="text" name="seat" value="${v.vm.seat }" class="easyui-numberbox"  maxlength="2" size="10" />
						<span class="add-on">个</span>
					</div>
				</td>
			</tr>
			 <tr>
				<th>
					使用单位:
				</th>
				<td>
					<input type="text" name="usingunit" value="${v.vm.usingunit }"  maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					强制险时间:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="insurancetime" value="<fmt:formatDate  value="${v.vm.insurancetime}" pattern="yyyy-MM-dd" />" />
				</td>
			</tr>
			 <tr>
				<th>
					商业险时间:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="cinsurancetime" value="<fmt:formatDate  value="${v.vm.cinsurancetime}" pattern="yyyy-MM-dd" />" />
				</td>
			</tr>
			 <tr>
				<th>
					状态:
				</th>
				<td>
					<select name="status" sValue="${v.vm.status}">
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
					<input type="text" name="usingpersonal" value="${v.vm.usingpersonal }" maxlength="10" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					备注:
				</th>
				<td>
						<textarea  name="remark" rows="2"  style="width: 95%"  >${v.vm.remark}</textarea>
				</td>
			</tr>
	  	    
  			<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						 <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
						 <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			</tr> 
			<input type="hidden" name="id" value="${v.vm.id}">
		    <input type="hidden"  name="createdate" value="<fmt:formatDate  value="${v.vm.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />"  />
			<input type="hidden" name="promulgator" value="${v.vm.promulgator}">
	</c:forEach>		
  	</table>
	
			
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
  			<input type="hidden" name="currentCallback" value="close" />	
</div>