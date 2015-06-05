<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	模块：个人办公-通讯录-公司通讯录 
--%>

<div style="width: 800px;margin: 0 auto;" >

	<form action=" public/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">
					姓名：
				</th>
				<td>
					<input type="text" class="easyui-linkbutton" required="true"  name="puName" class="required" maxlength="50"
						size="50" />

				</td>
			
				<th width="80px">
					 性别：
				</th>
				<td>
					<input type="radio" name="puSex" checked="checked" value="男"/>男
					<input type="radio" name="puSex" value="女"/>女
				</td>
			</tr>
			<tr>
				<th>
					电子邮箱：
				</th>
				<td>
					<input type="text" class=" email"  name="puEmail"  maxlength="50"
						size="50" />

				</td>
		
				<th>
					手机：
				</th>
				<td>
					<input type="text" class="phone"   name="puPhone" maxlength="11" size="50"/>
			</tr>
			<tr>
				<th>
					其他联系：
				</th>
				<td>
				<input type="text" name="puOther" maxlength="11" size="50" />

				</td>
			
				<th>
					分组：
				</th>
				<td>

						<select class="combox required" name="puGrouping">
					<c:forEach var="g" items="${group}">
						<option value="${g.id }">${g.gaName }</option>
					</c:forEach>
				</select>

				</td>
			</tr>
			<tr>
				<th>
					公司名称：
				</th>
				<td>
				<input type="text" name="puCompany" size="50" />

				</td>
			
				<th>
					办公电话：
				</th>
				<td>
				<input type="text" name="puOfficPhone"  size="50" />

				</td>
			</tr>
			
			<tr>
				<th>
					办公传真：
				</th>
				<td>
				<input type="text" name="puOfficFax"  size="50" />

				</td>
			
					<th>
					公司地址：
				</th>
				<td>
				<input type="text" name="puCompanyAddress"  size="50" />

				</td>
			</tr>
			<tr>
					<th>
					邮政编码：
				</th>
				<td>
				<input type="text" name="puZip"  size="50" />

				</td>
		
					<th>
					职位：
				</th>
				<td>
				<input type="text" name="puPost"  size="50" />

				</td>
			</tr>
			<tr>
					<th>
					家庭地址：
				</th>
				<td>
				<input type="text" name="puHomeAddress"  size="50" />

				</td>
			
					<th>
					家庭电话：
				</th>
				<td>
				<input type="text" name="puHomePhone"  size="50" />

				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td colspan="3">
					<textarea  name="puRemark" rows="6" cols="100" style="width: 608px"></textarea>
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






