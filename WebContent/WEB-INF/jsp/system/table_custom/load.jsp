<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--导出打印自定义管理--查询
--%>

<div style="width: 98%;margin: 0 auto;">
	<table class="table table-bordered table-striped center"  >
		<thead>
			<tr>
				<th width="30" >顺序号</th>
				<th width="140"  >名称</th>
				<th width="140"  >别名</th>
				<th width="80" >类型</th>
				<th width="60"  >导出</th>
				<th width="60"  >打印</th>
				<%--<th width="60"  >显示</th>
				--%><th width="50" >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${requestScope.ts }" >
				
				<tr >
					<td style="text-align: center;">${m.fieldSort }</td>
					<td style="text-align: center;"><c:out value="${m.fieldTitle }" /></td>
					<td style="text-align: center;"><c:out value="${m.fieldAnotherTitle }" /></td>
					<td style="text-align: center;">
						<c:choose>
							<c:when test="${m.fieldType==1}">文本</c:when>
							<c:when test="${m.fieldType==2}">数字</c:when>
							<c:when test="${m.fieldType==3}">日期</c:when>
							<c:when test="${m.fieldType==4}">日期</c:when>
							<c:when test="${m.fieldType==5}">文本</c:when>
							<c:when test="${m.fieldType==6}">文本</c:when>
							<c:when test="${m.fieldType==7}">文本</c:when>
						</c:choose>
					</td>
					<td style="text-align: center;">
						<c:choose>
							<c:when test="${m.isExport==1}">
								 <img src="resource/images/small/Check_16x16.png" alt="是" title="是"  style="margin-top: 2px;"/>
							</c:when>
							<c:otherwise>
								<img src="resource/images/small/Delete_16x16.png" alt="否" title="否" style="margin-top: 2px;"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align: center;">
						<c:choose>
							<c:when test="${m.isPrint==1}">
								<img src="resource/images/small/Check_16x16.png" alt="是" title="是"  style="margin-top: 2px;"/>
							</c:when>
							<c:otherwise>
								<img src="resource/images/small/Delete_16x16.png" alt="否" title="否" style="margin-top: 2px;"/>
							</c:otherwise>
						</c:choose>
					</td>
					<%--<td>
						<c:choose>
							<c:when test="${m.isShow==1}">
								<img src="resource/images/small/Check_16x16.png" alt="是" title="是"  style="margin-top: 2px;"/>
							</c:when>
							<c:otherwise>
								<img src="resource/images/small/Delete_16x16.png" alt="否" title="否" style="margin-top: 2px;"/>
							</c:otherwise>
						</c:choose>
					</td>
					--%><td style="text-align: center;"><a href="tableCustom/updatePage.do?id=${m.id }&rel=${param.rel}" target="dialog" width="800" height="400" rel="${param.rel}_update" title="修改导入导出设置">修改</a></td>
				</tr>
				
			</c:forEach>
			
			
			
			
		</tbody>
	</table>
			
</div>
