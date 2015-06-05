<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--驾驶员管理--查看驾驶员详情 
--%>

<div style="width: 600px;margin: 0 auto;" >	
  		<table class="table table-bordered" >	
  	   <tr>
				<th style="width: 100px">姓名:</th>
				<td>
				     <c:out value="${vt.drivingname}"></c:out>
				</td>
			</tr>
			 <tr >
					<th >性别:</th>
					<td>
					<c:if test="${vt.gender==0}">
					男
					</c:if>
					<c:if test="${vt.gender==1}">女</c:if>
					</td>
			</tr>
			 <tr>
				<th>驾驶证号:</th>
				<td>
			         <c:out value="${vt.drivingno}"></c:out>  
				</td>
			</tr>
			 <tr>
				<th>出生日期:</th>
				<td>
				<fmt:formatDate value="${vt.birthday }" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			 <tr>
				<th>领证日期:</th>
				<td>
					<fmt:formatDate value="${vt.registerdate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			 <tr>
				<th>证件到期时间:</th>
				<td>
					<fmt:formatDate value="${vt.papervaliddate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			 <tr>
				<th>驾龄:</th>
				<td>
					<c:out value="${vt.drivingage }"></c:out>
				</td>
			</tr>
			 <tr>
				<th>联系电话:</th>
				<td>
					<c:out value="${vt.phone}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>准驾车型:</th>
				<td>
				     <c:out value="${vt.allowdriving}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>单位或地址:</th>
				<td>
				     <c:out value="${vt.address}"></c:out>
				</td>
			</tr>
			<tr>
			<th>创建日期:</th>
			<td>
			 <fmt:formatDate  value="${vt.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			</tr>
			<tr>
			<th>创建人:</th>
			<td>
			<my:outTrueName id="${vt.promulgator}"/>
			
			</td>
			</tr>
			 <tr>
				<th>年检记录:</th>
				<td>
					<c:out value="${vt.checkrecord }"></c:out>
				</td>
			</tr>
			
  	</table>
  	
	
</div>