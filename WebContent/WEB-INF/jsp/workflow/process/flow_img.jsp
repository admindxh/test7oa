<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：工作流程--流程图显示页面
--%>
<div  style="position: relative;background-color:  ">

	<img  src="processDefinition/showFlowImg.do?processDefinitionId=${param.processDefinitionId }" id="flow_img_" style="max-width: none;" /><br/>
		
</div>
<%--如果是根据流程实例显示流程图，则查询出当前流程进行到的节点--%>
<script type="text/javascript">
<!--
	$(function(){

		var pId='<%=(String)request.getParameter("processInstanceId")%>';
		if(pId=="null"){
			pId="";
		}
		$.ajax({
			url:'processDefinition/activityCoordinates.do?processDefinitionId=<%=(String)request.getParameter("processDefinitionId")%>&processInstanceId='+pId,
			cache: false,
			dataType:"json",
			success:function(json){
				var html="";
				$.each(json, function(i, j){
					if(j.current){
						html+="<div class='node-info' style='position: absolute; left: "+(j.x)+"px; top:"+(j.y)+"px; width: "+j.width+"px; height: "+j.height+"px; border: 2px red solid;'>";
					}else{
						html+="<div class='node-info' style='position: absolute; left: "+(j.x)+"px; top:"+(j.y)+"px; width: "+j.width+"px; height: "+j.height+"px;'>";
					}
					html+="<div  style='display: none;'><div  style='padding:10px'>名称："+j.name+"<br/><br/>描述："+(j.documentation||"");
					if(j.assignee){
						html+="<br/><br/>执行人："+j.assignee;
					}else{
						if(j.userNames){
							html+="<br/><br/>待选人："+j.userNames;
						}
						if(j.roleNames){
							html+="<br/><br/>待选角色："+j.roleNames;
						}
					}
					html+="</div></div></div>";
					
				});
				var $img=$("#flow_img_").after(html);
				var $div=$img.parent();
				$(".node-info",$div).each(function(){
					var $this=$(this);
					//解决ie下 设置 position absolute 时 mouseenter等一些事件无效,需设置背景解决
					if ($.browser.msie){
						if($.browser.msie<9){
							 $this.css({"filter":"Alpha(opacity=1)","background":"#fff"});
						}else{
							 $this.css({"box-shadow" : "0 1px 1px rgba(0, 0, 0, 0.075) inset"});
						}
					}
					$this.tooltip({  
						 content: $(">div",$this).html()  
				    });
				});
				
			}
		});
		
	});
//-->
</script>
	
