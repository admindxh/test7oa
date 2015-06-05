<%@ page contentType="text/plain;charset=UTF-8" pageEncoding="UTF-8"%>
<%--
	
	ajax 返回 消息格式  
	statusCode:消息状态码  300：操作失败，301：登录超时，200：操作成功
	message：消息提示。
		*****************************************************************************************
	easyUI
	
	//对当前容器进行的操作
	currentCallback: 
		  close 关闭当前
		  refresh 	刷新当前
		  url 	在当前组件刷新此url
	
	//对其他容器进行的操作
	otherCallback: 
				close
				refresh
				url
	//需要操作的其他容器的id			
	otherBoxId:  面板，弹出框，标签
	datagridId: 需要刷新的数据表格id
	callback_fn:回调函数
	
	rows,total,主要是为解决datagrid查询服务器异常时，默认rows为空， 解决js报错
--%>


{
	"statusCode":"${statusCode}", 
	"message":"${message}", 
	
	"currentCallback":"${param.currentCallback}",
	"otherCallback":"${param.otherCallback}",
	"otherBoxId":"${param.otherBoxId}",
	"datagridId":"${param.datagridId}",
	"callback_fn":"${param.callback_fn}",
	
	"rows":[],
	"total":0
}