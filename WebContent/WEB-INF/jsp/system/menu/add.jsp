<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--菜单管理 -- 添加菜单
--%>

					
<form method="post" action="menu/add.do"   onsubmit="return validateSubmitForm(this)">
						
		<my:isDeveloper>
			<div style="margin-left: 20px;margin-top: 10px">
				<c:choose>
					<c:when test="${param.superId==null}">
						<h4><p class="text-info">添加顶级菜单</p></h4>
					</c:when>
					<c:otherwise>
						<h4><p class="text-info">添加菜单：上级菜单为：<span style="color: red" >[ <c:out  value="${param.superName }" /> ]</span></p></h4>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="divider"></div>
		</my:isDeveloper>
		
		<table class="table table-nobordered ">	
			<tr>
				<th style="width: 80px;">菜单序号：</th>
				<td>
					<input type="text" name="menuSort" class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3" />
					<span class="text-info">用于同级菜单排序，值越小越靠前(数值范围：1-999 整数)</span>
				</td>
			</tr>
			<tr>
				<th>菜单名称：</th>
				<td>
					<input type="text" name="menuName" class="easyui-validatebox"  required="true"   data-options="validType:['length[1,20]']" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>菜单图片：</th>
				<td>
					<a href="menu/icons.do" lookupGroup="icon" title="菜单图标查询">
						<input type="text" maxlength="100" name="menuIcon" readonly="readonly"  style="width: 300px" toName="icon.url"  value="resource/images/menu/001.png"/>
					</a>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="icon"  title="清空"></a>
					
				</td>
			</tr>
			<tr>
				<th>上级菜单：</th>
				<td>
					<a href="menu/lookUpPage.do" lookupGroup="menu" title="上级菜单查询" width="400">
						<input value="<c:out value="${param.superName }" default=""/>" type="text" readonly="readonly" class="easyui-validatebox"  required="true" toName="menu.name" />
					</a>
					
					<input type="hidden" name="menuSuperId"  toName="menu.id"  value="${param.superId }" />
				</td>
			</tr>
			<tr>
				<th>默认展示：</th>
				<td>
					<select  name="menuOpen" >
							<option value="false">合并</option>
							<option value="true">展开</option>
					</select>
					<span class="text-info">用于树状列表，默认是否展开，显示子菜单</span>
				</td>
			</tr>
			
			<tr>
				<th>url：</th>
				<td>
					<input  type="text" name="menuUrl"   maxlength="255" />
					
				</td>
			</tr>
			<tr>
				<th>target：</th>
				<td>
					<input type="text" name="menuTarget"  maxlength="10"  value="navTab"/>
					<span class="text-info">页面打开区域，dwz参数：navTab(在标签中),dialog(弹出窗口);_blank(在浏览器新窗口)</span>
				</td>
			</tr>
			
			<tr>
				<th>rel：</th>
				<td>
					<input type="text" name="menuRel"  maxlength="20"/>
					<span class="text-info">dwz参数,rel为打开页面所在标签或弹出窗口的id，如果重复，将在同一标签打开</span>
				</td>
			</tr>
			<tr>
				<th>external：</th>
				<td>
					<select   name="menuExternal" >
							<option value="false" >false</option>
							<option value="true">true</option>
					</select>
					<span class="text-info">dwz参数,为external="true"或者href是外网连接时，以iframe方式打开navTab页面</span>
				</td>
			</tr>
			<tr>
				<th>fresh：</th>
				<td>
					<select   name="menuFresh" >
							<option value="false">false</option>
							<option value="true">true</option>
							
					</select>
					<span class="text-info">dwz参数,表示重复打开navTab时是否重新加载数据</span>
				</td>
			</tr>
			<tr>
				<th>是否可用：</th>
				<td>
					<select  name="menuStatus" >
							<option value="1">可用</option>
							<option value="0">禁用</option>
					</select>
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
		
		<input type="hidden" name="currentCallback" value="menu/addPage.do?rel=<%= request.getParameter("rel") %>"/>
		
		<input type="hidden" name="callback_fn" value="queryLeftMenus"/>
</form>
			
						
						
					
		

	

