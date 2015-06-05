package com.oa.manager.administration.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.bean.FList;
import com.oa.manager.administration.bean.FilList;
import com.oa.manager.administration.bean.LookMan;
import com.oa.manager.administration.bean.Options;
import com.oa.manager.administration.bean.PerVote;
import com.oa.manager.administration.bean.Reult;
import com.oa.manager.administration.bean.TpCorrelation;
import com.oa.manager.administration.bean.TpCount;
import com.oa.manager.administration.bean.TpQuestion;
import com.oa.manager.administration.bean.TpVote;
import com.oa.manager.administration.bean.TpVotedetail;
import com.oa.manager.administration.service.IVoteService;
import com.oa.manager.common_platform.bean.Mapping;
import com.oa.manager.system.bean.SyUsers;
@Service
public class VoteServiceImpl extends BaseServiceImpl implements IVoteService{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid voteload(PageParam param, TpVote vote) {
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from TpVote u where 1=1 ");
		List list=new ArrayList();
		
		if(StringUtils.isNotBlank(vote.getTpTitle())){
			sb.append(" and u.tpTitle like ?");
			list.add("%"+vote.getTpTitle()+"%");
		}
		
		if(vote.getStuts()!=null&&!vote.getStuts().equals("")){
			sb.append(" and u.stuts=?");
			list.add(vote.getStuts());
		}
		if(StringUtils.isNotBlank(vote.getUserid())){//根据truename到user表查询对应的userid
			sb.append(" and  u.userid=?");
			list.add(vote.getUserid());
		}
		
		if(vote.getStartDate()!=null&&!"".equals(vote.getStartDate())){
			
			sb.append(" and u.tpCreatetime>=?");	
			list.add(vote.getStartDate());
		}
		if(vote.getEndDate()!=null&&!"".equals(vote.getEndDate())){
			
			sb.append(" and u.tpCreatetime<=?");
			list.add(vote.getEndDate());
		}
		
		data.setTotal((Long)dao.findOne("select count(*)"+sb.toString(),list));
		
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by u.tpCreatetime ,u.tpStick desc ");
		}
		
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(u.id as id,u.userid as userid,u.tpTitle as tpTitle,u.tpAnonymous as tpAnonymous," +
				"u.tpValidity as tpValidity,u.tpExpiry as tpExpiry,u.tpCreatetime as tpCreatetime,u.stuts as stuts,u.tpStick as tpStick) "
				+sb.toString(),param.getPage(),param.getRows(),list);
		for(Map<String,Object> m:rows){
			m.put("userName",MyCache.getInstance().getTrueName((String)(m.get("userid"))));
		}
		data.setRows(rows);
		return  data ;
		
	}
	
	public String addvote(TpVote vo,String[] deptid,String[] userids) {
		Collection c = new ArrayList();
		
		int num=vo.getTpValidity().compareTo(vo.getTpExpiry());
		if(num==1){//0  前等于后     1  前大于后     -1 前小于后
			return "vote.time.error";//有效日期不能大于失效日期
		}
		
		
		try{
			String id=(String)dao.saveReturnId(vo);
			for(int i=0;i<deptid.length;i++){
				TpCorrelation rel  = new TpCorrelation();
				rel.setVoteid(id);
				rel.setFkid(deptid[i]);
				rel.setType("0");
				c.add(rel);
			}
			for(int j=0;j<userids.length;j++){
				TpCorrelation rel  = new TpCorrelation();
				rel.setVoteid(id);
				rel.setFkid(userids[j]);
				rel.setType("1");
				c.add(rel);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		if(dao.saveOrUpdateAll(c)){
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		
	}
	
	public java.util.Map<String,Object> findVoteById(String id) {
		TpVote vo = dao.get(TpVote.class,id);//获取投票对象
		@SuppressWarnings("unchecked")
		List<TpCorrelation> list = dao.find(" from TpCorrelation t where t.type='0' and t.voteid=? ",id);//获取部门集合
		@SuppressWarnings("unchecked")
		List<TpCorrelation> al = dao.find(" from TpCorrelation t where t.type='1' and t.voteid=? ",id); //获取人员集合
		List<String> deptIds = new ArrayList<String>();//部门ids
		List<String> userIds = new ArrayList<String>();//人员ids
		for(int i=0;i<list.size();i++){
			TpCorrelation rel=list.get(i);
			deptIds.add(rel.getFkid());
		}
		for(int j=0;j<al.size();j++){
			TpCorrelation rel=al.get(j);
			userIds.add(rel.getFkid());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("n",vo);
		map.put("deptIds", StringUtils.join(deptIds, ","));
		map.put("userIds", StringUtils.join(userIds, ","));
		return map;
	}
	
	public String updatevote(TpVote vo,String deptid,String userid) {
		
		//待更新的对象集合
		Collection<Object> c = new ArrayList<Object>();
			
			
			TpVote oldvote=dao.get(TpVote.class,vo.getId());
			if(oldvote==null){
				return "msg.update.content";
			}
			int num=vo.getTpValidity().compareTo(vo.getTpExpiry());
			if(num==1){//0  前等于后     1  前大于后     -1 前小于后
				
				return "vote.time.error";//有效日期不能大于失效日期
			}
			
			
			if(vo.getTpAnonymous()==null){
				oldvote.setTpAnonymous((short)0);
			}else{
				oldvote.setTpAnonymous((short)1);
			}
			if(vo.getTpStick()==null){
				oldvote.setTpStick((short)0);
			}else{
				oldvote.setTpStick((short)1);
			}
			oldvote.setTpTitle(vo.getTpTitle());
			oldvote.setTpDescribe(vo.getTpDescribe());
			oldvote.setTpSeeresult(vo.getTpSeeresult());
			oldvote.setTpValidity(vo.getTpValidity());
			oldvote.setTpExpiry(vo.getTpExpiry());
			c.add(oldvote);
//*********************************************************************************************************************************************部门修改
			List<String> olddeptIds = dao.find("select fkid from TpCorrelation where voteid=? and type='0' ",vo.getId());//原有的     部门id集合
			
			Set<String> addDeptIds=new HashSet<String>();//需要发送的部门集合
			String[] deptIdsList=null;
			if(StringUtils.isNotBlank(deptid)){
				deptIdsList = deptid.split(",");
			}
			
			if(deptIdsList!=null){
					for(String id:deptIdsList){
						addDeptIds.add(id);
					}
			}
			//原先已有的-现在需要添加的==需要删除的
			List<String> delDeptIds=new ArrayList<String>(olddeptIds);//需要删除的
			delDeptIds.remove(addDeptIds);//需要删除的
			for(String id:delDeptIds){
				dao.delete(" delete TpCorrelation where fkid=? and voteid=?  ",id,vo.getId());
				
			}
			//现在需要添加的-原先已有的=需要真正添加的
			addDeptIds.remove(olddeptIds);
			for(String id:addDeptIds){
				TpCorrelation t = new TpCorrelation();
				  t.setVoteid(vo.getId());
				  t.setFkid(id);
				  t.setType("0");
				  c.add(t);
			}
//**********************************************************************************************************************************************人员修改
			List<String> olduserIds = dao.find("select fkid from TpCorrelation where voteid=? and type='1' ",vo.getId());//原有的    人员id集合
			Set<String> addUserIds=new HashSet<String>();//需要发送的人员集合
			String[] userIdsList=null;	
			if(StringUtils.isNotBlank(userid)){
				userIdsList = userid.split(",");
			}
			if(userIdsList!=null){
				for(String id:userIdsList){
					addUserIds.add(id);
				}
			}
			//原先已有的-现在需要添加的==需要删除的
			List<String> delUserIds=new ArrayList<String>(olduserIds);//需要删除的
			delUserIds.remove(addUserIds);
			for(String id:delUserIds){
				dao.delete(" delete TpCorrelation where fkid=? and voteid=? ",id,vo.getId());
			}
			//现在需要添加的-原先已有的=需要真正添加的
			addUserIds.remove(olduserIds);
			for(String id:addUserIds){
				TpCorrelation t = new TpCorrelation();
				t.setVoteid(vo.getId());
				t.setFkid(id);
				t.setType("1");
				c.add(t);
			}
			
//*************************************************************************************************************************************************
		if (dao.saveOrUpdateAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}
	
	@Override
	public boolean deletevote(String[] ids) {
		List<TpVote> list=new ArrayList<TpVote>();
		for (String id:ids){
			TpVote vote=dao.get(TpVote.class, id);
			 if(vote!=null){
				 list.add(vote);
			 }
		}
		return dao.deleteAll(list);
		
	}
	
	public String updatepropublish(String id){
		
		TpVote vo=dao.get(TpVote.class, id);
		
		if(vo==null){
			return "vote.no.data";//投票已不存在！
		}
		//查询投票所属人员
		List<Object>  userIdList  = dao.find("select c.fkid from TpCorrelation c where c.type='1' and c.voteid=? ",id);
		//查询投票所属部门
		List<Object>  deptIdList  = dao.find("select c.fkid from TpCorrelation c where c.type='0' and c.voteid=? ",id);
		String[] userIds = new String[userIdList.size()];
		String[] deptIds = new String[deptIdList.size()];
		if(userIds.length>0){
			for(int i=0;i<userIds.length;i++){
				userIds[i]=(String) userIdList.get(i);
			}
		}
		if(deptIds.length>0){
			for(int j=0;j<deptIds.length;j++){
				deptIds[j]=(String)deptIdList.get(j);
			}
		}
		
		List list=dao.find(" from TpQuestion where voteid=? ",id);
		if(list==null||list.size()==0){
			return "vote.questoin.nohave";//无子投票不能发布
			
		}else{//发布投票修改发布日期和状态
			boolean b=dao.update("update TpVote t   set t.stuts=?,t.tpPublishtime=? where t.id=?",(short)1,DateUtil.currentTimestamp(),id);
			if(b){
				
				saveMsgWarn(5,id,userIds,deptIds);
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}
	}
	
	@Override
	public String updatevalid(String[] ids) {
		for(String id:ids){
			TpVote vo=(TpVote)dao.findOne(" from TpVote where id=?",id);
			if(vo==null){
				return "vote.no.data";//投票已不存在
			}
			if(vo.getStuts()==0){
				return "vote.stuts.weifabu";//请先发布投票
			}else if(vo.getStuts()==2){
				
				dao.update("update TpVote set stuts=? where id=?",(short)1,id);
				
			}else{
				
				return "vote.stuts.yizhongzhi";//投票已为生效，请勿重复修改！
			}
		}
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	
	@Override
	public String updatedtop(String[] ids) {
		
		
		
		for(String id:ids){
			TpVote vo=(TpVote)dao.findOne(" from TpVote where id=?",id);
			if(vo==null){
				return "vote.no.data";//投票已不存在
			}
			if(vo.getStuts()==0){
				return "vote.stuts.weifabu";//请先发布投票
			}else if(vo.getStuts()==1){
				
				dao.update("update TpVote set stuts=? where id=?",(short)2,id);
				
			}else{
				
				return "vot.stuts.yishengxiao";//投票已为终止，请勿重复修改！
			}
		}
		return MsgConfig.MSG_KEY_SUCCESS;
		
	}
	
	public List<TpQuestion> loadquestion(String id){
		
		List<TpQuestion>list=dao.find(" from TpQuestion where voteid=? order by seqnum  asc ",id);
		
			
		return list;
	}
	
	@Override
	public String delztp(String id,String voteid) {
		if(dao.delete(" delete TpQuestion where id=? ",id)){
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		
	}
	
	public Map fingQuestionById(String id){
		TpQuestion tq=dao.get(TpQuestion.class, id);//获取字投票对象用于修改界面
		Map map=new HashMap();
		map.put("n",tq);
		
		return map;
	}
	
	@Override
	public String updateztp(TpQuestion tq,FList f ) {
		TpQuestion t=dao.get(TpQuestion.class,tq.getId());
		if(t==null){
			return "msg.update.content";
			
		}
		Collection<Object> c = new ArrayList<Object>();
		t.setSeqnum(tq.getSeqnum());
		t.setTitle(tq.getTitle());
		t.setType(tq.getType());
		c.add(t);
		if(f!=null){
			List<TpVotedetail> al=f.getF();
			if(al!=null&&al.size()>0){
				for(TpVotedetail vt:al){
					vt.setTitleid(tq.getId());
					c.add(vt);
				}
			}
		}
		if(dao.saveOrUpdateAll(c)){
			return MsgConfig.MSG_KEY_SUCCESS;	
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		
		
	}
	
	@Override
	public String addztp(TpQuestion tq, String[] f) {
		if(2==tq.getType()){
			dao.save(tq);
		}else{
		
			String id=(String) dao.saveReturnId(tq);//保存子投票返回id   
			if(f!=null&&f.length>0){
				for(int i=0;i<f.length;i++){
					TpVotedetail detail=new TpVotedetail();
					detail.setOptiondesc(f[i]);
					detail.setTitleid(id);
				    dao.save(detail);
				}
				
			}
		}
		return MsgConfig.MSG_KEY_SUCCESS;
	};
	
	public List fingVotedetail(String id) {
		List list=dao.find(" from TpVotedetail where titleid=? ",id);
		return list;
	};
	
	
	@Override
	public List<TpVotedetail> loaddetails(String id) {
		
		List<TpVotedetail> list = dao.find(" from TpVotedetail where titleid=?",id);
		
		return list;
	}
	
	@Override
	public String deletetpxm(String id) {
		boolean b = dao.delete(" delete TpVotedetail where id=? ",id);
		if(b){
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid loadtp(PageParam param, TpVote vote){
		DataGrid data=new DataGrid();
		Member me = ServletUtil.getMember();//获取当前登陆用户id
		StringBuffer sb=new StringBuffer("from TpVote t,TpCorrelation cor where (fkid='"+me.getId()+"' or fkid='"+me.getDeptId()+"') and t.id=cor.voteid and stuts='1'  ");
		List list=new ArrayList();
		if(StringUtils.isNotBlank(vote.getTpTitle())){
			sb.append(" and t.tpTitle like ?");
			list.add("%"+vote.getTpTitle()+"%");
		}
		data.setTotal((Long)dao.findOne("select count(distinct t.id)"+sb,list));//查询总数
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by t.tpCreatetime ,t.tpStick desc ");
		}
		
		
		//new Map(效率高);
		List<Map<String,Object>> rows= dao.findPage("select distinct  new Map(t.id as id,t.userid as userid,t.tpTitle as tpTitle,t.tpAnonymous as tpAnonymous," +
				"t.tpValidity as tpValidity,t.tpExpiry as tpExpiry,t.tpSeeresult as tpSeeresult) "+sb.toString(),param.getPage(),param.getRows(),list);
		
		for(Map<String,Object> map:rows){//发布人
			
			map.put("count", dao.findUniqueOne("select count(*) from TpCount c where c.voteid=? and c.userid=? ",(String)(map.get("id")),ServletUtil.getMember().getId()));//查询当前用户每个投票的投票总数
			map.put("userName",MyCache.getInstance().getTrueName((String)map.get("userid")));
			
		}	
		data.setRows(rows);
		return data;
	}
	
	@Override
	public List<TpQuestion> fingztpById(String id) {
		List<TpQuestion> list=dao.find(" from TpQuestion where voteid=? order by seqnum asc ",id);
		return list;
	}
	
	@Override
	public List<TpVotedetail> findtpxmById(String id) {
		
		List<TpVotedetail> list=dao.find(" from TpVotedetail where titleid=? ",id);
		
		return list;
	}
	
	@Override
	public TpVote findTpVoteById(String id) {
		TpVote vo=(TpVote) dao.findOne("from TpVote where id=? ", id);
		
		return vo;
	}

@Override
public String addpertp(FilList fil, String voteid) {
	//首先判断投票界面是否有子投票
	List l=dao.find("from TpQuestion where voteid=? ",voteid);
	if(l.size()<=0){
		return "vote.exist.ztp";//不存在子投票，不能投票!
	}
	//判断投票是否已经终止
	TpVote vote=(TpVote) dao.findOne("from TpVote where id=?",voteid);
	
	if(vote.getStuts()==2){
		return "vote.stuts.stop";//此投票已终止，不能投票！
	}
	//判断是否已经投票
	Member me = ServletUtil.getMember();//获取当前登陆用户id
	List  cl=dao.find("from TpCount where voteid=? and userid=? ",voteid,me.getId());
	
	if(cl.size()>0){
		return "vote.add.repate";//您已投票，请勿重复投票！
	}
	TpVote vo=findTpVoteById(voteid);
    Collection<Object> c = new ArrayList<Object>();
	if(fil!=null){
		List<TpCount> al=fil.getFil();
		if(al!=null&&al.size()>0){
			for(int i=0;i<al.size();i++){
				TpCount t=al.get(i);
				if(StringUtils.isNotBlank(t.getVotedetailid())){//判断单选 多选
					t.setUserid(me.getId());
					t.setVotetime(DateUtil.currentTimestamp());
					c.add(t);
				}
				if(StringUtils.isNotBlank(t.getTextarea())){//判断文本类型
					t.setUserid(me.getId());
					t.setVotetime(DateUtil.currentTimestamp());
					c.add(t);
				}
			}
		}else{
				return "vote.nodata.question";//无投票选项，无法投票
		}
		
	}
	boolean b=dao.saveOrUpdateAll(c);
	if(b){
		return MsgConfig.MSG_KEY_SUCCESS;
	}else{
		return MsgConfig.MSG_KEY_FAIL;
	}
		
}

@Override
public List queryTp(String id, String userid) {
	
	List list=dao.find(" from TpCount where voteid=? and userid=? ",id,userid );
	
	return list;
}

@Override
public int count(String voteid, String ztpid, String detailid) {
	List list=dao.find(" from TpCount where voteid=? and titleid=? and votedetailid=? ",voteid,ztpid,detailid);
	return list.size();
}

@Override
public int countVote(String id) {
	Set<SyUsers> set=new HashSet<SyUsers>();
	List list=dao.find(" from TpCorrelation where voteid=? and type='0' ",id);//该投票下所有部门集合
	List list1=dao.find(" from TpCorrelation where voteid=? and type='1' ", id);//该投票下所有人员集合
	//*****************************************************************
	for(int i=0;i<list.size();i++){
		TpCorrelation cor=(TpCorrelation) list.get(i);
		List<SyUsers> al=dao.find(" from SyUsers where deptId=? ",cor.getFkid());
		for(int j=0;j<al.size();j++){
			SyUsers user=al.get(j);
			set.add(user);
		}
	}
	//******************************************************************
	for(int h=0;h<list1.size();h++){
		TpCorrelation c=(TpCorrelation) list1.get(h);
		List<SyUsers> al=dao.find(" from SyUsers where id=? ",c.getFkid());
		for(int k=0;k<al.size();k++){
			SyUsers u=al.get(k);
			set.add(u);
		}
		
	}
	return set.size();
}

@Override
public List<Object> countArea(String voteid, String ztpid) {
	List<Object> list=dao.find(" select textarea  from TpCount where voteid=? and titleid=?  ",voteid,ztpid);
	return list;
}

@Override
public List<LookMan> lookman(String id,TpVote vote) {
	
	//TpVote vote=(TpVote) dao.findOne("from TpVote where id=? ",id);
	List<LookMan> al=new ArrayList<LookMan>();
	
	List<String> list=dao.find(" select distinct userid from TpCount where voteid=? ",id);
	if(list.size()>0){
		for(int i=0;i<list.size();i++){
			String userid =list.get(i);
			TpCount  t=(TpCount) dao.findOne("from TpCount where userid=? and voteid=? ", userid,id);
			
			LookMan man=new LookMan();
			man.setD(t.getVotetime());//投票时间
			man.setUsername(userid);//用户id
			man.setVotetitle(vote.getTpTitle());//标题
			Object obj=dao.findUniqueOne("select deptId from SyUsers where id=? ",userid);
			man.setDeptname((String)obj);
			al.add(man);
		}
	}
	
	return al;
}

	@Override
	public String delcleanDate(String id) {
		TpVote vo=dao.get(TpVote.class, id);
		if(vo==null){
			return "msg.update.content";//数据已不存在
		}
		List<TpCount> list=dao.find("from TpCount where voteid=? ",id);//删除统计记录
		if(list.size()>0){
			dao.delete(" delete TpCount where voteid=? ",id);
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return "vote.already.clean";//数据已被清空
		}
	}
	
	@Override
	public List<PerVote> selecttp(String id) {
		List<PerVote> bl=new ArrayList<PerVote>();
		List<TpQuestion> list=fingztpById(id);//子投票集合
		for(int i=0;i<list.size();i++){
			PerVote pv=new PerVote();
			TpQuestion t=list.get(i);
			pv.setQuestion(t);
			List<TpVotedetail> al = findtpxmById(t.getId());//子投票对应的选项集合
			pv.setList(al);
			bl.add(pv);
		}
		return bl;
	}
	
	@Override
	public List<Reult> selectVotingResults(String id) {
		List<Reult> bl=new ArrayList<Reult>();//用于展示投票结果界面
		List<TpQuestion> list=fingztpById(id);//得到该投票下的所有子投票集合
		for(int i=0;i<list.size();i++){
			TpQuestion t=list.get(i); 
			List<TpVotedetail> al = findtpxmById(t.getId());//得到子投票id得到对应的选项集合
			Reult r = new Reult();
			List<Options> arr=new ArrayList<Options>();//存放选项和对应投票数对象集合
			if(t.getType()==2){
				List<Object> s=countArea(id, t.getId());
				r.setStr(s);//存放文本内容
			}else{
				int allCount=0;
				for(int j=0;j<al.size();j++){
					Options option = new Options();
					TpVotedetail detail=al.get(j);
					int b=count(id, t.getId(), detail.getId());
					option.setDetail(detail);
					option.setTote(b);
					arr.add(option);
					allCount+=b;
				}
				r.setAllCount(allCount);
				for(Options o:arr){
					int c=o.getTote();
					int b=(int)(((double)c/allCount)*100);
					o.setPercentage(b);
				}
			}
			r.setList(arr);
			r.setQuestion(t);//存放子投票对象
			bl.add(r);
		}
		return bl;
	}
//*****************************************安卓********************************************************88
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryVoteById(String id) {
		Map<String,Object> map = (Map<String, Object>) dao.findUniqueOne(" select new Map(su.trueName as createName,u.tpTitle as tpTitle,"+
				"u.tpValidity as tpValidity,u.tpExpiry as tpExpiry,u.tpCreatetime as tpCreatetime,"+
				"u.tpDescribe as tpDescribe,u.tpPublishtime as tpPublishtime) "+
				"from TpVote u,SyUsers as su where u.userid=su.id and u.id=? ",id);
		if(map==null){
			return new HashMap<String,Object>();
		}else{
			List<TpCorrelation> list = dao.find(" from TpCorrelation t where t.voteid=? ",id);//获取关联集合
			
			if(list.size()>0){
				List<String> deptIds = new ArrayList<String>();//部门ids		
				List<String> userIds = new ArrayList<String>();//用户ids
				for (TpCorrelation m:list) {
					if (m.getType().equals("0")) {
						deptIds.add(m.getFkid());
					}else if(m.getType().equals("1")) {
						userIds.add(m.getFkid());
					}
				}
				String d = "";
				if(deptIds.size()>0){
					for(String s:deptIds){
						d+=MyCache.getInstance().getDeptName(s)+",";
					}
					map.put("deptNames",d.substring(0,d.length()-1));//参与部门
				}else{
					map.put("deptNames",d);//参与部门
				}
				String u = "";
				if(userIds.size()>0){
					for(String s:userIds){
					   u+=MyCache.getInstance().getTrueName(s)+",";
					}
					map.put("userNames",u.substring(0,u.length()-1));//参与人
				}else{
					map.put("userNames",u);//参与人
				}
			}else{
				map.put("deptNames","");//参与部门
				map.put("userNames","");//参与人
			}
			return map;
		}
	}
}
