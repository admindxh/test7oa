<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：公共平台--会议管理 -- 新增
--%>

<div style="width: 1000px;margin: 0 auto;" >

	<form action="meeting/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">
					类型：
				</th>
				<td>
					<select class="easyui-validatebox" required="true" name="MType">
						<option value="">
							--请选择会议类型--
						</option>
						<my:outOptions type="2" />
					</select>
				</td>
			</tr>
			<tr>
				<th>
					标题：
				</th>
				<td>
					<input type="text" name="MName" class="easyui-validatebox"
						required="true" data-options="validType:['length[1,50]']"
						maxlength="50" />

				</td>
			</tr>
			<tr>
				<th>
					主持人：
				</th>
				<td>
					<input type="text" name="MHost" class="easyui-validatebox" required="true" maxlength="50"
						size="50" />

				</td>
			</tr>
			<tr>
				<th>
					会议日期：
				</th>
				<td>
					<input type="text" readonly="readonly"  class="easyui-validatebox" required="true" onFocus="WdatePicker()"
						name="MDate" />


				</td>
			</tr>
			<tr>
				<th>
					开始时间：
				</th>
				<td>
					<input type="text" readonly="readonly"  class="easyui-validatebox" required="true" id="statime"
						onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"
						name="MStatime" />


				</td>
			</tr>
			<tr>
				<th>

					结束时间：
				</th>
				<td>
					<input type="text" readonly="readonly"  class="easyui-validatebox" required="true"
						onFocus="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'statime\');}'})"
						name="MEndtime" />


				</td>
			</tr>
			<tr>
				<th>
					会议室：
				</th>
				<td>

					<select class="combox required" class="easyui-validatebox" required="true" name="MRid">
						<option value="">
							--请选择会议室--
						</option>
						<c:forEach var="l" items="${list}">
							<option value="${l.id }">
								${l.mrName }
							</option>
						</c:forEach>
					</select>

				</td>
			</tr>
			<tr>
				<th>
					参与部门：
				</th>
				<td>
					<a 	href="dept/lookUpPage.do?type=2" 	lookupGroup="dept" title="部门查询">
					<textarea  readonly="readonly"
						toName="dept.deptName" rows="2"  style="width: 500PX" />


					</a>
					<input type="hidden" value=""  name="deptid"  toName="dept.id">
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel" 
						plain="true" href="javascript:;" clearLookup="dept" title="清空"></a>
				</td>
			</tr>

			<tr>
				<th>
					参与人：
				</th>
				<td>
					
					<a href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询" >
						<textarea  readonly="readonly" 	toName="user.trueName"  rows="2" style="width: 500PX" /></a>
					<input type="hidden" name="userid" toName="user.id" />
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>


				</td>
			</tr>
			<tr>
				<th>
					简介：
				</th>
				<td>

					<textarea name="MSummary" rows="3" style="width: 800PX"
						maxlength="5000"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td c>
					<textarea class="editor" name="MRemark" rows="6" cols="100" style="width: 98%;"></textarea>
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