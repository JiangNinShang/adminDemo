package main.newer.domain;

import java.io.Serializable;

public class Position implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pid;
	private String pname;
	private int salary;
	private Integer state;
	
	public Position() {
		// TODO Auto-generated constructor stub
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Position(int pid, String pname, int salary, Integer state) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.salary = salary;
		this.state = state;
	}
	
}
