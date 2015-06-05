<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--即时消息--发送消息
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<div class="easyui-tab"  title="发送消息" 	style="padding:2px; " >
		 <div style="width: 98%;margin: 5px auto ;" >	
			 <form method="post" action="instantMessage/add.do"  onsubmit="return validateSubmitForm(this)">
				接收人：
					<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<input type="text"  readonly="readonly"  toName="user.trueName" style="width: 400px" class="easyui-validatebox" required="true" value="<my:outTrueName id="${param.userId}"/>"/>
					</a>
					<input type="hidden"  name="userIds" toName="user.id"  value="${param.userId }"/>
				
			 		<button type="submit" class="btn btn-primary" style="margin-left: 5px;">发送</button>
					
					<div class="divider"></div>
				   <textarea  name="messageContent"  rows="5" class="editor" style="width: 98%;margin-top: 5px"></textarea>
					
				  <input type="hidden" name="currentCallback" value="close" />
			</form>
		</div>

	</div>

	<div class="easyui-tab"  title="历史记录"   href="instantMessage/selectHistoryMessage.do?userId=${param.userId }&rel=${param.rel }_lsjl&page=1&rows=5"	style="padding:2px; " >
		
					
					
	</div>
	

</div>






