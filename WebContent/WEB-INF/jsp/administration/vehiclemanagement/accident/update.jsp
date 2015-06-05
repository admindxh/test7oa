<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--事故管理--修改事故管理
--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="accident/update.do"  onsubmit="return validateSubmitForm(this)">

  	<table class="table table-bordered ">
  		<c:forEach var="v" items="${vd}" >
  	  		 <tr>
				<th style="width: 110px">
					车牌号:
				</th>
				<td>
				    <input type="text"  readonly="readonly" toName="vehiclemanagement.name" value="${v.cph}" maxlength="50" size="50" />	
					<input type="hidden" name="licenseno"   readonly="readonly"  readonly="readonly"size="50" toName="vehiclemanagement.id" value="${v.ids }">
				</td>
			</tr>
			 <tr>
				<th>
                                            驾驶员:
				</th>
				<td>
				    <input type="text"  readonly="readonly" toName="vehiclemanagement.drivingname" value="${v.jsy}" maxlength="50" size="50" />	
					<input type="hidden" name="drivingno"   readonly="readonly" size="50" toName="vehiclemanagement.drivingno" value="${v.vd.drivingno}">
				</td>
			<!-- 	
				<td>
					<input type="text" class="notice_dept" readonly="readonly" toName="accident.name" value="${v.jsy}" maxlength="50" size="50" />
					<input type="hidden"  name="drivingno"  readonly="readonly" toName="accident.id" value="${v.vd.drivingno}">
				</td> -->
			</tr>
			 <tr>
				<th>
					发生时间:
				</th> 
				<td>
			    <input type="text"  readonly="readonly" onFocus="WdatePicker()" name="happendate" value="<fmt:formatDate  value="${v.vd.happendate}" pattern="yyyy-MM-dd" />" />
				</td>
			</tr>
			 <tr>
				<th>
					发生地点:
				</th>
				<td>
				<input type="text" name="happenplace" value="<c:out  value="${v.vd.happenplace }"></c:out>"   maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					事故确认者:
				</th>
				<td>
					<input type="text" name="accidentconfirm" value="<c:out value="${v.vd.accidentconfirm}"></c:out>"  maxlength="10" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					保险理赔金额:
				</th>
				<td>
					<input type="text" name="insurancecompensation" class="easyui-numberbox" data-options="precision:2"  value="<c:out value="${v.vd.insurancecompensation }"></c:out>" maxlength="11" size="10" />
				</td>
			</tr>
			 <tr>
				<th>
				   对方姓名:
				</th>
				<td>
					<input type="text" name="byname" value="<c:out value="${v.vd.byname}" /> "  maxlength="10" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					对方住址:
				</th>
				<td>
					<input type="text" name="byaddress" value="<c:out value="${v.vd.byaddress}"/> "  maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					对方电话:
				</th>
				<td>
				     <input type="text" name="byphone"   value="<c:out value="${v.vd.byphone}"></c:out>"  maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					公司暂负金额:
				</th>
				<td>
					<input type="text" name="cgmoney" class="easyui-numberbox" data-options="precision:2"  value="${v.vd.cgmoney }"  maxlength="9" size="50" />
				</td>
			</tr>
			<tr>
				<th>
					本人负担金额:
				</th>
				<td>
					<input type="text" name="selfgmomey" class="easyui-numberbox" data-options="precision:2" value="${v.vd.selfgmomey }"  maxlength="9" size="50" />
				</td>
			</tr>
			<tr>
				<th>
					对方车牌号码:
				</th>
				<td>
				<input type="text" name="bylicensenumberId"  value="<c:out value="${v.vd.bylicensenumberId }"></c:out>" maxlength="32" size="50" />
				</td>
			</tr>
			<tr>
				<th>
					事故概要:
				</th>
				<td>
					<textarea  name="summary" rows="2"  style="width: 95%"  >${v.vd.summary}</textarea>
				</td>
			</tr>
			 <tr>
				<th>
					和解内容:
				</th>
				<td>
					<textarea  name="contentreconciliation" rows="2"  style="width: 95%"  >${v.vd.contentreconciliation }</textarea>
				</td>
			</tr> 	
			 <tr>
				<th>
					备注:
				</th>
				<td>
						<textarea  name="remark" rows="2"  style="width: 95%"  >${v.vd.remark }</textarea>
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
			<input type="hidden" name="id" value="${v.vd.id }">
			<input type="hidden"  name="createdate" value="<fmt:formatDate  value="${requestScope.v.vd.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />"  />
			<input type="hidden" name="promulgator" value="${v.vd.promulgator}">
			 <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	 <input type="hidden" name="currentCallback" value="close" />
	 </c:forEach>
  </table>
 </form>
</div>