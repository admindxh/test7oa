<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-人员合同-新增  
--%>

<div style="width:99%;margin: 0 auto;" >

	<form action="deal/update.do"  method="post" enctype="multipart/form-data"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">
					姓名：
				</th>
				<td >
					<a  href="user/lookUpPage.do?type=2" 	lookupGroup="user" title="用户查询">
					<input  class="easyui-validatebox" required="true" value="<my:outTrueName id="${deal.pdUid}"/>"  readonly="readonly" type="text" toName="user.trueName"  size="80" />
					</a>
					<input type="hidden"  name="pdUid" toName="user.id" value="${deal.pdUid }"/>
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>
				</td>
			
				<th>
					证件号码：
				</th>
				<td>
					<input type="text" name="pdIdNo" class="easyui-validatebox" required="true"  maxlength="50" value="<c:out value="${deal.pdIdNo } "/>" />
				</td>
			</tr>
			<tr>
				<th >
					合同编号：
				</th>
				<td>
					<input type="text" name="pdNumber" class="easyui-validatebox" required="true"  maxlength="50" value="<c:out value="${deal.pdNumber }" />" />
				</td>
			
				<th>
					合同名称：
				</th>
				<td>
					<input type="text" name="pdName"  maxlength="50"  class="easyui-validatebox" required="true" value="<c:out value="${deal.pdName }" />"/>
				</td>
			</tr>
			<tr>
				<th >
					合同类型：
				</th>
				<td>
					<select class="combox" name="pdType"  class="easyui-validatebox" required="true" sValue="${deal.pdType}" >
					<option value="">--请选择--</option>
						<my:outOptions type="701"/>
					</select>
				</td>
			
				<th>
					合同状态：
				</th>
				<td>
					<select class="combox" name="pdStatus" class="easyui-validatebox" required="true" sValue="${deal.pdStatus}" >
					<option value="">--请选择--</option>
						<my:outOptions type="702"/>
					</select>
				</td>
			</tr>
			
			<tr>
				<th >
					签订日期：
				</th>
				<td>
					<input type="text" name="pdSignDate"  maxlength="50" class="easyui-validatebox" required="true"  value="<fmt:formatDate  value="${deal.pdSignDate}" pattern="yyyy-MM-dd" />" onFocus="WdatePicker()" />
				</td>
			
				<th>
					签订次数：
				</th>
				<td>
					<input type="text" name="pdSignNumber" class="easyui-validatebox" required="true"  value="<c:out value="${deal.pdSignNumber }"/>"  maxlength="50"  />
				</td>
			</tr>
			
			<tr>
				<th >
					合同生效日期：
				</th>
				<td>
					<input type="text" name="pdStart"  maxlength="50" class="easyui-validatebox" required="true"  onFocus="WdatePicker()" value="<fmt:formatDate  value="${deal.pdStart}" pattern="yyyy-MM-dd" />"  />
				</td>
			
				<th>
					合同结束日期：
				</th>
				<td>
					<input type="text" name="pdEnd"  maxlength="50" class="easyui-validatebox" required="true"  onFocus="WdatePicker()" value="<fmt:formatDate  value="${deal.pdEnd}" pattern="yyyy-MM-dd" />"  />
				</td>
			</tr>
			
			<tr>
				<th>
					合同内容：
				</th>
				<td colspan="3">
					<textarea name="pdContent"  class="editor" style="width: 98%;"><c:out value="${deal.pdContent}" /></textarea>
				</td>
			</tr>
			
			<tr>
				<th>
					上传附件：
				</th>
				<td colspan="3">
					<div>
						<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
						template='
							<tr>
					 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
									<td ><input type="file" required="true"  fileupload="no" name="file[#index#]"  /></td>
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
							<c:forEach  var="f"  items="${requestScope.file }" varStatus="status">
								<tr >	
										<td style="text-align: center;"><div class="itemDetail-rownumber">${status.count }</div></td>
										<td><c:out value="${f.peName }"/></td>
										<td style="text-align: center;">
											<a class="btnDel"  href="deal/delFile.do?id=${f.id }"  title="请谨慎操作!!此操作为动态删除,会立即执行删除附件操作,您确认删除吗?"></a>
										</td>
										
								</tr>
							</c:forEach>
							
						</tbody>
					</table>


					</div>
				</td>

			</tr>
			
			
			<tr>
				<th >
					操作人：
				</th>
				<td>
					<input type="text" name="pdStaffId"  maxlength="50" value="<my:outLoginInfo type="trueName"/>"  readonly="readonly"/>
				</td>
			
				<th>
					操作时间：
				</th>
				<td>
					<input type="text" name="pdDate"  maxlength="50"  value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>"  readonly="readonly" />
				</td>
			</tr>
			

			<input type="hidden" name="id" value="${deal.id }" />
			<input type="hidden" name="currentCallback" value="close" />
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />



			<tr>
				<th></th>
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


