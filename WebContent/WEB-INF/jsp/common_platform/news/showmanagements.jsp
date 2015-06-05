<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>


<table   width="100%"  style="border:1px solid #EEF4F5;padding-left: 10px;padding-right: 10px;">
	<c:forEach var="n" items="${news}">
		 <tr style="background:#FAFAFA;height:50px;">
				<td style="border:1px solid #EEF4F5;line-height: 20px;">
				 &nbsp; &nbsp;&nbsp;&nbsp;
						 <c:choose>
						               <c:when test="${!empty n.bycommentnameId}">
						                  <span style="color:#1D53BF"><my:outTrueName id="${n.conmmentsnameId }"/></span>  回复   <span style="font-size:16px;"><my:outTrueName id="${n.bycommentnameId }"/></span>: <my:scriptEscape value="${n.newscomments}" /> 
						               </c:when>
						               <c:otherwise>
						                   <span style="color:#1D53BF"><my:outTrueName id="${n.conmmentsnameId }"/></span>: <my:scriptEscape value="${n.newscomments}" /> 
						               </c:otherwise>
						             </c:choose>
						             	<span style="color:#A8B6B6;margin-left: 20px;"><fmt:formatDate value="${n.conmmentsdate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
						            </td> 
						            <td style="border:1px solid #EEF4F5;float:right;padding-top: 15px;width: 90px;">
						                		               
                                        <a style="margin-left: 10px;color:#1D53BF" href="news/answer.do?id=${param.id}&conmmentsname=${n.conmmentsnameId}&rel=${param.rel}"   target="dialog" width="1000" height="500" rel="对${param.rel}_anser" title="回复：<my:outTrueName id="${n.conmmentsnameId }"/>" >回复</a>
                                        | 
						                <a style="color:#1D53BF" href="news/deletecontent.do?id=${n.id}&newsid=${n.newsid}&&otherBoxId=${param.rel}&otherCallback=refresh" target="ajaxTodo"   title="确定要删除吗?">删除  </a>
						                
						            </td>
		</tr> 
	</c:forEach>
</table> 