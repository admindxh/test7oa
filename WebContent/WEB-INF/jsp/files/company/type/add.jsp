<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公司文档类型管理：添加类型
--%>

<div style="width: 98%;margin: 0 auto;" >
		
	<form method="post" action="companyFiles/type/add.do" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-bordered ">
				<tr>
					<th style="width: 80px">上级：</th>
					<td>
						<c:out value="${requestScope.superName }"></c:out>
					</td>
				</tr>
				<tr>
					<th >名称：</th>
					<td>
						<input type="text" name="name" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="50" />
						<br/><span class="text-info">同级下名称不可重复</span>
					</td>
				</tr>
				<tr>
			    	<th >顺序号：</th>
			    	<td>
			     		<input type="text" name="sortNumber" class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3"/>
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
									
									<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
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
							<tbody></tbody>
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
									
									<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
								</tr>
							'>
							<thead>
								<tr>
									 <th width="35" style="text-align: center;">序号</th>
									 <th style="text-align: center;">授权用户</th>
									 <th style="text-align: center;">权限</th>
								 	 <th width="35" style="text-align: center;">删除</th>
								</tr>
							</thead>
							<tbody></tbody>
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
		
		<input type="hidden" name="superId" value="${param.superId }"/>
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
		<input type="hidden" name="currentCallback" value="close" />
		<input type="hidden" name="callback_fn" value="loadLeftWdTypes"/>
		
	</form>
</div>

