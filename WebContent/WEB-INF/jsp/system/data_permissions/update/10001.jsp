<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：系统管理--数据权限 -- 修改
--%>

<%@ include file="/WEB-INF/jsp/system/data_permissions/update.jsp" %>
			
<script type="text/javascript">
<!--
$(function (){

    $('#<%=request.getParameter("rel")%>_filter').queryFilter({
    	data:$('#<%=request.getParameter("rel")%>_data').html(),
    	fields:[
    		{
    			field:"userId",
    			name:"发布人",
    			type:"userId",
    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" targetBox="td"><input type="text"  readonly="readonly"  toName="user.trueName" /></a><input type="hidden"  name="userId"  toName="user.id"  /><a href="javascript:;" lookupSetValue="user">当前用户</a>'
    		},
    		{
    			field:"deptId",
    			name:"所属部门",
    			type:"deptId",
    			html:'<a href="dept/lookUpPage.do?type=1"  lookupGroup="role" title="角色查询" targetBox="td"><input type="text"  readonly="readonly"  toName="role.roleName" /></a><input type="hidden"  name="deptId"  toName="role.id"  /><a href="javascript:;" lookupSetValue="dept">当前部门</a>'
    		},
    		{
    			field:"{loginUserId}",
    			name:"{当前用户}",
    			type:"userId",
    			html:'<a href="user/lookUpPage.do?type=2"  lookupGroup="user" title="用户查询" targetBox="td"><input type="text"  readonly="readonly"  toName="user.trueName" /></a><input type="hidden"  name="{loginUserId}"  toName="user.id"  />'
    		},
    		{
    			field:"{loginDeptId}",
    			name:"{当前部门}",
    			type:"deptId",
    			html:'<a href="dept/lookUpPage.do?type=2"  lookupGroup="dept" title="部门查询" targetBox="td"><input type="text"  readonly="readonly"  toName="dept.deptName" /></a><input type="hidden"  name="{loginDeptId}"  toName="dept.id"  />'
    		},
    		{
    			field:"{loginRoleId}",
    			name:"{当前角色}",
    			type:"roleId",
    			html:'<a href="role/lookUpPage.do?type=2"  lookupGroup="role" title="角色查询" targetBox="td"><input type="text"  readonly="readonly"  toName="role.roleName" /></a><input type="hidden"  name="{loginRoleId}"  toName="role.id"  />'
    		}
    	
    	]
    	
    });
   

});

//-->
</script>
						
					