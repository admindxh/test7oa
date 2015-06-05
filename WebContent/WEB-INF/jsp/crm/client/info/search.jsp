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
					field:"clNo",
					name:"客户编号",
					type:"string",
					html: '<input type="text" name="clNo"/>'
				}, 
				{	
					field:"clName",
					name:"客户名称",
					type:"string",
					html: '<input type="text" name="clName"/>'
				},
				{	
					field:"clCategory",
					name:"客户类别",
					type:"string",
					html: '<select name="clCategory" selectOptions="listValue" optionsType="408"></select>'
				},
				{	
					field:"clType",
					name:"客户类型",
					type:"string",
					html: '<select name="clType" selectOptions="listValue" optionsType="401"></select>'
				},
				{	
					field:"clGrade",
					name:"客户等级",
					type:"string",
					html: '<select name="clGrade" selectOptions="listValue" optionsType="403"></select>'
				},
				{	
					field:"clStatus",
					name:"客户状态",
					type:"string",
					html: '<select name="clStatus" selectOptions="listValue" optionsType="404"></select>'
				},
				{	
					field:"clStaje",
					name:"客户阶段",
					type:"string",
					html: '<select name="clStaje" selectOptions="listValue" optionsType="405"></select>'
				},
				{	
					field:"clRelation",
					name:"客户关系",
					type:"string",
					html: '<select name="clRelation" selectOptions="listValue" optionsType="406"></select>'
				},
				{	
					field:"clSource",
					name:"客户来源",
					type:"string",
					html: '<select name="clSource" selectOptions="listValue" optionsType="402"></select>'
				},
				{
	    			field:"clStaff",
	    			name:"负责人",
	    			type:"string",
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" targetBox="td" ><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="clStaff" 	toName="user.id"></a>'
	    		},
				{	
					field:"clIndustry",
					name:"所属行业",
					type:"string",
					html: '<select name="clIndustry" selectOptions="listValue" optionsType="407"></select>'
				},
				{	
					field:"clDescribe",
					name:"行业描述",
					type:"string",
					html: '<input type="text" name="clDescribe"/>'
				},
				{	
					field:"clAddress",
					name:"公司地址",
					type:"string",
					html: '<input type="text" name="clAddress"/>'
				},
				{	
					field:"clReceipt",
					name:"收货地址",
					type:"string",
					html: '<input type="text" name="clReceipt"/>'
				},
				{	
					field:"clEmail",
					name:"邮箱",
					type:"string",
					html: '<input type="text" name="clEmail"/>'
				},
				{	
					field:"clPhoneNumeber",
					name:"手机号",
					type:"string",
					html: '<input type="text" name="clPhoneNumeber"/>'
				},
				{	
					field:"clPhone",
					name:"电话",
					type:"string",
					html: '<input type="text" name="clPhone"/>'
				},
				{	
					field:"clFax",
					name:"传真",
					type:"string",
					html: '<input type="text" name="clFax"/>'
				},
				{	
					field:"clWebsite",
					name:"公司网址",
					type:"string",
					html: '<input type="text" name="clWebsite"/>'
				},
	    		{	
					field:"clZip",
					name:"邮编",
					type:"string",
					html: '<input type="text" name="clZip"/>'
				},
				{	
					field:"clQq",
					name:"QQ/MSN",
					type:"string",
					html: '<input type="text" name="clQq"/>'
				},
				{	
					field:"clWangwang",
					name:"旺旺",
					type:"string",
					html: '<input type="text" name="clWangwang"/>'
				},
				{	
	    			field:"clBank",
	    			name:"开户银行",
	    			type:"string",
	    			html: '<input type="text" name="clBank"/>'
	    		},
	    		{	
					field:"clPeople",
					name:"开户人",
					type:"string",
					html: '<input type="text" name="clPeople"/>'
				},
				{	
					field:"clNumber",
					name:"银行账号",
					type:"string",
					html: '<input type="text" name="clNumber"/>'
				},
				{
	    			field:"clCreate",
	    			name:"创建人",
	    			type:"string",
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" targetBox="td" ><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="clCreate" 	toName="user.id"></a>'
	    		},
				{
	    			field:"clUptade",
	    			name:"修改人",
	    			type:"string", 
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" targetBox="td"><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="clUptade" 	toName="user.id"></a>'
	    		}
	    		
				
				
				]
	
    });
   

});
//-->
</script>
    	     




