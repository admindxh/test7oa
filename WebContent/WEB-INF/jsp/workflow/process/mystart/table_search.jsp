<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--工作查询条件 --%>

	<span>
		<label>状态：</label>
		<select name="workStatus" style="width: 90px" submitForm="change">
			  <option value="">全部</option>
			  <option value="0">未完成</option>
			  <option value="1">已完成</option>
	   </select>	
		
	</span>
	<span>
		<label>工作标题：</label>
		<input type="text" name="workTitle" />
		&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
	</span>
	<span>
		<label>表单检索：</label>
		<input type="text" name="formHtml" />
		&nbsp;<i class="boot_icon-question-sign" selectLike="tooltip"></i>
	</span>

	<span>
		<label>创建时间：</label>
			<input type="text"  readonly="readonly"  onFocus="WdatePicker()" name="startedAfter" class="span2"/>
			--
			<input type="text"  readonly="readonly"  onFocus="WdatePicker()" name="startedBefore" class="span2"/>
	</span>
	
	<span>
		<label>结束时间：</label>
			<input type="text"  readonly="readonly"  onFocus="WdatePicker()" name="finishedAfter" class="span2"/>
			--
			<input type="text"  readonly="readonly"  onFocus="WdatePicker()" name="finishedBefore" class="span2"/>
	</span>


