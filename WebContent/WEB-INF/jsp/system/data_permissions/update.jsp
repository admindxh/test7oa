<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--数据权限 -- 修改
--%>

<div style="width: 98%;margin: 0 auto;" >
<form method="post" action="dataPer/update.do" beforeCallback="getSearchFilterRules"  onsubmit="return validateSubmitForm(this)">									
	<table class="table table-bordered ">
			<shiro:hasPermission name="district:update">
			<tr>
				<th></th>
				<td>
					<div  style="float: right;">
						  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  
					</div>
				</td>
			 </tr>
			 </shiro:hasPermission>
			<tr>
				<th style="width: 80px">说明：</th>
				<td>
					
					<textarea  name="desc" rows="2" maxlength="50"><c:out value="${requestScope.per.desc }"/></textarea>
					<input type="hidden" name="rules" />
					<input type="hidden" name="id" value="${requestScope.per.id }" />
					
				</td>
			</tr>
	
	</table>
</form>
	<table class="table table-bordered ">
			
			<tr>
				<th>查询规则：</th>
				<td>
					<div id="${param.rel }_filter" style="width: 95%;margin: 20px auto;"></div>
				</td>
			</tr>
			
	</table>

	
	<span id="${param.rel }_data" style="display: none;"><c:out value="${requestScope.per.rules }"/></span>
</div>

<script type="text/javascript">
<!--
function getSearchFilterRules($form){
	 var group = $('#<%=request.getParameter("rel")%>_filter').queryFilter("getData");
	 $form.find("input[name='rules']").val(JSON.stringify(group));
	 return true;
}
//-->
</script>

