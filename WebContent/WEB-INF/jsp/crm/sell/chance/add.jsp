<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-销售管理-销售机会-添加
--%>

<div style="width: 800px; margin: 0 auto;">

	<form action="chance/add.do" method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered ">
			<tr>
				<th width="80px">
					主题：
				</th>
				<td>
					<input type="text" name="scName" class="easyui-validatebox" 
						required="true" maxlength="50" size="50" style="width: 300PX"/>

				</td>
		</tr>
			<tr>
				<th width="80px">
					客户名称：
				</th>
				<td>
					<input type="text" toName="customer.name" class="easyui-validatebox" required="true" style="width: 300PX" /> 
					<a href="info/lookUpPage.do" lookupGroup="customer" title="用户查询"><img src="resource/images/menu/24507_s.gif"  style="margin-top:-3px;"/></a>
					<input type="hidden" id="Info" name="scInfo" toName="customer.id" >
				</td>
			</tr>
			<tr>
			
			<tr>
				<th width="80px">
					联系人：
				</th>
				<td>
					<input type="text" toName="linkman.name" class="easyui-validatebox glkh" required="true" style="width: 300PX" /> </a>
				 	<a href="linkman/lookUpPage.do?id={#Info}" lookupGroup="linkman" title="用户查询"><img src="resource/images/menu/24507_s.gif"  style="margin-top:-3px;"/></a>
				    <input type="hidden"  name="scLinkman" toName="linkman.id" class="glkh">
				</td>
			</tr>
			<tr>
				<th width="80px">
					联系人电话：
				</th>
				<td>
					<input type="text" name="" toName="linkman.phone" class="easyui-validatebox glkh" style="width: 300PX"
						required="true" maxlength="50" size="50" />
				</td>
			</tr>	
			</tr>
			<tr>
				<th>
					机会来源：
				</th>
				<td>
					<select name="scSource" style="width: 320PX" >
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="601" />
					</select>

				</td>

				
			</tr>
			<tr>
				<th>
					机会状态：
				</th>
				<td>
					<select name="scStatus" style="width: 320PX" >
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="602" />
					</select>

				</td>
		</tr>
		<tr>
		<tr>
				<th>
					负责的部门：
				</th>
				<td colspan="3">
					<a href="dept/lookUpPage.do?type=2" lookupGroup="dept"
						title="部门查询"> <textarea readonly="readonly"
							toName="dept.deptName" rows="2" style="width: 500PX" /> </a>
					<input type="hidden" value="" name="scBranch" toName="dept.id">
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="dept" title="清空"></a>
				</td>
			</tr>
		</tr>
		<tr>
			<th>
					负责人：
				</th>
				<td colspan="3">
					<a href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询">
						<input type="text" toName="user.trueName" style="width: 500PX" /> </a>
					<input type="hidden" name="scStaff" toName="user.id"  >
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>

				</td>			
	
		</tr>
			
			<tr>
				<th>
					描述：
				</th>
				<td colspan="3">
					<textarea name="scPicture" rows="6" cols="100" style="width: 608px"></textarea>
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






