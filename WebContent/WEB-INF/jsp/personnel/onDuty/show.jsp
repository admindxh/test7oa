<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-值班管理-值班记录详情
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
 					
	<table class="table table-bordered ">
				<tr>
				<th style="width: 120px">值班人：</th>
				<td>
				<my:outTrueName id='${requestScope.o.userid}'/>
  				</td>
			</tr>
			<tr>
				<th>值班时间：</th>
				<td>
				<fmt:formatDate value="${requestScope.o.starttime }" pattern="yyyy-MM-dd HH:mm:ss" />
				--<fmt:formatDate value="${requestScope.o.endtime }" pattern="yyyy-MM-dd HH:mm:ss" />
			 
				</td> 
			</tr>
			<tr>
				<th style="width: 80px">标题：</th>
				<td>
				<c:out value="${requestScope.o.title}" />
				</td>
			</tr>
			<tr>
				<th>通知类型：</th>
				<td>
					<select  name="type"  sValue="${requestScope.o.type}">
						<my:outOptions type="21"/>
					</select>
				</td>
			</tr> 
			<tr>
				<th>附件：</th>
				<td>
				
				
				
				
				 <div class="easyui-tab"  title="附件" 	style="padding:2px; ">
			
					
						
		<c:forEach  var="f"  items="${requestScope.fs }" >
			
			<c:url var="url_download" value="onDuty/download.do" >
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
				 
				 
				 
				</td>
				
			</tr>
		
			<tr>
				<th>内容：</th>
				<td>
				<div style="margin:10px;" >
				<my:scriptEscape value="${requestScope.o.content}"/>
				</div>
				</td>
			</tr>
		 
			
	</table>
		 
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	 
</div>
										
						
					