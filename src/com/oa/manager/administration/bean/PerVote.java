package com.oa.manager.administration.bean;

import java.util.List;
/**
 * 类名：PerVote
 * 功能：个人投票界面	  	 
 * 作者：MaFanghua
 * 版本：1.0
 * 日期：2013-6-17 下午9:05:37
 */
public class PerVote {
		private TpQuestion question;
		private List<TpVotedetail> list;
		public List<TpVotedetail> getList() {
			return list;
		}
		public void setList(List<TpVotedetail> list) {
			this.list = list;
		}
		public TpQuestion getQuestion() {
			return question;
		}
		public void setQuestion(TpQuestion question) {
			this.question = question;
		}
		
}
