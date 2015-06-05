<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-销售管理-销售机会-查看
--%>

<div style="width: 800px; margin: 0 auto;">
	<form action="personal/update.do" method="post"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered ">
			<tr>
				<th width="80px">
					主题：
				</th>
				<td>
					<c:out value="${chance.scName }"/>

				</td>
		</tr>
			<tr>
				<th width="80px">
					客户名称：
				</th>
				<td>
					${chance.info }
					
					
				</td>
			</tr>
			<tr>
			
			<tr>
				<th width="80px">
					联系人：
				</th>
				<td>
					
					${chance.linkman }
					
					
				</td>
			</tr>
			<tr>
				<th width="80px">
					联系人电话：
				</th>
				<td>
					<c:out value="${chance.phone }"/>
				</td>
			</tr>	
			</tr>
			<tr>
				<th>
					机会来源：
				</th>
				<td>
					<my:outSelectValue id="${chance.scSource }" />

				</td>

				
			</tr>
			<tr>
				<th>
					机会状态：
				</th>
				<td>
					<my:outSelectValue id="${chance.scStatus }" />

				</td>
		</tr>
		<tr>
		<tr>
				<th>
					负责的部门：
				</th>
				<td >
						<my:outDeptName id="${chance.scBranch}"/>
				</td>
			</tr>
		</tr>
		<tr>
			<th>
					负责人：
				</th>
				<td >
						<my:outTrueName id="${chance.scStaff}"/>
				</td>			
	
		</tr>
			<tr>
				<th>分配时间</th>
				<td>
					<fmt:formatDate value="${chance.scDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<th>
					描述：
				</th>
				<td colspan="3">
					<c:out  value="${chance.scPicture }"/>
				</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td> <my:outTrueName id="${chance.scCreate}"/></td>
			</tr>
			<tr>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.scCreateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</table>
	</form>
</div>




