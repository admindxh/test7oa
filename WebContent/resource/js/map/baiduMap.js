/**
 * 地图表单查询
 * @param form
 * @returns {Boolean}
 */ 
function baiduMapSearch(form){
		 
		var $form=$(form);
		if($form.attr("target")=="callbackframe"){
			 
			$.messager.progress({
				text : '表单提交中...'
			});
			//禁用表单提交按钮,将按钮文字改为提交中,并记录原文字
			$("button:submit",$form).each(function(){
				$(this).prop("disabled",true).addClass("disabled").attr("oldText",$(this).text()).text("查询中");
			});
			return false;
		} 
		if(!$form.form("validate")){
			Msg.alert("提示","表单填写不完整!",'warning');
			return false;
		}
		var userid =$("#userid").val();
		var starttime = $("#starttime").val(); 
		var endtime = $("#endtime").val(); 
		var x = $form.serializeArray(); 
			$.ajax({
				type: $form.attr("method") || 'POST',
				url:"locus/queryUsersLocus.do",
				//data:$form.serializeArray(),
				data:{"userid":userid,"starttime":starttime,"endtime":endtime},
				dataType:"json",
				cache: false,
				success:function(json){ 
					if(json!=null){ 
						mapLink(json);
					}else{
						Msg.alert("提示","表单提交无返回信息",'error');
					}
				},
				beforeSend:function(XMLHttpRequest){
					 
					$.messager.progress({
						text : '表单提交中...'
					});
					//禁用表单提交按钮,将按钮文字改为提交中,并记录原文字
					$("button:submit",$form).each(function(){
						$(this).prop("disabled",true).addClass("disabled").attr("oldText",$(this).text()).text("提交中");
					});
				},
				complete:function(XMLHttpRequest, textStatus){
					//禁用表单提交按钮,将按钮文字改为提交中,并记录原文字
					$("button:submit",$form).each(function(){
						$(this).prop("disabled",false).removeClass("disabled").text($(this).attr("oldText"));
					});
					$.messager.progress('close');
				}
			});
			return false; 
	}
   /**
    * 地图连点划线
    * @param json
    */
  function mapLink(json){  
		 
		var map = new BMap.Map("allmap");		// 创建Map实例
		var point ="";  
		 
		var startIcon = new BMap.Icon("resource/images/baiduMap/start.png", new BMap.Size(30,45));
		var positionIcon = new BMap.Icon("resource/images/baiduMap/position.png", new BMap.Size(30,45));
		var endIcon = new BMap.Icon("resource/images/baiduMap/end.png", new BMap.Size(30,45));

		//var marker2 = new BMap.Marker(pt,{icon:myIcon}); 
		map.enableScrollWheelZoom();                            //启用滚轮放大缩小
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		 
	 	var locusPoints = new Array();
		if(json.length>0){
			$.each(json, function(i, d){
			 
				var pt = new BMap.Point(d.longitude,d.latitude);
				locusPoints.push(pt);
			 
				// 编写自定义函数,创建标注
				var marker =null ;
				if(i==0){
					marker= new BMap.Marker(pt,{icon:endIcon});
				}else if(i==(json.length-1)){
					marker= new BMap.Marker(pt,{icon:startIcon});
				}else{
					marker= new BMap.Marker(pt,{icon:positionIcon});
					
				}
				//创建信息窗口
				var infoWindow = new BMap.InfoWindow("人员姓名:&nbsp;&nbsp;"+d.username+"<br>"+"定位时间:&nbsp;&nbsp;"+d.ltime);
				marker.addEventListener("click", function(){this.openInfoWindow(infoWindow);});
				map.addOverlay(marker);
				if(i==0){
					marker.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的动画
				}
			});
		}else{
			point = new BMap.Point(117.064218,36.690387);  // 创建点坐标
			locusPoints.push(point);
		} 
		
		//连线
		var polyline = new BMap.Polyline(locusPoints, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
		map.addOverlay(polyline);				
		 
		map.setViewport(locusPoints); // 初始化地图,设置中心点坐标和地图级别。
		map.addControl(new BMap.ScaleControl());                    // 添加默认比例尺控件

	}