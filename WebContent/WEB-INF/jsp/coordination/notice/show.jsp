<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--通知管理--详情
--%>

<div class="easyui-tabs" fit="true" border="false" >
	
	<div class="easyui-tab"  title="通知" 	style="padding:2px; " >
			
			<div class="div_titlebold" ><c:out value="${requestScope.n.noticeTitle}" /></div>
			<div class="div_sendUser">发送人：<my:outTrueName id="${requestScope.n.noticeSendId }"/> &nbsp;&nbsp;<fmt:formatDate value="${n.noticeCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" />	</div>	
			
			<div class="divider"></div>
				
			<div style="margin:10px;" >
				<my:scriptEscape value="${requestScope.nc.noticeContent}"/>
			</div>
			
	</div>
	
	<div class="easyui-tab"  title="附件" 	style="padding:2px; ">
			
					
						
		<c:forEach  var="f"  items="${requestScope.nfs }" >
			
			<c:url var="url_download" value="notice/download.do" >
				<c:param name="newName" value="${f.fileNewName }" />
				<c:param name="name" value="${f.fileName}" />
				<c:param name="ext" value="${f.fileExt}" />
			</c:url>
			<div style="margin-top: 10px;margin-left: 20px;" ><a href="${url_download }" target="_blank">
					<img src="resource/images/icons/${f.fileExt }.gif" 
					onerror="javascript:this.src='resource/images/icons/page.gif'" align="top" />&nbsp;<c:out value="${f.fileName }"/>&nbsp;&nbsp;&nbsp;下载</a>
					 &nbsp;&nbsp;<my:fileSizeFormat value="${f.fileSize }"/>
					
			</div>
			<div class="divider"></div>
		</c:forEach>
											
							
					
					
				
			
	
	</div>
	
	<div class="easyui-tab"  title="查阅情况" 	style="padding:2px; ">
			
						
			<table class="table table-bordered table-striped center"  style="width: 800px;margin: 5px auto;">
				<thead>
					<tr>
						<th style="text-align: center;">接收人员</th>
						<th style="text-align: center;" width="170" >阅读状态(${requestScope.readNum }/${requestScope.allNum })</th>
						<th style="text-align: center;" width="170" >阅读时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="nr" items="${requestScope.nrs }" >
						<tr>
							<td style="text-align: center;">
								<my:outTrueName id="${nr.userId }"/>
							</td>
							<td style="text-align: center;">
		
								<c:choose>
									<c:when test="${empty nr.readTime }">
										 <img src="resource/images/email_close.gif" alt="未读"  style="margin-top: 3px;"/>
									</c:when>
									<c:otherwise>
										<img src="resource/images/email_open.gif" alt="已读" style="margin-top: 3px;"/>
									</c:otherwise>
								</c:choose>
							
							</td>
							<td style="text-align: center;">
								<fmt:formatDate value="${nr.readTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
	
			
	
	</div>
	
	
</div>





