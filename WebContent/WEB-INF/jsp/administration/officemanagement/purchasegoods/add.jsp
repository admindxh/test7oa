<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品购买--添加物买购买--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="purchasegoods/add.do"  onsubmit="return validateSubmitForm(this)">
	<table  class="table table-bordered ">
   			<tr>
				<th style="width: 110px">物品名称:</th>
				<td>
		              <a href="goodsmanagerment/lookUpPage.do"  lookupGroup="goodsmanagerment" title="用品名称" >
		     	      <input type="text"  class="easyui-validatebox"  required='true'  readonly="readonly" toName="goodsmanagerment.usingname"  />
				      </a>
				      <input type="hidden"  name="usingnameId"   toName="goodsmanagerment.id"  >
				</td>
			</tr>
			<tr>
				<th >单位:</th>
				<td>
		              
		     		<input type="text"   readonly="readonly" toName="goodsmanagerment.danwei"  />
				     
				</td>
			</tr>
			<tr>
				<th>单价:</th>            
				<td>
			   		 <input type="text" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" required='true'  name="unitprice" maxlength="10"   />
				</td>
			</tr>
			<tr>      
				<th>购买数量:</th>
				<td>              
	     	    	<input type="text" name="amount" class="easyui-numberbox"  required='true' min="1" maxlength="11" >
				</td>
			</tr>
			<tr>
				<th>购买人姓名:</th>
				<td>
					<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询">
							<input type="text" toName="user.trueName" readonly="readonly"/>
					</a>
					<input type="hidden"  name="purchaser" toName="user.id" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					
				</td>
			</tr>
			<tr>
				<th>购买时间:</th>
				<td>
					
					<input type="text"  name="buyTime"  class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					
				</td>
			</tr>
			
			
			<tr>
				<th></th>
				<td>
						<div  style="margin-top: 10px;margin-bottom: 10px; ">
							<button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn clear" >清空</button>
						</div>
				</td>
			</tr>
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	<input type="hidden" name="currentCallback" value="close" />
			
  	</table>
  	</form>
	
</div>