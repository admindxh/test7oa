<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公司文档类型管理 查找带回
--%>


<div style="width: 400px;margin: 10px auto;" >	
	 <ul id="personalFiles_super_lookup_tree" class="ztree"></ul>

</div>
	

<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"personalFiles/tree/allNode.do",
			cache: false,
			dataType:"json",
			success:function(json){
				var setting = {
						data: {
							simpleData: {
								enable: true
							}
						}
				};
				var zNodes = new Array();
				zNodes.push({id : 0,name : "个人文档",open:true,click:"$.bringBack({id:'0',name:'个人文档'})",iconSkin:"folder"});
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.name,
						pId : d.pId,open:false,click:"$.bringBack({id:'"+d.id+"',name:'"+d.name+"'})",iconSkin:"folder"});
					
				});
				
				$.fn.zTree.init($("#personalFiles_super_lookup_tree"), setting, zNodes);
		
			}
		});
		
	});
//-->
</script>
