(function($){
	
	$.fn.extend({
		    calendarInit: function(options){
		    	var defaults={
		    			defaultView:'agendaDay',
		    			monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		    			monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
		    			dayNames:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		    			dayNamesShort:['周日','周一','周二','周三','周四','周五','周六'],
		    			buttonText:{
		    				today: '今天',
		    				month: '月',
		    				week: '周',
		    				day: '日'
		    			},
		    			allDayText: '',
		    			axisFormat: 'H:mm',
		    			titleFormat: {
		    				month: 'yyyy年MMM月',
		    				week: "yyyy年 MMM月d日 { '&#8212;'[yyyy年] [ MMM月]d日 }",
		    				day: 'yyyy年MMM月d日 dddd'
		    			},
		    			columnFormat: {
		    				month: 'ddd',
		    				week: 'ddd M/d',
		    				day: 'dddd M/d'
		    			},
		    			timeFormat: { // for event elements
		    				'': 'H:mm' // default
		    			},
		    			firstDay:1,
		    			header: {
		    				left: 'prev,next today',
		    				center: 'title',
		    				right: 'agendaDay,agendaWeek,month'
		    			},
		    			editable: false,//默认不可拖拽 编辑
		    			allDayDefault:false,
		    			
		    	};
		    	$.extend(defaults,options);
		    	this.each(function(){
		    		$(this).fullCalendar(defaults);
		    	});
			}
		});

})(jQuery);
