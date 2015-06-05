<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理 -- 修改文档
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
					
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">标题：</th>
				<td>
					<c:out value="${requestScope.f.title }" />
					
				</td>
			</tr>
			<tr>
				<th>附件：</th>
				<td>
					
					<c:forEach  var="f"  items="${requestScope.files }" >
						
							<c:url var="url_download" value="project/file/download.do" >
								<c:param name="name" value="${f.name }" />
								<c:param name="uuid" value="${f.uuid}" />
								<c:param name="ext" value="${f.ext}" />
							</c:url>
							<a href="${url_download }" target="_blank">
									<img src="resource/images/icons/${f.ext }.gif" 
									onerror="javascript:this.src='resource/images/icons/page.gif'" align="top" />&nbsp;<c:out value="${f.name }"/></a>
									 (<my:fileSizeFormat value="${f.size }"/>)&nbsp;&nbsp;&nbsp;&nbsp;
									
							
							
						</c:forEach>
						
				</td>
				
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					<my:scriptEscape value="${requestScope.f.content}"/>
					
				</td>
			</tr>
			
			
	
		
	</table>


</div>
										
						
					
		

	

