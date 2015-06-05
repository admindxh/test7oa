/**  
 * @Project: jxoa
 * @Title: OneToOneServiceImpl.java
 * @Package com.oa.test.service.impl
 * @date 2013-5-15 上午10:27:42
 * @Copyright: 2013 
 */
package com.oa.test.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.test.bean.User;
import com.oa.test.service.ICascadeService;

/**
 * 
 * 类名：OneToOneServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-15 上午10:27:42
 *
 */
@Service
public class CascadeServiceImpl extends BaseServiceImpl implements ICascadeService{
	
	
	public void updateOne2One(){
		
		
		
		//User user=dao.get(User.class,"402881f73ea78aa0013ea78b509f0002");
		
		List<User> list =(List<User>)dao.find("select u from User u left join u.idcard d where d.address = ?","济南");
		
		
		//Hibernate.initialize(user.getIdcard());
		
		//Idcard c=user.getIdcard();
		
		System.out.println("cccc==="+list.get(0).getIdcard().getAddress());
		
		//user.setName("小王");
		//c.setAddress("南京");
		//user.setIdcard(c);
		
		//c.setUser(user);
		
		//service.update(user);
		
		//dao.update(user);
		//dao.delete(old);
		
	}
	
}
