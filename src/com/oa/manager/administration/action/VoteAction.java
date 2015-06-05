package com.oa.manager.administration.action;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.bean.FList;
import com.oa.manager.administration.bean.FilList;
import com.oa.manager.administration.bean.LookMan;
import com.oa.manager.administration.bean.PerVote;
import com.oa.manager.administration.bean.Reult;
import com.oa.manager.administration.bean.TpQuestion;
import com.oa.manager.administration.bean.TpVote;
import com.oa.manager.administration.service.IVoteService;


/**
* 类名：VoteAction
* 功能：公共平台--投票管理
* 详细：
* 作者：MaFanghua
* 版本：1.0
* 日期：2013-6-7 上午10:20:28
*/
@Controller
@RequestMapping("vote")
public class VoteAction extends BaseAction{
			
	@Autowired
	private IVoteService voteservice;
	
	/**
	 * 行政办公-----跳转到投票管理页面
	 * @return
	 */
	@RequiresPermissions("vote:read")
	@RequestMapping("load")
	public String load(){
		
		return "administration/vote/tp/load";
	}
	/**
	 * 行政办公----分页查询投票
	 * @param param
	 * @param vote
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:read")
	@RequestMapping("loadvote")
	public ModelAndView loadvote( PageParam param,TpVote vote){
		return ajaxJsonEscape(voteservice.voteload(param, vote));
	}
	/**
	 * 删除投票
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("vote:delete")
	@RequestMapping("delete")
	public ModelAndView delete(String [] ids){
		
		return ajaxDone(voteservice.deletevote(ids));
		
	}
	/**
	 * 增加投票     跳转增加界面
	 * @return
	 */
	@RequiresPermissions("vote:add")
	@RequestMapping("preaddPage")
	public String preaddpage(){
		
		return "administration/vote/tp/addVote";
	}
	/**
	 * 增加投票
	 * @param vo
	 * @param errors
	 * @param deptid  
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("vote:add")
	@RequestMapping("addPage")
	public ModelAndView addPage(@Valid TpVote vo, Errors errors,String[] deptIds,String[] userIds){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		vo.setTpCreatetime(DateUtil.currentTimestamp());//创建日期
		Member me = ServletUtil.getMember();//获取当前登陆人id
		
		vo.setUserid(me.getId());//发布人
		if(vo.getTpAnonymous()==null){//匿名投票
			vo.setTpAnonymous((short)0);
		}
		if(vo.getTpStick()==null){//置顶
			vo.setTpStick((short)0);
		}
		vo.setStuts((short)0);//状态  默认未发布
		

		return ajaxDone(voteservice.addvote(vo, deptIds,userIds));
	}
	/**
	 * 修改投票     跳转到修改界面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		
		map.addAllAttributes(voteservice.findVoteById(id));
		if(map.get("n")==null){
			return NODATA;
		}
		return "administration/vote/tp/updateVote";
		
	}
	/**
	 * 更新投票
	 * @param vo
	 * @param errors
	 * @param deptid
	 * @param userid
	 * @return
	 */
	@RequiresPermissions("vote:update")
	@RequestMapping("updatevote")
	public ModelAndView updatevote(@Valid TpVote vo,Errors errors,String deptid,String userid){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(voteservice.updatevote(vo,deptid,userid));
	}
	/**
	 * 发布投票
	 * @param id
	 * @return
	 */
	@RequiresPermissions("vote:publish")
	@RequestMapping("publish")
	public ModelAndView publish(String id){
		
		return ajaxDone(voteservice.updatepropublish(id));
		
	}
	/**
	 * 修改投票状态为生效
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("vote:valid")
	@RequestMapping("valid")
	public ModelAndView valid(String [] ids){
		
		return ajaxDone(voteservice.updatevalid(ids));
	}
	/**
	 * 修改投票状态为终止
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("vote:stop")
	@RequestMapping("stop")
	public ModelAndView stop(String [] ids){
		
		return ajaxDone(voteservice.updatedtop(ids));
	}
	/**
	 * 加载子投票
	 * @param id  投票id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:read")
	@RequestMapping("loadQuestion")
	public String loadQuestion(String id,ModelMap map){
		TpVote vo=voteservice.findTpVoteById(id);
		if(vo==null){
			return "administration/vote/tp/nodata";
		}
		map.addAttribute("voteid",id);
		map.addAttribute("questions",voteservice.loadquestion(id));
		return "administration/vote/ztp/showquestion";
	}
	/**
	 * 增加子投票  跳转到增加界面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:add")
	@RequestMapping("preaddquestion")
	public String preaddquestion(String id,ModelMap map){
		
		map.addAttribute("voteid",id);
		return "administration/vote/ztp/addquestion";
	}
	/**
	 * 增加子投票
	 * @param mn
	 * @param errors
	 * @param f   投票选项
	 * @return
	 */
	@RequiresPermissions("vote:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid TpQuestion mn, Errors errors,String[] f){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		
		return ajaxDone(voteservice.addztp(mn, f));
		
	}
	/**
	 * 删除子投票
	 * @param id
	 * @param voteid
	 * @return
	 */
	@RequiresPermissions("vote:delete")
	@RequestMapping("delztp")
	public ModelAndView delztp(String id,String voteid){
		
		return ajaxDone(voteservice.delztp(id,voteid));
		
	}
	/**
	 * 修改子投票    跳转到修改界面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:update")
	@RequestMapping("preudpateztp")
	public String preudpateztp(String id,ModelMap map){
		
		map.addAllAttributes(voteservice.fingQuestionById(id));
		map.addAttribute("list",voteservice.fingVotedetail(id));
		if(map.get("n")==null){
			return NODATA;
		}
		return "administration/vote/ztp/update";
		
	}
	/**
	 * 更新子投票
	 * @param tq
	 * @param errors
	 * @param f  投票选项
	 * @return
	 */
	@RequiresPermissions("vote:update")
	@RequestMapping("updatztp")
	public ModelAndView updateztp(@Valid TpQuestion tq,Errors errors, FList f){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(voteservice.updateztp(tq,f));
	}
	/**
	 * 删除投票选项
	 * @param id
	 * @return
	 */
	@RequiresPermissions("vote:delete")
	@RequestMapping("deltpxm")
	public ModelAndView deltpxm(String id){
	
		return ajaxDone(voteservice.deletetpxm(id));
		
	}
	/**
	 * 个人办公-----跳转到个人投票界面
	 * @param param
	 * @param vote
	 * @param map
	 * @return
	 */
	@RequiresPermissions("pervote:read")
	@RequestMapping("pervote")
	public String pervote(){

		return "administration/vote/pervote/load";
		
	}
	/**
	 * 个人办公----分页查询个人投票
	 * @param param
	 * @param vote
	 * @return
	 */
	@RequiresPermissions("pervote:read")
	@RequestMapping("pervoteload")
	public ModelAndView pervoteload(PageParam param,TpVote vote){
		
		return ajaxJsonEscape(voteservice.loadtp(param, vote));
	}
	/**
	 * 查询子投票和选项  用于跳转投票界面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("pervote:tp")
	@RequestMapping("tp")
	public String  tp(String id,ModelMap map){
			//首先判断该投票是否存在
			TpVote vo=voteservice.findTpVoteById(id);
			if(vo==null){
				return NODATA;
			}
			//再判断该投票是否生效
			if("2".equals(vo.getStuts())){
				return "vote.stuts.stop";//投票已终止不能投票
			}
			List<PerVote> bl=voteservice.selecttp(id);
			map.addAttribute("bl",bl);//题目和选项集合
			map.addAttribute("vo",vo);//投票对象
		return "administration/vote/pervote/pertp" ;
	}
	/**
	 * 增加投票记录
	 * @param fil  投票记录集合
	 * @param errors
	 * @param voteid
	 * @return
	 */
	@RequiresPermissions("pervote:tp")
	@RequestMapping("addpertp")
	public  ModelAndView addpertp(FilList fil,Errors errors,String voteid){
	
		return  ajaxDone(voteservice.addpertp(fil, voteid));
		 
		
	}
	/**
	 * 查看投票结果       用于用户查看
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("pervote:show")
	@RequestMapping("lookresult")
	public String lookresult(String id,ModelMap map){
		TpVote vo=voteservice.findTpVoteById(id);
		if(vo==null){
			return NODATA;
		}
		
		//判断是否投票      用于投票后可看
		Member me = ServletUtil.getMember();//获取当前登陆用户id
		List list1=voteservice.queryTp(id,me.getId());
		if(vo.getTpSeeresult()==0&&list1.size()<=0){
			return "administration/vote/pervote/voteafter";
		}
		List<Reult> bl=voteservice.selectVotingResults(id);//用于展示投票结果界面
		
		map.addAttribute("vo",vo);
		map.addAttribute("bl",bl);
	  
		return "administration/vote/pervote/voting_results";
	}
	/**
	 * 查看投票结果     用于管理员查看
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:show")
	@RequestMapping("seeresult")
	public String seeresult(String id,ModelMap map){
		
		TpVote vo=voteservice.findTpVoteById(id);
		if(vo==null){
			return NODATA;
		}
		List<Reult> bl=voteservice.selectVotingResults(id);
		map.addAttribute("vo",vo);
		map.addAttribute("bl",bl);
		return "administration/vote/pervote/voting_results";
	}
	/**
	 * 查看投票人
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("vote:showman")
	@RequestMapping("findman")
	public String findman(String id,ModelMap map){
		TpVote vo=voteservice.findTpVoteById(id);
		if(vo==null){
			return NODATA;
		}
		List<LookMan> list=voteservice.lookman(id,vo);
		map.addAttribute("list",list);
		return "administration/vote/tp/lookman";
	}
	/**
	 * 清空投票结果
	 * @param id
	 * @return
	 */
	@RequiresPermissions("vote:clean")
	@RequestMapping("deldata")
	public ModelAndView deldata(String id){
		
		return ajaxDone(voteservice.delcleanDate(id));
	}
	
}
