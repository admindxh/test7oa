<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：项目管理 -- 修改
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="project/update.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">项目名称：</th>
				<td>
					<input type="text" name="name" class="easyui-validatebox" required="true" validType="minlength[1]"  maxlength="100"   value="<c:out value="${requestScope.p.name }" />"/>
					
				</td>
			</tr>
			<tr>
				<th>项目类型：</th>
				<td>
					<select class="easyui-validatebox" required="true"  name="type"  sValue="${requestScope.p.type }">
						<my:outOptions type="331"/>
					</select>
				</td>
			</tr>
			<tr>
				<th>重要程度：</th>
				<td>
					<select class="easyui-validatebox" required="true"  name="importance"  sValue="${requestScope.p.importance }">
						<my:outOptions type="332"/>
					</select>
				</td>
			</tr>
			<tr>
				<th>项目时间：</th>
				<td>
					<input type="text"  name="startTime" id="project_update" class="easyui-validatebox" style="width: 140px"  required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${requestScope.p.startTime }" pattern="yyyy-MM-dd HH:mm" />"/>
					--
					<input type="text"  name="endTime"  class="easyui-validatebox" required="true"  style="width: 140px"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'project_update\');}'})"  value="<fmt:formatDate value="${requestScope.p.endTime }" pattern="yyyy-MM-dd HH:mm" />"/>
				</td>
			</tr>
			<tr>
				<th>项目状态：</th>
				<td>
					<select   name="status" sValue="${requestScope.p.status }">
						<option value="0">未开始</option>
						<option value="1">进行中</option>
						<option value="2">暂停</option>
						<option value="3">结束</option>
					</select>
					<span class="text-info">说明项目现在运行情况，状态为 结束 时不能对项目添加任务，进度。</span>
				</td>
			</tr>
			<tr>
				<th>负责人：</th>
				<td>
					<a href="user/lookUpPage.do?type=2"  lookupGroup="fz" title="用户查询">
						<textarea  readonly="readonly"  toName="fz.trueName"  rows="2" style="width: 400px"><my:outTrueName id="${requestScope.fzIds }"/></textarea>
					</a>
					<input type="hidden"  name="fzIds" toName="fz.id" value="${requestScope.fzIds }"/>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="fz"  title="清空"></a>
					<br/><span class="text-info">负责人可以管理项目任务,项目文档。</span>
				</td>
			</tr>
			
			<tr>
				<th>授权部门：</th>
				<td>
					<a href="dept/lookUpPage.do?type=2" lookupGroup="dept" title="部门查询">
						<textarea  readonly="readonly"  toName="dept.deptName"  rows="2" style="width: 400px" ><my:outDeptName id="${requestScope.deptIds }"/></textarea>
					</a>
					<input type="hidden" name="deptIds" toName="dept.id" value="${requestScope.deptIds }"/>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
					
					<br/><span class="text-info">只有查询功能。</span>
				</td>
			</tr>
			
			<tr>
				<th>授权人员：</th>
				<td>
					<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 400px"><my:outTrueName id="${requestScope.userIds }"/></textarea>
					</a>
					<input type="hidden"  name="userIds" toName="user.id" value="${requestScope.userIds }"/>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					<br/><span class="text-info">只有查询功能。</span>

				</td>
			</tr>
			<tr>
				<th>提醒：</th>
				<td>
					<label class="checkbox inline" ><input type="checkbox" name="isSendMsg" value="1" />系统提醒</label>
				</td>
			</tr>
	
			<tr>
				<th>项目内容：</th>
				<td>
					<textarea  name="content"  rows="5" class="editor"  style="width: 98%;" ><c:out value="${requestScope.p.content}" /></textarea>
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

	<input type="hidden" name="id" value="<c:out value="${requestScope.p.id}" />"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					
		

	

