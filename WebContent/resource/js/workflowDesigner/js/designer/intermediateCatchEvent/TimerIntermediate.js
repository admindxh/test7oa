/**
 * 定时器中间事件
 */
draw2d.TimerIntermediate=function(_url){
	_url="resource/js/workflowDesigner/css/icons/timerevent.png";
	draw2d.ResizeImage.call(this,_url);
	this.rightOutputPort=null;
	this.leftOutputPort=null;
	this.topOutputPort=null;
	this.bottomOutputPort=null;
	this.boundaryId=null;
	this.name="";
	this.timeType=null;//事件类型
	this.expression="";
	this.setDimension(40,40);
};
draw2d.TimerIntermediate.prototype=new draw2d.Node();
draw2d.TimerIntermediate.prototype.type="draw2d.TimerIntermediate";
draw2d.TimerIntermediate.prototype.generateId=function(){
	this.id="intertimer"+Sequence.create();
	this.boundaryId=this.id;
	

};
draw2d.TimerIntermediate.prototype.createHTMLElement=function(){
	var item = draw2d.ResizeImage.prototype.createHTMLElement.call(this);
	return item;
};
draw2d.TimerIntermediate.prototype.setDimension=function(w, h){
	draw2d.ResizeImage.prototype.setDimension.call(this, w, h);
};
draw2d.TimerIntermediate.prototype.setWorkflow=function(_4fe5){
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
draw2d.TimerIntermediate.prototype.figureDoubleClick=function(){
	var data = {event:this};
	var event = data.event;
	var tid = event.getId();
	openProperties(tid,'intermediateCatchEvent/timerIntermediateEventProperties');
};
draw2d.TimerIntermediate.prototype.getContextMenu=function(){
	if(this.workflow.disabled)return null;
	var menu =new draw2d.ContextMenu(100, 50);
	var data = {event:this};
	menu.appendMenuItem(new draw2d.ContextMenuItem("属性", "properties-icon",data,function(x,y)
	{
		var data = this.getData();
		var event = data.event;
		var tid = event.getId();
		if(typeof openProperties != "undefined"){
			openProperties(tid,'intermediateCatchEvent/timerIntermediateEventProperties');
		}
	}));
	menu.appendMenuItem(new draw2d.ContextMenuItem("删除", "icon-remove",data,function(x,y)
	{
		var data = this.getData();
		var event = data.event;
		var tid = event.getId();
		var wf = event.getWorkflow();
		wf.getCommandStack().execute(new draw2d.CommandDelete(event));
		//wf.removeFigure(task);
	}));
	
	return menu;
};
draw2d.TimerIntermediate.prototype.toXML=function(){
	
	var xml='<intermediateCatchEvent id="'+this.boundaryId+'" name="'+this.name+'" >\n';
	xml=xml+'<timerEventDefinition>\n';
	if(this.timeType=='timeDate'){
		xml=xml+'<timeDate>'+this.expression+'</timeDate>\n';
	}else if(this.timeType=='timeDuration'){
		xml=xml+'<timeDuration>'+this.expression+'</timeDuration>\n';
	}else{
		xml=xml+'<timeCycle>'+this.expression+'</timeCycle>\n';
	}
	xml=xml+'</timerEventDefinition>\n';
	xml=xml+'</intermediateCatchEvent>\n';
	return xml;
};
draw2d.TimerIntermediate.prototype.toBpmnDI=function(){
	var w=this.getWidth();
	var h=this.getHeight();
	var x=this.getAbsoluteX();
	var y=this.getAbsoluteY();
	var xml='<bpmndi:BPMNShape bpmnElement="'+this.boundaryId+'" id="BPMNShape_'+this.boundaryId+'">\n';
	xml=xml+'<omgdc:Bounds height="'+h+'" width="'+w+'" x="'+x+'" y="'+y+'"/>\n';
	xml=xml+'</bpmndi:BPMNShape>\n';
	return xml;
};	
