
//初始化信息
$(function(){
	try {
		$("body").append("<div id='lookup_window'></div> ");
		$lookupWindow=$("#lookup_window");//统一查找带回id
	
		$.fn.window.defaults.zIndex=10001;//解决不覆盖流程节点图标问题
		
		workflowId=$("#m_workflowId").val();
		flowId=$("#m_flowId").val();
		flowType=$("#m_flowType").val();
		flowName=$("#m_flowName").val();
		flowDesc=$("#m_flowDesc").val()
		if($("#m_flowXml").length==0){
			//无流程设计，需新建
			createCanvas(true, false);
		}else{
			createCanvas(false, false);
		}
		
		
		
		_task_obj = $('#task');
		_task_context_menu = $('#task-context-menu').menu({});
		$('.easyui-linkbutton').draggable({
			proxy : function(source) {
				var n = $('<div class="draggable-model-proxy"></div>');
				n.html($(source).html()).appendTo('body');
				return n;
			},
			deltaX : 0,
			deltaY : 0,
			revert : true,
			cursor : 'auto',
			onStartDrag : function() {
				$(this).draggable('options').cursor = 'not-allowed';
			},
			onStopDrag : function() {
				$(this).draggable('options').cursor = 'auto';
			}
		});
		$('#paintarea')
				.droppable(
						{
							accept : '.easyui-linkbutton',
							onDragEnter : function(e, source) {
								$(source).draggable('options').cursor = 'auto';
							},
							onDragLeave : function(e, source) {
								$(source).draggable('options').cursor = 'not-allowed';
							},
							onDrop : function(e, source) {
								$(this).removeClass('over');
								var wfModel = $(source).attr('wfModel');
								var shape = $(source).attr('shape');
								if (wfModel) {
									var x = $(source).draggable('proxy')
											.offset().left;
									var y = $(source).draggable('proxy')
											.offset().top;
									var xOffset = workflow.getAbsoluteX();
									var yOffset = workflow.getAbsoluteY();
									var scrollLeft = workflow
											.getScrollLeft();
									var scrollTop = workflow.getScrollTop();
									addModel(wfModel, x - xOffset
											+ scrollLeft, y - yOffset
											+ scrollTop, shape);
								}
							}
						});
		//$('#paintarea').bind('contextmenu',function(e){
		//alert(e.target.tagName);
		//});

	} catch (e) {
		//alert(e);
	}
	
	
	$('#process-definition-tab').tabs({
		fit : true,
		onSelect : function(title) {
			if (title == '设计') {
			} else if (title == '源码') {
				$('#descriptorarea').val(workflow.toXML());

			}
		}
	});
	
	
	
	
});

