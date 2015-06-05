<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	模块：个人办公-通讯录-公司通讯录
--%>


<div style="width: 80%; margin: 0 auto;">
	<form action="public/excel.do"  method="post" enctype="multipart/form-data"
		onsubmit="return validateSubmitForm(this)">

		<table class="table table-nobordered ">
			<tr>
				<th width="80px">

					分组：
				</th>
				<td>

					<select class="combox required" name="group">
						<c:forEach var="g" items="${group}">
							<option value="${g.id }">
								${g.gaName }
							</option>
						</c:forEach>
					</select>

				</td>
			</tr>
			<tr>
				<th>
					Excel导入：
				</th>
				<td>
					<input type="file" fileupload="no" class="easyui-linkbutton" required="true"  name="file"
						style="width: 80px"/>
					&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;&nbsp;
				</th>
				<td>
					<a target="_blank" href="./templet/importStaff.xls">模板下载</a>
				</td>
			</tr>
			<input type="hidden" name="otherBoxId" value="${param.rel}" />
			<input type="hidden" name="otherCallback" value="refresh" />
			<input type="hidden" name="currentCallback" value="close" />
			 

				<tr>
					<th>
					</th>
					<td>
						<div style="margin-top: 10px; margin-bottom: 10px;">
							<button type="submit" class="btn btn-primary">
								导入
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







