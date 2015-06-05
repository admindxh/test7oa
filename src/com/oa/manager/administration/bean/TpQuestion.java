package com.oa.manager.administration.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * TpQuestion entity. @author MyEclipse Persistence Tools
 */

public class TpQuestion implements java.io.Serializable {

	// Fields

	private String id;
	@NotEmpty
	@Length(min=1,max=50,message="标题长度在1-50之间。")
	private String title;//题目
	private String voteid;//投票id
	@NotNull
	@Min(1) //被注释的元素必须是一个数字，其值必须大于等于指定的最小值
	private Integer seqnum;//排序号
	private Short type;//题目类型  0 单选 1 多选 2 文本

	// Constructors

	/** default constructor */
	public TpQuestion() {
	}

	/** full constructor */
	public TpQuestion(String title, String voteid, Integer seqnum, Short type) {
		this.title = title;
		this.voteid = voteid;
		this.seqnum = seqnum;
		this.type = type;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVoteid() {
		return this.voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public Integer getSeqnum() {
		return this.seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}