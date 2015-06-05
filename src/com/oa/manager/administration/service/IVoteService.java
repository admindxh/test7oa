package com.oa.manager.administration.service;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.FList;
import com.oa.manager.administration.bean.FilList;
import com.oa.manager.administration.bean.LookMan;
import com.oa.manager.administration.bean.PerVote;
import com.oa.manager.administration.bean.Reult;
import com.oa.manager.administration.bean.TpQuestion;
import com.oa.manager.administration.bean.TpVote;
import com.oa.manager.administration.bean.TpVotedetail;


public interface IVoteService extends IBaseService{
	
	/**
	 * 查询投票
	 */
	public DataGrid voteload(PageParam param,TpVote vote);
	
	
	/**
	 * 增加投票
	 * @param vo
	 * @param deptid 
	 * @param userid
	 * @return
	 */
	public String addvote(TpVote vo,String[] deptid,String[] userid);
	
	 /**
	  * 查询投票对象
	  * @param id
	  * @return
	  */
	public Map findVoteById(String id);
	/**
	 * 更新投票
	 * @param vo
	 * @param deptIds
	 * @param userIds
	 * @return
	 */
	 public String updatevote(TpVote vo,String deptIds,String userIds);
	 
	/**
	 * 删除投票
	 * @param ids
	 * @return
	 */
	public boolean deletevote(String[] ids);
	/**
	 * 删除子投票
	 * @param id  子投票id
	 * @param voteid  投票id
	 * @return
	 */
	public String delztp(String id,String voteid);
	/**
	 * 修改投票状态为生效
	 * @param ids
	 * @return
	 */
	public String updatevalid(String [] ids);
	/**
	 * 修改投票状态为终止
	 * @param ids
	 * @return
	 */
	public String updatedtop(String [] ids);
	/**
	 * 发布投票
	 * @param id
	 * @return
	 */
	public String updatepropublish(String id);
	/**
	 * 加载子投票
	 * @param id  投票id
	 * @return
	 */
	public List<TpQuestion> loadquestion(String id);
	
	/**
	 * 查询子投票
	 * @param id
	 * @return
	 */
	public Map fingQuestionById(String id);
	/**
	 * 修改子投票
	 * @param tq
	 * @param f
	 * @return
	 */
	public String updateztp(TpQuestion tq,FList f);
	/**
	 * 增加子投票
	 * @param tq
	 * @param f
	 * @return
	 */
	public String addztp(TpQuestion tq,String [] f);
	/**
	 * 查询投票选项
	 * @param id  子投票id
	 * @return
	 */
	public List fingVotedetail(String id);
	/**
	 * 加载投票选项
	 * @param id   
	 * @return
	 */
	public List<TpVotedetail> loaddetails(String id);
    /**
     * 删除投票选项
     * @param id
     * @return
     */
	public String deletetpxm(String id);
	
	/**
	 * 加载个人投票
	 * @param param
	 * @param vote
	 * @return
	 */
	public DataGrid loadtp(PageParam param, TpVote vote);
	/**
	 * 查询个人投票总数
	 * @param vote
	 * @return
	 */
	//public Long counttp(TpVote vote);	
	/**
	 * 查询子投票
	 * @param id   投票id
	 * @return
	 */
	public List<TpQuestion> fingztpById(String id);
	/**
	 * 查询投票项目
	 * @param id   子投票id
	 * @return
	 */
	public List<TpVotedetail> findtpxmById(String id);
	/**
	 * 查询投票对象
	 * @param id
	 * @return
	 */
	public TpVote findTpVoteById(String id);
	/**
	 * 增加个人投票记录
	 * @param fil
	 * @param voteid
	 * @return
	 */
	public String addpertp(FilList fil,String voteid);
	/**
	 * 查询用户是否投过票
	 * @param id   投票id
	 * @param userid   
	 * @return
	 */
	public List queryTp(String id,String userid);
	/**
	 * 统计选项被投票的个数
	 * @param voteid  投票id 
	 * @param ztpid   子投票id
	 * @param detailid 选项id
	 * @return
	 */
	public int count(String voteid,String ztpid,String detailid);
	/**
	 * 统计投票发布范围    
	 * @param id
	 * @return
	 */
	public int countVote(String id);
	/**
	 * 文本类型子投票 集合
	 * @param voteid  投票id
	 * @param ztpid   子投票id
	 * @return
	 */
	public List<Object> countArea(String voteid,String ztpid);
	/**
	 * 查询投票人
	 * @param id  投票id
	 * @return
	 */
	public List<LookMan> lookman(String id,TpVote vote);
	/**
	 * 清空投票记录数据
	 * @param id  投票id
	 * @return
	 */
	public String delcleanDate(String id);
	
	/**
	 * 查询子投票和对应的选项    用于投票界面
	 * @param id  投票id
	 * @return
	 */
	public List<PerVote> selecttp(String id);
	/**
	 * 查看投票结果     用户查看  
	 * @param id
	 * @return
	 */
	public List<Reult> selectVotingResults(String id);
//*****************************************安卓****************************************************8	
	/**
	 * 根据投票id查询投票详情
	 * @param id 投票id
	 * @return
	 */
	public Map<String,Object> queryVoteById(String id);
}

