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
					field:"o.clNo",
					name:"客户编号",
					type:"string",
					html: '<input type="text" name="o.clNo"/>'
				}, 
				{	
					field:"o.clName",
					name:"客户名称",
					type:"string",
					html: '<input type="text" name="o.clName"/>'
				},
				{	
					field:"o.clCategory",
					name:"客户类别",
					type:"string",
					html: '<select name="o.clCategory" selectOptions="listValue" optionsType="408"></select>'
				},
				{	
					field:"o.clType",
					name:"客户类型",
					type:"string",
					html: '<select name="o.clType" selectOptions="listValue" optionsType="401"></select>'
				},
				{	
					field:"o.clGrade",
					name:"客户等级",
					type:"string",
					html: '<select name="o.clGrade" selectOptions="listValue" optionsType="403"></select>'
				},
				{	
					field:"o.clStatus",
					name:"客户状态",
					type:"string",
					html: '<select name="o.clStatus" selectOptions="listValue" optionsType="404"></select>'
				},
				{	
					field:"o.clStaje",
					name:"客户阶段",
					type:"string",
					html: '<select name="o.clStaje" selectOptions="listValue" optionsType="405"></select>'
				},
				{	
					field:"o.clRelation",
					name:"客户关系",
					type:"string",
					html: '<select name="o.clRelation" selectOptions="listValue" optionsType="406"></select>'
				},
				{	
					field:"o.clSource",
					name:"客户来源",
					type:"string",
					html: '<select name="o.clSource" selectOptions="listValue" optionsType="402"></select>'
				},
				{
	    			field:"o.clStaff",
	    			name:"负责人",
	    			type:"string",
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" ><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="o.clStaff" 	toName="user.id"></a>'
	    		},
				{	
					field:"o.clIndustry",
					name:"所属行业",
					type:"string",
					html: '<select name="o.clIndustry" selectOptions="listValue" optionsType="407"></select>'
				},
				{	
					field:"o.clDescribe",
					name:"行业描述",
					type:"string",
					html: '<input type="text" name="o.clDescribe"/>'
				},
				{	
					field:"o.clAddress",
					name:"公司地址",
					type:"string",
					html: '<input type="text" name="o.clAddress"/>'
				},
				{	
					field:"o.clReceipt",
					name:"收货地址",
					type:"string",
					html: '<input type="text" name="o.clReceipt"/>'
				},
				{	
					field:"o.clEmail",
					name:"邮箱",
					type:"string",
					html: '<input type="text" name="o.clEmail"/>'
				},
				{	
					field:"o.clPhoneNumeber",
					name:"手机号",
					type:"string",
					html: '<input type="text" name="o.clPhoneNumeber"/>'
				},
				{	
					field:"o.clPhone",
					name:"电话",
					type:"string",
					html: '<input type="text" name="o.clPhone"/>'
				},
				{	
					field:"o.clFax",
					name:"传真",
					type:"string",
					html: '<input type="text" name="o.clFax"/>'
				},
				{	
					field:"o.clWebsite",
					name:"公司网址",
					type:"string",
					html: '<input type="text" name="o.clWebsite"/>'
				},
	    		{	
					field:"o.clZip",
					name:"邮编",
					type:"string",
					html: '<input type="text" name="o.clZip"/>'
				},
				{	
					field:"o.clQq",
					name:"QQ/MSN",
					type:"string",
					html: '<input type="text" name="o.clQq"/>'
				},
				{	
					field:"o.clWangwang",
					name:"旺旺",
					type:"string",
					html: '<input type="text" name="o.clWangwang"/>'
				},
				{	
	    			field:"o.clBank",
	    			name:"开户银行",
	    			type:"string",
	    			html: '<input type="text" name="o.clBank"/>'
	    		},
	    		{	
					field:"o.clPeople",
					name:"开户人",
					type:"string",
					html: '<input type="text" name="o.clPeople"/>'
				},
				{	
					field:"o.clNumber",
					name:"银行账号",
					type:"string",
					html: '<input type="text" name="o.clNumber"/>'
				},
				{
	    			field:"o.clCreate",
	    			name:"创建人",
	    			type:"string",
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" ><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="o.clCreate" 	toName="user.id"></a>'
	    		},
				{
	    			field:"clUptade",
	    			name:"修改人",
	    			type:"string", 
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" ><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="o.clUptade" 	toName="user.id"></a>'
	    		}
	    		
				
				
				]
	
    });
   

});
//-->
</script>
    	     




