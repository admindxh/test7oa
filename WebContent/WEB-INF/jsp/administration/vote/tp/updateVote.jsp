<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公共平台--投票管理 -- 更新界面
--%>
<div style="width: 700px;margin: 0 auto;">
	<form action="vote/updatevote.do" method="post"  onsubmit="return validateSubmitForm(this)">
			<table class="table table-bordered">
			<tr>
				<th style="width: 110px">标题:</th>
				<td><input type="text" name="tpTitle" class="easyui-validatebox" required='true' validType="minlength[1]" value="${requestScope.n.tpTitle}"></td>
			</tr>
			<tr>
				<th>
					发布范围 (部门):
				</th>
				<td>
				
					<a  href="dept/lookUpPage.do?type=2" lookupGroup="dept"  title="部门查询">
						<textarea  readonly="readonly"  toName="dept.deptName"  rows="2" style="width: 400px" ><my:outDeptName id="${requestScope.deptIds}" /></textarea>
					</a>
					<input type="hidden" name="deptid" toName="dept.id" value="${requestScope.deptIds}"/>
			
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>
					发布范围 (人员):
				</th>
				<td>
					<a  href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 400px"><my:outTrueName id="${requestScope.userIds }"  /></textarea>
					</a>
					<input type="hidden"  name="userid" toName="user.id"  value="${requestScope.userIds}" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>
					描述 :
				</th>
				<td>
					<textarea name="tpDescribe" cols="200" rows="3" maxlength="300">${requestScope.n.tpDescribe}</textarea>
				</td>
			</tr>
			<tr>
				<th>查看投票结果 :</th>
				<td>
					<select  name="tpSeeresult" sValue="${requestScope.n.tpSeeresult}">
						<option value="0">投票后可看</option>
						<option value="1">投票前可看</option>
						<option value="2">不允许查看</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>匿名投票 :</th>
				<td>
					<c:choose>
						<c:when test="${requestScope.n.tpAnonymous==1}">
							<label class="checkbox inline" ><input type="checkbox"   name="tpAnonymous" value="${requestScope.n.tpAnonymous}" checked="checked" >允许匿名投票</label>
						</c:when>
						<c:otherwise>
							<label class="checkbox inline" ><input type="checkbox"   name="tpAnonymous" value="${requestScope.n.tpAnonymous}" >允许匿名投票</label>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>开始日期 :</th>
				<td>
					<input type="text"  required="true" class="easyui-validatebox" readonly="readonly" onFocus="WdatePicker()" name="tpValidity" value="<fmt:formatDate value="${requestScope.n.tpValidity}" pattern="yyyy-MM-dd"/>"  />
				</td>
			</tr>
			<tr>
				<th>终止日期 :</th>
				<td>
					<input type="text"  required="true" class="easyui-validatebox" readonly="readonly" onFocus="WdatePicker()" name="tpExpiry" value="<fmt:formatDate value="${requestScope.n.tpExpiry}" pattern="yyyy-MM-dd"/>" />
				</td>
			</tr>
			<tr>
				<th>置顶 :</th>
				<td>
					<c:choose>
						<c:when test="${requestScope.n.tpStick==1}">
							<label class="checkbox inline" ><input type="checkbox"  name="tpStick" value="${requestScope.n.tpStick}" checked="checked">使投票置顶</label>
						</c:when>
						<c:otherwise>
							<label class="checkbox inline" ><input type="checkbox"  name="tpStick" value="${requestScope.n.tpStick}">使投票置顶</label>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			</tr>
			
		</table>	
			<input type="hidden" name="id" value="${requestScope.n.id }"/>
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
  			<input type="hidden" name="currentCallback" value="close" />
			
	</form>



</div>