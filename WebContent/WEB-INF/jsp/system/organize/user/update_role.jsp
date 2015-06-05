<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--组织机构--用户管理--角色修改
--%>
<div style="width: 450px;margin: 10px auto;" >
	  	
	  <form method="post" action="user/updateRole.do" beforeCallback="ber_updateUserRole" warn="确定修改用户角色吗?"  onsubmit="return validateSubmitForm(this)">
	  		<shiro:hasPermission name="user:updateRole">
	  		<div style="margin:1px;padding:5px;border:1px solid #ddd;">

		      	 <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="zTreeCheckAllNodes('user_role_tree',true)">全选</a>
		      	 <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'" onclick="zTreeCheckAllNodes('user_role_tree',false)">全部取消</a>
	     
	  		</div>
	  		</shiro:hasPermission>
			<ul id="user_role_tree" class="ztree"></ul>
		
			<shiro:hasPermission name="user:updateRole">
				
				<button type="submit" class="btn btn-primary" style="margin: 20px;">保存</button>
				<!-- 角色id -->
				<input type="hidden" name="userId" value="${param.id }"  />
				
				<!-- 需要添加的角色关联 -->
				<input type="hidden" name="addRoleIds"  />
				<!-- 需要删除的角色关联 -->
				<input type="hidden" name="delRoleIds"  />
				
				<input type="hidden" name="currentCallback" value="close" />
						
			</shiro:hasPermission>
					
				
			
	</form>
</div>


<script type="text/javascript">
<!--
	$(function(){
		
		$.ajax({
			url:'user/updateRoleQuery.do?id=<%= request.getParameter("id") %>',
			dataType:"json",
			success:function(json){
				var isUpdate=$("button[type='submit']",$("#user_role_tree").closest("form")).length;
				if(isUpdate==0){
					var setting = {
							
						view: {
							dblClickExpand: false,
							showIcon: false
						},
						data: {
							simpleData: {
								enable: true
							},
							key: {
								title: "title"
							}
						}
					};
				}else{
					var setting = {
							check: {
								enable: true
							},
							view: {
								dblClickExpand: false,
								showIcon: false
							},
							data: {
								simpleData: {
									enable: true
								},
								key: {
									title: "title"
								}
							}
					};
				}
				
				var zNodes = new Array();
				
				if(isUpdate==0){
					$.each(json.roles, function(i, r) {
						
						if($.inArray(r.id,json.hasRoles)!=-1){
							zNodes.push({id : r.id,name : r.roleName,title:r.roleDesc});
						}
					});
				}else{
					$.each(json.roles, function(i, r) {
						
						if($.inArray(r.id,json.hasRoles)==-1){
							zNodes.push({id : r.id,name : r.roleName,title:r.roleDesc});
						}else{
							
							zNodes.push({id : r.id,name : r.roleName,title:r.roleDesc,checked:true});
							
						}
					});
				}
				
				$.fn.zTree.init($("#user_role_tree"), setting, zNodes);
				
			}
		});
		

	});
	function ber_updateUserRole($form){
		
		var treeObj = $.fn.zTree.getZTreeObj("user_role_tree");
		//获取所有改变的节点
		var nodes = treeObj.getChangeCheckedNodes();
		if(nodes.length==0){
			Msg.topCenter({
				title:"提示",
				msg:"您没有进行任何修改，无需保存"
			});
			return false;
		}
		var addRoleIds = new Array();
		var delRoleIds = new Array();
		for ( var i = 0,k=nodes.length; i <k ; i++) {
			var node=nodes[i];
			
			//checkedOld:节点初始化时的勾选状态  true 表示节点初始化时 输入框被勾选  false 表示节点初始化时 输入框未勾选
			//为节点自定义isMenu属性，true:菜单,false:操作
			if(node.checkedOld){
				//原先被勾选，现在取消勾选
				//删除角色关联
				delRoleIds.push(node.id);
				
			}else{
				//原先未勾选，现在勾选了
				addRoleIds.push(node.id);
				
			}
		}
		$form.find("input[name='addRoleIds']").val(addRoleIds.join(","));
		$form.find("input[name='delRoleIds']").val(delRoleIds.join(","));
		return true;
	}
	//-->
</script>	
	