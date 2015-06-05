<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	中间区域
--%>
<%--
	菜单
	<ul class="ul-menu">
	  <li><a href="javascript:alert('xxx');"></a></li>
	  <li><a href="javascript:;">菜单二</a></li>		
	  <li>
	  		<a href="javascript:;">二级菜单</a>
	  		<ul class="ul-submenu">
	  			<li >
	  				<a href="javascript:;">子菜单</a>
	  			</li>
	  			<li >
	  				<a href="javascript:;">三级菜单</a> 
	  				<ul class="ul-submenu">
	  					<li ><a>xx</a></li>
	  				</ul>
	  			</li>
	  		</ul>
	  </li>
	  
</ul>
 --%>
<div  class="easyui-layout" fit="true" border="false">
		<!-- 左侧-->
		<div region="west" split="true"  title="${requestScope.name }" style="width:220px; padding: 1px; " >
			
				<ul class="ul-menu">
					 ${requestScope.menus }
				</ul>

		</div>
		<!-- 中间-->
		<div  region="center" style="overflow: hidden;border-top: none" >
		
			<div  class="maintabs"  fit="true" border="false" >
				
				
			</div>
		</div>

</div>

