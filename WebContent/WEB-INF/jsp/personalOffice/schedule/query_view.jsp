<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：个人办公--日程安排
--%>

<div id="${param.rel }_calendar" style="width: 98%;margin: 5px auto;"></div>

<script type="text/javascript">
<!--
	$(function(){
	
		$('#<%=request.getParameter("rel")%>_calendar').calendarInit({
			events: function(start, end, callback) {
		        $.ajax({
		            url: 'schedule/view/query.do',
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
			                        title: d.title,
			                        start: new Date(d.start),
			                        end:new Date(d.end)
			                    });
		                });
		                callback(events);
		            }
		        });
		    },
			dayClick:function( date, allDay, jsEvent, view ) {
				
		        MUI.openDialog('添加日程安排','schedule/addPage.do?view=true&start='+date.format("yyyy-MM-dd HH:mm:ss")+'&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_add',{width:1000,height:450});
		        
			},
			eventClick: function(calEvent, jsEvent, view) {
		        MUI.openDialog('修改日程安排','schedule/updatePage.do?id='+calEvent.id+'&view=true&rel=<%=request.getParameter("rel")%>','<%=request.getParameter("rel")%>_update',{width:1000,height:450});
		    }
		    
		});
		
	});


//-->
</script>