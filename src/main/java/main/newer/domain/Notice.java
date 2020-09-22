package main.newer.domain;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{
	private int nid;
	private Date time;
	private int uid;
	private String info;
	private String zpbm;
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	public Notice(int nid, Date time, int uid, String info, String zpbm) {
		super();
		this.nid = nid;
		this.time = time;
		this.uid = uid;
		this.info = info;
		this.zpbm = zpbm;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getZpbm() {
		return zpbm;
	}
	public void setZpbm(String zpbm) {
		this.zpbm = zpbm;
	}
	
}
