<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：模块：人事管理-考勤管理-休息日设置
--%>

<div id="${param.rel }_calendar" style="width: 98%;margin: 5px auto;"></div>

<script type="text/javascript">
<!--
	$(function(){
	
		$('#<%=request.getParameter("rel")%>_calendar').calendarInit({
			defaultView:'month',
			
			header: {
				left: 'prev,next today',
				center: 'title'
				
			},
			events: function(start, end, callback) {
		        $.ajax({
		            url: 'attendance/view/closeTimeQuery.do',
		            cache: false,
		            dataType: 'json',
		            data: {
		                start: start.format("yyyy-MM-dd HH:mm:ss"),
		                end: end.format("yyyy-MM-dd HH:mm:ss")
		            },
		            success: function(data) {
		                var events = [];
		                $.each(data,function(i,d){
		                	 events.push({
		                		 	id:d.id,
			                        title: d.name,
			                        start: new Date(d.start),
			                        end:new Date(d.end)
			                    });
		                });
		                callback(events);
		            }
		        });
		    },
			dayClick:function( date, allDay, jsEvent, view ) {
				
		        MUI.openDialog('添加休息日','attendance/closeTimeSetPage.do?start='+date.format("yyyy-MM-dd HH:mm:ss")+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_add',{width:800,height:450});
		        
			},
			eventClick: function(calEvent, jsEvent, view) {
		        MUI.openDialog('修改休息日','attendance/updateCloseTimePage.do?id='+calEvent.id+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:800,height:450});
		    }
		    
		});
		
	});


//-->
</script>