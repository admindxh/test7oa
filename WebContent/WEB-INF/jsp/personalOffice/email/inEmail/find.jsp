<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-内部邮件-回复邮件
--%>
<div style="width: 99%; margin: 0 auto;">

	<table class="table table-bordered ">
		<tr>
			<th width="80px">
				发件人：
			</th>
			<td>
				<my:outTrueName id="${email.emInbox.eiUid }" />
			</td>
		</tr>
		<tr>
			<th>
			发件时间：
			</th>
			<td>
				<fmt:formatDate value="${email.emInbox.eiDate}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
		</tr>
		<tr>
			<th>
			标题：
			</th>
			<td>
				<c:out value="${email.emInbox.eiName }" />

			</td>
		</tr>
		<c:if test="${size>0}">
			<tr>
				<th>
				附件：
				</th>


				<td>

					<c:forEach var="f" items="${requestScope.emFile }">

						<c:url var="url_download" value="email/download.do">
							<c:param name="newName" value="${f.efNewName }" />
							<c:param name="name" value="${f.efName}" />
							<c:param name="ext" value="${f.efExt}" />
						</c:url>
						<div style="margin-top: 5px; margin-left: 10px;">
							<img src="resource/images/icons/${f.efExt }.gif"
								onerror="javascript:this.src='resource/images/icons/page.gif'"
								align="middle" />
							<a href="${url_download }" target="_blank"> <c:out
									value="${f.efName }" />
							</a>
						</div>

					</c:forEach>

				</td>
			</tr>
		</c:if>
		<tr>
		<th>内容：</th>
		<td>
		
			<my:scriptEscape value="${email.emInbox.eiDetail }" />
		
		</td>
		</tr>
		<input type="hidden" name="id" value="${email.emInbox.id}" />
		<input type="hidden" name="currentCallback" value="close" />
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />


		<tr>
		<th></th>

			<td>
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<button type="submit" class="btn btn-primary" title="回复"
						href="email/addPageIn.do?id=${email.emInbox.id}" target="navTab"
						rel="${param.rel}"><span>回复</span>
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary"  title="转发"
						href="email/forwardIn.do?id=${email.emInbox.id}" target="navTab"
						rel="${param.rel}"><span>转发</span>
					</button>
				</div>
			</td>
		</tr>



	</table>
</div>







