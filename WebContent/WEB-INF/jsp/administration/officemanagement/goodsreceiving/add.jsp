<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品领用--添加物品领用页面--%>
<div style="width: 600px;margin: 0 auto;" >
<form method="post" action="goodsreceiving/add.do"   onsubmit="return validateSubmitForm(this)">
	<table  class="table table-bordered ">
   			<tr>
				<th style="width: 110px">物品名称:</th>
				<td>
		              <a href="goodsmanagerment/lookUpPage.do"  lookupGroup="goodsmanagerment" title="用品名称" >
		     			  <input type="text"  class="easyui-validatebox"  required='true'  readonly="readonly" toName="goodsmanagerment.usingname"  />
				      </a>
				      <input type="hidden"   name="usingnameid" readonly="readonly" toName="goodsmanagerment.id"  >
				</td>
			</tr>
			<tr>
				<th >单位:</th>
				<td>
		     		<input type="text"   readonly="readonly" toName="goodsmanagerment.danwei"  />
				     
				</td>
			</tr>
			 <tr>
				<th>领用数量:</th>            
				<td>
			    	 <input type="text" class="easyui-numberbox"  required="true"   name="usenumber"  min="1" maxlength="11"/>
				</td>
			</tr>
		    <tr>
				<th>领用人:</th>
				<td>
					<a  href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询">
						<input type="text" toName="user.trueName" readonly="readonly" class="easyui-validatebox"  required="true"/>
					</a>
					<input type="hidden"  name="recipients" toName="user.id" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
						
				</td>
			</tr>
			<tr>
				<th>领用时间:</th>
				<td>
					
					<input type="text"  name="receiveTime"  class="easyui-validatebox" required="true"   readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					
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
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	<input type="hidden" name="currentCallback" value="close" />
			
			
		</table>	
  	
  </form>
	
</div>