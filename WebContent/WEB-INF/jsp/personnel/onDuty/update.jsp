<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-值班管理-修改值班记录
--%>

<div style="width: 98%;margin: 0 auto ;" >
			
<form method="post" action="onDuty/update.do" enctype="multipart/form-data"  onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
				<tr>
				<th style="width: 120px">值班人：</th>
				<td>
				<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" >
					<input type="text"  readonly="readonly"  toName="user.trueName"  class="easyui-validatebox" 
					required="true" value="<c:out value="${requestScope.username}" />" />
				</a> 
				<input type="hidden"  name="userid" value="<c:out value="${requestScope.o.userid}" />"  toName="user.id"  />
 				</td>
			</tr>
			<tr>
				<th>值班时间：</th>
				<td>
					<input type="text" name="starttime" class="easyui-validatebox" required="true" 
					 id="starttime"   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					 readonly="readonly" value="<c:out value="${requestScope.o.starttime}" />" />
				 --					
					<input type="text" name="endtime" id="endtime" class="easyui-validatebox" required="true" 
					 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\');}'})" readonly="readonly"
					value="<c:out value="${requestScope.o.endtime}" />" />
				</td> 
			</tr>
			<tr>
				<th style="width: 80px">标题：</th>
				<td>
					<input type="text" name="title" class="easyui-validatebox" required="true"  
					validType="minlength[1]"  maxlength="100" 
					 value="<c:out value="${requestScope.o.title}" />"/>
					
				</td>
			</tr>
			<tr>
				<th>通知类型：</th>
				<td>
					<select  name="type"  sValue="${requestScope.o.type}">
						<my:outOptions type="21"/>
					</select>
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
							<c:forEach  var="m"  items="${requestScope.fs }" varStatus="status">
								<tr >	
										<td style="text-align: center;"><div class="itemDetail-rownumber">${status.count }</div></td>
										<td><c:out value="${m.fileName }"/></td>
										<td>
											<a class="btnDel"  href="onDuty/delFile.do?id=${m.id }"  title="请谨慎操作!!此操作为动态删除,会立即执行删除附件操作,您确认删除吗?"></a>
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
					<textarea  name="content"  rows="5" class="editor" style="width: 98%"><c:out value="${requestScope.o.content}" /></textarea>
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
		
	<input type="hidden" name="id" value="<c:out value="${requestScope.o.id}" />"/>
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
    <input type="hidden" name="currentCallback" value="close" />
	
</form>
</div>
										
						
					