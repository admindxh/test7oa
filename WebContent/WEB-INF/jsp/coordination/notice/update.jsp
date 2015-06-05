<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：协同办公--通知管理 --修改
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="notice/update.do" enctype="multipart/form-data"  onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">标题：</th>
				<td>
					<input type="text" name="noticeTitle" class="easyui-validatebox" required="true"  validType="minlength[1]"  maxlength="100"  value="<c:out value="${requestScope.n.noticeTitle}" />"/>
					
				</td>
			</tr>
			<tr>
				<th>通知类型：</th>
				<td>
					<select  name="noticeType"  sValue="${requestScope.n.noticeType}">
						<my:outOptions type="1"/>
					</select>
				</td>
			</tr>
			<tr>
				<th>接收部门：</th>
				<td>
					<a  href="dept/lookUpPage.do?type=2" lookupGroup="dept"  title="部门查询">
						<textarea  readonly="readonly"  toName="dept.deptName"  rows="2" style="width: 400px" ><my:outDeptName id="${requestScope.nc.deptIds}" /></textarea>
					</a>
					<input type="hidden" name="deptIds" toName="dept.id" value="<c:out value="${requestScope.nc.deptIds}" />"/>
			
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
					
				
				</td>
			</tr>
			
			<tr>
				<th>接收人员：</th>
				<td>
					<a  href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询">
					<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 400px"><my:outTrueName id="${requestScope.nc.userIds}"/></textarea>
					</a>
					<input type="hidden"  name="userIds" toName="user.id"  value="<c:out value="${requestScope.nc.userIds}" />"/>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>提醒：</th>
				<td>
					<label class="checkbox inline" ><input type="checkbox" name="isSendMsg" value="1" />系统提醒</label>
				</td>
			</tr>
			<tr>
				<th>附件：</th>
				<td>
					<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
						template='
							<tr>
					 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
								<td ><input type="file" fileupload="no" class="easyui-validatebox"  required="true" name="file[#index#]"  /></td>
								<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
							</tr>
						'>
						<thead>
							<tr>
								 <th width="35" style="text-align: center;">序号</th>
								 <th style="text-align: center;">附件</th>
							 	 <th width="35" style="text-align: center;">删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach  var="m"  items="${requestScope.nfs }" varStatus="status">
								<tr >	
										<td style="text-align: center;"><div class="itemDetail-rownumber">${status.count }</div></td>
										<td><c:out value="${m.fileName }"/></td>
										<td>
											<a class="btnDel"  href="notice/delFile.do?id=${m.id }"  title="请谨慎操作!!此操作为动态删除,会立即执行删除附件操作,您确认删除吗?"></a>
										</td>
										
								</tr>
							</c:forEach>
						</tbody>
					</table>
				
				</td>
				
			</tr>
		
			<tr>
				<th>内容：</th>
				<td>
					<textarea  name="noticeContent"  rows="5" class="editor" style="width: 98%"><c:out value="${requestScope.nc.noticeContent}" /></textarea>
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
		
	<input type="hidden" name="id" value="<c:out value="${requestScope.n.id}" />"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					