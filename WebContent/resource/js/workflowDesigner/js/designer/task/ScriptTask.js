draw2d.ScriptTask=function(){
	draw2d.Task.call(this);
	this.setTitle("脚本任务");
	this.setIcon();
	this.scriptFormat=null;//脚本格式
	this.resultVariable=null;//返回变量
};
draw2d.ScriptTask.prototype=new draw2d.Task();
draw2d.ScriptTask.prototype.type="draw2d.ScriptTask";
draw2d.ScriptTask.newInstance=function(scriptTaskXMLNode){
	var task = new draw2d.ScriptTask();
	task.id=scriptTaskXMLNode.attr('id');
	task.taskId=scriptTaskXMLNode.attr('id');
	task.taskName=scriptTaskXMLNode.attr('name');
	task.setContent(scriptTaskXMLNode.attr('name'));
	return task;
};
draw2d.ScriptTask.prototype.setWorkflow=function(_5019){
	draw2d.Task.prototype.setWorkflow.call(this,_5019);
};
draw2d.ScriptTask.prototype.getContextMenu=function(){
	var menu = draw2d.Task.prototype.getContextMenu.call(this);
  return menu;
};
draw2d.ScriptTask.prototype.setIcon = function(){
	var icon=draw2d.Task.prototype.setIcon.call(this);
	icon.className="script-task-icon";
};
draw2d.ScriptTask.prototype.getStartElementXML=function(){
	var name = this.taskId;
	
	var xml='<scriptTask id="'+this.taskId+'" ';
	
	if(this.taskName!=null&&this.taskName!=""){
		xml+=' name="'+this.taskName+'" ';
	}
	
	if(this.scriptFormat!=null&&this.scriptFormat!=""){
		xml+=' scriptFormat="'+this.scriptFormat+'" ';
	}
	
	if(this.resultVariable!=null&&this.resultVariable!=''){
		xml=xml+'activiti:resultVariable="'+this.resultVariable+'" ';
	}

	xml=xml+'>\n';
	return xml;
};
draw2d.ScriptTask.prototype.getEndElementXML=function(){
	var xml = '</scriptTask>\n';
	return xml;
};

draw2d.ScriptTask.prototype.getScriptXML=function(){
	var xml='<script>';
	xml=xml+this.expression;
	xml=xml+'</script>';
	return xml;
};
draw2d.ScriptTask.prototype.figureDoubleClick=function(){
	var data = {event:this};
	var event = data.event;
	var tid = event.getId();
	openProperties(tid,'task/scriptTaskProperties');
};
draw2d.ScriptTask.prototype.getContextMenu=function(){
	if(this.workflow.disabled)return null;
	var menu =new draw2d.ContextMenu(100, 50);
	var data = {task:this};
	menu.appendMenuItem(new draw2d.ContextMenuItem("属性", "properties-icon",data,function(x,y)
	{
		var data = this.getData();
		var task = data.task;
		var tid = task.getId();
		if(typeof openProperties != "undefined"){
			openProperties(tid,'task/scriptTaskProperties');
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

draw2d.ScriptTask.prototype.toXML=function(){
	var xml=this.getStartElementXML();
	xml=xml+this.getDocumentationXML();
	xml=xml+this.getScriptXML();
	xml=xml+this.getEndElementXML();
	return xml;
};
draw2d.ScriptTask.prototype.toBpmnDI=function(){
	var w=this.getWidth();
	var h=this.getHeight();
	var x=this.getAbsoluteX();
	var y=this.getAbsoluteY();
	var xml='<bpmndi:BPMNShape bpmnElement="'+this.taskId+'" id="BPMNShape_'+this.taskId+'">\n';
	xml=xml+'<omgdc:Bounds height="'+h+'" width="'+w+'" x="'+x+'" y="'+y+'"/>\n';
	xml=xml+'</bpmndi:BPMNShape>\n';
	return xml;
};