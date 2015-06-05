
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：系统管理--菜单管理 -- 菜单查找带回
--%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="58" style="padding-left: 20px;">
		
		<ul id="menu_gllookup_tree" class="ztree"></ul>
	
	</div>
	
</div>
<script type="text/javascript">
<!--
	$(function(){
		var type='<%=request.getParameter("type")%>';
		var url="menu/query.do";
		if(2==type){
			url="menu/queryMyAll.do";
		}
		$.ajax({
			url:url,
			cache: false,
			dataType:"json",
			success:function(json){
				var setting = {
						view: {
							dblClickExpand: false
						},
						data: {
							simpleData: {
								enable: true
							}
						}
				};
				var zNodes = new Array();
				$.each(json, function(i, m) {
					
					zNodes.push({id : m.id,name : m.menuName,pId : m.menuSuperId,open:m.menuOpen,icon:m.menuIcon,click:"$.bringBack({id:'"+m.id+"',name:'"+m.menuName+"'})"});
				
				});

				$.fn.zTree.init($("#menu_gllookup_tree"), setting, zNodes);
			}
		});
	});
	
//-->
</script>
							
						
					
		

	

