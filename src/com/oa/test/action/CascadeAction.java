/**  
 * @Project: jxoa
 * @Title: OneToOneAction.java
 * @Package com.oa.test.action
 * @date 2013-5-15 上午10:04:06
 * @Copyright: 2013 
 */
package com.oa.test.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.test.bean.Idcard;
import com.oa.test.bean.User;
import com.oa.test.service.ICascadeService;

/**
 * 
 * 类名：OneToOneAction
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-15 上午10:04:06
 *
 */
@Controller
@RequestMapping("/cascade")
public class CascadeAction extends BaseAction{
	
	@Autowired
	private ICascadeService service; 
	
	
	@RequestMapping("ont2one/save")
	public ModelAndView save(User user,Idcard c){
		
		user.setName("小黑");
		
		c.setAddress("济南");
		
		user.setIdcard(c);
		
		
		
		service.save(user);
	
		c.setUser(user);
		
		
		
		service.save(c);
		
		return ajaxDoneSuccess();
	
	}
	@RequestMapping("ont2one/update")
	
	public ModelAndView update(){
		
		service.updateOne2One();
		
		return ajaxDoneSuccess();
	
	}
	
}
