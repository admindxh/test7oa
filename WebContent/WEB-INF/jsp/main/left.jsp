<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>

<div id="nav" class="easyui-accordion " fit="true" >
	<div title="快捷菜单" iconCls="ui-icon-computer" tools="#left_kjmenu_tools" >
		<ul class="ul-menu" style="margin-left: 2px;margin-right: 2px;margin-top: 2px">
			<c:forEach  var="m"  items="${requestScope.mymenus }" >
				 <li>
				 	<c:choose>
				 		<c:when test="${empty m.url }">
				 				<a style="color: black;">
				 		</c:when>
				 		<c:otherwise>
				 			<a  href="${m.url }" target="${m.target }" external="${m.external }" fresh="false" rel="my_${m.rel }" title="<c:out value="${m.name }"/>">
				 		</c:otherwise>
				 	</c:choose>
				 	
				 	&nbsp;&nbsp;<img src="${m.icon }" />&nbsp;&nbsp;<c:out value="${m.name }"/></a>
				 
				 </li>
			
			
			</c:forEach>
		</ul>
	</div>
	
	<%--<div title="系统菜单" iconCls="ui-icon-computer" tools="#left_menu_tools" >
		<ul id="left_menu_tree" class="ztree"></ul>
	</div>

	--%><div  title="在线人员" iconCls="ui-icon-group" tools="#left_online_tools" id="left_online_div">
		 <ul id="left_online_tree" class="ztree"></ul>
	</div>
	<div  title="全部人员" iconCls="ui-icon-group" tools="#left_allUsers_tools" id="left_allUsers_div">
		 <ul id="left_allUsers_tree" class="ztree"></ul>
	</div>
	
</div>
<div id="left_kjmenu_tools" style="display: none;">  
 	
     <a href="menu/my/load.do?rel=mykjmenu" class="ui-icon-cog"   title="快捷菜单管理"  target="dialog" width="800" height="400" style="margin-left: 5px;"></a>
	
</div>
<div id="left_menu_tools" style="display: none;">  
 	 <a href="javascript:;" class="icon-reload" title="刷新"  onclick="queryLeftMenuTree()"></a>
     <a href="javascript:;" class="icon-add"   title="合并"  onclick="zTreeExpandAllNodes('left_menu_tree',false)" style="margin-left: 5px;"></a>
	 <a href="javascript:;" class="icon-remove" title="展开" onclick="zTreeExpandAllNodes('left_menu_tree',true)" style="margin-left: 5px;" ></a>
   
</div>
<div id="left_online_tools" style="display: none;">  
 	 <a href="javascript:;" class="icon-reload" title="刷新"  onclick="queryLeftOnlineTree()"></a>
     <a href="javascript:;" class="icon-add"   title="合并"  onclick="zTreeExpandAllNodes('left_online_tree',false)" style="margin-left: 5px;"></a>
	 <a href="javascript:;" class="icon-remove" title="展开" onclick="zTreeExpandAllNodes('left_online_tree',true)" style="margin-left: 5px;" ></a>
</div>   
<div id="left_allUsers_tools" style="display: none;">  
 	 <a href="javascript:;" class="icon-reload" title="刷新"  onclick="queryLeftAllUsersTree()"></a>
     <a href="javascript:;" class="icon-add"   title="合并"  onclick="zTreeExpandAllNodes('left_allUsers_tree',false)" style="margin-left: 5px;"></a>
	 <a href="javascript:;" class="icon-remove" title="展开" onclick="zTreeExpandAllNodes('left_allUsers_tree',true)" style="margin-left: 5px;" ></a>
</div>   
