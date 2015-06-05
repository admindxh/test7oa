<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table class="flowtable" >
	<caption>追加计划申请单</caption>
	
	<tr>
		<th style="width: 60px;">申请人</th>
		<td>
			<input type="text"  taskId="task1375067415098" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th style="width: 60px;">申请日期</th>
		<td>
			<input type="text" taskId="task1375067415098" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>
	<tr>
		<th>部门</th>
		<td>
			<input type="text" taskId="task1375067415098" readonly="readonly" loginUserInfo="deptName" />
		</td>
		<th>计划类型</th>
		<td>
			<select taskId="task1375067415098" name="jhlx" required="true" >
				<option value="">--请选择--</option>
				<option value="0">一般</option>
			  	<option value="1">特殊</option>
			  	<option value="2">其他</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>产品名称</th>
		<td>
			<input type="text" taskId="task1375067415098" name="cpmc" required="true" />
		</td>
		<th>产品型号</th>
		<td>
			<input type="text" taskId="task1375067415098" name="cpxh" required="true"/>
		</td>
	</tr>
	
	<tr>
		<th>需求量</th>
		<td>
			<input type="text" taskId="task1375067415098"  name="xql" required="true"/>
		</td>
		<th>供货时间</th>
		<td>
			<input type="text" taskId="task1375067415098" name="ghsj" required="true"/>
		</td>
	</tr>
	<tr>
		<th>备注</th>
		<td colspan="3" >
			<textarea   taskId="task1375067415098" ></textarea>
		</td>
	</tr>
	
	<tr>
		<th>审批人</th>
		
		<td >
			<input type="text" taskId="task1375067789622" loginUserInfo="name"   />
		</td>
		
		<th>审批日期</th>
		<td>
			<input type="text" taskId="task1375067789622" nowDate="yyyy-MM-dd" />
		</td>
	
	</tr>
	<tr>
		<th>审批结果</th>
		<td colspan="3" >
			<select taskId="task1375067789622" name="isAgree" required="true" processVariable="true">
				<option value="" >--请选择--</option>
				<option value="1">做</option>
				<option value="0">不做</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>审批意见</th>
		<td colspan="3">
			<textarea taskId="task1375067789622" ></textarea>
		</td>
	</tr>

</table>