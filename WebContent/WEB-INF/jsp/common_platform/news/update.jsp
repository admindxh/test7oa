<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块:公各平台--新闻管理更新页面
--%>
<div style="width:98%;margin: 0 auto;">
<form method="post" action="news/update.do"   onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered">
	        <tr>
				<th style="width: 90px">标题:</th>
				<td>
					<input type="text" name="newstitle" class="easyui-validatebox" required='true' validType="minlength[1]" maxlength="100" value="<c:out value="${news.newstitle}" />"  size="50" />
				</td>
			</tr>
			<tr>
		       <th>新闻类型:</th>
		       <td>
		          <select class="easyui-validatebox" required="true"   name="typeid" sValue="${requestScope.news.typeid }">
		          	<option value="">--选择新闻类型--</option>
		          	<my:outOptions type="3"/>
		          </select>
		      </td>
			</tr>
			<tr>
				<th>评论:</th>
	            <td>
					<select    name="allowcomment" sValue="${news.allowcomment}">
						<option value="0" >允许评论</option>
						<option value="1" >禁止评论</option>
					</select>
				</td>
			</tr>
			<tr>
			<th>置顶:</th>
				<td>
					<c:choose>
						<c:when test="${news.stick==1}">
							<label class="checkbox inline" ><input type="checkbox" name="Stick" checked="checked" value="1">使新闻置顶，显示为重要</label>
						</c:when>
						<c:otherwise>
							<label class="checkbox inline" ><input type="checkbox"  name="Stick" value="1">使新闻置顶，显示为重要</label>
						</c:otherwise>
						</c:choose>
				 </td>
			</tr>
			<!-- 
			<tr>
				<th>新闻简介:</th>
				<td>
					<textarea rows="3" cols="100" name="plotsummary" ><c:out value="${news.plotsummary}" /></textarea>
				</td>
			</tr> -->
			<tr>
				<th>新闻内容:</th>
				<td>
					<textarea  name="newsplot" cols="100" rows="15" class="editor" value="" style="width: 98%;"><c:out value="${news.newsplot}" /></textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			</tr>
		</table>	
			<input type="hidden" name="status" value="${news.status }">
			<input type="hidden"  name="createdtime" value="<fmt:formatDate  value="${requestScope.news.createdtime}" pattern="yyyy-MM-dd HH:mm:ss" />"  />
			<input type="hidden" name="hits" value="${news.hits}">
			<input type="hidden" name="contents" value="${news.contents}">
			<input type="hidden" name="promulgator" value="${news.promulgator}">
			<input type="hidden" name="id" value="${requestScope.news.id }"/>
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
  			<input type="hidden" name="currentCallback" value="close" />
		

</form>
</div>