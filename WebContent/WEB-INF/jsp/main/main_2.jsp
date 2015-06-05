<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%@include file="/WEB-INF/jsp/commons/jstl_message_tld.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><fmt:message key="app.name" bundle="${commonBundle}"/></title>
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<%@ include file="/WEB-INF/jsp/commons/include.head.jsp"%>
	

</head>
<body >
	<div id="sy_layout" class="easyui-layout" fit="true">
	
		<!-- 顶部-->
		<div  region="north" border="false"  style="height: 40px; padding: 1px; overflow: hidden;" >
			<div style="float: left;">
				<img src="resource/images/logo/logo.png"   style="margin-left: 5px;"/>
			
			</div>
			
			<div style="padding-top:5px">
				
					<div style="float: right;">
						<a href="javascript:void(0);" class="easyui-menubutton"
							menu="#layout_north_gryhMenu" iconCls="ui-icon-cog">当前用户：<my:outLoginInfo type="trueName"/></a>
						
					</div>
					
					<%-- 隐藏菜单 --%>
					<div id="layout_north_gryhMenu"	style="width:150px;display: none;">
					
						<div iconCls="icon-back" onclick="goOutSystem()">安全退出</div>
						<div class="menu-sep"></div>
						<div>
							<span>个人中心</span>
							<div style="width:150px;">
								<div onclick="MUI.openDialog('个人信息','user/my/info.do?rel=grbg_grse_myinfo','grbg_grse_myinfo',{width:1000,height:450})">个人信息</div>		
								<div onclick="MUI.openDialog('登陆日志','log/login/my.do?rel=grbg_grse_log','grbg_grse_log',{width:1000,height:450})">登陆日志</div>
								<div onclick="MUI.openDialog('登陆管理','user/my/loginInfo.do?rel=main_login_info','main_login_info',{width:1000,height:450})">登陆管理</div>
								<div onclick="MUI.openDialog('修改密码','user/updateMyPwPage.do?rel=grsz_xgmm','grsz_xgmm',{width:750,height:350})">修改密码</div>
							</div>
						</div>
						<div>
							<span>更换主题</span>
							<div style="width:150px;">
								<div onclick="changeEasyUITheme('default');">default</div>
								<div onclick="changeEasyUITheme('gray');">gray</div>
								<div onclick="changeEasyUITheme('bootstrap');">bootstrap</div>
								<div onclick="changeEasyUITheme('black');">black</div>
								<div class="menu-sep"></div>
								<div onclick="changeEasyUITheme('cupertino');">cupertino</div>
								<div onclick="changeEasyUITheme('dark-hive');">dark-hive</div>
								<div onclick="changeEasyUITheme('pepper-grinder');">pepper-grinder</div>
								<div onclick="changeEasyUITheme('sunny');">sunny</div>
								<div class="menu-sep"></div>
								<div onclick="changeEasyUITheme('metro');">metro</div>
								<div onclick="changeEasyUITheme('metro-blue');">metro-blue</div>
								<div onclick="changeEasyUITheme('metro-gray');">metro-gray</div>
								<div onclick="changeEasyUITheme('metro-green');">metro-green</div>
								<div onclick="changeEasyUITheme('metro-orange');">metro-orange</div>
								<div onclick="changeEasyUITheme('metro-red');">metro-red</div>
							</div>
						</div>
					
						<div iconCls="icon-help">  
							 <a	href="user_help/index.jsp" target="_blank" >在线帮助</a>
						</div>
						
					</div>
					
				
					<div id="div_top_2" >
					
						
						<shiro:hasPermission name="instantMessage:read"> 
						
							<div >
							<a  href="instantMessage/load.do?rel=top_grbg_jsgt"  target="dialog"  width="1000" height="450" rel="top_grbg_jsxgt" title="即时沟通">
								<img src="resource/images/menu/reply.gif" alt="即时沟通" style="vertical-align:middle;"/>(<span style="color: red" id="ft_jhst">0</span>)</a>
							</div>	
						</shiro:hasPermission>
					
						
							<div >
							<a  href="msgwarn/load.do?rel=top_grbg_xxtx" title="消息提醒"  target="dialog"  width="1000" height="450"  rel="top_grbg_xxtx">
								<img src="resource/images/menu/msg.gif" alt="消息提醒" style="vertical-align:middle;"/>(<span style="color: red" id="ft_xxtx">0</span>)</a>
							</div>
						
						
						<shiro:hasPermission name="schedule:read"> 	
							<div >
							<a  href="schedule/load.do?rel=top_grbg_rcap" title="日程安排"  target="dialog"  width="1000" height="450" rel="top_grbg_rcap">
								<img src="resource/images/menu/Schedule.gif" alt="日程安排" style="vertical-align:middle;"/>(<span style="color: red" id="ft_rcap">0</span>)</a>
							</div>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="email/loadIn.do">
						
							<div >
							<a  href="email/loadIn.do?rel=top_grbg_nbyj_sjx&status=0" title="未读邮件"  target="dialog"  width="1000" height="450" rel="top_grbg_nbyj_sjx">
								<img src="resource/images/menu/mail_new.gif" alt="未读邮件" style="vertical-align:middle;"/>(<span style="color: red" id="ft_wdyj">0</span>)</a>
							</div>
						</shiro:hasPermission>
						
						
						<shiro:hasPermission name="task:read"> 		
						<div >
							<a  href="task/todo/load.do?rel=top_gzlc_dbrw"  target="dialog"  width="1000" height="450" rel="top_gzlc_dbrw" title="待办任务">
							<img src="resource/images/menu/flow.gif" alt="待办任务" style="vertical-align:middle;"/>(<span style="color: red" id="ft_flow_haveDo">0</span>)</a>
						</div>
						</shiro:hasPermission>
						
						<div style="width: 200px;"><span id="clock"  ></span></div>
					
				
						
				
					</div>
				
			</div>
		</div>
		
		<!-- 中间工作区-->
		<div id="mainPanle" region="center" style="overflow: hidden;padding:1px">
			
			<div  class="easyui-tab" id="mainWorkTab" fit="true" border="false" tabHeight="35" >
				<!-- 首页 -->
				<div   title="<img src='resource/images/menu/1_close.png'/> 首页"  >
							
					   <div id="sy_layout" class="easyui-layout" fit="true">
								<!-- 左侧-->
								<div region="west" split="true"  title="导航菜单" style="width:230px; padding: 1px;">
									<%@ include file="/WEB-INF/jsp/main/left.jsp"%>
								</div>
								<!-- 中间-->
								<div  region="center" style="overflow: hidden;border-top: none" >
								
									<div  class="maintabs"  fit="true" border="false" >
										<div  id="tab-mainPage" title="首页"  href="main/home.do"	tools="#tab-mainPage_tools">
											
										</div>
										
									</div>
								</div>
					  </div>
				</div>
				
				<c:forEach var="m" items="${requestScope.menus }" >
				
					<div   title="<img src='${m.menuIcon }'> ${m.menuName }"  href="menu/mymenus/by.do?id=${m.id }">
					
					</div>
					
				</c:forEach>
				
			</div>
			
		</div>
		
		<!-- 底部 -->
		<div region="south" border="false"  style="height: 30px; overflow: hidden;">
		
			<div id="div_footer" >
	
				 Copyright &copy; 2014  
				
			</div>
		</div>
		<script type="text/javascript">
			
			<!--
				//获取一些基本信息
				
				var scheduleWarn=<%=request.getAttribute("scheduleWarn") %>;
				
				var msgWarnTime=<%=request.getAttribute("msgWarnTime") %>;
			 
				loginUserId='<%=(String)request.getAttribute("userId") %>';
				loginName='<%=(String)request.getAttribute("trueName") %>';
				loginDeptId='<%=(String)request.getAttribute("deptId") %>';
				loginDeptName='<%=(String)request.getAttribute("deptName") %>';
				
				
			//-->
		</script>
		<%@ include file="/WEB-INF/jsp/main/commons-menu.jsp"%>
	
	</div>
</body>
</html>