/**  
 * @Title Menu.java
 * @date 2013-12-13 上午11:56:47
 * @Copyright: 2013 
 */
package com.oa.commons.model;

import java.util.List;

/**
 * 系统菜单 对象
 * @author LiuJincheng
 * @version 1.0
 */
public class Menu implements java.io.Serializable{
	
	
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	/**
	 * 上级菜单id
	 */
	private String superId;
	
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单图片url
	 */
	private String icon;
	/**
	 * 菜单url
	 */
	private String url;
	/**
	 * 页面打开区域，dwz参数：navTab(在标签中),dialog(弹出窗口);_blank(在浏览器新窗口)
	 */
	private String target;
	/**
	 * dwz参数,rel为打开页面所在标签或弹出窗口的id，如果重复，将在同一标签打开
	 */
	private String rel;
	/**
	 * 默认是否打开，true或false
	 */
	private String open;
	/**
	 * dwz参数,为external="true"或者href是外网连接时，以iframe方式打开navTab页面
	 */
	private String external;
	/**
	 * dwz参数,表示重复打开navTab时是否重新加载数据
	 */
	private String fresh;
	/**
	 * 子菜单
	 */
	private List<Menu> childrenMenus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuperId() {
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getExternal() {
		return external;
	}
	public void setExternal(String external) {
		this.external = external;
	}
	public String getFresh() {
		return fresh;
	}
	public void setFresh(String fresh) {
		this.fresh = fresh;
	}
	public List<Menu> getChildrenMenus() {
		return childrenMenus;
	}
	public void setChildrenMenus(List<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}
	
}
