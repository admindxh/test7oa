<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--新建工作
--%>

<div style="width: 800px;margin: 0 auto;" >
	<table class="table table-bordered " >
	
			<tr>
				<th style="width: 80px">流程名称：</th>
				<td>
					<c:out value="${requestScope.pd.name }"></c:out>
				</td>
			</tr>	
		   	<tr>
				<th>流程类型：</th>
				<td>
					<my:outSelectValue id="${requestScope.pd.category }"/>
				</td>
			</tr>	
		   	
		   	
		   	<tr>
				<th>描述：</th>
				<td>
					<c:out value="${requestScope.pd.description }"></c:out>
				</td>
			</tr>	
		   	<tr>
		   		<th>&nbsp;</th>
				<td>
					<a class="btn btn-primary"  href="processDefinition/showFlowImgPage.do?processDefinitionId=${requestScope.pd.id}"  target="dialog" width="800" height="400" rel="${param.rel }_flowimg" title="${requestScope.pd.name }__流程图_第${requestScope.pd.version }版">流程图</a>
					
					<c:if test="${requestScope.pd.hasStartFormKey }">
						<a class="btn btn-primary" style="margin-left: 10px;"  href="processDefinition/startForm/show.do?processDefinitionId=${requestScope.pd.id}"  target="dialog" width="800" height="500" rel="${param.rel }_flowform" title="${requestScope.pd.name }__流程表单">表单模板</a>
					</c:if>
				</td>
			</tr>	
				  
	</table>			
				
				
				
	<form method="post" action="work/add.do" enctype="multipart/form-data" onsubmit="return validateSubmitForm(this)">
							
		<table class="table table-bordered " >
			  	
				<tr>
					<th style="width: 80px">工作名称：</th>
					<td>
						<input type="text" name="workTitle"  class="easyui-validatebox"  required="true"  validType="minlength[1]"  maxlength="255" style="width: 400px"/>
						<br/><span class="text-info">请填写工作名称，方便查找。</span>
					</td>
				</tr>
				<tr>
					<th>附件：</th>
					<td>
						<table class="table table-bordered itemDetail" addButton="添加工作附件"  style="width:100%;margin-top: 0;"
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
	
		  <input type="hidden" name="id" value="${param.id}"/>	
		  <input type="hidden" name="key" value="${param.key}"/>
	
	
		
	</form>

</div>