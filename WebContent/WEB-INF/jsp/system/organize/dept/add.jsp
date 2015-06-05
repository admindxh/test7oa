<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--部门管理 -- 新增部门
--%>



	<form  action="dept/add.do"   method="post"   onsubmit="return validateSubmitForm(this)">
		   
			<div  style="margin-left: 20px;margin-top: 10px">
				
				<c:choose>
					<c:when test="${param.superId==null}">
						<h4><p class="text-info">添加顶级部门</p></h2>
					</c:when>
					<c:otherwise>
						<h4><p class="text-info">添加部门：上级部门为：<span style="color: red">[ <my:outDeptName id="${param.superId }"/> ]</span></p></h4>
					</c:otherwise>
				</c:choose>
			</div>
		<div class="divider"></div>
		 <table class="table table-nobordered ">	
		   
		   <tr>
		    	<th style="width: 80px;">部门序号：</th>
		    	<td>
		     		<input type="text" name="deptSort" class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3"/>
		    		<span class="text-info">用于同级部门排序，值越小越靠前(数值范围：1-999 整数)</span>
		    	</td>
		   </tr>
		 
		    <tr>
				 <th>部门名称：</th>
				 <td>
					 <input type="text" name="deptName" class="easyui-validatebox"  required="true"   data-options="validType:['length[1,50]']"  maxlength="50" />
				 </td>
		   </tr>
		    <tr>
				<th>电话：</th>
				<td>
					<input type="text" name="deptPhone" maxlength="255" />
					
				</td>
			</tr>
		  	<tr>
				<th>传真：</th>
				<td>
					<input  type="text" name="deptFax"  maxlength="255"  class="easyui-validatebox" />
					
				</td>
			</tr>
			 <tr>
				<th>地址：</th>
				<td>
					<textarea  name="deptAddress"  rows="2" maxlength="100"></textarea>
				</td>
			</tr>
		
			<tr>
				<th>部门经理：</th>
				<td>
					<a  href="user/lookUpPage.do?type=1"  lookupGroup="user" title="用户查询">
						<input type="text"  readonly="readonly"  toName="user.trueName"  class="easyui-validatebox" required="true"/>
					</a>
					<input type="hidden" name="leadUid" toName="user.id"/>
					
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="user"  title="清空"></a>
				</td>
			</tr>
		   <tr>
				<th>上级部门：</th>
				<td>
					<a href="dept/lookUpPage.do?type=3" lookupGroup="dept"  title="上级部门查询">
						
						<c:choose>
							<c:when test="${empty param.superId }">
								<input  type="text" value="顶级部门"  readonly="readonly"  toName="dept.deptName"  class="easyui-validatebox" required="true"/>
							</c:when>
							<c:otherwise>
								<input  type="text" value="<my:outDeptName id="${param.superId }" />"  readonly="readonly"  toName="dept.deptName"  class="easyui-validatebox" required="true"/>
							</c:otherwise>
						</c:choose>
					
					</a>
					<input type="hidden" name="superId"  toName="dept.id"  value="<c:out value="${param.superId }" default="0"/>" />
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="dept"  title="清空"></a>
					<span  class="text-info">[无上级]，则为顶级部门</span>
					
				</td>
		   </tr>
		   <tr>
			  <th>备注：</th>
			  <td>
					<textarea  name="deptDesc"  rows="2" maxlength="200"></textarea>
			  </td>
		  </tr>
		  
		  <tr>
			 <th></th>
			 <td>
				<div  style="margin-top: 10px;margin-bottom: 10px;">
					  <button type="submit" class="btn btn-primary" >保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
					  <button type="button" class="btn clear" >清空</button>
				</div>
			 </td>
		  </tr>
	 </table>  
		  
	 <input type="hidden" name="currentCallback" value="refresh" />
	 <input type="hidden" name="callback_fn" value="queryLeftDepts"/>	
		  
 </form>
	




							
						
					
		

	

