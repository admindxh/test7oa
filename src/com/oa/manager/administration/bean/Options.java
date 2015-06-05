package com.oa.manager.administration.bean;
/**
 * 类名：Options
 * 功能：投票选项和对应投票数对象      用于结果展示界面	 
 * 作者：MaFanghua
 * 版本：1.0
 * 日期：2013-6-22 下午08:00:37
 */
public class Options {
		
		private TpVotedetail detail;//选项对象
		private int tote;//投票数
		/**
		 * 百分比 90% ==> 90
		 */
		private int percentage;
		
		public int getPercentage() {
			return percentage;
		}
		public void setPercentage(int percentage) {
			this.percentage = percentage;
		}
		public TpVotedetail getDetail() {
			return detail;
		}
		public void setDetail(TpVotedetail detail) {
			this.detail = detail;
		}
		public int getTote() {
			return tote;
		}
		public void setTote(int tote) {
			this.tote = tote;
		}
}
