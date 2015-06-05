<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块:公各平台--新闻管理增加页面
--%>
<div style="width: 98%;margin: 0 auto;">
<form method="post" action="news/add.do"   onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered ">
	        <tr>
				<th style="width: 90px">标题:</th>
				<td>
					<input type="text" name="newstitle" class="easyui-validatebox"  required='true' validType="minlength[1]" maxlength="100" />
				</td>
			</tr>
			<tr>
		     <th>新闻类型:</th>
			 <td>
		          <select class="easyui-validatebox" required="true" name="typeid">
		          	<option value="">--选择新闻类型--</option>
		          	<my:outOptions type="3"/>
		          </select>
			 </td>
			</tr>
			<tr>
				<th> 评论:</th>
				<td>
					<select  name="allowcomment">
					<option value="0">允许评论</option>
					<option value="1">禁止评论</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>置顶:</th>
				<td>
					<label class="checkbox inline" ><input type="checkbox" name="Stick" value="1">使新闻置顶，显示为重要</label>
				 </td>
			</tr> 
			<!-- 
			<tr>
				<th>新闻简介:</th>
				<td>
					<textarea rows="3" cols="100" name="plotsummary" maxlength="200"></textarea>
				</td>
				</tr>
			<tr> -->
				<th>新闻内容:</th>
				<td>
					<textarea  name="newsplot" cols="100"  rows="10" class="editor" style="width: 98%;"></textarea>
				</td>
			</tr>
			<tr>
					<th></th>
					<td >
						<div  style="margin-top: 10px;margin-bottom: 10px;">
							  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
							  <button type="button" class="btn clear" >清空</button>
						</div>
					</td>
				</tr>
		</table>	
			 <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	 <input type="hidden" name="currentCallback" value="close" />
		
</form>
</div>