/**  
 * @Title SearchGroups.java
 * @date 2013-11-13 下午2:08:02
 * @Copyright: 2013 
 */
package com.oa.commons.model;

import java.util.List;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public class SearchGroups {
	/**
	 * 条件
	 */
	private List<SearchRules> rules;
	/**
	 * 分组
	 */
	private List<SearchGroups> groups;
	/**
	 * 连接此组的连接符
	 */
	private String op;

	public List<SearchRules> getRules() {
		return rules;
	}

	public void setRules(List<SearchRules> rules) {
		this.rules = rules;
	}

	public List<SearchGroups> getGroups() {
		return groups;
	}

	public void setGroups(List<SearchGroups> groups) {
		this.groups = groups;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	
}
