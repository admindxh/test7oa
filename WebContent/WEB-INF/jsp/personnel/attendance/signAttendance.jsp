<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-考勤管理-上下班考勤打卡
--%>
<div style="margin-top: 10px">
		<span class="label label-warning">
			说明：
		</span>
		 <c:if test="${!empty requestScope.att  }">
		<span class="alert" >规定上班时间之前 <c:out value="${requestScope.att.upstart }"/>分钟到
		之后 <c:out value="${requestScope.att.upend }"/>分钟这段时间可进行上班登记。规定下班时间之前<c:out value="${requestScope.att.downstart }"/> 分钟到
		之后 <c:out value="${requestScope.att.downend }"/> 分钟这段时间可进行下班登记。</span>&nbsp;&nbsp;
		  </c:if>
		 <c:if test="${empty requestScope.att  }">
		 <span class="alert" >请先关联用户与考勤登记时间再进行考勤登记。</span>
		 </c:if>
	</div>	
<div style="width: 600px;margin: 0 auto;" >						
 	
	<table class="table table-bordered table-striped center" >
		<thead>
			<tr>
				<th width="60px" >序号</th>
				<th width="110px" >登记类别</th>
				<th width="100px" >规定时间</th>
				<th width="100px" >登记时间</th>
				<th  >操作</th>
			</tr>
 		</thead>
 		<tbody >	
			<tr >
				<td style="text-align: center;" >1</td>
				<td style="text-align: center;" >第一次上班登记</td>
				<td style="text-align: center;">
					<c:out value="${requestScope.att.firstup }"/> 
				</td>
				<td style="text-align: center;"> 
				<c:if test="${!empty requestScope.att.firstup  }">
					<c:choose>
						<c:when test="${!empty requestScope.sign1 }">
							<c:out value="${requestScope.sign1.signtime }"/>
						</c:when>
						<c:otherwise>
							尚未登记 
						</c:otherwise>
					</c:choose>
				</c:if>	
				</td>
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.firstup  }">
					<c:choose>
						<c:when test="${empty requestScope.sign1 }">
							 <c:choose>
								<c:when test="${requestScope.fu=='1' }">
							 		<a href="attendance/signAttendance.do?type=1&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo" class="btn btn-primary btn-small" title="确认进行考勤上班登记吗?" >登记</a>  
								</c:when>
								<c:otherwise>
									 不在允许的登记时间段内
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							 已经登记
						</c:otherwise>
					</c:choose> 
				</c:if>	
				</td> 
			</tr>
			<tr>
				<td style="text-align: center;" >2</td>
				<td style="text-align: center;">第一次下班时间</td>
				<td style="text-align: center;">					
					<c:out value="${requestScope.att.firstdown }"/>
				</td> 
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.firstdown  }">
					<c:choose>
						<c:when test="${!empty requestScope.sign2 }">
							 <c:out value="${requestScope.sign2.signtime }"/>
						</c:when>
						<c:otherwise>
							尚未登记
						</c:otherwise>
					</c:choose>
				</c:if>	
				</td>
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.firstdown  }">
					<c:choose>
						<c:when test="${empty requestScope.sign2 }">
							 <c:choose>
								<c:when test="${requestScope.fd=='2' }">
							 		<a href="attendance/signAttendance.do?type=2&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo" class="btn btn-primary btn-small" title="确认进行考勤下班登记吗?" >登记</a>  
								</c:when>
								<c:otherwise>
									 不在允许的登记时间段内
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							 已经登记
						</c:otherwise>
					</c:choose> 
				</c:if>	
				</td>
 			</tr>
			<tr>
				<td style="text-align: center;" >3</td>
				<td style="text-align: center;">第二次上班时间</td>
				<td style="text-align: center;">
					<c:out value="${requestScope.att.secondup }"/>
				</td> 
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.secondup  }">
					<c:choose>
						<c:when test="${!empty requestScope.sign3 }">
							<c:out value="${requestScope.sign3.signtime }"/>
						</c:when>
						<c:otherwise>
						尚未登记 	
						</c:otherwise>
					</c:choose>
				</c:if>
				</td>
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.secondup  }">
					<c:choose>
						<c:when test="${empty requestScope.sign3 }">
							 <c:choose>
								<c:when test="${requestScope.su=='3' }">
							 		<a href="attendance/signAttendance.do?type=3&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo" class="btn btn-primary btn-small" title="确认进行考勤上班登记吗?" >登记</a>  
								</c:when>
								<c:otherwise>
									 不在允许的登记时间段内
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							 已经登记
						</c:otherwise>
					</c:choose> 
				</c:if>	
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" >4</td>
				<td style="text-align: center;">第二次下班时间</td>
				<td style="text-align: center;">					
					<c:out value="${requestScope.att.seconddown }"/>
				</td> 
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.seconddown  }">
					<c:choose>
						<c:when test="${!empty requestScope.sign4 }">
							<c:out value="${requestScope.sign4.signtime }"/>
						</c:when>
						<c:otherwise>
							尚未登记 
						</c:otherwise>
					</c:choose>
				</c:if>
				</td>
				<td style="text-align: center;">		
				<c:if test="${!empty requestScope.att.seconddown  }">
					<c:choose>
						<c:when test="${empty requestScope.sign4 }">
							 <c:choose>
								<c:when test="${requestScope.sd=='4' }">
							 		<a href="attendance/signAttendance.do?type=4&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo" class="btn btn-primary btn-small" title="确认进行考勤下班登记吗?" >登记</a>  
								</c:when>
								<c:otherwise>
									 不在允许的登记时间段内
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							 已经登记
						</c:otherwise>
					</c:choose> 
				</c:if>	
				</td>	
				</tr>	
			<tr>
				<td style="text-align: center;" >5</td>
				<td style="text-align: center;">第三次上班时间</td>
				<td style="text-align: center;">
					<c:out value="${requestScope.att.thirdup }"/>
				</td> 
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.thirdup  }">
					<c:choose>
						<c:when test="${!empty requestScope.sign5 }">
							<c:out value="${requestScope.sign5.signtime }"/>
						</c:when>
						<c:otherwise>
							尚未登记 
						</c:otherwise>
					</c:choose>
				</c:if>
				</td>
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.thirdup  }">
					<c:choose>
						<c:when test="${empty requestScope.sign5 }">
							 <c:choose>
								<c:when test="${requestScope.tu=='5' }">
							 		<a href="attendance/signAttendance.do?type=5&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo" class="btn btn-primary btn-small" title="确认进行考勤上班登记吗?" >登记</a>  
								</c:when>
								<c:otherwise>
									 不在允许的登记时间段内
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							 已经登记
						</c:otherwise>
					</c:choose> 
				</c:if>	
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" >6</td>
				<td style="text-align: center;">第三次下班时间</td>
				<td style="text-align: center;">					
					<c:out value="${requestScope.att.thirddown }"/>
				</td> 
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.thirddown  }">
					<c:choose>
						<c:when test="${!empty requestScope.sign6 }"> 
							<c:out value="${requestScope.sign6.signtime }"/>
						</c:when>
						<c:otherwise>
						尚未登记 	
						</c:otherwise>
					</c:choose>
				</c:if>
				</td>
				<td style="text-align: center;">
				<c:if test="${!empty requestScope.att.thirddown  }">
					<c:choose>
						<c:when test="${empty requestScope.sign6 }">
							 <c:choose>
								<c:when test="${requestScope.td=='6' }">
							 		<a href="attendance/signAttendance.do?type=6&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo" class="btn btn-primary btn-small" title="确认进行考勤下班登记吗?" >登记</a>  
								</c:when>
								<c:otherwise>
									 不在允许的登记时间段内
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							 已经登记
						</c:otherwise>
					</c:choose> 
				</c:if>	
				</td>	
			</tr>			
			  
			 
			</tbody>
	</table>
	 
</div>									
						
					
		

	

