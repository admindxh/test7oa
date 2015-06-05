<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理--人员管理--在职员工
	--%>



	<form action=" staff/updateStatus.do" 
		method="post" onsubmit="return validateSubmitForm(this)">


		<div class="easyui-tabs"  border="false">
			<div  title="在职员工" onclick="value(this,'在职')"
				style="padding: 2px;">
				<div align="center"  style="margin-top: 80px;">
					<h2>
						暂不用填写
					</h2>
				</div>
				<div style="margin-top: 80px; margin-bottom: 10px;" align="center">
								<button type="submit" class="btn btn-primary">
									保存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn clear">
									清空
								</button>
							</div>
			</div>
		
			<div  title="离职员工" onclick="value(this,'离职')"
				style="padding: 2px;">
				<table class="table table-bordered " style="width: 100%">
					<tr>
						<th width="80px">
							离职原因：
						</th>
						<td>
							<select class="combox" name="psResReason">
								<option value="">
									--请选择--
								</option>
								<my:outOptions type="14"/>
							</select>
						</td>

						<th width="80px">
							离职时间：
						</th>
						<td>
							<input type="text" readonly="readonly" onFocus="WdatePicker()"
								name="psResDate" />
							<a class="inputDateButton" href="javascript:;">选择</a>
						</td>
					</tr>
					<tr>
						<th>
							备注：
						</th>
						<td colspan="3">
							<textarea class="edit" name="psResRemark" style="width: 88%"></textarea>
						</td>
					</tr>
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
			</div>
			<div  title="退休员工" onclick="value(this,'退休')"
				style="padding: 2px;">
				<table class="table table-bordered " style="width: 100%">
					<tr>
						<th width="80px">
							退休原因：
						</th>
						<td>
							<select class="combox" name="psRetReason">
								<option value="">
									--请选择--
								</option>
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
							<textarea class="edit" name="psRetRemark" style="width: 88%"></textarea>
						</td>
					</tr>
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
			</div>

		</div>


		<input type="hidden" name="id" value="${param.id}">
		<input type="hidden" name="psStatus" id="status" value="在职">
		<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
		<input type="hidden" name="currentCallback" value="close" />




	</form>




<script type="text/javascript">
	function value(from, bt) {
<%--alert(bt);--%>
		var $form = $(from).closest("form");
		if (bt == '在职') {
			document.getElementById("status").value = bt;
		} else if (bt == '离职') {
			//$("input[toName='user.name']",$form).addClass("required");
			document.getElementById("status").value = bt;

		} else if (bt == '退休') {
			//	$("input[toName='user.name']",$form).removeClass("required"); 
			document.getElementById("status").value = bt;

		}
	}
	//$("input[toName='user.name']", $form).removeClass("required");
</script>






