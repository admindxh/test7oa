/**
 * easyui zTree 
 */
//zTree 节点点击事件   打开标签，或者弹出窗口
//event 对象标准的 js event 对象
//treeId(String) 对应 zTree 的 treeId 
//treeNodeJSON   被点击的节点 JSON 数据对象
function zTreeClick(event,treeId, treeNodeJSON){
	//点击打开
	if(treeNodeJSON.clickOpen){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNodeJSON);
	}
	if(!treeNodeJSON.href)return ;
	
	if(treeNodeJSON.dwzTarget=="navTab"){
		
		var title = treeNodeJSON.title||treeNodeJSON.name;
		var tabid = treeNodeJSON.rel || "_blank";
		var fresh = eval(treeNodeJSON.fresh || "true");
		var external = eval(treeNodeJSON.external || "false");
		
		MUI.openCenterTab(title,treeNodeJSON.href,tabid,external,false);
		
		return false;
		
	}
	else if(treeNodeJSON.dwzTarget=="ajax"){
		$('#'+treeNodeJSON.rel).panel('refresh',treeNodeJSON.href);
		return false;
	}else if(treeNodeJSON.dwzTarget=="dialog"){
		
		var title = treeNodeJSON.title||treeNodeJSON.name;
		var id = treeNodeJSON.rel || "_newdialog";
		
		MUI.openDialog(title, treeNodeJSON.href, id, treeNodeJSON.dialogParam);
		return false;
	}
	else{
		alert("需要拓展打开方式");
		event.preventDefault();
		return false;
	}
	
}
/**
 * 刷新表格数据
 * @param {} event
 * @param {} treeId
 * @param {} treeNodeJSON
 */
function refreshDatagrid(event,treeId, treeNodeJSON){
	//点击打开
	if(treeNodeJSON.clickOpen){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNodeJSON);
	}
	if(!treeNodeJSON.datagrid)return ;
	var $dg=$("#"+treeNodeJSON.datagrid);
	var $form=$dg.closest(".datagrid").find($dg.attr("toolbar")+">form");
	var o = {};
	$.each($form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	var queryParams = $dg.datagrid('options').queryParams;//获取原查询条件
	$.extend(queryParams,o,treeNodeJSON.param);//将现在的查询条件合并到原条件中
	$dg.datagrid('load', queryParams);
	if(treeNodeJSON.updateTitle){//修改表格title
		$dg.datagrid('getPanel').panel("setTitle",treeNodeJSON.updateTitle);
	}
}

//展开,合并,全部节点
function zTreeExpandAllNodes(id,expand){
	$.fn.zTree.getZTreeObj(id).expandAll(expand);
}
//全选，全部取消按钮
function zTreeCheckAllNodes(id,expand) {
	$.fn.zTree.getZTreeObj(id).checkAllNodes(expand)
}




