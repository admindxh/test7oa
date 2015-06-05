draw2d.ManualTask=function(){
	draw2d.Task.call(this);
	this.setTitle("手工任务");
	this.setIcon();
};
draw2d.ManualTask.prototype=new draw2d.Task();
draw2d.ManualTask.prototype.type="draw2d.ManualTask";

draw2d.ManualTask.newInstance=function(manualTaskXMLNode){
	var task = new draw2d.ManualTask();
	task.id=manualTaskXMLNode.attr('id');
	task.taskId=manualTaskXMLNode.attr('id');
	task.taskName=manualTaskXMLNode.attr('name');
	task.setContent(manualTaskXMLNode.attr('name'));
	return task;
};
draw2d.ManualTask.prototype.setWorkflow=function(_5019){
	draw2d.Task.prototype.setWorkflow.call(this,_5019);
};

draw2d.ManualTask.prototype.figureDoubleClick=function(){
	var data = {event:this};
	var event = data.event;
	var tid = event.getId();
	openProperties(tid,'task/manualTaskProperties');
};
draw2d.ManualTask.prototype.getContextMenu=function(){
	if(this.workflow.disabled)return null;
	var menu =new draw2d.ContextMenu(100, 50);
	var data = {task:this};
	menu.appendMenuItem(new draw2d.ContextMenuItem("属性", "properties-icon",data,function(x,y)
	{
		var data = this.getData();
		var task = data.task;
		var tid = task.getId();
		if(typeof openProperties != "undefined"){
			openProperties(tid,'task/manualTaskProperties');
		}
	}));
	menu.appendMenuItem(new draw2d.ContextMenuItem("删除", "icon-remove",data,function(x,y)
	{
		var data = this.getData();
		var task = data.task;
		var tid = task.getId();
		var wf = task.getWorkflow();
		wf.getCommandStack().execute(new draw2d.CommandDelete(task));
	}));
	
	return menu;
};

draw2d.ManualTask.prototype.setIcon = function(){
	var icon=draw2d.Task.prototype.setIcon.call(this);
	icon.className="manual-task-icon";
};

draw2d.ManualTask.prototype.getStartElementXML=function(){
	var name = this.taskId;
	var taskName = trim(this.taskName);
	if(taskName != null && taskName != "")
		name = taskName;
	var xml='<manualTask id="'+this.taskId+'" name="'+name+'" ';
	
	xml=xml+'>\n';
	return xml;
};
draw2d.ManualTask.prototype.getEndElementXML=function(){
	var xml = '</manualTask>\n';
	return xml;
};

draw2d.ManualTask.prototype.getExtensionElementsXML=function(){
	if(this.listeners.getSize()==0&&this.forms.getSize()==0)return '';
	var xml = '<extensionElements>\n';
	xml=xml+this.getFormsXML();
	xml=xml+this.getListenersXML();
	xml=xml+'</extensionElements>\n';
	return xml;
};
draw2d.ManualTask.prototype.getListenersXML=function(){
	var xml = '';
	for(var i=0;i<this.listeners.getSize();i++){
		var listener = this.listeners.get(i);
		xml=xml+listener.toXML();
	}
	return xml;
};
draw2d.ManualTask.prototype.getFormsXML=function(){
	var xml = '';
	for(var i=0;i<this.forms.getSize();i++){
		var form = this.forms.get(i);
		xml=xml+form.toXML();
	}
	return xml;
};

draw2d.ManualTask.prototype.toXML=function(){
	var xml=this.getStartElementXML();
	xml=xml+this.getDocumentationXML();
	xml=xml+this.getExtensionElementsXML();
	xml=xml+this.getEndElementXML();
	return xml;
};
draw2d.ManualTask.prototype.toBpmnDI=function(){
	var w=this.getWidth();
	var h=this.getHeight();
	var x=this.getAbsoluteX();
	var y=this.getAbsoluteY();
	var xml='<bpmndi:BPMNShape bpmnElement="'+this.taskId+'" id="BPMNShape_'+this.taskId+'">\n';
	xml=xml+'<omgdc:Bounds height="'+h+'" width="'+w+'" x="'+x+'" y="'+y+'"/>\n';
	xml=xml+'</bpmndi:BPMNShape>\n';
	return xml;
};
