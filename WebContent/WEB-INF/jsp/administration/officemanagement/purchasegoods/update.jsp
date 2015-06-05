<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品购买--修改--%>
<div style="width: 700px;margin: 0 auto;" >
<form method="post" action="purchasegoods/update.do"   onsubmit="return validateSubmitForm(this)">
	<table   class="table table-bordered ">
 
   			<tr>
				<th>物品名称:</th>
				<td>
	               
	     	        <input type="text" value="<c:out value="${requestScope.bi.usingname}"/>"   readonly="readonly" />
			       
				</td>
			</tr>
			<tr>
				<th >单位:</th>
				<td>
					<c:out value="${requestScope.bi.danwei}"/>
				</td>
			</tr>
			<tr>
				<th>单价:</th>            
				<td>
			    	<input type="text" cclass="easyui-numberbox" data-options="precision:2" required='true' value="<c:out value="${requestScope.bs.unitprice }" />"   name="unitprice" maxlength="10" size="50"  />
				</td>
			</tr>
			 <tr>      
				<th>购买数量:</th>
				<td>              
	     	  		 <input type="text" class="easyui-numberbox"  required='true' name="amount" value="<c:out value="${requestScope.bs.amount }"/>"   min="1" maxlength="11">
				</td>
			</tr>
			<tr>
				<th>购买人姓名:</th>
				<td>
					<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询">
						  <input type="text" toName="user.trueName" readonly="readonly" value="<my:outTrueName id="${requestScope.bs.purchaser }"/>"/>
					</a>
					<input type="hidden" value="${requestScope.bs.purchaser }"  name="purchaser" toName="user.id" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
					    
				</td>
			</tr>
			<tr>
				<th>购买时间:</th>
				<td>
					<input type="text"  name="buyTime"  class="easyui-validatebox" required="true"  value="<fmt:formatDate value="${requestScope.bs.buyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
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
			
			
		
			<input type="hidden" name="id" value="${requestScope.bs.id }" />
		
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />	
		 	<input type="hidden" name="currentCallback" value="close" />
		 
	</table>
  
  	</form>
	
</div>