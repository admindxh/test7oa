<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-人员合同-查看                      
--%>

<div style="width:99%;margin: 0 auto;" >

	
		<table class="table table-bordered " >	
			<tr>
				<th width="90px">
					姓名：
				</th>
				<td >
					<my:outTrueName id="${deal.pdUid}"/>
					
				</td>
			
				<th width="90px">
					证件号码：
				</th>
				<td>
					<c:out value="${deal.pdIdNo }" />
				</td>
			</tr>
			<tr>
				<th >
					合同编号：
				</th>
				<td>
					<c:out value="${deal.pdNumber }" />
				</td>
			
				<th>
					合同名称：
				</th>
				<td>
					<c:out value="${deal.pdName }" />
				</td>
			</tr>
			<tr>
				<th >
					合同类型：
				</th>
				<td>
					<my:outSelectValue id="${deal.pdType }" />
				</td>
			
				<th>
					合同状态：
				</th>
				<td>
			<my:outSelectValue id="${deal.pdStatus }" />
				</td>
			</tr>
			
			<tr>
				<th >
					签订日期：
				</th>
				<td>
					<fmt:formatDate  value="${deal.pdSignDate}" pattern="yyyy-MM-dd" />
				</td>
			
				<th>
					签订次数：
				</th>
				<td>
					<c:out value="${deal.pdSignNumber }"  />
				</td>
			</tr>
			
			<tr>
				<th >
					合同生效日期：
				</th>
				<td>
					<fmt:formatDate  value="${deal.pdStart}" pattern="yyyy-MM-dd" />
				</td>
			
				<th>
					合同结束日期：
				</th>
				<td>
				<fmt:formatDate  value="${deal.pdEnd}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			
			<tr>
				<th>
					合同内容：
				</th>
				<td colspan="3">
					<my:scriptEscape value="${deal.pdContent}" />
				</td>
			</tr>
			
			
				<c:if test="${size>0}">
				<tr>
					<th>附件：</th>
					<td colspan="3">
						
							<c:forEach  var="f"  items="${file }" >
								
								<c:url var="url_download" value="deal/download.do" >
									<c:param name="newName" value="${f.peNewName}" />
									<c:param name="name" value="${f.peName}" />
									<c:param name="ext" value="${f.peExt}" />
								</c:url>
									<div style="margin-top: 5px;margin-left: 10px;" >
										<img src="resource/images/icons/${f.peExt }.gif" onerror="javascript:this.src='resource/images/icons/page.gif'" align="middle" />
										<a href="${url_download }" target="_blank">
										
										<c:out value="${f.peName }"/></a>
									</div>
							</c:forEach>
						</td>	
					</tr>
			</c:if>
					
				
			
			
			<tr>
				<th >
					操作人：
				</th>
				<td>
					<my:outTrueName id="${deal.pdStaffId}"/>
				</td>
			
				<th>
					操作时间：
				</th>
				<td>
					<fmt:formatDate  value="${deal.pdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					
				</td>
			</tr>
			

		</table>
	
</div>


