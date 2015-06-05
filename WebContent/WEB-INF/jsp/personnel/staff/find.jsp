<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理--人员管理-查看
--%>
<div style="width:900px;margin: 0 auto;" >

	<form action=" staff/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-nobordered " >	
			<tr>
				<th width="80px">
					姓名：
				</th>
				<td>
					<c:out value="${requestScope.ps.psName }"/>

				</td>
			
				<th>
					性别：
				</th>
				<td>
				
				   <c:out value="${requestScope.ps.psSex}"/>
				
				
				</td>
			</tr>
			<tr>
				<th>
					出生日期：
				</th>
				<td>
				<fmt:formatDate  value="${requestScope.ps.psBirthday}" pattern="yyyy-MM-dd" />

				</td>
			
				<th>
					年龄：
				</th>
				<td>
					<c:out value="${requestScope.ps.psAge }"/>

				</td>
			</tr>
			<tr>
				<th>
					现居住地：
				</th>
				<td>
					<c:out value="${requestScope.ps.psPresentAddress }"/>

				</td>
			
				<th>
					住宅电话：
				</th>
				<td>
				<c:out value="${requestScope.ps.psHomePhone }"/>

				</td>
			</tr>
			<tr>
				<th>
					手机号码：
				</th>
				<td>
					<c:out value="${requestScope.ps.psPhone }"/>

				</td>
			
				<th>
					其他联系：
				</th>
				<td>
				<c:out value="${requestScope.ps.psOther }"/>

				</td>
			</tr>

			<tr>
				<th>
					户口所在地：
				</th>
				<td>
					<c:out value="${requestScope.ps.psAccountAddress }"/>

				</td>
			
				<th>
					民族：
				</th>
				<td>
					<c:out value="${requestScope.ps.psEthnic }"/>

				</td>
			</tr>


			<tr>
				<th>
					身份证号：
				</th>
				<td>
					<c:out value="${requestScope.ps.psCard }"/>

				</td>
			
				<th>
					籍贯：
				</th>
				<td>
					<c:out value="${requestScope.ps.psHomeTown }"/>

				</td>
			</tr>
			<tr>
				<th>
					用户账号：
				</th>
				<td>

					<my:outTrueName id="${requestScope.ps.psUserId}"/>
					



				</td>
			
				<th>
					员工编号：
				</th>
				<td>
					<c:out value="${requestScope.ps.psStaffId }"/>

				</td>
			</tr>
			<tr>
				<th>
					工资卡号：
				</th>
				<td>
					<c:out value="${requestScope.ps.psWage }"/>

				</td>
		
				<th>
					社保号：
				</th>
				<td>
					<c:out value="${requestScope.ps.psSocial }"/>

				</td>
			</tr>
			<tr>
				<th>
					最高学历：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.ps.psEdu }"/>
					
				</td>
			
				<th>
					所学专业：
				</th>
				<td>
					<c:out value="${requestScope.ps.psSpecialty }"/>

				</td>
			</tr>
			<tr>
				<th>
					毕业院校：
				</th>
				<td>
					<c:out value="${requestScope.ps.psSchool}"/>

				</td>
			
				<th>
					毕业时间：
				</th>
				<td>
					<fmt:formatDate value="${requestScope.ps.psGraduation}" pattern="yyyy-MM-dd" />

				</td>
			</tr>


			<tr>
				<th>
					员工类型：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.ps.psType }"/>
						

				</td>
			
				<th>
					员工职位：
				</th>
				<td>
					<my:outSelectValue id="${requestScope.ps.psPost }"/>
					

				</td>
			</tr>
			<tr>
				<th>
					入职时间：
				</th>
				<td>
					<fmt:formatDate value="${requestScope.ps.psEntryDate}" pattern="yyyy-MM-dd" />
				</td>
		
				<th>
					员工状态：
				</th>
				<td>
					
					<c:out value="${requestScope.ps.psStatus }"/>
				</td>
			</tr>
			  <c:if test="${requestScope.ps.psStatus=='在职' }">
				
				</c:if>
				  <c:if test="${requestScope.ps.psStatus=='离职' }">
					<tr>
								<th>
									离职原因：
								</th>
								<td>
									<my:outSelectValue id="${requestScope.ps.psResReason }"/>
										
								</td>
							
								<th>
									离职时间：
								</th>
								<td>
									<fmt:formatDate value="${requestScope.ps.psResDate}" pattern="yyyy-MM-dd" />
							
								</td>
							</tr>
							<dl class="nowrap">
								<th>
									备注：
								</th>
								<td>
									<c:out value="${requestScope.ps.psResRemark}"/>
								</td>
							</tr>
				</c:if>
				 <c:if test="${requestScope.ps.psStatus=='退休' }">
					<tr>
								<th>
									退休原因：
								</th>
								<td>	
									<my:outSelectValue id="${requestScope.ps.psRetReason }"/>
								
								</td>
							
								<th>
									退休时间：
								</th>
								<td>
									<fmt:formatDate value="${requestScope.ps.psRetDate}" pattern="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td>
									<c:out value="${requestScope.ps.psRetRemark}"/>
								</td>
							</tr>
				</c:if>
				
			

		</div>




	</form>
</div>




