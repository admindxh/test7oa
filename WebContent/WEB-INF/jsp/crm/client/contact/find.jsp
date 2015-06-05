<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户管理-联系人信息-查看
--%>

<div style="width: 800px; margin: 0 auto;">
	<form action="personal/update.do" method="post"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered ">
			<tr>
				<th width="100px">
					姓名：
				</th>
				<td>
					<c:out value="${requestScope.man.liName }"/>

				</td>

				<th width="100px">
					性别：
				</th>
				<td>
					<c:out value="${requestScope.man.liSex }"/>
				</td>
			</tr>
			<tr>

				<th>
					类型：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.man.liType }" />
				</td>
				<th>
					种类：
				</th>
				<td>
				<my:outSelectValue id="${requestScope.man.liSpecies }" />

				</td>
			</tr>
			<tr>
				<th>
					客户名称：
				</th>
				<td>
					<c:out value="${requestScope.info.clName }"/>
				</td>

				<th>
					负责业务：
				</th>
				<td>
					<c:out value="${requestScope.man.liBusiness }"/>
				</td>
			</tr>
			<tr>
				<th>
					部门：
				</th>
				<td>
				<c:out value="${requestScope.man.liDept }"/>

				</td>
				<th>
					职务：
				</th>
				<td>
				<c:out value="${requestScope.man.liPost }"/>
				</td>
			</tr>
			<tr>
				<th>
					电话：
				</th>
				<td>
					<c:out value="${requestScope.man.liTelephone }"/>
				</td>
				<th>
					称谓：
				</th>
				<td>
					<c:out value="${requestScope.man.liAppellation }"/>
				</td>
			</tr>
			<tr>
				<th>
					邮箱：
				</th>
				<td>
					<c:out value="${requestScope.man.liEmail }"/>
				</td>

				<th>
					传真：
				</th>
				<td>
					<c:out value="${requestScope.man.liFax }"/>
				</td>
			</tr>
			<tr>
				<th>
					其他联系方式：
				</th>
				<td>
					<c:out value="${requestScope.man.liMobilePhone }"/>			
				</td>
				<th>
					MSN/QQ：
				</th>
				<td>
					<c:out value="${requestScope.man.liMsnQq }"/>
				</td>
			</tr>
			<tr>
				<th>
					出生日期：
				</th>
				<td>
					<fmt:formatDate value="${requestScope.man.liBirthday}" pattern="yyyy-MM-dd" />
				</td>

				<th>
					邮编：
				</th>
				<td>
					<c:out value="${requestScope.man.liZipCode}"/>

				</td>
			</tr>
			<tr>
				<th>
					家庭住址：
				</th>
				<td>
					<c:out value="${requestScope.man.liHomeaddress }"/>

				</td>

				<th>
					家庭电话：
				</th>
				<td>
					<c:out value="${requestScope.man.liHomephone }"/>
				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td colspan="3">
					<c:out value="${requestScope.man.remark }" />
				</td>
			</tr>
			<tr>
				<th>
					创建人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.man.createMan}"/> 
				</td>
				<th>
					创建时间：
				</th>
				<td>
				<fmt:formatDate value="${requestScope.man.createtime}" pattern="yyyy-MM-dd HH:mm:ss " />
				</td>
			</tr>
			<tr>
				<th>
					修改人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.man.updateMan}"/> 
				</td>
				<th>
					修改时间：
				</th>
				<td>
					<fmt:formatDate value="${requestScope.man.updatetime}" pattern="yyyy-MM-dd HH:mm:ss " />
				</td>
			</tr>
		</table>
	</form>
</div>




