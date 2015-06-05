<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构--角色管理--权限修改
--%>

<div style="width: 550px;margin: 10px auto;" >
	  	
  	<form  action="role/power/update.do" beforeCallback="ber_updateRolePower" warn="确定修改角色权限吗？" method="post"   onsubmit="return validateSubmitForm(this)">
  		<div style="margin:1px;padding:5px;border:1px solid #ddd;">
 			<a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'"  onclick="zTreeExpandAllNodes('role_power_tree',false)">合并</a>
	       	 <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="zTreeExpandAllNodes('role_power_tree',true)">展开</a>
	      	
     		<shiro:hasPermission name="role:updatePower">
				 <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="zTreeCheckAllNodes('role_power_tree',true)">全选</a>
	      	 	 <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'" onclick="zTreeCheckAllNodes('role_power_tree',false)">全部取消</a>
	      	 
				<button type="submit" class="btn btn-primary btn-small" style="margin-left: 20px;">保存</button>
					 
				<!-- 角色id -->
				<input type="hidden" name="roleId" value="${param.id }"  />
				
				<!-- 需要添加的菜单关联 -->
				<input type="hidden" name="addMenuIds"  />
				<!-- 需要删除的菜单关联 -->
				<input type="hidden" name="delMenuIds"  />
				<!-- 需要添加的操作关联 -->
				<input type="hidden" name="addActionIds"  />
				<!-- 需要删除的操作关联 -->
				<input type="hidden" name="delActionIds"  />
				
				<input type="hidden" name="currentCallback" value="close" />
					
			</shiro:hasPermission>
  		</div>
	  	
		<ul id="role_power_tree" class="ztree"></ul>
	
		
				
	</form>
</div>
<script type="text/javascript">
<!--
	$(function(){
		$.ajax({
			url:"role/power/updatePage/query.do?id=<%=(String)request.getParameter("id")%>",
			cache: false,
			dataType:"json",
			success:function(json){
				var $rolePowerTree=$("#role_power_tree");
				var isChange=true;
				if($(("button[type='submit']"),$rolePowerTree.closest("form")).length==0){
					isChange=false;
				}
				if(isChange){
					var setting = {
							check: {
								enable: true
							},
							view: {
								dblClickExpand: false,
								showIcon: true
							},
							data: {
								simpleData: {
									enable: true
								}
							}
					};
				}else{
					var setting = {
							view: {
								dblClickExpand: false,
								showIcon: true
							},
							data: {
								simpleData: {
									enable: true
								}
							}
					};
				}
				var zNodes = new Array();
				
				$.each(json.menus, function(i, m) {
					if(m.id!="0"){
						if($.inArray(m.id,json.hasMenus)==-1){
							zNodes.push({id : m.id,name : m.menuName,pId : m.menuSuperId,open:false,isMenu:true,icon:m.menuIcon});
						}else{
							zNodes.push({id : m.id,name : m.menuName,pId : m.menuSuperId,open:false,isMenu:true,checked:true,icon:m.menuIcon});
						}
					}
				});
				
				$.each(json.actions, function(i, a) {
					if($.inArray(a.id,json.hasActions)==-1){
						if(isChange){
							zNodes.push({id : a.id,name : a.actionName,pId : a.menuId,isMenu:false,icon:"resource/images/menu/3.png"});
						}
					}else{
						zNodes.push({id : a.id,name : a.actionName,pId : a.menuId,isMenu:false,checked:true,icon:"resource/images/menu/3.png"});
					}
					
				});
				
				$.fn.zTree.init($rolePowerTree, setting, zNodes);
			}
		});
	});
function ber_updateRolePower($form){

	var treeObj = $.fn.zTree.getZTreeObj("role_power_tree");
	//获取所有改变的节点
	var nodes = treeObj.getChangeCheckedNodes();
	if(nodes.length==0){
		Msg.topCenter({
			title:"提示",
			msg:"您没有进行任何修改，无需保存"
		});
		return false;
	}
	var addMenuIds = new Array();
	var delMenuIds = new Array();
	var addActionIds = new Array();
	var delActionIds = new Array();
	for ( var i = 0,k=nodes.length; i <k ; i++) {
		var node=nodes[i];
		//checkedOld:节点初始化时的勾选状态  true 表示节点初始化时 输入框被勾选  false 表示节点初始化时 输入框未勾选
		//为节点自定义isMenu属性，true:菜单,false:操作
		if(node.checkedOld){
			//原先被勾选，现在取消勾选
			if(node.isMenu){
				//删除关联菜单
				delMenuIds.push(node.id);
			}else{
				//删除关联操作
				delActionIds.push(node.id);
			}
		}else{
			//原先未勾选，现在勾选了
			if(node.isMenu){
				//添加关联菜单
				addMenuIds.push(node.id);
			}else{
				//添加关联操作
				addActionIds.push(node.id);
			}
		}
	}
	
	$form.find("input[name='addMenuIds']").val(addMenuIds.join(","));
	$form.find("input[name='delMenuIds']").val(delMenuIds.join(","));
	$form.find("input[name='addActionIds']").val(addActionIds.join(","));
	$form.find("input[name='delActionIds']").val(delActionIds.join(","));
	return true;
}

//-->
</script>

	

