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
					活动主题：
				</th>
				<td>
					<c:out value="${requestScope.ac.acTitle }"/>

				</td>

				<th width="100px">
					活动方式：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.ac.acType }"/>
				</td>
			</tr>
			<tr>

				<th>
					活动时间：
				</th>
				<td>
					<fmt:formatDate value="${requestScope.ac.acTime }"  pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<th>
					客户(单位)：
				</th>
				<td>
					<c:out value="${requestScope.info.clName}"></c:out>
				</td>
			</tr>
			<tr>
				<th>
					联系人：
				</th>
				<td>
					<c:out value="${requestScope.ac.acContacts }"/>
				</td>

				<th>
					机会名称：
				</th>
				<td>
					<c:out value="${requestScope.ch.scName }"/>
				</td>
			</tr>
			<tr>
				<th>
					活动负责人：
				</th>
				<td>
				<c:out value="${requestScope.ac.acOurStaff }"/>

				</td>
				<th>
					重要：
				</th>
				<td>
					<c:if test="${requestScope.ac.acImportance==0}">一般</c:if>
					<c:if test="${requestScope.ac.acImportance==1}">重要</c:if>
				  	<c:if test="${requestScope.ac.acImportance==2}">非常重要</c:if>
				</td>
			</tr>
			<tr>
				<th>
					活动详情：
				</th>
				<td colspan="3">
					<c:out value="${requestScope.ac.acDetail }"/>
				</td>
				
			</tr>
			<tr>
				<th>
					活动地点：
				</th>
				<td>
					<c:out value="${requestScope.ac.acPlace }"/>
				</td>

				<th>
					持续时间：
				</th>
				<td>
					<c:out value="${requestScope.ac.acDuration }"/>&nbsp;(小时)
				</td>
			</tr>
			<tr>
				<th>
					赠送礼品：
				</th>
				<td>
					<c:out value="${requestScope.ac.acGiveGift }"/>			
				</td>
				<th>
					礼品数量：
				</th>
				<td>
					<c:out value="${requestScope.ac.acGiftNum }"/>
				</td>
			</tr>
			<tr>
				<th>
					花费：
				</th>
				<td>
					<fmt:formatNumber value="${requestScope.ac.acSpend}"  pattern="#0.00" ></fmt:formatNumber>
				</td>
				<th>
					创建人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.ac.acCreateMan }"/>
				</td>
			</tr>
			<tr>
				<th>
					创建时间：
				</th>
				<td colspan="3">
					<fmt:formatDate value="${requestScope.ac.acCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</table>
	</form>
</div>




