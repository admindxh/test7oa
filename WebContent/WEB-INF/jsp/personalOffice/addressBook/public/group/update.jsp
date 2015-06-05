<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-通讯录-公共通讯录-分组设置修改  
	--%>

<div style="width: 70%; margin: 0 auto;">

	<form action="publicGroup/update.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-nobordered ">
			<tr>
				<th width="80px">
					名称：
				</th>
				<td>
					<input type="text" class="easyui-validatebox" required="true"  name="gaName"  maxlength="50"
						size="50" value="<c:out value="${requestScope.gro.gaName }"/>"/>
				</td>
			</tr>
			<tr>
				<th>
					说明：
				</th>
				<td>
						<textarea class="" name="gaRemark" rows="6" cols="50" ><c:out value="${requestScope.gro.gaRemark }"/></textarea>
				</td>
			</tr>
			
			<input type="hidden" name="id" value="${requestScope.gro.id}" />
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
			<input type="hidden" name="currentCallback" value="close" />

			<tr>
				<th>
				</th>
				<td>
					<div style="margin-top: 10px; margin-bottom: 10px;">
						<button type="submit" class="btn btn-primary">
							保存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn clear">
							清空
						</button>
					</div>
				</td>
			</tr>


		</table>
	</form>
</div>








