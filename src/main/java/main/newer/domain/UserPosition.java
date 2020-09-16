package main.newer.domain;

import java.io.Serializable;

public class UserPosition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int udid;
	private int uid;
	private int pid;
	
	public int getUdid() {
		return udid;
	}

	public void setUdid(int udid) {
		this.udid = udid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserPosition(int udid, int uid, int pid) {
		super();
		this.udid = udid;
		this.uid = uid;
		this.pid = pid;
	}

	public UserPosition() {
		// TODO Auto-generated constructor stub
	}
	
}
