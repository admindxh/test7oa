<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--办公用品管理--物品购买--查看详情页面--%>

<div style="width: 600px;margin: 0 auto;" >	
  	<table class="table table-bordered" >
  		
  			<tr>
				<th style="width:80px">物品名称:</th>
				<td>${requestScope.bi.usingname}</td>
			</tr>
			<tr>
				<th >单位:</th>
				<td><c:out value="${requestScope.bi.danwei}"/>  </td>
			</tr>
			<tr>
				<th>单价:</th>            
				<td><fmt:formatNumber value="${requestScope.bs.unitprice }" pattern="##.00"></fmt:formatNumber></td>
			</tr>
			<tr>      
				<th>数量:</th>
				<td> ${requestScope.bs.amount }</td>
			</tr>
			<tr>
			     <th>总额:</th>
			     <td><fmt:formatNumber value="${requestScope.bs.totalmonety }" pattern="###.00"></fmt:formatNumber> </td>
			</tr>
			<tr>
				<th>购买人姓名:</th>
				<td>
					 <my:outTrueName id="${requestScope.bs.purchaser }"/>
				</td>
			</tr>
			<tr>
			  <th>购买时间:</th>
			  <td><fmt:formatDate value="${requestScope.bs.buyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
			    <th>创建人:</th>
                <td><my:outTrueName id="${requestScope.bs.userid }"  /></td>
			</tr>
			<tr>
			  <th>创建时间:</th>
			  <td><fmt:formatDate value="${requestScope.bs.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			
			
		</table>	
  
</div>