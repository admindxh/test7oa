/**
 * 用户任务对象
 */
draw2d.UserTask=function(){
	draw2d.Task.call(this);
	this.setTitle("用户任务");
	this.setIcon();
	this.formKey = null;//表单
	this.performerType=null;//人员指派类型
	this.users=null;//任务候选人
	this.groups=null;//任务候选组
	this.dueDate=null;//任务到期时间
	this.priority=null;//优先级
	this.taskListeners=new draw2d.ArrayList();//用户任务监听器
};
draw2d.UserTask.prototype=new draw2d.Task();
draw2d.UserTask.prototype.type="draw2d.UserTask";
draw2d.UserTask.newInstance=function(userTaskXMLNode){
	var task = new draw2d.UserTask();
	task.id=userTaskXMLNode.attr('id');
	task.taskId=userTaskXMLNode.attr('id');
	task.taskName=userTaskXMLNode.attr('name');
	task.setContent(userTaskXMLNode.attr('name'));
	return task;
};
draw2d.UserTask.prototype.setWorkflow=function(_5019){
	draw2d.Task.prototype.setWorkflow.call(this,_5019);
};
draw2d.UserTask.prototype.getContextMenu=function(){
	var menu = draw2d.Task.prototype.getContextMenu.call(this);
  return menu;
};
draw2d.UserTask.prototype.setIcon = function(){
	var icon=draw2d.Task.prototype.setIcon.call(this);
	icon.className="user-task-icon";
};
//双击流程元素打开属性页面
draw2d.UserTask.prototype.figureDoubleClick=function(){
	var data = {event:this};
	var event = data.event;
	nodeid = event.getId();
	openProperties(nodeid,'task/userTaskProperties','task');
};
draw2d.UserTask.prototype.getContextMenu=function(){
	if(this.workflow.disabled)return null;
	var menu =new draw2d.ContextMenu(100, 50);
	var data = {task:this};
	menu.appendMenuItem(new draw2d.ContextMenuItem("属性", "properties-icon",data,function(x,y)
	{
		var data = this.getData();
		var task = data.task;
		nodeid = task.getId();
		if(typeof openProperties != "undefined"){
			openProperties(nodeid,'task/userTaskProperties','task');
		}
	}));
	menu.appendMenuItem(new draw2d.ContextMenuItem("删除", "icon-remove",data,function(x,y)
	{
		var data = this.getData();
		var task = data.task;
		var tid = task.getId();
		var wf = task.getWorkflow();
		wf.getCommandStack().execute(new draw2d.CommandDelete(task));
		//wf.removeFigure(task);
	}));
	
	return menu;
};
draw2d.UserTask.prototype.getTaskListener=function(id){
	for(var i=0;i<this.taskListeners.getSize();i++){
		var listener = this.taskListeners.get(i);
		if(listener.getId()=== id){
			return listener;
		}
	}
};
//用户任务监听器相关操作
draw2d.UserTask.prototype.deleteTaskListener=function(id){
	var listener = this.getTaskListener(id);
	this.taskListeners.remove(listener);
};
draw2d.UserTask.prototype.addTaskListener=function(listener){
	this.taskListeners.add(listener);
};
draw2d.UserTask.prototype.setTaskListeners=function(listeners){
	this.taskListeners = listeners;
};


draw2d.UserTask.prototype.getStartElementXML=function(){
	//var name = this.taskId;
	var taskName = trim(this.taskName);
	//if(taskName != null && taskName != "")
	//	name = taskName;
	var xml='<userTask id="'+this.taskId+'" ';
	if(taskName!=null&&taskName!=""){
		xml+=' name="'+taskName+'" ';
	}
	
	if(this.expression!=null&&this.expression!=""){
		xml=xml+'activiti:assignee="'+this.expression+'" ';
	}else {
		if(this.users!=null&&this.users!=""){
			xml=xml+'activiti:candidateUsers="'+this.users+'" ';
		}
		if(this.groups!=null&&this.groups!=""){
			xml=xml+'activiti:candidateGroups="'+this.groups+'" ';
		}
	}
	if(this.formKey != null && this.formKey != ""){
		xml=xml+'activiti:formKey="'+this.formKey+'" ';
	}
	if(this.dueDate != null&&this.dueDate != ""){
		xml+=' activiti:dueDate="'+this.dueDate+'" ';
	}
	if(this.priority != null&&this.priority != ""){
		xml+=' activiti:priority="'+this.priority+'" ';
	}
	
	xml=xml+'>\n';
	return xml;
};
draw2d.UserTask.prototype.getEndElementXML=function(){
	var xml = '</userTask>\n';
	return xml;
};

draw2d.UserTask.prototype.getExtensionElementsXML=function(){
	if(this.listeners.getSize()==0&&this.taskListeners.getSize()==0&&this.forms.getSize()==0)return '';
	var xml = '<extensionElements>\n';
	xml=xml+this.getFormsXML();
	xml=xml+this.getListenersXML();
	xml=xml+this.getTaskListenersXML();
	xml=xml+'</extensionElements>\n';
	return xml;
};
//多实例
draw2d.UserTask.prototype.getMultiInstanceXML=function(){
	if(this.isSequential==null || this.isSequential=="")return '';
	var xml = '<multiInstanceLoopCharacteristics  isSequential="'+this.isSequential+'" ';
	if(this.collection!=null&&this.collection!=""){
		xml+=' activiti:collection="'+this.collection+'" '
	}
	if(this.elementVariable!=null && this.elementVariable !=""){
		xml+=' activiti:elementVariable="'+this.elementVariable+'" '
	}
	xml+=' >\n';
	
	if(this.loopCardinality!=null&& this.loopCardinality!=""){
		xml+='<loopCardinality>'+this.loopCardinality+'</loopCardinality>\n';
	}
	if(this.completionCondition !=null && this.completionCondition !=""){
		xml+='<completionCondition>'+this.completionCondition+'</completionCondition>\n';
	}
	xml+='</multiInstanceLoopCharacteristics>\n';
	return xml;
};

draw2d.UserTask.prototype.getListenersXML=function(){
	var xml = '';
	for(var i=0;i<this.listeners.getSize();i++){
		var listener = this.listeners.get(i);
		xml=xml+listener.toXML();
	}
	return xml;
};
draw2d.UserTask.prototype.getTaskListenersXML=function(){
	var xml = '';
	for(var i=0;i<this.taskListeners.getSize();i++){
		var listener = this.taskListeners.get(i);
		xml=xml+listener.toXML();
	}
	return xml;
};
draw2d.UserTask.prototype.getFormsXML=function(){
	var xml = '';
	for(var i=0;i<this.forms.getSize();i++){
		var form = this.forms.get(i);
		xml=xml+form.toXML();
	}
	return xml;
};

draw2d.UserTask.prototype.getPerformersBPMNXML=function(){
	var xml = '';
	if(this.performerType=='candidateUsers'){
		if(this.candidateUsers.getSize()!=0){
			xml=xml+'<potentialOwner>\n';
			xml=xml+'<resourceAssignmentExpression>\n';
			xml=xml+'<formalExpression>\n';
			xml=xml+'<![CDATA[';
			for(var i=0;i<this.candidateUsers.getSize();i++){
				var user = this.candidateUsers.get(i);
				xml=xml+'user('+user.sso+'),';
			}
			xml=xml.substring(0, xml.length-1);
			xml=xml+']]>\n';
			xml=xml+'</formalExpression>\n';
			xml=xml+'</resourceAssignmentExpression>\n';
			xml=xml+'</potentialOwner>\n';
		}
	}else if(this.performerType=='candidateGroups'){
		if(this.candidateGroups.getSize()!=0){
			xml=xml+'<potentialOwner>\n';
			xml=xml+'<resourceAssignmentExpression>\n';
			xml=xml+'<formalExpression>\n';
			xml=xml+'<![CDATA[';
			for(var i=0;i<this.candidateGroups.getSize();i++){
				var group = this.candidateGroups.get(i);
				xml=xml+'group('+group+'),';
			}
			xml=xml.substring(0, xml.length-1);
			xml=xml+']]>\n';
			xml=xml+'</formalExpression>\n';
			xml=xml+'</resourceAssignmentExpression>\n';
			xml=xml+'</potentialOwner>\n';
		}
	}
	return xml;
};
draw2d.UserTask.prototype.toXML=function(){
	var xml=this.getStartElementXML();
	xml=xml+this.getDocumentationXML();
	xml=xml+this.getMultiInstanceXML();//多实例
	xml=xml+this.getExtensionElementsXML();
	xml=xml+this.getPerformersBPMNXML();
	xml=xml+this.getEndElementXML();
	return xml;
};
draw2d.UserTask.prototype.toBpmnDI=function(){
	var w=this.getWidth();
	var h=this.getHeight();
	var x=this.getAbsoluteX();
	var y=this.getAbsoluteY();
	var xml='<bpmndi:BPMNShape bpmnElement="'+this.taskId+'" id="BPMNShape_'+this.taskId+'">\n';
	xml=xml+'<omgdc:Bounds height="'+h+'" width="'+w+'" x="'+x+'" y="'+y+'"/>\n';
	xml=xml+'</bpmndi:BPMNShape>\n';
	return xml;
};