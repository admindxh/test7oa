<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>

<%--
	模块：个人办公--个人投票界面
--%>
<%
	int i=0;
 %>

<div style="width: 96%;margin: 0 auto;" >
		
		<form method="post" action="vote/addpertp.do"   onsubmit="return validateSubmitForm(this)" >
	
			
			<table title="投票" style="width: 100%" >
				<caption><h3><c:out value="${vo.tpTitle}" /></h3></caption>
				<tbody>
				<c:forEach var="v" items="${bl}" varStatus="status1" >
					<tr>
						<th style="width: 20px">
							
							<span class="badge" style="margin-left: 4px">${status1.count }</span>
							
						</th>
						<td>
							<div style="font-weight: bolder;margin-left: 5px">${v.question.title}</div>
						
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<div style="margin-top: 5px">
								
								<c:if test="${v.question.type!=2}">
								<ol>
								<c:forEach var="t" items="${v.list}" varStatus="status" >
									<li>
									<c:if test="${v.question.type==0}">
										
												<label class="radio" style="display: inline-block;"><input type="radio"  name="fil[<%=i %>].votedetailid" value="${t.id}" onclick="myRadio(this)" radioType="${v.question.id}">&nbsp;&nbsp;${t.optiondesc}</label>
												<input type="hidden" name="fil[<%=i %>].titleid" value="${v.question.id}">
												<input type="hidden" name="fil[<%=i++ %>].voteid" value="${vo.id}">
										
									</c:if>
									<c:if test="${v.question.type==1}">
										
												<label class="checkbox" style="display: inline-block;"><input name="fil[<%=i %>].votedetailid" value="${t.id}" type="checkbox">${t.optiondesc}</label>
												<input type="hidden" name="fil[<%=i %>].titleid" value="${v.question.id}">
												<input type="hidden" name="fil[<%=i++ %>].voteid" value="${vo.id}">
											
									</c:if>
									</li>
								</c:forEach>
								</ol>
								</c:if>
								<c:if test="${v.question.type==2}">
										<textarea  name="fil[<%=i %>].textarea"  rows="2" style="width: 400px"></textarea >
												<input type="hidden" name="fil[<%=i %>].titleid" value="${v.question.id}">
												<input type="hidden" name="fil[<%=i++ %>].voteid" value="${vo.id}">
											
								</c:if>	
								
							</div>
						
						</td>
						
					</tr>
					
				</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<th></th>
						<td >
							<div  style="width: 200px; margin: 0 auto;">
								  <button type="submit" class="btn btn-primary" >投票</button>
								 
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		
			<input type="hidden" name="voteid" value="${vo.id}"> 
			<input type="hidden" name="otherCallback" value="refresh" />	
		 	<input type="hidden" name="currentCallback" value="close" />
		 	<input type="hidden" name="otherBoxId" value="${param.rel}" />	
	
	
	</form>
</div>
