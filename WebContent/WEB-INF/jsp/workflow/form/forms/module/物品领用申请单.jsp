<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<table class="flowtable"  style="width: 800px">
	<caption>物品领用申请单</caption>
	
	<tr>
		<th style="width: 60px;">申请人</th>
		<td>
			<input type="text"  taskId="task1384501188646" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th style="width: 60px;">申请日期</th>
		<td>
			<input type="text" taskId="task1384501188646" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>
	
	<tr>
		<th>申请物品</th>
		<td colspan="3">
			
			<table class="table table-bordered itemDetail" taskId="task1384501188646"  addButton="添加"  style="width:98%;margin-top: 0;"
				template='
					<tr>
			 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
						<td>
				     		 <input type="text" taskId="task1384501188646"  class="easyui-validatebox"  required="true"  readonly="readonly" toName="goodsmanagerment.usingname" 
				     		 	 href="goodsmanagerment/lookUpPage.do"  lookupGroup="goodsmanagerment" lookupTitle="物品查询" targetBox="tr" />
						      
						      <input type="hidden"   name="usingnameid" readonly="readonly" toName="goodsmanagerment.id"  >
			
						</td>
						<td style="text-align: center;">
							<input type="text" readonly="readonly" taskId="task1384501188646"  toName="goodsmanagerment.danwei"/>
						</td>
						<td style="text-align: center;">
							<input type="text" class="easyui-numberbox" taskId="task1384501188646"  required="true" min="1" maxlength="11" />
						</td>
						
						<td style="text-align: center;"><a href="javascript:;"  class="btnDel" taskId="task1384501188646"   title="删除" ></a></td>	
					</tr>
				'>
				<thead>
					<tr>
						 <th width="35" style="text-align: center;">序号</th>
						 <th style="text-align: center;">物品</th>
						 <th width="60" style="text-align: center;">单位</th>
						 <th width="150" style="text-align: center;">数量</th>
					 	 <th width="35" style="text-align: center;">删除</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
				
		</td>
		
	</tr>
	<tr>
		<th>申领事由</th>
		<td colspan="3" >
			<textarea  name="sqsy" taskId="task1384501188646" class="easyui-validatebox" required="true" ></textarea>
		</td>
	</tr>
	<tr>
		<th>审批人</th>
		
		<td>
			<input type="text" taskId="task1384501193392" loginUserInfo="name"  readonly="readonly" />
		</td>
		
		<th>审批日期</th>
		<td>
			<input type="text" taskId="task1384501193392" nowDate="yyyy-MM-dd" readonly="readonly"/>
		</td>
	
	</tr>
	<tr>
		<th>审批结果</th>
		<td colspan="3" >
			<select taskId="task1384501193392" name="isAgree" class="easyui-validatebox" required="true"  processVariable="true">
				<option value="" >--请选择--</option>
				<option value="1">批准</option>
				<option value="0">不批准</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th>审批意见</th>
		<td colspan="3">
			<textarea taskId="task1384501193392" ></textarea>
		</td>
	</tr>
	<tr>
		<th style="width: 60px;">发放人</th>
		<td>
			<input type="text"  taskId="task1384501205934" readonly="readonly" loginUserInfo="name"   />
		</td>
		<th style="width: 60px;">发放日期</th>
		<td>
			<input type="text" taskId="task1384501205934" readonly="readonly" nowDate="yyyy-MM-dd" />
		</td>
	</tr>
	<tr>
		<th>发放备注</th>
		<td colspan="3" >
			<textarea  taskId="task1384501205934" ></textarea>
		</td>
	</tr>
	
	
</table>
