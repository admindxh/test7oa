<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	session="false"%>
<%@include file="/WEB-INF/jsp/commons/jstl_message_tld.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title><fmt:message key="app.name" bundle="${commonBundle}"/></title>
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<meta http-equiv=content-type content="text/html; charset=utf-8">
	<style type=text/css>
		td{
			font-size: 12px;
			font-family: tahoma,sans-serif
		}
	</style>
</head>
<body style="font-size: 12px;font-family: tahoma,sans-serif;background-color: #cccccc">
	<table height="95%" cellspacing=0 cellpadding=0 width="100%" align=center border=0>
		<tbody>
			<tr valign=center align=middle>
				<td>
					<table cellspacing=0 cellpadding=0 width=468 bgcolor=#ffffff
						border=0>
						<tbody>
							<tr>
								<td width=20 background="resource/images/error/rbox_1.gif" height=20></td>
								<td width=108 background="resource/images/error/rbox_2.gif" height=20></td>
								<td width=56><img height=20 src="resource/images/error/rbox_ring.gif" width=56></td>
								<td width=100 background="resource/images/error/rbox_2.gif"></td>
								<td width=56><img height=20 src="resource/images/error/rbox_ring.gif" width=56></td>
								<td width=108 background="resource/images/error/rbox_2.gif"></td>
								<td width=20 background="resource/images/error/rbox_3.gif" height=20></td>
							</tr>
							<tr>
								<td align=left background="resource/images/error/rbox_4.gif" rowspan=2></td>
								<td align=middle bgcolor=#eeeeee colspan=5 height=50>
									<p>
										<strong>抱歉，您要下载的文件--<span style="color: red;">${name }</span>,不存在！&#8230;&#8230;<br><br></strong>
									</p>
								</td>
								<td align=left background="resource/images/error/rbox_6.gif" rowspan=2></td>
							</tr>
							<tr>
								<td align=left colspan=5 height=80>
									<p align=center>
										<br>
									<p id=lid2>原因：</p>
									<ul>
										<li id=list1>访问错误。<br>
										<li id=list2>文件已被删除或已被移走。
										<li id=list3>单击<a href="javascript:;" onclick='window.opener=null;window.close()'>关闭</a>当前页面按钮。</li>
									</ul>
									<div align=right>
										<br/><fmt:message key="app.name" bundle="${commonBundle}"/>
									</div>
								</td>
							</tr>
							<tr>
								<td align=left background="resource/images/error/rbox_7.gif" height=20></td>
								<td align=left background="resource/images/error/rbox_8.gif" colspan=5 height=20></td>
								<td align=left background="resource/images/error/rbox_9.gif" height=20></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
