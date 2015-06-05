<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 
	模块：个人办公-通讯录-个人通讯录-分组设置添加   publicGroup/add.do
	--%>

<div style="width: 70%; margin: 0 auto;">

	<form action="publicGroup/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-nobordered ">
			<tr>
				<th width="80px">
					分组名称：
				</th>
				<td>
					<input type="text"  class="easyui-validatebox" required="true"  name="gaName" class="required" maxlength="50"
						size="50" />

				</td>
			</tr>
			<tr>
				<th>
					说明：
				</th>
				<td>
					<textarea class="" name="gaRemark" rows="6" cols="50" ></textarea>
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










