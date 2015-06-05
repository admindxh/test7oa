/**  
 * @Title: DataPermissionsServiceImpl.java
 * @date 2013-10-18 下午4:06:27
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.manager.system.bean.SyDataPermissions;
import com.oa.manager.system.service.IDataPermissionsService;

/**
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
@Service
public class DataPermissionsServiceImpl extends BaseServiceImpl implements IDataPermissionsService{

	
	public SyDataPermissions updateSelectOne(SyDataPermissions per){
		SyDataPermissions old=(SyDataPermissions)dao.findOne("from SyDataPermissions where type=? ",per.getType());
		if(old==null){
			dao.save(per);
			return per;
		}else{
			if(!per.getName().equals(old.getName())){
				old.setName(per.getName());
				dao.update(old);
			}
		}
		return old;
		
	}
	public String updateSyDataPermissions(SyDataPermissions per){
		Object obj=dao.findOne("from SyDataPermissions where id!=? and  type=? ",per.getId(),per.getType());
		if(obj==null){
			SyDataPermissions old=dao.get(SyDataPermissions.class, per.getId());
			old.setDesc(per.getDesc());
			old.setRules(per.getRules());
			
			saveLog("修改数据权限", "名称:"+old.getName()+",类型:"+old.getType());
				
			MyCache.getInstance().removeCache(MyCache.DATAPERMISSIONS, old.getType());
				
			return MsgConfig.MSG_KEY_SUCCESS;
			
		}else{
			return "msg.dataPermissions.unique";//已有此数据权限
		}
	}
	
	
	
	public String selectRules(String type){
		
		SyDataPermissions dp=(SyDataPermissions)dao.findOne("from SyDataPermissions where type=?",type);
		if(dp==null){
			return null;
		}else{
			return dp.getRules();
		}
	}
	
}
