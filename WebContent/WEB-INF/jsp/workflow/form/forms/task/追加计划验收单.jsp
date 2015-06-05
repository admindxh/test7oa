<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<table class="flowtable" >
	
	<tr>
		<th style="width: 60px;">验收时间</th>
		<td>
			<input type="text"  name="yshj" readonly="readonly"  required="true" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		</td>
		
	</tr>
	<tr>
		<th >验收结果</th>
		<td>
			<textarea  ></textarea>
		</td>
	</tr>
	<tr>
		<th >备注</th>
		<td>
			<textarea  ></textarea>
		</td>
	</tr>
	
</table>