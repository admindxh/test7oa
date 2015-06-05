<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：文档管理--图片空间
--%>
<div  class="easyui-tabs"  fit="true" border="false" >
	<%--<div  title="图片预览"  >
		<%@ include file="/WEB-INF/jsp/files/imageSpace/view/query.jsp"%>
	</div>
	--%>
	<div  title="图片管理"  href="imageSpace/loadGl.do?rel=${param.rel }_gl" rel="${param.rel }_gl" >
		
	</div>
	<div  title="类型管理"  href="imageSpace/type/load.do?rel=${param.rel }_type" rel="${param.rel }_type" 	>
	
	</div>

</div>
