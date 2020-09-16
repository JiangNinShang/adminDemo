package main.newer.domain;

import java.io.Serializable;

public class Jurisdiction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int jid;
	private String jname;
	private String jpath;
	private Integer state;
	
	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getJpath() {
		return jpath;
	}

	public void setJpath(String jpath) {
		jpath = jpath;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Jurisdiction() {
		// TODO Auto-generated constructor stub
	}

	public Jurisdiction(int jid, String jname, String jpath, Integer state) {
		super();
		this.jid = jid;
		this.jname = jname;
		this.jpath = jpath;
		this.state = state;
	}
	
}
