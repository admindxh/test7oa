<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：人事管理-考勤管理-考勤时间设置详情
--%>

<div style="width: 98%;margin: 0 auto;" >						
						
	<table class="table table-bordered ">
	
			<tr>
				<th style="width: 120px">名称：</th>
				<td>
					 <c:out value="${requestScope.att.name }" />
				</td>
			</tr>
			<tr>
				<th>第一次上班时间：</th>
				<td>
					<c:out value="${requestScope.att.firstup }"/>
				</td> 
				<th>第一次下班时间：</th>
				<td>					
					<c:out value="${requestScope.att.firstdown }"/>
				</td> 
			</tr>
			<tr>
				<th>第二次上班时间：</th>
				<td>
				<c:out value="${requestScope.att.secondup }"/>
				</td> 
				<th>第二次下班时间：</th>
				<td>					
					<c:out value="${requestScope.att.seconddown }"/>
				</td> 
			</tr>	
			<tr>
				<th>第三次上班时间：</th>
				<td>
					<c:out value="${requestScope.att.thirdup }"/>
				</td> 
				<th>第三次下班时间：</th>
				<td>					
					<c:out value="${requestScope.att.thirddown }"/>
				</td> 
			</tr>			
			 
			<tr>
				<th>上班打卡开始时间：</th>
				<td>
				   <c:out value="${requestScope.att.upstart }"/>&nbsp;分钟
				</td>
				<th rowspan="4" >工作日设置：</th>
				<td rowspan="4" >
				 <table> 
					 <tr>
					  <th>类型</th><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th>周六</th><th>周七</th>
					 </tr>
					 <tr><td>第一次上班</td>
					 <td><input type="checkbox" name="work1" value="1" <c:if test="${!empty requestScope.w11  }"> checked="checked"</c:if> ></td>
					 <td><input type="checkbox" name="work2" value="1" <c:if test="${!empty requestScope.w21  }"> checked="checked"</c:if> ></td> 
					 <td><input type="checkbox" name="work3" value="1" <c:if test="${!empty requestScope.w31  }"> checked="checked"</c:if> ></td>
					 <td><input type="checkbox" name="work4" value="1" <c:if test="${!empty requestScope.w41  }"> checked="checked"</c:if> ></td>
					 <td><input type="checkbox" name="work5" value="1" <c:if test="${!empty requestScope.w51  }"> checked="checked"</c:if> ></td>
					 <td><input type="checkbox" name="work6" value="1" <c:if test="${!empty requestScope.w61  }"> checked="checked"</c:if> ></td>
					 <td><input type="checkbox" name="work7" value="1" <c:if test="${!empty requestScope.w71  }"> checked="checked"</c:if> ></td>
					  </tr>
					  <tr><td>第二次上班</td>
					  <td><input type="checkbox" name="work1" value="2" <c:if test="${!empty requestScope.w12  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work2" value="2" <c:if test="${!empty requestScope.w22  }"> checked="checked"</c:if> ></td> 
					  <td><input type="checkbox" name="work3" value="2" <c:if test="${!empty requestScope.w32  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work4" value="2" <c:if test="${!empty requestScope.w42  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work5" value="2" <c:if test="${!empty requestScope.w52  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work6" value="2" <c:if test="${!empty requestScope.w62  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work7" value="2" <c:if test="${!empty requestScope.w72  }"> checked="checked"</c:if> ></td>
					  </tr>
					  <tr><td>第三次上班</td>
					  <td><input type="checkbox" name="work1" value="3" <c:if test="${!empty requestScope.w13  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work2" value="3" <c:if test="${!empty requestScope.w23  }"> checked="checked"</c:if> ></td> 
					  <td><input type="checkbox" name="work3" value="3" <c:if test="${!empty requestScope.w33  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work4" value="3" <c:if test="${!empty requestScope.w43  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work5" value="3" <c:if test="${!empty requestScope.w53  }"> checked="checked"</c:if> ></td> 
					  <td><input type="checkbox" name="work6" value="3" <c:if test="${!empty requestScope.w63  }"> checked="checked"</c:if> ></td>
					  <td><input type="checkbox" name="work7" value="3" <c:if test="${!empty requestScope.w73  }"> checked="checked"</c:if> ></td>
					  </tr>
					  <tr><td colspan="8">
					  <span class="label label-warning">说明：</span>
					   <span class="alert" >勾中着为工作时间,工作时间需要登记打卡。</span>
					  </td></tr>
					</table>
				
				
				</td>
			</tr>
			<tr> 
				<th>上班打卡结束时间：</th>
				<td>
				    <c:out value="${requestScope.att.upend }"/> &nbsp;分钟
				</td>
			</tr>
			<tr>
				<th>下班打卡开始时间：</th>
				<td>
				    <c:out value="${requestScope.att.downstart }"/> &nbsp;分钟
				</td>
			</tr>
			<tr>
				<th>下班打卡结束时间：</th>
				<td>
				    <c:out value="${requestScope.att.downend }"/>&nbsp;分钟
				</td>
			</tr> 
			<tr>
				<th>创建时间:</th>
				<td >
				&nbsp;<fmt:formatDate value="${requestScope.att.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />
					 
				</td>
				<th>创建人：</th>
				<td>
				    <c:out value="${requestScope.att.createuser }"/>
				</td>
			 </tr>
			
			
	</table>
</div>									
						
					
		

	

