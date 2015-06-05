<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：新闻管理--新闻回复评论
--%>
<div style="width: 98%;margin: 0 auto;">
<form method="post" action="news/answernewscomment.do"   onsubmit="return validateSubmitForm(this)">
	<table class="table table-bordered ">
		<tr>
			<td><textarea  name="newscomments"  rows="5" class="editor" required='true' style="width: 98%;"></textarea></td>
		</tr>
		<tr>
			
			<td >
				<div  style="width: 200px;margin: 0 auto;">
					 <button type="submit" class="btn btn-primary" >评论</button>&nbsp;&nbsp;&nbsp;&nbsp;
					 <button type="button" class="btn clear" >清空</button>
				</div>
			</td>
		</tr>
		<input type="hidden"  name="newsid" value=" ${param.id}">
        <input type="hidden" name="bycommentnameId" value="${param.conmmentsname}">
        <input type="hidden" name="otherCallback" value="news/contents.do?id=${param.id}&rel=${param.rel }" />
        <input type="hidden" name="currentCallback" value="close" />
        <input type="hidden" name="otherBoxId" value="${param.rel}_pl" />
	</table>
</form>
</div>