<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-内部邮件-发件箱 
--%>
<div style="width: 99%;margin: 0 auto;" >

	<form action="email/addDraft.do"  enctype="multipart/form-data" method="post" 
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">收件人：</th>
				<td>
					<my:outTrueName id="${inUid }"/>
				</td>
			</tr>
			
			<tr>
				<th>标题：</th>
				<td>
					<c:out value="${email.emOutbox.eoName }"/>
				</td>
			</tr>
		
			
					
				<c:if test="${size>0}">
				<tr>
					<th>附件：</th>
					<td>
						
							<c:forEach  var="f"  items="${requestScope.emFile }" >
								
								<c:url var="url_download" value="email/download.do" >
									<c:param name="newName" value="${f.efNewName }" />
									<c:param name="name" value="${f.efName}" />
									<c:param name="ext" value="${f.efExt}" />
								</c:url>
									<div style="margin-top: 5px;margin-left: 10px;" >
										<img src="resource/images/icons/${f.efExt }.gif" onerror="javascript:this.src='resource/images/icons/page.gif'" align="middle" />
										<a href="${url_download }" target="_blank">
										
										<c:out value="${f.efName }"/></a>
									</div>
							</c:forEach>
						</td>	
					</tr>
			</c:if>
			
		<tr>
		<th>内容：</th>
		<td>
		<my:scriptEscape value="${email.emOutbox.eoDetail }"/>
		
		</td>
		</tr>	
		
			
			<input type="hidden" name="edId" value="${email.emOutbox.id}">
				<input type="hidden" name="currentCallback" value="close" />
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />


		
	
<tr>
		<th></th>

			<td>
				<div style="margin-top: 10px; margin-bottom: 10px;">
				<button type="submit" class="btn btn-primary" title="编辑发送"
						href="email/forwardOut.do?id=${email.emOutbox.id}" target="navTab"
						rel="${param.rel}_find"><span>编辑发送</span>
					</button>
				</div>
			</td>
		</tr>



	</table>
</div>								
						
					
		

	

