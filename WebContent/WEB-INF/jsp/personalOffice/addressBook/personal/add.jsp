<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	模块：个人办公-通讯录-个人通讯录 
--%>

<div style="width:800px;margin: 0 auto;" >

	<form action=" personal/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">
					姓名：
				</th>
				<td>
					<input type="text" name="peName" class="easyui-validatebox" required="true" maxlength="50"
						size="50" />

				</td>
			
				<th width="80px">
					 性别：
				</th>
				<td>
					<input type="radio" name="peSex" checked="checked" value="男"/>男
					<input type="radio" name="peSex" value="女"/>女
				</td>
			</tr>
			<tr>
				<th>
					电子邮箱：
				</th>
				<td>
					<input type="text" name="peEmail"  class=" email" maxlength="50"
						size="50" />

				</td>
			
				<th>
					手机：
				</th>
				<td>
					<input type="text" class="phone"  name="pePhone" maxlength="11" size="50"/>
			</tr>
			<tr>
				<th>
					其他联系：
				</th>
				<td>
				<input type="text" name="peOther" maxlength="11" size="50" />

				</td>
			
				<th>
					分组：
				</th>
				<td>
				<select class="easyui-validatebox" required="true" name="peGrouping">
					<c:forEach var="g" items="${group}">
						<option value="${g.id }">${g.grName }</option>
					</c:forEach>
				</select>

					

				</td>
			</tr>
			<tr>
				<th>
					公司名称：
				</th>
				<td>
				<input type="text" name="peCompany" size="50" />

				</td>
			
				<th>
					办公电话：
				</th>
				<td>
				<input type="text" name="peOfficPhone"  size="50" />

				</td>
			</tr>
			
			<tr>
				<th>
					办公传真：
				</th>
				<td>
				<input type="text" name="peOfficFax"  size="50" />

				</td>
			
					<th>
					公司地址：
				</th>
				<td>
				<input type="text" name="peCompanyAddress"  size="50" />

				</td>
			</tr>
			<tr>
					<th>
					邮政编码：
				</th>
				<td>
				<input type="text" name="peZip"  size="50" />

				</td>
			
					<th>
					职位：
				</th>
				<td>
				<input type="text" name="pePost"  size="50" />

				</td>
			</tr>
			<tr>
					<th>
					家庭地址：
				</th>
				<td>
				<input type="text" name="peHomeAddress"  size="50" />

				</td>
			
					<th>
					家庭电话：
				</th>
				<td>
				<input type="text" name="peHomePhone"  size="50" />

				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td colspan="3">
					<textarea  name="peRemark" rows="6" cols="100" style="width: 608px"></textarea>
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






