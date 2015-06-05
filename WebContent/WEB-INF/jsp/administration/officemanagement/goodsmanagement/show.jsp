<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品管理--查看页面--%>

<div style="width: 600px;margin: 0 auto;" >	
  	<table class="table table-bordered" >	
   			<tr>
				<th style="width:80px">物品名称:</th>
				<td>${bi.usingname}</td>
			</tr>
			 <tr>      
				<th>类别:</th>
				<td><my:outSelectValue id="${bi.typeId }"/> </td>
			</tr>
			 <tr>
				<th>规格型号:</th>            
				<td>${bi.specificationstype}</td>
			</tr>
			 <tr>
				<th>库存量:</th>
				<td>${bi.currentinventory}</td>
			</tr>
			 <tr>
				<th>单位:</th>
				<td>${bi.danwei}</td>
			</tr>
			 <tr>
				<th>备注:</th>
				<td>
					${bi.remark}
				</td>
			</tr>
			<tr>
			    <th>创建人:</th>
			    <td> <my:outTrueName id="${bi.userid }"  /></td>
			</tr>
			<tr>
				<th>创建日期:</th>
				<td><fmt:formatDate value="${bi.createdate }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
			
  </table>
  	
	
</div>