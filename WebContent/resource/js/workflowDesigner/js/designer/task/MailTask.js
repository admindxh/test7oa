draw2d.MailTask=function(){
	draw2d.Task.call(this);
	this.setTitle("邮件任务");
	this.setIcon();
	
	this.toEmail=null;//收件人，多个用，隔开,必须
	this.fromEmail=null;//发件人，如果不设置使用默认配置，
	this.subjectEmail=null;//主题
	this.ccEmail=null;//邮件抄送人。可以使用逗号分隔多个接收者
	this.bccEmail=null;//邮件暗送人。可以使用逗号分隔多个接收者
	this.charsetEmail=null;//可以修改邮件的字符集，对很多非英语语言是必须设置的。
	this.htmlEmail=null;//作为邮件内容的HTML。
	this.textEmail=null;//邮件的内容，在需要使用原始文字（非富文本）的邮件时使用。 
						//可以与html一起使用，对于不支持富客户端的邮件客户端。 
						//客户端会降级到仅显示文本的方式。
	
};
draw2d.MailTask.prototype=new draw2d.Task();
draw2d.MailTask.prototype.type="draw2d.MailTask";
draw2d.MailTask.newInstance=function(mailTaskXMLNode){
	var task = new draw2d.MailTask();
	task.id=mailTaskXMLNode.attr('id');
	task.taskId=mailTaskXMLNode.attr('id');
	
	return task;
};
draw2d.MailTask.prototype.setWorkflow=function(_5019){
	draw2d.Task.prototype.setWorkflow.call(this,_5019);
};
draw2d.MailTask.prototype.getContextMenu=function(){
	var menu = draw2d.Task.prototype.getContextMenu.call(this);
  return menu;
};
draw2d.MailTask.prototype.setIcon = function(){
	var icon=draw2d.Task.prototype.setIcon.call(this);
	icon.className="mail-task-icon";
};
draw2d.MailTask.prototype.getStartElementXML=function(){
	var xml='<serviceTask id="'+this.taskId+'"  ';
	
	if(this.taskName!=null&&this.taskName!=""){
		xml+=' name="'+this.taskName+'" ';
	}
	xml=xml+'activiti:type="mail">\n<extensionElements>\n';
	xml=xml+'<activiti:field name="to" expression="'+this.toEmail+'" />\n';
	if(this.fromEmail!=null&&this.fromEmail!=''){
		xml=xml+'<activiti:field name="from" expression="'+this.fromEmail+'" />\n';
	}
	if(this.subjectEmail!=null&&this.subjectEmail!=''){
		xml=xml+'<activiti:field name="subject" expression="'+this.subjectEmail+'" />\n';
	}
	if(this.ccEmail!=null&&this.ccEmail!=''){
		xml=xml+'<activiti:field name="cc" expression="'+this.ccEmail+'" />\n';
	}
	if(this.bccEmail!=null&&this.bccEmail!=''){
		xml=xml+'<activiti:field name="bcc" expression="'+this.bccEmail+'" />\n';
	}
	var charset=this.charsetEmail;
	if(charset==null||charset==''){
		charset='UTF8';
	}
	if(this.charsetEmail!=null&&this.charsetEmail!=''){
		xml=xml+'<activiti:field name="charset" expression="'+charset+'" />\n';
	}
	if(this.htmlEmail!=null&&this.htmlEmail!=''){
		xml=xml+'<activiti:field name="html" >\n';
		xml=xml+'<activiti:expression>\n';
		xml=xml+'<![CDATA['+this.htmlEmail;
		xml=xml+']]>\n';
		xml=xml+'</activiti:expression>\n';
		xml=xml+'</activiti:field>\n';
	}
	if(this.textEmail!=null&&this.textEmail!=''){
		xml=xml+'<activiti:field name="text" >\n';
		xml=xml+'<activiti:expression>\n';
		xml=xml+'<![CDATA['+this.textEmail;
		xml=xml+']]>\n';
		xml=xml+'</activiti:expression>\n';
		xml=xml+'</activiti:field>\n';
	}
	xml=xml+'</extensionElements>\n';
	return xml;
};
draw2d.MailTask.prototype.getEndElementXML=function(){
	var xml = '</serviceTask>\n';
	return xml;
};

draw2d.MailTask.prototype.figureDoubleClick=function(){
	var data = {event:this};
	var event = data.event;
	var tid = event.getId();
	openProperties(tid,'task/mailTaskProperties');
};
draw2d.MailTask.prototype.getContextMenu=function(){
	if(this.workflow.disabled)return null;
	var menu =new draw2d.ContextMenu(100, 50);
	var data = {task:this};
	menu.appendMenuItem(new draw2d.ContextMenuItem("属性", "properties-icon",data,function(x,y)
	{
		var data = this.getData();
		var task = data.task;
		var tid = task.getId();
		if(typeof openProperties != "undefined"){
			openProperties(tid,'task/mailTaskProperties');
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

draw2d.MailTask.prototype.toXML=function(){
	var xml=this.getStartElementXML();
	xml=xml+this.getDocumentationXML();
	xml=xml+this.getEndElementXML();
	return xml;
};

draw2d.MailTask.prototype.toBpmnDI=function(){
	var w=this.getWidth();
	var h=this.getHeight();
	var x=this.getAbsoluteX();
	var y=this.getAbsoluteY();
	var xml='<bpmndi:BPMNShape bpmnElement="'+this.taskId+'" id="BPMNShape_'+this.taskId+'">\n';
	xml=xml+'<omgdc:Bounds height="'+h+'" width="'+w+'" x="'+x+'" y="'+y+'"/>\n';
	xml=xml+'</bpmndi:BPMNShape>\n';
	return xml;
};