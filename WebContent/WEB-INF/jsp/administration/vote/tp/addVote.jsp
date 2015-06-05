<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	模块：公共平台--投票管理 -- 新增投票
--%>
	

<div style="width: 700px;margin: 0 auto;" >
		<form action="vote/addPage.do" method="post"" onsubmit="return validateSubmitForm(this)"> 
			<table   class="table table-bordered ">
				<tr>
					<th style="width: 110px">标题:</th>
					<td>
						<input type="text" name="tpTitle" class="easyui-validatebox"  required='true' validType="minlength[1]" maxlength="50"  />
					</td>
				</tr>
				<tr>
					<th>发布范围 (部门):</th>
					<td>
						<a href="dept/lookUpPage.do?type=2" lookupGroup="dept"  title="部门查询">
							<textarea  readonly="readonly"  toName="dept.deptName"  rows="2" style="width: 400px"  ></textarea>
						</a>
						<input type="hidden" name="deptIds"  toName="dept.id" />
						<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
					</td>
				</tr>
				<tr>
					<th>发布范围 (人员):</th>
					<td>
						<a href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询">
							<textarea  readonly="readonly"  toName="user.trueName"  rows="2" style="width: 400px"></textarea>
						</a>
						<input type="hidden"  name="userIds" toName="user.id" />
						<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					</td>
				</tr>
				<tr>
					<th>描述:</th>
					<td>
						<textarea name="tpDescribe" cols="200" rows="3" maxlength="200"></textarea>
					</td>
				</tr>
				<tr>
					<th>查看你投票结果:</th>
					<td>
						<select  name="tpSeeresult">
						<option value="0">投票后可看</option>
						<option value="1">投票前可看</option>
						<option value="2">不允许查看</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>匿名投票:</th>
					<td>
						<label class="checkbox inline" ><input type="checkbox" name="tpAnonymous" value="1" />允许匿名投票</label>
					</td>
				</tr>
				<tr>
					<th>开始日期:</th>
					<td>
						<input type="text" required="true" class="easyui-validatebox span2"  readonly="readonly"  onFocus="WdatePicker()" name="tpValidity"  />
					</td>
				</tr>
				<tr>
					<th>终止日期:</th>
					<td>
						<input type="text" required="true"  class="easyui-validatebox span2"  readonly="readonly" onFocus="WdatePicker()" name="tpExpiry" />
					</td>
				</tr>
				<tr>
					<th>置顶:</th>
					<td>
						<label class="checkbox inline" ><input type="checkbox" name="tpStick" value="1" />使投票置顶</label>
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
			 <input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	 <input type="hidden" name="currentCallback" value="close" />
		</form>

</div>
