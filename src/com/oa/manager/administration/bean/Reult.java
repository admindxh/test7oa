package com.oa.manager.administration.bean;

import java.util.List;
/**
 * 类名：Reult
 * 功能：投票结果    用于结果展示界面	 
 * 作者：MaFanghua
 * 版本：1.0
 * 日期：2013-6-20 下午18:00:37
 */
public class Reult {
	
	private TpQuestion question;//子投票
	private List<Options> list;//选项和投票数对象集合
	
	private List<Object> str;//存放文本类型投票的集合
	/**
	 * 投票总数
	 */
	private int allCount;
	
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public List<Object> getStr() {
		return str;
	}
	public void setStr(List<Object> str) {
		this.str = str;
	}
	public TpQuestion getQuestion() {
		return question;
	}
	public void setQuestion(TpQuestion question) {
		this.question = question;
	}
	public List<Options> getList() {
		return list;
	}
	public void setList(List<Options> list) {
		this.list = list;
	}
	
	
}
