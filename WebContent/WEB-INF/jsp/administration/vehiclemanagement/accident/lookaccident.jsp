<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
行政办公--车辆管理--事故管理--查看事故管理查看详情
--%>

<div style="width: 600px;margin: 0 auto;" >	
  		<table class="table table-bordered" >	
  			<c:forEach var="v" items="${vd}" >
  	  		 <tr>
				<th style="width: 100px">车牌号:</th>
				<td>
	     	       <c:out value="${v.cph }"></c:out>
				</td>
			</tr>
			 <tr>
				<th>驾驶员:</th>
				<td>
				   <c:out value="${v.jsy }"></c:out>	
				</td>
			</tr>
			 <tr>
				<th>发生时间:</th> 
				<td>
			 	  <fmt:formatDate value="${v.vd.happendate}" pattern="yyyy-MM-dd"/> 
				</td>
			</tr>   
			 <tr>
				<th>发生地点:</th>
				<td>
		             <c:out value="${v.vd.happenplace }"></c:out>
				</td>
			</tr>
			 <tr>
				<th>事故确认者:</th>
				<td>
			        <c:out value="${v.vd.accidentconfirm}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>保险理赔金额:</th>
				<td>
				<fmt:formatNumber value="${v.vd.insurancecompensation}"  pattern="#0.00" ></fmt:formatNumber>
				     
				</td>
			</tr>
			 <tr>
				<th>对方姓名:</th>
				<td>
					<c:out value="${v.vd.byname}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>对方住址:</th>
				<td>
				    <c:out value="${v.vd.byaddress}"></c:out>
				</td>
			</tr>
			 <tr>
				<th>对方电话:</th>
				<td>
				       <c:out value="${v.vd.byphone}"></c:out> 
				</td>
			</tr>
			 <tr>
				<th>公司暂负金额:</th>
				<td>
				<fmt:formatNumber value="${v.vd.cgmoney}"  pattern="#0.00" ></fmt:formatNumber>
					
				</td>
			</tr>
			<tr>
				<th>本人负担金额:</th>
				<td>
				<fmt:formatNumber value="${v.vd.selfgmomey}"  pattern="#0.00" ></fmt:formatNumber>
					
				</td>
			</tr>
			<tr>
				<th>
					对方车牌号码:
				</th>
				<td>
				     <c:out value="${v.vd.bylicensenumberId }"></c:out>
				</td>
			</tr>
			<tr>
				<th>创建日期:</th>
					<td>
					   <fmt:formatDate  value="${v.vd.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
			</tr>
			<tr>
			<th>创建人:</th>
			<td>
		             <my:outTrueName id="${v.vd.promulgator}"/>
			</td>
			</tr>
			<tr>
				<th>事故概要:</th>
				<td>
             		 ${v.vd.summary}
				</td>
			</tr>
			 <tr>
				<th>和解内容:</th>
				<td>
						${v.vd.contentreconciliation }
				</td>
			</tr> 	
			</c:forEach>
   	</table>
	
</div>