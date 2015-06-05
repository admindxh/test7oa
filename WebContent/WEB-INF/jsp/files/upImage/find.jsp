	<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：图片上传管理--查看
--%>

<div style="width: 900px; margin: 0 auto;">
	<form action="" >

		<table class="table table-bordered ">
			<tr>
				<th width="100px">
					标题：
				</th>
				<td>
					<c:out value="${requestScope.img.title }" />

				</td>

				<th width="100px">
					地址：
				</th>
				<td>
					<c:out value="${requestScope.img.address }" />
				</td>
			</tr>
			<tr>

				<th>
					经度：
				</th>
				<td>
					<c:out value="${requestScope.img.longitude }" />
				</td>
				<th>
					纬度：
				</th>
				<td>
					<c:out value="${requestScope.img.latitude }" />
				</td>
			</tr>
			<tr>
				<th>
					上传时间：
				</th>
				<td>
					${requestScope.img.uptime }
				</td>

				<th>
					创建人：
				</th>
				<td>
					<my:outTrueName id="${requestScope.img.createuser }"/>
				</td>
			</tr>
			<tr>
				<th>
					创建时间：
				</th>
				<td colspan="3"> 
					<fmt:formatDate value="${requestScope.img.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>
					内容：
				</th>
				<td colspan="3">
					<c:out value="${requestScope.img.content }"/>
				</td>
			</tr>
			<c:choose>
          		<c:when test="${fn:length(infolist)!=0}">
          			<c:forEach var="m" items="${infolist}">
          			 <tr>
	          			 <th>
							图片：
						 </th>
          			 	<td>
          			 		<img  src="${savePath}/${m.fileNewName }${m.fileExt }" />
          			 	</td>
          			 	<th>
							大小：
						 </th>
          			 	<td>
          			 		<c:out value="${m.fileSize }"></c:out>
          			 	</td>
          			 </tr>
          			</c:forEach>
          		</c:when>
          		<c:otherwise>
          		<tr>
          			<th>
						图片：
					</th>
				    <td colspan="3">暂无图片信息</td>
				</tr>
          		</c:otherwise>
	        </c:choose>
		</table>
	</form>
</div>
