<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--查看详情
--%>
		
<div style="width: 600px;margin: 0 auto;" >	


  	<table class="table table-bordered" >	
  	<c:forEach var="v" items="${vm}" >
  	   <tr>
				<th  style="width: 100px">车牌号:</th>
				<td>
				     <c:out value="${v.vm.licenseno}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>驾驶员:</th>
				<td>
	     	          <c:out value="${v.xm }"></c:out>
				</td>
			</tr>
			 <tr>
				<th>类型:</th>
				<td>
			<my:outSelectValue id="${v.vm.vehicletypeId }"/>
				</td>
			</tr>
			<tr>
				<th>购买日期:</th>
				<td>
				<fmt:formatDate  value="${v.vm.buydate}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			 <tr>
				<th>价格:</th>
				<td>
				<fmt:formatNumber value="${v.vm.price}"  pattern="#0.00" ></fmt:formatNumber>
				</td>
			</tr>
			 <tr>
				<th>发动机号:</th>
				<td>
					<c:out value="${v.vm.enginenumber}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>出产编号:</th>
				<td>
					<c:out value="${v.vm.pono }"></c:out>
				</td>
			</tr>
			<tr>
				<th>载重:</th>
				<td>
				      <c:out value="${v.vm.weight }"></c:out> 吨
				</td>
			</tr>
			<tr>
				<th>座位:</th>
				<td>
					<c:out value="${v.vm.seat }"></c:out> 个
				</td>
			</tr>
			 <tr>
				<th>使用单位:</th>
				<td>
					<c:out value="${v.vm.usingunit }"></c:out>
				</td>
			</tr>
			<tr>
				<th>强制险时间:</th>
				<td>
					<fmt:formatDate  value="${v.vm.insurancetime}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>商业险时间:</th>
				<td>
					<fmt:formatDate  value="${v.vm.cinsurancetime}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			 <tr>
				<th>状态:</th>
				<td>
				    <c:if test="${v.vm.status==0 }">在库</c:if>
				    <c:if test="${v.vm.status==1 }">使用中</c:if>
				    <c:if test="${v.vm.status==2 }">维修</c:if>
				    <c:if test="${v.vm.status==3 }">报废</c:if>
				
				</td>
			</tr>
			 <tr>
				<th> 使用者:</th>
				<td>
					<c:out value="${v.vm.usingpersonal }"></c:out>
				</td>
			</tr>
				<tr>
			<th>创建日期:</th>
			<td>
			 <fmt:formatDate  value="${v.vm.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			</tr>
			<tr>
			<th>创建人:</th>
			<td>
			<my:outTrueName id="${v.vm.promulgator}"/>
			</td>
			</tr>
			
		</c:forEach>                        
  	</table>
  	
	
</div>