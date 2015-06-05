<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
	主页
--%>

			
	<%--<iframe width="100%" layoutH="5"  frameborder="0"  src="http://www.iaphone.com/"></iframe>

	--%>
	
	<%--<iframe class="edui-faked-webapp" title="365日历" src="http://app.baidu.com/app/enter?appid=114629&amp;tn=app_canvas&amp;app_spce_id=1&amp;apikey=9HrmGf2ul4mlyK8ktO2Ziayd&amp;api_key=9HrmGf2ul4mlyK8ktO2Ziayd" logo_url="http://apps.bdimg.com/store/static/kvt/340238f487321ab424dd471680e314a3.jpg" frameborder="0" height="500" scrolling="no" width="560"></iframe>

--%>
<%--<iframe name="weather_inc" src="http://www.tianqi.com/index.php?c=code&id=2&num=3" width="440" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>


--%>
<%--左侧DIV--%>

	<div class="panel collapse"   defH="70" style="width: 50%;float: left">
		<h1>天气预报 
			
		</h1>
		<div>
			 <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=2&num=3" width="440" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
		</div>
	</div>
	
			<div class="panel collapse"  defH="70" style="width: 50%;float: left">
			<h1>个人信息
				
			</h1>
			<div>
				<div style="width: 49%;float: left;">
					<div style="margin-top: 8px;margin-left: 10px;">姓名：${requestScope.user.trueName }</div>
					<div style="margin-top: 8px;margin-left: 10px;">性别：<c:choose><c:when test="${requestScope.user.userSex==1}">男</c:when><c:otherwise>女</c:otherwise></c:choose></div>
					
				</div>
				
				<div style="width: 49%;float: left;">
					<div style="margin-top: 8px;margin-left: 10px;">部门：<my:outDeptName id="${requestScope.user.deptId }" /></div>
					<div style="margin-top: 8px;margin-left: 10px;">
					角色:
						<c:forEach var="rid" items="${sessionScope.minfo.roleIds }" >
							[<my:outRoleName id="${rid }" />]
						</c:forEach>
					</div>
				</div>
			
				
				
			
			
			</div>
		</div>
	
	
	<c:if test="${requestScope.affiche!=null }">

		<div class="panel collapse"  defH="150" style="width: 50%;float: left">
			<h1>系统公告 
				<a href="affiche/load/my.do?rel=gtpt_xtgg" title="系统公告" target="navTab" rel="gtpt_xtgg" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
			</h1>
			<div>
				<table class="list" width="100%" >
					<thead>
						<tr>
							<th align="center">标题</th>
							<th width="150">发送时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="afc" items="${requestScope.affiche }" >
							<tr>
								<td title="${afc.afficheTitle }">
									<a href="affiche/show.do?id=${afc.id }" target="navTab" rel="home_xtgg_show">
										<c:if test="${afc.isTop==1}"><img src="resource/images/arrow_up.gif"  style="margin-top: 3px;"/></c:if>
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
		</div>
	</c:if>
	
	
		<c:if test="${requestScope.afficheDept !=null}">
			<div class="panel collapse"  defH="150" style="width: 50%;float: left">
				<h1>部门公告
					<a href="affiche/dept/load/my.do?rel=gtpt_bmgg" title="部门公告" target="navTab" rel="gtpt_bmgg" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
				</h1>
				<div>
					<table class="list" width="100%" >
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
										<a href="affiche/dept/show.do?id=${afcd.id }" target="navTab" rel="home_xtggdept_show">
											<c:if test="${afcd.isTop==1}"><img src="resource/images/arrow_up.gif"  style="margin-top: 3px;"/></c:if>
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
			</div>
		</c:if>
	
	<c:if test="${ requestScope.schedule!=null }">
		<div class="panel collapse"  defH="150" style="width: 50%;float: left">
			<h1>今日日程
				<a href="schedule/load.do?rel=grbg_rcap" title="日程安排" target="navTab" rel="grbg_rcap" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
			</h1>
			<div>
				<table class="list" width="100%" >
					<thead>
						<tr>
							<th align="center">主题</th>
							<th width="150">开始时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sch" items="${requestScope.schedule }" >
							<tr>
								<td title="${sch.schTitle }">
									<a href="schedule/show.do?id=${sch.id }" target="navTab" rel="home_xtggdept_show">
										<c:out value="${sch.schTitle }" />
									</a>
								</td>
								<td >
									<fmt:formatDate value="${sch.startTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	
			
		<c:if test="${ requestScope.meetings !=null }">
			<div class="panel collapse"  defH="150" style="width: 50%;float: left">
				<h1>我的会议
					<a href="meeting/loadtome.do" title="我的会议" target="navTab" rel="ggpt_hygl" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
				</h1>
				<div>
					<table class="list" width="100%" >
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
										<a href="meeting/find.do?id=${meet.id }" target="navTab" rel="home_hy_find">
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
			</div>
		</c:if>
	
	<c:if test="${requestScope.news!=null }">
		<div class="panel collapse"  defH="150" style="width: 50%;float: left">
			<h1>新闻
				<a href="news/load_news.do?rel=grbg_xw" title="新闻" target="navTab" rel="grbg_xw" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
			</h1>
			<div>
				<table class="list" width="100%" >
						<thead>
							<tr>
								<th align="center">新闻标题</th>
								<th width="150">发布时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="xw" items="${requestScope.news }" >
								<tr>
									<td title="${xw.newstitle}"><a href="news/look.do?id=${xw.id}&rel=grbg_xw_look" target="navTab" rel="grbg_xw_look">${xw.newstitle}</a></td>
									<td title="${xw.createdtime}"><fmt:formatDate value="${xw.createdtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</c:if>
	
	<c:if test="${ requestScope.permsgs !=null }">	
			<div class="panel collapse"  defH="150" style="width: 50%;float: left">
				<h1>消息提醒<a href="msgwarn/load.do?rel=grbg_xxtx" title="消息提醒" target="navTab" rel="grbg_xxtx" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
				</h1>
				<div>
					<table class="list" width="100%" >
						<thead>
							<tr>
								<th align="center">消息</th>
								<th width="150">发送时间</th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach var="permsg" items="${requestScope.permsgs }" >
								<tr>
								<td>
									<c:choose>
										<c:when test="${permsg.msgType==1}">
											<a href="msgwarn/show.do?id=${permsg.id }" target="navTab" rel="${param.rel}_show">
												您有新通知，请及时查看！
											</a>
										</c:when>
										<c:when test="${permsg.msgType==2}">
											<a href="msgwarn/show.do?id=${permsg.id }" target="navTab" rel="${param.rel}_show">
												您有新会议，请及时查看！
											</a>
										</c:when>
										<c:when test="${permsg.msgType==3}">
											<a href="msgwarn/show.do?id=${permsg.id }" target="navTab" rel="${param.rel}_show">
												有新发布的新闻，请及时查看！
											</a>
										</c:when>
										<c:when test="${permsg.msgType==4}">
											<a href="msgwarn/show.do?id=${permsg.id }" target="navTab" rel="${param.rel}_show">
												您有新共享日志，请及时查看！
											</a>
										</c:when>
										<c:otherwise>无此类型</c:otherwise>
									</c:choose>
											
								</td>
						
								<td >
									<fmt:formatDate value="${permsg.msgTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								
								
								
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					
					
					
				</div>
			</div>
		</c:if>
	
	
	<c:if test="${requestScope.emails!=null }">
		<div class="panel collapse"  defH="150" style="width: 50%;float: left">
			<h1>未读邮件
				<a href="email/loadIn.do?rel=grbg_nbyj_sjx&status=0" title="未读邮件" target="navTab" rel="grbg_nbyj_sjx" style="float: right;margin-right: 15px;" ><img src="resource/images/more.png"  style="margin-top: 5px;" alt="更多" title="更多"/></a>
			</h1>
			<div>
				<table class="list" width="100%" >
						<thead>
							<tr>
								<th align="center">标题</th>
								<th width="150">发送时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="v" items="${requestScope.emails }" >
								<tr>
									<td title="<c:out value="${v.ei.eiName}" />">
										<a href="email/findIn.do?id=${v.ei.id}" target="navTab" rel="grbg_nbyj_sjx_find">
											<c:out value="${v.ei.eiName}" />
										</a>
									</td>
									<td ><fmt:formatDate value="${v.ei.eiDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					
								</tr>
								
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</c:if>
	
	
	




	


		


