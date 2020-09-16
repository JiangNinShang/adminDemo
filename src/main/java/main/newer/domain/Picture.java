package main.newer.domain;

import java.io.Serializable;

public class Picture implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pid;
	private String pPath;
	
	public Picture() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Picture [pid=" + pid + ", pPath=" + pPath + "]";
	}

	public Picture(int pid, String pPath) {
		super();
		this.pid = pid;
		this.pPath = pPath;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getpPath() {
		return pPath;
	}

	public void setpPath(String pPath) {
		this.pPath = pPath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
