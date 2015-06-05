<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-通讯录-个人通讯录  
--%>

<div style="width: 800px;margin: 0 auto;" >
	<form  action="personal/update.do"  method="post"   onsubmit="return validateSubmitForm(this)">
		 
		<table class="table table-bordered " >	
			<tr>
					<th width="80px">
					姓名：
				</th>
				<td>
					<input type="text" name="peName" class="easyui-validatebox" required="true" maxlength="50"
						size="50" value="<c:out value="${requestScope.per.peName }"/>"/>

				</td>
			
				<th width="80px">
					 性别：
				</th>
				<td>
				<c:choose>

   					<c:when test="${requestScope.per.peSex=='男' }"> 
   						<input type="radio" name="peSex" checked="checked" value="男"/>男 
   					</c:when>
  					<c:otherwise>  
						<input type="radio" name="peSex"  value="男"/>男
  				 	</c:otherwise>
				</c:choose>
				<c:choose>

   					<c:when test="${requestScope.per.peSex=='女' }"> 
   						<input type="radio" name="peSex" checked="checked" value="女"/>女
   					</c:when>
  					<c:otherwise>  
						<input type="radio" name="peSex"  value="女"/>女
  				 	</c:otherwise>
				</c:choose>
				
				</td>
			</tr>
			<tr>
				<th>
					电子邮箱：
				</th>
				<td>
					<input type="text" class=" email" name="peEmail"  maxlength="50"
						size="50" value="<c:out value="${requestScope.per.peEmail }"/>"/>

				</td>
			
				<th>
					手机：
				</th>
				<td>
					<input type="text"  class="phone"  name="pePhone" maxlength="11" size="50" value="<c:out value="${requestScope.per.pePhone }"/>"/>
			</tr>
			<tr>
				<th>
					其他联系：
				</th>
				<td>
				<input type="text" name="peOther" maxlength="11" size="50" value="<c:out value="${requestScope.per.peOther }"/>"/>

				</td>
			
				<th>
					分组：
				</th>
				<td>
					<select  name="peGrouping" class="easyui-validatebox" required="true" sValue="${requestScope.per.peGrouping}">
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
				<input type="text" name="peCompany" size="50" value="<c:out value="${requestScope.per.peCompany }"/>"/>

				</td>
			
				<th>
					办公电话：
				</th>
				<td>
				<input type="text" name="peOfficPhone"  size="50" value="<c:out value="${requestScope.per.peOfficPhone }"/>"/>

				</td>
			</tr>
			
			<tr>
				<th>
					办公传真：
				</th>
				<td>
				<input type="text" name="peOfficFax"  size="50" value="<c:out value="${requestScope.per.peOfficFax }"/>"/>

				</td>
			
					<th>
					公司地址：
				</th>
				<td>
				<input type="text" name="peCompanyAddress"  size="50" value="<c:out value="${requestScope.per.peCompanyAddress }"/>"/>

				</td>
			</tr>
			<tr>
					<th>
					邮政编码：
				</th>
				<td>
				<input type="text" name="peZip"  size="50" value="<c:out value="${requestScope.per.peZip }"/>"/>

				</td>
			
					<th>
					职位：
				</th>
				<td>
				<input type="text" name="pePost"  size="50" value="<c:out value="${requestScope.per.pePost }"/>"/>

				</td>
			</tr>
			<tr>
					<th>
					家庭地址：
				</th>
				<td>
				<input type="text" name="peHomeAddress"  size="50" value="<c:out value="${requestScope.per.peHomeAddress }"/>"/>

				</td>
			
					<th>
					家庭电话：
				</th>
				<td>
				<input type="text" name="peHomePhone"  size="50"  value="<c:out value="${requestScope.per.peHomePhone }"/>"/>

				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td  colspan="3">
					<textarea  name="peRemark" rows="6" cols="100" style="width: 608px" ><c:out value="${requestScope.per.peRemark }"/></textarea>
				</td>
			</tr>
			<input type="hidden" name="peUid" value="${requestScope.per.peUid }">
			<input type="hidden" name="id" value="${requestScope.per.id }">
				  <input type="hidden" name="currentCallback" value="close" />
		  <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 
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




