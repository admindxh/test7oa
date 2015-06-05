<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>



	<c:if test="${requestScope.affiche!=null }">

	
		<div id="p1" title="系统公告" collapsible="true" >
			
			<table class="table table-bordered table-condensed table-striped center" style="width: 99%;margin: 3px auto;" >
				<thead>
					<tr>
						<th align="center">标题</th>
						<th width="150">发送时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="afc" items="${requestScope.affiche }" >
						<tr >
							<td title="${afc.afficheTitle }" >
								<a href="affiche/show.do?id=${afc.id }" target="dialog" width="1000" height="450" title="系统公告详情"  rel="home_xtgg_show">
									<c:if test="${afc.isTop==1}"><img src="resource/images/arrow_up.gif"  style="margin-top: -3px;"/></c:if>
									<c:out value="${afc.afficheTitle }" />
								</a>
							</td>
							<td >
								<fmt:formatDate value="${afc.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
				
		</div>
		
	</c:if>
	
	
		<c:if test="${requestScope.afficheDept !=null}">
			
			<div id="p2"  title="部门公告">
				<table class="table table-bordered table-condensed table-striped center" style="width: 99%;margin: 3px auto;" >
					<thead>
						<tr>
							<th align="center">标题</th>
							<th width="150">发送时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="afcd" items="${requestScope.afficheDept }" >
							<tr>
								<td title="${afcd.afficheTitle }">
									<a href="affiche/dept/show.do?id=${afcd.id }" target="dialog" width="1000" height="450" title="部门公告详情" rel="home_xtggdept_show">
										<c:if test="${afcd.isTop==1}"><img src="resource/images/arrow_up.gif"  style="margin-top: -3px;"/></c:if>
										<c:out value="${afcd.afficheTitle }" />
									</a>
								</td>
								<td >
									<fmt:formatDate value="${afcd.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		</c:if>
	
	
	
			
		<c:if test="${ requestScope.meetings !=null }">
			
			<div id="p3" title="我的会议">
				<table class="table table-bordered table-condensed table-striped center" style="width: 99%;margin: 3px auto;" >
					<thead>
						<tr>
							<th align="center">会议标题</th>
							<th width="150">会议日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="meet" items="${requestScope.meetings }" >
							<tr>
								<td title="${meet.MName }">
									<a href="meeting/find.do?id=${meet.id }"  target="dialog"  width="1200" height="450" title="会议详情" rel="home_hy_find">
										<c:out value="${meet.MName }" />
									</a>
								</td>
								<td >
									<fmt:formatDate value="${meet.MDate}" pattern="yyyy-MM-dd" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		</c:if>
	
	<c:if test="${requestScope.news!=null }">
		
		<div id="p4"  title="新闻">
			<table class="table table-bordered table-condensed table-striped center" style="width: 99%;margin: 3px auto;">
					<thead>
						<tr>
							<th align="center">新闻标题</th>
							<th width="150">发布时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="xw" items="${requestScope.news }" >
							<tr>
								<td title="${xw.newstitle}"><a href="news/look.do?id=${xw.id}&rel=grbg_xw_look" target="navTab" rel="grbg_xw_look" title="新闻详情">${xw.newstitle}</a></td>
								<td title="${xw.createdtime}"><fmt:formatDate value="${xw.createdtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		
	</c:if>
	

