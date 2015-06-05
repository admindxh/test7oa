<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-招聘管理-修改简历
--%>

	<div style="width:100%;margin: 0 auto;" >
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="middle" >&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="53%" valign="top">&nbsp;</td>
        </tr>
      <tr>
        <td valign="middle"><span class="left_txt">
			<div id="queryForm" align="center">
		<form action="job/update.do"  method="post"
		onsubmit="return validateSubmitForm(this)" name="saveResumeForm">
			<table align="center" width="800px" >
			<tr><td><font color="red">${mes}</font></td></tr>
				<tr>
				<td colspan="1" align="center"><h2>应聘简历</h2></td>
				</tr>
				<tr><td>${mes}</td></tr>
				<tr >
				<td align="right">填表日期:
						 <input name="pjDate" type="text"  size="20" maxlength="20" readonly="readonly" value="<fmt:formatDate  value="${job.pjDate}" pattern="yyyy-MM-dd" />"  />
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
				<table border="1" cellspacing="1" bordercolor="black" align="center" width="100%" id="tb">
					<tr>
					<td align="center" width="10%">姓名</td>
					<td colspan="2" ><input type="text" name="pjName" class="easyui-validatebox" required="true"  style="width:93%" value="${job.pjName }"/></td>
					<td align="center" width="50px">性别</td>
					<td ><input type="text" name="pjSex" style="width:84%" class="easyui-validatebox" required="true" value="${job.pjSex }"/></td>
					<td colspan="2" align="center" >出生日期</td>
					<td colspan="2"><input type="text" name="pjBirthdate" onFocus="WdatePicker()" style="width:90%" value="${job.pjBirthdate }"/></td>
					
					</tr>
					<tr>
					<td align="center">身份证号</td>
					<td colspan="2"><input type="text" name="pjIdNo"  style="width:93%" value="${job.pjIdNo }"/></td>
					<td align="center">民族</td>
					<td ><input type="text" name="pjEthnic"  style="width:84%" value="${job.pjEthnic }"/></td>
					<td colspan="2" align="center">婚姻状态</td>
					<td colspan="2"><input type="text" name="pjMarital"  style="width:90%" value="${job.pjMarital }"/></td>
					
					</tr>
					<tr>
					<td align="center">联系方式</td>
					<td colspan="2"><input type="text" name="pjPhone" style="width:93%" value="${job.pjPhone }"/></td>
					<td align="center">身高</td>
					<td ><input type="text" name="pjHeight"  style="width:84%" value="${job.pjHeight }"/></td>
					<td colspan="2" align="center">QQ</td>
					<td colspan="2"><input type="text" name="pjQq"  style="width:90%" value="${job.pjQq }"/></td>
					
					</tr>
					<tr>
					<td align="center">学历</td>
					<td colspan="2"><input type="text" name="pjEducation" style="width:93%" value="${job.pjEducation }"/></td>
					<td align="center">学位</td>
					<td colspan="2"><input type="text" name="pjDegree" style="width:84%" value="${job.pjDegree }"/></td>
					<td align="center">经验</td>
					<td colspan="2"><input type="text" name="pjExperience" style="width:90%" value="${job.pjExperience }"/></td>
					</tr>
					<tr>
					<td align="center">现住地址</td>
					<td colspan="3" ><input type="text" name="pjAddress"  style="width:95%" value="${job.pjAddress }" /></td>
					<td align="center">籍贯</td>
					<td colspan="2" style="width:20%""><input type="text" name="pjHometown"  style="width:182%" value="${job.pjHometown }"/></td>
					</tr>
					
					
					<tr>
					<td colspan="9">求职意向</td>
					</tr>
					<tr>
					<td align="center" colspan="2">应聘岗位</td>
					<td align="center" colspan="2">期望薪资</td>
					<td align="center" colspan="3">技能/特长</td>
					<td align="center" colspan="2">求职状态</td>
					
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%" value="${job.pjPost }" name="pjPost"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjSalary }" name="pjSalary"/></td>
					<td   colspan="3"><input type="text"  style="width:94%" value="${job.pjSkill }" name="pjSkill"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjJobStatus }" name="pjJobStatus"/></td>
					
					</tr>
					
					
					<tr>
					<td colspan="9">教育状况</td>
					</tr>
					<tr>
					<td align="center" colspan="2">起止时间</td>
					<td align="center" colspan="2">学校名称</td>
					<td align="center" colspan="3">所学专业</td>
					<td align="center" colspan="2">学历</td>
					
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%" value="${job.pjEduTime1 }" name="pjEduTime1"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjSchool1 }"  name="pjSchool1"/></td>
					<td   colspan="3"><input type="text"  style="width:94%" value="${job.pjSpecialty1 }" name="pjSpecialty1"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjEducation1 }"  name="pjEducation1"/></td>
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%" value="${job.pjEduTime2 }" name="pjEduTime2"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjSchool2 }" name="pjSchool2"/></td>
					<td   colspan="3"><input type="text"  style="width:94%" value="${job.pjSpecialty2 }" name="pjSpecialty2"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjEducation2 }"  name="pjEducation2"/></td>
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%"  value="${job.pjEduTime3 }" name="pjEduTime3"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjSchool3 }" name="pjSchool3"/></td>
					<td   colspan="3"><input type="text"  style="width:94%" value="${job.pjSpecialty3 }" name="pjSpecialty3"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjEducation3 }"  name="pjEducation3"/></td>
					</tr>
					
					<tr>
					<td colspan="9">工作经历</td>
					</tr>
					<tr>
					<td align="center" colspan="2">起止时间</td>
					<td align="center" colspan="2">工作单位</td>
					<td align="center" >职务</td>
					<td align="center" colspan="2">薪资</td>
					<td align="center" colspan="2">离职原因</td>
					
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%" value="${job.pjWorkTime1 }" name="pjWorkTime1"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjCompany1 }" name="pjCompany1"/></td>
					<td   ><input type="text"  style="width:90%" value="${job.pjPosition1 }"  name="pjPosition1"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjSalary1 }"  name="pjSalary1"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjLeave1 }"  name="pjLeave1"/></td>
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%" value="${job.pjWorkTime2 }" name="pjWorkTime2"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjCompany2 }" name="pjCompany2"/></td>
					<td   ><input type="text"  style="width:90%" value="${job.pjPosition2 }"  name="pjPosition2"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjSalary2 }"  name="pjSalary2"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjLeave2 }"  name="pjLeave2"/></td>
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%" value="${job.pjWorkTime3 }" name="pjWorkTime3"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjCompany3 }" name="pjCompany3"/></td>
					<td   ><input type="text"  style="width:90%" value="${job.pjPosition3 }" name="pjPosition3"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjSalary3 }"  name="pjSalary3"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjLeave3 }" name="pjLeave3"/></td>
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%"  value="${job.pjWorkTime4 }" name="pjWorkTime4"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjCompany4 }"  name="pjCompany4"/></td>
					<td   ><input type="text"  style="width:90%" 	value="${job.pjPosition4 }"	name="pjPosition4"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjSalary4}" name="pjSalary4"/></td>
					<td   colspan="2"><input type="text"  style="width:90%"  value="${job.pjLeave4 }"  name="pjLeave4"/></td>
					</tr>
					<tr>
					<td  colspan="2"><input type="text"  style="width:92%"  value="${job.pjWorkTime5 }" name="pjWorkTime5"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjCompany5 }"  name="pjCompany5"/></td>
					<td   ><input type="text"  style="width:90%" value="${job.pjPosition5 }"	name="pjPosition5"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjSalary5 }"  name="pjSalary5"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjLeave5 }"  name="pjLeave5"/></td>
					</tr>
					
					<tr>
					<td colspan="9">家庭成员</td>
					</tr>
					<tr>
					<td align="center" >称呼</td>
					<td align="center" colspan="2">姓名</td>
					<td align="center" colspan="2">单位及职务</td>
					
					<td align="center" colspan="2">居住地</td>
					<td align="center" colspan="2">联系方式</td>
					
					</tr>
					<tr>
					<td   ><input type="text"  style="width:80%" name="pjCall1" value="${job.pjCall1 }"/></td>
					<td  colspan="2"><input type="text"  style="width:94%"	value="${job.pjName1 }" name="pjName1"/></td>
					<td   colspan="2"><input type="text"  style="width:92%" value="${job.pjUnit1 }" name="pjUnit1"/></td>
					
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjResidence1 }"  name="pjResidence1"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjPhone1 }"  name="pjPhone1"/></td>
					</tr>
					<tr>
					<td   ><input type="text"  style="width:80%" name="pjCall2" value="${job.pjCall2 }"/></td>
					<td  colspan="2"><input type="text"  style="width:94%" 	value="${job.pjName2 }" name="pjName2"/></td>
					<td   colspan="2"><input type="text"  style="width:92%" value="${job.pjUnit2 }" name="pjUnit2"/></td>
					
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjResidence2 }"  name="pjResidence2"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjPhone2 }"  name="pjPhone2"/></td>
					</tr>
					<tr>
					<td   ><input type="text"  style="width:80%" name="pjCall3" value="${job.pjCall3 }" /></td>
					<td  colspan="2"><input type="text"  style="width:94%" 	value="${job.pjName3 }" name="pjName3"/></td>
					<td   colspan="2"><input type="text"  style="width:92%" value="${job.pjUnit3 }" name="pjUnit3"/></td>
					
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjResidence3 }"  name="pjResidence3"/></td>
					<td   colspan="2"><input type="text"  style="width:90%" value="${job.pjPhone3 }"  name="pjPhone3"/></td>
					</tr>
					
					<tr>
					<td colspan="9">项目经验（工作业绩）</td>
					</tr>
					<tr>
					<td colspan="9">
					<textarea  style="width:98%;height: 200px" name="pjProject"  ><c:out value="${job.pjProject }" /></textarea>
					</td>
					</tr>
					<tr>
					<td colspan="9">职业规划</td>
					</tr>
					<tr>
					<td colspan="9">
					<textarea  style="width:98%;height: 100px" name="pjPlan" ><c:out value="${job.pjPlan }"/></textarea>
					</td>
					</tr>
					<tr>
					<td colspan="9">自我评价</td>
					</tr>
					<tr>
					<td colspan="9">
					<textarea  style="width:98%;height: 100px" name="pjEvaluate" ><c:out value="${job.pjEvaluate }" /></textarea>
					</td>
					</tr>
					<tr>
					<td colspan="9">面试结果</td>
					</tr>
					<tr>
					<td colspan="9">
					<textarea  style="width:98%;height: 200px" name="pjResult" ><c:out value="${job.pjResult }"/></textarea>
					</td>
					</tr>
				</table>
				</td>
				</tr>
				
				<input type="hidden" name="id" value="${job.id }"/>
				<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
				<input type="hidden" name="currentCallback" value="close" />
				
				<tr>
					<td align="center">
						<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					</tr>
			</table>
		</form>
		</div>
		</td>
        </tr>
      
    </table></td>
    
  </tr>

</table>
</div>



