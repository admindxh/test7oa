<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人文档管理：修改文件
--%>

<div style="width: 500px;margin: 0 auto;" >					
<form method="post" action="personalFiles/updateFile.do" enctype="multipart/form-data"   onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th style="width: 80px">上级目录：</th>
					<td>
						<a href="personalFiles/lookUpPage.do" lookupGroup="type" title="目录查询" width="500" height="350">
							<input type="text"  readonly="readonly"  toName="type.name" class="span2" value="<c:out value="${requestScope.superName}" />"/>
						</a>
						<input type="hidden"  name="wdSuperId"  toName="type.id"  value="${requestScope.f.wdSuperId}"/>
					</td>
				</tr>
			<tr>
				<th >文件名：</th>
				<td>
					<input type="text" name="wdName" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="50" value="<c:out value="${requestScope.f.wdName}" />"/>
					
				</td>
			</tr>
			<tr>
				<th>文件：</th>
				<td>
					<input type="file" fileupload="no" name="file"  />
					<br/><span class="text-info">不选择文件则不修改附件，如果选择，将会覆盖原文件！</span>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea  name="wdDesc" rows="2" maxlength="50" style="width: 250px"><c:out value="${requestScope.f.wdDesc}" /></textarea>
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
			
			
			
	<input type="hidden" name="id" value="${requestScope.f.id }"/>
	
	<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
	<input type="hidden" name="currentCallback" value="close" />
			
			

	
</form>
</div>
										
						
					
		

	

