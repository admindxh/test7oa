<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--导出打印自定义管理--更新
--%>

					
<div style="width: 600px;margin: 0 auto;" >			
	
	<form method="post" action="tableCustom/update.do"   onsubmit="return validateSubmitForm(this)">
	
		<table class="table table-bordered " >			
			<tr>
				<th style="width: 80px">名称：</th>
				<td><c:out value="${requestScope.tc.fieldTitle }" /></td>
			
			</tr>
			
			<tr>
				<th>顺序号：</th>
				<td>
					<input type="text" name="fieldSort" maxlength="3" class="required digits" min="1" max="999" size="25" value="${requestScope.tc.fieldSort }"/>
					<br/><span class="text-info">用于打印，导出的排序，值越小越靠前(数值范围：1-999 整数)</span>
				</td>
			</tr>
			<tr>
				<th>别名：</th>
				<td>
					<input  type="text" name="fieldAnotherTitle"   maxlength="20" size="25" value="<c:out value="${requestScope.tc.fieldAnotherTitle }" />"/>
					<br/><span class="text-info">自定义的别名，如果不设置则显示默认名称</span>
				</td>
			</tr>
			<tr>
				<th>是否导出：</th>
				<td>
					<select   name="isExport" sValue="${requestScope.tc.isExport }">
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>是否打印：</th>
				<td>
					<select  name="isPrint" sValue="${requestScope.tc.isPrint }">
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
				</td>
			</tr>
			<%--<tr>
				<th>是否显示：</th>
				<td>
					<select name="isShow" sValue="${requestScope.tc.isShow }">
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
				</td>
			</tr>
			--%>
			<tr>
				<th>
				</th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
					 	 <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
						 <button type="button" class="btn clear" >清空</button>
					 </div>
				</td>
			</tr>
	 </table>
	 <input type="hidden" name="id" value="${requestScope.tc.id }"/>
	 <input type="hidden" name="currentCallback" value="close" />
	 <input type="hidden" name="otherBoxId" value="${param.rel }" />
	 <input type="hidden" name="otherCallback" value="refresh" />
</form>
</div>	
					
		

	

