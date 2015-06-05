<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：文档管理--共享文档 --查询单个文件
--%>
<div style="width: 500px;margin: 0 auto;" >						
				
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">文件：</th>
				<td>
					<img src="resource/images/icons/${f.wdExt }.gif" onerror="javascript:this.src='resource/images/icons/page.gif'" align="top" />&nbsp;<c:out value="${f.wdName }"/>.<c:out value="${f.wdExt }"/>
					
						<c:url var="url_download" value="shareFiles/download.do" >
							<c:param name="newName" value="${f.wdNewName }" />
							<c:param name="name" value="${f.wdName}.${f.wdExt}" />
							<c:param name="ext" value="${f.wdExt}" />
						</c:url>
						
						<c:url var="url_inline" value="shareFiles/inline.do" >
							<c:param name="newName" value="${f.wdNewName }" />
							<c:param name="name" value="${f.wdName}.${f.wdExt}" />
							<c:param name="ext" value="${f.wdExt}" />
						</c:url>
						<a  href="${url_inline }" target="_blank" style="margin-left: 5px;color: blue;">预览</a>
						
						<a  href="${url_download }" target="_blank" style="margin-left: 5px;color: blue;">立即下载</a></td>
					
			</tr>
			<tr>
				<th>大小：</th>
				<td><my:fileSizeFormat value="${f.wdSize }"/></td>
			</tr>
			<tr>
				<th>创建人：</th>
				<td>
					<my:outTrueName id="${f.wdUserId }"/>
				</td>
			</tr>
			<tr>
				<th>创建日期：</th>
				<td>
					<fmt:formatDate value="${f.wdCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<c:out value="${f.wdDesc }"/>
				</td>
			</tr>
			
	</table>
	

</div>	
			