<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：公各平台--投票管理增加子投票页面
--%>

<div style="width: 600px;margin: 0 auto;">
<form method="post" action="vote/add.do"  onsubmit="return validateSubmitForm(this)" >

	<table class="table table-bordered ">
		<tr>
			<th>标题:</th>
			<td><input type="text" name="title" class="easyui-validatebox" required='true' validType="minlength[1]" maxlength="50" ></td>
		</tr>
		<tr>
			<th>排序号：</th>
			<td><input type="text" name="seqnum" class="easyui-numberbox"  maxlength="10" required='true' validType="minlength[1]" ></td>
		</tr>
		<tr>
			<th>类型:</th>
			<td>
				<select  name="type" onchange="doclick(this)">
					<option value="0">单选</option>
					<option value="1">多选</option>
					<option value="2">文本</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>投票项目:</th>
			<td>
				<table class="table table-bordered itemDetail" addButton="添加"  style="width:100%;margin-top: 0;"
						template='
							<tr>
					 			
								<td ><input type="text"  class="easyui-validatebox"   name="f"  /></td>
								<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
							</tr>
						'>
						<thead>
							<tr>
								 <th style="text-align: center;">选项</th>
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
					<div style="margin-top: 10px;margin-bottom: 10px;">
						 <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
						 <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
		</tr>
	</table>
		
		<input type="hidden" name="voteid" value="${voteid}">
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
