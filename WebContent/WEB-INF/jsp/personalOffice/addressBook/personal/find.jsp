<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-通讯录-个人通讯录
--%>

<div style="width: 800px; margin: 0 auto;">


	<table class="table table-bordered ">
		<tr>
			<th width="80px">
				姓名：
			</th>
			<td>
				${requestScope.per.peName }

			</td>

			<th width="80px">
				性别：
			</th>
			<td>
				${requestScope.per.peSex}
			</td>
		</tr>
		<tr>
			<th>
				电子邮箱：
			</th>
			<td>
				${requestScope.per.peEmail }
			</td>

			<th>
				手机：
			</th>
			<td>
				${requestScope.per.pePhone }
		</tr>
		<tr>
			<th>
				其他联系：
			</th>
			<td>
				${requestScope.per.peOther }

			</td>

			<th>
				分组：
			</th>
			<td>
				${requestScope.per.peGrouping}
				
			</td>
		</tr>
		<tr>
			<th>
				公司名称：
			</th>
			<td>
				${requestScope.per.peCompany }

			</td>

			<th>
				办公电话：
			</th>
			<td>
				${requestScope.per.peOfficPhone }

			</td>
		</tr>

		<tr>
			<th>
				办公传真：
			</th>
			<td>
				${requestScope.per.peOfficFax }

			</td>

			<th>
				公司地址：
			</th>
			<td>
				${requestScope.per.peCompanyAddress }

			</td>
		</tr>
		<tr>
			<th>
				邮政编码：
			</th>
			<td>
				${requestScope.per.peZip }

			</td>

			<th>
				职位：
			</th>
			<td>
				${requestScope.per.pePost }

			</td>
		</tr>
		<tr>
			<th>
				家庭地址：
			</th>
			<td>
				${requestScope.per.peHomeAddress }

			</td>

			<th>
				家庭电话：
			</th>
			<td>
				${requestScope.per.peHomePhone }

			</td>
		</tr>
		<tr>
			<th>
				备注：
			</th>
			<td colspan="3">
				${requestScope.per.peRemark }
			</td>
		</tr>
	</table>

</div>

