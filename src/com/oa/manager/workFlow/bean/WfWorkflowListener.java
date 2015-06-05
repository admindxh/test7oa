package com.oa.manager.workFlow.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * WfWorkflowListener entity. @author MyEclipse Persistence Tools
 */

public class WfWorkflowListener implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 监听器名称
	 */
	@NotBlank(message="监听器名称不能为空")
	@Length(min=1,max=255,message="监听器名称不能超过255个字符。")
	private String name;
	/**
	 * 监听器类型，1:执行监听器,2:任务监听器
	 */
	@NotNull(message="监听器类型不能为空")
	private Short type;
	/**
	 * 监听器事件
	 */
	@NotBlank(message="监听器事件不能为空")
	private String event;
	/**
	 * 执行的类型，1:class;2:expression
	 */
	@NotNull(message="执行的类型不能为空")
	private Short valueType;
	/**
	 * 执行内容,类名称或表达式
	 */
	@NotBlank(message="执行内容不能为空")
	@Length(min=1,max=255,message="执行内容不能超过255个字符。")
	private String value;
	private String fieldsJson;
	private String desc;

	// Constructors

	/** default constructor */
	public WfWorkflowListener() {
	}

	/** full constructor */
	public WfWorkflowListener(String name, Short type, String event,
			Short valueType, String value, String fieldsJson, String desc) {
		this.name = name;
		this.type = type;
		this.event = event;
		this.valueType = valueType;
		this.value = value;
		this.fieldsJson = fieldsJson;
		this.desc = desc;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Short getValueType() {
		return this.valueType;
	}

	public void setValueType(Short valueType) {
		this.valueType = valueType;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldsJson() {
		return this.fieldsJson;
	}

	public void setFieldsJson(String fieldsJson) {
		this.fieldsJson = fieldsJson;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}