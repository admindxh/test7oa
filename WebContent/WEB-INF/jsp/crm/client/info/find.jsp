<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户管理-客户信息-查看
--%>

<div style="width: 800px; margin: 0 auto;">
	<form action="personal/update.do" method="post"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered ">
			<tr>
				<th width="80px">
					客户编号：
				</th>
				<td>
					<c:out value="${requestScope.info.clNo }"/>

				</td>

				<th width="80px">
					客户名称：
				</th>
				<td>
					<c:out value="${requestScope.info.clName }"/>
				</td>
			</tr>
			<tr>

				<th>
					客户类别：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.info.clCategory }" />
				</td>
				<th>
					客户类型：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clType }" />

				</td>


			</tr>
			<tr>
				<th>
					客户等级：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clGrade }" />

				</td>

				<th>
					客户状态：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clStatus }" />
				</td>
			</tr>
			<tr>
				<th>
					客户阶段：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clStaje }" />

				</td>
				<th>
					客户关系：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clRelation }" />
				</td>
			</tr>

			<tr>
				<th>
					客户来源：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clSource }" />
				</td>
				<th>
					负责人：
				</th>
				<td colspan="3">
					<my:outTrueName id="${requestScope.info.clStaff}"/> 

				</td>


			</tr>
			<tr>
				<th>
					所属行业：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.info.clIndustry }" />
				</td>

				<th>
					行业描述：
				</th>
				<td>
					<c:out value="${requestScope.info.clDescribe }"/>

				</td>
			</tr>
			<tr>
				<th>
					公司地址：
				</th>
				<td>
					<c:out value="${requestScope.info.clAddress }"/>

				</td>

				<th>
					收货地址：
				</th>
				<td>
					<c:out value="${requestScope.info.clReceipt }"/>

				</td>
			</tr>

			<tr>
				<th>
					邮箱：
				</th>
				<td>
					<c:out value="${requestScope.info.clEmail }"/>

				</td>

				<th>
					手机号：
				</th>
				<td>
					<c:out value="${requestScope.info.clPhoneNumeber}"/>

				</td>
			</tr>
			<tr>
				<th>
					电话：
				</th>
				<td>
					<c:out value="${requestScope.info.clPhone }"/>

				</td>

				<th>
					传真：
				</th>
				<td>
					<c:out value="${requestScope.info.clFax }"/>

				</td>
			</tr>
			<tr>
				<th>
					公司网址：
				</th>
				<td>
					<c:out value="${requestScope.info.clWebsite }"/>

				</td>

				<th>
					邮编：
				</th>
				<td>
					<c:out value="${requestScope.info.clZip }"/>

				</td>
			</tr>
			<tr>
				<th>
					QQ/MSN：
				</th>
				<td>
					<c:out value="${requestScope.info.clQq }"/>

				</td>

				<th>
					旺旺：
				</th>
				<td>
					<c:out value="${requestScope.info.clWangwang }"/>
				</td>
			</tr>
			<tr>
				<th>
					开户银行：
				</th>
				<td>
					<c:out value="${requestScope.info.clBank }"/>

				</td>

				<th>
					开户人：
				</th>
				<td>
					<c:out value="${requestScope.info.clPeople }"/>

				</td>
			</tr>
			<tr>
				<th>
					银行账号：
				</th>
				<td colspan="3">
					<c:out value="${requestScope.info.clNumber }"/>

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
			<tr>
				<th>
					创建人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.info.clCreate}"/> 

				</td>

				<th>
					创建时间：
				</th>
				<td>
				<fmt:formatDate value="${requestScope.info.clCreateTime}" pattern="yyyy-MM-dd HH:mm:ss " />

				</td>
			</tr>
			<tr>
				<th>
					修改人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.info.clUptade}"/> 

				</td>

				<th>
					修改时间：
				</th>
				<td>
				<fmt:formatDate value="${requestScope.info.clUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss " />

				</td>
			</tr>

		</table>
	</form>
</div>




