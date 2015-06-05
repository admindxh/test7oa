<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：CRM-客户活动管理-活动信息-添加
--%>

<div style="width: 800px; margin: 0 auto;">

	<form action="activity/add.do" method="post" onsubmit="return validateSubmitForm(this)">
		<table class="table table-bordered ">
			<tr>
				<th width="100px">
					活动主题：
				</th>
				<td>
					<input type="text" name="acTitle" class="easyui-validatebox" required="true" validType="minlength[1]"  maxlength="50"  />
				</td>
				<th>
					方式：
				</th>
				<td>
					<select name="acType" style="width: 100px;" class="easyui-validatebox" required="true" >
						<option value="">
							--请选择--
						</option>
						<my:outOptions type="603" />
					</select>
				</td>
			</tr>
			<tr>
				<th>
					销售机会：
				</th>
				<td>
					<input type="text"  readonly="readonly" value="${ requestScope.c.scName}"   />
					<input type="hidden" name="chanceid" value="${requestScope.c.id }" />
				</td>
				<th>
					重要：
				</th>
				<td>
					<select name="acImportance" style="width: 100px;" class="easyui-validatebox" required="true">
						<option value="">请选择</option>
						<option value="0">一般</option>
						<option value="1">重要</option>
						<option value="2">非常重要</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					活动时间：
				</th>
				<td>
					<input type="text" required="true" class="easyui-validatebox span3"  readonly="readonly"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="acTime"  />
				</td>
				
				<th>
					客户(单位)：
				</th>
				<td>
					<input type="text" readonly="readonly"    value="${requestScope.info.clName }"  />
					<input type="hidden" name="acDanwei"  value="${requestScope.info.id}"  />
				</td>
			</tr>
			<tr>
				<th>
					客户联系人：
				</th>
				<td>
					<input type="text" name="acContacts" value="${requestScope.man.liName}"   class="easyui-validatebox" required="true" validType="minlength[1]"   />
					
				</td>
				<th>
					活动负责人：
				</th>
				<td>
					<input type="text" name="acOurStaff" toName="user.trueName" maxlength="30" class="easyui-validatebox" required="true" />
					<a href="user/lookUpPage.do?type=1" lookupGroup="user" title="用户查询"><img src="resource/images/menu/24507_s.gif"  style="margin-top:-3px;"/></a>
				</td>
			</tr>
			<tr>
				<th>
					活动详情：
				</th>
				<td colspan="3">
					<textarea name="acDetail" rows="6" cols="100" style="width:608px"></textarea>
				</td>
				
			</tr>
			<tr>
				<th></th>
				<td align="left" colspan="3"><input type="button" class="btn btn-primary btn-lg" value="活动细节" onclick="doclick(this)"></td>
			</tr>
			<tr style="display:none">
				<th></th>
				<td colspan="3">
					
						<table>
							<tr>
								<th>
									活动地点：
								</th>
								<td>
									<input type="text" name="acPlace" style="width: 250px;" />
								</td>
								<th>
									持续时间：
								</th>
								<td>
									<input type="text" name="acDuration"  style="width: 100px;" class="easyui-numberbox" maxlength="4" />(小时)
								</td>
						   </tr>
							<tr>
								<th>
									增送礼品：
								</th>
								<td>
									<input type="text" name="acGiveGift"  style="width: 250px;" />
								</td>
								<th>
									礼品数量：
								</th>
								<td>
									<input type="text" name="acGiftNum" style="width: 100px;"  class="easyui-numberbox" maxlength="6" />
								</td>
						   </tr>
						   <tr>
								<th>
									花费：
								</th>
								<td colspan="3">
									<input type="text" name="acSpend"  style="width: 100px;" class="easyui-numberbox" data-options="precision:2" maxlength="10"/>
								</td>
						   </tr>
						</table>
					
				</td>
			</tr>
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
			<input type="hidden" name="currentCallback" value="close" />

			<tr>
				<th>
				</th>
				<td>
					<div style="margin-top: 10px; margin-bottom: 10px;">
						<button type="submit" class="btn btn-primary">
							保存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn clear">
							清空
						</button>
					</div>
				</td>
			</tr>
		</table>
	</form>	
</div>
<script type="text/javascript">
<!--			
			
			var flag=true;
			function doclick(s){
				var $tr=$(s).closest("tr");
				if(flag==true){
					$tr.next().show();
					flag=false;
				}else{
					$tr.next().hide();
					flag=true;
				}
			}
	
//-->
</script>
