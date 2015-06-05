<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：公共平台--会议设置 -- 新增会议室
	--%>

<div style="width: 500px;margin: 0 auto;" >

	<form action="room/update.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				
				<th width="80px">
					编号：
				</th>
				<td>
					<input type="text" name="mrNo" class="easyui-validatebox" required="true" maxlength="50"
						size="50" value="${requestScope.room.mrNo }" />

				</td>
			</tr>
			<tr>
				<th>
					名称：
				</th>
				<td>
					<input type="text" name="mrName" class="easyui-validatebox" required="true" maxlength="50"
						size="50" value="${requestScope.room.mrName }"/>

				</td>
			</tr>
			<tr>
				<th>
					容纳人数：
				</th>
				<td>
					<input type="text" name="mrNum"  maxlength="50"
						size="50" value="${requestScope.room.mrNum }"/>
				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td>
					<textarea  name="mrRemark"  rows="6" cols="80">${requestScope.room.mrRemark }</textarea>
					
				</td>
			</tr>

			<input type="hidden" name="id" value="${requestScope.room.id}" />
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







