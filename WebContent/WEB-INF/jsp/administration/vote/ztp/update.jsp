<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：公共平台--子投票投票管理 -- 更新界面
--%>
<div style="width: 600px;margin: 0 auto;">
	<form action="vote/updatztp.do" method="post"  onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered">
		<tr>
			<th>标题:</th>
			<td><input type="text" name="title" class="easyui-validatebox" required='true' maxlength="50" validType="minlength[1]" value="${requestScope.n.title}"></td>
		</tr>
		<tr>
			<th>排序号:</th>
			<td><input type="text" name="seqnum" class="easyui-numberbox" maxlength="20" required='true' validType="minlength[1]"  value="${requestScope.n.seqnum}"></td>
		</tr>
		<tr>
			<th>类型:</th>
			<td>
				<select    name="type" sValue="${requestScope.n.type}" onchange="doclick(this)">
					<option  value="0">单选</option>
					<option  value="1">多选</option>
					<option  value="2">文本</option>
				</select>
			</td>
		</tr>
		<tr <c:if test="${requestScope.n.type==2}">style="display: none;" </c:if>>
			
				<th>投票项目:</th>
				<td>
					<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
						template='
							<tr>
					 			<td ><input type="text"  class="easyui-validatebox"   name="f[#index#].optiondesc"  /></td>
								<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
							</tr>
						'>
						<thead>
							<tr>
								 <th style="text-align: center;" >选项</th>
							 	 <th width="35" style="text-align: center;">删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach  var="v"  items="${list }" varStatus="status">
								<tr >	
										<td>
											<input type="text" value="${v.optiondesc}" name="f[${status.index }].optiondesc" size="50">
											<input type="hidden" value="${v.id}" name="f[${status.index }].id" >
										</td>
										<td>
											<a class="btnDel"  href="vote/deltpxm.do?id=${v.id }"  title="请谨慎操作!您确认删除吗?"></a>
										</td>
										
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
		<input type="hidden" name="id" value="${requestScope.n.id }"/>
		<input type="hidden" name="otherCallback" value="refresh" />	
		<input type="hidden" name="currentCallback" value="close" />
		<input type="hidden" name="otherBoxId" value="${param.rel }" />
		
	
	
	</form>


</div>
<script type="text/javascript">
	function doclick(s){
	
		var $tr=$(s).closest("tr");

		var type=$(s).val();
		if(type==2){
			
			$tr.next().hide();
		}else{
			
			$tr.next().show();
		}
	}


</script>