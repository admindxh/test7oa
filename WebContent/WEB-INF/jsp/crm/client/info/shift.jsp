<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户管理-客户信息-修改
--%>

<div style="width: 800px; margin: 0 auto;">
	<form action="info/shift.do" method="post"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered ">
			<tr>
				<th width="80px">
					客户编号：
				</th>
				<td>
					<input type="text" name="clNo" disabled="disabled"
						 maxlength="50" size="50"
						value="<c:out value="${requestScope.info.clNo }"/>" />

				</td>

				<th width="80px">
					客户名称：
				</th>
				<td>
					<input type="text" name="clName" class="easyui-validatebox" required="true"  disabled="disabled"
						value="<c:out value="${requestScope.info.clName }"/>"
						 maxlength="50" size="50" />
				</td>
			</tr>
			<tr>

				<th>
					客户类别：
				</th>
				<td>
					<select name="clCategory" style="width: 100px;"   disabled="disabled"
						sValue="${requestScope.info.clCategory }">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="408" />
					</select>
				</td>
				<th>
					客户类型：
				</th>
				<td>
					<select name="clType" style="width: 100px;" disabled="disabled"
						sValue="${requestScope.info.clType }">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="401" />
					</select>

				</td>


			</tr>
			<tr>
				<th>
					客户等级：
				</th>
				<td>
					<select name="clGrade" style="width: 100px;" disabled="disabled"
						sValue="${requestScope.info.clGrade}">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="403" />
					</select>

				</td>

				<th>
					客户状态：
				</th>
				<td>
					<select name="clStatus" style="width: 100px;" disabled="disabled"
						sValue="${requestScope.info.clStatus }">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="404" />
					</select>



				</td>
			</tr>
			<tr>
				<th>
					客户阶段：
				</th>
				<td>
					<select name="clStaje" style="width: 100px;"  disabled="disabled"
						sValue="${requestScope.info.clStaje }">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="405" />
					</select>



				</td>
				<th>
					客户关系：
				</th>
				<td>
					<select name="clRelation" style="width: 100px;" disabled="disabled"
						sValue="${requestScope.info.clRelation }">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="406" />
					</select>
				</td>
			</tr>

			<tr>
				<th>
					客户来源：
				</th>
				<td>
					<select name="clSource" style="width: 100px;" disabled="disabled"
						sValue="${requestScope.info.clSource }">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="402" />
					</select>
				</td>
				<th>
					负责人：
				</th>
				<td colspan="3">
					<a href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询">
						<input type="text" toName="user.trueName"
							value="<my:outTrueName id="${requestScope.info.clStaff}"/>" /> </a>
					<input type="hidden" name="clStaff" toName="user.id"
						value="${ requestScope.info.clStaff}">
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>

				</td>


			</tr>
			<tr>
				<th>
					所属行业：
				</th>
				<td>
					<select name="clIndustry" style="width: 100px;"  disabled="disabled"
						sValue="${requestScope.info.clIndustry}">
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="407" />
					</select>
				</td>

				<th>
					行业描述：
				</th>
				<td>
					<input type="text" name="clDescribe" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clDescribe }"/>" />

				</td>
			</tr>
			<tr>
				<th>
					公司地址：
				</th>
				<td>
					<input type="text" name="clAddress" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clAddress }"/>" />

				</td>

				<th>
					收货地址：
				</th>
				<td>
					<input type="text" name="clReceipt" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clReceipt }"/>" />

				</td>
			</tr>

			<tr>
				<th>
					邮箱：
				</th>
				<td>
					<input type="text" name="clEmail" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clEmail }"/>" />

				</td>

				<th>
					手机号：
				</th>
				<td>
					<input type="text" name="clPhoneNumeber" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clPhoneNumeber}"/>" />

				</td>
			</tr>
			<tr>
				<th>
					电话：
				</th>
				<td>
					<input type="text" name="clPhone" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clPhone }"/>" />

				</td>

				<th>
					传真：
				</th>
				<td>
					<input type="text" name="clFax" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clFax }"/>" />

				</td>
			</tr>
			<tr>
				<th>
					公司网址：
				</th>
				<td>
					<input type="text" name="clWebsite" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clWebsite }"/>" />

				</td>

				<th>
					邮编：
				</th>
				<td>
					<input type="text" name="clZip" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clZip }"/>" />

				</td>
			</tr>
			<tr>
				<th>
					QQ/MSN：
				</th>
				<td>
					<input type="text" name="clQq" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clQq }"/>" />

				</td>

				<th>
					旺旺：
				</th>
				<td>
					<input type="text" name="clWangwang" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clWangwang }"/>" />

				</td>
			</tr>
			<tr>
				<th>
					开户银行：
				</th>
				<td>
					<input type="text" name="clBank" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clBank }"/>" />

				</td>

				<th>
					开户人：
				</th>
				<td>
					<input type="text" name="clPeople" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clPeople }"/>" />

				</td>
			</tr>
			<tr>
				<th>
					银行账号：
				</th>
				<td colspan="3">
					<input type="text" name="clNumber" size="50" disabled="disabled"
						value="<c:out value="${requestScope.info.clNumber }"/>" />

				</td>

			</tr>


			<tr>
				<th>
					备注：
				</th>
				<td colspan="3">
					<c:out value="${requestScope.info.clRemark }" />
				</td>
			</tr>
			<input type="hidden" name="clCreate" value="${requestScope.info.clCreate}">
			<input type="hidden" name="clCreateTime" value="<fmt:formatDate value="${requestScope.info.clCreateTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
			<input type="hidden" name="id" value="${requestScope.info.id }">
			<input type="hidden" name="currentCallback" value="close" />
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />

			<tr>
				<th>
				</th>
				<td>
					<div style="margin-top: 10px; margin-bottom: 10px;">
						<button type="submit" class="btn btn-primary">
							保存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn clear">
							清空
						</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>




