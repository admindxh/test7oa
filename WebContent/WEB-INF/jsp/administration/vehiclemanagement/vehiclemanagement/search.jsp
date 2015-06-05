<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--行政办公--车辆管理-车辆管理--高级搜索--%>

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
    			field:"licenseno",
    			name:"车牌号",
    			type:"string",
    			html: '<input type="text" name="licenseno"/>'
    		},
    		{	
    			field:"vg.vehicletypeId",
    			name:"车辆类型",
    			type:"string",
    			html: '<select name="vg.vehicletypeId" selectOptions="listValue" optionsType="10"></select>'
    		},
    		{	
    			field:"usingunit",
    			name:"使用单位",
    			type:"string",
    			html: '<input type="text" name="usingunit"/>'
    		},
    		{
    			field:"vt.drivingname",
    			name:"驾驶员",
    			type:"string",
    			html:'<a href="driver/lookupdriver.do"  lookupGroup="accident" title="驾驶员姓名查询" targetBox="td"><input type="text"  readonly="readonly" name="vt.drivingname"  toName="accident.name" class="span2"/></a>'
    		},
    		{	
    			field:"buydate",
    			name:"购买日期",
    			type:"date",
    			html: '<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="buydate"  />'
    		},
    		{	
    			field:"usingunit",
    			name:"发动机号",
    			type:"string",
    			html: '<input type="text" name="usingunit" />'
    		},
    		{	
    			field:"status",
    			name:"状态",
    			type:"string",
    			html: '<select  name="status" >'
		    			+'<option value="0">在库</option><option value="1">使用中</option>'
		    			+'<option value="2">维修</option><option value="3">报废</option></select>'
    		},
    		{	
    			field:"pono",
    			name:"出产编号",
    			type:"string",
    			html: '<input type="text" name="pono" />'
    		},
    		{	
    			field:"insurancetime",
    			name:"强制险时间",
    			type:"date",
    			html: '<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="insurancetime"  />'
    		},
    		{	
    			field:"cinsurancetime",
    			name:"商业险时间",
    			type:"date",
    			html: '<input type="text"  readonly="readonly" onFocus="WdatePicker()" name="cinsurancetime"  />'
    		}
    	]
    	
    });
   

});
//-->
</script>