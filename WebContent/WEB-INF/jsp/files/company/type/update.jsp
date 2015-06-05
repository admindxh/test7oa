<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公司文档类型管理：修改类型
--%>
<div style="width: 98%;margin: 0 auto;" >
		
	<form method="post" action="companyFiles/type/update.do" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-bordered ">
				<tr>
					<th style="width: 80px">上级分类：</th>
					<td>
						<a href="companyFiles/type/lookUpPage.do?type=2" lookupGroup="type" title="分类查询" width="500" height="350">
							<input type="text"  readonly="readonly"  toName="type.name" class="span2" value="<c:out value="${requestScope.supName}" />"/>
						</a>
						<input type="hidden"  name="superId"  toName="type.id"  value="${requestScope.t.superId}"/>
					</td>
				</tr>
				<tr>
					<th >名称：</th>
					<td>
						<input type="text" name="name"  value="<c:out value="${requestScope.t.name}" />" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="50" />
						<br/><span class="text-info">同级下名称不可重复</span>
					</td>
				</tr>
				<tr>
			    	<th >顺序号：</th>
			    	<td>
			     		<input type="text" name="sortNumber"  value="<c:out value="${requestScope.t.sortNumber}" />" class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3"/>
			    		<span class="text-info">用于同级排序，值越小越靠前(数值范围：1-999 整数)</span>
			    	</td>
		 	   </tr>
				<tr>
					<th>授权部门：</th>
					<td>
						
						<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
							template='
								<tr>
						 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
									
									<td>
										<a href="dept/lookUpPage.do?type=2" lookupGroup="dept"  title="部门查询" targetBox="td">
											<textarea  readonly="readonly"  toName="dept.deptName"  rows="2"  style="width: 200px" ></textarea>
										</a>
										<input type="hidden" name="deptPowers[#index#].tableIds" toName="dept.id" />
									</td>
									<td>
										<label class="checkbox inline" ><input type="checkbox" name="deptPowers[#index#].powerAdd" value="1" />添加</label>
										<label class="checkbox inline" ><input type="checkbox" name="deptPowers[#index#].powerUpdate" value="1" />修改</label>
										<label class="checkbox inline" ><input type="checkbox" name="deptPowers[#index#].powerDelete" value="1" />删除</label>
									</td>
									
									<td style="text-align: center;"><a href="javascript:;"  class="btnDel"  title="删除"></a></td>	
								</tr>
							'>
							<thead>
								<tr>
									 <th width="35" style="text-align: center;">序号</th>
									 <th style="text-align: center;">授权部门</th>
									 <th style="text-align: center;">权限</th>
								 	 <th width="35" style="text-align: center;">删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach  var="m"  items="${requestScope.deptPowers }" varStatus="status">
								<tr >	
										<td style="text-align: center;"><div class="itemDetail-rownumber">${status.count }</div></td>
										<td>
											<a href="dept/lookUpPage.do?type=2" lookupGroup="dept"  title="部门查询" targetBox="td">
												<textarea  readonly="readonly"  toName="dept.deptName"  rows="2"  style="width: 200px" ><my:outDeptName id="${m.tableIds }" /></textarea>
											</a>
											<input type="hidden" name="deptPowers[#index#].tableIds" toName="dept.id" value="${m.tableIds }"/>
										</td>
										<td>
											<label class="checkbox inline" ><input type="checkbox" name="deptPowers[#index#].powerAdd" value="1"  <c:if test="${!empty m.powerAdd }">checked="checked"</c:if>/>添加</label>
											<label class="checkbox inline" ><input type="checkbox" name="deptPowers[#index#].powerUpdate" value="1" <c:if test="${!empty m.powerUpdate }">checked="checked"</c:if>/>修改</label>
											<label class="checkbox inline" ><input type="checkbox" name="deptPowers[#index#].powerDelete" value="1" <c:if test="${!empty m.powerDelete }">checked="checked"</c:if>/>删除</label>
											
										</td>
										
										<td>
											<a class="btnDel"   href="companyFiles/type/delPower.do?id=${m.id }"  title="请谨慎操作!!此操作为动态删除,会立即执行删除操作,您确认删除吗?"></a>
										</td>
										<input type="hidden" name="deptPowers[#index#].id"  value="${m.id }" />
								</tr>
								</c:forEach>
							
							</tbody>
						</table>
							
					</td>
				
				</tr>
				<tr>
					<th>授权用户：</th>
					<td>
						
						<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
							template='
								<tr>
						 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
									
									<td>
										<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
											<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 200px"></textarea>
										</a>
										<input type="hidden"  name="userPowers[#index#].tableIds" toName="user.id" />
									
									</td>
									<td>
										<label class="checkbox inline" ><input type="checkbox" name="userPowers[#index#].powerAdd" value="1" />添加</label>
										<label class="checkbox inline" ><input type="checkbox" name="userPowers[#index#].powerUpdate" value="1" />修改</label>
										<label class="checkbox inline" ><input type="checkbox" name="userPowers[#index#].powerDelete" value="1" />删除</label>
									</td>
									
									<td style="text-align: center;"><a href="javascript:;" class="btnDel"   title="删除"></a></td>	
								</tr>
							'>
							<thead>
								<tr>
									 <th width="35" style="text-align: center;">序号</th>
									 <th style="text-align: center;">授权用户</th>
									 <th  style="text-align: center;">权限</th>
								 	 <th width="35" style="text-align: center;">删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach  var="m"  items="${requestScope.userPowers }" varStatus="status">
								<tr >	
										<td style="text-align: center;"><div class="itemDetail-rownumber">${status.count }</div></td>
										<td>
											<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
												<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 200px"><my:outTrueName id="${m.tableIds }" /></textarea>
											</a>
											<input type="hidden"  name="userPowers[#index#].tableIds" toName="user.id" value="${m.tableIds }"/>
										
										</td>
										<td>
											<label class="checkbox inline" ><input type="checkbox" name="userPowers[#index#].powerAdd" value="1"  <c:if test="${!empty m.powerAdd }">checked="checked"</c:if>/>添加</label>
											<label class="checkbox inline" ><input type="checkbox" name="userPowers[#index#].powerUpdate" value="1" <c:if test="${!empty m.powerUpdate }">checked="checked"</c:if>/>修改</label>
											<label class="checkbox inline" ><input type="checkbox" name="userPowers[#index#].powerDelete" value="1" <c:if test="${!empty m.powerDelete }">checked="checked"</c:if>/>删除</label>
											
										</td>
										
										<td>
											<a class="btnDel"   href="companyFiles/type/delPower.do?id=${m.id }"  title="请谨慎操作!!此操作为动态删除,会立即执行删除操作,您确认删除吗?"></a>
										</td>
										<input type="hidden" name="userPowers[#index#].id"  value="${m.id }" />
								</tr>
								</c:forEach>
							
							</tbody>
						</table>
							
					</td>
				
				</tr>
				<tr>
					<th></th>
					<td>
						<div  style="margin-top: 10px;margin-bottom: 10px;">
							  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
							  <button type="button" class="btn clear" >清空</button>
						</div>
					</td>
				</tr>
				
		</table>
		<input type="hidden" name="id" value="<c:out value="${requestScope.t.id}" />"/>
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
		<input type="hidden" name="currentCallback" value="close" />
		<input type="hidden" name="callback_fn" value="loadLeftWdTypes"/>
		
	</form>
</div>					