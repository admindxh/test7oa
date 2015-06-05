<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息--历史消息
--%>

<div style="width:99%;margin: 10px auto;">
	<ul>
		<c:forEach var="m" items="${requestScope.list }" >
			<li style="background: none repeat scroll 0 0 #FFFFFF;
				    cursor: pointer;
				    float: left;
				    height: 180px;
				    list-style: none outside none;
				    margin: 10px;
				    overflow: hidden;
				    text-align: center;
				    width: 240px;
				    
				    ">
				<a href="imageSpace/show.do?id=${m.id }" target="dialog" width="900" height="450" rel="imageSpace_show" title="预览图片">
					<img src="${m.savePath }" title="${m.name }" style="max-width:240px ;max-height: 180px"/>
				</a>
				
			</li>	
		</c:forEach>
	</ul>
</div>



