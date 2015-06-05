<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理--人员管理-人员添加  
--%>

<div style="width:900px;margin: 0 auto;" >

	<form action=" staff/add.do"  method="post"
		onsubmit="return validateSubmitForm(this)">
		<table class="table table-nobordered " >	
			<tr>
				<th width="80px">
					姓名：
				</th>
				<td>
					<input type="text" name="psName" class="easyui-validatebox" required="true" maxlength="50"
						size="35"/>

				</td>
			
				<th width="80px">
					性别：
				</th>
				<td>
				
   						<input type="radio" name="psSex" checked="checked" value="男" />
					男
   					
						<input type="radio" name="psSex" value="女" />
					女
  				 
				</td>
			</tr>
			<tr>
				<th>
					出生日期：
				</th>
				<td>
					<input type="text"  readonly="readonly" onfocus="WdatePicker({startDate:'1980-01-01'})" size="30" name="psBirthday"   />
					
				</td>
			
				<th>
					年龄：
				</th>
				<td>
					<input type="text" name="psAge" class="digits" maxlength="50"
						size="35" />

				</td>
			</tr>
			<tr>
				<th>
					现居住地：
				</th>
				<td>
					<input type="text" name="psPresentAddress" maxlength="50" size="35" />

				</td>
			
				<th>
					住宅电话：
				</th>
				<td>
					<input type="text" name="psHomePhone" maxlength="50" size="35" />

				</td>
			</tr>
			<tr>
				<th>
					手机号码：
				</th>
				<td>
					<input type="text" name="psPhone" class="phone" maxlength="50"
						size="35" />

				</td>
			
				<th>
					其他联系：
				</th>
				<td>
					<input type="text" name="psOther" maxlength="50" size="35" />

				</td>
			</tr>

			<tr>
				<th>
					户口所在地：
				</th>
				<td>
					<input type="text" name="psAccountAddress" maxlength="50" size="35"  />

				</td>
			
				<th>
					民族：
				</th>
				<td>
					<input type="text" name="psEthnic" maxlength="50" size="35" />

				</td>
			</tr>


			<tr>
				<th>
					身份证号：
				</th>
				<td>
					<input type="text" name="psCard" class="" maxlength="50" size="35" />

				</td>
			
				<th>
					籍贯：
				</th>
				<td>
					<input type="text" name="psHomeTown" maxlength="50" size="35" />

				</td>
			</tr>
			<tr>
				<th>
					登录账号：
				</th>
				<td>


					<a 	href="user/lookUpPage.do?type=1" 		lookupGroup="user" title="用户查询">
					 <input type="text"  toName="user.trueName"   /> </a>
					<input type="hidden"  name="psUserId"  toName="user.id">
					<a class="easyui-linkbutton clearLookup" icon="icon-cancel"
						plain="true" href="javascript:;" clearLookup="user" title="清空"></a>

				</td>
			
				<th>
					员工编号：
				</th>
				<td>
					<input type="text" name="psStaffId" class="" maxlength="50"
						size="35"/>

				</td>
			</tr>
			<tr>
				<th>
					工资卡号：
				</th>
				<td>
					<input type="text" name="psWage" class="" maxlength="50"
						size="35"/>

				</td>
			
				<th>
					社保号：
				</th>
				<td>
					<input type="text" name="psSocial" class="digits" maxlength="50"
						size="35" />

				</td>
			</tr>
			<tr>
				<th>
					最高学历：
				</th>
				<td>
					<select name="psEdu"  >
					<option value="">--请选择--</option>
						<my:outOptions type="13"/>
					</select>

				</td>
			
				<th>
					所学专业：
				</th>
				<td>
					<input type="text" name="psSpecialty" maxlength="50" size="35"  />

				</td>
			</tr>
			<tr>
				<th>
					毕业院校：
				</th>
				<td>
					<input type="text" name="psSchool" maxlength="50" size="35" />

				</td>
			
				<th>
					毕业时间：
				</th>
				<td>
					<input type="text" readonly="readonly" onFocus="WdatePicker()"
						size="30" name="psGraduation"  />
					
				</td>
			</tr>


			<tr>
				<th>
					员工类型：
				</th>
				<td>
					<select  name="psType"  >
					<option value="">--请选择--</option>
						<my:outOptions type="11"/>
					</select>

				</td>
		
				<th>
					员工职位：
				</th>
				<td>
					<select name="psPost" >
					<option value="">--请选择--</option>
						<my:outOptions type="12"/>
					</select>

				</td>
			</tr>
			<tr>
				<th>
					入职时间：
				</th>
				<td>
					<input type="text" readonly="readonly" onFocus="WdatePicker()"
						size="30" name="psEntryDate"  />
					
				</td>
				<th>
					职位状态：
				</th>
				<td>
					<select name="psStatus" id="status"  onchange="show()">
						<option value="在职">在职</option>
						<option value="离职">离职</option>
						<option value="退休">退休</option>
					</select>
					
				</td>
			</tr>
			
			<tr>
			
			<td colspan="4">
			<div id="div1" style="display: none;width:100%"   >
			<table class="table table-bordered " style="width: 100%" >
			<tr>
								<th width="80px">
									离职原因：
								</th>
								<td>
									<select  name="psResReason" >
										<option value="">--请选择--</option>
										<my:outOptions type="14"/>
									</select>
								</td>
							
								<th width="80px">
									离职时间：
								</th>
								<td>
									<input type="text" readonly="readonly" onFocus="WdatePicker()"
										name="psResDate"  />
									<a class="inputDateButton" href="javascript:;">选择</a>
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="3">
									<textarea class="edit" name="psResRemark" style="width: 82%"></textarea>
								</td>
							</tr>
			</table>
			</div>
			<div id="div2" style="display: none;width:100%"   >
			<table class="table table-bordered " style="width: 100%" >
				<tr>
								<th width="80px">
									退休原因：
								</th>
								<td>
									<select  name="psRetReason" >
									<option value="">--请选择--</option>
										<my:outOptions type="15"/>
									</select>
								</td>
							
								<th width="80px">
									退休时间：
								</th>
								<td>
									<input type="text" readonly="readonly" onFocus="WdatePicker()"
										name="psRetDate" />
									<a class="inputDateButton" href="javascript:;">选择</a>
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="3">
									<textarea class="edit" name="psRetRemark" style="width: 82%"></textarea>
								</td>
							</tr>
							</table></div></td></tr>
		
			
			</td>
			</tr>
<%--			<input type="hidden" name="psStatus" id="status" value="${status }" onpropertychange="show()">--%>
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
<%--	function value(from, bt) {--%>
<%--		var $form = $(from).closest("form");--%>
<%--		if (bt == '在职') {--%>
<%--			document.getElementById("status").value = bt;--%>
<%--		} else if (bt == '离职') {--%>
<%--			//$("input[toName='user.name']",$form).addClass("required");--%>
<%--			document.getElementById("status").value = bt;--%>
<%--		} else if (bt == '退休') {--%>
<%--			//	$("input[toName='user.name']",$form).removeClass("required"); --%>
<%--			document.getElementById("status").value = bt;--%>
<%--		}--%>
<%--	}--%>
<%--	//$("input[toName='user.name']", $form).removeClass("required");--%>


function show(){
	
	var status=$("#status").val();
	if(status=="在职"){
		document.getElementById("div1").style.display="none";
		document.getElementById("div2").style.display="none";
	}else if(status=="离职"){
		document.getElementById("div1").style.display="";
		document.getElementById("div2").style.display="none";
	}else if(status=="退休"){
		document.getElementById("div2").style.display="";
		document.getElementById("div1").style.display="none";
	}
	
}
</script>




