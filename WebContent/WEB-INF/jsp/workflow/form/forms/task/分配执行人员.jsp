<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<table class="flowtable" >
	
	<tr>
		<th style="width: 100px;">下一步执行人</th>
		<td>
			
			 <input  type="text" toName="user.trueName" required="true"  href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询" targetBox="table"/>
			
			<input type="hidden"  name="assigneeUserId"  toName="user.id"  processVariable="true" />
		</td>
	</tr>
	
</table>