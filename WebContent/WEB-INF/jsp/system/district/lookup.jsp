<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：系统管理--地区管理--查找带回
--%>


<div style="width: 400px;margin: 0 auto;">

	<ul id="district_gllookup_tree" class="ztree"></ul>

</div>
	
<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"district/lookUp.do",
			dataType:"json",
			success:function(json){
				var setting = {
						view: {
							dblClickExpand: false,
							showIcon: false
						},
						data: {
							simpleData: {
								enable: true
							}
						},
						callback: {
							onClick: chooseDistrict
						}
				};
				var zNodes = new Array();
				$.each(json, function(i,d) {
					if(d.superId){
						zNodes.push({id : d.id,name : d.name,pId : d.superId,open:false});
					}
				
				});
				
				$.fn.zTree.init($("#district_gllookup_tree"), setting, zNodes);
				
			}
		});
	});
	function chooseDistrict(event,treeId, treeNodeJSON){
		var names = new Array();
		names.push(treeNodeJSON.name);
		dgParentNodeName(treeNodeJSON,names);
		names.reverse();
		$.bringBack({name:names.join("-")});
		
	}
	function dgParentNodeName(treeNodeJSON,names){
		var node = treeNodeJSON.getParentNode();
		if(node!=null){
			//去除中国
			if(node.id!="402881ea3f5b1d14013f5b1fdc080006"){
				names.push(node.name);
			}
			dgParentNodeName(node,names);
		}
	}
	
//-->
</script>
							
						
					
		

	

