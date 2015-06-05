<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>公文收文</caption>
	
	<tr>
		<th style="width: 60px;">收文时间</th>
		<td>
			<input type="text" taskId="task1376881267479" readonly="readonly"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
		</td>
		<th style="width: 60px;">文件类型</th>
		<td>
			<input type="text" taskId="task1376881267479" />
		</td>
	</tr>
	<tr>
		<th>秘密等级</th>
		<td>
			<select taskId="task1376881267479" >
				<option value="1">普通</option>
			  	<option value="2">秘密</option>
			  	<option value="3">机密</option>
			  	<option value="4">绝密</option>
			</select>
		</td>
		<th>紧急程度</th>
		<td>
			<select taskId="task1376881267479" >
				
				<option value="1">一般</option>
			  	<option value="2">紧急</option>
			  	<option value="3">特急</option>
			</select>
		</td>
	
	</tr>
	
	<tr>
		<th>发文单位</th>
		<td colspan="3" >
			<textarea  name="fwdw" taskId="task1376881267479" required="true" ></textarea>
		</td>
	</tr>
	
	<tr>
		<th>文件名称</th>
		
		<td >
			<input type="text" name="wjmc" taskId="task1376881267479"  required="true" />
		</td>
		
		<th>文件分数</th>
		<td>
			<input type="text" name="wjfs" taskId="task1376881267479" required="true" />
		</td>
	
	</tr>
	<tr>
		<th>主题词</th>
		<td colspan="3" >
			<input type="text" name="ztc" taskId="task1376881267479"  required="true" />
		</td>
	</tr>
	
	<tr>
		<th>内容摘要</th>
		<td colspan="3" >
			<textarea  name="nrzy" taskId="task1376881267479" required="true" ></textarea>
		</td>
	</tr>
	
	
	<tr>
		<th>
			拟办意见
		</th>
		<td colspan="3">
			<textarea taskId="task1376883171734" ></textarea>
		</td>
	</tr>
	
	<tr>
		<th>
			领导批阅
		</th>
		<td colspan="3">
			<textarea taskId="task1376883748443" ></textarea>
		</td>
	</tr>

	<tr>
		<th>签收人</th>
		<td colspan="3">
			<input type="text"  taskId="task1376881267479" readonly="readonly" loginUserInfo="name"   />
		</td>
		
	</tr>


</table>