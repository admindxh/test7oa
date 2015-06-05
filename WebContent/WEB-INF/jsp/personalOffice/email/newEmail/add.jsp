<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公-内部邮件-新建邮件
--%>

<div style="width: 99%;margin: 0 auto;" >

	<form action="email/add.do"  enctype="multipart/form-data" method="post" 
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered " >	
			<tr>
				<th width="80px">收件人：</th>
				<td>
				<a 	href="user/lookUpPage.do?type=2"	lookupGroup="user" title="用户查询">
					<textarea  readonly="readonly" class="easyui-validatebox" required="true"
						toName="user.trueName" style="width: 210PX" rows="2" ></textarea>
					
					</a>
					<input type="hidden"  name="userIds" toName="user.id" />
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>
					
				</td>
			</tr>
			
			<tr>
				<th>标题：</th>
				<td>
					<input type="text" name="eoName" class="easyui-validatebox" required="true"   maxlength="50"   size="80" />
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
				<th>内容：</th>
				<td>
					<textarea  name="eoDetail"  rows="15" class="editor" style="width: 98%;"></textarea>
				</td>
			</tr>
			<input type="hidden" name="type" value="new"/>
			<input  type="hidden"  name="getId" value=""/>
			<input type="hidden" name="currentCallback" value="close" />

			<tr>
				<th></th>
				<td>
					<div style="margin-top: 10px; margin-bottom: 10px;">
						<button type="submit" class="btn btn-primary" onclick="mailboxaddfasong(this)">
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
			//$("input[toName='user.trueName']",$form).attr("required","true"); 
			$("input[toName='user.trueName']",$form).validatebox("enableValidation");
		}
	
		function mailboxaddcaogao(bt){
			var $form=$(bt).closest("form");
			//$("input[toName='user.trueName']",$form).removeAttr("required"); 
			$form.attr("action","email/save.do");
			$("input[toName='user.trueName']",$form).validatebox("disableValidation");
			
		}

	</script>					
					
		

	

