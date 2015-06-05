<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：模块：人事管理-考勤管理-休息日设置列表
--%>

<div  class="easyui-tabs"  fit="true" border="false" >
	<div   title="休息日设置"  	style="padding:2px; " >
			<%@ include file="/WEB-INF/jsp/personnel/attendance/closeTime_view.jsp" %>
	</div>

	<div  title="休息日管理"  href="attendance/manageCloseTime/load.do?rel=${param.rel }_gl"	style="padding:2px; " >
	
	</div>

</div>