<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--
	模块：公司文档管理：创建文件
--%>
<div style="width: 600px;margin: 0 auto;" >					
<form method="post" action="database/backup.do"  onsubmit="return validateSubmitForm(this)">
						
	<table class="table table-bordered ">
			<tr>
				<th>文件名：</th>
				<td>
					<input type="text" name="name" class="easyui-validatebox"  required="true"   validType="minlength[1]"  maxlength="50" /><br/>
					<span class="text-info">为备份文件命名，例：首次备份.sql。</span>
				</td>
			</tr>
			<tr>
				<th>提醒：</th>
				<td>系统本身有自动备份功能，建议在特殊情况下才进行手动备份。<br/>因备份时会锁住所有表，为不影响使用，建议在系统不使用的时候备份。<br/>数据量越大，备份时间越长，如需导出备份文件，请等到数据完全备份完成之后再导出。</td>
			</tr>
			<tr>
				<th></th>
				<td>
					<div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >备份</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
					</div>
				</td>
			 </tr>
			
	</table>
	<input type="hidden" name="currentCallback" value="close" />
	<input type="hidden" name="otherCallback" value="refresh" />
	<input type="hidden" name="otherBoxId" value="${param.rel }" />
	
	
</form>
</div>
										
						
					
		

	

