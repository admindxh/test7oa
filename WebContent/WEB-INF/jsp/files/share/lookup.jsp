<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公司文档类型管理 查找带回
--%>


<div style="width: 400px;margin: 10px auto;" >	
	 <ul id="shareFiles_super_lookup_tree" class="ztree"></ul>

</div>
	

<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"shareFiles/tree/allNode.do",
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
				$.each(json, function(i, d) {
					
					zNodes.push({id : d.id,name : d.name,
						pId : d.pId,open:false,click:"$.bringBack({id:'"+d.id+"',name:'"+d.name+"'})",iconSkin:"folder"});
					
				});
				
				$.fn.zTree.init($("#shareFiles_super_lookup_tree"), setting, zNodes);
		
			}
		});
		
	});
//-->
</script>
