package com.oa.manager.administration.bean;

import java.sql.Timestamp;


/**
 * 物品领用
 */

public class BiGoodsreceiving  implements java.io.Serializable {


    // Fields    

     /**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 关联物品id
	 */
     private String usingnameid;
     /**
      * 领用数量
      */
     private Integer usenumber;
     /**
      * 领用人id
      */
     private String recipients;
     /**
      * 领用时间
      */
     private Timestamp receiveTime;
     /**
      * 创建人id
      */
     private String userid;
     /**
      * 创建时间
      */
     private Timestamp createdate;


    // Constructors

    /** default constructor */
    public BiGoodsreceiving() {
    }

	/** minimal constructor */
    public BiGoodsreceiving(Timestamp createdate) {
        this.createdate = createdate;
    }
    
    /** full constructor */
    public BiGoodsreceiving(String usingnameid, Integer usenumber, String recipients,Timestamp receiveTime, String userid, Timestamp createdate) {
        this.usingnameid = usingnameid;
        this.usenumber = usenumber;
        this.recipients = recipients;
        this.receiveTime=receiveTime;
        this.userid = userid;
        this.createdate = createdate;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUsingnameid() {
        return this.usingnameid;
    }
    
    public void setUsingnameid(String usingnameid) {
        this.usingnameid = usingnameid;
    }

    public Integer getUsenumber() {
        return this.usenumber;
    }
    
    public void setUsenumber(Integer usenumber) {
        this.usenumber = usenumber;
    }

    public String getRecipients() {
        return this.recipients;
    }
    
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timestamp getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   








}