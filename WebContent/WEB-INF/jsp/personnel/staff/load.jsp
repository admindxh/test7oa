<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理--人员管理
--%>
<%--<div class="pageContent" style="padding: 1px">--%>
<%--		<div class="tabs">--%>
<%--		<div class="tabsHeader">--%>
<%--			<div class="tabsHeaderContent">--%>
<%--				<ul><my:validationAuthority url="staff/work.do">--%>
<%--					<li><a href="staff/work.do?rel=${param.rel }" target="ajax" rel="${param.rel }_work"><span>在职员工</span></a></li>--%>
<%--					</my:validationAuthority>--%>
<%--					<my:validationAuthority url="staff/res.do">--%>
<%--					<li><a href="staff/res.do?rel=${param.rel }" target="ajax" rel="${param.rel }_res"><span>离职员工</span></a></li>--%>
<%--					</my:validationAuthority>--%>
<%--					<my:validationAuthority url="staff/ret.do">--%>
<%--					<li><a href="staff/ret.do?rel=${param.rel }" target="ajax" rel="${param.rel }_ret"><span>退休员工</span></a></li>--%>
<%--					</my:validationAuthority>--%>
<%--				</ul>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		--%>
<%--			--%>
<%--		<div class="tabsContent">--%>
<%--			<my:validationAuthority url="staff/work.do">--%>
<%--			<div id="${param.rel }_work" class="unitBox">--%>
<%--				--%>
<%--				<%@ include file="/WEB-INF/jsp/personnel/staff/work.jsp" %>--%>
<%--			--%>
<%--			</div>--%>
<%--			</my:validationAuthority>--%>
<%--			<my:validationAuthority url="staff/res.do">--%>
<%--			<div id="${param.rel }_res" class="unitBox">--%>
<%--				--%>
<%--				<%@ include file="/WEB-INF/jsp/personnel/staff/res.jsp" %>--%>
<%--			--%>
<%--			</div>--%>
<%--			</my:validationAuthority>--%>
<%--			<my:validationAuthority url="staff/ret.do">--%>
<%--			<div id="${param.rel }_ret" class="unitBox">--%>
<%--				--%>
<%--				<%@ include file="/WEB-INF/jsp/personnel/staff/ret.jsp" %>--%>
<%--			--%>
<%--			</div>--%>
<%--			</my:validationAuthority>--%>
<%--		</div>--%>
<%--		--%>
<%--		<div class="tabsFooter">--%>
<%--			<div class="tabsFooterContent"></div>--%>
<%--		</div>--%>
<%--		--%>
<%--	</div>--%>
<%--	--%>
<%--</div>--%>


<div  class="easyui-tabs"  fit="true" border="false" >
	<div class="easyui-tab"  title="在职员工" 	style="padding:2px; " >
		<%@ include file="/WEB-INF/jsp/personnel/staff/work.jsp" %>
	</div>

	<div class="easyui-tab"  title="离职员工"  href="staff/res.do?rel=${param.rel }_lizhi"	style="padding:2px; " >
	
	</div>
	<div class="easyui-tab"  title="退休员工"  href="staff/ret.do?rel=${param.rel }_tuixiu"	style="padding:2px; " >
	
	</div>

</div>

