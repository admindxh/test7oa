<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>发文登记单</caption>
	
	<tr style="width: 60px;">
		<th>公文标题</th>
		
		<td colspan="3" >
			<input type="text" name="gwbt" taskId="task1376896066259"  required="true" />
		</td>
	</tr>
	<tr>
		<th>收文字号</th>
		<td colspan="3" >
			<input type="text" name="swzh" taskId="task1376896066259"  required="true" />
		</td>
	</tr>
	<tr>
		<th>发文字号</th>
		<td colspan="3" >
			<input type="text" name="fwzh" taskId="task1376896066259"  required="true" />
		</td>
	</tr>
	<tr>
		<th>主题词</th>
		<td colspan="3" >
			<input type="text" name="ztc" taskId="task1376896066259"  required="true" />
		</td>
	</tr>
	<tr>
		<th>主送机关</th>
		<td colspan="3" >
			<textarea  name="zsjg" taskId="task1376896066259" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>抄送机关</th>
		<td colspan="3" >
			<textarea  name="csjg" taskId="task1376896066259" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th >起草单位</th>
		<td colspan="3" >
			<textarea  name="qcdw" taskId="task1376896066259" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>拟稿人</th>
		<td colspan="3">
			<input type="text"  taskId="task1376896066259" readonly="readonly" loginUserInfo="name"   />
		</td>
		
	</tr>
	<tr>
		<th>审稿人</th>
		<td colspan="3">
			<input type="text"  taskId="task1376896120327" readonly="readonly" loginUserInfo="name"   />
		</td>
		
	</tr>
	<tr>
		<th>核稿人</th>
		<td colspan="3">
			<input type="text"  taskId="task1376896139227" readonly="readonly" loginUserInfo="name"   />
		</td>
		
	</tr>
	

	<tr>
		<th>校对人</th>
		<td >
			<input type="text"  taskId="task1377244367031" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th>印制份数</th>
		<td >
			<input type="text"  taskId="task1377244367031"  name="yzfs" required="true"/>
		</td>
	</tr>


</table>