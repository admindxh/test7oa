<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：人事管理-招聘管理-查看简历
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
				<td  align="center" colspan="1" align="center"><h2>应聘简历</h2></td>
				</tr>
				<tr><td>${mes}</td></tr>
				<tr >
				<td align="right">填表日期:
						  <fmt:formatDate  value="${job.pjDate}" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
				<td  align="center" colspan="2" align="center">
				<table border="1" cellspacing="1" bordercolor="black" align="center" width="100%" id="tb">
					<tr>
					<td align="center" width="10%">姓名</td>
					<td  align="center" colspan="2" >&nbsp;<c:out  value="${job.pjName }"/></td>
					<td align="center" width="50px">性别</td>
					<td >&nbsp; <c:out  value="${job.pjSex }"/></td>
					<td  align="center" colspan="2" align="center" >出生日期</td>
					<td  align="center" colspan="2"> &nbsp;<c:out  value="${job.pjBirthdate }"/></td>
					
					</tr>
					<tr>
					<td align="center">身份证号</td>
					<td  align="center" colspan="2">&nbsp; <c:out  value="${job.pjIdNo }"/></td>
					<td align="center">民族</td>
					<td >&nbsp; <c:out  value="${job.pjEthnic }"/></td>
					<td  align="center" colspan="2" align="center">婚姻状态</td>
					<td  align="center" colspan="2">&nbsp; <c:out  value="${job.pjMarital }"/></td>
					
					</tr>
					<tr>
					<td align="center">联系方式</td>
					<td  align="center" colspan="2">&nbsp; <c:out  value="${job.pjPhone }"/></td>
					<td align="center">身高</td>
					<td >&nbsp; <c:out  value="${job.pjHeight }"/></td>
					<td  align="center" colspan="2" align="center">QQ</td>
					<td  align="center" colspan="2"> &nbsp;<c:out  value="${job.pjQq }"/></td>
					
					</tr>
					<tr>
					<td align="center">学历</td>
					<td  align="center" colspan="2">&nbsp;<c:out value="${job.pjEducation }"/></td>
					<td align="center">学位</td>
					<td  align="center" colspan="2">&nbsp;<c:out value="${job.pjDegree }"/></td>
					<td align="center">经验</td>
					<td  align="center" colspan="2">&nbsp;<c:out value="${job.pjExperience }"/></td>
					</tr>
					<tr>
					<td align="center">现住地址</td>
					<td  align="center" colspan="3" >&nbsp;<c:out value="${job.pjAddress }" /></td>
					<td align="center">籍贯</td>
					<td  align="center" colspan="2" style="width:20%"">&nbsp;<c:out value="${job.pjHometown }"/></td>
					</tr>
					
					
					<tr>
					<td  align="center" colspan="9">求职意向</td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">应聘岗位</td>
					<td align="center"  align="center" colspan="2">期望薪资</td>
					<td align="center"  align="center" colspan="3">技能/特长</td>
					<td align="center"  align="center" colspan="2">求职状态</td>
					
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjPost }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjSalary }" /></td>
					<td    align="center" colspan="3">&nbsp;<c:out value="${job.pjSkill }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjJobStatus }" /></td>
					
					</tr>
					
					
					<tr>
					<td  align="center" colspan="9">教育状况</td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">起止时间</td>
					<td align="center"  align="center" colspan="2">学校名称</td>
					<td align="center"  align="center" colspan="3">所学专业</td>
					<td align="center"  align="center" colspan="2">学历</td>
					
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjEduTime1 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjSchool1 }" /></td>
					<td    align="center" colspan="3">&nbsp;<c:out value="${job.pjSpecialty1 }"/></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjEducation1 }" /></td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjEduTime2 }"/></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjSchool2 }" /></td>
					<td    align="center" colspan="3">&nbsp;<c:out value="${job.pjSpecialty2 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjEducation2 }"/></td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out  value="${job.pjEduTime3 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjSchool3 }" /></td>
					<td    align="center" colspan="3">&nbsp;<c:out value="${job.pjSpecialty3 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjEducation3 }"  /></td>
					</tr>
					
					<tr>
					<td  align="center" colspan="9">工作经历</td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">起止时间</td>
					<td align="center"  align="center" colspan="2">工作单位</td>
					<td align="center" >职务</td>
					<td align="center"  align="center" colspan="2">薪资</td>
					<td align="center"  align="center" colspan="2">离职原因</td>
					
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjWorkTime1 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjCompany1 }" /></td>
					<td   align="center">&nbsp;<c:out value="${job.pjPosition1 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjSalary1 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjLeave1 }"  /></td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjWorkTime2 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjCompany2 }" /></td>
					<td  align="center" >&nbsp;<c:out value="${job.pjPosition2 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjSalary2 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjLeave2 }"  /></td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjWorkTime3 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjCompany3 }" /></td>
					<td  align="center" >&nbsp;<c:out value="${job.pjPosition3 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjSalary3 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjLeave3 }" /></td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out  value="${job.pjWorkTime4 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjCompany4 }" /></td>
					<td  align="center" >&nbsp;<c:out	value="${job.pjPosition4 }" /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjSalary4}"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out  value="${job.pjLeave4 }"   /></td>
					</tr>
					<tr>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjWorkTime5 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjCompany5 }"   /></td>
					<td  align="center" >&nbsp;<c:out value="${job.pjPosition5 }"	 /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjSalary5 }"   /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjLeave5 }"   /></td>
					</tr>
					
					<tr>
					<td  align="center" colspan="9">家庭成员</td>
					</tr>
					<tr>
					<td align="center" >称呼</td>
					<td align="center"  align="center" colspan="2">姓名</td>
					<td align="center"  align="center" colspan="2">单位及职务</td>
					
					<td align="center"  align="center" colspan="2">居住地</td>
					<td align="center"  align="center" colspan="2">联系方式</td>
					
					</tr>
					<tr>
					<td  align="center" >&nbsp;<c:out value="${job.pjCall1 }"/></td>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out	value="${job.pjName1 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjUnit1 }"  /></td>
					
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjResidence1 }"   /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjPhone1 }"   /></td>
					</tr>
					<tr>
					<td  align="center" >&nbsp;<c:out value="${job.pjCall2 }"/></td>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out 	value="${job.pjName2 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjUnit2 }"  /></td>
					
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjResidence2 }"   /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjPhone2 }"   /></td>
					</tr>
					<tr>
					<td   align="center" >&nbsp;<c:out value="${job.pjCall3 }" /></td>
					<td align="center"  align="center" colspan="2">&nbsp;<c:out value="${job.pjName3 }"  /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjUnit3 }"  /></td>
					
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjResidence3 }"   /></td>
					<td    align="center" colspan="2">&nbsp;<c:out value="${job.pjPhone3 }"   /></td>
					</tr>
					
					<tr>
					<td   colspan="9">项目经验（工作业绩）</td>
					</tr>
					<tr>
					<td   colspan="9" style="width:98%;height: 200px">
					&nbsp;<c:out value="${job.pjProject }" />
					</td>
					</tr>
					<tr>
					<td   colspan="9">职业规划</td>
					</tr>
					<tr>
					<td   colspan="9" style="width:98%;height: 100px">
					&nbsp;<c:out value="${job.pjPlan }"/>
					</td>
					</tr>
					<tr>
					<td   colspan="9">自我评价</td>
					</tr>
					<tr>
					<td   colspan="9" style="width:98%;height: 100px">
					&nbsp;<c:out value="${job.pjEvaluate }" />
					</td>
					</tr>
					<tr>
					<td   colspan="9">面试结果</td>
					</tr>
					<tr>
					<td   colspan="9" style="width:98%;height: 200px">
					&nbsp;<c:out value="${job.pjResult }"/>
					</td>
					</tr>
				</table>
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
	</body>
</html>


