/**  
 * @Project: jxoa
 * @Title: FlowNode.java
 * @Package com.oa.manager.workFlow.model
 * @date 2013-7-5 上午11:29:40
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.entity;

/**
 * 
 * 类名：FlowNode
 * 功能：流程节点
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-5 上午11:29:40
 *
 */
public class FlowNode {
	
	private String name;
	
	private String x;
	
	private String y;
	
	private String width;
	
	private String height;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
}
