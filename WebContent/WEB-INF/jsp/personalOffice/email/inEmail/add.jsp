<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-内部邮件-回复邮件
--%>


<div style="width: 99%;margin: 0 auto;" >

	<form action="email/addIn.do"  enctype="multipart/form-data" method="post" 
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">收件人：</th>
				<td>
					<a 	href="user/lookUpPage.do?type=2" 	lookupGroup="user" title="用户查询">
					<textarea  readonly="readonly" class="easyui-validatebox" required="true"
						toName="user.trueName" style="width: 210PX" rows="2" ><my:outTrueName id="${email.emInbox.eiUid}"/></textarea>
					
					</a>
					<input type="hidden" value="${email.emInbox.eiUid }"    name="userIds" toName="user.id" />
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>
					
				</td>
			</tr>
			<tr>
				<th>标题：</th>
				<td>
					<input type="text" name="eoName"  class="required"  maxlength="50" value="回复：<c:out value="${email.emInbox.eiName }"/>"  size="80" />
				</td>
			</tr>
			<tr>
				<th>附件：</th>
				<td>
					<div>
						<table class="table table-bordered itemDetail" addButton="添加"  style="width: 100%"
							
							template='
								<tr>
						 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
									<td ><input type="file" required="true"  fileupload="no" name="file[#index#]"  /></td>
									<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
								</tr>
							' >
						
						<thead>
							<tr>
								 <th width="35" style="text-align: center;">序号</th>
								 <th style="text-align: center;">附件</th>
								 <th width="35" style="text-align: center;">删除</th>
							</tr>
							
						</thead>
					 	<tbody>
					 		
					 	
					 	</tbody>
				
					</table>
					</div>
				</td>
				
			</tr>
			<tr>
				<th>回复：</th>
				<td>
				<textarea  name="eoDetail"  rows="15" class="editor" style="width: 98%;"><br/>--------------原始邮件-------------<br/><table  width="90%"  style="background-color: #EFF5FB;" height="80px"><tr><td>发件人：<my:outTrueName id="${uid }"/></td></tr><tr><td>发送时间：<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr><tr><td>收件人：<my:outTrueName id="${sessionScope.minfo.id }"/></td></tr><tr><td>主&nbsp;&nbsp;&nbsp;&nbsp;题：<span style="font-weight: bold;"><c:out value="${name }" /></span></td></tr></table><div style="margin-top:10px;"><c:out value="${detail }" /></div></textarea>
				</td>
			</tr>
			<input type="hidden" name="type" value="out"/>
			<input type="hidden" name="id" value="${email.emInbox.id}" />
			<input type="hidden" name="currentCallback" value="close" />

		
		<tr>
		<th></th>
				
				<td>
					<div style="margin-top: 10px; margin-bottom: 10px;">
						<button type="submit" class="btn btn-primary"  onclick="mailboxaddfasong(this)">
							发送
						</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary" onclick="mailboxaddcaogao(this)">保存草稿箱</button>
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
<script type="text/javascript" >
function mailboxaddfasong(bt){
	var $form=$(bt).closest("form");
	$form.attr("action","email/add.do");
	$("input[toName='user.trueName']",$form).validatebox("enableValidation");
}

function mailboxaddcaogao(bt){
	var $form=$(bt).closest("form");
	$form.attr("action","email/save.do");
	$("input[toName='user.trueName']",$form).validatebox("disableValidation");
	
}

	</script>					
									
					
		

	

