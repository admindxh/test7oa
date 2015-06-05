<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--部门管理  部门更新
--%>

	
	<form method="post" action="dept/update.do"    onsubmit="return validateSubmitForm(this)">
			
		  <div  style="margin-left: 10px;margin-top: 10px;margin-bottom: 5px;">
				<shiro:hasPermission name="dept:add">
					<a class="btn btn-primary" href="dept/addPage.do?rel=${param.rel}" rel="${param.rel }_box" target="ajax" ><i class="boot_icon-plus-sign boot_icon-white"></i> 添加顶级部门</a>
			
					<c:url var="url_addxjdept" value="dept/addPage.do" >
						<c:param name="rel" value="${param.rel}" />
						<c:param name="superId" value="${dept.id }" />
						
					</c:url>
					
					<a class="btn btn-primary" href="${url_addxjdept }" rel="${param.rel }_box" target="ajax" ><i class="boot_icon-plus-sign boot_icon-white"></i> 添加下级部门</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="dept:delete">
					<c:url var="url_delxjdept" value="dept/del.do" >
						<c:param name="id" value="${requestScope.dept.id }" />
						<c:param name="otherBoxId" value="${param.rel}_box" />
						<c:param name="otherCallback" value="dept/addPage.do?rel=${param.rel}" />
						<c:param name="callback_fn" value="queryLeftDepts" />
					</c:url>
				
					<a class="btn btn-danger" target="ajaxTodo"  href="${url_delxjdept }" title="您确定删除部门<font color='red'>[${requestScope.dept.deptName }]</font>吗？如果此部门有下级部门，或者此部门有用户，将无法删除！"><i class="boot_icon-remove-sign boot_icon-white"></i> 删除</a>
				</shiro:hasPermission>
			
		 </div>
		 <div class="divider"></div>
			<table class="table table-nobordered ">	
			
			<tr>
				<th style="width: 80px;">部门序号：</th>
				<td>
					<input type="text" name="deptSort" maxlength="3"  class="required digits" min="1" max="999" size="25" value="${requestScope.dept.deptSort}" />
					<span class="text-info">用于同级部门排序，值越小越靠前(数值范围：1-999 整数)</span>
				</td>
			</tr>
			<tr>
				<th>部门名称：</th>
				<td>
					<input type="text" name="deptName" class="required" maxlength="50" size="25" value="<c:out value="${requestScope.dept.deptName}"/>" />
				</td>
			</tr>
			<tr>
				<th>电话：</th>
				<td>
					<input type="text" name="deptPhone"  maxlength="20" size="25" value="<c:out value="${requestScope.dept.deptPhone}"/>"/>
					
				</td>
			</tr>
			<tr>
				<th>传真：</th>
				<td>
					<input  type="text" name="deptFax"   maxlength="20" size="25" value="<c:out value="${requestScope.dept.deptFax}"/>"/>
					
				</td>
			</tr>
			<tr>
				<th>地址：</th>
				<td>
					<textarea   name="deptAddress" cols="80" rows="3" maxlength="100"><c:out value="${requestScope.dept.deptAddress}"/></textarea>
				</td>
			</tr>
			
			<tr>
				<th>部门经理：</th>
				<td>
					<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询">
					<input type="text"  readonly="readonly" toName="user.trueName" value="<my:outTrueName id="${requestScope.dept.leadUid}"/>"/>
					</a>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					<input type="hidden" name="leadUid" toName="user.id" value="${requestScope.dept.leadUid}"/>
				</td>
			</tr>
			
			<tr>
				<th>上级部门：</th>
				<td>
					<a href="dept/lookUpPage.do?type=3" lookupGroup="dept"  title="上级部门查询">
					<c:choose>
						<c:when test="${requestScope.dept.superId=='0'}">
							<input class="required" type="text" readonly="readonly" size="25" toName="dept.deptName" value="无上级" /></a><a class="btnLook" href="dept/lookUpPage.do" lookupGroup="dept" >查找带回</a>
							<input type="hidden" name="superId" size="25" toName="dept.id" value="0"/>
								
						</c:when>
						<c:otherwise>
							<input type="text" readonly="readonly" size="25" toName="dept.deptName" value="<my:outDeptName id="${requestScope.dept.superId}"/>" />
							<input type="hidden" name="superId" size="25" toName="dept.id" value="${requestScope.dept.superId}"/>
						</c:otherwise>
					</c:choose>
					</a>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
					<span class="text-info">[无上级]，则为顶级部门</span>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="deptDesc" cols="80" rows="2" maxlength="200"><c:out value="${requestScope.dept.deptDesc}"/></textarea>
				</td>
			</tr> 
			 <shiro:hasPermission name="dept:update">
				<tr>
				  <th></th>
				  <td>
					 <div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
						 
					 </div>
				  </td>
			   </tr>
		   </shiro:hasPermission>
		</table>
		<input type="hidden" name="currentCallback" value="refresh" />
		<input type="hidden" name="callback_fn" value="queryLeftDepts"/>	
		<input type="hidden" name="id" value="${requestScope.dept.id}"/>
		
</form>
										
					
					
		

	

