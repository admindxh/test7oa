/**  
 * @Title SearchRules.java
 * @date 2013-11-13 下午2:08:59
 * @Copyright: 2013 
 */
package com.oa.commons.model;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public class SearchRules {
	/**
	 * 字段名称
	 */
	private String field;
	/**
	 * 类型
	 * string  byte int	 shot	int	long	float	double	boolean	date  timestamp
	 */
	private String type;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 运算符 
	 * "	  equal": "相等",		 				sql: =
	 *       "notequal": "不相等"	  				sql: !=
	 *       "startwith": "以..开始", 				sql: like %like
	 *       "endwith": "以..结束",					sql: like value%
	 *       "like": "相似",							sql: like %value%
	 *       "greater": "大于",						sql: >
	 *       "greaterorequal": "大于或等于",			sql: >=
	 *       "less": "小于",							sql: <
	 *       "lessorequal": "小于或等于",			sql: <=
	 *       "in": "包括在...",						sql: in
	 *       "notin": "不包括..."      				sql: not in 
	 */
	private String op;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
}
