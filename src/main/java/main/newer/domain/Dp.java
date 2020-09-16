package main.newer.domain;

import java.io.Serializable;

public class Dp implements Serializable{
	private int did;
	private int deid;
	private int pid;
	
	public Dp() {
		// TODO Auto-generated constructor stub
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getDeid() {
		return deid;
	}

	public void setDeid(int deid) {
		this.deid = deid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Dp(int did, int deid, int pid) {
		super();
		this.did = did;
		this.deid = deid;
		this.pid = pid;
	}
	
}
