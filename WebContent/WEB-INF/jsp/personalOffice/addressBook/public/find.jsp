<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-通讯录-公司通讯录
--%>

<div style="width: 800px; margin: 0 auto;">

	<table class="table table-bordered ">
		<tr>
			<th width="80px">
				姓名：
			</th>
			<td>
				${requestScope.per.puName }

			</td>

			<th width="80px">
				性别：
			</th>
			<td>
				${requestScope.per.puSex}
			</td>
		</tr>
		<tr>
			<th>
				电子邮箱：
			</th>
			<td>
				${requestScope.per.puEmail }
			</td>

			<th>
				手机：
			</th>
			<td>
				${requestScope.per.puPhone }
		</tr>
		<tr>
			<th>
				其他联系：
			</th>
			<td>
				${requestScope.per.puOther }

			</td>

			<th>
				分组：
			</th>
			<td>
				<c:forEach var="g" items="${group}">
					<c:if test="${g.id==requestScope.per.puGrouping}">${g.gaName }</c:if>
				</c:forEach>



			</td>
		</tr>
		<tr>
			<th>
				公司名称：
			</th>
			<td>
				${requestScope.per.puCompany }

			</td>

			<th>
				办公电话：
			</th>
			<td>
				${requestScope.per.puOfficPhone }

			</td>
		</tr>

		<tr>
			<th>
				办公传真：
			</th>
			<td>
				${requestScope.per.puOfficFax }

			</td>

			<th>
				公司地址：
			</th>
			<td>
				${requestScope.per.puCompanyAddress }

			</td>
		</tr>
		<tr>
			<th>
				邮政编码：
			</th>
			<td>
				${requestScope.per.puZip }

			</td>

			<th>
				职位：
			</th>
			<td>
				${requestScope.per.puPost }

			</td>
		</tr>
		<tr>
			<th>
				家庭地址：
			</th>
			<td>
				${requestScope.per.puHomeAddress }

			</td>

			<th>
				家庭电话：
			</th>
			<td>
				${requestScope.per.puHomePhone }

			</td>
		</tr>
		<tr>
			<th>
				备注：
			</th>
			<td colspan="3">
				${requestScope.per.puRemark }
			</td>
		</tr>
	</table>
</div>

