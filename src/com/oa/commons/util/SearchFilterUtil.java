/**  
 * @Title: SearchFilterUtil.java
 * @date 2013-10-18 上午11:27:12
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oa.commons.cache.MyCache;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.PageParam;

/**
 * 根据查询规则拼装查询语句 工具类
 * 
 * json格式规则语句
 * 
 * {"groups"://一个查询分组
 * 	 		{
 * 			"op":"or"，//连接此组的连接符
 * 			"rules":[{//条件
 * 				"field":"loginIp",//字段名称
 * 				"op":"equal",//运算符 需要替换成 = != like 等
 * 				"value":"东",//值
 * 				"type":"string"//值的类型
 * 				}],
 * 			"groups":[]//组内再进行分组，每个组属性和这个分组一样，运算符，条件，内部分组
 * 			
 * 			}}
 * 
 * 最后拼接结果:
 * 原Hql and (系统规则查询分组) and (用户条件过滤规则组)
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public class SearchFilterUtil {
	
	/**
	 * 拼接查询规则hql语句, 
	 * @param hql	需要拼接的hql语句
	 * @param map	存放赋值参数的Map 
	 * @param searchRules 用户高级查询的搜索查询规则	
	 * @param type	系统查询规则 类型编码 如果不需要 传入 null
	 */
	@SuppressWarnings("rawtypes")
	public static void appendRules(StringBuffer hql,Map map,PageParam param,String type){
		JSONArray groups=new JSONArray();
		//1.添加系统查询规则设置分组
		if(type!=null){
			String rules=MyCache.getInstance().getDataPermissions(type);
			if(rules==null){
				new MyRuntimeException("查询规则不存在,类型编码"+type);
			}else{
				JSONObject j=JSON.parseObject(rules);
				if(j!=null){
					j.put("op","and");//此分组用and连接 hql 之前的条件
					groups.add(j);
				}
			}
		}
		//2.添加 高级查询规则
		if(StringUtils.isNotBlank(param.getSearchRules())){
			JSONObject j=JSON.parseObject(param.getSearchRules());
			if(j!=null){
				j.put("op","and");//用户查询必须启用and连接
				groups.add(j);
			}
		}
		if(!groups.isEmpty()){
			appendGroup(groups, hql, map,true);
			System.out.println("***拼接之后的HQL***"+hql);
		}
	}
	//递归组装hql
	/**{"groups":[{"op":"and"}]}  and ()
	 * 拼装hql语句
	 * @param groups	查询规则同一级别分组集合
	 * @param hql		需要拼接的hql语句
	 * @param map		拼接语句是对应的参数，采用Map形式，hql :别名
	 * @param hasRules	分组集合条件前面是否有条件语句，如果有，第一个分组条件则需要添加关联语句 and / or
	 */
	@SuppressWarnings("rawtypes")
	private static void appendGroup(JSONArray groups,StringBuffer hql,Map map,boolean hasRules){
		
		for(int i=0,k=groups.size();i<k;i++){
		
			JSONObject group=groups.getJSONObject(i);
			//0.如果没有条件也没有分组的话 继续运行后面的分组
			JSONArray rules=group.getJSONArray("rules");
			JSONArray subGroups=group.getJSONArray("groups");
			if((rules==null||rules.isEmpty())&&(subGroups==null||subGroups.isEmpty()))continue;
			//1.拼接and or 连接符
			if(i!=0||hasRules){//第一个分组需要判断前面是否需要添加连接符
				String op=group.getString("op");//获取连接符号
				if(StringUtils.isNotBlank(op)){
					hql.append(" "+op+" ");
				}else{
					hql.append(" and ");//默认and 
				}
			}
			hql.append("(");
			//2.拼接条件
			List<Integer> addRules=new ArrayList<Integer>();
			if(rules!=null&&!rules.isEmpty()){
				
				for(int j=0,l=rules.size();j<l;j++){
					//组装条件查询语句
					if(j!=0)hql.append(" and ");
					JSONObject rule=rules.getJSONObject(j);
					if(appendRule(hql,rule,map)){
						addRules.add(1);
					}
					
				}
			}
			//3.拼接分组 递归
			if(subGroups!=null&&!subGroups.isEmpty()){
				if(addRules.isEmpty()){
					appendGroup(subGroups, hql, map,false);
				}else{
					appendGroup(subGroups, hql, map,true);
				}
			}
			//4.结束
			hql.append(") ");
			
		}
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean appendRule(StringBuffer hql,JSONObject rule,Map map){
		String value=StringUtil.trim(rule.getString("value"));//值	
		if(StringUtils.isBlank(value))return false;	
		String field=rule.getString("field");//字段名称
		String op=rule.getString("op");//操作符
		String type=rule.getString("type");//值类型
		
		if(field.startsWith("{")){
			//特殊类型处理 当前用户id，部门，所属角色
			if("{loginUserId}".equals(field)){
				String userId=ServletUtil.getMember().getId();
				boolean has=false;
				for(String v:value.split(",")){
					if(userId.equals(v)){
						has=true;
						break;
					}
				}
				if("equal".equals(op)||"in".equals(op)){
					if(has){
						hql.append(" 1=1 ");
					}else{
						hql.append(" 1=0 ");
					}
				}else if("notequal".equals(op)||"notin".equals(op)){
					if(has){
						hql.append(" 1=0 ");
					}else{
						hql.append(" 1=1 ");
					}
				}
			}else if("{loginDeptId}".equals(field)){
				String deptId=ServletUtil.getMember().getDeptId();
				boolean has=false;
				for(String v:value.split(",")){
					if(deptId.equals(v)){
						has=true;
						break;
					}
				}
				if("equal".equals(op)||"in".equals(op)){
					if(has){
						hql.append(" 1=1 ");
					}else{
						hql.append(" 1=0 ");
					}
				}else if("notequal".equals(op)||"notin".equals(op)){
					if(has){
						hql.append(" 1=0 ");
					}else{
						hql.append(" 1=1 ");
					}
				}
				
				
			}else if("{loginRoleId}".equals(field)){
				List<String> roleIds= ServletUtil.getCurrentUserRoleIds();
				boolean has=false;
				for(String v:value.split(",")){
					if(roleIds.contains(v)){
						has=true;
						break;
					}
				}
				if("equal".equals(op)||"in".equals(op)){
					if(has){
						hql.append(" 1=1 ");
					}else{
						hql.append(" 1=0 ");
					}
				}else if("notequal".equals(op)||"notin".equals(op)){
					if(has){
						hql.append(" 1=0 ");
					}else{
						hql.append(" 1=1 ");
					}
				}
			}
			
		}else{
			//属于表的字段
			String key=field.replaceAll("\\.", "")+map.size();//去除. 添加数 防止重复
			if("equal".equals(op)){
				hql.append(" "+field+" = "+":"+key);
				map.put(key,getValue(value,type));
			}else if("notequal".equals(op)){
				hql.append(" "+field+" != "+":"+key);
				map.put(key,getValue(value,type));
			}else if("startwith".equals(op)){
				hql.append(" "+field+" like :"+key);
				map.put(key,getValue(value,type)+"%");
			}else if("endwith".equals(op)){
				hql.append(" "+field+" like "+":"+key);
				map.put(key,"%"+getValue(value,type));
			}else if("like".equals(op)){
				hql.append(" "+field+" like "+":"+key);
				map.put(key,"%"+getValue(value,type)+"%");
			}else if("greater".equals(op)){
				hql.append(" "+field+" > "+":"+key);
				map.put(key,getValue(value,type));
			}else if("greaterorequal".equals(op)){
				hql.append(" "+field+" >= "+":"+key);
				map.put(key,getValue(value,type));
			}else if("less".equals(op)){
				hql.append(" "+field+" < "+":"+key);
				map.put(key,getValue(value,type));
			}else if("lessorequal".equals(op)){
				hql.append(" "+field+" <= "+":"+key);
				map.put(key,getValue(value,type));
			}else if("in".equals(op)){
				List list=null;
				if("roleId".equals(type)&&"{loginRoleId}".equals(value)){//角色id特殊处理,
					list=ServletUtil.getCurrentUserRoleIds();
				}else{
					list=new ArrayList();
					for(String v:value.split(",")){
						list.add(getValue(v,type));
					}
				}
				hql.append(" "+field+" in ("+":"+key+")");
				map.put(key,list);
				
			}else if("notin".equals(op)){
				List list=null;
				if("roleId".equals(type)&&"{loginRoleId}".equals(value)){//角色id特殊处理,
					list=ServletUtil.getCurrentUserRoleIds();
				}else{
					list=new ArrayList();
					for(String v:value.split(",")){
						list.add(getValue(v,type));
					}
				}
				hql.append(" "+field+" not in ("+":"+key+")");
				map.put(key,list);
			}
		}
		return true;
	}
	
	private static Object getValue(String value,String type){
		if(StringUtils.isBlank(type))return value;//类型为空时默认string类型
		if("string".equals(type)){
			return value;
		}
		else if("userId".equals(type)||"deptId".equals(type)){
			//特殊类型处理 
			if("{loginUserId}".equals(value)){
				return ServletUtil.getMember().getId();
			}else if("{loginDeptId}".equals(value)){
				return ServletUtil.getMember().getDeptId();
			}else{
				return value;
			}
		}else if("date".equals(type)){
			return DateUtil.string2Date(value);
		}else if("timestamp".equals(type)){
			return DateUtil.string2Timestamp(value);
		}
		else if("int".equals(type)){
			return Integer.parseInt(value);
		}else if("short".equals(type)){
			return Short.parseShort(value);
		}else if("float".equals(type)){
			return Float.parseFloat(value);
		}else if("double".equals(type)){
			return Double.parseDouble(value);
		}else if("byte".equals(type)){
			return Byte.parseByte(value);
		}else if("long".equals(type)){
			return Long.parseLong(value);
		}else if("boolean".equals(type)){
			return Boolean.parseBoolean(value);
		}
		return value;
	}

	
}
