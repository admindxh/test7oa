<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：公司文档类型管理 查找带回
--%>


<div style="width: 400px;margin: 10px auto;" >	
	 <ul id="wd_type_lookup_tree" class="ztree"></ul>

</div>
	

<script type="text/javascript">
<!--
	$(function(){
		var type='<%= request.getParameter("type") %>';
		var url;
		if(type==2){
			url="companyFiles/type/allTypes.do";
		}else{
			url="companyFiles/type/myTypes.do";
		}
		$.ajax({
			url:url,
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
				
				$.each(json, function(i, m) {
					
					zNodes.push({id : m.id,name : m.name,
						pId : m.superId,open:false,click:"$.bringBack({id:'"+m.id+"',name:'"+m.name+"'})",iconSkin:"folder"});
					
				});
				
				$.fn.zTree.init($("#wd_type_lookup_tree"), setting, zNodes);
		
			}
		});
	});
//-->
</script>
