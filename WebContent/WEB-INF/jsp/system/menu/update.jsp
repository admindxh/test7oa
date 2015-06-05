<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp"%>
<%--
	模块：系统管理--菜单管理  菜单更新
--%>

					
<form method="post" action="menu/update.do"  onsubmit="return validateSubmitForm(this)">
						

		<my:isDeveloper>
			 <div  style="margin-left: 10px;margin-top: 10px;margin-bottom: 5px;">
				
					<a class="btn btn-primary" href="menu/addPage.do?rel=${param.rel}" rel="${param.rel }_box" target="ajax" ><i class="boot_icon-plus-sign boot_icon-white"></i> 添加顶级菜单</a>
					
					<c:url var="url_addxjmenu" value="menu/addPage.do" >
						<c:param name="rel" value="${param.rel}" />
						<c:param name="superId" value="${requestScope.m.id }" />
						<c:param name="superName" value="${requestScope.m.menuName }" />
					</c:url>
					<a class="btn btn-primary" href="${url_addxjmenu }" rel="${param.rel }_box" target="ajax" ><i class="boot_icon-plus-sign boot_icon-white"></i> 添加下级菜单</a>
				
					<c:url var="url_delxjmenu" value="menu/del.do" >
						<c:param name="id" value="${requestScope.m.id }" />
						<c:param name="otherBoxId" value="${param.rel}_box" />
						<c:param name="otherCallback" value="menu/addPage.do?rel=${param.rel }" />
						<c:param name="callback_fn" value="queryLeftMenus" />
					</c:url>
					<a class="btn btn-danger" target="ajaxTodo"  href="${url_delxjmenu }" title="您确定删除菜单<font color='red'>[${requestScope.m.menuName }]</font>吗？如果此菜单有下级菜单，将无法删除！"><i class="boot_icon-remove-sign boot_icon-white"></i>删除</a>
				
			</div>
			<div class="divider"></div>
		</my:isDeveloper>
		<table class="table table-nobordered ">
			<tr>
				<th>菜单序号：</th>
				<td>
					<input type="text" name="menuSort"  value="${requestScope.m.menuSort }" class="easyui-numberbox" required="true"  min="1" max="999" maxlength="3" />
					<span class="text-info">用于同级菜单排序，值越小越靠前(数值范围：1-999 整数)</span>
				</td>
			</tr>
			<tr>
				<th>菜单名称：</th>
				<td>
					<input type="text" name="menuName"  value="<c:out value="${requestScope.m.menuName}"/>" class="easyui-validatebox"  required="true"   data-options="validType:['length[1,20]']" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>菜单图片：</th>
				<td>
					<a href="menu/icons.do" lookupGroup="icon" title="菜单图标查询">
						<input type="text" maxlength="100" name="menuIcon" readonly="readonly"  toName="icon.url"  value="<c:out value="${requestScope.m.menuIcon}"/>"/>
					</a>
					<a class="easyui-linkbutton clearLookup"  icon="icon-cancel"	plain="true"  href="javascript:;"  clearLookup="icon"  title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>上级菜单：</th>
				<td>	
					<a href="menu/lookUpPage.do" lookupGroup="menu" title="上级菜单查询" width="500">
						<input type="text" readonly="readonly" class="easyui-validatebox"  required="true"  value="<c:out value="${requestScope.superName}"/>"  toName="menu.name" />
					</a>
					<input type="hidden" name="menuSuperId"  toName="menu.id"  value="${requestScope.m.menuSuperId }"/>
				</td>
			</tr>
			<tr>
				<th>默认展示：</th>
				<td>
					<select  name="menuOpen" sValue="<c:out value="${requestScope.m.menuOpen}"/>">
							<option value="false">合并</option>
							<option value="true">展开</option>
					</select>
					<span class="text-info">用于树状列表，默认是否展开，显示子菜单</span>
				</td>
			</tr>
			<%--以下为开发人员可以设置的内容--%>
			<my:isDeveloper>
				<tr>
					<th>url：</th>
					<td>
						<input  type="text" name="menuUrl"   maxlength="255" style="width: 90%" value="<c:out value="${requestScope.m.menuUrl}"/>"/>
						
					</td>
				</tr>
				<tr>
					<th>target：</th>
					<td>
						<input type="text" name="menuTarget"  maxlength="10"  vaule="navTab" value="<c:out value="${requestScope.m.menuTarget}"/>"/>
						<span class="text-info">页面打开区域，dwz参数：navTab(在标签中),dialog(弹出窗口);_blank(在浏览器新窗口)</span>
					</td>
				</tr>
				
				<tr>
					<th>rel：</th>
					<td>
						<input type="text" name="menuRel" maxlength="20"  value="<c:out value="${requestScope.m.menuRel}"/>"/>
						<span class="text-info">dwz参数,rel为打开页面所在标签或弹出窗口的id，如果重复，将在同一标签打开</span>
					</td>
				</tr>
				<tr>
					<th>external：</th>
					<td>
						<select  name="menuExternal" sValue="<c:out value="${requestScope.m.menuExternal}"/>">
								<option value="false" >false</option>
								<option value="true">true</option>
						</select>
						<span class="text-info">dwz参数,为external="true"或者href是外网连接时，以iframe方式打开navTab页面</span>
					</td>
				</tr>
				<tr>
					<th>fresh：</th>
					<td>
						<select   name="menuFresh" sValue="<c:out value="${requestScope.m.menuFresh}"/>">
								<option value="false">false</option>
								<option value="true">true</option>
								
						</select>
						<span class="text-info">dwz参数,表示重复打开navTab时是否重新加载数据</span>
					</td>
				</tr>
				<tr>
				<th>是否可用：</th>
				<td>
					<select   name="menuStatus" sValue="<c:out value="${requestScope.m.menuStatus}"/>">
							<option value="1">可用</option>
							<option value="0">禁用</option>
					</select>
				</td>
			</tr>
			
				<tr>
				  <th></th>
				  <td>
					 <div  style="margin-top: 10px;margin-bottom: 10px;">
						  <button type="submit" class="btn btn-primary" >修改</button>&nbsp;&nbsp;&nbsp;&nbsp;
						  <button type="button" class="btn clear" >清空</button>
						
					 </div>
				  </td>
			    </tr>
			</my:isDeveloper>
			
			
			
		</table>

	 
	 <input type="hidden" name="id" value="${requestScope.m.id}"/>
	 <input type="hidden" name="currentCallback" value="menu/addPage.do?rel=<%= request.getParameter("rel") %>"/>
	 <input type="hidden" name="callback_fn" value="queryLeftMenus"/>
</form>
										
						
					
		

	

