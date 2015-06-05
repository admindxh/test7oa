<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	服务器校验失败，返回错误信息
	
	 ajax 返回 消息格式
	statusCode:消息状态码
	message：消息提示。错误信息序列
	
--%>

{
	"statusCode":"${statusCode}", 
	"message":"提交数据不完整，<span style='color: red'>${errorCount}</span>个字段有错误，请改正后再提交!<br/>错误信息：<br/><c:forEach var='m' items='${message }' ><c:out value='${m }' /><br/></c:forEach>"
}