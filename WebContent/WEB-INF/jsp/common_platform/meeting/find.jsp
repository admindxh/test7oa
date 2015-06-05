<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公共平台--会议管理 -- 查看会议详情
--%>

<div style="width: 1000px;margin: 0 auto;" >
	<table class="table table-bordered " >	
			<tr>
			
				<th width="80px">
					类型：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.meeting.MType }"/>
					
				</td>
			</tr>
			<tr>
				<th>
					标题：
				</th>
				<td>
				<c:out value="${requestScope.meeting.MName}" />
				</td>
			</tr>
			<tr>
				<th>
					主持人：
				</th>
				<td>
					<c:out value="${requestScope.meeting.MHost}" />

				</td>
			</tr>
			<tr>
				<th>
					会议日期：
				</th>
				<td>
					<fmt:formatDate value="${requestScope.meeting.MDate}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>
					开始时间：
				</th>
				<td>
					<c:out value="${requestScope.meeting.MStatime}" />
				</td>
			</tr>
			<tr>
				<th>

					结束时间：
				</th>
				<td>
				<c:out value="${requestScope.meeting.MEndtime}" />
				</td>
			</tr>
			<tr>
				<th>
					会议室：
				</th>
				<td>
						<c:out value="${requestScope.rName}" />

				</td>
			</tr>
			<tr>
				<th>
					参与部门：
				</th>
				<td>
					<my:outDeptName id="${requestScope.deptIds}"/>
				</td>
			</tr>
			<tr>
				<th>
					参与人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.userIds}"/>
				</td>
			</tr>
			<tr>
				<th>
					简介：
				</th>
				<td>
					<c:out value="${requestScope.meeting.MSummary }" />
				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td>
					<my:scriptEscape value="${requestScope.meeting.MRemark }"/>
				</td>
			</tr>



</table>
</div>







