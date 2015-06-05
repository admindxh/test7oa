draw2d.CallActivity=function(_url){
	_url="resource/js/workflowDesigner/css/icons/callactivity.png";
	draw2d.ResizeImage.call(this,_url);
	this.rightOutputPort=null;
	this.leftOutputPort=null;
	this.topOutputPort=null;
	this.bottomOutputPort=null;
	this.subProcessId=null;
	this.name="";
	this.documentation=null;
	this.insource=null;//主-->子 传入变量,  主流程变量名称
	this.intarget=null;//主-->子 传入变量,对应子流程变量名称
	this.outsource=null;//子-->主 传入变量,子流程变量名称
	this.outtarget=null;//子-->主 传入变量,对应主流程变量名称
	this.callSubProcess=null;//调用的子流程id
	this.setDimension(30,30);
};
draw2d.CallActivity.prototype=new draw2d.Node();
draw2d.CallActivity.prototype.type="draw2d.CallActivity";
draw2d.CallActivity.prototype.generateId=function(){
	this.id="callactivity"+Sequence.create();
	this.subProcessId=this.id;

};
draw2d.CallActivity.prototype.createHTMLElement=function(){
	var item = draw2d.ResizeImage.prototype.createHTMLElement.call(this);
	return item;
};
draw2d.CallActivity.prototype.setDimension=function(w, h){
	draw2d.ResizeImage.prototype.setDimension.call(this, w, h);
};
draw2d.CallActivity.prototype.setWorkflow=function(_4fe5){
	draw2d.ResizeImage.prototype.setWorkflow.call(this,_4fe5);
	this.resizeable=false;//不让改变大小
	if(_4fe5!==null&&this.rightOutputPort===null){
		this.rightOutputPort=new draw2d.MyOutputPort();
		this.rightOutputPort.setMaxFanOut(1);
		this.rightOutputPort.setWorkflow(_4fe5);
		this.rightOutputPort.setName("rightOutputPort");
		//this.rightOutputPort.setBackgroundColor(new draw2d.Color(245,115,115));
		this.addPort(this.rightOutputPort,this.width,this.height/2);
	}
	if(_4fe5!==null&&this.leftOutputPort===null){
		this.leftOutputPort=new draw2d.MyOutputPort();
		this.leftOutputPort.setMaxFanOut(1);
		this.leftOutputPort.setWorkflow(_4fe5);
		this.leftOutputPort.setName("leftOutputPort");
		//this.leftOutputPort.setBackgroundColor(new draw2d.Color(245,115,115));
		this.addPort(this.leftOutputPort,0,this.height/2);
	}
	if(_4fe5!==null&&this.topOutputPort===null){
		this.topOutputPort=new draw2d.MyOutputPort();
		this.topOutputPort.setMaxFanOut(1);
		this.topOutputPort.setWorkflow(_4fe5);
		this.topOutputPort.setName("topOutputPort");
		//this.topOutputPort.setBackgroundColor(new draw2d.Color(245,115,115));
		this.addPort(this.topOutputPort,this.width/2,0);
	}
	if(_4fe5!==null&&this.bottomOutputPort===null){
		this.bottomOutputPort=new draw2d.MyOutputPort();
		this.bottomOutputPort.setMaxFanOut(1);
		this.bottomOutputPort.setWorkflow(_4fe5);
		this.bottomOutputPort.setName("bottomOutputPort");
		//this.bottomOutputPort.setBackgroundColor(new draw2d.Color(245,115,115));
		this.addPort(this.bottomOutputPort,this.width/2,this.height);
	}
};
draw2d.CallActivity.prototype.figureDoubleClick=function(){
	var data = {event:this};
	var event = data.event;
	nodeid= event.getId();
	openProperties(nodeid,'subProcess/callActivityProperties');
};
draw2d.CallActivity.prototype.getContextMenu=function(){
	if(this.workflow.disabled)return null;
	var menu =new draw2d.ContextMenu(100, 50);
	var data = {event:this};
	menu.appendMenuItem(new draw2d.ContextMenuItem("属性", "properties-icon",data,function(x,y)
	{
		var data = this.getData();
		var event = data.event;
		nodeid = event.getId();
		if(typeof openProperties != "undefined"){
			openProperties(nodeid,'subProcess/callActivityProperties');
		}
	}));
	menu.appendMenuItem(new draw2d.ContextMenuItem("删除", "icon-remove",data,function(x,y)
	{
		var data = this.getData();
		var event = data.event;
		var tid = event.getId();
		var wf = event.getWorkflow();
		wf.getCommandStack().execute(new draw2d.CommandDelete(event));
	}));
	
	return menu;
};
draw2d.CallActivity.prototype.toXML=function(){
	
	var xml='<callActivity id="'+this.subProcessId+'" name="'+this.name+'" calledElement="'+this.callSubProcess+'" >\n';
	if(this.documentation!=null&&this.documentation!=''){
		xml=xml+'<documentation>';
		xml=xml+this.documentation;
		xml=xml+'</documentation>';
	}
	
	if((this.insource!=null&&this.intarget!=null&&this.insource!=''&&this.intarget!='')||(this.outsource!=null&&this.outtarget!=null&&this.outsource!=''&&this.outtarget!='')){
		xml=xml+'<extensionElements>\n';
	}
	
	if(this.insource!=null&&this.intarget!=null&&this.insource!=''&&this.intarget!=''){
		xml=xml+'<activiti:in source="'+this.insource+'" target="'+this.intarget+'"/>\n';
	}
	if(this.outsource!=null&&this.outtarget!=null&&this.outsource!=''&&this.outtarget!=''){
		xml=xml+'<activiti:out source="'+this.outsource+'" target="'+this.outtarget+'"/>\n';
	}
	if((this.insource!=null&&this.intarget!=null&&this.insource!=''&&this.intarget!='')||(this.outsource!=null&&this.outtarget!=null&&this.outsource!=''&&this.outtarget!='')){
		xml=xml+'</extensionElements>\n';
	}
	
	xml=xml+'</callActivity>\n';
	return xml;
};
draw2d.CallActivity.prototype.toBpmnDI=function(){
	var w=this.getWidth();
	var h=this.getHeight();
	var x=this.getAbsoluteX();
	var y=this.getAbsoluteY();
	var xml='<bpmndi:BPMNShape bpmnElement="'+this.subProcessId+'" id="BPMNShape_'+this.subProcessId+'">\n';
	xml=xml+'<omgdc:Bounds height="'+h+'" width="'+w+'" x="'+x+'" y="'+y+'"/>\n';
	xml=xml+'</bpmndi:BPMNShape>\n';
	return xml;
};	
