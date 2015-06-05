<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--驾驶员--修改驾驶员信息页面
--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="driver/update.do"   onsubmit="return validateSubmitForm(this)">

  		<table class="table table-bordered">
  	  		 <tr>
				<th style="width:110px">
					姓名:
				</th>
				<td>
					<input type="text" name="drivingname" class="easyui-validatebox"  required='true' validType="minlength[1]" value="<c:out value="${vt.drivingname}"/>" maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
                   性别:
				</th>
				<td>
					<select name="gender" sValue="${vt.gender}" >
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
			 <input type="text" name="drivingno" value="<c:out value="${vt.drivingno} "></c:out>"   minlength="0" maxlength="20" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					出生日期:
				</th>
				<td>
				<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="birthday" value="<fmt:formatDate value="${vt.birthday }" pattern="yyyy-MM-dd" />" />
				</td>
			</tr>
			 <tr>
				<th>
					领证日期:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="registerdate" value="<fmt:formatDate value="${vt.registerdate }" pattern="yyyy-MM-dd"/>" />
				</td>
			</tr>
			 <tr>
				<th>
					证件到期时间:
				</th>
				<td>
					<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="papervaliddate" value="<fmt:formatDate value="${vt.papervaliddate }" pattern="yyyy-MM-dd"/>" />
				</td>
			</tr>
			 <tr>
				<th>
					驾龄:
				</th>
				<td>
					<div class="input-append">
						<input type="text" name="drivingage" value="<c:out value="${vt.drivingage }"></c:out>"  class="easyui-numberbox"  maxlength="3" size="5" />
						<span class="add-on">年</span>
					</div>
				</td>
			</tr>
			 <tr>
				<th>
					联系电话:
				</th>
				<td>
					<input type="text" name="phone"   value="<c:out value="${vt.phone}"></c:out>"  maxlength="15"  />
				</td>
			</tr>
			 <tr>
				<th>
				   准驾车型:
				</th>
				<td>
					<input type="text" name="allowdriving" value='<c:out value="${vt.allowdriving}"></c:out>'  maxlength="20" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					单位或地址:
				</th>
				<td>
					<input type="text" name="address" value="<c:out value="${vt.address}"></c:out>" maxlength="50" size="50" />
				</td>
			</tr>
			 <tr>
				<th>
					年检记录:
				</th>
				<td>
					<textarea  name="checkrecord" rows="2"  style="width: 95%" >${vt.checkrecord }</textarea>
				</td>
			</tr>
			 <tr>
				<th>
					备注:
				</th>
				<td>
						<textarea  name="remark" rows="2"  style="width: 95%"  >${vt.remark}</textarea>
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
		</table>	
			 <input type="hidden" name="id" value="${vt.id }">
			 <input type="hidden"  name="createdate" value="<fmt:formatDate  value="${requestScope.vt.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />"  />
			 <input type="hidden" name="promulgator" value="${vt.promulgator}">
			 <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
  			 <input type="hidden" name="currentCallback" value="close" />	
  	</form>
	
</div>