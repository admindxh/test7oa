<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户管理-客户信息-客户共享
--%>

<div style="width: 800px; margin: 0 auto;">
	<form action="info/share.do" method="post"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered " >
			<tr>
				<th width="80px">
					客户编号：
				</th>
				<td>
					<c:out value="${requestScope.info.clNo }" />

				</td>

				<th width="80px">
					客户名称：
				</th>
				<td>
					<c:out value="${requestScope.info.clName }" />
				</td>
			</tr>

			<tr>
				<th>
					创建人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.info.clCreate}" />

				</td>
				<th>
					负责人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.info.clStaff}" />

				</td>
			</tr>
			<tr>
				<th>
					共享给部门：
				</th>
				<td colspan="3">
					<a href="dept/lookUpPage.do?type=2" lookupGroup="dept"
						title="部门查询"> <textarea readonly="readonly"
							toName="dept.deptName" rows="2" style="width: 600PX" /> </a>
					<input type="hidden" value="" name="deptid" toName="dept.id">
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="dept" title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>
					共享给人：
				</th>
				<td colspan="3">

					<a href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询">
						<textarea readonly="readonly" toName="user.trueName" rows="2"
							style="width: 600PX" />
					</a>
					<input type="hidden" name="userid" toName="user.id" />
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>


				</td>

			</tr>

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




