<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	模块：人事管理--人员管理-人员查询
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
					field:"psName",
					name:"姓名",
					type:"string",
					html: '<input type="text" name="psName"/>'
				}, 
				{	
					field:"psSex",
					name:"性别",
					type:"string",
					html: '<input type="radio" name="psSex" value="男" />男<input type="radio" name="psSex" value="女" />女'
				},
				{	
					field:"psName",
					name:"年龄",
					type:"string",
					html: '<input type="text" name="psAge"/>'
				},
				{	
					field:"psPresentAddress",
					name:"现居住地",
					type:"string",
					html: '<input type="text" name="psPresentAddress"/>'
				},
				{	
					field:"psHomePhone",
					name:"住宅电话",
					type:"string",
					html: '<input type="text" name="psHomePhone"/>'
				},
				{	
					field:"psPhone",
					name:"手机号码",
					type:"string",
					html: '<input type="text" name="psPhone"/>'
				},
				{	
					field:"psOther",
					name:"其他联系",
					type:"string",
					html: '<input type="text" name="psOther"/>'
				},
				{	
					field:"psAccountAddress",
					name:"户口所在地",
					type:"string",
					html: '<input type="text" name="psAccountAddress"/>'
				},
				{	
					field:"psEthnic",
					name:"民族",
					type:"string",
					html: '<input type="text" name="psEthnic"/>'
				},
				{	
					field:"psCard",
					name:"身份证号",
					type:"string",
					html: '<input type="text" name="psEthnic"/>'
				},
				{	
					field:"psHomeTown",
					name:"籍贯",
					type:"string",
					html: '<input type="text" name="psHomeTown"/>'
				},
				{
	    			field:"psUserId",
	    			name:"用户账号",
	    			type:"string",
	    			html:'<a href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询" ><input type="text"  readonly="readonly"   toName="user.trueName" /><input type="hidden" name="psUserId" 	toName="user.id"></a>'
	    		},
	    		{	
					field:"psStaffId",
					name:"员工编号",
					type:"string",
					html: '<input type="text" name="psStaffId"/>'
				},
				{	
					field:"psWage",
					name:"工资卡号",
					type:"string",
					html: '<input type="text" name="psWage"/>'
				},
				{	
					field:"psSocial",
					name:"社保号",
					type:"string",
					html: '<input type="text" name="psSocial"/>'
				},
				{	
	    			field:"psEdu",
	    			name:"最高学历",
	    			type:"string",
	    			html: '<select name="psEdu" selectOptions="listValue" optionsType="13"></select>'
	    		},
	    		{	
					field:"psSpecialty",
					name:"所学专业",
					type:"string",
					html: '<input type="text" name="psSpecialty"/>'
				},
				{	
					field:"psSchool",
					name:"毕业院校",
					type:"string",
					html: '<input type="text" name="psSchool"/>'
				},
				{	
	    			field:"psType",
	    			name:"员工类型",
	    			type:"string",
	    			html: '<select name="psType" selectOptions="listValue" optionsType="11"></select>'
	    		},
	    		{	
	    			field:"psPost",
	    			name:"员工职位",
	    			type:"string",
	    			html: '<select name="psPost" selectOptions="listValue" optionsType="12"></select>'
	    		}
	    		
	    		
				
				
				]
	
    });
   

});
//-->
</script>
    	     




