<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：个人办公-通讯录-个人通讯录-分组设置添加   
	--%>

<div style="width: 100%; margin: 0 auto;">

	<form action="group/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-nobordered ">
<%--			<tr>--%>
<%--				<th width="80px">--%>
<%--					编号：--%>
<%--				</th>--%>
<%--				<td>--%>
<%--					<input type="text" name="grNo" class="easyui-linkbutton" required="true"  maxlength="50"--%>
<%--						size="50" />--%>
<%----%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr>
				<th>
					分组名称：
				</th>
				<td>
					<input type="text" name="grName"  maxlength="50"  class="easyui-validatebox" required="true" 
						size="50" />

				</td>
			</tr>
			<tr>
				<th>
					说明：
				</th>
				<td>
					<textarea class="" name="grRemark" rows="6" cols="50"></textarea>
				</td>
			</tr>


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












