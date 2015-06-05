<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：新闻管理--新闻内容极评论显示的条数
--%>
<c:set var="allowcomment"  value="${param.allowcomment }"></c:set>
<c:set var="id"  value="${param.id }"></c:set>
<c:if test="${!empty  n.allowcomment}">
	<c:set var="allowcomment"  value="${n.allowcomment}"></c:set>
	<c:set var="id"  value="${n.id }"></c:set>
</c:if>



<div style="height: 20px">
	
	<span class="badge badge-important" style="margin-left: 15px">${requestScope.total }</span>人参与
	
	<div style="float: right;margin-right: 10px">
		
		<c:if test="${allowcomment==0}">
				<a href="news/answer.do?id=${id}&rel=${param.rel}" class="btn btn-primary btn-small"  target="dialog" width="1000" height="500" rel="${param.rel}_anser" title="评论新闻" >评论</a>  
		</c:if>
	</div>

</div>

<div  style="width:98% ;border:1px solid #EEF4F5;margin: 10px auto;" >
<c:forEach var="n" items="${news}">
          
             <div style="margin-top: 5px;height: 20px">
            	 &nbsp; &nbsp;&nbsp;&nbsp;
              <c:choose>
                <c:when test="${!empty n.bycommentnameId}">
               
                 <span style="color:#1D53BF"><my:outTrueName id="${n.conmmentsnameId }"/></span> @   <span class="label label-info"><my:outTrueName id="${n.bycommentnameId }"/></span>
                </c:when>
                <c:otherwise>
                    <span style="color:#1D53BF"><my:outTrueName id="${n.conmmentsnameId }"/></span>
                </c:otherwise>
             	</c:choose>
             	：
             
             	<a style="float:right;margin-right: 10px;color: #1D53BF" href="news/answer.do?id=${param.id}&conmmentsname=${n.conmmentsnameId}&rel=${param.rel}"   target="dialog" width="1000" height="500" rel="${param.rel}_anser" title="回复：<my:outTrueName id="${n.conmmentsnameId }"/>" >回复</a>	               
             	<span style="float:right; margin-right: 10px;"><fmt:formatDate value="${n.conmmentsdate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
             	
              	
              
              </div> 
              
              
               <div style="width:98% ;margin: 5px auto;">	
                     <div class="well " >
              		<my:scriptEscape value="${n.newscomments}" />
              	</div>
               </div>  
             
            
    </c:forEach>
</div>
