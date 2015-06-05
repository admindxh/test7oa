<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	模块：CRM-客户管理-客户信息-查询
--%>

<div id="${param.rel }_filter" style="width: 95%;margin: 0 auto;"></div>

<form  onsubmit="return datagridSearch(this,'${param.rel }_datagrid');"  queryFilterId="${param.rel }_filter" >
	
	<div style="width: 60px;margin: 0 auto;">
		<button class="btn btn-primary btn-small" type="submit">查询</button>
	</div>
</form>

<script type="text/javascript">
<!--
$(function (){
	
	$('#<%=request.getParameter("rel")%>_filter').queryFilter({
    	
    	fields:[
				{	
					field:"l.liName",
					name:"姓名",
					type:"string",
					html: '<input type="text" name="l.liName"/>'
				}, 
				{	
					field:"l.liSex",
					name:"性别",
					type:"string",
					html: '<select name="l.liSex"><option value="">全部</option><option value="男">男</option><option value="女">女</option></select>'
				},
				{	
					field:"l.liType",
					name:"类型",
					type:"string",
					html: '<select name="l.liType" selectOptions="listValue" optionsType="502"></select>'
				},
				{	
					field:"l.liSpecies",
					name:"种类",
					type:"string",
					html: '<select name="l.liSpecies" selectOptions="listValue" optionsType="501"></select>'
				},
				{	
					field:"l.liBusiness",
					name:"负责业务",
					type:"string",
					html: '<input type="text" name="l.liBusiness"/>'
				},
				{	
					field:"l.liDept",
					name:"部门",
					type:"string",
					html: '<input type="text" name="l.liDept"/>'
				},
				{	
					field:"l.liPost",
					name:"职务",
					type:"string",
					html: '<input type="text" name="l.liPost"/>'
				},
				{	
					field:"l.liAppellation",
					name:"称谓",
					type:"string",
					html: '<input type="text" name="l.liAppellation"/>'
				},
				{	
					field:"l.liTelephone",
					name:"电话",
					type:"string",
					html: '<input type="text" name="l.liTelephone"/>'
				},
				{
	    			field:"l.liEmail",
	    			name:"邮箱",
	    			type:"string",
	    			html:'<input type="text" name="l.liEmail"/>'
	    		},
				{	
					field:"l.liFax",
					name:"传真",
					type:"string",
					html: '<input type="text" name="l.liFax"/>'
				},
				{	
					field:"l.liMobilePhone",
					name:"其他联系方式",
					type:"string",
					html: '<input type="text" name="l.liMobilePhone"/>'
				},
				{	
					field:"l.liMsnQq",
					name:"MSN/QQ",
					type:"string",
					html: '<input type="text" name="l.liMsnQq"/>'
				},
				{	
					field:"l.liZipCode",
					name:"邮编",
					type:"string",
					html: '<input type="text" name="l.liZipCode"/>'
				},
				{	
					field:"l.liHomeaddress",
					name:"家庭住址",
					type:"string",
					html: '<input type="text" name="l.liHomeaddress"/>'
				},
				{	
					field:"l.liHomephone",
					name:"家庭电话",
					type:"string",
					html: '<input type="text" name="l.liHomephone"/>'
				},
				{
	    			field:"l.createMan",
	    			name:"创建人",
	    			type:"string",
	    			html:'<a href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询" targetBox="td"><input type="text" readonly="readonly" toName="user.trueName"/></a><input type="hidden" name="l.createMan" toName="user.id">'
	    			
	    		},
				{
	    			field:"l.updateMan",
	    			name:"修改人",
	    			type:"string", 
	    			html:'<a href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询" targetBox="td"><input type="text" readonly="readonly" toName="user.trueName"/></a><input type="hidden" name="l.updateMan" toName="user.id">'
	    		}
	    		
				
				
				]
	
    });
   

});
//-->
</script>
