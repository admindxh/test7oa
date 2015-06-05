<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：个人办公-通讯录-公司通讯录   
--%>


<div style="width: 800px; margin: 0 auto;">
	<form action="public/update.do"  method="post"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-bordered ">
			<tr>
				<th width="80px">
					姓名：
				</th>
				<td>
					<input type="text" class="easyui-linkbutton" required="true"  name="puName" class="required" maxlength="50"
						size="50" value="<c:out value="${requestScope.per.puName }"/>" />

				</td>
		
				<th width="80px">
					性别：
				</th>
				<td>
					<c:choose>

						<c:when test="${requestScope.per.puSex=='男' }">
							<input type="radio" name="puSex" checked="checked" value="男" />男 
   					</c:when>
						<c:otherwise>
							<input type="radio" name="puSex" value="男" />男
  				 	</c:otherwise>
					</c:choose>
					<c:choose>

						<c:when test="${requestScope.per.puSex=='女' }">
							<input type="radio" name="puSex" checked="checked" value="女" />女
   					</c:when>
						<c:otherwise>
							<input type="radio" name="puSex" value="女" />女
  				 	</c:otherwise>
					</c:choose>

				</td>
				</tr>
				<tr>
					<th>
						电子邮箱：
					</th>
					<td>
						<input type="text" class=" email" name="puEmail" maxlength="50"
							size="50" value="<c:out value="${requestScope.per.puEmail }"/>" />

					</td>
				
					<th>
						手机：
					</th>
					<td>
						<input type="text" class="phone" name="puPhone" maxlength="11"
							size="50" value="<c:out value="${requestScope.per.puPhone }"/>" />
				</tr>
				<tr>
					<th>
						其他联系：
					</th>
					<td>
						<input type="text" name="puOther" maxlength="11" size="50"
							value="<c:out value="${requestScope.per.puOther }"/>" />

					</td>
				
					<th>
						分组：
					</th>
					<td>

						<select class="combox required" name="puGrouping"
							sValue="${requestScope.per.puGrouping}">
							<c:forEach var="g" items="${group}">
								<option value="${g.id }">
									${g.gaName }
								</option>
							</c:forEach>
						</select>

					</td>
				</tr>
				<tr>
					<th>
						公司名称：
					</th>
					<td>
						<input type="text" name="puCompany" size="50"
							value="<c:out value="${requestScope.per.puCompany }"/>" />

					</td>
				
					<th>
						办公电话：
					</th>
					<td>
						<input type="text" name="puOfficPhone" size="50"
							value="<c:out value="${requestScope.per.puOfficPhone }"/>" />

					</td>
				</tr>

				<tr>
					<th>
						办公传真：
					</th>
					<td>
						<input type="text" name="puOfficFax" size="50"
							value="<c:out value="${requestScope.per.puOfficFax }"/>" />

					</td>
				
					<th>
						公司地址：
					</th>
					<td>
						<input type="text" name="puCompanyAddress" size="50"
							value="<c:out value="${requestScope.per.puCompanyAddress }"/>" />

					</td>
				</tr>
				<tr>
					<th>
						邮政编码：
					</th>
					<td>
						<input type="text" name="puZip" size="50"
							value="<c:out value="${requestScope.per.puZip }"/>" />

					</td>
				
					<th>
						职位：
					</th>
					<td>
						<input type="text" name="puPost" size="50"
							value="<c:out value="${requestScope.per.puPost }"/>" />

					</td>
				</tr>
				<tr>
					<th>
						家庭地址：
					</th>
					<td>
						<input type="text" name="puHomeAddress" size="50"
							value="<c:out value="${requestScope.per.puHomeAddress }"/>" />

					</td>
				
					<th>
						家庭电话：
					</th>
					<td>
						<input type="text" name="puHomePhone" size="50"
							value="<c:out value="${requestScope.per.puHomePhone }"/>" />

					</td>
				</tr>
				<tr>
					<th>
						备注：
					</th>
					<td colspan="3"><textarea name="puRemark" rows="6" cols="100" style="width: 608px" ><c:out value="${requestScope.per.puRemark }" /></textarea>
					</td>
				</tr>
				<input type="hidden" name="id" value="${requestScope.per.id }">
				<input type="hidden" name="currentCallback" value="close" />
				<input type="hidden" name="datagridId"
					value="${param.rel }_datagrid" />

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








