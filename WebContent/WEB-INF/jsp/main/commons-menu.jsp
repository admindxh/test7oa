<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 首页工具 按钮 --%>
<div id="tab-mainPage_tools">
  <a href="javascript:void(0)" class="icon-mini-refresh" title="初始化顺序" onclick="resetMainPortalState()"></a>
  
</div>


<%-- 中间 标签菜单 --%>
<div id="maintabs_menu" class="easyui-menu" style="width:150px;display: none">
	<div id="maintabs-update" iconCls="icon-reload">刷新</div>
	<div class="menu-sep"></div>
	<div id="maintabs-close" iconCls="icon-cancel">关闭</div>
	<div id="maintabs-closeall" >关闭全部</div>
	<div id="maintabs-closeother">关闭其他</div>
	<div class="menu-sep"></div>
	<div id="maintabs-closeright">关闭右侧</div>
	<div id="maintabs-closeleft">关闭左侧</div>

</div>

<%-- 底部弹出框 任务栏 --%>
<div id="taskbar" style="left:0px; display:none;">
	<div class="taskbarContent">
		<ul></ul>
	</div>
	<div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
	<div class="taskbarRight" style="display:none;">taskbarRight</div>
</div>

<%-- 底部弹出框 任务栏菜单 --%>
<div id="taskbar_menu" class="easyui-menu" style="width:150px;display: none">
	<div id="taskbar_menu-update" iconCls="icon-reload">刷新</div>
	<div class="menu-sep"></div>
	<div id="taskbar_menu-close" iconCls="icon-cancel">关闭</div>
	<div id="taskbar_menu-closeall">关闭全部</div>
	<div id="taskbar_menu-closeother">关闭其他</div>
	<div class="menu-sep"></div>
	<div id="taskbar_menu-closeright">关闭右侧</div>
	<div id="taskbar_menu-closeleft">关闭左侧</div>

</div>



