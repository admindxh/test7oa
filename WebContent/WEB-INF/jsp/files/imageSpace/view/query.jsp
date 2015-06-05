<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：文档管理--图片空间--图片管理
--%>

<div  class="search-div">
	<form  id="${param.rel}_form"  onsubmit="return imageSpaceSearch(this);" >
		<div  class="search-content " >
			
			<span>
				<label>类型：</label>
				<select name="type"   ajaxUrl="imageSpace/type/list.do" >
    				<option value="" >全部类型</option>
    			</select>
			</span>
			<span>
				<label>名称：</label>
				<input	type="text" name="name"  />
				&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
			</span>
			<button class="btn btn-primary btn-small" type="submit" style="margin-left: 5px">查询</button>
		
		</div>
	
	</form>
	
</div>
<div>
	
	<div id="${param.rel }_imgbox" class="easyui-panel" border="false">

			<%@ include file="/WEB-INF/jsp/files/imageSpace/view/img_list.jsp" %>

	</div>
	   
	    
	<div id="${param.rel }_pagination"  style="border:1px solid #ddd;margin-bottom: 5px"></div>
	
</div>

<script type="text/javascript">
<!--
	$(function(){
		var $form=$('#<%=request.getParameter("rel")%>_form');
		
		$('#<%=request.getParameter("rel")%>_pagination').pagination({
			layout:['list','sep','first','prev','links','next','last','sep'],
		    total:<%=(Long)request.getAttribute("total")%>,
		    pageSize:20,
		    onSelectPage:function(pageNumber, pageSize){
		    	var name=$form.data("name");
		    	if(!name){
		    		name="";
		    	}
		    	var type=$form.data("type");
		    	if(!type){
		    		type="";
		    	}
				//刷新图片
				$('#<%=request.getParameter("rel")%>_imgbox').panel('refresh', 'imageSpace/view/query.do?name='+name+'&type='+type+'&page='+pageNumber+'&rows='+pageSize);
				$form.data("pageSize",pageSize);//保存pageSize
			}
		});
		
	});
    
    function imageSpaceSearch(form){
    	var $form=$(form);
    	var type=$("select[name='type']",$form).val();
    	var name=$("input[name='name']",$form).val();
    	
    	$form.data("type",type);
    	$form.data("name",name);
   		var pageSize=$form.data("pageSize");
   		if(!pageSize)pageSize=20;
    	//搜索图片
		$('#<%=request.getParameter("rel")%>_imgbox').panel('refresh', 'imageSpace/view/query.do?name='+name+'&type='+type+'&page='+1+'&rows='+pageSize);
		
    	return false;
    }
    


//-->
</script>

