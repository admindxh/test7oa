<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品领用--查看详情页面--%>
<div style="width: 600px;margin: 0 auto;" >	
  	<table class="table table-bordered" >
  		
   			<tr>
				<th style="width:80px" >物品名称:</th>
				<td><c:out value="${requestScope.bi.usingname}"/></td>
			</tr>
			<tr>
				<th >单位:</th>
				<td><c:out value="${requestScope.bi.danwei}"/>  </td>
			</tr>
			<tr>
				<th>领用数量:</th>            
				<td>${requestScope.bv.usenumber}</td>
			</tr>
			 <tr>
				<th>领用人:</th>
				<td> <my:outTrueName id="${requestScope.bv.recipients}"/>
				</td>
			</tr>
			<tr>
			     <th>领用时间:</th>
			     <td> <fmt:formatDate value="${requestScope.bv.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
			<tr>
			     <th>创建人 :</th>
			     <td><my:outTrueName id="${requestScope.bv.userid }"   /></td>
			</tr>
			<tr>
			     <th>创建时间:</th>
			     <td> <fmt:formatDate value="${requestScope.bv.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
			
  	</table>
	
</div>