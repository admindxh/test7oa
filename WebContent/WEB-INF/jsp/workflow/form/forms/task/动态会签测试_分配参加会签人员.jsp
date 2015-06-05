<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<table class="flowtable" >
	
	<tr>
		<th style="width: 100px;">参加会签人员</th>
		<td>
			
			 <textarea  readonly="readonly" toName="user.trueName" required="true"  href="user/lookUpPage.do?type=2" lookupGroup="user" title="用户查询" targetBox="table"></textarea>
			
			<input type="hidden"  name="assigneeUserIdList"  toName="user.id"  processVariable="true" />
		</td>
	</tr>
	
</table>